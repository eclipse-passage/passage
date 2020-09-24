/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.equinox.requirements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.base.KeyValuePairs;

/**
 * Looks for {@linkplain Requirement} declaration in a single
 * {@code Capability}.
 * 
 * @see RequirementsFromBundle
 * @see BundleRequirements
 */
final class LicCapabilityAttributesFromDeclaration {

	private final String declarations;

	public LicCapabilityAttributesFromDeclaration(String declarations) {
		this.declarations = declarations;
	}

	public Collection<Map<String, Object>> get() throws LicensingException {
		String namespace = new LicCapabilityNamespace().get();
		List<String> sources = Arrays.stream(declarations.split(",")) //$NON-NLS-1$
				.map(String::trim) //
				.filter(source -> source.startsWith(namespace)) //
				.filter(declaration -> !declaration.isEmpty())//
				.map(source -> source.substring(0, namespace.length()))//
				.collect(Collectors.toList());
		List<Map<String, Object>> structured = new ArrayList<>();
		for (String source : sources) {
			structured.add(attributes(source));
		} // morsel failure is contagious
		return structured;
	}

	private Map<String, Object> attributes(String source) throws LicensingException {
		Properties properties = new KeyValuePairs(//
				lined(source), //
				"Failed to compose licensing requirement declaration") //$NON-NLS-1$
						.get();
		return properties.keySet().stream() //
				.map(String.class::cast)//
				.collect(Collectors.toMap(Function.identity(), properties::get));
	}

	private String lined(String source) {
		return source.replaceAll(";", "\n"); //$NON-NLS-1$//$NON-NLS-2$
	}

}
