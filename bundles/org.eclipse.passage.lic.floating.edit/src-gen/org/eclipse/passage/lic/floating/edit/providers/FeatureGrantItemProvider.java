/**
 */
package org.eclipse.passage.lic.floating.edit.providers;

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

import org.eclipse.passage.lic.floating.edit.FLoatingLicensesEditPlugin;

/**
 * This is the item provider adapter for a {@link org.eclipse.passage.lic.floating.model.api.FeatureGrant} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FeatureGrantItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureGrantItemProvider(AdapterFactory adapterFactory) {
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
			addFeaturePropertyDescriptor(object);
			addVersionPropertyDescriptor(object);
			addValidPropertyDescriptor(object);
			addCapacityPropertyDescriptor(object);
			addPackPropertyDescriptor(object);
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
						getResourceLocator(), getString("_UI_FeatureGrant_identifier_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_FeatureGrant_identifier_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_FeatureGrant_type"), //$NON-NLS-1$
						FloatingPackage.eINSTANCE.getFeatureGrant_Identifier(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Feature feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFeaturePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_FeatureGrant_feature_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_FeatureGrant_feature_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_FeatureGrant_type"), //$NON-NLS-1$
						FloatingPackage.eINSTANCE.getFeatureGrant_Feature(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Version feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addVersionPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_FeatureGrant_version_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_FeatureGrant_version_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_FeatureGrant_type"), //$NON-NLS-1$
						FloatingPackage.eINSTANCE.getFeatureGrant_Version(), true, false, true, null, null, null));
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
						getResourceLocator(), getString("_UI_FeatureGrant_valid_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_FeatureGrant_valid_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_FeatureGrant_type"), //$NON-NLS-1$
						FloatingPackage.eINSTANCE.getFeatureGrant_Valid(), true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Capacity feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCapacityPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_FeatureGrant_capacity_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_FeatureGrant_capacity_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_FeatureGrant_type"), //$NON-NLS-1$
						FloatingPackage.eINSTANCE.getFeatureGrant_Capacity(), true, false, false,
						ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Pack feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPackPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_FeatureGrant_pack_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_FeatureGrant_pack_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_FeatureGrant_type"), //$NON-NLS-1$
						FloatingPackage.eINSTANCE.getFeatureGrant_Pack(), true, false, true, null, null, null));
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
		String label = ((FeatureGrant) object).getIdentifier();
		return label == null || label.length() == 0 ? getString("_UI_FeatureGrant_type") : //$NON-NLS-1$
				getString("_UI_FeatureGrant_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

		switch (notification.getFeatureID(FeatureGrant.class)) {
		case FloatingPackage.FEATURE_GRANT__IDENTIFIER:
		case FloatingPackage.FEATURE_GRANT__FEATURE:
		case FloatingPackage.FEATURE_GRANT__CAPACITY:
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
