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
package org.eclipse.passage.lic.internal.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeaturePermissionMixln {

	public FeaturePermissionMixln(@JsonProperty("featureIdentifier") String featureIdentifier,
			@JsonProperty("matchVersion") String matchVersion, @JsonProperty("matchRule") String matchRule,
			@JsonProperty("leaseTime") String leaseTime, @JsonProperty("expireTime") String expireTime) {
	}
}
