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
package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.util.Map;

import org.eclipse.jface.dialogs.IDialogConstants;

final class ButtonConfig {

	private final int id;
	private final Runnable action;
	private final String name;
	private final String tooltip;
	private final String image;

	ButtonConfig(int id, Runnable action, String name, String tooltip, String image) {
		this.image = image;
		this.id = IDialogConstants.CLIENT_ID + id;
		this.action = action;
		this.name = name;
		this.tooltip = tooltip;
	}

	void reside(Map<Integer, ButtonConfig> residence) {
		residence.put(id, this);
	}

	int id() {
		return id;
	}

	String name() {
		return name;
	}

	String tooltip() {
		return tooltip;
	}

	String image() {
		return image;
	}

	Runnable action() {
		return action;
	}

}
