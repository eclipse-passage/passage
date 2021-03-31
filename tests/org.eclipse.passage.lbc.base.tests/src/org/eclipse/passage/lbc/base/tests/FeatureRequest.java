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
package org.eclipse.passage.lbc.base.tests;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lbc.internal.base.EagerFloatingState;
import org.eclipse.passage.lbc.internal.base.api.FloatingState;
import org.eclipse.passage.lbc.internal.base.api.RawRequest;
import org.eclipse.passage.lic.floating.model.meta.FloatingPackage;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.PassageAction;
import org.eclipse.passage.lic.internal.base.FeatureIdentifier;
import org.eclipse.passage.lic.internal.base.ProductIdentifier;
import org.eclipse.passage.lic.internal.base.ProductVersion;
import org.eclipse.passage.lic.internal.emf.EObjectToBytes;
import org.eclipse.passage.lic.internal.net.LicenseUser;

@SuppressWarnings("restriction")
final class FeatureRequest {

	private final PassageAction action;
	private final LicensedProduct product;
	private final String feature;
	private final String user;
	private final String algo;
	private final Optional<EObject> payload;
	private final FloatingState state;

	FeatureRequest(PassageAction action, LicensedProduct product, String feature, String user, String algo,
			FloatingState state) {
		this(action, product, feature, user, algo, Optional.empty(), state);
	}

	FeatureRequest(PassageAction action, LicensedProduct product, String feature, String user, String algo,
			EObject payload, FloatingState state) {
		this(action, product, feature, user, algo, Optional.of(payload), state);
	}

	FeatureRequest(PassageAction action, LicensedProduct product, String feature, String user, String algo,
			Optional<EObject> payload) {
		this(action, product, feature, user, algo, payload, new EagerFloatingState(new TestLicFolder()));
	}

	FeatureRequest(PassageAction action, LicensedProduct product, String feature, String user, String algo,
			Optional<EObject> payload, FloatingState state) {
		this.action = action;
		this.product = product;
		this.feature = feature;
		this.user = user;
		this.algo = algo;
		this.payload = payload;
		this.state = state;
	}

	public RawRequest get() throws LicensingException {
		RequestConstructed construct = new RequestConstructed()//
				.withAction(action)//
				.withParameters(Arrays.asList(//
						new ProductIdentifier(product), //
						new ProductVersion(product), //
						new LicenseUser(user), //
						new FeatureIdentifier(feature))) //
				.withState(state);
		if (payload.isPresent()) {
			construct.withContent(raw(payload.get()));
		}
		return construct.getValid();
	}

	private byte[] raw(EObject obj) throws LicensingException {
		return new EObjectToBytes(obj)//
				.get(Collections.singletonMap(FloatingPackage.eNS_URI, FloatingPackage.eINSTANCE));
	}

}
