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

import java.util.Date;

import org.eclipse.passage.lic.runtime.access.FeaturePermission;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

final class FeaturePermissionMixIn implements FeaturePermission {

	static FeaturePermissionMixIn create(FeaturePermission d) {
		LicensingConditionMixIn licensingCondition = LicensingConditionMixIn.create(d.getLicensingCondition());
		LicensingConfigurationMixIn licensingConfiguration = LicensingConfigurationMixIn
				.create(d.getLicensingConfiguration());
		Date leaseDate = d.getLeaseDate();
		Date expireDate = d.getExpireDate();
		FeaturePermissionMixIn mixIn = new FeaturePermissionMixIn(licensingCondition, licensingConfiguration, leaseDate,
				expireDate);
		return mixIn;
	}

	private final LicensingConditionMixIn licensingCondition;
	private final LicensingConfigurationMixIn licensingConfiguration;
	private final Date leaseDate;
	private final Date expireDate;

	@JsonCreator
	FeaturePermissionMixIn(@JsonProperty("licensingCondition") LicensingConditionMixIn licensingCondition,
			@JsonProperty("licensingConfiguration") LicensingConfigurationMixIn licensingConfiguration,
			@JsonProperty("leaseDate") Date leaseDate, @JsonProperty("expireDate") Date expireDate) {
		this.licensingCondition = licensingCondition;
		this.licensingConfiguration = licensingConfiguration;
		this.leaseDate = leaseDate;
		this.expireDate = expireDate;
	}

	@Override
	public LicensingConditionMixIn getLicensingCondition() {
		return licensingCondition;
	}

	@Override
	public LicensingConfigurationMixIn getLicensingConfiguration() {
		return licensingConfiguration;
	}

	@Override
	public Date getLeaseDate() {
		return leaseDate;
	}

	@Override
	public Date getExpireDate() {
		return expireDate;
	}

}