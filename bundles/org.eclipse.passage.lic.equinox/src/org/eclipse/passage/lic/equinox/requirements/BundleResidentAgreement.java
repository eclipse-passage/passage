/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.equinox.requirements;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.agreements.ResolvedAgreement;
import org.eclipse.passage.lic.equinox.io.FileFromBundle;
import org.osgi.framework.Bundle;

/**
 * @since 2.1
 */
public final class BundleResidentAgreement implements ResolvedAgreement {

	private final Bundle bundle;
	private final String path;

	public BundleResidentAgreement(Bundle bundle, String path) {
		this.bundle = bundle;
		this.path = path;
	}

	@Override
	public InputStream content() throws IOException {
		return new FileFromBundle(bundle, path).get();
	}

	@Override
	public String path() {
		return path;
	}

	final static class Pack implements Supplier<List<ResolvedAgreement>> {

		private final Bundle bundle;
		private final List<String> paths;

		Pack(Bundle bundle, List<String> paths) {
			this.bundle = bundle;
			this.paths = paths;
		}

		@Override
		public List<ResolvedAgreement> get() {
			return paths.stream()//
					.map(path -> new BundleResidentAgreement(bundle, path))//
					.collect(Collectors.toList());
		}

	}

}
