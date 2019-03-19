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
package org.eclipse.passage.lic.jface.viewers;

import org.eclipse.passage.lic.base.restrictions.RestrictionVerdicts;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionVerdict;

public class BaseRestrictionRepresenter implements RestrictionRepresenter {

	@Override
	public String getSummary(Iterable<RestrictionVerdict> verdicts) {
		RestrictionVerdict last = RestrictionVerdicts.resolveLastVerdict(verdicts);
		return getSummary(last);
	}

	@Override
	public String getSummary(RestrictionVerdict verdict) {
		if (verdict == null) {
			return "The product in licensed properly";
		}
		return "There are issues with licensing";
	}

}
