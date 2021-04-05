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
package org.eclipse.passage.lic.shipments.model.meta;

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
 * @see org.eclipse.passage.lic.shipments.model.meta.ShipmentsFactory
 * @model kind="package"
 * @generated
 */
public interface ShipmentsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "shipments"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/passage/lic/shipments/0.1.0"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.passage.lic"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ShipmentsPackage eINSTANCE = org.eclipse.passage.lic.shipments.model.impl.ShipmentsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.shipments.model.impl.PersonalImpl <em>Personal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.shipments.model.impl.PersonalImpl
	 * @see org.eclipse.passage.lic.shipments.model.impl.ShipmentsPackageImpl#getPersonal()
	 * @generated
	 */
	int PERSONAL = 0;

	/**
	 * The feature id for the '<em><b>Licenses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONAL__LICENSES = 0;

	/**
	 * The number of structural features of the '<em>Personal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONAL_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Personal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONAL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.shipments.model.impl.FloatingImpl <em>Floating</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.shipments.model.impl.FloatingImpl
	 * @see org.eclipse.passage.lic.shipments.model.impl.ShipmentsPackageImpl#getFloating()
	 * @generated
	 */
	int FLOATING = 1;

	/**
	 * The feature id for the '<em><b>Licenses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING__LICENSES = 0;

	/**
	 * The number of structural features of the '<em>Floating</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Floating</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.shipments.model.impl.PersonalLicenseDescriptorImpl <em>Personal License Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.shipments.model.impl.PersonalLicenseDescriptorImpl
	 * @see org.eclipse.passage.lic.shipments.model.impl.ShipmentsPackageImpl#getPersonalLicenseDescriptor()
	 * @generated
	 */
	int PERSONAL_LICENSE_DESCRIPTOR = 2;

	/**
	 * The number of structural features of the '<em>Personal License Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Personal License Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.shipments.model.impl.PersonalLicenseImpl <em>Personal License</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.shipments.model.impl.PersonalLicenseImpl
	 * @see org.eclipse.passage.lic.shipments.model.impl.ShipmentsPackageImpl#getPersonalLicense()
	 * @generated
	 */
	int PERSONAL_LICENSE = 3;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE__USER = PERSONAL_LICENSE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>License</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE__LICENSE = PERSONAL_LICENSE_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Personal License</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_FEATURE_COUNT = PERSONAL_LICENSE_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Personal License</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSONAL_LICENSE_OPERATION_COUNT = PERSONAL_LICENSE_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.shipments.model.impl.FloatingLicenseDescriptorImpl <em>Floating License Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.shipments.model.impl.FloatingLicenseDescriptorImpl
	 * @see org.eclipse.passage.lic.shipments.model.impl.ShipmentsPackageImpl#getFloatingLicenseDescriptor()
	 * @generated
	 */
	int FLOATING_LICENSE_DESCRIPTOR = 4;

	/**
	 * The number of structural features of the '<em>Floating License Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Floating License Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_DESCRIPTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.shipments.model.impl.FloatingLicenseImpl <em>Floating License</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.shipments.model.impl.FloatingLicenseImpl
	 * @see org.eclipse.passage.lic.shipments.model.impl.ShipmentsPackageImpl#getFloatingLicense()
	 * @generated
	 */
	int FLOATING_LICENSE = 5;

	/**
	 * The feature id for the '<em><b>Company</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE__COMPANY = FLOATING_LICENSE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>License</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE__LICENSE = FLOATING_LICENSE_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Floating License</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_FEATURE_COUNT = FLOATING_LICENSE_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Floating License</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_OPERATION_COUNT = FLOATING_LICENSE_DESCRIPTOR_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.shipments.model.api.Personal <em>Personal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Personal</em>'.
	 * @see org.eclipse.passage.lic.shipments.model.api.Personal
	 * @generated
	 */
	EClass getPersonal();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.shipments.model.api.Personal#getLicenses <em>Licenses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Licenses</em>'.
	 * @see org.eclipse.passage.lic.shipments.model.api.Personal#getLicenses()
	 * @see #getPersonal()
	 * @generated
	 */
	EReference getPersonal_Licenses();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.shipments.model.api.Floating <em>Floating</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Floating</em>'.
	 * @see org.eclipse.passage.lic.shipments.model.api.Floating
	 * @generated
	 */
	EClass getFloating();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.passage.lic.shipments.model.api.Floating#getLicenses <em>Licenses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Licenses</em>'.
	 * @see org.eclipse.passage.lic.shipments.model.api.Floating#getLicenses()
	 * @see #getFloating()
	 * @generated
	 */
	EReference getFloating_Licenses();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.shipments.PersonalLicenseDescriptor <em>Personal License Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Personal License Descriptor</em>'.
	 * @see org.eclipse.passage.lic.shipments.PersonalLicenseDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.shipments.PersonalLicenseDescriptor"
	 * @generated
	 */
	EClass getPersonalLicenseDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.shipments.model.api.PersonalLicense <em>Personal License</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Personal License</em>'.
	 * @see org.eclipse.passage.lic.shipments.model.api.PersonalLicense
	 * @generated
	 */
	EClass getPersonalLicense();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.shipments.model.api.PersonalLicense#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see org.eclipse.passage.lic.shipments.model.api.PersonalLicense#getUser()
	 * @see #getPersonalLicense()
	 * @generated
	 */
	EAttribute getPersonalLicense_User();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.shipments.model.api.PersonalLicense#getLicense <em>License</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>License</em>'.
	 * @see org.eclipse.passage.lic.shipments.model.api.PersonalLicense#getLicense()
	 * @see #getPersonalLicense()
	 * @generated
	 */
	EAttribute getPersonalLicense_License();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.shipments.FloatingLicenseDescriptor <em>Floating License Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Floating License Descriptor</em>'.
	 * @see org.eclipse.passage.lic.shipments.FloatingLicenseDescriptor
	 * @model instanceClass="org.eclipse.passage.lic.shipments.FloatingLicenseDescriptor"
	 * @generated
	 */
	EClass getFloatingLicenseDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.shipments.model.api.FloatingLicense <em>Floating License</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Floating License</em>'.
	 * @see org.eclipse.passage.lic.shipments.model.api.FloatingLicense
	 * @generated
	 */
	EClass getFloatingLicense();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.shipments.model.api.FloatingLicense#getCompany <em>Company</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Company</em>'.
	 * @see org.eclipse.passage.lic.shipments.model.api.FloatingLicense#getCompany()
	 * @see #getFloatingLicense()
	 * @generated
	 */
	EAttribute getFloatingLicense_Company();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.shipments.model.api.FloatingLicense#getLicense <em>License</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>License</em>'.
	 * @see org.eclipse.passage.lic.shipments.model.api.FloatingLicense#getLicense()
	 * @see #getFloatingLicense()
	 * @generated
	 */
	EAttribute getFloatingLicense_License();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ShipmentsFactory getShipmentsFactory();

} //ShipmentsPackage
