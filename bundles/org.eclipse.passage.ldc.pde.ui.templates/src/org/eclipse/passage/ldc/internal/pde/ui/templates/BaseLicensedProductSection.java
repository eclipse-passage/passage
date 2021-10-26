/*******************************************************************************
 * Copyright (c) 2019, 2021 ArSysOp
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.passage.ldc.internal.pde.core.templates.DefaultProductRequirement;
import org.eclipse.passage.ldc.internal.pde.core.templates.TemplateId;
import org.eclipse.passage.ldc.internal.pde.core.templates.features.FeatureIdentifierOptionId;
import org.eclipse.passage.ldc.internal.pde.core.templates.features.FeatureNameOptionId;
import org.eclipse.passage.ldc.internal.pde.core.templates.features.FeatureProviderOptionId;
import org.eclipse.passage.ldc.internal.pde.core.templates.features.FeatureVersionVersionOptionId;
import org.eclipse.passage.ldc.internal.pde.core.templates.products.ProductIdentifierOptionId;
import org.eclipse.passage.ldc.internal.pde.core.templates.products.ProductNameOptionId;
import org.eclipse.passage.ldc.internal.pde.core.templates.products.ProductVersionVersionOptionId;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.equinox.requirements.RequirementsToBundle;
import org.eclipse.pde.core.plugin.IMatchRules;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.eclipse.pde.core.plugin.ISharedPluginModel;
import org.eclipse.pde.internal.core.ibundle.IBundlePluginBase;
import org.eclipse.pde.internal.core.ibundle.IBundlePluginModelBase;
import org.eclipse.pde.ui.IFieldData;
import org.eclipse.pde.ui.templates.PluginReference;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;

@SuppressWarnings("restriction")
public abstract class BaseLicensedProductSection extends BaseLicensingSection {

	public static final String KEY_APPLICATION_CLASS = "applicationClass"; //$NON-NLS-1$
	public static final String KEY_WINDOW_TITLE = "windowTitle"; //$NON-NLS-1$

	public static final String VALUE_PRODUCT_ID = "product"; //$NON-NLS-1$
	public static final String VALUE_PERSPECTIVE_NAME = "Licensed Perspective"; //$NON-NLS-1$
	public static final String VALUE_APPLICATION_ID = "application"; //$NON-NLS-1$

	protected static final String VALUE_PROCESSOR_LICENSING_ID = "licensing"; //$NON-NLS-1$
	protected static final String VALUE_PROCESSOR_LICENSING_CLASS = "org.eclipse.passage.lic.e4.ui.addons.E4LicensingProcessor"; //$NON-NLS-1$

	public BaseLicensedProductSection() {
		super(BaseLicensedProductSection.class);
	}

	public BaseLicensedProductSection(Class<?> clazz) {
		super(FrameworkUtil.getBundle(clazz));
	}

	public BaseLicensedProductSection(Bundle bundle) {
		super(bundle);
	}

	@Override
	public boolean isDependentOnParentWizard() {
		return true;
	}

	@Override
	protected String getTemplateDirectory() {
		return "templates"; //$NON-NLS-1$
	}

	protected abstract TemplateId getDevTemplate();

	@Override
	protected void initializeFields(IFieldData data) {
		// In a new project wizard, we don't know this yet - the
		// model has not been created
		String packageName = getFormattedPackageName(data.getId());
		initializeOption(KEY_PACKAGE_NAME, packageName);
	}

	@Override
	public void initializeFields(IPluginModelBase modelBase) {
		initializeProductDev(modelBase);
		String packageName = getFormattedPackageName(modelBase.getPluginBase().getId());
		initializeOption(KEY_PACKAGE_NAME, packageName);
	}

	@Override
	protected void generateFiles(IProgressMonitor monitor) throws CoreException {
		super.generateFiles(monitor);
		generateFiles(monitor, bundleTemplate(getDevTemplate()));
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
						new RequirementsToBundle().printed(requirements(identifier)));
		// FIXME: version also must be set - from Bundle-Version
	}

	protected abstract List<Requirement> requirements(String product);

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
				"org.eclipse.passage.lic.e4.ui", //$NON-NLS-1$
				"org.eclipse.passage.seal.demo"); //$NON-NLS-1$
	}

	protected List<String> getRCP4xDependencies() {
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
				"org.eclipse.passage.seal.demo", //$NON-NLS-1$
				"org.apache.logging.log4j"); //$NON-NLS-1$
	}

	protected Requirement createProductRequirement(String product) {
		return new DefaultProductRequirement(() -> product, //
				() -> getManifestHeader("Bundle-Name"), // //$NON-NLS-1$
				() -> getManifestHeader("Bundle-Version"), // //$NON-NLS-1$
				() -> getManifestHeader("Bundle-Vendor")// //$NON-NLS-1$
		).get();
	}

	protected void initializeProductDev(IPluginModelBase base) {
		systemOption(new FeatureIdentifierOptionId(), base.getPluginBase().getId() + '.' + VALUE_PRODUCT_ID);
		systemOption(new FeatureNameOptionId(), base.getPluginBase().getName());
		systemOption(new FeatureProviderOptionId(), base.getPluginBase().getProviderName());
		systemOption(new FeatureVersionVersionOptionId(), base.getPluginBase().getVersion());
		systemOption(new ProductIdentifierOptionId(), base.getPluginBase().getId() + '.' + VALUE_PRODUCT_ID);
		systemOption(new ProductNameOptionId(), base.getPluginBase().getName());
		systemOption(new ProductVersionVersionOptionId(), base.getPluginBase().getVersion());
	}

}
