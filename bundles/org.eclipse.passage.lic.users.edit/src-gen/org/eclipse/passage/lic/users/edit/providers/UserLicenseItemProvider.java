/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.users.edit.providers;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.passage.lic.users.edit.UsersEditPlugin;
import org.eclipse.passage.lic.users.model.api.UserLicense;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.passage.lic.users.model.api.UserLicense} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class UserLicenseItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserLicenseItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addPlanIdentifierPropertyDescriptor(object);
			addProductIdentifierPropertyDescriptor(object);
			addProductVersionPropertyDescriptor(object);
			addValidFromPropertyDescriptor(object);
			addValidUntilPropertyDescriptor(object);
			addConditionTypePropertyDescriptor(object);
			addConditionExpressionPropertyDescriptor(object);
			addPackIdentifierPropertyDescriptor(object);
			addIssueDatePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Plan Identifier feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPlanIdentifierPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_UserLicense_planIdentifier_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_UserLicense_planIdentifier_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_UserLicense_type"), //$NON-NLS-1$
						UsersPackage.eINSTANCE.getUserLicense_PlanIdentifier(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Product Identifier feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addProductIdentifierPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_UserLicense_productIdentifier_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_UserLicense_productIdentifier_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_UserLicense_type"), //$NON-NLS-1$
						UsersPackage.eINSTANCE.getUserLicense_ProductIdentifier(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Product Version feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addProductVersionPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_UserLicense_productVersion_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_UserLicense_productVersion_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_UserLicense_type"), //$NON-NLS-1$
						UsersPackage.eINSTANCE.getUserLicense_ProductVersion(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Valid From feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addValidFromPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_UserLicense_validFrom_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_UserLicense_validFrom_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_UserLicense_type"), //$NON-NLS-1$
						UsersPackage.eINSTANCE.getUserLicense_ValidFrom(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Valid Until feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addValidUntilPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_UserLicense_validUntil_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_UserLicense_validUntil_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_UserLicense_type"), //$NON-NLS-1$
						UsersPackage.eINSTANCE.getUserLicense_ValidUntil(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Condition Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addConditionTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_UserLicense_conditionType_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_UserLicense_conditionType_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_UserLicense_type"), //$NON-NLS-1$
						UsersPackage.eINSTANCE.getUserLicense_ConditionType(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Condition Expression feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addConditionExpressionPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_UserLicense_conditionExpression_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_UserLicense_conditionExpression_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_UserLicense_type"), //$NON-NLS-1$
						UsersPackage.eINSTANCE.getUserLicense_ConditionExpression(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Issue Date feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIssueDatePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_UserLicense_issueDate_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_UserLicense_issueDate_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_UserLicense_type"), //$NON-NLS-1$
						UsersPackage.eINSTANCE.getUserLicense_IssueDate(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Pack Identifier feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPackIdentifierPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_UserLicense_packIdentifier_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_UserLicense_packIdentifier_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_UserLicense_type"), //$NON-NLS-1$
						UsersPackage.eINSTANCE.getUserLicense_PackIdentifier(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This returns license.png.
	 * 
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/license.png")); //$NON-NLS-1$
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean shouldComposeCreationImage() {
		return true;
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		UserLicense userLicense = (UserLicense) object;
		String planId = userLicense.getPlanIdentifier();
		String productId = userLicense.getProductIdentifier();
		String productVersion = userLicense.getProductVersion();
		Object[] substitutions = new Object[] { planId, productId, productVersion };
		return getString("_UI_UserLicense_label", substitutions); //$NON-NLS-1$
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(UserLicense.class)) {
		case UsersPackage.USER_LICENSE__PLAN_IDENTIFIER:
		case UsersPackage.USER_LICENSE__PRODUCT_IDENTIFIER:
		case UsersPackage.USER_LICENSE__PRODUCT_VERSION:
		case UsersPackage.USER_LICENSE__VALID_FROM:
		case UsersPackage.USER_LICENSE__VALID_UNTIL:
		case UsersPackage.USER_LICENSE__CONDITION_TYPE:
		case UsersPackage.USER_LICENSE__CONDITION_EXPRESSION:
		case UsersPackage.USER_LICENSE__PACK_IDENTIFIER:
		case UsersPackage.USER_LICENSE__ISSUE_DATE:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		default:
			super.notifyChanged(notification);
			return;
		}
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return UsersEditPlugin.INSTANCE;
	}

}
