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

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.passage.lic.internal.emf.i18n.EmfMessages;

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

	public static String extractValidationError(EObject eObject) {
		if (eObject == null) {
			return EmfMessages.LicensingEcore_input_invalid;
		}
		final Diagnostic result = Diagnostician.INSTANCE.validate(eObject);
		if (result.getSeverity() == Diagnostic.OK) {
			return null;
		}
		// Get the error count and create an appropriate Error message:
		final int errorCount = result.getChildren().size();
		final String header = EmfMessages.LicensingEcore_inpur_header;
		final String entry = EmfMessages.LicensingEcore_input_entry;
		final StringBuilder sb = new StringBuilder();
		sb.append(String.format(header, errorCount));
		sb.append('\n');
		int messageCount = 0;
		for (final Diagnostic d : result.getChildren()) {
			sb.append('\n');
			sb.append(String.format(entry, ++messageCount, d.getMessage()));
		}
		return sb.toString();
	}

}
