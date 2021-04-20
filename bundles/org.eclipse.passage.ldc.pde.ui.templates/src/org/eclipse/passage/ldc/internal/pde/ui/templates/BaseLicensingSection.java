/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.ldc.internal.pde.ui.templates;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.passage.ldc.internal.pde.core.templates.OptionId;
import org.eclipse.passage.ldc.internal.pde.core.templates.TemplateId;
import org.eclipse.pde.ui.templates.OptionTemplateSection;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public abstract class BaseLicensingSection extends OptionTemplateSection {

	private final Bundle bundle;

	public BaseLicensingSection() {
		this(BaseLicensingSection.class);
	}

	public BaseLicensingSection(Class<?> clazz) {
		this(FrameworkUtil.getBundle(clazz));
	}

	public BaseLicensingSection(Bundle bundle) {
		this.bundle = bundle;
	}

	@Override
	public String getUsedExtensionPoint() {
		// none at the moment
		return null;
	}

	@Override
	protected URL getInstallURL() {
		return bundle.getEntry("/"); //$NON-NLS-1$
	}

	@Override
	protected ResourceBundle getPluginResourceBundle() {
		return Platform.getResourceBundle(bundle);
	}

	@Override
	protected void updateModel(IProgressMonitor monitor) throws CoreException {
		// nothing to do ATM, but perharps capability declaration should be moved here
	}

	// FIXME:AF: extract to function
	protected String getFormattedPackageName(String id) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < id.length(); i++) {
			char ch = id.charAt(i);
			if (buffer.length() == 0) {
				if (Character.isJavaIdentifierStart(ch))
					buffer.append(Character.toLowerCase(ch));
			} else {
				if (Character.isJavaIdentifierPart(ch) || ch == '.')
					buffer.append(ch);
			}
		}
		return buffer.toString().toLowerCase(Locale.ENGLISH);
	}

	protected URL bundleTemplate(TemplateId id) {
		return FileLocator.find(FrameworkUtil.getBundle(id.getClass()), //
				Path.ROOT.append(getTemplateDirectory()).append(id.id()));
	}

	protected void systemOption(OptionId id, String value) {
		addOption(id.id(), id.id(), value, 0);
	}

}
