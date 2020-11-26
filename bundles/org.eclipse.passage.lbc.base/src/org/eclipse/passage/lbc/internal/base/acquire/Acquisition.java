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
package org.eclipse.passage.lbc.internal.base.acquire;

import java.util.Objects;
import java.util.Optional;

import org.eclipse.passage.lbc.internal.api.FloatingResponse;
import org.eclipse.passage.lbc.internal.api.Grants;
import org.eclipse.passage.lbc.internal.base.EObjectTransfer;
import org.eclipse.passage.lbc.internal.base.Failure;
import org.eclipse.passage.lbc.internal.base.PlainSuceess;
import org.eclipse.passage.lbc.internal.base.ProductUserRequest;
import org.eclipse.passage.lbc.internal.base.Failure.NoGrantsAvailable;
import org.eclipse.passage.lic.floating.model.api.GrantAcqisition;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.base.FeatureIdentifier;

public final class Acquisition {

	private final ProductUserRequest data;

	public Acquisition(ProductUserRequest data) {
		Objects.requireNonNull(data, "Acquisition::data"); //$NON-NLS-1$
		this.data = data;
	}

	public FloatingResponse get() {
		Optional<String> feature = new FeatureIdentifier(key -> data.raw().parameter(key)).get();
		if (!feature.isPresent()) {
			return new Failure.BadRequestNoFeature();
		}
		Optional<GrantAcqisition> acquisition;
		try {
			acquisition = acquisition(feature.get());
		} catch (LicensingException e) {
			return new Failure.OperationFailed("Acquire", e.getMessage()); //$NON-NLS-1$
		}
		if (acquisition.isEmpty()) {
			return noGrants(feature.get());
		}
		return new EObjectTransfer(acquisition.get());
	}

	public FloatingResponse returnBack(GrantAcqisition acqisition) {
		boolean released = grants().release(//
				data.product().get(), //
				acqisition);
		if (!released) {
			return new Failure.NotReleased(data.product().get(), acqisition);
		}
		return new PlainSuceess();

	}

	private Optional<GrantAcqisition> acquisition(String feature) throws LicensingException {
		return grants().acquire(//
				data.product().get(), //
				data.user().get(), //
				feature);
	}

	private Grants grants() {
		return data.raw().state().grants();
	}

	private NoGrantsAvailable noGrants(String feature) {
		return new Failure.NoGrantsAvailable(data.product().get(), feature);
	}

}
