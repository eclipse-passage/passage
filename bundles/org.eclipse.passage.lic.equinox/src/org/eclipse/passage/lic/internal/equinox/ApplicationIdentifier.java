package org.eclipse.passage.lic.internal.equinox;

import java.util.function.Supplier;

import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.passage.lic.internal.base.BaseProductInfo;
import org.eclipse.passage.lic.internal.base.InvalidLicensingConfiguration;

@SuppressWarnings("restriction")
public final class ApplicationIdentifier implements Supplier<String> {
	private final IApplicationContext context;

	public ApplicationIdentifier(IApplicationContext context) {
		this.context = context;
	}

	@Override
	public String get() {

		String property = new BaseProductInfo.Identifier(context::getBrandingProperty).get();
		if (property != null) {
			return property;
		}
		String brandingId = context.getBrandingId();
		if (brandingId != null) {
			return brandingId;
		}
		String applicationId = context.getBrandingApplication();
		if (applicationId != null) {
			return applicationId;
		}
		// OK, no more ideas
		return new InvalidLicensingConfiguration().getProductIdentifier();
	}

}
