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
package org.eclipse.passage.lic.internal.licenses.migration.to200;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;

public class To200Factory implements EFactory {

	private EFactory delegate = LicensesFactory.eINSTANCE;

	public To200Factory() {
	}

	@Override
	public EList<EAnnotation> getEAnnotations() {
		return delegate.getEAnnotations();
	}

	@Override
	public EAnnotation getEAnnotation(String source) {
		return delegate.getEAnnotation(source);
	}

	@Override
	public EClass eClass() {
		return delegate.eClass();
	}

	@Override
	public Resource eResource() {
		return delegate.eResource();
	}

	@Override
	public EObject eContainer() {
		return delegate.eContainer();
	}

	@Override
	public EStructuralFeature eContainingFeature() {
		return delegate.eContainingFeature();
	}

	@Override
	public EReference eContainmentFeature() {
		return delegate.eContainmentFeature();
	}

	@Override
	public EList<EObject> eContents() {
		return delegate.eContents();
	}

	@Override
	public TreeIterator<EObject> eAllContents() {
		return delegate.eAllContents();
	}

	@Override
	public boolean eIsProxy() {
		return delegate.eIsProxy();
	}

	@Override
	public EList<EObject> eCrossReferences() {
		return delegate.eCrossReferences();
	}

	@Override
	public Object eGet(EStructuralFeature feature) {
		return delegate.eGet(feature);
	}

	@Override
	public Object eGet(EStructuralFeature feature, boolean resolve) {
		return delegate.eGet(feature, resolve);
	}

	@Override
	public void eSet(EStructuralFeature feature, Object newValue) {
		delegate.eSet(feature, newValue);
	}

	@Override
	public boolean eIsSet(EStructuralFeature feature) {
		return delegate.eIsSet(feature);
	}

	@Override
	public void eUnset(EStructuralFeature feature) {
		delegate.eUnset(feature);
	}

	@Override
	public Object eInvoke(EOperation operation, EList<?> arguments) throws InvocationTargetException {
		return delegate.eInvoke(operation, arguments);
	}

	@Override
	public EList<Adapter> eAdapters() {
		return delegate.eAdapters();
	}

	@Override
	public boolean eDeliver() {
		return delegate.eDeliver();
	}

	@Override
	public void eSetDeliver(boolean deliver) {
		delegate.eSetDeliver(deliver);
	}

	@Override
	public void eNotify(Notification notification) {
		delegate.eNotify(notification);
	}

	@Override
	public EPackage getEPackage() {
		return delegate.getEPackage();
	}

	@Override
	public void setEPackage(EPackage value) {
		delegate.setEPackage(value);
	}

	@Override
	public EObject create(EClass eClass) {
		return delegate.create(eClass);
	}

	@Override
	public Object createFromString(EDataType eDataType, String literalValue) {
		return delegate.createFromString(eDataType, literalValue);
	}

	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		return delegate.convertToString(eDataType, instanceValue);
	}

}
