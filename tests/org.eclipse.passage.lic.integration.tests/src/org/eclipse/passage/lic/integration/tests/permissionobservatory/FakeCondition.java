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
package org.eclipse.passage.lic.integration.tests.permissionobservatory;

import java.util.Date;

import org.eclipse.passage.lic.api.access.AccessManager;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;

/**
 * <p>
 * As we test only {@linkplain AccessManager#evaluateConditions()} method, we
 * need to feed it.
 * </p>
 * 
 * <p>
 * These are dummy conditions, which are valid for 10 minutes since creation
 * time.
 * </p>
 * 
 * @since 0.6
 */
class FakeCondition implements LicensingCondition {
	private final Date now = new Date();

	@Override
	public String getFeatureIdentifier() {
		return "feature"; //$NON-NLS-1$
	}

	@Override
	public String getMatchVersion() {
		return "1.0.0"; //$NON-NLS-1$
	}

	@Override
	public String getMatchRule() {
		return "equal"; //$NON-NLS-1$
	}

	/**
	 * Fake condition is valid from creation moment
	 */
	@Override
	public Date getValidFrom() {
		return now;
	}

	/**
	 * Fake condition is valid for 10 minutes from creation
	 */
	@Override
	public Date getValidUntil() {
		return new Date(now.getTime() + 10 * 60 * 1000);
	}

	@Override
	public String getConditionType() {
		return "time";//$NON-NLS-1$
	}

	@Override
	public String getConditionExpression() {
		return ""; //$NON-NLS-1$
	}

}
