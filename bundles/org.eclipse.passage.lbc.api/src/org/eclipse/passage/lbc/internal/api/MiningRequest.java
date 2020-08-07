package org.eclipse.passage.lbc.internal.api;

import org.eclipse.passage.lic.internal.base.ProductIdentifier;
import org.eclipse.passage.lic.internal.base.ProductVersion;

/**
 * @since 1.0
 */
public interface MiningRequest {

	ProductIdentifier getProductIdentifier();

	ProductVersion getProductVersion();

	BackendUser getUser();

}
