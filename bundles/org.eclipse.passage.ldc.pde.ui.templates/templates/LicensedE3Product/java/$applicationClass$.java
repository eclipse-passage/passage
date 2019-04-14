package $packageName$;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.passage.lic.equinox.ApplicationConfigurations;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.access.AccessManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * This class controls all aspects of the application's execution
 */
public class $applicationClass$ implements IApplication {

	@Override
	public Object start(IApplicationContext context) throws Exception {
		Display display = PlatformUI.createDisplay();
		try {
			Bundle bundle = FrameworkUtil.getBundle(LicensedApplication.class);
			if (bundle == null) {
				return null;
			}
			BundleContext bundleContext = bundle.getBundleContext();
			ServiceReference<AccessManager> reference = bundleContext.getServiceReference(AccessManager.class);
			if (reference == null) {
				return null;
			}
			AccessManager accessManager = bundleContext.getService(reference);
			LicensingConfiguration licensingConfiguration = ApplicationConfigurations.getLicensingConfiguration(context);
			accessManager.executeAccessRestrictions(licensingConfiguration);
			int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART) {
				return IApplication.EXIT_RESTART;
			} else {
				return IApplication.EXIT_OK;
			}
		} finally {
			display.dispose();
		}
		
	}

	@Override
	public void stop() {
		if (!PlatformUI.isWorkbenchRunning()) {
			return;
		}
		final IWorkbench workbench = PlatformUI.getWorkbench();
		final Display display = workbench.getDisplay();
		display.syncExec(() -> {
			if (!display.isDisposed()) {
				workbench.close();
			}
		});
	}
}
