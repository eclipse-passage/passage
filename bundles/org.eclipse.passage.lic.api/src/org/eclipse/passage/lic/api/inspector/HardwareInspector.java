/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.api.inspector;

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.passage.lic.api.access.PermissionEmitter;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;

/**
 * <p>
 * Contract for a service capable to uncover and enlist current hardware
 * details, sufficient for licensing.
 * </p>
 *
 * <p>
 * Used, for example, by {@code PermissionEmitter} on analysis of a
 * {@code LicensingCondition}, which has <i>hardware</i> {@code conditionType}.
 * This one is expected to enlist all hardware information encoded in it's
 * {@code conditionExpression}. Thus the {@code emitter} can check is encoded
 * hardware information fits to the one gathered by this service.
 * </p>
 *
 * @see PermissionEmitter
 * @see LicensingCondition#getConditionType
 * @see LicensingCondition#getConditionExpression
 * @since 0.4.0
 * @deprecated use 1.0 inspection.RuntimeEnvironment
 */
@Deprecated
public interface HardwareInspector {
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: OS.Family
	 */
	@Deprecated
	String PROPERTY_OS_FAMILY = "os.family"; //$NON-NLS-1$
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: OS.Manufacturer
	 */
	@Deprecated
	String PROPERTY_OS_MANUFACTURER = "os.manufacturer"; //$NON-NLS-1$
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: OS.Version
	 */
	@Deprecated
	String PROPERTY_OS_VERSION = "os.version"; //$NON-NLS-1$
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: OS.BuildNumber
	 */
	@Deprecated
	String PROPERTY_OS_BUILDNUMBER = "os.buildnumber"; //$NON-NLS-1$

	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: Computer.Manufacturer
	 */
	@Deprecated
	String PROPERTY_SYSTEM_MANUFACTURER = "system.manufacturer"; //$NON-NLS-1$
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: Computer.Model
	 */
	@Deprecated
	String PROPERTY_SYSTEM_MODEL = "system.model"; //$NON-NLS-1$
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: Computer.Serial
	 */
	@Deprecated
	String PROPERTY_SYSTEM_SERIALNUMBER = "system.serialnumber"; //$NON-NLS-1$

	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: BaseBoard.Manufacturer
	 */
	@Deprecated
	String PROPERTY_BASEBOARD_MANUFACTURER = "baseboard.manufacturer"; //$NON-NLS-1$
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: BaseBoard.Mode
	 */
	@Deprecated
	String PROPERTY_BASEBOARD_MODEL = "baseboard.model"; //$NON-NLS-1$
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: BaseBoard.version
	 */
	@Deprecated
	String PROPERTY_BASEBOARD_VERSION = "baseboard.version"; //$NON-NLS-1$
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: BaseBoard.Serial
	 */
	@Deprecated
	String PROPERTY_BASEBOARD_SERIALNUMBER = "baseboard.serialnumber"; //$NON-NLS-1$
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: Firmware.Manufacturer
	 */
	@Deprecated
	String PROPERTY_FIRMWARE_MANUFACTURER = "firmware.manufacturer"; //$NON-NLS-1$
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: Firmware.Version
	 */
	@Deprecated
	String PROPERTY_FIRMWARE_VERSION = "firmware.version"; //$NON-NLS-1$
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: Firmware.ReleaseDate
	 */
	@Deprecated
	String PROPERTY_FIRMWARE_RELEASEDATE = "firmware.releasedate"; //$NON-NLS-1$
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: Firmware.Name
	 */
	@Deprecated
	String PROPERTY_FIRMWARE_NAME = "firmware.name"; //$NON-NLS-1$
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: Firmware.Description
	 */
	@Deprecated
	String PROPERTY_FIRMWARE_DESCRIPTION = "firmware.description"; //$NON-NLS-1$

	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: Cpu.Vendor
	 */
	@Deprecated
	String PROPERTY_CPU_VENDOR = "cpu.vendor"; //$NON-NLS-1$ ;
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: Cpu.Family
	 */
	@Deprecated
	String PROPERTY_CPU_FAMILY = "cpu.family"; //$NON-NLS-1$
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: Cpu.Model
	 */
	@Deprecated
	String PROPERTY_CPU_MODEL = "cpu.model"; //$NON-NLS-1$
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: Cpu.Name
	 */
	@Deprecated
	String PROPERTY_CPU_NAME = "cpu.name"; //$NON-NLS-1$
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: Cpu.Identifier
	 */
	@Deprecated
	String PROPERTY_CPU_IDENTIFIER = "cpu.identifier"; //$NON-NLS-1$
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: Cpu.ProcessorId
	 */
	@Deprecated
	String PROPERTY_CPU_PROCESSORID = "cpu.processorid"; //$NON-NLS-1$

	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: Disk.Model
	 */
	@Deprecated
	String PROPERTY_HWDISK_MODEL = "hwdisk.model"; //$NON-NLS-1$ ;
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: Disk.Name
	 */
	@Deprecated
	String PROPERTY_HWDISK_NAME = "hwdisk.name"; //$NON-NLS-1$
	/**
	 * @since 0.4.0
	 * @deprecated moved to `base`: Disk.Serial
	 */
	@Deprecated
	String PROPERTY_HWDISK_SERIAL = "hwdisk.serial"; //$NON-NLS-1$

	/**
	 * Discovers hardware information and writes it to the {@code output}
	 *
	 * @param output target stream. Stays {@code opened}, closure is on the caller's
	 *               side.
	 * @since 0.4.0
	 */
	void dumpHardwareInfo(OutputStream output) throws IOException;

	/**
	 * Enlist names for all uncovered hardware properties.
	 *
	 * @since 0.4.0
	 */
	Iterable<String> getKnownProperties();

	/**
	 * Get a value for a named hardware information.
	 *
	 * @since 0.4.0
	 */
	String inspectProperty(String name);

}
