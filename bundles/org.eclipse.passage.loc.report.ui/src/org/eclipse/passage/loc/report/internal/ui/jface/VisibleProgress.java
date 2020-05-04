package org.eclipse.passage.loc.report.internal.ui.jface;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.loc.report.internal.core.ProductCustomer;
import org.eclipse.passage.loc.report.internal.ui.i18n.ExportCustomersWizardMessages;
import org.eclipse.passage.loc.yars.internal.api.Progress;

@SuppressWarnings("restriction")
final class VisibleProgress implements Progress<ProductCustomer> {

	private final IProgressMonitor monitor;

	VisibleProgress(IProgressMonitor monitor) {
		this.monitor = monitor;
	}

	@Override
	public void estimate(int amount) {
		monitor.beginTask(NLS.bind(ExportCustomersWizardMessages.VisibleProgress_task, amount), amount);
	}

	@Override
	public void reportNodeSrart(ProductCustomer entry) {
		monitor.subTask(entry.toString());
	}

	@Override
	public void reportNodeFinish(ProductCustomer entry) {
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
