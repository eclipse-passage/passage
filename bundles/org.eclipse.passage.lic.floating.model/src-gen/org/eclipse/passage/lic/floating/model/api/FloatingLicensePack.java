/**
 */
package org.eclipse.passage.lic.floating.model.api;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>License Pack</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.FloatingLicensePack#getLicense <em>License</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.FloatingLicensePack#getHost <em>Host</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.FloatingLicensePack#getUsers <em>Users</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.FloatingLicensePack#getFeatures <em>Features</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getFloatingLicensePack()
 * @model
 * @generated
 */
public interface FloatingLicensePack extends EObject {
	/**
	 * Returns the value of the '<em><b>License</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>License</em>' reference.
	 * @see #setLicense(LicenseRequisites)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getFloatingLicensePack_License()
	 * @model required="true"
	 * @generated
	 */
	LicenseRequisites getLicense();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.floating.model.api.FloatingLicensePack#getLicense <em>License</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>License</em>' reference.
	 * @see #getLicense()
	 * @generated
	 */
	void setLicense(LicenseRequisites value);

	/**
	 * Returns the value of the '<em><b>Host</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Host</em>' reference.
	 * @see #setHost(FloatingServer)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getFloatingLicensePack_Host()
	 * @model required="true"
	 * @generated
	 */
	FloatingServer getHost();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.floating.model.api.FloatingLicensePack#getHost <em>Host</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Host</em>' reference.
	 * @see #getHost()
	 * @generated
	 */
	void setHost(FloatingServer value);

	/**
	 * Returns the value of the '<em><b>Users</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.passage.lic.floating.model.api.UserGrant}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Users</em>' reference list.
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getFloatingLicensePack_Users()
	 * @model required="true"
	 * @generated
	 */
	EList<UserGrant> getUsers();

	/**
	 * Returns the value of the '<em><b>Features</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.passage.lic.floating.model.api.FeatureGrant}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getPack <em>Pack</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Features</em>' reference list.
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getFloatingLicensePack_Features()
	 * @see org.eclipse.passage.lic.floating.model.api.FeatureGrant#getPack
	 * @model opposite="pack" required="true"
	 * @generated
	 */
	EList<FeatureGrant> getFeatures();

} // FloatingLicensePack
