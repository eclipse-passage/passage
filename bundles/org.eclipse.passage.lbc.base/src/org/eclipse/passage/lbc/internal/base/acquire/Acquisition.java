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

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lbc.internal.base.EObjectTransfer;
import org.eclipse.passage.lbc.internal.base.Failure;
import org.eclipse.passage.lbc.internal.base.Failure.NoGrantsAvailable;
import org.eclipse.passage.lbc.internal.base.api.Grants;
import org.eclipse.passage.lbc.internal.base.PlainSuceess;
import org.eclipse.passage.lbc.internal.base.ProductUserRequest;
import org.eclipse.passage.lic.floating.model.api.GrantAcqisition;
import org.eclipse.passage.lic.floating.model.meta.FloatingPackage;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;
import org.eclipse.passage.lic.internal.base.FeatureIdentifier;
import org.eclipse.passage.lic.internal.emf.EObjectFromBytes;
import org.eclipse.passage.lic.internal.net.handle.NetResponse;

public final class Acquisition {

	private final ProductUserRequest data;
	private final Logger log = LogManager.getLogger(getClass());

	public Acquisition(ProductUserRequest data) {
		Objects.requireNonNull(data, "Acquisition::data"); //$NON-NLS-1$
		this.data = data;
	}

	public NetResponse get() {
		Optional<String> feature = new FeatureIdentifier(key -> data.raw().parameter(key)).get();
		if (!feature.isPresent()) {
			return new Failure.BadRequestNoFeature();
		}
		Optional<GrantAcqisition> acquisition;
		try {
			acquisition = acquisition(feature.get());
		} catch (LicensingException e) {
			log.error("failed: ", e); //$NON-NLS-1$
			return new Failure.OperationFailed(new ConditionAction.Acquire().name(), e.getMessage());
		}
		if (acquisition.isEmpty()) {
			return noGrants(feature.get());
		}
		return new EObjectTransfer(acquisition.get());
	}

	public NetResponse returnBack() throws LicensingException {
		GrantAcqisition acquisition;
		try {
			acquisition = acquisition();
		} catch (IOException e) {
			throw new LicensingException(e);
		}
		boolean released = grants().release(data.product().get(), acquisition);
		if (!released) {
			return new Failure.NotReleased(data.product().get(), acquisition);
		}
		return new PlainSuceess();

	}

	private Optional<GrantAcqisition> acquisition(String feature) throws LicensingException {
		return grants().acquire(//
				data.product().get(), //
				data.user().get(), //
				feature);
	}

	private GrantAcqisition acquisition() throws LicensingException, IOException {
		return new EObjectFromBytes<>(data.raw().content(), GrantAcqisition.class)//
				.get(Collections.singletonMap(FloatingPackage.eNS_URI, FloatingPackage.eINSTANCE));

	}

	private Grants grants() {
		return data.raw().state().grants();
	}

	private NoGrantsAvailable noGrants(String feature) {
		return new Failure.NoGrantsAvailable(data.product().get(), feature);
	}

}
