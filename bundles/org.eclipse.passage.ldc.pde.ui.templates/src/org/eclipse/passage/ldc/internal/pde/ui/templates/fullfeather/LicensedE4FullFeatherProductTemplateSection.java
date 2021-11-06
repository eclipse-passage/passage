/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.ldc.internal.pde.ui.templates.fullfeather;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.passage.ldc.internal.pde.core.templates.TemplateId;
import org.eclipse.passage.ldc.internal.pde.core.templates.dev.DevMinimalTemplateId;
import org.eclipse.passage.ldc.internal.pde.core.templates.products.ProductIdentifierOptionId;
import org.eclipse.passage.ldc.internal.pde.ui.templates.BaseLicensedProductSection;
import org.eclipse.passage.ldc.internal.pde.ui.templates.HelpContexts;
import org.eclipse.passage.ldc.internal.pde.ui.templates.i18n.PdeUiTemplatesMessages;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginReference;

public final class LicensedE4FullFeatherProductTemplateSection extends BaseLicensedProductSection {

	public LicensedE4FullFeatherProductTemplateSection() {
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

	@Override
	public String getSectionId() {
		return "LicensedE4FullFeatherProduct"; //$NON-NLS-1$
	}

	@Override
	protected TemplateId getDevTemplate() {
		return new DevMinimalTemplateId();
	}

	@Override
	protected void updateModel(IProgressMonitor monitor) throws CoreException {
		setManifestHeader("Bundle-ActivationPolicy", "lazy"); //$NON-NLS-1$ //$NON-NLS-2$
		setManifestHeader("Service-Component", "OSGI-INF/*"); //$NON-NLS-1$ //$NON-NLS-2$
		createLicensingCapability(getStringOption(new ProductIdentifierOptionId().id()));
		createProductExtension();
	}

	@Override
	protected List<Requirement> requirements(String product) {
		return Arrays.asList(//
				createProductRequirement(product), //
				new AntimagicShieldFeatureLicRequirement().get());
	}

	@Override
	public IPluginReference[] getDependencies(String schemaVersion) {
		return getDependencies(getRCP4xDependencies());
	}

	@Override
	public String[] getNewFiles() {
		return new String[] { //
				"css/default.css", //$NON-NLS-1$
				"e4xmi/Application.e4xmi", //$NON-NLS-1$
				"OSGI-INF/" }; //$NON-NLS-1$
	}

	private void createOptions() {
		addOption(KEY_WINDOW_TITLE, PdeUiTemplatesMessages.LicensedE4ProductTemplateSection_key_window_title_label,
				"Full Feather Passage Licensed Product", 0); //$NON-NLS-1$
		addOption(KEY_PACKAGE_NAME, PdeUiTemplatesMessages.LicensedE4ProductTemplateSection_key_package_name_label,
				(String) null, 0);
	}

	private void createProductExtension() throws CoreException {
		IPluginBase plugin = model.getPluginBase();
		IPluginExtension extension = productRuntime();
		IPluginElement product = product(extension);
		addCssProperty(product);
		addApplicationProperty(product);
		extension.add(product);
		plugin.add(extension);
	}

	private IPluginExtension productRuntime() throws CoreException {
		IPluginExtension extension = createExtension("org.eclipse.core.runtime.products", true); //$NON-NLS-1$
		extension.setId(VALUE_PRODUCT_ID);
		return extension;
	}

	private void addApplicationProperty(IPluginElement element) throws CoreException {
		IPluginElement property;
		property = model.getFactory().createElement(element);
		property.setName("property"); //$NON-NLS-1$
		property.setAttribute("name", "applicationXMI");//$NON-NLS-1$ //$NON-NLS-2$
		property.setAttribute("value", "platform:/plugin/" + getValue(KEY_PLUGIN_ID) + "/e4xmi/Application.e4xmi"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		element.add(property);
	}

	private void addCssProperty(IPluginElement element) throws CoreException {
		IPluginElement property = model.getFactory().createElement(element);
		property.setName("property"); //$NON-NLS-1$
		property.setAttribute("name", "applicationCSS");//$NON-NLS-1$ //$NON-NLS-2$
		property.setAttribute("value", "platform:/plugin/" + getValue(KEY_PLUGIN_ID) + "/css/default.css"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		element.add(property);
	}

	private IPluginElement product(IPluginExtension extension) throws CoreException {
		IPluginElement element = model.getFactory().createElement(extension);
		element.setName("product"); //$NON-NLS-1$
		element.setAttribute("application", "org.eclipse.e4.ui.workbench.swt.E4Application"); //$NON-NLS-1$ //$NON-NLS-2$
		element.setAttribute("name", getStringOption(KEY_PACKAGE_NAME)); //$NON-NLS-1$
		return element;
	}

	@Override
	protected List<String> getRCP4xDependencies() {
		List<String> result = new ArrayList<>();
		result.add("javax.annotation"); //$NON-NLS-1$
		result.addAll(super.getRCP4xDependencies());
		result.add("org.eclipse.passage.lic.execute"); //$NON-NLS-1$
		result.remove("org.eclipse.passage.seal.demo"); //$NON-NLS-1$
		return result;
	}

}
