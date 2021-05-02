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
package org.eclipse.passage.lic.emf.ecore;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

public class LicensingEcore {

	public static String composeFullQualifiedName(EClass eClass) {
		if (eClass == null) {
			return null;
		}
		EPackage ePackage = eClass.getEPackage();
		if (ePackage == null) {
			return eClass.getName();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(ePackage.getNsPrefix()).append('.');
		sb.append(ePackage.getName()).append('.');
		sb.append(eClass.getName());
		return sb.toString();
	}

	public static String composeFullQualifiedName(EDataType eDataType) {
		if (eDataType == null) {
			return null;
		}
		EPackage ePackage = eDataType.getEPackage();
		if (ePackage == null) {
			return eDataType.getName();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(ePackage.getNsPrefix()).append('.');
		sb.append(ePackage.getName()).append('.');
		sb.append(eDataType.getName());
		return sb.toString();
	}

}
