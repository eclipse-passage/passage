package org.eclipse.passage.loc.report.internal.core.user;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.passage.loc.report.internal.core.ExportCommandFailureTest;
import org.eclipse.passage.loc.yars.internal.api.Progress;
import org.eclipse.passage.loc.yars.internal.api.ReportException;

@SuppressWarnings("restriction")
public class ExportCustomersCommandFailureTest extends ExportCommandFailureTest<TestCustomers> {

	@Override
	protected void export(TestCustomers data, Path output) throws ReportException {
		new ProductCustomersToCsv(data.storage())//
				.export(//
						new HashSet<>(Arrays.asList("none")), //$NON-NLS-1$
						output, //
						new Progress.Inane<ProductCustomer>()//
				);

	}

	@Override
	protected TestCustomers data() {
		return new TestCustomers.Empty();
	}

}
