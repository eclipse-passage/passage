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
package org.eclipse.passage.lic.base;

import java.io.IOException;
import java.io.StringReader;
import java.util.Objects;
import java.util.Properties;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;

/**
 * 
 * @since 2.1
 */
public final class KeyValuePairs {

	private final String source;
	private final String error;

	public KeyValuePairs(String source, String error) {
		Objects.requireNonNull(source, "KeyValuePairs::source"); //$NON-NLS-1$
		Objects.requireNonNull(error, "KeyValuePairs::error"); //$NON-NLS-1$
		this.source = source;
		this.error = error;
	}

	public KeyValuePairs(String source) {
		this(source, BaseMessages.getString("KeyValuePairs.default_error")); //$NON-NLS-1$
	}

	public Properties get() throws LicensingException {
		Properties properties = new Properties();
		try (StringReader reader = new StringReader(source)) {
			properties.load(reader);
		} catch (IOException e) {
			throw fail(e);
		}
		return properties;
	}

	private LicensingException fail(IOException e) {
		return new LicensingException(error, e);
	}

}
