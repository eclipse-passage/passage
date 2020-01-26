/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.products.edit.providers;

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
import org.eclipse.passage.lic.products.edit.ProductsEditPlugin;
import org.eclipse.passage.lic.products.model.api.Product;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.lic.products.model.meta.ProductsFactory;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.passage.lic.products.model.api.ProductVersion} object.
 * <!-- begin-user-doc -->
 * 
 * <!-- end-user-doc -->
 * @generated
 */
public class ProductVersionItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductVersionItemProvider(AdapterFactory adapterFactory) {
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

			addVersionPropertyDescriptor(object);
			addNamePropertyDescriptor(object);
			addInstallationTokenPropertyDescriptor(object);
			addSecureTokenPropertyDescriptor(object);
			addNewsPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Version feature.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addVersionPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_ProductVersion_version_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_ProductVersion_version_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_ProductVersion_type"), //$NON-NLS-1$
						ProductsPackage.eINSTANCE.getProductVersion_Version(), true, false, false,
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
						getResourceLocator(), getString("_UI_ProductVersion_name_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_ProductVersion_name_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_ProductVersion_type"), //$NON-NLS-1$
						ProductsPackage.eINSTANCE.getProductVersion_Name(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Installation Token feature.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInstallationTokenPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_ProductVersion_installationToken_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_ProductVersion_installationToken_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_ProductVersion_type"), //$NON-NLS-1$
						ProductsPackage.eINSTANCE.getProductVersion_InstallationToken(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Secure Token feature.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSecureTokenPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_ProductVersion_secureToken_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_ProductVersion_secureToken_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_ProductVersion_type"), //$NON-NLS-1$
						ProductsPackage.eINSTANCE.getProductVersion_SecureToken(), true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the News feature.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNewsPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_ProductVersion_news_feature"), //$NON-NLS-1$
						getString("_UI_PropertyDescriptor_description", "_UI_ProductVersion_news_feature", //$NON-NLS-1$//$NON-NLS-2$
								"_UI_ProductVersion_type"), //$NON-NLS-1$
						ProductsPackage.eINSTANCE.getProductVersion_News(), true, true, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ProductsPackage.eINSTANCE.getProductVersion_ProductVersionFeatures());
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
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
	 * This returns Product.gif.
	 * 
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/product.png")); //$NON-NLS-1$
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
		// FIXME: provide "name" feature for ProductVersion
		ProductVersion productVersion = (ProductVersion) object;
		Product product = productVersion.getProduct();
		if (product == null) {
			return getString("_UI_ProductVersion_type"); //$NON-NLS-1$
		}
		String productName = product.getName();
		String version = productVersion.getVersion();
		if (version == null || version.length() == 0) {
			return productName;
		}
		return getString("_UI_ProductVersion_text_pattern", new Object[] { productName, version }); //$NON-NLS-1$
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

		switch (notification.getFeatureID(ProductVersion.class)) {
		case ProductsPackage.PRODUCT_VERSION__VERSION:
		case ProductsPackage.PRODUCT_VERSION__NAME:
		case ProductsPackage.PRODUCT_VERSION__INSTALLATION_TOKEN:
		case ProductsPackage.PRODUCT_VERSION__SECURE_TOKEN:
		case ProductsPackage.PRODUCT_VERSION__NEWS:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case ProductsPackage.PRODUCT_VERSION__PRODUCT_VERSION_FEATURES:
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
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors
				.add(createChildParameter(ProductsPackage.eINSTANCE.getProductVersion_ProductVersionFeatures(),
						ProductsFactory.eINSTANCE.createProductVersionFeature()));
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
		return ProductsEditPlugin.INSTANCE;
	}

}
