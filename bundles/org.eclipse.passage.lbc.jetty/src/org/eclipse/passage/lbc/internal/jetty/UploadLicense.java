/*******************************************************************************
 * Copyright (c) 2021, 2022 ArSysOp
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

import java.nio.file.Path;
import java.util.List;

import org.eclipse.passage.lbc.internal.base.interaction.IncomingLicense;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.base.io.LicensingFolder;
import org.eclipse.passage.lic.base.io.UserHomePath;
import org.eclipse.passage.lic.internal.jetty.interaction.Command;
import org.eclipse.passage.lic.internal.jetty.interaction.Scope;

final class UploadLicense extends Command {

	private final Path storage;

	public UploadLicense(String scope) {
		this(scope, new LicensingFolder(new UserHomePath()).get()); // $NON-NLS-1$
	}

	public UploadLicense(String scope, Path storage) {
		this(new Scope.Of(scope), new Upload().get(), storage);
	}

	private UploadLicense(Scope scope, List<String> names, Path storage) {
		super(scope, names);
		this.storage = storage;
	}

	public void upload(String from) {
		ServiceInvocationResult<List<Path>> result = new IncomingLicense(from, storage).upload();
		if (result.data().isPresent()) {
			reportDestination(result.data().get());
		}
		reportDiagnostic(result.diagnostic());

	}

	private void reportDestination(List<Path> list) {
		System.out.println("Floating lincens(es) uploaded to "); //$NON-NLS-1$
		list.forEach(path -> System.out.println("\t" + path.toAbsolutePath())); //$NON-NLS-1$
	}

	public void upload(String... args) {
		if (args.length == 1) {
			upload(args[0]);
		} else {
			System.out.println(usage());
		}
	}

	@Override
	protected List<String> commands() {
		return new Upload().get();
	}

	@Override
	public String usage() {
		return "[fls:upload] scans the given folder for floating licenses and uploads all findings at the Server's disposal.\n" //$NON-NLS-1$
				+ "Usage:\n\t" //$NON-NLS-1$
				+ scope.id() + ":upload <path-to-folder>\n\t"; //$NON-NLS-1$
	}

	private static final class Upload extends Name {

		protected Upload() {
			super("upload"); //$NON-NLS-1$
		}

	}

}
