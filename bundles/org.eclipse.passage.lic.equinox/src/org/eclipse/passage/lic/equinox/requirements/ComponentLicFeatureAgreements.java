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
package org.eclipse.passage.lic.equinox.requirements;

import java.util.Map;

import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.base.NamedData;
import org.eclipse.passage.lic.base.StringNamedData;

/**
 * <p>
 * Encapsulate reading of a raw string {@code agreements} value of a feature
 * under licensing from a {@code OSGi component}'s properties.
 * </p>
 * <p>
 * Sample value: "LICENSE::terms of use.txt::thirdparty/license.html"
 * </p>
 * 
 * @see NamedData
 * @since 2.1
 */
public final class ComponentLicFeatureAgreements extends StringNamedData {

	/**
	 * @param agreements string made of square(::)-separated list of bundle-local
	 *                   paths to licensing agreement content files
	 */
	public ComponentLicFeatureAgreements(Requirement requirement) {
		super(new ListOfAgreements().toSource(requirement.agreements()));
	}

	public ComponentLicFeatureAgreements(Map<String, Object> container) {
		super(container);
	}

	@Override
	public String key() {
		return "licensing.feature.agreements"; //$NON-NLS-1$
	}

}
