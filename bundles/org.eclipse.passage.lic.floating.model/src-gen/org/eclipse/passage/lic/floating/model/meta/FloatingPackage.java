/**
 */
package org.eclipse.passage.lic.floating.model.meta;

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
 * @see org.eclipse.passage.lic.floating.model.meta.FloatingFactory
 * @model kind="package"
 * @generated
 */
public interface FloatingPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "floating"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/passage/lic/floating/0.1.0"; //$NON-NLS-1$

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
	FloatingPackage eINSTANCE = org.eclipse.passage.lic.floating.model.impl.FloatingPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.floating.model.impl.FloatingLicensePackImpl <em>License Pack</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.floating.model.impl.FloatingLicensePackImpl
	 * @see org.eclipse.passage.lic.floating.model.impl.FloatingPackageImpl#getFloatingLicensePack()
	 * @generated
	 */
	int FLOATING_LICENSE_PACK = 0;

	/**
	 * The feature id for the '<em><b>License</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_PACK__LICENSE = 0;

	/**
	 * The feature id for the '<em><b>Host</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_PACK__HOST = 1;

	/**
	 * The feature id for the '<em><b>Users</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_PACK__USERS = 2;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_PACK__FEATURES = 3;

	/**
	 * The number of structural features of the '<em>License Pack</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_PACK_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>License Pack</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_LICENSE_PACK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.floating.model.impl.LicenseRequisitesImpl <em>License Requisites</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.floating.model.impl.LicenseRequisitesImpl
	 * @see org.eclipse.passage.lic.floating.model.impl.FloatingPackageImpl#getLicenseRequisites()
	 * @generated
	 */
	int LICENSE_REQUISITES = 1;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES__IDENTIFIER = 0;

	/**
	 * The feature id for the '<em><b>Issue Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES__ISSUE_DATE = 1;

	/**
	 * The feature id for the '<em><b>Company</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES__COMPANY = 2;

	/**
	 * The feature id for the '<em><b>Plan</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES__PLAN = 3;

	/**
	 * The feature id for the '<em><b>Product</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES__PRODUCT = 4;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES__VALID = 5;

	/**
	 * The number of structural features of the '<em>License Requisites</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>License Requisites</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LICENSE_REQUISITES_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.floating.model.impl.ProductRefImpl <em>Product Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.floating.model.impl.ProductRefImpl
	 * @see org.eclipse.passage.lic.floating.model.impl.FloatingPackageImpl#getProductRef()
	 * @generated
	 */
	int PRODUCT_REF = 2;

	/**
	 * The feature id for the '<em><b>Product</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_REF__PRODUCT = 0;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_REF__VERSION = 1;

	/**
	 * The number of structural features of the '<em>Product Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_REF_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Product Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_REF_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.floating.model.impl.FloatingServerImpl <em>Server</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.floating.model.impl.FloatingServerImpl
	 * @see org.eclipse.passage.lic.floating.model.impl.FloatingPackageImpl#getFloatingServer()
	 * @generated
	 */
	int FLOATING_SERVER = 3;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER__IDENTIFIER = 0;

	/**
	 * The feature id for the '<em><b>Authentication</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER__AUTHENTICATION = 1;

	/**
	 * The number of structural features of the '<em>Server</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Server</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_SERVER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.floating.model.impl.UserGrantImpl <em>User Grant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.floating.model.impl.UserGrantImpl
	 * @see org.eclipse.passage.lic.floating.model.impl.FloatingPackageImpl#getUserGrant()
	 * @generated
	 */
	int USER_GRANT = 4;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_GRANT__USER = 0;

	/**
	 * The feature id for the '<em><b>Authentication</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_GRANT__AUTHENTICATION = 1;

	/**
	 * The number of structural features of the '<em>User Grant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_GRANT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>User Grant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_GRANT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.floating.model.impl.FeatureGrantImpl <em>Feature Grant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.floating.model.impl.FeatureGrantImpl
	 * @see org.eclipse.passage.lic.floating.model.impl.FloatingPackageImpl#getFeatureGrant()
	 * @generated
	 */
	int FEATURE_GRANT = 5;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT__IDENTIFIER = 0;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT__FEATURE = 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT__VERSION = 2;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT__VALID = 3;

	/**
	 * The feature id for the '<em><b>Capacity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT__CAPACITY = 4;

	/**
	 * The feature id for the '<em><b>Pack</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT__PACK = 5;

	/**
	 * The number of structural features of the '<em>Feature Grant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Feature Grant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_GRANT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.floating.model.api.ValidityPeriod <em>Validity Period</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.floating.model.api.ValidityPeriod
	 * @see org.eclipse.passage.lic.floating.model.impl.FloatingPackageImpl#getValidityPeriod()
	 * @generated
	 */
	int VALIDITY_PERIOD = 6;

	/**
	 * The number of structural features of the '<em>Validity Period</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Validity Period</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.floating.model.impl.ValidityPeriodClosedImpl <em>Validity Period Closed</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.floating.model.impl.ValidityPeriodClosedImpl
	 * @see org.eclipse.passage.lic.floating.model.impl.FloatingPackageImpl#getValidityPeriodClosed()
	 * @generated
	 */
	int VALIDITY_PERIOD_CLOSED = 7;

	/**
	 * The feature id for the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_CLOSED__FROM = VALIDITY_PERIOD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Until</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_CLOSED__UNTIL = VALIDITY_PERIOD_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Validity Period Closed</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_CLOSED_FEATURE_COUNT = VALIDITY_PERIOD_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Validity Period Closed</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDITY_PERIOD_CLOSED_OPERATION_COUNT = VALIDITY_PERIOD_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.floating.model.impl.EvaluationInstructionsImpl <em>Evaluation Instructions</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.floating.model.impl.EvaluationInstructionsImpl
	 * @see org.eclipse.passage.lic.floating.model.impl.FloatingPackageImpl#getEvaluationInstructions()
	 * @generated
	 */
	int EVALUATION_INSTRUCTIONS = 8;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_INSTRUCTIONS__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_INSTRUCTIONS__EXPRESSION = 1;

	/**
	 * The number of structural features of the '<em>Evaluation Instructions</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_INSTRUCTIONS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Evaluation Instructions</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_INSTRUCTIONS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.passage.lic.floating.model.impl.VersionMatchImpl <em>Version Match</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.passage.lic.floating.model.impl.VersionMatchImpl
	 * @see org.eclipse.passage.lic.floating.model.impl.FloatingPackageImpl#getVersionMatch()
	 * @generated
	 */
	int VERSION_MATCH = 9;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_MATCH__VERSION = 0;

	/**
	 * The feature id for the '<em><b>Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_MATCH__RULE = 1;

	/**
	 * The number of structural features of the '<em>Version Match</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_MATCH_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Version Match</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_MATCH_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.floating.model.api.FloatingLicensePack <em>License Pack</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License Pack</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.FloatingLicensePack
	 * @generated
	 */
	EClass getFloatingLicensePack();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.passage.lic.floating.model.api.FloatingLicensePack#getLicense <em>License</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>License</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.FloatingLicensePack#getLicense()
	 * @see #getFloatingLicensePack()
	 * @generated
	 */
	EReference getFloatingLicensePack_License();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.passage.lic.floating.model.api.FloatingLicensePack#getHost <em>Host</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Host</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.FloatingLicensePack#getHost()
	 * @see #getFloatingLicensePack()
	 * @generated
	 */
	EReference getFloatingLicensePack_Host();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.passage.lic.floating.model.api.FloatingLicensePack#getUsers <em>Users</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Users</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.FloatingLicensePack#getUsers()
	 * @see #getFloatingLicensePack()
	 * @generated
	 */
	EReference getFloatingLicensePack_Users();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.passage.lic.floating.model.api.FloatingLicensePack#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Features</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.FloatingLicensePack#getFeatures()
	 * @see #getFloatingLicensePack()
	 * @generated
	 */
	EReference getFloatingLicensePack_Features();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites <em>License Requisites</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>License Requisites</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.LicenseRequisites
	 * @generated
	 */
	EClass getLicenseRequisites();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getIdentifier()
	 * @see #getLicenseRequisites()
	 * @generated
	 */
	EAttribute getLicenseRequisites_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getIssueDate <em>Issue Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Issue Date</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getIssueDate()
	 * @see #getLicenseRequisites()
	 * @generated
	 */
	EAttribute getLicenseRequisites_IssueDate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getCompany <em>Company</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Company</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getCompany()
	 * @see #getLicenseRequisites()
	 * @generated
	 */
	EAttribute getLicenseRequisites_Company();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getPlan <em>Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Plan</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getPlan()
	 * @see #getLicenseRequisites()
	 * @generated
	 */
	EAttribute getLicenseRequisites_Plan();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getProduct <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Product</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getProduct()
	 * @see #getLicenseRequisites()
	 * @generated
	 */
	EReference getLicenseRequisites_Product();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getValid <em>Valid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Valid</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.LicenseRequisites#getValid()
	 * @see #getLicenseRequisites()
	 * @generated
	 */
	EReference getLicenseRequisites_Valid();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.floating.model.api.ProductRef <em>Product Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Ref</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.ProductRef
	 * @generated
	 */
	EClass getProductRef();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.floating.model.api.ProductRef#getProduct <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Product</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.ProductRef#getProduct()
	 * @see #getProductRef()
	 * @generated
	 */
	EAttribute getProductRef_Product();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.floating.model.api.ProductRef#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.ProductRef#getVersion()
	 * @see #getProductRef()
	 * @generated
	 */
	EAttribute getProductRef_Version();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.floating.model.api.FloatingServer <em>Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Server</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.FloatingServer
	 * @generated
	 */
	EClass getFloatingServer();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.floating.model.api.FloatingServer#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.FloatingServer#getIdentifier()
	 * @see #getFloatingServer()
	 * @generated
	 */
	EAttribute getFloatingServer_Identifier();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.passage.lic.floating.model.api.FloatingServer#getAuthentication <em>Authentication</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Authentication</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.FloatingServer#getAuthentication()
	 * @see #getFloatingServer()
	 * @generated
	 */
	EReference getFloatingServer_Authentication();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.floating.model.api.UserGrant <em>User Grant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Grant</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.UserGrant
	 * @generated
	 */
	EClass getUserGrant();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.floating.model.api.UserGrant#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.UserGrant#getUser()
	 * @see #getUserGrant()
	 * @generated
	 */
	EAttribute getUserGrant_User();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.passage.lic.floating.model.api.UserGrant#getAuthentication <em>Authentication</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Authentication</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.UserGrant#getAuthentication()
	 * @see #getUserGrant()
	 * @generated
	 */
	EReference getUserGrant_Authentication();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant <em>Feature Grant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Grant</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.FeatureGrant
	 * @generated
	 */
	EClass getFeatureGrant();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.FeatureGrant#getIdentifier()
	 * @see #getFeatureGrant()
	 * @generated
	 */
	EAttribute getFeatureGrant_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Feature</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.FeatureGrant#getFeature()
	 * @see #getFeatureGrant()
	 * @generated
	 */
	EAttribute getFeatureGrant_Feature();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Version</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.FeatureGrant#getVersion()
	 * @see #getFeatureGrant()
	 * @generated
	 */
	EReference getFeatureGrant_Version();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getValid <em>Valid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Valid</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.FeatureGrant#getValid()
	 * @see #getFeatureGrant()
	 * @generated
	 */
	EReference getFeatureGrant_Valid();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getCapacity <em>Capacity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Capacity</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.FeatureGrant#getCapacity()
	 * @see #getFeatureGrant()
	 * @generated
	 */
	EAttribute getFeatureGrant_Capacity();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.passage.lic.floating.model.api.FeatureGrant#getPack <em>Pack</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Pack</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.FeatureGrant#getPack()
	 * @see #getFeatureGrant()
	 * @generated
	 */
	EReference getFeatureGrant_Pack();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.floating.model.api.ValidityPeriod <em>Validity Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Validity Period</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.ValidityPeriod
	 * @generated
	 */
	EClass getValidityPeriod();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.floating.model.api.ValidityPeriodClosed <em>Validity Period Closed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Validity Period Closed</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.ValidityPeriodClosed
	 * @generated
	 */
	EClass getValidityPeriodClosed();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.floating.model.api.ValidityPeriodClosed#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>From</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.ValidityPeriodClosed#getFrom()
	 * @see #getValidityPeriodClosed()
	 * @generated
	 */
	EAttribute getValidityPeriodClosed_From();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.floating.model.api.ValidityPeriodClosed#getUntil <em>Until</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Until</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.ValidityPeriodClosed#getUntil()
	 * @see #getValidityPeriodClosed()
	 * @generated
	 */
	EAttribute getValidityPeriodClosed_Until();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.floating.model.api.EvaluationInstructions <em>Evaluation Instructions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Evaluation Instructions</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.EvaluationInstructions
	 * @generated
	 */
	EClass getEvaluationInstructions();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.floating.model.api.EvaluationInstructions#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.EvaluationInstructions#getType()
	 * @see #getEvaluationInstructions()
	 * @generated
	 */
	EAttribute getEvaluationInstructions_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.floating.model.api.EvaluationInstructions#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expression</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.EvaluationInstructions#getExpression()
	 * @see #getEvaluationInstructions()
	 * @generated
	 */
	EAttribute getEvaluationInstructions_Expression();

	/**
	 * Returns the meta object for class '{@link org.eclipse.passage.lic.floating.model.api.VersionMatch <em>Version Match</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Version Match</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.VersionMatch
	 * @generated
	 */
	EClass getVersionMatch();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.floating.model.api.VersionMatch#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.VersionMatch#getVersion()
	 * @see #getVersionMatch()
	 * @generated
	 */
	EAttribute getVersionMatch_Version();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.passage.lic.floating.model.api.VersionMatch#getRule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rule</em>'.
	 * @see org.eclipse.passage.lic.floating.model.api.VersionMatch#getRule()
	 * @see #getVersionMatch()
	 * @generated
	 */
	EAttribute getVersionMatch_Rule();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	FloatingFactory getFloatingFactory();

} //FloatingPackage
