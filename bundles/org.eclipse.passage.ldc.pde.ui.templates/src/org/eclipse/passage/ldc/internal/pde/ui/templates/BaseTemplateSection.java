package org.eclipse.passage.ldc.internal.pde.ui.templates;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.Platform;
import org.eclipse.pde.core.plugin.IPluginReference;
import org.eclipse.pde.ui.templates.OptionTemplateSection;
import org.eclipse.pde.ui.templates.PluginReference;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public abstract class BaseTemplateSection extends OptionTemplateSection {

	public static final String KEY_APPLICATION_CLASS = "applicationClass"; //$NON-NLS-1$
	public static final String KEY_WINDOW_TITLE = "windowTitle"; //$NON-NLS-1$
	public static final String KEY_PRODUCT_BRANDING = "productBranding"; //$NON-NLS-1$
	public static final String KEY_PRODUCT_NAME = "productName"; //$NON-NLS-1$

	public static final String VALUE_PRODUCT_ID = "product"; //$NON-NLS-1$
	public static final String VALUE_PRODUCT_NAME = "Licensed Product"; //$NON-NLS-1$
	public static final String VALUE_PERSPECTIVE_NAME = "Licensed Perspective"; //$NON-NLS-1$
	public static final String VALUE_APPLICATION_ID = "application"; //$NON-NLS-1$

	private Bundle bundle = FrameworkUtil.getBundle(BaseTemplateSection.class);

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

	protected IPluginReference[] getRCP3xDependencies() {
		IPluginReference[] dep = new IPluginReference[6];
		dep[0] = new PluginReference("org.eclipse.equinox.ds"); //$NON-NLS-1$
		dep[1] = new PluginReference("org.eclipse.equinox.util"); //$NON-NLS-1$
		dep[2] = new PluginReference("org.eclipse.equinox.event"); //$NON-NLS-1$
		dep[3] = new PluginReference("org.eclipse.core.runtime"); //$NON-NLS-1$
		dep[4] = new PluginReference("org.eclipse.ui"); //$NON-NLS-1$
		dep[5] = new PluginReference("org.eclipse.passage.lic.equinox"); //$NON-NLS-1$
		return dep;
	}

}
