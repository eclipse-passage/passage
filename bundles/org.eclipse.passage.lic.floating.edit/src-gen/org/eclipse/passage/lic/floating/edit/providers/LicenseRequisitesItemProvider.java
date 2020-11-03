/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.floating.edit.providers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
import org.eclipse.passage.lic.floating.edit.FLoatingLicensesEditPlugin;
import org.eclipse.passage.lic.floating.model.api.LicenseRequisites;
import org.eclipse.passage.lic.floating.model.api.ProductRef;
import org.eclipse.passage.lic.floating.model.meta.FloatingPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.passage.lic.floating.model.api.LicenseRequisites} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class LicenseRequisitesItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LicenseRequisitesItemProvider(AdapterFactory adapterFactory) {
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
			addIssueDatePropertyDescriptor(object);
			addCompanyPropertyDescriptor(object);
			addPlanPropertyDescriptor(object);
			addProductPropertyDescriptor(object);
			addValidPropertyDescriptor(object);
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
						getResourceLocator(), getString("_UI_LicenseRequisites_identifier_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicenseRequisites_identifier_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicenseRequisites_type"), //$NON-NLS-1$
						FloatingPackage.eINSTANCE.getLicenseRequisites_Identifier(), true, false, false,
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
						getResourceLocator(), getString("_UI_LicenseRequisites_issueDate_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicenseRequisites_issueDate_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicenseRequisites_type"), //$NON-NLS-1$
						FloatingPackage.eINSTANCE.getLicenseRequisites_IssueDate(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
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
						getResourceLocator(), getString("_UI_LicenseRequisites_company_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicenseRequisites_company_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicenseRequisites_type"), //$NON-NLS-1$
						FloatingPackage.eINSTANCE.getLicenseRequisites_Company(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Plan feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPlanPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_LicenseRequisites_plan_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicenseRequisites_plan_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicenseRequisites_type"), //$NON-NLS-1$
						FloatingPackage.eINSTANCE.getLicenseRequisites_Plan(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Product feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addProductPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_LicenseRequisites_product_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicenseRequisites_product_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicenseRequisites_type"), //$NON-NLS-1$
						FloatingPackage.eINSTANCE.getLicenseRequisites_Product(), true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Valid feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addValidPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_LicenseRequisites_valid_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicenseRequisites_valid_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicenseRequisites_type"), //$NON-NLS-1$
						FloatingPackage.eINSTANCE.getLicenseRequisites_Valid(), true, false, true, null, null, null));
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
		LicenseRequisites license = (LicenseRequisites) object;
		String company = license.getCompany() == null ? "unknown" : license.getCompany(); //$NON-NLS-1$
		String product = Optional.ofNullable(license.getProduct())//
				.map(ProductRef::getProduct) //
				.orElse("unknown"); //$NON-NLS-1$
		String version = Optional.ofNullable(license.getProduct())//
				.map(ProductRef::getVersion) //
				.orElse("unknown"); //$NON-NLS-1$
		return getString("_UI_LicenseRequisites_type_detailed", //$NON-NLS-1$
				new Object[] { //
						company,//
						product,//
						version
				});
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

		switch (notification.getFeatureID(LicenseRequisites.class)) {
		case FloatingPackage.LICENSE_REQUISITES__IDENTIFIER:
		case FloatingPackage.LICENSE_REQUISITES__ISSUE_DATE:
		case FloatingPackage.LICENSE_REQUISITES__COMPANY:
		case FloatingPackage.LICENSE_REQUISITES__PLAN:
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
		return FLoatingLicensesEditPlugin.INSTANCE;
	}

}
