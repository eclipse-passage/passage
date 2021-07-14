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

import java.util.Map;
import java.util.function.Function;

/**
 * Supplies an {@code identifier} of a feature under licensing, which is
 * expected to be stored in a variety of sources under a special key.
 * 
 * @see NamedData
 * 
 * @since 2.1
 */
public final class FeatureIdentifier extends StringNamedData {

	public FeatureIdentifier(String value) {
		super(value);
	}

	public FeatureIdentifier(Map<String, Object> values) {
		super(values);
	}

	public FeatureIdentifier(Function<String, String> retriever) {
		super(retriever);
	}

	@Override
	public String key() {
		return "licensing.feature.identifier"; //$NON-NLS-1$
	}

}
