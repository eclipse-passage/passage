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

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;

import org.eclipse.passage.lic.floating.model.meta.FloatingPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FloatingLicenseAccessItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FloatingLicenseAccessItemProvider(AdapterFactory adapterFactory) {
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

			addUserPropertyDescriptor(object);
			addServerPropertyDescriptor(object);
			addOriginLicensePackPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the User feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUserPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_FloatingLicenseAccess_user_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_FloatingLicenseAccess_user_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_FloatingLicenseAccess_type"), //$NON-NLS-1$
						FloatingPackage.eINSTANCE.getFloatingLicenseAccess_User(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Server feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addServerPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_FloatingLicenseAccess_server_feature"), //$NON-NLS-1$
				getString("_UI_PropertyDescriptor_description", "_UI_FloatingLicenseAccess_server_feature", //$NON-NLS-1$//$NON-NLS-2$
						"_UI_FloatingLicenseAccess_type"), //$NON-NLS-1$
				FloatingPackage.eINSTANCE.getFloatingLicenseAccess_Server(), true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Origin License Pack feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOriginLicensePackPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_FloatingLicenseAccess_originLicensePack_feature"), //$NON-NLS-1$
				getString("_UI_PropertyDescriptor_description", "_UI_FloatingLicenseAccess_originLicensePack_feature", //$NON-NLS-1$//$NON-NLS-2$
						"_UI_FloatingLicenseAccess_type"), //$NON-NLS-1$
				FloatingPackage.eINSTANCE.getFloatingLicenseAccess_OriginLicensePack(), true, false, false,
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
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((FloatingLicenseAccess) object).getUser();
		return label == null || label.length() == 0 ? getString("_UI_FloatingLicenseAccess_type") : //$NON-NLS-1$
				getString("_UI_FloatingLicenseAccess_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

		switch (notification.getFeatureID(FloatingLicenseAccess.class)) {
		case FloatingPackage.FLOATING_LICENSE_ACCESS__USER:
		case FloatingPackage.FLOATING_LICENSE_ACCESS__ORIGIN_LICENSE_PACK:
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
