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
package org.eclipse.passage.lbc.internal.base.chains;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import org.eclipse.passage.lbc.internal.api.BackendLicensingRequest;
import org.eclipse.passage.lbc.internal.api.chains.Chain;
import org.eclipse.passage.lbc.internal.api.persistence.LoadedLicense;
import org.eclipse.passage.lbc.internal.api.persistence.PersistableLicense;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.net.LicensingAction;

@Deprecated
//FIXME: to be reimplemented
public abstract class Operation<I, O> implements Chain {

	private final Function<BackendLicensingRequest, I> parse;
	private final Function<ServiceInvocationResult<O>, ServiceInvocationResult<String>> serialize;
	private final LoadedLicense load;

	public Operation(Function<BackendLicensingRequest, I> parse, //
			Function<ServiceInvocationResult<O>, ServiceInvocationResult<String>> serialize, //
			LoadedLicense load) {
		Objects.requireNonNull(parse, "Action::parse"); //$NON-NLS-1$
		Objects.requireNonNull(serialize, "Action::serialize"); //$NON-NLS-1$
		Objects.requireNonNull(load, "Action::load"); //$NON-NLS-1$
		this.parse = parse;
		this.serialize = serialize;
		this.load = load;
	}

	protected final Optional<PersistableLicense> license(Condition request) {
		return load.apply(request);
	}

	protected abstract ServiceInvocationResult<O> execute(I input);

	public abstract LicensingAction action();

	@Override
	public final String apply(BackendLicensingRequest request) {
		return parse.andThen(this::execute).andThen(serialize).apply(request).data().get();
	}

}
