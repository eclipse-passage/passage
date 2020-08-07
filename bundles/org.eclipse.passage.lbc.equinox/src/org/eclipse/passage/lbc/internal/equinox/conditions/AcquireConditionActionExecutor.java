/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lbc.internal.equinox.conditions;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.passage.lbc.api.BackendActionExecutor;
import org.eclipse.passage.lbc.internal.api.BackendLicenseVault;
import org.eclipse.passage.lbc.internal.api.BackendUser;
import org.eclipse.passage.lbc.internal.api.MiningRequest;
import org.eclipse.passage.lbc.internal.base.BaseLicenseVault;
import org.eclipse.passage.lbc.internal.base.BaseMiningRequest;
import org.eclipse.passage.lbc.internal.equinox.i18n.EquinoxMessages;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.api.conditions.ConditionActions;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.base.ProductIdentifier;
import org.eclipse.passage.lic.internal.base.ProductVersion;
import org.eclipse.passage.lic.net.LicensingNet;
import org.osgi.service.component.annotations.Component;

/**
 * According to AccessManager specification implementation of
 * {@code Iterable<ConditionDescriptor> extractConditions(Object configuration)}
 * {@link org.eclipse.passage.lic.api.access.AccessManager}
 */
@Component(property = LicensingNet.ACTION + '=' + ConditionActions.ACQUIRE)
public class AcquireConditionActionExecutor implements BackendActionExecutor {

	private final BackendLicenseVault baseLicenseVault = new BaseLicenseVault();

	@Override
	public LicensingResult executeAction(HttpServletRequest request, HttpServletResponse response) {
		String source = getClass().getName();
		try {
			Collection<ConditionPack> availableLicenses = baseLicenseVault.availableLicenses(miningRequest(request));
			Resource resource = new XMIResourceImpl();
			resource.getContents().addAll(transferable(availableLicenses));
			resource.save(response.getOutputStream(), new HashMap<>());
			response.setContentType(request.getContentType());
			return LicensingResults.createOK(EquinoxMessages.AcquireConditionActionExecutor_k_mined, source);
		} catch (IOException e) {
			String error = EquinoxMessages.AcquireConditionActionExecutor_e_mining_failed;
			return LicensingResults.createError(error, source, e);
		}
	}

	private MiningRequest miningRequest(HttpServletRequest request) {
		ProductIdentifier productId = new ProductIdentifier(key -> request.getParameter(key));
		ProductVersion productVersion = new ProductVersion(key -> request.getParameter(key));
		BackendUser user = new BackendUser(key -> request.getParameter(key));
		MiningRequest miningRequest = new BaseMiningRequest(productId, productVersion, user);
		return miningRequest;
	}

	private List<TransferableConditionPack> transferable(Collection<ConditionPack> licenses) {
		return licenses.stream().map(pack -> new TransferableConditionPack(pack)).collect(Collectors.toList());
	}

}
