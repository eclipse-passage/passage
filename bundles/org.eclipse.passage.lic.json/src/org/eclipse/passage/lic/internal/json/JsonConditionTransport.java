/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.json;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.passage.lic.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.api.conditions.mining.ContentType;

public final class JsonConditionTransport implements ConditionTransport {

	private final ContentType type = new ContentType.Json();

	@Override
	public ContentType id() {
		return type;
	}

	@Override
	public Data read(InputStream input) throws IOException {
		// TODO: signature
		return new Data(new JsonObjectMapper().get().readValue(input, ConditionPack.class).conditions);
	}

}
