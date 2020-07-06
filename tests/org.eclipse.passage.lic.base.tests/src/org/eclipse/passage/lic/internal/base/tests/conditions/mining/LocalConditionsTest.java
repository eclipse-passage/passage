/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.internal.base.tests.conditions.mining;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionMiningException;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.conditions.mining.MiningEquipment;
import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;
import org.eclipse.passage.lic.internal.base.registry.ReadOnlyRegistry;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

@SuppressWarnings("restriction")
public final class LocalConditionsTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void minesConditions() throws IOException {
		// given
		List<String> features = Arrays.asList("A", "B", "C"); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		writePseudoLicenseFile(features);
		Spy spy = new Spy();
		Collection<Condition> condition;
		// when
		try {
			condition = new TempFolderResidentConditions(//
					folder.getRoot().toPath(), equipment(spy), spy)//
							.all(product());
		} catch (ConditionMiningException e) {
			fail("Local conition mining on the valid infrastructure and data is not supposed to fail"); //$NON-NLS-1$
			throw new RuntimeException(); // unreachable
		}
		// then
		assertNoPerLicenseExceptions(spy);
		assertCrutialServicesHaveBeenProperlyInvolved(spy);
		assertMiningResultsAreOk(features, condition);
	}

	@Test(expected = NullPointerException.class)
	public void miningPathIsMandatoryOnRuntime() throws ConditionMiningException {
		new TempFolderResidentConditions(null, equipment(new Spy()), new Spy()).all(product());
	}

	@Test(expected = NullPointerException.class)
	public void miningEquipmentIsMandatory() {
		new TempFolderResidentConditions(Paths.get("."), null, new Spy()); //$NON-NLS-1$
	}

	@Test(expected = NullPointerException.class)
	public void perLicenseExceptionHandlerIsMandatory() {
		new TempFolderResidentConditions(Paths.get("."), equipment(new Spy()), null); //$NON-NLS-1$
	}

	private void assertMiningResultsAreOk(List<String> features, Collection<Condition> condition) {
		assertEquals(features.size(), condition.size());
		assertEquals(//
				new HashSet<>(features), //
				condition.stream()//
						.map(Condition::feature)//
						.collect(Collectors.toSet()));
	}

	private void assertNoPerLicenseExceptions(Spy spy) {
		assertEquals(0, spy.perLicenseFailures);
	}

	private void assertCrutialServicesHaveBeenProperlyInvolved(Spy spy) {
		assertTrue("Product public key has not been asked", spy.keyAsked); //$NON-NLS-1$
		assertEquals("License has been decoded incorrectly", 3, spy.decoded); //$NON-NLS-1$
		assertEquals("Conditions has been transported incorrectly", 3, spy.transported); //$NON-NLS-1$
	}

	private MiningEquipment equipment(Spy spy) {
		return new MiningEquipment(//
				() -> new ReadOnlyRegistry<>(new RuntimeKeyKeeper(product(), spy)), //
				() -> new ReadOnlyRegistry<>(new EchoStreamCodec(product(), spy)), //
				() -> new ReadOnlyRegistry<>(new HeadOnlyTransport(spy)));
	}

	private LicensedProduct product() {
		return new BaseLicensedProduct("test-local-condition-minder-product", "1.0.0"); //$NON-NLS-1$//$NON-NLS-2$
	}

	private void writePseudoLicenseFile(List<String> features) throws IOException {
		LicensedProduct product = product();
		folder.newFolder(product.identifier());
		folder.newFolder(Paths.get(product.identifier()).resolve(product.version()).toString());
		File lic = folder.newFile(//
				Paths.get(product.identifier())//
						.resolve(product.version())//
						.resolve("fake-license" + new PassageFileExtension.LicenseEncrypted().get()) //$NON-NLS-1$
						.toString());
		try (PrintWriter writer = new PrintWriter(new FileWriter(lic))) {
			features.forEach(writer::println);
		}
	}

	static final class Spy implements Consumer<LicensingException> {

		private boolean keyAsked = false;
		private int decoded = 0;
		private int transported = 0;
		private int perLicenseFailures = 0;

		void keyAsked() {
			keyAsked = true;
		}

		void decodeLine() {
			decoded++;
		}

		void transportLine() {
			transported++;
		}

		@Override
		public void accept(LicensingException e) {
			perLicenseFailures++;
		}
	}

}
