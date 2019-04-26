/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.lic.licenses.edit.providers;

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
import org.eclipse.passage.lic.licenses.edit.LicensesEditPlugin;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.meta.LicensesPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.passage.lic.licenses.model.api.LicenseGrant} object.
 * <!-- begin-user-doc -->
 * 
 * <!-- end-user-doc -->
 * @generated
 */
public class LicenseGrantItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LicenseGrantItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addFeatureIdentifierPropertyDescriptor(object);
			addMatchVersionPropertyDescriptor(object);
			addMatchRulePropertyDescriptor(object);
			addValidFromPropertyDescriptor(object);
			addValidUntilPropertyDescriptor(object);
			addConditionTypePropertyDescriptor(object);
			addConditionExpressionPropertyDescriptor(object);
			addCapacityPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Feature Identifier feature.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFeatureIdentifierPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_LicenseGrant_featureIdentifier_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicenseGrant_featureIdentifier_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicenseGrant_type"), //$NON-NLS-1$
						LicensesPackage.eINSTANCE.getLicenseGrant_FeatureIdentifier(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Match Version feature.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMatchVersionPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_LicenseGrant_matchVersion_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicenseGrant_matchVersion_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicenseGrant_type"), //$NON-NLS-1$
						LicensesPackage.eINSTANCE.getLicenseGrant_MatchVersion(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Match Rule feature.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMatchRulePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_LicenseGrant_matchRule_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicenseGrant_matchRule_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicenseGrant_type"), //$NON-NLS-1$
						LicensesPackage.eINSTANCE.getLicenseGrant_MatchRule(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Valid From feature.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addValidFromPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_LicenseGrant_validFrom_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicenseGrant_validFrom_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicenseGrant_type"), //$NON-NLS-1$
						LicensesPackage.eINSTANCE.getLicenseGrant_ValidFrom(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Valid Until feature.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addValidUntilPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_LicenseGrant_validUntil_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicenseGrant_validUntil_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicenseGrant_type"), //$NON-NLS-1$
						LicensesPackage.eINSTANCE.getLicenseGrant_ValidUntil(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Condition Type feature.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addConditionTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_LicenseGrant_conditionType_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicenseGrant_conditionType_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicenseGrant_type"), //$NON-NLS-1$
						LicensesPackage.eINSTANCE.getLicenseGrant_ConditionType(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Condition Expression feature.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addConditionExpressionPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_LicenseGrant_conditionExpression_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicenseGrant_conditionExpression_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicenseGrant_type"), //$NON-NLS-1$
						LicensesPackage.eINSTANCE.getLicenseGrant_ConditionExpression(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Capacity feature.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCapacityPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_LicenseGrant_capacity_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_LicenseGrant_capacity_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_LicenseGrant_type"), //$NON-NLS-1$
						LicensesPackage.eINSTANCE.getLicenseGrant_Capacity(), true, false, false,
						ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
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
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean shouldComposeCreationImage() {
		return true;
	}

	/**
	 * This returns the label text for the adapted class.
	 * 
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		LicenseGrant licenseGrant = (LicenseGrant) object;
		String feature = licenseGrant.getFeatureIdentifier();
		if (feature == null || feature.length() == 0) {
			return getString("_UI_LicenseGrant_type"); //$NON-NLS-1$

		}
		String type = licenseGrant.getConditionType();
		if (type == null || type.length() == 0) {
			return feature;
		}
		return getString("_UI_LicenseGrant_text_pattern", new Object[] { type, feature }); //$NON-NLS-1$
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(LicenseGrant.class)) {
		case LicensesPackage.LICENSE_GRANT__FEATURE_IDENTIFIER:
		case LicensesPackage.LICENSE_GRANT__MATCH_VERSION:
		case LicensesPackage.LICENSE_GRANT__MATCH_RULE:
		case LicensesPackage.LICENSE_GRANT__VALID_FROM:
		case LicensesPackage.LICENSE_GRANT__VALID_UNTIL:
		case LicensesPackage.LICENSE_GRANT__CONDITION_TYPE:
		case LicensesPackage.LICENSE_GRANT__CONDITION_EXPRESSION:
		case LicensesPackage.LICENSE_GRANT__CAPACITY:
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
	 * 
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
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return LicensesEditPlugin.INSTANCE;
	}

}
