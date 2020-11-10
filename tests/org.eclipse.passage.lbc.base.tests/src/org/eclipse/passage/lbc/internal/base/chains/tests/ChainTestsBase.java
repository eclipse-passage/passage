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
package org.eclipse.passage.lbc.internal.base.chains.tests;

import java.util.HashMap;

import org.eclipse.passage.lbc.base.tests.LbcTestsBase;
import org.eclipse.passage.lbc.internal.api.BackendLicensingRequest;
import org.eclipse.passage.lbc.internal.base.BaseLicensingRequest;
import org.eclipse.passage.lbc.json.JsonSerialization;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;

@SuppressWarnings("restriction")
public abstract class ChainTestsBase extends LbcTestsBase {

	protected BackendLicensingRequest certificateRequest() {
		return requestForBody(new JsonSerialization<ExaminationCertificate>()//
				.apply(new BaseServiceInvocationResult<>(certificate())).data().get());
	}

	protected BackendLicensingRequest conditionRequest() {
		return requestForBody(new JsonSerialization<Condition>()//
				.apply(new BaseServiceInvocationResult<>(condition())).data().get());
	}

	protected BackendLicensingRequest requestForBody(String body) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("body", body); //$NON-NLS-1$
		return new BaseLicensingRequest<>(map, this::requestParameter, this::requestBody);
	}

}
