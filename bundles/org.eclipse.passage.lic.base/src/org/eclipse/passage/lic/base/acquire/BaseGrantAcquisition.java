/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support and improvements
 *******************************************************************************/
package org.eclipse.passage.lic.base.acquire;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.eclipse.passage.lic.api.FeatureIdentifier;
import org.eclipse.passage.lic.api.GrantIdentifier;
import org.eclipse.passage.lic.api.UserIdentifier;
import org.eclipse.passage.lic.api.acquire.GrantAcquisition;

/**
 *
 * @since 2.1
 */
public final class BaseGrantAcquisition implements GrantAcquisition, Serializable {

	private static final long serialVersionUID = 2755031536488685673L;
	private final String id;
	private final GrantIdentifier grant;
	private final FeatureIdentifier feature;
	private final UserIdentifier user;
	private final Date created;

	/**
	 * @since 4.0
	 */
	public BaseGrantAcquisition(String id, GrantIdentifier grant, FeatureIdentifier feature, UserIdentifier user,
			Date created) {
		this.id = Objects.requireNonNull(id);
		this.grant = Objects.requireNonNull(grant);
		this.feature = Objects.requireNonNull(feature);
		this.user = Objects.requireNonNull(user);
		this.created = Objects.requireNonNull(created);
	}

	@Override
	public String identifier() {
		return id;
	}

	@Override
	public GrantIdentifier grant() {
		return grant;
	}

	@Override
	public FeatureIdentifier feature() {
		return feature;
	}

	@Override
	public UserIdentifier user() {
		return user;
	}

	@Override
	public Date created() {
		return created;
	}

}
