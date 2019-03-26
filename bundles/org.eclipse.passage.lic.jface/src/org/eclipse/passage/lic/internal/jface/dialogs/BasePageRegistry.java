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
package org.eclipse.passage.lic.internal.jface.dialogs;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.passage.lic.jface.dialogs.LicensingPageContributor;
import org.eclipse.passage.lic.jface.dialogs.LicensingPageRegistry;
import org.eclipse.passage.lic.jface.dialogs.LicensingRegistryPage;

public class BasePageRegistry implements LicensingPageRegistry {

	private Map<String, LicensingPageContributor<?>> pageContributors = new LinkedHashMap<>();

	@Override
	public Iterable<LicensingPageContributor<?>> getPageContributors() {
		return pageContributors.values();
	}

	@Override
	public <R> void registerPageContributor(LicensingPageContributor<R> contributor) {
		pageContributors.put(contributor.getPageIdentifier(), contributor);
	}

	public <R> void registerPageContributor(Class<? extends LicensingRegistryPage<R>> pageClass, String pageName) {
		String pageIdentifier = pageClass.getName();
		BasePageContributor<R> contributor = new BasePageContributor<R>(pageIdentifier, pageName, pageClass);
		registerPageContributor(contributor);
	}

}
