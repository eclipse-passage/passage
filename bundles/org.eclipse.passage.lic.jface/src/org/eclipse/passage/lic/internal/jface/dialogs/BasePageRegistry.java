/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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
package org.eclipse.passage.lic.internal.jface.dialogs;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.passage.lic.jface.dialogs.LicensingPage;
import org.eclipse.passage.lic.jface.dialogs.LicensingPageContributor;
import org.eclipse.passage.lic.jface.dialogs.LicensingPageRegistry;

public class BasePageRegistry implements LicensingPageRegistry {

	private Map<String, LicensingPageContributor> pageContributors = new LinkedHashMap<>();

	@Override
	public Iterable<LicensingPageContributor> getPageContributors() {
		return pageContributors.values();
	}

	@Override
	public void registerPageContributor(LicensingPageContributor contributor) {
		pageContributors.put(contributor.getPageIdentifier(), contributor);
	}

	public void registerPageContributor(Class<? extends LicensingPage> pageClass, String pageName) {
		String pageIdentifier = pageClass.getName();
		BasePageContributor contributor = new BasePageContributor(pageIdentifier, pageName, pageClass);
		registerPageContributor(contributor);
	}

}
