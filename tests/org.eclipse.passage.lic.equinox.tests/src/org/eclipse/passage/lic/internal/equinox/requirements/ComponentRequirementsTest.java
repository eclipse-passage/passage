package org.eclipse.passage.lic.internal.equinox.requirements;

import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;

@SuppressWarnings("restriction")
public final class ComponentRequirementsTest {

	@Test
	public void providedAsResolvedRequirementsImpl() throws InvalidSyntaxException {
		BundleContext context = FrameworkUtil.getBundle(getClass()).getBundleContext();
		assertTrue(context.getServiceReferences(ResolvedRequirements.class, null).stream() //
				.map(s -> context.getService(s)) //
				.filter(s -> s.getClass() == ComponentRequirements.class) //
				.findAny() //
				.isPresent());
	}

}
