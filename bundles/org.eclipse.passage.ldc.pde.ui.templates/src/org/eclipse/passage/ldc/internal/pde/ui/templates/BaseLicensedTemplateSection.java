/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel;
import org.eclipse.passage.lic.internal.base.requirements.BaseFeature;
import org.eclipse.passage.lic.internal.base.requirements.BaseRequirement;
import org.eclipse.passage.lic.internal.equinox.requirements.RequirementsToBundle;
import org.eclipse.pde.core.plugin.IMatchRules;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.eclipse.pde.core.plugin.ISharedPluginModel;
import org.eclipse.pde.internal.core.ibundle.IBundlePluginBase;
import org.eclipse.pde.internal.core.ibundle.IBundlePluginModelBase;
import org.eclipse.pde.ui.templates.OptionTemplateSection;
import org.eclipse.pde.ui.templates.PluginReference;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;

@SuppressWarnings("restriction")
public abstract class BaseLicensedTemplateSection extends OptionTemplateSection {

	public static final String KEY_APPLICATION_CLASS = "applicationClass"; //$NON-NLS-1$
	public static final String KEY_WINDOW_TITLE = "windowTitle"; //$NON-NLS-1$
	public static final String KEY_PRODUCT_BRANDING = "productBranding"; //$NON-NLS-1$
	public static final String KEY_PRODUCT_NAME = "productName"; //$NON-NLS-1$

	public static final String VALUE_PRODUCT_ID = "product"; //$NON-NLS-1$
	public static final String VALUE_PRODUCT_NAME = "Licensed Product"; //$NON-NLS-1$
	public static final String VALUE_PERSPECTIVE_NAME = "Licensed Perspective"; //$NON-NLS-1$
	public static final String VALUE_APPLICATION_ID = "application"; //$NON-NLS-1$

	protected static final String VALUE_PROCESSOR_LICENSING_ID = "licensing"; //$NON-NLS-1$
	protected static final String VALUE_PROCESSOR_LICENSING_CLASS = "org.eclipse.passage.lic.e4.ui.addons.LicensingProcessor"; //$NON-NLS-1$

	private Bundle bundle = FrameworkUtil.getBundle(BaseLicensedTemplateSection.class);

	@Override
	public boolean isDependentOnParentWizard() {
		return true;
	}

	@Override
	public String getUsedExtensionPoint() {
		return null;
	}

	@Override
	public String[] getNewFiles() {
		return new String[0];
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
	protected String getTemplateDirectory() {
		return "templates"; //$NON-NLS-1$
	}

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

	protected IPluginReference[] getDependencies(List<String> symbolicNames) {
		List<IPluginReference> dependencies = new ArrayList<>();
		String version = "0.0.0"; //$NON-NLS-1$
		int match = IMatchRules.GREATER_OR_EQUAL;
		symbolicNames.forEach(n -> dependencies.add(new PluginReference(n, version, match)));
		return dependencies.toArray(new IPluginReference[dependencies.size()]);
	}

	protected void createLicensingCapability(String identifier) {
		IPluginBase plugin = model.getPluginBase();
		if (!(plugin instanceof IBundlePluginBase)) {
			return;
		}
		ISharedPluginModel shared = ((IBundlePluginBase) plugin).getModel();
		if (!(shared instanceof IBundlePluginModelBase)) {
			return;
		}
		((IBundlePluginModelBase) shared).getBundleModel().getBundle()//
				.setHeader(Constants.PROVIDE_CAPABILITY, //
						new RequirementsToBundle()//
								.printed(Arrays.asList(defaultRequirement(identifier))));
	}

	private Requirement defaultRequirement(String identifier) {
		return new BaseRequirement(//
				new BaseFeature(//
						identifier, //
						"1.0.0", //$NON-NLS-1$
						identifier, //
						"Eclipse Passage Template"), //$NON-NLS-1$
				new RestrictionLevel.Warning(), //
				this);
	}

	protected void createApplicationExtension(String identifier, String classValue) throws CoreException {
		IPluginBase plugin = model.getPluginBase();

		IPluginExtension extension = createExtension("org.eclipse.core.runtime.applications", true); //$NON-NLS-1$
		extension.setId(identifier);

		IPluginElement element = model.getPluginFactory().createElement(extension);
		element.setName("application"); //$NON-NLS-1$
		extension.add(element);

		IPluginElement run = model.getPluginFactory().createElement(element);
		run.setName("run"); //$NON-NLS-1$
		run.setAttribute("class", classValue); //$NON-NLS-1$
		element.add(run);

		if (!extension.isInTheModel()) {
			plugin.add(extension);
		}
	}

	protected void createProcessorExtension(String identifier, String classValue) throws CoreException {
		IPluginBase plugin = model.getPluginBase();

		IPluginExtension extension = createExtension("org.eclipse.e4.workbench.model", true); //$NON-NLS-1$
		extension.setId(identifier);

		IPluginElement processor = model.getPluginFactory().createElement(extension);
		processor.setName("processor"); //$NON-NLS-1$
		processor.setAttribute("apply", "always"); //$NON-NLS-1$ //$NON-NLS-2$
		processor.setAttribute("beforefragment", Boolean.FALSE.toString()); //$NON-NLS-1$
		processor.setAttribute("class", classValue); //$NON-NLS-1$
		extension.add(processor);

		if (!extension.isInTheModel()) {
			plugin.add(extension);
		}
	}

	protected List<String> getRCP3xDependencies() {
		return Arrays.asList(//
				"org.apache.felix.scr", //$NON-NLS-1$
				"org.eclipse.equinox.event", //$NON-NLS-1$
				"org.eclipse.core.runtime", //$NON-NLS-1$
				"org.eclipse.ui", //$NON-NLS-1$
				"org.eclipse.passage.lic.equinox", //$NON-NLS-1$
				"org.eclipse.passage.lic.e4.ui"); //$NON-NLS-1$
	}

	protected List<String> getRCP4Dependencies() {
		return Arrays.asList(//
				"javax.inject", //$NON-NLS-1$
				"org.eclipse.core.runtime", //$NON-NLS-1$
				"org.eclipse.swt", //$NON-NLS-1$
				"org.eclipse.jface", //$NON-NLS-1$
				"org.eclipse.e4.core.di", //$NON-NLS-1$
				"org.eclipse.e4.ui.di", //$NON-NLS-1$
				"org.eclipse.e4.ui.services", //$NON-NLS-1$
				"org.eclipse.e4.ui.model.workbench", //$NON-NLS-1$
				"org.eclipse.e4.ui.workbench", //$NON-NLS-1$
				"org.eclipse.e4.core.contexts", //$NON-NLS-1$
				"org.eclipse.passage.lic.equinox", //$NON-NLS-1$
				"org.eclipse.passage.lic.e4.ui", //$NON-NLS-1$
				"org.slf4j.api"); //$NON-NLS-1$
	}

}
