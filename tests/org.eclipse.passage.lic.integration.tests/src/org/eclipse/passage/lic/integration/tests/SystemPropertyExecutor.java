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
package org.eclipse.passage.lic.integration.tests;

import org.eclipse.passage.lic.runtime.RestrictionExecutor;
import org.eclipse.passage.lic.runtime.RestrictionVerdict;
import org.osgi.service.component.annotations.Component;

@Component
public class SystemPropertyExecutor implements RestrictionExecutor {

	@Override
	public void execute(Iterable<RestrictionVerdict> actions) {
		for (RestrictionVerdict verdict : actions) {
			String featureIdentifier = verdict.getLicensingRequirement().getFeatureIdentifier();
			String restrictionLevel = verdict.getRestrictionLevel();
			System.setProperty(featureIdentifier, restrictionLevel);
		}
	}

}
