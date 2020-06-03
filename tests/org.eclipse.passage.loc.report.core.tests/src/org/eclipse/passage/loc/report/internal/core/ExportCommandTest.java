package org.eclipse.passage.loc.report.internal.core;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;

import org.junit.Test;

/**
 * <p>
 * Supplies default smoke-tests for each export operation:
 * </p>
 * <ul>
 * <li>for empty data set (only headers should appear in the target file)
 * and</li>
 * <li>for not-empty one</li>
 * </ul>
 * 
 * @param <T> domain-specific subclass of {@linkplain TestData}
 */
public abstract class ExportCommandTest<T extends TestData<?>> extends BaseExportCommandTest<T> {
	@Test
	public void exportSome() {
		testExport(some());
	}

	@Test
	public void exportNone() {
		testExport(none());
	}

	private void testExport(T data) {
		Path output = outputFile(""); //$NON-NLS-1$
		exportSilent(data, output);
		assertEquals(data.csv(), results(output));
	}

	protected abstract T some();

	protected abstract T none();

}
