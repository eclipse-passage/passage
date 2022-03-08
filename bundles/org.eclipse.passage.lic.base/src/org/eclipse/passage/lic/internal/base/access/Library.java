/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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
package org.eclipse.passage.lic.internal.base.access;

import java.nio.file.Path;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.PassageLicenseCoverage;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.agreements.AgreementAcceptanceService;
import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.conditions.mining.LicenseReadingService;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.diagnostic.code.ForeignLicense;

/**
 * <p>
 * Represent a library's licensing aspect for an owning product.
 * </p>
 * <p>
 * Implementation must be stateless: service is to be instantiated as many times
 * as it is appealed to, no caching to perform for scanned extensions.
 * </p>
 * <p>
 * A library appeals to it's own standalone {@linkplain Framewrok} instance when
 * checks license coverage for its features. Nothing to be contributed to the
 * product {@code access cycle} for this scenario.
 * </p>
 * <p>
 * However, a library should participate in
 * </p>
 * 
 * <ul>
 * 
 * <li>1. the owning product license coverage assessment</li>
 * 
 * <li>2. bulk license import (prevent end user from dealing with all the
 * product-libraries relations)</li>
 * 
 * </ul>
 * 
 * <p>
 * smoothly, so it should
 * </p>
 * 
 * <ul>
 * 
 * <li>1.1. supply it's own assessment results when asked: {@code access()}</li>
 * 
 * <li>1.2. facilitate acceptance of a license agreement, in case it demands
 * some for acceptance:
 * {@code agreementsService(AgreementToAccept agreement)}</li>
 * 
 * <li>2.1. supply a {@linkplain LicenseReadingService} that will read licensing
 * {@linkplain Condition}s from a license file, in case it can be treated a this
 * library's license</li>
 * 
 * <li>2.2. provide a way to install a license (it defined to be this library's
 * license) to path configured for the library as <i>license residence</i>:
 * {@code installLicense(Path license)}.</li>
 * 
 * </ul>
 * 
 */
public interface Library extends PassageLicenseCoverage {

	/**
	 * Library represents its own <i>product</i>.
	 */
	LicensedProduct product();

	/**
	 * Request a library to perform full license coverage assessment.
	 */
	@Override
	ServiceInvocationResult<ExaminationCertificate> assess();

	/**
	 * <p>
	 * For a given {@code agreement} a library should perform an analysis if this
	 * agreement is demanded by it, and in this case supply an instance and
	 * {@linkplain AgreementAcceptanceService} for acceptance.
	 * </p>
	 * <p>
	 * If the {@code agreement} does not belong to the library, report empty result
	 * with bearable diagnostic of {@linkplain ForeignLicense} trouble code.
	 * </p>
	 */
	ServiceInvocationResult<AgreementAcceptanceService> agreementsService(AgreementToAccept agreement);

	/**
	 * Supply license reading service configured for the library to be used for
	 * imported license analysis.
	 */
	ServiceInvocationResult<LicenseReadingService> licenseReadingService();

	/**
	 * <p>
	 * If the given {@code license} relates to the library, it should install to the
	 * license residence, configured for this library's
	 * {@code access cycle configuration}
	 * </p>
	 * 
	 * <p>
	 * If the {@code license} file does not belong to the library, report negative
	 * result with bearable diagnostic of {@linkplain ForeignLicense} trouble code.
	 * </p>
	 * <p>
	 * If the {@code license} file can be read, but was not imported for some
	 * reason, report negative result with severe trouble in diagnostic.
	 * </p>
	 * <p>
	 * In case the {@code license} has been actually installed, report positive
	 * result, optionally with whatever informative diagnostic.
	 * </p>
	 * 
	 * @return diagnosed result of installation: whether installation of the
	 *         {@code license} has been actually performed or not
	 */
	ServiceInvocationResult<Boolean> installLicense(Path license);

}
