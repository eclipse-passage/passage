package org.eclipse.passage.lbc.internal.api;

import java.util.function.Function;

import org.eclipse.passage.lic.internal.base.StringNamedData;

/**
 * @since 1.0
 */
public class BackendUser extends StringNamedData {

	public BackendUser(String value) {
		super(value);
	}

	public BackendUser(Function<String, String> retrieve) {
		super(retrieve);
	}

	@Override
	public String key() {
		return "user"; //$NON-NLS-1$
	}

}
