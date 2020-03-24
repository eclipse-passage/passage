package org.eclipse.passage.lic.internal.base;

import java.util.Optional;
import java.util.function.Function;

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
