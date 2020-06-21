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
package org.eclipse.passage.lic.internal.json.tobemoved;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Objects;

import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;

/**
 * @since 0.6
 */
@SuppressWarnings("restriction")
public final class JsonConditionTransport implements ConditionTransport {

	private final ContentType type = new ContentType("application/json"); //$NON-NLS-1$

	@Override
	public ContentType id() {
		return type;
	}

	@Override
	public Collection<Condition> read(InputStream input) throws IOException {
		return new JsonObjectMapper().get().readValue(input, ConditionPack.class).conditions;
	}

	@Override
	public void write(Collection<Condition> conditions, OutputStream output) throws IOException {
		Objects.requireNonNull(conditions, "JsonConditionTransport::write::conditions"); //$NON-NLS-1$
		output.write(new JsonObjectMapper().get().writeValueAsBytes(new ConditionPack(conditions)));
	}

}
