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
package org.eclipse.passage.lic.runtime.inspector;

import java.io.IOException;
import java.io.OutputStream;

public interface HardwareInspector {
	
	String PROPERTY_OS_FAMILY = "os.family"; //$NON-NLS-1$
	String PROPERTY_OS_MANUFACTURER = "os.manufacturer"; //$NON-NLS-1$
	String PROPERTY_OS_VERSION = "os.version"; //$NON-NLS-1$
	String PROPERTY_OS_BUILDNUMBER = "os.buildnumber"; //$NON-NLS-1$

	String PROPERTY_SYSTEM_MANUFACTURER = "system.manufacturer"; //$NON-NLS-1$
	String PROPERTY_SYSTEM_MODEL = "system.model"; //$NON-NLS-1$
	String PROPERTY_SYSTEM_SERIALNUMBER = "system.serialnumber"; //$NON-NLS-1$

	String PROPERTY_BASEBOARD_MANUFACTURER = "baseboard.manufacturer"; //$NON-NLS-1$
	String PROPERTY_BASEBOARD_MODEL = "baseboard.model"; //$NON-NLS-1$
	String PROPERTY_BASEBOARD_VERSION = "baseboard.version"; //$NON-NLS-1$
	String PROPERTY_BASEBOARD_SERIALNUMBER = "baseboard.serialnumber"; //$NON-NLS-1$

	String PROPERTY_FIRMWARE_MANUFACTURER = "firmware.manufacturer"; //$NON-NLS-1$
	String PROPERTY_FIRMWARE_VERSION = "firmware.version"; //$NON-NLS-1$
	String PROPERTY_FIRMWARE_RELEASEDATE = "firmware.releasedate"; //$NON-NLS-1$
	String PROPERTY_FIRMWARE_NAME = "firmware.name"; //$NON-NLS-1$
	String PROPERTY_FIRMWARE_DESCRIPTION = "firmware.description"; //$NON-NLS-1$

	String PROPERTY_CPU_VENDOR = "cpu.vendor"; //$NON-NLS-1$;
	String PROPERTY_CPU_FAMILY = "cpu.family"; //$NON-NLS-1$
	String PROPERTY_CPU_MODEL = "cpu.model"; //$NON-NLS-1$
	String PROPERTY_CPU_NAME = "cpu.name"; //$NON-NLS-1$
	String PROPERTY_CPU_IDENTIFIER = "cpu.identifier"; //$NON-NLS-1$
	String PROPERTY_CPU_PROCESSORID = "cpu.processorid"; //$NON-NLS-1$

	void dumpHardwareInfo(OutputStream output) throws IOException;

	Iterable<String> getKnownProperties();

	String inspectProperty(String name);

}
