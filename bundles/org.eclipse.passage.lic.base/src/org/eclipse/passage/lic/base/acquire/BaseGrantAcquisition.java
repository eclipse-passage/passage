/*******************************************************************************
 * Copyright (c) 2020, 2022 ArSysOp
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
package org.eclipse.passage.lic.base.acquire;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.eclipse.passage.lic.api.acquire.GrantAcquisition;

/**
 * 
 * @since 2.1
 */
public final class BaseGrantAcquisition implements GrantAcquisition, Serializable {

	private static final long serialVersionUID = 2755031536488685673L;
	private final String id;
	private final String grant;
	private final String feature;
	private final String user;
	private final Date created;

	public BaseGrantAcquisition(String id, String grant, String feature, String user, Date created) {
		Objects.requireNonNull(id, "BaseGrantAcquisition::id"); //$NON-NLS-1$
		Objects.requireNonNull(grant, "BaseGrantAcquisition::grant"); //$NON-NLS-1$
		Objects.requireNonNull(feature, "BaseGrantAcquisition::feature"); //$NON-NLS-1$
		Objects.requireNonNull(user, "BaseGrantAcquisition::user"); //$NON-NLS-1$
		Objects.requireNonNull(created, "BaseGrantAcquisition::created"); //$NON-NLS-1$
		this.id = id;
		this.grant = grant;
		this.feature = feature;
		this.user = user;
		this.created = created;
	}

	@Override
	public String identifier() {
		return id;
	}

	@Override
	public String grant() {
		return grant;
	}

	@Override
	public String feature() {
		return feature;
	}

	@Override
	public String user() {
		return user;
	}

	@Override
	public Date created() {
		return created;
	}

}
