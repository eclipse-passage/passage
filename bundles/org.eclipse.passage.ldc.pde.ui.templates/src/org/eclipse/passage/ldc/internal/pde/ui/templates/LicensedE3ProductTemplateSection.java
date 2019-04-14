package org.eclipse.passage.ldc.internal.pde.ui.templates;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.passage.lic.base.LicensingNamespaces;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.eclipse.pde.core.plugin.ISharedPluginModel;
import org.eclipse.pde.internal.core.ibundle.IBundle;
import org.eclipse.pde.internal.core.ibundle.IBundleModel;
import org.eclipse.pde.internal.core.ibundle.IBundlePluginBase;
import org.eclipse.pde.internal.core.ibundle.IBundlePluginModelBase;
import org.eclipse.pde.internal.ui.templates.IHelpContextIds;
import org.eclipse.pde.internal.ui.templates.PDETemplateMessages;
import org.eclipse.pde.ui.IFieldData;
import org.osgi.framework.Constants;

@SuppressWarnings("restriction")
public class LicensedE3ProductTemplateSection extends BaseTemplateSection {

	private static final String LICENSED_E3_PRODUCT = "LicensedE3Product"; //$NON-NLS-1$

	public LicensedE3ProductTemplateSection() {
		setPageCount(1);
		createOptions();
	}

	@Override
	public void addPages(Wizard wizard) {
		WizardPage page = createPage(0, IHelpContextIds.TEMPLATE_RCP_MAIL);
		page.setTitle(PDETemplateMessages.HelloRCPTemplate_title);
		page.setDescription(PDETemplateMessages.HelloRCPTemplate_desc);
		wizard.addPage(page);
		markPagesAdded();
	}

	private void createOptions() {
		addOption(KEY_WINDOW_TITLE, PDETemplateMessages.HelloRCPTemplate_windowTitle, "Licensed RCP", 0); //$NON-NLS-1$
		addOption(KEY_PACKAGE_NAME, PDETemplateMessages.MailTemplate_packageName, (String) null, 0);
		addOption(KEY_APPLICATION_CLASS, PDETemplateMessages.HelloRCPTemplate_appClass, "LicensedApplication", 0); //$NON-NLS-1$
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
		return LICENSED_E3_PRODUCT;
	}

	@Override
	protected void updateModel(IProgressMonitor monitor) throws CoreException {
		createLicensingCapability();
		createApplicationExtension();
		createPerspectiveExtension();
		createProductExtension();
	}

	private void createLicensingCapability() {
		IPluginBase plugin = model.getPluginBase();
		if (plugin instanceof IBundlePluginBase) {
			IBundlePluginBase bpBase = (IBundlePluginBase) plugin;
			ISharedPluginModel spm = bpBase.getModel();
			if (spm instanceof IBundlePluginModelBase) {
				IBundlePluginModelBase bpmb = (IBundlePluginModelBase) spm;
				IBundleModel bundleModel = bpmb.getBundleModel();
				IBundle bundle = bundleModel.getBundle();
				String productFqn = getStringOption(KEY_PACKAGE_NAME) + '.' + VALUE_PRODUCT_ID;
				StringBuilder sb = new StringBuilder();
				sb.append(LicensingNamespaces.CAPABILITY_LICENSING_FEATURE).append(';');
				sb.append(LicensingNamespaces.CAPABILITY_LICENSING_FEATURE).append('=');
				sb.append('"').append(productFqn).append('"');
				bundle.setHeader(Constants.PROVIDE_CAPABILITY, sb.toString());
			}
		}

	}

	private void createApplicationExtension() throws CoreException {
		IPluginBase plugin = model.getPluginBase();

		IPluginExtension extension = createExtension("org.eclipse.core.runtime.applications", true); //$NON-NLS-1$
		extension.setId(VALUE_APPLICATION_ID);

		IPluginElement element = model.getPluginFactory().createElement(extension);
		element.setName("application"); //$NON-NLS-1$
		extension.add(element);

		IPluginElement run = model.getPluginFactory().createElement(element);
		run.setName("run"); //$NON-NLS-1$
		run.setAttribute("class", getStringOption(KEY_PACKAGE_NAME) + "." + getStringOption(KEY_APPLICATION_CLASS)); //$NON-NLS-1$ //$NON-NLS-2$
		element.add(run);

		if (!extension.isInTheModel()) {
			plugin.add(extension);
		}
	}

	private void createPerspectiveExtension() throws CoreException {
		IPluginBase plugin = model.getPluginBase();

		IPluginExtension extension = createExtension("org.eclipse.ui.perspectives", true); //$NON-NLS-1$
		IPluginElement element = model.getPluginFactory().createElement(extension);
		element.setName("perspective"); //$NON-NLS-1$
		element.setAttribute("class", getStringOption(KEY_PACKAGE_NAME) + ".Perspective"); //$NON-NLS-1$ //$NON-NLS-2$
		element.setAttribute("name", VALUE_PERSPECTIVE_NAME); //$NON-NLS-1$
		element.setAttribute("id", plugin.getId() + ".perspective"); //$NON-NLS-1$ //$NON-NLS-2$
		extension.add(element);

		if (!extension.isInTheModel()) {
			plugin.add(extension);
		}
	}

	private void createProductExtension() throws CoreException {
		IPluginBase plugin = model.getPluginBase();
		IPluginExtension extension = createExtension("org.eclipse.core.runtime.products", true); //$NON-NLS-1$
		extension.setId(VALUE_PRODUCT_ID);

		IPluginElement element = model.getFactory().createElement(extension);
		element.setName("product"); //$NON-NLS-1$
		element.setAttribute("name", getStringOption(KEY_WINDOW_TITLE)); //$NON-NLS-1$
		element.setAttribute("application", plugin.getId() + "." + VALUE_APPLICATION_ID); //$NON-NLS-1$ //$NON-NLS-2$

		extension.add(element);

		if (!extension.isInTheModel()) {
			plugin.add(extension);
		}
	}

	@Override
	public IPluginReference[] getDependencies(String schemaVersion) {
		return getRCP3xDependencies();
	}

}
