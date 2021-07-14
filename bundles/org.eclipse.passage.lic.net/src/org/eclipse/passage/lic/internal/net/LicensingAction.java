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
package org.eclipse.passage.lic.internal.net;

import java.util.Map;
import java.util.function.Function;

import org.eclipse.passage.lic.api.PassageAction;
import org.eclipse.passage.lic.base.BaseNamedData;

public final class LicensingAction extends BaseNamedData<PassageAction> {

	public LicensingAction(Function<String, PassageAction> retrieve) {
		super(retrieve);
	}

	public LicensingAction(Map<String, Object> data) {
		super(key -> new PassageAction.Of(String.valueOf(data.get(key))));
	}

	public LicensingAction(PassageAction action) {
		super(key -> action);
	}

	@Override
	public String key() {
		return "action"; //$NON-NLS-1$
	}

}
