/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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
package org.eclipse.passage.lic.features.model.meta;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.passage.lic.features.model.meta.FeaturesFactory
 * @model kind="package"
 * @generated
 */
public interface FeaturesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "features"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/passage/lic/features/2.0.0"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "features"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FeaturesPackage eINSTANCE = org.eclipse.passage.lic.features.model.impl.FeaturesPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.features.FeatureSetDescriptor <em>Feature Set Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.features.FeatureSetDescriptor
	 * @see org.eclipse.passage.lic.features.model.impl.FeaturesPackageImpl#getFeatureSetDescriptor()
	 * @generated
	 */
	int FEATURE_SET_DESCRIPTOR = 0;

	/**
	 * The number of structural features of the '<em>Feature Set Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_SET_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Feature Set Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_SET_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.features.FeatureDescriptor <em>Feature Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.features.FeatureDescriptor
	 * @see org.eclipse.passage.lic.features.model.impl.FeaturesPackageImpl#getFeatureDescriptor()
	 * @generated
	 */
	int FEATURE_DESCRIPTOR = 1;

	/**
	 * The number of structural features of the '<em>Feature Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Feature Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.features.FeatureVersionDescriptor <em>Feature Version Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.features.FeatureVersionDescriptor
	 * @see org.eclipse.passage.lic.features.model.impl.FeaturesPackageImpl#getFeatureVersionDescriptor()
	 * @generated
	 */
	int FEATURE_VERSION_DESCRIPTOR = 2;

	/**
	 * The number of structural features of the '<em>Feature Version Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_VERSION_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Feature Version Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_VERSION_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.features.model.impl.FeatureSetImpl <em>Feature Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.features.model.impl.FeatureSetImpl
	 * @see org.eclipse.passage.lic.features.model.impl.FeaturesPackageImpl#getFeatureSet()
	 * @generated
	 */
	int FEATURE_SET = 3;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_SET__IDENTIFIER = FEATURE_SET_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_SET__NAME = FEATURE_SET_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_SET__DESCRIPTION = FEATURE_SET_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_SET__FEATURES = FEATURE_SET_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Feature Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_SET_FEATURE_COUNT = FEATURE_SET_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Feature Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_SET_OPERATION_COUNT = FEATURE_SET_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.features.model.impl.FeatureImpl <em>Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.features.model.impl.FeatureImpl
	 * @see org.eclipse.passage.lic.features.model.impl.FeaturesPackageImpl#getFeature()
	 * @generated
	 */
	int FEATURE = 4;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__IDENTIFIER = FEATURE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Provider</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__PROVIDER = FEATURE_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__NAME = FEATURE_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__DESCRIPTION = FEATURE_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Feature Set</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__FEATURE_SET = FEATURE_DESCRIPTOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Feature Versions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__FEATURE_VERSIONS = FEATURE_DESCRIPTOR_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FEATURE_COUNT = FEATURE_DESCRIPTOR_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_OPERATION_COUNT = FEATURE_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.features.model.impl.FeatureVersionImpl <em>Feature Version</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.features.model.impl.FeatureVersionImpl
	 * @see org.eclipse.passage.lic.features.model.impl.FeaturesPackageImpl#getFeatureVersion()
	 * @generated
	 */
	int FEATURE_VERSION = 5;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_VERSION__VERSION = FEATURE_VERSION_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_VERSION__FEATURE = FEATURE_VERSION_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>News</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_VERSION__NEWS = FEATURE_VERSION_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Feature Version</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_VERSION_FEATURE_COUNT = FEATURE_VERSION_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Feature Version</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_VERSION_OPERATION_COUNT = FEATURE_VERSION_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.features.FeatureSetDescriptor <em>Feature Set Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Set Descriptor</em>'.
	 * @see org.eclipse.passage.lic.features.FeatureSetDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.features.FeatureSetDescriptor"
	 * @generated
	 */
	EClass getFeatureSetDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.features.FeatureDescriptor <em>Feature Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Descriptor</em>'.
	 * @see org.eclipse.passage.lic.features.FeatureDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.features.FeatureDescriptor"
	 * @generated
	 */
	EClass getFeatureDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.features.FeatureVersionDescriptor <em>Feature Version Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Version Descriptor</em>'.
	 * @see org.eclipse.passage.lic.features.FeatureVersionDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.features.FeatureVersionDescriptor"
	 * @generated
	 */
	EClass getFeatureVersionDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.features.model.api.FeatureSet <em>Feature Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Set</em>'.
	 * @see org.eclipse.passage.lic.features.model.api.FeatureSet
	 * @generated
	 */
	EClass getFeatureSet();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.features.model.api.FeatureSet#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.features.model.api.FeatureSet#getIdentifier()
	 * @see #getFeatureSet()
	 * @generated
	 */
	EAttribute getFeatureSet_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.features.model.api.FeatureSet#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.passage.lic.features.model.api.FeatureSet#getName()
	 * @see #getFeatureSet()
	 * @generated
	 */
	EAttribute getFeatureSet_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.features.model.api.FeatureSet#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.passage.lic.features.model.api.FeatureSet#getDescription()
	 * @see #getFeatureSet()
	 * @generated
	 */
	EAttribute getFeatureSet_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.features.model.api.FeatureSet#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Features</em>'.
	 * @see org.eclipse.passage.lic.features.model.api.FeatureSet#getFeatures()
	 * @see #getFeatureSet()
	 * @generated
	 */
	EReference getFeatureSet_Features();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.features.model.api.Feature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature</em>'.
	 * @see org.eclipse.passage.lic.features.model.api.Feature
	 * @generated
	 */
	EClass getFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.features.model.api.Feature#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.features.model.api.Feature#getIdentifier()
	 * @see #getFeature()
	 * @generated
	 */
	EAttribute getFeature_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.features.model.api.Feature#getProvider <em>Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Provider</em>'.
	 * @see org.eclipse.passage.lic.features.model.api.Feature#getProvider()
	 * @see #getFeature()
	 * @generated
	 */
	EAttribute getFeature_Provider();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.features.model.api.Feature#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.passage.lic.features.model.api.Feature#getName()
	 * @see #getFeature()
	 * @generated
	 */
	EAttribute getFeature_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.features.model.api.Feature#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.passage.lic.features.model.api.Feature#getDescription()
	 * @see #getFeature()
	 * @generated
	 */
	EAttribute getFeature_Description();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.passage.lic.features.model.api.Feature#getFeatureSet <em>Feature Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Feature Set</em>'.
	 * @see org.eclipse.passage.lic.features.model.api.Feature#getFeatureSet()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_FeatureSet();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.features.model.api.Feature#getFeatureVersions <em>Feature Versions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Feature Versions</em>'.
	 * @see org.eclipse.passage.lic.features.model.api.Feature#getFeatureVersions()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_FeatureVersions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.features.model.api.FeatureVersion <em>Feature Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Version</em>'.
	 * @see org.eclipse.passage.lic.features.model.api.FeatureVersion
	 * @generated
	 */
	EClass getFeatureVersion();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.features.model.api.FeatureVersion#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.passage.lic.features.model.api.FeatureVersion#getVersion()
	 * @see #getFeatureVersion()
	 * @generated
	 */
	EAttribute getFeatureVersion_Version();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.passage.lic.features.model.api.FeatureVersion#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Feature</em>'.
	 * @see org.eclipse.passage.lic.features.model.api.FeatureVersion#getFeature()
	 * @see #getFeatureVersion()
	 * @generated
	 */
	EReference getFeatureVersion_Feature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.features.model.api.FeatureVersion#getNews <em>News</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>News</em>'.
	 * @see org.eclipse.passage.lic.features.model.api.FeatureVersion#getNews()
	 * @see #getFeatureVersion()
	 * @generated
	 */
	EAttribute getFeatureVersion_News();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	FeaturesFactory getFeaturesFactory();

} //FeaturesPackage
