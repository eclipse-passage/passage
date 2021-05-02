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
package org.eclipse.passage.lic.emf;

import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

/**
 * @since 2.0
 */
public final class SimpleQualifiedNames implements QualifiedNames {

	@Override
	public String caseEClass(EClass eClass) {
		StringBuilder qualifier = Optional.ofNullable(eClass)//
				.map(EClass::getEPackage)//
				.map(this::qualifier)//
				.orElseGet(StringBuilder::new);
		Optional.ofNullable(eClass)//
				.map(EClass::getName)//
				.ifPresent(qualifier::append);
		return qualifier.toString();
	}

	@Override
	public String caseEDataType(EDataType eDataType) {
		StringBuilder qualifier = Optional.ofNullable(eDataType)//
				.map(EDataType::getEPackage)//
				.map(this::qualifier)//
				.orElseGet(StringBuilder::new);
		Optional.ofNullable(eDataType)//
				.map(EDataType::getName)//
				.ifPresent(qualifier::append);
		return qualifier.toString();
	}

	private StringBuilder qualifier(EPackage ePackage) {
		StringBuilder sb = new StringBuilder();
		sb.append(ePackage.getNsPrefix()).append('.');
		sb.append(ePackage.getName()).append('.');
		return sb;
	}

}
