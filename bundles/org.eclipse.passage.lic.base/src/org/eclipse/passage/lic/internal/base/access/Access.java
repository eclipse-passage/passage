/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.base.access;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;

/**
 * Top-level access cycle
 */
public final class Access {

	private final Framework framework;

	public Access(Framework framework) {
		this.framework = framework;
	}

	public boolean canUse(String feature) {
		return new Allow(framework, feature).apply();
	}

	public ServiceInvocationResult<ExaminationCertificate> acquire(String feature) {
		return new Expose(framework, feature).apply();
	}

	public ServiceInvocationResult<Boolean> release(ExaminationCertificate certificate) {
		// FIXME: implement with either MinedConditions service evolution or new service
		// invention
		return null;
	}
}
