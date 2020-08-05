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
package org.eclipse.passage.lic.internal.equinox;

import java.util.Optional;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.FrameworkSupplier;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.Passage;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.access.Access;
import org.eclipse.passage.lic.internal.base.diagnostic.code.NoFramework;
import org.eclipse.passage.lic.internal.equinox.i18n.AccessMessages;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;

@SuppressWarnings("restriction")
public final class EquinoxPassage implements Passage {

	private final String friend = "org.eclipse.passage.seal.internal.demo.DemoFrameworkSupplier"; //$NON-NLS-1$

	@Override
	public boolean canUse(String feature) {
		Optional<Framework> framework = frameworkIfAny();
		if (!framework.isPresent()) {
			return false;
		}
		return new Access(framework.get()).canUse(feature);
	}

	@Override
	public ServiceInvocationResult<ExaminationCertificate> checkLicense(String feature) {
		Optional<Framework> framework = frameworkIfAny();
		if (!framework.isPresent()) {
			return noFramework();
		}
		return new Access(framework.get()).check(feature);
	}

	@Override
	public ServiceInvocationResult<LicensedProduct> product() {
		Optional<Framework> framework = frameworkIfAny();
		if (!framework.isPresent()) {
			return noFramework();
		}
		return new BaseServiceInvocationResult<>(framework.get().product());
	}

	private Optional<Framework> frameworkIfAny() {
		BundleContext context = FrameworkUtil.getBundle(getClass()).getBundleContext();
		try {
			return context.getServiceReferences(FrameworkSupplier.class, null).stream() //
					.map(context::getService) //
					// DI is used only to get rid of overwhelming dependencies here
					.filter(supplier -> supplier.getClass().getName().equals(friend)).findAny() //
					.flatMap(FrameworkSupplier::get);
		} catch (InvalidSyntaxException e) {
			return Optional.empty();
		}
	}

	private <T> ServiceInvocationResult<T> noFramework() {
		return new BaseServiceInvocationResult<T>(//
				new Trouble(//
						new NoFramework(), //
						String.format(AccessMessages.EquinoxPassage_no_framewrok, friend))//
		);
	}

}
