/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.emf.migration;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.passage.lic.emf.QualifiedNames;
import org.eclipse.passage.lic.emf.SimpleQualifiedNames;

public final class DelegatingEFactory extends EFactoryImpl {

	public final Map<String, EClass> eClassMap = new HashMap<>();
	public final Map<String, EDataType> eDataTypeMap = new HashMap<>();
	public final Map<String, EFactory> eClassFactories = new HashMap<>();
	public final Map<String, EFactory> eDataTypeFactories = new HashMap<>();

	private final QualifiedNames names;

	DelegatingEFactory() {
		names = new SimpleQualifiedNames();
	}

	public void delegateEClass(EFactory factory, EClass from, EClass to) {
		String key = names.caseEClass(from);
		eClassFactories.put(key, factory);
		eClassMap.put(key, to);
	}

	public void delegateEDataType(EFactory factory, EDataType from, EDataType to) {
		String key = names.caseEDataType(from);
		eDataTypeFactories.put(key, factory);
		eDataTypeMap.put(key, to);
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

	private EFactory resolveDelegate(EClass eClass) {
		return eClassFactories.get(names.caseEClass(eClass));
	}

	private EFactory resolveDelegate(EDataType eDataType) {
		return eDataTypeFactories.get(names.caseEDataType(eDataType));
	}

	private EClass resolveEClass(EClass eClass) {
		return eClassMap.get(names.caseEClass(eClass));
	}

	private EDataType resolveEDataType(EDataType eDataType) {
		return eDataTypeMap.get(names.caseEDataType(eDataType));
	}

}
