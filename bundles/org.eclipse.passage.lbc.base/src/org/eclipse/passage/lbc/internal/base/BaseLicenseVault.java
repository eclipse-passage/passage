package org.eclipse.passage.lbc.internal.base;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.passage.lbc.internal.api.BackendLicenseVault;
import org.eclipse.passage.lbc.internal.api.MiningRequest;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.base.conditions.BaseConditionPack;

/**
 * @since 1.0
 */
public class BaseLicenseVault implements BackendLicenseVault {

	// Returns a list of one ConditionPack
	@Override
	public Collection<ConditionPack> availableLicenses(MiningRequest request) {
		return Collections.singletonList(new BaseConditionPack("floating", Collections.emptyList())); //$NON-NLS-1$
	}

}
