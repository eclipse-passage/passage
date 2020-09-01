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
package org.eclipse.passage.lbc.chains;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.passage.lbc.chains.transform.CertificateOfRequest;
import org.eclipse.passage.lbc.internal.api.RequestedCertificate;
import org.eclipse.passage.lbc.internal.api.persistence.PersistableLicense;
import org.eclipse.passage.lbc.internal.base.ReleaseReport;
import org.eclipse.passage.lbc.internal.base.ReleaseReport.ReleaseResult;
import org.eclipse.passage.lbc.internal.base.SatisfiedRequirements;
import org.eclipse.passage.lbc.internal.base.persistence.LockFolder;
import org.eclipse.passage.lbc.json.Serialization;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;

@SuppressWarnings("restriction")
public final class Release extends Operation<RequestedCertificate, ReleaseReport> {

	public Release(LockFolder folder) {
		super(new CertificateOfRequest(), new Serialization<ReleaseReport>(), folder);
	}

	@Override
	public ServiceInvocationResult<ReleaseReport> execute(RequestedCertificate request) {
		List<ReleaseResult> verdicts = new SatisfiedRequirements().apply(request.certificate()).entrySet().stream() //
				.map(Map.Entry::getValue) //
				.map(Permission::condition) //
				.map(condition -> new ReleaseReport.ReleaseResult(condition, release(condition)))
				.collect(Collectors.toList());
		return new BaseServiceInvocationResult<>(new ReleaseReport(verdicts));
	}

	private boolean release(Condition condition) {
		final Optional<PersistableLicense> found = license(condition);
		if (found.isPresent()) {
			PersistableLicense persistable = found.get();
			if (!persistable.get().releasable()) {
				return false;
			}
			return persistable.releaseOne();
		}
		return false;
	}

}
