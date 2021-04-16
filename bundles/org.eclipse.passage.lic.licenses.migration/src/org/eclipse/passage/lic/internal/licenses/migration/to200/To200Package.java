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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;

public final class To200Package extends EPackageImpl {

	private final To200Factory factory = new To200Factory();

	public To200Package(String nsUri) {
		setNsURI(nsUri);
		setEFactoryInstance(factory);
		factory.setEPackage(this);
	}

	public To200Factory getDelegatingEFactory() {
		return factory;
	}

	@Override
	public void eSetProxyURI(URI uri) {
		// TODO Auto-generated method stub
		super.eSetProxyURI(uri);
	}

	@Override
	public EList<EClassifier> getEClassifiers() {
		// TODO Auto-generated method stub
		return super.getEClassifiers();
	}

	@Override
	public EClassifier getEClassifier(String name) {
		// TODO Auto-generated method stub
		return super.getEClassifier(name);
	}

	@Override
	public EClassifier getEClassifierGen(String name) {
		// TODO Auto-generated method stub
		return super.getEClassifierGen(name);
	}

	@Override
	public void eSet(int featureID, Object newValue) {
		// TODO Auto-generated method stub
		super.eSet(featureID, newValue);
	}

	@Override
	public boolean eIsSet(int featureID) {
		// TODO Auto-generated method stub
		return super.eIsSet(featureID);
	}

	@Override
	protected Resource createResource(String uri) {
		// TODO Auto-generated method stub
		return super.createResource(uri);
	}

	@Override
	protected EClass createEClass(int id) {
		// TODO Auto-generated method stub
		return super.createEClass(id);
	}

	@Override
	protected EEnum createEEnum(int id) {
		// TODO Auto-generated method stub
		return super.createEEnum(id);
	}

	@Override
	protected void createEAttribute(EClass owner, int id) {
		// TODO Auto-generated method stub
		super.createEAttribute(owner, id);
	}

	@Override
	protected void createEReference(EClass owner, int id) {
		// TODO Auto-generated method stub
		super.createEReference(owner, id);
	}

	@Override
	protected EClass initEClass(EClass c, Class<?> instanceClass, String name, boolean isAbstract,
			boolean isInterface) {
		// TODO Auto-generated method stub
		return super.initEClass(c, instanceClass, name, isAbstract, isInterface);
	}

	@Override
	protected EClass initEClass(EClass c, Class<?> instanceClass, String name, boolean isAbstract, boolean isInterface,
			boolean isGenerated) {
		// TODO Auto-generated method stub
		return super.initEClass(c, instanceClass, name, isAbstract, isInterface, isGenerated);
	}

	@Override
	protected EClass initEClass(EClass c, Class<?> instanceClass, String name, boolean isAbstract, boolean isInterface,
			boolean isGenerated, String instanceTypeName) {
		// TODO Auto-generated method stub
		return super.initEClass(c, instanceClass, name, isAbstract, isInterface, isGenerated, instanceTypeName);
	}

	@Override
	protected EAttribute initEAttribute(EAttribute a, EClassifier type, String name, String defaultValue,
			int lowerBound, int upperBound, boolean isTransient, boolean isVolatile, boolean isChangeable,
			boolean isUnsettable) {
		// TODO Auto-generated method stub
		return super.initEAttribute(a, type, name, defaultValue, lowerBound, upperBound, isTransient, isVolatile,
				isChangeable, isUnsettable);
	}

	@Override
	protected EAttribute initEAttribute(EAttribute a, EClassifier type, String name, String defaultValue,
			int lowerBound, int upperBound, boolean isTransient, boolean isVolatile, boolean isChangeable,
			boolean isUnsettable, boolean isID) {
		// TODO Auto-generated method stub
		return super.initEAttribute(a, type, name, defaultValue, lowerBound, upperBound, isTransient, isVolatile,
				isChangeable, isUnsettable, isID);
	}

	@Override
	protected EAttribute initEAttribute(EAttribute a, EClassifier type, String name, String defaultValue,
			int lowerBound, int upperBound, boolean isTransient, boolean isVolatile, boolean isChangeable,
			boolean isUnsettable, boolean isID, boolean isUnique) {
		// TODO Auto-generated method stub
		return super.initEAttribute(a, type, name, defaultValue, lowerBound, upperBound, isTransient, isVolatile,
				isChangeable, isUnsettable, isID, isUnique);
	}

	@Override
	protected EAttribute initEAttribute(EAttribute a, EClassifier type, String name, String defaultValue,
			int lowerBound, int upperBound, boolean isTransient, boolean isVolatile, boolean isChangeable,
			boolean isUnsettable, boolean isID, boolean isUnique, boolean isDerived) {
		// TODO Auto-generated method stub
		return super.initEAttribute(a, type, name, defaultValue, lowerBound, upperBound, isTransient, isVolatile,
				isChangeable, isUnsettable, isID, isUnique, isDerived);
	}

	@Override
	protected EAttribute initEAttribute(EAttribute a, EClassifier type, String name, String defaultValue,
			int lowerBound, int upperBound, boolean isTransient, boolean isVolatile, boolean isChangeable,
			boolean isUnsettable, boolean isID, boolean isUnique, boolean isDerived, boolean isOrdered) {
		// TODO Auto-generated method stub
		return super.initEAttribute(a, type, name, defaultValue, lowerBound, upperBound, isTransient, isVolatile,
				isChangeable, isUnsettable, isID, isUnique, isDerived, isOrdered);
	}

