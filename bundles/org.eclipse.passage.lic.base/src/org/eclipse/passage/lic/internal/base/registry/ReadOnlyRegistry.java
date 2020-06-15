package org.eclipse.passage.lic.internal.base.registry;

import java.util.Collection;

import org.eclipse.passage.lic.internal.api.registry.Service;
import org.eclipse.passage.lic.internal.api.registry.ServiceId;

@SuppressWarnings("restriction")
public final class ReadOnlyRegistry<I extends ServiceId, S extends Service<I>> extends BaseRegistry<I, S> {

	public ReadOnlyRegistry(Collection<S> service) {
		super(service);
	}

}
