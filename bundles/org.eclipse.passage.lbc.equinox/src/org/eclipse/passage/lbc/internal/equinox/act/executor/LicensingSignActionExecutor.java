package org.eclipse.passage.lbc.internal.equinox.act.executor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.passage.lbc.api.BackendActionExecutor;
import org.eclipse.passage.lic.api.LicensingResult;
import org.osgi.service.component.annotations.Component;

/**
 * Service dedicated to support licensing signature
 */

@Component
public class LicensingSignActionExecutor implements BackendActionExecutor {

	@Override
	public LicensingResult executeAction(HttpServletRequest request, HttpServletResponse response) {

		return null;
	}

}
