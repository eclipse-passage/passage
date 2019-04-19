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
package org.eclipse.passage.ldc.internal.pde.ui.templates;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.eclipse.pde.ui.IFieldData;

public class LicensedE4ProductTemplateSection extends BaseLicensedTemplateSection {

	private static final String LICENSED_E4_PRODUCT = "LicensedE4Product"; //$NON-NLS-1$
	private static final String E4_SWT_APPLICATION_ID = "org.eclipse.e4.ui.workbench.swt.E4Application"; //$NON-NLS-1$

	public LicensedE4ProductTemplateSection() {
		setPageCount(1);
		createOptions();
	}

	@Override
	public void addPages(Wizard wizard) {
		WizardPage page = createPage(0, HelpContexts.TEMPLATE_E4_PRODUCT);
		page.setTitle(PdeUiTemplatesMessages.LicensedE4ProductTemplateSection_page_title);
		page.setDescription(PdeUiTemplatesMessages.LicensedE4ProductTemplateSection_page_description);
		wizard.addPage(page);
		markPagesAdded();
	}

	private void createOptions() {
		addOption(KEY_WINDOW_TITLE, PdeUiTemplatesMessages.LicensedE4ProductTemplateSection_key_window_title_label,
				"Licensed E4 RCP", 0); //$NON-NLS-1$
		addOption(KEY_PACKAGE_NAME, PdeUiTemplatesMessages.LicensedE4ProductTemplateSection_key_package_name_label,
				(String) null, 0);
	}

	@Override
	protected void initializeFields(IFieldData data) {
		// In a new project wizard, we don't know this yet - the
		// model has not been created
		String packageName = getFormattedPackageName(data.getId());
		initializeOption(KEY_PACKAGE_NAME, packageName);
	}

	@Override
	public void initializeFields(IPluginModelBase modelBase) {
		String packageName = getFormattedPackageName(modelBase.getPluginBase().getId());
		initializeOption(KEY_PACKAGE_NAME, packageName);
	}

	@Override
	public String getSectionId() {
		return LICENSED_E4_PRODUCT;
	}

	@Override
	protected void updateModel(IProgressMonitor monitor) throws CoreException {
		setManifestHeader("Bundle-ActivationPolicy", "lazy"); //$NON-NLS-1$ //$NON-NLS-2$
		String productFqn = getStringOption(KEY_PACKAGE_NAME) + '.' + VALUE_PRODUCT_ID;
		createLicensingCapability(productFqn);
		createProductExtension();
	}

	private void createProductExtension() throws CoreException {
		IPluginBase plugin = model.getPluginBase();
		IPluginExtension extension = createExtension("org.eclipse.core.runtime.products", true); //$NON-NLS-1$
		extension.setId(VALUE_PRODUCT_ID);

		IPluginElement element = model.getFactory().createElement(extension);
		element.setName("product"); //$NON-NLS-1$
		element.setAttribute("application", E4_SWT_APPLICATION_ID); //$NON-NLS-1$
		element.setAttribute("name", getStringOption(KEY_PACKAGE_NAME)); //$NON-NLS-1$

		IPluginElement property;

		property = model.getFactory().createElement(element);
		property.setName("property"); //$NON-NLS-1$
		property.setAttribute("name", "applicationCSS");//$NON-NLS-1$ //$NON-NLS-2$
		property.setAttribute("value", "platform:/plugin/" + getValue(KEY_PLUGIN_ID) + "/css/default.css"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		element.add(property);

		extension.add(element);

		if (!extension.isInTheModel())
			plugin.add(extension);
	}

	@Override
	public IPluginReference[] getDependencies(String schemaVersion) {
		return getDependencies(getRCP4Dependencies());
	}

	@Override
	public String[] getNewFiles() {
		return new String[] { "css/default.css", "Application.e4xmi" }; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
