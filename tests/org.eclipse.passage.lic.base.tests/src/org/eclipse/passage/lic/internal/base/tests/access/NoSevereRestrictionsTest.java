package org.eclipse.passage.lic.internal.base.tests.access;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.tests.fakes.conditions.FakeLicensedProduct;
import org.eclipse.passage.lic.api.tests.fakes.requirements.FakeRequirement;
import org.eclipse.passage.lic.internal.api.diagnostic.TroubleCode;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel;
import org.eclipse.passage.lic.internal.base.restrictions.BaseExaminationCertificate;
import org.eclipse.passage.lic.internal.base.restrictions.BaseRestriction;
import org.eclipse.passage.lic.internal.base.restrictions.NoSevereRestrictions;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class NoSevereRestrictionsTest {

	@Test
	public void noRestrictionLevelPass() {
		assertTrue(new NoSevereRestrictions().test(certificate()));
	}

	@Test
	public void singleInfoRestrictionLevelPasses() {
		assertTrue(new NoSevereRestrictions().test(certificate(new RestrictionLevel.Info())));
	}

	@Test
	public void singleWarningRestrictionLevelPasses() {
		assertTrue(new NoSevereRestrictions().test(certificate(new RestrictionLevel.Warning())));
	}

	@Test
	public void singleErrorRestrictionLevelFails() {
		assertFalse(new NoSevereRestrictions().test(certificate(new RestrictionLevel.Error())));
	}

	@Test
	public void singleFatalRestrictionLevelFails() {
		assertFalse(new NoSevereRestrictions().test(certificate(new RestrictionLevel.Error())));
	}

	@Test
	public void singleUnexpectedRestrictionLevelFails() {
		assertFalse(new NoSevereRestrictions().test(certificate(new RestrictionLevel.Error())));
	}

	@Test
	public void singleFailureIsContageous() {
		assertFalse(new NoSevereRestrictions().test(certificate(//
				new RestrictionLevel.Error(), //
				new RestrictionLevel.Info())));
	}

	@Test
	public void unexpectedRestrictionLevelFailureIsContageous() {
		assertFalse(new NoSevereRestrictions().test(certificate(//
				new RestrictionLevel.Info(), //
				new RestrictionLevel.Of("some-unexpected-level")))); //$NON-NLS-1$
	}

	private ExaminationCertificate certificate(RestrictionLevel... levels) {
		return new BaseExaminationCertificate(//
				Collections.emptyList(), //
				Arrays.stream(levels) //
						.map(level -> new BaseRestriction( //
								new FakeLicensedProduct(), //
								new FakeRequirement(level), //
								new TroubleCode.Of(777, "test demand"))) //$NON-NLS-1$
						.collect(Collectors.toList())//
		);
	}

}
