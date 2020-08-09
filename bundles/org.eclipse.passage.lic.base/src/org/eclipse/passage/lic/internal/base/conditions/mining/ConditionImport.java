/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.base.conditions.mining;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.eclipse.passage.lic.base.io.NullStreamCodec;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.diagnostic.code.ServiceFailedOnInfrastructureDenial;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;
import org.eclipse.passage.lic.internal.base.io.LicensingFolder;
import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;
import org.eclipse.passage.lic.internal.base.io.PathFromLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.UserHomePath;

/**
 * Imports conditions for a given {@link LicensedProduct}
 * 
 * @since 1.0
 *
 */
public class ConditionImport {

	private final LicensedProduct product;

	public ConditionImport(LicensedProduct product) {
		this.product = product;
	}

	/**
	 * Imports LicensingConditions from the given source
	 * 
	 * @param source the path to import from
	 * @return the path to imported condition
	 */
	public ServiceInvocationResult<String> from(String source) {
		Path configurationPath = new PathFromLicensedProduct(new LicensingFolder(new UserHomePath()), product).get();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss-SSS", Locale.ENGLISH); //$NON-NLS-1$
		String fileName = dateFormat.format(new Date()) + new PassageFileExtension.LicenseEncrypted().get();
		File dest = configurationPath.resolve(fileName).toFile();
		File destParent = dest.getParentFile();
		if (!destParent.exists()) {
			boolean mkdirs = destParent.mkdirs();
			if (!mkdirs) {
				return new BaseServiceInvocationResult<>(new Trouble(new ServiceFailedOnInfrastructureDenial(), //
						String.format(BaseMessages.getString("BaseConditionMinerRegistry.e_dest_create_failed"), //$NON-NLS-1$
								destParent)));
			}
		}
		try (FileInputStream fis = new FileInputStream(source); FileOutputStream fos = new FileOutputStream(dest)) {
			NullStreamCodec.transfer(fis, fos);
			return new BaseServiceInvocationResult<String>(fileName);
		} catch (Exception e) {
			String message = String
					.format(BaseMessages.getString("BaseConditionMinerRegistry_lic_conditions_import_failed"), source); //$NON-NLS-1$
			return new BaseServiceInvocationResult<>(
					new Trouble(new ServiceFailedOnInfrastructureDenial(), message, e));
		}
	}

}
