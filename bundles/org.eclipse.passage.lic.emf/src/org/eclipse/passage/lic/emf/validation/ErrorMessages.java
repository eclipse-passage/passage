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
package org.eclipse.passage.lic.emf.validation;

import java.util.Optional;
import java.util.function.Function;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.passage.lic.internal.emf.i18n.EmfMessages;

/**
 * Summarizes validation errors for the given {@link EObject}
 * 
 * @since 2.0
 */
public final class ErrorMessages implements Function<EObject, Optional<String>> {

	@Override
	public Optional<String> apply(EObject input) {
		if (input == null) {
			return Optional.of(EmfMessages.LicensingEcore_input_invalid);
		}
		final Diagnostic result = Diagnostician.INSTANCE.validate(input);
		if (result.getSeverity() == Diagnostic.OK) {
			return Optional.empty();
		}
		// Get the error count and create an appropriate Error message:
		final int errorCount = result.getChildren().size();
		final String header = EmfMessages.LicensingEcore_inpur_header;
		final String entry = EmfMessages.LicensingEcore_input_entry;
		final StringBuilder sb = new StringBuilder();
		sb.append(String.format(header, errorCount));
		sb.append('\n');
		int messageCount = 0;
		for (final Diagnostic d : result.getChildren()) {
			sb.append('\n');
			sb.append(String.format(entry, ++messageCount, d.getMessage()));
		}
		return Optional.of(sb.toString());
	}

}
