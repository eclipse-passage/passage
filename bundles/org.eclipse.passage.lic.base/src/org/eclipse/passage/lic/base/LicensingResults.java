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

public class LicensingResults {

	public static LicensingResult createOK() {
		return new BaseLicensingResult(OK, "", LicensingResults.class.getName()); //$NON-NLS-1$
	}

	public static LicensingResult createOK(String message) {
		return new BaseLicensingResult(OK, message, LicensingResults.class.getName());
	}

	public static LicensingResult createOK(String message, String source) {
		return new BaseLicensingResult(OK, message, source);
	}

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
		Map<String, Object> attachments = new HashMap<String, Object>();
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

	public static LicensingResult createInfo(String message, String source, Map<String, Object> attachments) {
		return new BaseLicensingResult(INFO, message, BaseLicensingResult.CODE_NOMINAL, source, null,
				Collections.emptyList(), attachments);
	}

	public static LicensingResult createWarning(String message, String source, Map<String, Object> attachments) {
		return new BaseLicensingResult(WARNING, message, BaseLicensingResult.CODE_NOMINAL, source, null,
				Collections.emptyList(), attachments);
	}

	public static LicensingResult createError(String message, String source) {
		return new BaseLicensingResult(ERROR, message, CODE_NOMINAL, source, null);
	}

	public static LicensingResult createError(String message, String source, Throwable e) {
		return new BaseLicensingResult(ERROR, message, CODE_NOMINAL, source, e);
	}

	public static LicensingResult createError(String message, String source, Iterable<LicensingResult> children) {
		return new BaseLicensingResult(ERROR, message, CODE_NOMINAL, source, null, children, null);
	}

	public static LicensingResult createError(String message, int code, Throwable e) {
		String source = LicensingResults.class.getName();
		return new BaseLicensingResult(ERROR, message, code, source, e);
	}

	public static LicensingResult createError(String message, int code, String source, Throwable e) {
		return new BaseLicensingResult(ERROR, message, code, source, e);
	}

}
