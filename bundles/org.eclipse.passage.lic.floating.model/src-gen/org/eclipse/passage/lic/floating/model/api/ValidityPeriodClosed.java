/**
 */
package org.eclipse.passage.lic.floating.model.api;

import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Validity Period Closed</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.ValidityPeriodClosed#getFrom <em>From</em>}</li>
 *   <li>{@link org.eclipse.passage.lic.floating.model.api.ValidityPeriodClosed#getUntil <em>Until</em>}</li>
 * </ul>
 *
 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getValidityPeriodClosed()
 * @model
 * @generated
 */
public interface ValidityPeriodClosed extends ValidityPeriod {
	/**
	 * Returns the value of the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' attribute.
	 * @see #setFrom(Date)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getValidityPeriodClosed_From()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	Date getFrom();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.floating.model.api.ValidityPeriodClosed#getFrom <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' attribute.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(Date value);

	/**
	 * Returns the value of the '<em><b>Until</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Until</em>' attribute.
	 * @see #setUntil(Date)
	 * @see org.eclipse.passage.lic.floating.model.meta.FloatingPackage#getValidityPeriodClosed_Until()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	Date getUntil();

	/**
	 * Sets the value of the '{@link org.eclipse.passage.lic.floating.model.api.ValidityPeriodClosed#getUntil <em>Until</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Until</em>' attribute.
	 * @see #getUntil()
	 * @generated
	 */
	void setUntil(Date value);

} // ValidityPeriodClosed
