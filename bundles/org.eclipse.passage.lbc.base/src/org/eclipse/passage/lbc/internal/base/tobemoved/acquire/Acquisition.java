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
package org.eclipse.passage.lbc.internal.base.tobemoved.acquire;

import java.util.Objects;
import java.util.Optional;

import org.eclipse.passage.lbc.internal.api.tobemoved.FloatingResponse;
import org.eclipse.passage.lbc.internal.api.tobemoved.Grants;
import org.eclipse.passage.lbc.internal.base.tobemoved.EObjectTransfer;
import org.eclipse.passage.lbc.internal.base.tobemoved.Failure;
import org.eclipse.passage.lbc.internal.base.tobemoved.Failure.NoGrantsAvailable;
import org.eclipse.passage.lbc.internal.base.tobemoved.FeatureRequest;
import org.eclipse.passage.lic.floating.model.api.GrantAcqisition;
import org.eclipse.passage.lic.internal.api.LicensingException;

public final class Acquisition {

	private final FeatureRequest data;

	public Acquisition(FeatureRequest data) {
		Objects.requireNonNull(data, "Acquisition::data"); //$NON-NLS-1$
		this.data = data;
	}

	public FloatingResponse get() {
		Optional<GrantAcqisition> acquisition;
		try {
			acquisition = acquisition();
		} catch (LicensingException e) {
			return new Failure.OperationFailed("Acquire", e.getMessage()); //$NON-NLS-1$
		}
		if (acquisition.isEmpty()) {
			return noGrants();
		}
		return new EObjectTransfer(acquisition.get());
	}

	private Optional<GrantAcqisition> acquisition() throws LicensingException {
		return grants().acquire(//
				data.product().get(), //
				data.user().get(), //
				data.feature().get());
	}

	private Grants grants() {
		return data.raw().state().grants();
	}

	private NoGrantsAvailable noGrants() {
		return new Failure.NoGrantsAvailable(data.product().get(), data.feature().get());
	}

}
