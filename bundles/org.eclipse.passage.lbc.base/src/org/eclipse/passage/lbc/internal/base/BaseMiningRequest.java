package org.eclipse.passage.lbc.internal.base;

import org.eclipse.passage.lbc.internal.api.MiningRequest;
import org.eclipse.passage.lbc.internal.api.BackendUser;
import org.eclipse.passage.lic.internal.base.ProductIdentifier;
import org.eclipse.passage.lic.internal.base.ProductVersion;

/**
 * @since 1.0
 */
public class BaseMiningRequest implements MiningRequest {

	private final ProductIdentifier identifier;
	private final ProductVersion version;
	private final BackendUser user;

	public BaseMiningRequest(ProductIdentifier identifier, ProductVersion version, BackendUser user) {
		this.identifier = identifier;
		this.version = version;
		this.user = user;
	}

	@Override
	public ProductIdentifier getProductIdentifier() {
		return identifier;
	}

	@Override
	public ProductVersion getProductVersion() {
		return version;
	}

	@Override
	public BackendUser getUser() {
		return user;
	}

}
