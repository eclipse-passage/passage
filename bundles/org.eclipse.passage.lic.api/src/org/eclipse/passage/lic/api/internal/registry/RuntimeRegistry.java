package org.eclipse.passage.lic.api.internal.registry;

public interface RuntimeRegistry<S extends Service<?>> extends Registry<S> {

	void register(S service);

	void unregister(S service);

}
