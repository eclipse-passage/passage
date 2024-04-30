/*******************************************************************************
 * Copyright (c) 2018, 2024 ArSysOp
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
package org.eclipse.passage.lic.features.model.util;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.passage.lic.internal.features.model.migration.FeaturesResourceHandler;

/**
 * <!-- begin-user-doc --> The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.passage.lic.features.model.util.FeaturesResourceFactoryImpl
 * @generated NOT
 * @since 1.1
 */
public class FeaturesResourceImpl extends XMIResourceImpl {
	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public FeaturesResourceImpl(URI uri) {
		super(uri);
	}

	@Override
	protected void init() {
		super.init();
		Map<Object, Object> options = getDefaultLoadOptions();
		options.put(OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
		options.put(OPTION_RESOURCE_HANDLER, new FeaturesResourceHandler());
	}

} // FeaturesResourceImpl
