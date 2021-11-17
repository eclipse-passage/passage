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
package org.eclipse.passage.seal.demo.tests.condition.mining;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.api.conditions.ValidityPeriodClosed;
import org.eclipse.passage.lic.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.api.conditions.mining.LicenseReadingService;
import org.eclipse.passage.lic.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.base.BaseLicensedProduct;
import org.eclipse.passage.lic.base.conditions.MatchingRuleCompatible;
import org.eclipse.passage.lic.base.conditions.mining.BaseLicenseReadingService;
import org.eclipse.passage.lic.base.conditions.mining.PersonalLicenseMiningEquipment;
import org.eclipse.passage.lic.base.io.PathFromLicensedProduct;
import org.eclipse.passage.lic.base.registry.ReadOnlyRegistry;
import org.eclipse.passage.lic.bc.BcStreamCodec;
import org.eclipse.passage.lic.equinox.io.BundleKeyKeeper;
import org.eclipse.passage.lic.licenses.model.transport.XmiConditionTransport;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * Integration test: demands OSGi running
 */
public final class BaseLicenseReadingServiceTest {

	private final String licFile = "e121463c-309e-43a7-b199-189c84ffb753.licen"; //$NON-NLS-1$

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void test() throws Exception {
		ServiceInvocationResult<Collection<ConditionPack>> result = service().read(license());
		assertTrue(result.diagnostic().severe().isEmpty());
		assertTrue(result.diagnostic().bearable().isEmpty());
		assertTrue(result.data().isPresent());
		assertEquals(1, result.data().get().size());
		assertPackState(result.data().get().iterator().next());
	}

	private void assertPackState(ConditionPack pack) throws Exception {
		assertTrue(pack.origin().coordinates().endsWith(licFile));
		assertEquals(1, pack.conditions().size());
		assertConditionState(pack.conditions().iterator().next());
	}

	private void assertConditionState(Condition condition) {
		assertEquals("0.1.0", condition.versionMatch().version()); //$NON-NLS-1$
		assertEquals(new MatchingRuleCompatible(), condition.versionMatch().rule()); // $NON-NLS-1$
		assertEquals("prince-to-frog", condition.feature()); //$NON-NLS-1$
		assertEquals("os=win", condition.evaluationInstructions().expression()); //$NON-NLS-1$
		assertEquals(new EvaluationType.Hardware(), condition.evaluationInstructions().type());
		// FIXME: check validity period legally after #566015 is fixed
		assertTrue(ValidityPeriodClosed.class.isInstance(condition.validityPeriod()));
		assertValidityPeriodState((ValidityPeriodClosed) condition.validityPeriod());
	}

	private void assertValidityPeriodState(ValidityPeriodClosed period) {
		assertDatesEqual(LocalDate.of(2020, Month.AUGUST, 20), period.from());
		assertDatesEqual(LocalDate.of(2021, Month.AUGUST, 20), period.to());
	}

	private void assertDatesEqual(LocalDate expected, ZonedDateTime actual) {
		assertEquals(Date.from(ZonedDateTime.of(//
				expected, //
				LocalTime.of(0, 0, 0), //
				ZoneId.of("Europe/Moscow")).toInstant()).getTime(), //$NON-NLS-1$
				Date.from(actual.toInstant()).getTime());
	}

	private LicenseReadingService service() {
		return new BaseLicenseReadingService(product(),
				new PersonalLicenseMiningEquipment(keyKeeper(), codecs(), transports()));
	}

	private KeyKeeperRegistry keyKeeper() {
		return () -> new ReadOnlyRegistry<>(Collections.singleton(new BundleKeyKeeper(//
				this::product, //
				bundle(), //
				residence().get().toString()//
		)));
	}

	private StreamCodecRegistry codecs() {
		return () -> new ReadOnlyRegistry<>(Collections.singleton(new BcStreamCodec(this::product)));
	}

	private ConditionTransportRegistry transports() {
		return () -> new ReadOnlyRegistry<>(Collections.singleton(new XmiConditionTransport()));
	}

	private Path license() throws URISyntaxException, IOException {
		Path license = licensePath();
		LicensedProduct product = product();
		File dir = folder.newFolder(product.identifier(), product.version());
		File target = new File(dir, license.getFileName().toString());
		try (InputStream input = bundle().getResource(license.toString()).openStream();
				OutputStream output = new FileOutputStream(target)) {
			output.write(input.readAllBytes());
		}
		return target.toPath();
	}

	private LicensedProduct product() {
		return new BaseLicensedProduct("anti-human-magic", "0.2"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private Supplier<Path> residence() {
		return new PathFromLicensedProduct(//
				() -> Paths.get("resource/org.eclipse.passage.lic.seal.demo.tests.conditions.mining"), //$NON-NLS-1$
				product());
	}

	private Path licensePath() {
		return residence().get().resolve("e121463c-309e-43a7-b199-189c84ffb753.licen"); //$NON-NLS-1$
	}

	private Bundle bundle() {
		return FrameworkUtil.getBundle(getClass());
	}

}
