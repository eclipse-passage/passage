/*******************************************************************************
 * Copyright (c) 2025 ArSysOp
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

package org.eclipse.passage.lic.cli;

import java.util.List;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;

public interface TheOtherSide extends Interaction {

	void withContext(ServiceInvocationResult<ExaminationCertificate> assessment, List<? extends Option.Key> keys);

	public static final class Blind implements TheOtherSide {

		private final Interaction delegate;

		public Blind(Interaction delegate) {
			this.delegate = delegate;
		}

		@Override
		public void prompt(String information) {
			delegate.prompt(information);
		}

		@Override
		public void swear(Throwable thro) {
			delegate.swear(thro);
		}

		@Override
		public String input() {
			return delegate.input();
		}

		@Override
		public void withContext(ServiceInvocationResult<ExaminationCertificate> assessment,
				List<? extends Option.Key> keys) {
			// see them not
		}

	}

}
