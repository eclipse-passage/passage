/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.jface.dialogs;

import org.eclipse.passage.lic.equinox.LicensingEquinox;
import org.eclipse.passage.lic.internal.jface.JFaceMessages;
import org.eclipse.passage.lic.internal.jface.dialogs.BasePageRegistry;
import org.eclipse.passage.lic.internal.jface.dialogs.ConditionLocationPage;
import org.eclipse.passage.lic.internal.jface.dialogs.ConditionTypePage;
import org.eclipse.passage.lic.internal.jface.dialogs.RestrictionLevelPage;

public class LicensingPages {

	private static final LicensingPages INSTANCE = new LicensingPages();

	private final LicensingPageRegistry pageRegistry;

	private LicensingPages() {
		LicensingPageRegistry registry = LicensingEquinox.getLicensingService(LicensingPageRegistry.class);
		if (registry == null) {
			registry = createDefaultContributor();
		}
		pageRegistry = registry;
	}

	private BasePageRegistry createDefaultContributor() {
		BasePageRegistry basePageRegistry = new BasePageRegistry();
		basePageRegistry.registerPageContributor(ConditionLocationPage.class, JFaceMessages.LicensingPages_page_locations);
		basePageRegistry.registerPageContributor(ConditionTypePage.class, JFaceMessages.LicensingPages_page_conditions);
		basePageRegistry.registerPageContributor(RestrictionLevelPage.class, JFaceMessages.LicensingPages_page_restrictions);
		return basePageRegistry;
	}

	public static Iterable<LicensingPageContributor> getPageContributors() {
		return INSTANCE.pageRegistry.getPageContributors();
	}

}
