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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.BasicResourceHandler;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.passage.lic.emf.migration.EFeatureRoutes;
import org.eclipse.passage.lic.emf.migration.EnsureStructure;
import org.eclipse.passage.lic.internal.emf.migration.RecognizeFeatures;

/**
 * Adds hooks for migrating legacy data
 * 
 * @since 2.0
 */
public abstract class MigratingResourceHandler extends BasicResourceHandler {

	@Override
	public void preLoad(XMLResource resource, InputStream inputStream, Map<?, ?> options) {
		ensureMigrations();
	}

	/**
	 * Ensure that everything required to read the legacy models is registered
	 */
	protected abstract void ensureMigrations();

	@Override
	public void postLoad(XMLResource resource, InputStream inputStream, Map<?, ?> options) {
		resource.getEObjectToExtensionMap().entrySet().forEach(this::convertEntry);
	}

	protected void convertEntry(Entry<EObject, AnyType> entry) {
		new RecognizeFeatures(entry.getValue(), attributes(), structures()).mixed(entry.getKey());
	}

	protected abstract EFeatureRoutes attributes();

	protected abstract EnsureStructure structures();
}
