/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.emf.edit;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;

public class LicensingEcore {

	public static EObject extractEObject(Object object) {
		if (object instanceof EObject) {
			return (EObject) object;
		}
		if (object instanceof Resource) {
			Resource resource = (Resource) object;
			EList<EObject> contents = resource.getContents();
			if (!contents.isEmpty()) {
				return contents.get(0);
			}
		}
		return null;
	}

	public static Resource extractResource(Object object) {
		if (object instanceof EObject) {
			EObject eObject = (EObject) object;
			return eObject.eResource();
		}
		if (object instanceof Resource) {
			return (Resource) object;
		}
		return null;
	}

	public static String extractValidationError(EObject eObject) {
		if (eObject == null) {
			return "Input is invalid";
		}
		final Diagnostic result = Diagnostician.INSTANCE.validate(eObject);
		if (result.getSeverity() == Diagnostic.OK) {
			return null;
		}
		// Get the error count and create an appropriate Error message:
		final int errorCount = result.getChildren().size();
	
		final String header = "%s error(s) occured while analyzing your inputs:";
		final String entry = "%s. %s";
	
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
