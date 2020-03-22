package org.eclipse.passage.lic.internal.equinox;

import java.util.function.Supplier;

import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.internal.base.BaseLicensingConfiguration;

@SuppressWarnings("restriction")
public final class ApplicationConfiguration implements Supplier<LicensingConfiguration> {

	private final IApplicationContext context;

	public ApplicationConfiguration(IApplicationContext context) {
		this.context = context;
	}

	@Override
	public LicensingConfiguration get() {
		return new BaseLicensingConfiguration(//
				new ApplicationIdentifier(context).get(), //
				new ApplicationVersion(context).get());
	}

}
