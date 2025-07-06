/*******************************************************************************
 * Copyright (c) 2021, 2025 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation; further evolution
 *******************************************************************************/
package org.eclipse.passage.lic.cli;

import java.util.List;
import java.util.Optional;

final class Options<K extends Option.Key, D extends Enum<?>> {

	private final List<Option<K, D>> options;
	private final TheOtherSide communication;

	Options(List<Option<K, D>> options, TheOtherSide communication) {
		this.options = options;
		this.communication = communication;
	}

	Option<K, D> promptAndPick() {
		while (true) {
			options.forEach(option -> communication.prompt(option.documentation()));
			String key = communication.input().trim();
			Optional<Option<K, D>> option = findOption(key);
			if (option.isPresent()) {
				return option.get();
			}
			communication.prompt(String.format("No option has been found for key [%s]", key)); //$NON-NLS-1$
		}
	}

	private Optional<Option<K, D>> findOption(String request) {
		if (request.length() != 1) {
			return Optional.empty();
		}
		char key = request.charAt(0);
		return options.stream().filter(op -> op.key().symbol() == key).findAny();
	}

	List<Option<K, D>> options() {
		return options;
	}

}
