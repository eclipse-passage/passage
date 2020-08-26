/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.loc.internal.emf;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;

public abstract class DomainContentAdapter<I, R extends EditingDomainRegistry<I>> extends EContentAdapter {

	protected final R registry;

	protected DomainContentAdapter(R registry) {
		this.registry = registry;
	}

	@Override
	public void notifyChanged(Notification notification) {
		Object notifier = notification.getNotifier();
		if (notifier instanceof ResourceSet) {
			ResourceSet resourceSet = (ResourceSet) notifier;
			switch (notification.getFeatureID(ResourceSet.class)) {
			case ResourceSet.RESOURCE_SET__RESOURCES:
				processResourceSetResources(resourceSet, notification);
				break;
			default:
				break;
			}
		} else if (notifier instanceof Resource) {
			Resource resource = (Resource) notifier;
			switch (notification.getFeatureID(Resource.class)) {
			case Resource.RESOURCE__CONTENTS:
				processResourceContents(resource, notification);
				break;
			case Resource.RESOURCE__IS_LOADED:
				processResourceIsLoaded(resource, notification);
				break;
			default:
				break;
			}
		}
		super.notifyChanged(notification);
	}

	@SuppressWarnings("unused")
	protected void processResourceSetResources(ResourceSet resourceSet, Notification notification) {
		// nothing by default
	}

	protected void processResourceContents(Resource resource, Notification notification) {
		Object oldValue = notification.getOldValue();
		Object newValue = notification.getNewValue();
		switch (notification.getEventType()) {
		case Notification.ADD:
			processResourceContentsAdded(resource, newValue);
			break;
		case Notification.REMOVE:
			processResourceContentsRemoved(resource, oldValue);
			break;

		default:
			break;
		}
	}

	protected void processResourceContentsAdded(Resource resource, Object newValue) {
		Class<I> contentClass = registry.getContentClass();
		if (contentClass.isInstance(newValue)) {
			I content = contentClass.cast(newValue);
			registry.registerContent(content);
		}
	}

	protected void processResourceContentsRemoved(Resource resource, Object oldValue) {
		Class<I> contentClass = registry.getContentClass();
		if (contentClass.isInstance(oldValue)) {
			I content = contentClass.cast(oldValue);
			String identifier = registry.resolveIdentifier(content);
			registry.unregisterContent(identifier);
		}
	}

	@SuppressWarnings("unused")
	protected void processResourceIsLoaded(Resource resource, Notification notification) {
		// nothing by default
	}

}
