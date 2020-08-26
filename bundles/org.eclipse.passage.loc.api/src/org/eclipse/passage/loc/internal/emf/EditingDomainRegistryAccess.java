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
package org.eclipse.passage.loc.internal.emf;

public interface EditingDomainRegistryAccess {

	String PROPERTY_DOMAIN_NAME = "org.eclipse.passage.lic.emf.edit.domain.name"; //$NON-NLS-1$
	String PROPERTY_FILE_EXTENSION = "org.eclipse.passage.lic.emf.edit.file.extension"; //$NON-NLS-1$

	EditingDomainRegistry<?> getDomainRegistry(String domain);

	String getFileExtension(String domain);

	SelectionCommandAdvisor getSelectionCommandAdvisor(String domain);
}
