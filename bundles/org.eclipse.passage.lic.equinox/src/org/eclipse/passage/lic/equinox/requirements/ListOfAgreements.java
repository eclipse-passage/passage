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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.agreements.ResolvedAgreement;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.base.StringNamedData;
import org.osgi.service.component.runtime.dto.ComponentDescriptionDTO;

/**
 * Looks for {@linkplain Requirement} declarations in the given
 * {@code OSGi component} ({@linkplain ComponentDescriptionDTO}).
 * 
 * @see RequirementFromCapability
 * @see BundleRequirements
 */
final class ListOfAgreements {

	private final String separator = "::"; //$NON-NLS-1$

	List<String> fromSource(StringNamedData data) {
		return Arrays.stream(value(data).split(separator)) // $NON-NLS-1$
				.map(String::trim)//
				.filter(path -> !path.isEmpty())//
				.collect(Collectors.toList());
	}

	String toSource(List<ResolvedAgreement> agreements) {
		return agreements.stream()//
				.map(ResolvedAgreement::path)//
				.collect(Collectors.joining(separator));
	}

	private String value(StringNamedData data) {
		return data.get().orElse(""); //$NON-NLS-1$
	}

}
