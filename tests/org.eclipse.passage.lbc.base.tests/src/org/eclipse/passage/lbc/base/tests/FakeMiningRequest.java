package org.eclipse.passage.lbc.base.tests;

import org.eclipse.passage.lbc.internal.api.BackendUser;
import org.eclipse.passage.lbc.internal.api.MiningRequest;
import org.eclipse.passage.lic.internal.base.ProductIdentifier;
import org.eclipse.passage.lic.internal.base.ProductVersion;

public class FakeMiningRequest implements MiningRequest {

	@Override
	public ProductIdentifier getProductIdentifier() {
		return new ProductIdentifier("identifier"); //$NON-NLS-1$
	}

	@Override
	public ProductVersion getProductVersion() {
		return new ProductVersion("version"); //$NON-NLS-1$
	}

	@Override
	public BackendUser getUser() {
		return new BackendUser("temporal"); //$NON-NLS-1$
	}

}
