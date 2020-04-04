package org.eclipse.passage.lic.internal.equinox.requirements;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.util.Collection;
import java.util.Optional;

import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;

@SuppressWarnings("restriction")
public final class BundleRequirementsTest {

	@Test
	public void providedAsResolvedRequirementsImpl() throws InvalidSyntaxException {
		assertTrue(mayBeService().isPresent());
	}

	@Test
	public void allRequirements() throws InvalidSyntaxException {
		Collection<Requirement> list = service().all();
		assertTrue(list.size() > 2); // at least two are declared in our test data bundle
	}

	private Optional<ResolvedRequirements> mayBeService() throws InvalidSyntaxException {
		BundleContext context = FrameworkUtil.getBundle(getClass()).getBundleContext();
		return context.getServiceReferences(ResolvedRequirements.class, null).stream() //
				.map(s -> context.getService(s)) //
				.filter(s -> s.getClass() == BundleRequirements.class) //
				.findAny();
	}

	private ResolvedRequirements service() throws InvalidSyntaxException {
		Optional<ResolvedRequirements> service = mayBeService();
		assumeTrue(service.isPresent());
		return service.get();
	}
}
