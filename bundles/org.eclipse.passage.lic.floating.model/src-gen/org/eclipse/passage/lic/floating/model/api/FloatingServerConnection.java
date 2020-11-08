/**
 */
package org.eclipse.passage.lic.floating.model.api;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Server Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.FloatingServerConnection#getIp <em>Ip</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.FloatingServerConnection#getPort <em>Port</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.FloatingServerConnection#getAuthentication <em>Authentication</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getFloatingServerConnection()
 * @model
 * @generated
 */
public interface FloatingServerConnection extends EObject {
	/**
	 * Returns the value of the '<em><b>Ip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ip</em>' attribute.
	 * @see #setIp(String)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getFloatingServerConnection_Ip()
	 * @model required="true"
	 * @generated
	 */
	String getIp();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.floating.model.api.FloatingServerConnection#getIp <em>Ip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ip</em>' attribute.
	 * @see #getIp()
	 * @generated
	 */
	void setIp(String value);

	/**
	 * Returns the value of the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port</em>' attribute.
	 * @see #setPort(int)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getFloatingServerConnection_Port()
	 * @model required="true"
	 * @generated
	 */
	int getPort();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.floating.model.api.FloatingServerConnection#getPort <em>Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port</em>' attribute.
	 * @see #getPort()
	 * @generated
	 */
	void setPort(int value);

	/**
	 * Returns the value of the '<em><b>Authentication</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Authentication</em>' containment reference.
	 * @see #setAuthentication(EvaluationInstructions)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getFloatingServerConnection_Authentication()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	EvaluationInstructions getAuthentication();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.floating.model.api.FloatingServerConnection#getAuthentication <em>Authentication</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Authentication</em>' containment reference.
	 * @see #getAuthentication()
	 * @generated
	 */
	void setAuthentication(EvaluationInstructions value);

} // FloatingServerConnection
