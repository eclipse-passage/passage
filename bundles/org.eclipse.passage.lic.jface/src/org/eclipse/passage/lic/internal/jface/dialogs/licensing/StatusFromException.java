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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.passage.lic.internal.api.diagnostic.Diagnostic;

public final class StatusFromException implements Supplier<IStatus> {
	private final Throwable throwable;

	public StatusFromException(Throwable throwable) {
		this.throwable = throwable;
	}

	@Override
	public IStatus get() {
		Status[] children = causes().stream() //
				.flatMap(this::segments)//
				.map(segment -> new Status(IStatus.ERROR, Diagnostic.class, segment))//
				.collect(Collectors.toList())//
				.toArray(new Status[0]);
		return new MultiStatus(Diagnostic.class, IStatus.ERROR, children, throwable.toString(), throwable);
	}

	private List<Throwable> causes() {
		List<Throwable> causes = new ArrayList<>();
		for (Throwable current = throwable; current != null; current = current.getCause()) {
			causes.add(current);
		}
		return causes;
	}

	private Stream<String> segments(Throwable thro) {
		List<String> out = new ArrayList<>();
		out.add(String.format("|>>>>> %s", thro.getLocalizedMessage())); //$NON-NLS-1$
		for (StackTraceElement element : thro.getStackTrace()) {
			out.add(element.toString());
		}
		return out.stream();
	}

}
