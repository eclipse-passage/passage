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
package org.eclipse.passage.lic.emf.ecore.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.passage.lic.emf.ecore.LicensingEcore;

public class DelegatingEFactory extends EFactoryImpl {

	public final Map<String, EClass> eClassMap = new HashMap<>();
	public final Map<String, EDataType> eDataTypeMap = new HashMap<>();
	public final Map<String, EFactory> eClassFactories = new HashMap<>();
	public final Map<String, EFactory> eDataTypeFactories = new HashMap<>();

	public DelegatingEFactory() {
	}

	public void addEClassDelegate(EFactory factory, Map<EClass, EClass> eClasses) {
		Set<Entry<EClass, EClass>> entrySet = eClasses.entrySet();
		for (Entry<EClass, EClass> entry : entrySet) {
			String key = LicensingEcore.composeFullQualifiedName(entry.getKey());
			eClassFactories.put(key, factory);
			eClassMap.put(key, entry.getValue());
		}
	}

	public void addEDataTypeDelegate(EFactory factory, Map<EDataType, EDataType> eDataTypes) {
		Set<Entry<EDataType, EDataType>> entrySet = eDataTypes.entrySet();
		for (Entry<EDataType, EDataType> entry : entrySet) {
			String key = LicensingEcore.composeFullQualifiedName(entry.getKey());
			eDataTypeFactories.put(key, factory);
			eDataTypeMap.put(key, entry.getValue());
		}
	}

	@Override
	public EObject create(EClass eClass) {
		EFactory factory = resolveDelegate(eClass);
		EClass resolved = resolveEClass(eClass);
		if (factory != null && resolved != null) {
			return factory.create(resolved);
		}
		return super.create(eClass);
	}

	@Override
	public Object createFromString(EDataType eDataType, String stringValue) {
		EFactory factory = resolveDelegate(eDataType);
		EDataType resolved = resolveEDataType(eDataType);
		if (factory != null && resolved != null) {
			return factory.createFromString(resolved, stringValue);
		}
		return super.createFromString(eDataType, stringValue);
	}

	@Override
	public String convertToString(EDataType eDataType, Object objectValue) {
		EFactory factory = resolveDelegate(eDataType);
		EDataType resolved = resolveEDataType(eDataType);
		if (factory != null && resolved != null) {
			return factory.convertToString(resolved, objectValue);
		}
		return super.convertToString(eDataType, objectValue);
	}

	protected EFactory resolveDelegate(EClass eClass) {
		String key = LicensingEcore.composeFullQualifiedName(eClass);
		return eClassFactories.get(key);
	}

	protected EFactory resolveDelegate(EDataType eDataType) {
		String key = LicensingEcore.composeFullQualifiedName(eDataType);
		return eDataTypeFactories.get(key);
	}

	protected EClass resolveEClass(EClass eClass) {
		String key = LicensingEcore.composeFullQualifiedName(eClass);
		return eClassMap.get(key);
	}

	protected EDataType resolveEDataType(EDataType eDataType) {
		String key = LicensingEcore.composeFullQualifiedName(eDataType);
		return eDataTypeMap.get(key);
	}

}
