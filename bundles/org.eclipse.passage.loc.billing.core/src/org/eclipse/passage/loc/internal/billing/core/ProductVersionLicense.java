package org.eclipse.passage.loc.internal.billing.core;

import java.util.Objects;

import org.eclipse.passage.lic.users.UserLicenseDescriptor;

public class ProductVersionLicense {

	private final String product;
	private final String version;

	public ProductVersionLicense(UserLicenseDescriptor license) {
		this.product = license.getProductIdentifier();
		this.version = license.getProductVersion();
	}

	public String getProduct() {
		return product;
	}

	public String getVersion() {
		return version;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		ProductVersionLicense license = (ProductVersionLicense) obj;
		return Objects.equals(getProduct(), license.getProduct()) && Objects.equals(getVersion(), license.getVersion());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getProduct(), getVersion());
	}

	@Override
	public String toString() {
		return getProduct() + ":" + getVersion(); //$NON-NLS-1$
	}

}
