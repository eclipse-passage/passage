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

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import org.eclipse.passage.lbc.internal.api.BackendLicensingRequest;
import org.eclipse.passage.lbc.internal.api.Chain;
import org.eclipse.passage.lbc.internal.api.persistence.PersistableLicense;
import org.eclipse.passage.lbc.internal.base.BackendAction;
import org.eclipse.passage.lbc.internal.base.persistence.LockFolder;
import org.eclipse.passage.lbc.json.JsonLoadedLicense;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.Condition;

@SuppressWarnings("restriction")
public abstract class Operation<T, U> implements Chain {

	private final Function<BackendLicensingRequest, T> parse;
	private final Function<ServiceInvocationResult<U>, ServiceInvocationResult<String>> serialize;
	private final LockFolder folder;

	public Operation(Function<BackendLicensingRequest, T> parse, //
			Function<ServiceInvocationResult<U>, ServiceInvocationResult<String>> serialize, //
			LockFolder folder) {
		Objects.requireNonNull(parse, "Action::parse"); //$NON-NLS-1$
		Objects.requireNonNull(serialize, "Action::serialize"); //$NON-NLS-1$
		Objects.requireNonNull(folder, "Action::folder"); //$NON-NLS-1$
		this.parse = parse;
		this.serialize = serialize;
		this.folder = folder;
	}

	protected final Optional<PersistableLicense> license(Condition request) {
		return new JsonLoadedLicense(folder).apply(request);
	}

	protected abstract ServiceInvocationResult<U> execute(T t);

	public abstract BackendAction action();

	@Override
	public final String apply(BackendLicensingRequest request) {
		return parse.andThen(this::execute).andThen(serialize).apply(request).data().get();
	}

}
