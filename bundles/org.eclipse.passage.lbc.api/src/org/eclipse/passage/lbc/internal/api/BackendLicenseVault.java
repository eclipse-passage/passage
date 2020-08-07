package org.eclipse.passage.lbc.internal.api;

import java.util.Collection;

import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;

/**
 * @since 1.0
 */
public interface BackendLicenseVault {

	Collection<ConditionPack> availableLicenses(MiningRequest request);

}
