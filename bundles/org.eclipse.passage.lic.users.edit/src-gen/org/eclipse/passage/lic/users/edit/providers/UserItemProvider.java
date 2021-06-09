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
package org.eclipse.passage.lic.users.edit.providers;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
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
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.meta.UsersFactory;
import org.eclipse.passage.lic.users.model.meta.UsersPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.passage.lic.users.model.api.User} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class UserItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserItemProvider(AdapterFactory adapterFactory) {
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

			addIdentifierPropertyDescriptor(object);
			addNamePropertyDescriptor(object);
			addDescriptionPropertyDescriptor(object);
			addContactPropertyDescriptor(object);
			addPreferredEvaluationTypePropertyDescriptor(object);
			addPreferredEvaluationExpressionPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Identifier feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIdentifierPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_LicenseOwner_identifier_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicenseOwner_identifier_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicenseOwner_type"), //$NON-NLS-1$
						UsersPackage.eINSTANCE.getLicenseOwner_Identifier(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_LicenseOwner_name_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicenseOwner_name_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicenseOwner_type"), //$NON-NLS-1$
						UsersPackage.eINSTANCE.getLicenseOwner_Name(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Description feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_LicenseOwner_description_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicenseOwner_description_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicenseOwner_type"), //$NON-NLS-1$
						UsersPackage.eINSTANCE.getLicenseOwner_Description(), true, true, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Contact feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	protected void addContactPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_LicenseOwner_contact_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicenseOwner_contact_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicenseOwner_type"), //$NON-NLS-1$
						UsersPackage.eINSTANCE.getLicenseOwner_Contact(), true, false, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Preferred Evaluation Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	protected void addPreferredEvaluationTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_User_preferredEvaluationType_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_User_preferredEvaluationType_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_User_type"), //$NON-NLS-1$
						UsersPackage.eINSTANCE.getUser_PreferredEvaluationType(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Preferred Evaluation Expression feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.0
	 * @generated
	 */
	protected void addPreferredEvaluationExpressionPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_User_preferredEvaluationExpression_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", //$NON-NLS-1$
								"_UI_User_preferredEvaluationExpression_feature", "_UI_User_type"), //$NON-NLS-1$ //$NON-NLS-2$
						UsersPackage.eINSTANCE.getUser_PreferredEvaluationExpression(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(UsersPackage.eINSTANCE.getLicenseOwner_Contact());
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns user.png
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/user.png")); //$NON-NLS-1$
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((User) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_User_type") : //$NON-NLS-1$
				getString("_UI_User_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

		switch (notification.getFeatureID(User.class)) {
		case UsersPackage.USER__IDENTIFIER:
		case UsersPackage.USER__NAME:
		case UsersPackage.USER__DESCRIPTION:
		case UsersPackage.USER__PREFERRED_EVALUATION_TYPE:
		case UsersPackage.USER__PREFERRED_EVALUATION_EXPRESSION:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case UsersPackage.USER__CONTACT:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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

		newChildDescriptors.add(createChildParameter(UsersPackage.eINSTANCE.getLicenseOwner_Contact(),
				UsersFactory.eINSTANCE.createContact()));
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
