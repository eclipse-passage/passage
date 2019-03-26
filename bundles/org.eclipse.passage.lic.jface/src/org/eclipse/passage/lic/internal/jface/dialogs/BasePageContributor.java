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

import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.jface.dialogs.LicensingPageContributor;
import org.eclipse.passage.lic.jface.dialogs.LicensingRegistryPage;
import org.eclipse.passage.lic.runtime.LicensingException;
import org.eclipse.passage.lic.runtime.LicensingResult;

public class BasePageContributor<R> implements LicensingPageContributor<R> {

	private final String pageIdentifier;
	private final String pageName;
	private final Class<? extends LicensingRegistryPage<R>> pageClass;

	public BasePageContributor(String pageIdentifier, String pageName,
			Class<? extends LicensingRegistryPage<R>> pageClass) {
		this.pageIdentifier = pageIdentifier;
		this.pageName = pageName;
		this.pageClass = pageClass;
	}

	@Override
	public String getPageIdentifier() {
		return pageIdentifier;
	}

	@Override
	public String getPageName() {
		return pageName;
	}

	@Override
	public LicensingRegistryPage<R> createPage() throws LicensingException {
		try {
			return pageClass.newInstance();
		} catch (Exception e) {
			String message = NLS.bind("Unable to create new instance of {0}", pageClass);
			LicensingResult error = LicensingResults.createError(message, pageIdentifier, e);
			throw new LicensingException(error);
		}
	}

}
