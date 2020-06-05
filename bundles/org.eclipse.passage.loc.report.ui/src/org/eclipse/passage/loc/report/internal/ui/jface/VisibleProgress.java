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
package org.eclipse.passage.loc.report.internal.ui.jface;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.loc.yars.internal.api.Progress;

@SuppressWarnings("restriction")
public final class VisibleProgress<T> implements Progress<T> {

	private final IProgressMonitor monitor;
	private final String message;

	public VisibleProgress(IProgressMonitor monitor, String message) {
		this.monitor = monitor;
		this.message = message;
	}

	@Override
	public void estimate(int amount) {
		monitor.beginTask(NLS.bind(message, amount), amount);
	}

	@Override
	public void reportNodeSrart(T entry) {
		monitor.subTask(entry.toString());
	}

	@Override
	public void reportNodeFinish(T entry) {
		monitor.worked(1);
	}

	@Override
	public void report(String info) {
		monitor.subTask(info);
	}

	@Override
	public boolean cancelDemanded() {
		return monitor.isCanceled();
	}

}
