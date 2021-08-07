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
package org.eclipse.passage.lic.internal.agreements.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.passage.lic.agreements.AgreementDescriptor;
import org.eclipse.passage.lic.agreements.AgreementGroupDescriptor;
import org.eclipse.passage.lic.agreements.model.api.Agreement;
import org.eclipse.passage.lic.agreements.model.api.AgreementGroup;
import org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage;
import org.eclipse.passage.lic.emf.meta.ClassMetadata;
import org.eclipse.passage.lic.emf.meta.EntityMetadata;
import org.eclipse.passage.lic.emf.meta.PlainEntityMetadata;

public final class AgreementsClassMetadata implements ClassMetadata {

	private final AgreementsPackage meta;
	private final Map<Class<?>, EntityMetadata> map;

	public AgreementsClassMetadata() {
		meta = AgreementsPackage.eINSTANCE;
		map = new HashMap<Class<?>, EntityMetadata>();
		map.put(AgreementGroupDescriptor.class, //
				new PlainEntityMetadata(//
						meta.getAgreementGroup(), //
						meta.getAgreementGroup_Identifier(), //
						meta.getAgreementGroup_Name()));
		map.put(AgreementGroup.class, map.get(AgreementGroupDescriptor.class));
		map.put(AgreementDescriptor.class, //
				new PlainEntityMetadata(//
						meta.getAgreement(), //
						meta.getAgreement_Identifier(), //
						meta.getAgreement_Name()));
		map.put(Agreement.class, map.get(AgreementDescriptor.class));
	}

	@Override
	public Optional<EntityMetadata> find(Class<?> clazz) {
		return Optional.ofNullable(map.get(clazz));
	}

}
