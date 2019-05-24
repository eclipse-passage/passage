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
package org.eclipse.passage.lic.jface.actions;

import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.passage.lic.api.requirements.LicensingRequirement;
import org.eclipse.passage.lic.api.restrictions.RestrictionVerdict;
import org.eclipse.passage.lic.base.restrictions.RestrictionVerdicts;
import org.eclipse.passage.lic.equinox.requirements.EquinoxRequirements;
import org.eclipse.passage.lic.equinox.restrictions.EquinoxRestrictions;
import org.eclipse.passage.lic.jface.dialogs.LicensingStatusDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

public class LicensedAction extends Action {

	@Override
	public void runWithEvent(Event event) {
		Shell shell = event.display.getActiveShell();
		String featureId = getId();
		Iterable<LicensingRequirement> required = EquinoxRequirements.getFeatureRequirements(featureId);
		Iterator<LicensingRequirement> iterator = required.iterator();
		if (iterator.hasNext()) {
			Iterable<RestrictionVerdict> verdicts = EquinoxRestrictions.getFeatureVerdicts(featureId);
			if (RestrictionVerdicts.shouldPauseExecution(RestrictionVerdicts.resolveLastVerdict(verdicts))) {
				LicensingStatusDialog dialog = new LicensingStatusDialog(shell, featureId);
				dialog.open();
				verdicts = EquinoxRestrictions.getFeatureVerdicts(featureId);
			}
			if (RestrictionVerdicts.shouldInterruptExecution(RestrictionVerdicts.resolveLastVerdict(verdicts))) {
				return;
			}
		}
		super.runWithEvent(event);
	}

}
