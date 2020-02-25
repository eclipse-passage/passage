package org.eclipse.passage.loc.billing.core.tests;

import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class StringsProvider {

	private final List<String> strings = new LinkedList<String>();
	private final Random random = new Random();

	private boolean contains(String string) {
		return strings.contains(string);
	}

	private boolean add(String string) {
		return strings.add(string);
	}

	public final void clear() {
		strings.clear();
	}

	public final String randomString() {
		byte[] array = new byte[5];
		random.nextBytes(array);

		String string = new String(array, Charset.forName("UTF-8")); //$NON-NLS-1$
		if (contains(string)) {
			return randomString();
		}
		add(string);
		return string;
	}

}
