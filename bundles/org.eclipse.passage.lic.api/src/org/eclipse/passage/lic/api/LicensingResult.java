/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.lic.api;

/**
 * Information about the outcome of some licensing activity.
 * 
 * @since 0.4.0
 */
public interface LicensingResult {

	/**
	 * The bit mask value <code>0x0</code> for a {@link #getSeverity severity}
	 * indicating the nominal case.
	 *
	 * @since 0.4.0
	 */
	int OK = 0x0;

	/**
	 * The bit mask value <code>0x1</code> for a {@link #getSeverity severity}
	 * indicating the information.
	 *
	 * @since 0.4.0
	 */
	int INFO = 0x1;

	/**
	 * The bit mask value <code>0x2</code> for a {@link #getSeverity severity}
	 * indicating the warning.
	 *
	 * @since 0.4.0
	 */
	int WARNING = 0x2;

	/**
	 * The bit mask value <code>0x4</code> for a {@link #getSeverity severity}
	 * indicating the error.
	 *
	 * @since 0.4.0
	 */
	int ERROR = 0x4;

	/**
	 * The bit mask value <code>0x8</code> for a {@link #getSeverity severity}
	 * indicating that the activity was canceled.
	 *
	 * @since 0.4.0
	 */
	int CANCEL = 0x8;

	/**
	 * Returns an indicator of the severity of the problem.
	 *
	 * @since 0.4.0
	 */
	int getSeverity();

	/**
	 * Returns a message describing the situation.
	 *
	 * @since 0.4.0
	 */
	String getMessage();

	/**
	 * Returns the unique identifier of the source.
	 *
	 * @since 0.4.0
	 */
	String getSource();

	/**
	 * Returns {@link #getSource source-specific} identity code.
	 *
	 * @since 0.4.0
	 */
	int getCode();

	/**
	 * Returns the exception that caused this result, or <code>null</code> if none.
	 *
	 * @since 0.4.0
	 */
	Throwable getException();

	/**
	 * Returns the keys for the attached data.
	 *
	 * @since 0.4.0
	 */
	Iterable<String> getAttachmentKeys();

	/**
	 * Returns the attachment for the given key or <code>null</code> if none.
	 * 
	 * @param key the key whose associated value is to be returned
	 * @return the attached object or <code>null</code> if none
	 * @see #getAttachmentKeys()
	 *
	 * @since 0.4.0
	 */
	Object getAttachment(String key);

	/**
	 * Returns the list of child {@link LicensingResult licensing results}.
	 *
	 * @since 0.4.0
	 */
	Iterable<LicensingResult> getChildren();

}
