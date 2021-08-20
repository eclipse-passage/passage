/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.emf.xmi;

import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.BasicResourceHandler;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.passage.lic.emf.migration.MigrationException;
import org.eclipse.passage.lic.emf.migration.MigrationRoutes;
import org.eclipse.passage.lic.internal.emf.migration.RecognizeFeatures;

/**
 * Adds hooks for migrating legacy data
 * 
 * @since 2.1
 */
public abstract class MigratingResourceHandler extends BasicResourceHandler {

	@Override
	public void preLoad(XMLResource resource, InputStream inputStream, Map<?, ?> options) {
		register();
	}

	/**
	 * Ensure that everything required to read the legacy models is registered
	 */
	protected abstract void register();

	@Override
	public final void postLoad(XMLResource resource, InputStream inputStream, Map<?, ?> options) {
		Set<Entry<EObject, AnyType>> entries = resource.getEObjectToExtensionMap().entrySet();
		for (Entry<EObject, AnyType> entry : entries) {
			try {
				convertEntry(entry);
			} catch (MigrationException e) {
				String message = Optional.ofNullable(resource.getURI())//
						.map(URI::toString)//
						.orElseGet(e::getMessage);
				throw new RuntimeException(message, e);
			}
		}
		evaporizeUnknownParticles(resource);
		complete(resource);
	}

	private void evaporizeUnknownParticles(XMLResource resource) {

	}

	protected void convertEntry(Entry<EObject, AnyType> entry) throws MigrationException {
		new RecognizeFeatures(entry.getValue(), attributes()).apply(entry.getKey());
	}

	protected abstract MigrationRoutes attributes();

	protected abstract void complete(XMLResource resource);
}
