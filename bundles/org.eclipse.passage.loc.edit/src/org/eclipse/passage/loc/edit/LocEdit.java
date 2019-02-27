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
package org.eclipse.passage.loc.edit;

import java.io.IOException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistry;

public class LocEdit {
	
	public static final String BUNDLE_SYMBOLIC_NAME = "org.eclipse.passage.loc.edit"; //$NON-NLS-1$

	public static final String EXTENSION_KEY_PRIVATE = ".scr"; //$NON-NLS-1$
	
	public static final String LICENSING_REGISTRY_FILE = "licensing.registry.file"; //$NON-NLS-1$

	public static EditingDomain extractEditingDomain(IEclipseContext context) {
		EditingDomain editingDomain = context.get(EditingDomain.class);
		if (editingDomain != null) {
			return editingDomain;
		}
		IEditingDomainProvider provider = context.get(IEditingDomainProvider.class);
		if (provider != null) {
			return provider.getEditingDomain();
		}
		EditingDomainRegistry registry = context.get(EditingDomainRegistry.class);
		if (registry != null) {
			return registry.getEditingDomain();
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

	public static String extractValidationError(Object input) {
		EObject eObject = null;
		if (input instanceof EObject) {
			eObject = (EObject) input;
		}
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

	public static IStatus save(Resource resource) {
		try {
			//FIXME: define parameters
			resource.save(null);
			return Status.OK_STATUS;
		} catch (IOException e) {
			return new Status(IStatus.ERROR, BUNDLE_SYMBOLIC_NAME, "Error saving resource", e);
		}
	}

}
