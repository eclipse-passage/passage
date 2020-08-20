package org.eclipse.passage.lic.internal.equinox;

import java.util.Optional;
import java.util.function.Function;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.FrameworkSupplier;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.diagnostic.code.NoFramework;
import org.eclipse.passage.lic.internal.equinox.i18n.AccessMessages;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

@SuppressWarnings("restriction")
abstract class FrameworkAware {

	private final String friend = "org.eclipse.passage.seal.demo"; //$NON-NLS-1$

	private final BundleContext context;

	protected FrameworkAware() {
		this.context = FrameworkUtil.getBundle(getClass()).getBundleContext();
	}

	protected Optional<ServiceReference<FrameworkSupplier>> frameworkIfAny() {
		try {
			return context.getServiceReferences(FrameworkSupplier.class, null).stream() //
					// DI is used only to get rid of overwhelming dependencies here
					// here we can check signature of the seal
					.filter(supplier -> supplier.getBundle().getSymbolicName().equals(friend))//
					.findAny();
		} catch (InvalidSyntaxException e) {
			return Optional.empty();
		}
	}

	private <T> ServiceInvocationResult<T> noFramework() {
		return new BaseServiceInvocationResult<T>(//
				new Trouble(//
						new NoFramework(), //
						String.format(AccessMessages.EquinoxPassage_no_framewrok, friend))//
		);
	}

	protected <T> ServiceInvocationResult<T> withFramework(Function<Framework, ServiceInvocationResult<T>> invoke) {
		Optional<ServiceReference<FrameworkSupplier>> candidate = frameworkIfAny();
		if (!candidate.isPresent()) {
			return noFramework();
		}
		ServiceReference<FrameworkSupplier> reference = candidate.get();
		try {
			Optional<Framework> framework = Optional.ofNullable(context.getService(reference))//
					.flatMap(FrameworkSupplier::get);
			if (!framework.isPresent()) {
				return noFramework();
			}
			return invoke.apply(framework.get());
		} finally {
			context.ungetService(reference);
		}
	}
}
