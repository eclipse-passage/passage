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
package org.eclipse.passage.lic.base;

import static org.eclipse.passage.lic.api.LicensingResult.ERROR;
import static org.eclipse.passage.lic.api.LicensingResult.INFO;
import static org.eclipse.passage.lic.api.LicensingResult.OK;
import static org.eclipse.passage.lic.api.LicensingResult.WARNING;
import static org.eclipse.passage.lic.base.BaseLicensingResult.CODE_NOMINAL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.passage.lic.api.LicensingEvents;
import org.eclipse.passage.lic.api.LicensingResult;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * 
 * @since 0.5.0
 *
 */
public final class LicensingResults {

	private LicensingResults() {
		// block
	}

	public static LicensingResult createOK() {
		return new BaseLicensingResult(OK, "", LicensingResults.class.getName()); //$NON-NLS-1$
	}

	public static LicensingResult createOK(String message) {
		return new BaseLicensingResult(OK, message, LicensingResults.class.getName());
	}

	/**
	 * Creates a {@link LicensingResult} with {@link LicensingResult#INFO} severity
	 * for the given source, message data
	 * 
	 * @param message - text message for the outcome
	 * @param source  - string source for the outcome
	 * 
	 * @return the licensing result object
	 * 
	 * @see LicensingResult
	 */
	public static LicensingResult createOK(String message, String source) {
		return new BaseLicensingResult(OK, message, source);
	}

	/**
	 * Creates a {@link LicensingResult} with {@link LicensingResult#OK} severity
	 * for the given source, message and attached data
	 * 
	 * @param message     a human-readable message, localized to the current locale
	 * @param source      the unique identifier of the reporter
	 * @param attachments a <code>Map&ltString,Object&gt</code> of attached data or
	 *                    <code>null</code>
	 * 
	 * @return the licensing result object
	 * 
	 * @since 0.5.0
	 * 
	 * @see LicensingResult
	 */
	public static LicensingResult createOK(String message, String source, Map<String, Object> attachments) {
		return new BaseLicensingResult(OK, message, BaseLicensingResult.CODE_NOMINAL, source, null,
				Collections.emptyList(), attachments);
	}

	public static LicensingResult createEvent(String topic, Object data) {
		String source = LicensingResult.class.getName();
		return createEvent(topic, data, source, ""); //$NON-NLS-1$
	}

	public static LicensingResult createEvent(String topic, Object data, String source, String message) {
		LicensingResult basis = createOK(message, source);
		return createEvent(topic, data, basis);
	}

	public static LicensingResult createEvent(String topic, Object data, LicensingResult basis) {
		int severity = basis.getSeverity();
		String message = basis.getMessage();
		int code = basis.getCode();
		String source = basis.getSource();
		Throwable exception = basis.getException();

		List<LicensingResult> details = new ArrayList<>();
		Iterable<LicensingResult> children = basis.getChildren();
		for (LicensingResult child : children) {
			details.add(child);
		}
		Map<String, Object> attachments = new HashMap<>();
		Iterable<String> attachmentKeys = basis.getAttachmentKeys();
		for (String key : attachmentKeys) {
			attachments.put(key, basis.getAttachment(key));
		}
		attachments.put(LicensingEvents.PROPERTY_TOPIC, topic);
		attachments.put(LicensingEvents.PROPERTY_SOURCE, source);
		attachments.put(LicensingEvents.PROPERTY_DATA, data);
		attachments.put(LicensingEvents.PROPERTY_MESSAGE, message);
		return new BaseLicensingResult(severity, message, code, source, exception, details, attachments);
	}

	/**
	 * Creates a {@link LicensingResult} with {@link LicensingResult#INFO} severity
	 * for the given source, message and attached data
	 * 
	 * @param message     a human-readable message, localized to the current locale
	 * @param source      the unique identifier of the reporter
	 * @param attachments a <code>Map&ltString,Object&gt</code> of attached data or
	 *                    <code>null</code>
	 * 
	 * @return the licensing result object
	 * 
	 * @since 0.5.0
	 * 
	 * @see LicensingResult
	 */
	public static LicensingResult createInfo(String message, String source, Map<String, Object> attachments) {
		return new BaseLicensingResult(INFO, message, BaseLicensingResult.CODE_NOMINAL, source, null,
				Collections.emptyList(), attachments);
	}

