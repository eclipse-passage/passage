/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.internal.oshi;

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.passage.lic.inspector.HardwareInspector;
import org.eclipse.passage.lic.oshi.OshiHal;

public class OshiHardwareInspector implements HardwareInspector {

	@Override
	public void dumpHardwareInfo(OutputStream output) throws IOException {

		OshiHal.dumpOperatingSystem(output);
		output.write('\n');

		OshiHal.dumpComputerSystem(output);
		output.write('\n');

		OshiHal.dumpCentralProcessor(output);;
	}

	@Override
	public String inspectProperty(String name) {
		return OshiHal.extractProperty(name);
	}

	@Override
	public Iterable<String> getKnownProperties() {
		return OshiHal.getKnownProperties();
	}

}
