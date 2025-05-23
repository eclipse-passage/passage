package org.eclipse.passage.lic.internal.base.access.storage;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.passage.lic.api.acquire.GrantAcquisition;
import org.eclipse.passage.lic.api.acquire.GrantsTraceService;

public final class InMemoryGrants implements GrantsTraceService {

	private final Set<GrantAcquisition> grants = new HashSet<>();

	@Override
	public void trace(GrantAcquisition grant) {
		grants.add(grant);
	}

	@Override
	public void forget(GrantAcquisition grant) {
		grants.remove(grant);
	}

}
