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
package org.eclipse.passage.lbc.internal.jetty;

import org.eclipse.passage.lic.internal.jetty.interaction.Command;
import org.eclipse.passage.lic.internal.jetty.interaction.Scope;

final class UploadLicense extends Command {

	public UploadLicense(String scope) {
		super(new Scope.Of(scope), new String[] { "upload" }); //$NON-NLS-1$
	}

	public void upload(String from) {
		// new IncomingLicense(from).upload();
	}

	public void upload(String from, String product, String version) {
		// new IncomingLicense(from).uploadForProduct(product, version);
	}

	public void upload(String... args) {
		if (args.length == 1) {
			upload(args[0]);
		} else if (args.length == 3) {
			upload(args[0], args[1], args[2]);
		} else {
			System.out.println(help());
		}
	}

	private String help() {
		return "[fls:upload] places the given floating license pack at the Server's disposal.\n" + //$NON-NLS-1$
				"Usage:\n\t" + //$NON-NLS-1$
				scope.id() + ":upload <path-to-license-pack-folder>\n\t" + //$NON-NLS-1$
				scope.id() + ":upload <path-to-license-pack-folder> <product-id> <product-version>\n"; //$NON-NLS-1$
	}

}
