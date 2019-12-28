package org.eclipse.passage.loc.report.internal.core;

import org.eclipse.passage.loc.yars.internal.api.DOSHandleMedia;
import org.eclipse.passage.loc.yars.internal.api.ExportData;

@SuppressWarnings("restriction")
final class InnerData implements ExportData<ProductCustomer, DOSHandleMedia<ProductCustomer>> {

	private final String name;
	private final String data;

	public InnerData(String name, String data) {
		this.name = name;
		this.data = data;
	}

	@Override
	public void write(DOSHandleMedia<ProductCustomer> media) {
		media.inner(data, name);

	}

}
