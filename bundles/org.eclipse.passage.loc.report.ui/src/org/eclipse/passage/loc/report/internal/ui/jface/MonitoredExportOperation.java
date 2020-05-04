package org.eclipse.passage.loc.report.internal.ui.jface;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.passage.loc.report.internal.core.ExportService;
import org.eclipse.passage.loc.yars.internal.api.ReportException;

@SuppressWarnings("restriction")
final class MonitoredExportOperation implements IRunnableWithProgress {

	private final ExportService service;
	private final Set<String> products;
	private final Path file;

	MonitoredExportOperation(ExportService service, Set<String> products, Path file) {
		this.service = service;
		this.products = products;
		this.file = file;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		try {
			service.exportCustomersForProducts(products, file, new VisibleProgress(monitor));
		} catch (ReportException e) {
			throw new InvocationTargetException(e);
		}
	}

}