	@Override
	protected EAttribute initEAttribute(EAttribute a, EClassifier type, String name, String defaultValue,
			int lowerBound, int upperBound, Class<?> containerClass, boolean isTransient, boolean isVolatile,
			boolean isChangeable, boolean isUnsettable, boolean isID, boolean isUnique, boolean isDerived,
			boolean isOrdered) {
		// TODO Auto-generated method stub
		return super.initEAttribute(a, type, name, defaultValue, lowerBound, upperBound, containerClass, isTransient,
				isVolatile, isChangeable, isUnsettable, isID, isUnique, isDerived, isOrdered);
	}

	@Override
	protected EAttribute initEAttribute(EAttribute a, EGenericType type, String name, String defaultValue,
			int lowerBound, int upperBound, Class<?> containerClass, boolean isTransient, boolean isVolatile,
			boolean isChangeable, boolean isUnsettable, boolean isID, boolean isUnique, boolean isDerived,
			boolean isOrdered) {
		// TODO Auto-generated method stub
		return super.initEAttribute(a, type, name, defaultValue, lowerBound, upperBound, containerClass, isTransient,
				isVolatile, isChangeable, isUnsettable, isID, isUnique, isDerived, isOrdered);
	}

	@Override
	protected EReference initEReference(EReference r, EClassifier type, EReference otherEnd, String name,
			String defaultValue, int lowerBound, int upperBound, boolean isTransient, boolean isVolatile,
			boolean isChangeable, boolean isContainment, boolean isResolveProxies) {
		// TODO Auto-generated method stub
		return super.initEReference(r, type, otherEnd, name, defaultValue, lowerBound, upperBound, isTransient,
				isVolatile, isChangeable, isContainment, isResolveProxies);
	}

	@Override
	protected EReference initEReference(EReference r, EClassifier type, EReference otherEnd, String name,
			String defaultValue, int lowerBound, int upperBound, boolean isTransient, boolean isVolatile,
			boolean isChangeable, boolean isContainment, boolean isResolveProxies, boolean isUnsettable) {
		// TODO Auto-generated method stub
		return super.initEReference(r, type, otherEnd, name, defaultValue, lowerBound, upperBound, isTransient,
				isVolatile, isChangeable, isContainment, isResolveProxies, isUnsettable);
	}

	@Override
	protected EReference initEReference(EReference r, EClassifier type, EReference otherEnd, String name,
			String defaultValue, int lowerBound, int upperBound, boolean isTransient, boolean isVolatile,
			boolean isChangeable, boolean isContainment, boolean isResolveProxies, boolean isUnsettable,
			boolean isUnique) {
		// TODO Auto-generated method stub
		return super.initEReference(r, type, otherEnd, name, defaultValue, lowerBound, upperBound, isTransient,
				isVolatile, isChangeable, isContainment, isResolveProxies, isUnsettable, isUnique);
	}

	@Override
	protected EReference initEReference(EReference r, EClassifier type, EReference otherEnd, String name,
			String defaultValue, int lowerBound, int upperBound, boolean isTransient, boolean isVolatile,
			boolean isChangeable, boolean isContainment, boolean isResolveProxies, boolean isUnsettable,
			boolean isUnique, boolean isDerived) {
		// TODO Auto-generated method stub
		return super.initEReference(r, type, otherEnd, name, defaultValue, lowerBound, upperBound, isTransient,
				isVolatile, isChangeable, isContainment, isResolveProxies, isUnsettable, isUnique, isDerived);
	}

	@Override
	protected EReference initEReference(EReference r, EClassifier type, EReference otherEnd, String name,
			String defaultValue, int lowerBound, int upperBound, Class<?> containerClass, boolean isTransient,
			boolean isVolatile, boolean isChangeable, boolean isContainment, boolean isResolveProxies,
			boolean isUnsettable, boolean isUnique, boolean isDerived, boolean isOrdered) {
		// TODO Auto-generated method stub
		return super.initEReference(r, type, otherEnd, name, defaultValue, lowerBound, upperBound, containerClass,
				isTransient, isVolatile, isChangeable, isContainment, isResolveProxies, isUnsettable, isUnique,
				isDerived, isOrdered);
	}

	@Override
	protected EReference initEReference(EReference r, EGenericType type, EReference otherEnd, String name,
			String defaultValue, int lowerBound, int upperBound, Class<?> containerClass, boolean isTransient,
			boolean isVolatile, boolean isChangeable, boolean isContainment, boolean isResolveProxies,
			boolean isUnsettable, boolean isUnique, boolean isDerived, boolean isOrdered) {
		// TODO Auto-generated method stub
		return super.initEReference(r, type, otherEnd, name, defaultValue, lowerBound, upperBound, containerClass,
				isTransient, isVolatile, isChangeable, isContainment, isResolveProxies, isUnsettable, isUnique,
				isDerived, isOrdered);
	}

	@Override
	protected void fixEClassifiers() {
		// TODO Auto-generated method stub
		super.fixEClassifiers();
	}

	@Override
	protected void fixInstanceClass(EClassifier eClassifier) {
		// TODO Auto-generated method stub
		super.fixInstanceClass(eClassifier);
	}

	@Override
	protected void fixEStructuralFeatures(EClass eClass) {
		// TODO Auto-generated method stub
		super.fixEStructuralFeatures(eClass);
	}

	@Override
	protected void fixEOperations(EClass eClass) {
		// TODO Auto-generated method stub
		super.fixEOperations(eClass);
	}

	@Override
	protected void fixEEnumLiterals(EEnum eEnum) {
		// TODO Auto-generated method stub
		super.fixEEnumLiterals(eEnum);
	}

}
