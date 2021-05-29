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
package org.eclipse.passage.loc.internal.licenses.core;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistry;

final class UpdateLicensePlan {

	private final LicenseRegistry licenses;
	private final Optional<IEditingDomainProvider> domain;

	UpdateLicensePlan(LicenseRegistry licenses) {
		this.licenses = licenses;
		this.domain = (licenses instanceof IEditingDomainProvider) //
				? Optional.of((IEditingDomainProvider) licenses)//
				: Optional.empty();
	}

	void withPersonal(PersonalLicensePack license) throws IOException {
		updatePlan(//
				license, //
				lic -> lic.getLicense().getPlan(), //
				LicensesPackage.eINSTANCE.getLicensePlan_Personal());
	}

	void withFloating(FloatingLicensePack license) throws IOException {
		updatePlan(//
				license, //
				lic -> lic.getLicense().getPlan(), //
				LicensesPackage.eINSTANCE.getLicensePlan_Floating());
	}

	private <T extends EObject> void updatePlan(T license, Function<T, String> id, EStructuralFeature ref)
			throws IOException {
		LicensePlan plan = plan(license, id);
		if (domain.isPresent()) {
			augmentUndoable(license, ref, plan);
		} else {
			augmentPlain(license, ref, plan);
		}
		savePlanResource(plan);
	}

	private <T extends EObject> LicensePlan plan(T license, Function<T, String> id) {
		return (LicensePlan) licenses.getLicensePlan(id.apply(license));
	}

	@SuppressWarnings("unchecked")
	private <T extends EObject> void augmentPlain(T license, EStructuralFeature ref, LicensePlan plan) {
		((EList<T>) plan.eGet(ref)).add(license);
	}

	private <T extends EObject> void augmentUndoable(T license, EStructuralFeature ref, LicensePlan plan) {
		EditingDomain edit = domain.get().getEditingDomain();
		edit.getCommandStack().execute(AddCommand.create(edit, plan, ref, license));
	}

	private void savePlanResource(LicensePlan plan) throws IOException {
		// FIXME: define parameters
		plan.eResource().save(null);
	}

}
