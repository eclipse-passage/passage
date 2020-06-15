package org.eclipse.passage.lic.api.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;

import org.eclipse.passage.lic.api.tests.fakes.FakeRequirement;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;
import org.junit.Test;

@SuppressWarnings("restriction")
public abstract class ResolvedReqiorementsContractTest {
	
	@Test
	public void prohibitsInjectionIntoResolvedRequirements() {
		testInterferenceResistence(shared -> shared.add(new FakeRequirement()));
		testInterferenceResistence(shared -> shared.addAll(Collections.singleton(new FakeRequirement())));
	}
	
	@Test
	public void prohibitsRemovalFromResolvedRequirements() {
		assumeTrue(service().all().size() > 0);
		testInterferenceResistence(Collection::clear);
		testInterferenceResistence(shared -> shared.retainAll(Collections.emptyList()));
		testInterferenceResistence(shared -> shared.removeAll(shared));
		testInterferenceResistence(shared -> shared.remove(shared.iterator().next()));
	}
	
	private void testInterferenceResistence(Consumer<Collection<Requirement>> interference) {
		// given
		ResolvedRequirements service = service();
		Collection<Requirement> shared = service.all();
		int before = shared.size();
		// when
		shared.clear();
		// then
		assertTrue(before == service.all().size());
	}
	
	protected abstract ResolvedRequirements service();

}