	/**
	 * Creates a {@link LicensingResult} with {@link LicensingResult#WARNING}
	 * severity for the given class, message and attached data
	 * 
	 * @param message     a human-readable message, localized to the current locale
	 * @param source      the unique identifier of the reporter
	 * @param attachments a <code>Map&ltString,Object&gt</code> of attached data or
	 *                    <code>null</code>
	 *
	 * @return the licensing result object
	 * 
	 * @since 0.5.0
	 * 
	 * @see LicensingResult
	 */
	public static LicensingResult createWarning(String message, String source, Map<String, Object> attachments) {
		return new BaseLicensingResult(WARNING, message, BaseLicensingResult.CODE_NOMINAL, source, null,
				Collections.emptyList(), attachments);
	}

	/**
	 * Creates a {@link LicensingResult} with {@link LicensingResult#WARNING}
	 * severity for the given class, message and attached data
	 * 
	 * @param message     a human-readable message, localized to the current locale
	 * @param source      the {@code Class} of the reporter
	 * @param attachments - attachments for the outcome
	 *
	 * @return the licensing result object
	 * 
	 * @since 0.6.0
	 * 
	 * @see LicensingResult
	 */
	public static LicensingResult createWarning(String message, Class<?> source, Map<String, Object> attachments) {
		Bundle bundle = FrameworkUtil.getBundle(source);
		return new BaseLicensingResult(WARNING, message, BaseLicensingResult.CODE_NOMINAL, bundle.getSymbolicName(),
				null, Collections.emptyList(), attachments);
	}

	/**
	 * Creates a {@link LicensingResult} with {@link LicensingResult#ERROR} severity
	 * for the given message and source data
	 * 
	 * @param message a human-readable message, localized to the current locale
	 * @param source  the unique identifier of the reporter
	 * 
	 * @return the licensing result object
	 * 
	 * @see LicensingResult
	 */
	public static LicensingResult createError(String message, String source) {
		return new BaseLicensingResult(ERROR, message, CODE_NOMINAL, source, null);
	}

	/**
	 * Creates a {@link LicensingResult} with {@link LicensingResult#ERROR} severity
	 * for the given message source and throwable data
	 * 
	 * @param message   a human-readable message, localized to the current locale
	 * @param source    the unique identifier of the reporter
	 * @param exception a low-level exception
	 * @return the licensing result object
	 * 
	 * @see LicensingResult
	 */
	public static LicensingResult createError(String message, String source, Throwable exception) {
		return new BaseLicensingResult(ERROR, message, CODE_NOMINAL, source, exception);
	}

	/**
	 * Creates a {@link LicensingResult} with {@link LicensingResult#ERROR} severity
	 * for the given message, source, code and children result data
	 * 
	 * @param message  a human-readable message, localized to the current locale
	 * @param source   the unique identifier of the reporter
	 * @param children a child result
	 * 
	 * @return the licensing result object
	 * 
	 * @see LicensingResult
	 */
	public static LicensingResult createError(String message, String source, Iterable<LicensingResult> children) {
		return new BaseLicensingResult(ERROR, message, CODE_NOMINAL, source, null, children, null);
	}

	/**
	 * Creates a {@link LicensingResult} with {@link LicensingResult#ERROR} severity
	 * for the given message, code and throwable data
	 * 
	 * @param message   a human-readable message, localized to the current locale
	 * @param code      error code value
	 * @param exception a low-level exception
	 * 
	 * @return the licensing result object
	 * 
	 * @see LicensingResult
	 */
	public static LicensingResult createError(String message, int code, Throwable exception) {
		String source = LicensingResults.class.getName();
		return new BaseLicensingResult(ERROR, message, code, source, exception);
	}

	/**
	 * Creates a {@link LicensingResult} with {@link LicensingResult#ERROR} severity
	 * for the given message, source, code and throwable data
	 * 
	 * @param message   a human-readable message, localized to the current locale
	 * @param source    the unique identifier of the reporter
	 * @param exception a low-level exception
	 * @param code      error code value
	 * 
	 * @return the licensing result object
	 * 
	 * @see LicensingResult
	 */
	public static LicensingResult createError(String message, int code, String source, Throwable exception) {
		return new BaseLicensingResult(ERROR, message, code, source, exception);
	}

}
