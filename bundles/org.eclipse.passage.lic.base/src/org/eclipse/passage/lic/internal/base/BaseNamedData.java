package org.eclipse.passage.lic.internal.base;

import java.util.Optional;
import java.util.function.Function;

/**
 * Lazy implementation does not store the value itself on construction time, but
 * keep the way to get the data should the actual need arise.
 * 
 * @param <T> type of the value to supply
 * @see NamedData
 */
public abstract class BaseNamedData<T> implements NamedData<T> {

	private final Function<String, T> retrieve;

	protected BaseNamedData(Function<String, T> retrieve) {
		this.retrieve = retrieve;
	}

	@Override
	public Optional<T> get() {
		return Optional.ofNullable(retrieve.apply(key()));
	}

}
