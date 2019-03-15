/**
 */
package org.eclipse.passage.lic.users.model.api;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.passage.lic.users.UserDescriptor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.User#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.User#getEmail <em>Email</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.User#getFullName <em>Full Name</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.User#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.users.model.api.User#getUserOrigin <em>User Origin</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUser()
 * @model superTypes="org.eclipse.passage.lic.users.model.api.UserDescriptor"
 * @generated
 */
public interface User extends EObject, UserDescriptor {
	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see #setIdentifier(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUser_Identifier()
	 * @model
	 * @generated
	 */
	@Override
	String getIdentifier();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.User#getIdentifier <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identifier</em>' attribute.
	 * @see #getIdentifier()
	 * @generated
	 */
	void setIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Email</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Email</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Email</em>' attribute.
	 * @see #setEmail(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUser_Email()
	 * @model required="true"
	 * @generated
	 */
	@Override
	String getEmail();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.User#getEmail <em>Email</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Email</em>' attribute.
	 * @see #getEmail()
	 * @generated
	 */
	void setEmail(String value);

	/**
	 * Returns the value of the '<em><b>Full Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Full Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Full Name</em>' attribute.
	 * @see #setFullName(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUser_FullName()
	 * @model
	 * @generated
	 */
	@Override
	String getFullName();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.User#getFullName <em>Full Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Full Name</em>' attribute.
	 * @see #getFullName()
	 * @generated
	 */
	void setFullName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUser_Description()
	 * @model
	 * @generated
	 */
	@Override
	String getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.User#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>User Origin</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.passage.lic.users.model.api.UserOrigin#getUsers <em>Users</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Origin</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Origin</em>' container reference.
	 * @see #setUserOrigin(UserOrigin)
	 * @see org.eclipse.passage.lic.users.model.meta.UsersPackage#getUser_UserOrigin()
	 * @see org.eclipse.passage.lic.users.model.api.UserOrigin#getUsers
	 * @model opposite="users" required="true" transient="false"
	 * @generated
	 */
	@Override
	UserOrigin getUserOrigin();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.users.model.api.User#getUserOrigin <em>User Origin</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Origin</em>' container reference.
	 * @see #getUserOrigin()
	 * @generated
	 */
	void setUserOrigin(UserOrigin value);

} // User
