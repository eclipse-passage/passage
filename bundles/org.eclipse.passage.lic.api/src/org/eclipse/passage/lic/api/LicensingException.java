package org.eclipse.passage.lic.api;

public class LicensingException extends Exception {

	private static final long serialVersionUID = 1L;

	private LicensingResult diagnostic;

	public LicensingException(LicensingResult result) {
		super(result.getMessage(), result.getException());
		this.diagnostic = result;
	}

	public final LicensingResult getResult() {
		return diagnostic;
	}
}
