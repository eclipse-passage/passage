package org.eclipse.passage.lic.internal.base;

import java.util.Map;
import java.util.function.Supplier;

public interface ProductInfo extends Supplier<String> {

	void write(Map<String, Object> target);

	void write(StringBuilder target);

}
