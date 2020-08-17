package org.eclipse.passage.lbc.internal.base;

import org.eclipse.passage.lbc.internal.api.Requester;

/**
 * @since 1.0
 */
public class BaseRequester implements Requester {

	private final String feature;
	private final String hardware;
	private final String process;

	public BaseRequester(String process, String hardware, String feature) {
		this.feature = feature;
		this.hardware = hardware;
		this.process = process;
	}

	@Override
	public String feature() {
		return feature;
	}

	@Override
	public String hardware() {
		return hardware;
	}

	@Override
	public String process() {
		return process;
	}

}
