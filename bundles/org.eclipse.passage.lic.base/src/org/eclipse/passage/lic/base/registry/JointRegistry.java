/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.base.registry;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.registry.Registry;
import org.eclipse.passage.lic.api.registry.Service;
import org.eclipse.passage.lic.api.registry.ServiceId;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;

/**
 * @since 2.1
 */
public final class JointRegistry<I extends ServiceId, S extends Service<I>> implements Registry<I, S> {

	private final List<Registry<I, S>> delegates;

	public JointRegistry(List<Registry<I, S>> delegates) {
		this.delegates = delegates;
	}

	@Override
	public boolean hasService(I id) {
		return delegates.stream().anyMatch(delegate -> delegate.hasService(id));
	}

	@Override
	public S service(I id) {
		return delegates.stream()//
				.filter(delgate -> delgate.hasService(id))//
				.map(delgate -> delgate.service(id)) //
				.findAny() //
				.orElseThrow(() -> new IllegalStateException(notFoundMessage(id)));
	}

	@Override
	public Collection<S> services() {
		return delegates.stream()//
				.map(Registry<I, S>::services) //
				.flatMap(Collection<S>::stream) //
				.collect(Collectors.toList());
	}

	private String notFoundMessage(ServiceId id) {
		return String.format(//
				BaseMessages.getString("JointRegistry.retrieve_absent"), //$NON-NLS-1$
				id, //
				delegates.size(), //
				delegates.stream()//
						.map(Object::toString)//
						.collect(Collectors.joining("\n\t"))); //$NON-NLS-1$
	}

}
