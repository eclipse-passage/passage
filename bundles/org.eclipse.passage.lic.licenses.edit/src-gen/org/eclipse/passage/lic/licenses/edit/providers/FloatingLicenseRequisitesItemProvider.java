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
package org.eclipse.passage.lic.licenses.edit.providers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.passage.lic.licenses.model.api.CompanyRef;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites;
import org.eclipse.passage.lic.licenses.model.api.ProductRef;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @since 2.0
 * @generated
 */
public class FloatingLicenseRequisitesItemProvider extends LicenseRequisitesItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FloatingLicenseRequisitesItemProvider(AdapterFactory adapterFactory) {
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

			addCompanyPropertyDescriptor(object);
			addGroupPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Company feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCompanyPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_FloatingLicenseRequisites_company_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_FloatingLicenseRequisites_company_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_FloatingLicenseRequisites_type"), //$NON-NLS-1$
						LicensesPackage.eINSTANCE.getFloatingLicenseRequisites_Company(), true, false, false, null,
						null, null));
	}

	/**
	 * This adds a property descriptor for the Group feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addGroupPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_FloatingLicenseRequisites_group_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_FloatingLicenseRequisites_group_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_FloatingLicenseRequisites_type"), //$NON-NLS-1$
						LicensesPackage.eINSTANCE.getFloatingLicenseRequisites_Group(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
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
	 * This returns the label text for the adapted class. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		FloatingLicenseRequisites license = (FloatingLicenseRequisites) object;
		String company = Optional.ofNullable(license.getCompany())//
				.map(CompanyRef::getName) //
				.orElse("unknown"); //$NON-NLS-1$
		String product = Optional.ofNullable(license.getProduct())//
				.map(ProductRef::getIdentifier) //
				.orElse("unknown"); //$NON-NLS-1$
		String version = Optional.ofNullable(license.getProduct())//
				.map(ProductRef::getVersion) //
				.orElse("unknown"); //$NON-NLS-1$
		return getString("_UI_LicenseRequisites_type_detailed", //$NON-NLS-1$
				new Object[] { //
						company, //
						product, //
						version });
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

		switch (notification.getFeatureID(FloatingLicenseRequisites.class)) {
		case LicensesPackage.FLOATING_LICENSE_REQUISITES__COMPANY:
		case LicensesPackage.FLOATING_LICENSE_REQUISITES__GROUP:
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

}
