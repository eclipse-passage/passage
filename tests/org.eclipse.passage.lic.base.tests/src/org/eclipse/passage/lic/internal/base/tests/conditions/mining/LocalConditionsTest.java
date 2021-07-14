/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.base.BaseLicensedProduct;
import org.eclipse.passage.lic.base.conditions.mining.PathResidentConditions;
import org.eclipse.passage.lic.base.conditions.mining.PersonalLicenseMiningEquipment;
import org.eclipse.passage.lic.base.io.PassageFileExtension;
import org.eclipse.passage.lic.base.registry.ReadOnlyRegistry;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public final class LocalConditionsTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void minesConditions() throws IOException {
		// given
		List<String> features = Arrays.asList("A", "B", "C"); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		writePseudoLicenseFile(features);
		Spy spy = new Spy();
		// when
		ServiceInvocationResult<Collection<ConditionPack>> conditions = //
				new PathResidentConditions(//
						folder.getRoot().toPath(), equipment(spy)).all(product());
		// then
		assertCrutialServicesHaveBeenProperlyInvolved(spy);
		assertMiningResultsAreOk(features, conditions);
	}

	@Test(expected = NullPointerException.class)
	public void miningPathIsMandatoryOnRuntime() {
		new PathResidentConditions(null, equipment(new Spy())).all(product());
	}

	@Test(expected = NullPointerException.class)
	public void miningEquipmentIsMandatory() {
		new PathResidentConditions(Paths.get("."), null); //$NON-NLS-1$
	}

	private void assertMiningResultsAreOk(List<String> features,
			ServiceInvocationResult<Collection<ConditionPack>> result) {
		assertTrue(result.diagnostic().severe().isEmpty());
		assertTrue(result.data().isPresent());
		Collection<ConditionPack> packs = result.data().get();
		assertEquals(1, packs.size());
		Collection<Condition> conditions = packs.iterator().next().conditions();
		assertEquals(features.size(), conditions.size());
		assertEquals(//
				new HashSet<>(features), //
				conditions.stream()//
						.map(Condition::feature)//
						.collect(Collectors.toSet()));
	}

	private void assertCrutialServicesHaveBeenProperlyInvolved(Spy spy) {
		assertTrue("Product public key has not been asked", spy.keyAsked); //$NON-NLS-1$
		assertEquals("License has been decoded incorrectly", 3, spy.decoded); //$NON-NLS-1$
		assertEquals("Conditions has been transported incorrectly", 3, spy.transported); //$NON-NLS-1$
	}

	private PersonalLicenseMiningEquipment equipment(Spy spy) {
		return new PersonalLicenseMiningEquipment(//
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

	static final class Spy {

		private boolean keyAsked = false;
		private int decoded = 0;
		private int transported = 0;

		void keyAsked() {
			keyAsked = true;
		}

		void decodeLine() {
			decoded++;
		}

		void transportLine() {
			transported++;
		}

	}

}
