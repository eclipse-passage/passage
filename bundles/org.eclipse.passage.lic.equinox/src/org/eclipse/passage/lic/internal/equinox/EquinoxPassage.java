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
import org.eclipse.passage.lic.internal.api.Passage;
import org.eclipse.passage.lic.internal.base.Access;
import org.eclipse.passage.lic.internal.equinox.i18n.EquinoxMessages;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("restriction")
public final class EquinoxPassage implements Passage {

	private final Logger log = LoggerFactory.getLogger(EquinoxPassage.class);

	@Override
	public boolean canUse(String feature) {
		Optional<FrameworkSupplier> supplier = frameworkSupplier();
		if (!supplier.isPresent()) {
			return false;
		}
		Optional<Framework> framework = supplier.get().get();
		if (!framework.isPresent()) {
			return false;
		}
		return new Access(framework.get()).canUse(feature);
	}

	@Override
	public void checkLicense(String feature) {
		// accessManager.executeAccessRestrictions(configuration);
		throw new UnsupportedOperationException();
	}

	Optional<FrameworkSupplier> frameworkSupplier() {
		BundleContext context = FrameworkUtil.getBundle(getClass()).getBundleContext();
		try {
			return context.getServiceReferences(FrameworkSupplier.class, null).stream() //
					.map(context::getService) //
					// DI is used only to get rid of overwhelming dependencies here
					.filter(supplier -> supplier.getClass().getName()
							.equals("org.eclipse.passage.seal.internal.demo.DemoFrameworkSupplier")) //$NON-NLS-1$ FIXME
					.findAny();
		} catch (InvalidSyntaxException e) {
			log.error(EquinoxMessages.EquinoxPassage_no_framework, e);
			return Optional.empty();
		}
	}

}
