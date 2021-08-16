/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.lic.licenses.edit.providers;

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
import org.eclipse.passage.lic.licenses.edit.LicensesEditPlugin;
import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.passage.lic.licenses.model.api.LicensePlan} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class LicensePlanItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LicensePlanItemProvider(AdapterFactory adapterFactory) {
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
			addAgreementsPropertyDescriptor(object);
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
						getResourceLocator(), getString("_UI_LicensePlan_identifier_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicensePlan_identifier_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicensePlan_type"), //$NON-NLS-1$
						LicensesPackage.eINSTANCE.getLicensePlan_Identifier(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_LicensePlan_name_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicensePlan_name_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicensePlan_type"), //$NON-NLS-1$
						LicensesPackage.eINSTANCE.getLicensePlan_Name(), true, false, false,
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
						getResourceLocator(), getString("_UI_LicensePlan_description_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicensePlan_description_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicensePlan_type"), //$NON-NLS-1$
						LicensesPackage.eINSTANCE.getLicensePlan_Description(), true, true, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Agreements feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @since 2.1
	 * @generated
	 */
	protected void addAgreementsPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_LicensePlan_agreements_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicensePlan_agreements_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicensePlan_type"), //$NON-NLS-1$
						LicensesPackage.eINSTANCE.getLicensePlan_Agreements(), true, false, false,
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
			childrenFeatures.add(LicensesPackage.eINSTANCE.getLicensePlan_Features());
			childrenFeatures.add(LicensesPackage.eINSTANCE.getLicensePlan_Personal());
			childrenFeatures.add(LicensesPackage.eINSTANCE.getLicensePlan_Floating());
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
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		LicensePlan licensePlan = (LicensePlan) object;
		String identifier = licensePlan.getIdentifier();
		String name = licensePlan.getName();
		if (identifier == null || identifier.length() == 0) {
			return getString("_UI_LicensePlan_type"); //$NON-NLS-1$
		}
		if (name == null || name.length() == 0) {
			return identifier;
		}
		return getString("_UI_LicensePlan_text_pattern", new Object[] { identifier, name }); //$NON-NLS-1$
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

		switch (notification.getFeatureID(LicensePlan.class)) {
		case LicensesPackage.LICENSE_PLAN__IDENTIFIER:
		case LicensesPackage.LICENSE_PLAN__NAME:
		case LicensesPackage.LICENSE_PLAN__DESCRIPTION:
		case LicensesPackage.LICENSE_PLAN__AGREEMENTS:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case LicensesPackage.LICENSE_PLAN__FEATURES:
		case LicensesPackage.LICENSE_PLAN__PERSONAL:
		case LicensesPackage.LICENSE_PLAN__FLOATING:
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

		newChildDescriptors.add(createChildParameter(LicensesPackage.eINSTANCE.getLicensePlan_Features(),
				LicensesFactory.eINSTANCE.createLicensePlanFeature()));

		newChildDescriptors.add(createChildParameter(LicensesPackage.eINSTANCE.getLicensePlan_Personal(),
				LicensesFactory.eINSTANCE.createPersonalLicensePack()));

		newChildDescriptors.add(createChildParameter(LicensesPackage.eINSTANCE.getLicensePlan_Floating(),
				LicensesFactory.eINSTANCE.createFloatingLicensePack()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return LicensesEditPlugin.INSTANCE;
	}

}
