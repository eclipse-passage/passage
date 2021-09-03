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
package org.eclipse.passage.lic.equinox.access;

import java.util.List;
import java.util.Optional;

final class Options<D extends Enum<?>> {
	private final List<Option<D>> options;
	private final Interaction interaction;

	Options(Interaction interaction, List<Option<D>> options) {
		this.options = options;
		this.interaction = interaction;
	}

	Option<D> promptAndPick() {
		while (true) {
			options.forEach(option -> interaction.prompt(option.documentation()));
			String key = interaction.input().trim();
			Optional<Option<D>> option = findOption(key);
			if (option.isPresent()) {
				return option.get();
			}
			interaction.prompt(String.format("No option has been found for key [%s]", key)); //$NON-NLS-1$
		}
	}

	private Optional<Option<D>> findOption(String request) {
		if (request.length() != 1) {
			return Optional.empty();
		}
		char key = request.charAt(0);
		return options.stream().filter(op -> op.key() == key).findAny();
	}

}
