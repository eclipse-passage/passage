package org.eclipse.passage.lic.internal.equinox;

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.equinox.i18n.EquinoxMessages;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("restriction")
final class FrameworkSupplier implements Supplier<Optional<Framework>> {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public Optional<Framework> get() {
		BundleContext context = FrameworkUtil.getBundle(getClass()).getBundleContext();
		try {
			return context.getServiceReferences(Framework.class, null).stream() //
					.map(context::getService) //
					.filter(s -> s.getClass() == EquinoxFramework.class) //
					.findAny();
		} catch (InvalidSyntaxException e) {
			log.error(EquinoxMessages.EquinoxPassage_no_framework, e);
			return Optional.empty();
		}
	}

}
