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

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.passage.lic.api.LicensingResult;

public class BaseLicensingResult implements LicensingResult {

	public static final int CODE_NOMINAL = 0;

	private int severity;
	private final String message;
	private final int code;
	private final String source;
	private final Throwable exception;

	private final Map<String, Object> attachments = new LinkedHashMap<>();
	private final List<LicensingResult> children = new ArrayList<>();

	public BaseLicensingResult(int severity, String message, String source) {
		this(severity, message, CODE_NOMINAL, source, null, null, null);
	}

	public BaseLicensingResult(int severity, String message, int code, String source, Throwable exception) {
		this(severity, message, code, source, exception, null, null);
	}

	public BaseLicensingResult(int severity, String message, int code, String source, Throwable exception,
			Iterable<LicensingResult> details, Map<String, Object> data) {
		super();
		this.severity = severity;
		this.message = message;
		this.source = source;
		this.code = code;
		this.exception = exception;
		if (details != null) {
			for (LicensingResult result : details) {
				addChild(result);
			}
		}
		if (data != null) {
			attachments.putAll(data);
		}
	}

	@Override
	public int getSeverity() {
		return severity;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String getSource() {
		return source;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public Throwable getException() {
		return exception;
	}

	@Override
	public Iterable<String> getAttachmentKeys() {
		return attachments.keySet();
	}

	@Override
	public Object getAttachment(String key) {
		return attachments.get(key);
	}

	@Override
	public Iterable<LicensingResult> getChildren() {
		return Collections.unmodifiableList(children);
	}

	protected void addChild(LicensingResult result) {
		int newSev = result.getSeverity();
		if (newSev > getSeverity()) {
			this.severity = newSev;
		}
		children.add(result);
	}

}
