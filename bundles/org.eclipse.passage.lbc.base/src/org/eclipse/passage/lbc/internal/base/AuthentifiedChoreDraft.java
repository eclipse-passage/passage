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
package org.eclipse.passage.lbc.internal.base;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.eclipse.passage.lbc.internal.base.api.RawRequest;
import org.eclipse.passage.lic.api.EvaluationInstructions;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.base.io.FileNameFromLicensedProduct;
import org.eclipse.passage.lic.base.io.PassageFileExtension;
import org.eclipse.passage.lic.base.io.PathFromLicensedProduct;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;
import org.eclipse.passage.lic.internal.net.handle.ChoreDraft;
import org.eclipse.passage.lic.internal.net.handle.Failure;
import org.eclipse.passage.lic.internal.net.handle.ProductUserRequest;

abstract class AuthentifiedChoreDraft extends ChoreDraft<RawRequest> {

	protected AuthentifiedChoreDraft(RawRequest request) {
		super(request);
	}

	@Override
	protected Optional<NetResponse> rawInvalid() {
		return serverIsNotAuthenticated();
	}

	@Override
	protected Optional<NetResponse> productUserInvalid(ProductUserRequest<RawRequest> refined) {
		return productUnknown(refined);
	}

	private Optional<NetResponse> serverIsNotAuthenticated() {
		Optional<EvaluationInstructions> instructions = new ServerAuthenticationInstructions(data).get();
		if (!instructions.isPresent()) {
			return Optional.of(new Failure.BadRequestInvalidServerAuthInstructions());
		}
		try {
			new ServerAuthentication(instructions.get()).evaluate();
		} catch (Exception e) {
			log.error("Failed on server authentication attempt: ", e); //$NON-NLS-1$
			return Optional.of(new Failure.ForeignServer(e.getMessage()));
		}
		return Optional.empty();
	}

	private Optional<NetResponse> productUnknown(ProductUserRequest<RawRequest> refined) {
		LicensedProduct product = refined.product().get();
		Path key = new PathFromLicensedProduct(data.state()::source, product).get()//
				.resolve(new FileNameFromLicensedProduct(product, new PassageFileExtension.PublicKey()).get());
		if (!Files.exists(key)) {
			log.error(String.format("Key file [%s] is not found", product)); //$NON-NLS-1$
			return Optional.of(new Failure.BadRequestUnkonwnProduct(product));
		}
		return Optional.empty();
	}

}
