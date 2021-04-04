/**
 */
package org.eclipse.passage.lic.shipments.model.api;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Floating License</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.shipments.model.api.FloatingLicense#getCompany <em>Company</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.shipments.model.api.FloatingLicense#getLicense <em>License</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.shipments.model.meta.ShipmentsPackage#getFloatingLicense()
 * @model
 * @generated
 */
public interface FloatingLicense extends EObject {
	/**
	 * Returns the value of the '<em><b>Company</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Company</em>' attribute.
	 * @see #setCompany(String)
	 * @see org.eclipse.passage.lic.shipments.model.meta.ShipmentsPackage#getFloatingLicense_Company()
	 * @model required="true"
	 * @generated
	 */
	String getCompany();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.shipments.model.api.FloatingLicense#getCompany <em>Company</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Company</em>' attribute.
	 * @see #getCompany()
	 * @generated
	 */
	void setCompany(String value);

	/**
	 * Returns the value of the '<em><b>License</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>License</em>' attribute.
	 * @see #setLicense(String)
	 * @see org.eclipse.passage.lic.shipments.model.meta.ShipmentsPackage#getFloatingLicense_License()
	 * @model required="true"
	 * @generated
	 */
	String getLicense();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.shipments.model.api.FloatingLicense#getLicense <em>License</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>License</em>' attribute.
	 * @see #getLicense()
	 * @generated
	 */
	void setLicense(String value);

} // FloatingLicense
