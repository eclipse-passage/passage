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
package org.eclipse.passage.lic.model.core;

import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_CONTENT_TYPE;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_CONTENT_TYPE_XML;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.passage.lic.model.meta.LicPackage;
import org.eclipse.passage.lic.registry.LicensePackDescriptor;
import org.eclipse.passage.lic.runtime.LicensingCondition;
import org.eclipse.passage.lic.runtime.io.LicensingConditionTransport;
import org.osgi.service.component.annotations.Component;

@Component(property = { LICENSING_CONTENT_TYPE + '=' + LICENSING_CONTENT_TYPE_XML })
public class XmiLicensingConditionExtractor implements LicensingConditionTransport {
	
	public XmiLicensingConditionExtractor() {
		EPackage.Registry.INSTANCE.put("http://www.arsysop.ru/passage/lic/0.3.1", LicPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put("http://www.arsysop.ru/passage/lic/0.3.2", LicPackage.eINSTANCE);
	}

	@Override
	public Iterable<LicensingCondition> readConditionDescriptors(InputStream input) throws IOException {
		Resource resource = new XMIResourceImpl();
		resource.load(input, new HashMap<>());
		List<LicensingCondition> extracted = new ArrayList<>();
		EList<EObject> contents = resource.getContents();
		for (EObject eObject : contents) {
			if (eObject instanceof LicensePackDescriptor) {
				LicensePackDescriptor license = (LicensePackDescriptor) eObject;
				Iterable<? extends LicensingCondition> conditions = license.getLicenseGrants();
				conditions.forEach(extracted::add);
			}
		}
		return extracted;
	}

	@Override
	public void writeConditionDescriptors(Iterable<LicensingCondition> conditions, OutputStream output)
			throws IOException {
		Resource resource = new XMIResourceImpl();
		EList<EObject> contents = resource.getContents();
		for (LicensingCondition conditionDescriptor : conditions) {
			if (conditionDescriptor instanceof EObject) {
				EObject eObject = (EObject) conditionDescriptor;
				contents.add(eObject);
			}
		}
		resource.save(output, new HashMap<>());
	}

}
