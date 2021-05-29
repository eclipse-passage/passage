package org.eclipse.passage.loc.licenses.emfforms.parts;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.LicensePlanFeature;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;

public final class LicensePlanContentProvider implements ITreeContentProvider {

	private final Object[] nobody = new Object[0];
	private final AdapterFactoryContentProvider delegate;

	public LicensePlanContentProvider() {
		delegate = new AdapterFactoryContentProvider(
				new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	public Object[] getChildren(Object parent) {
		if (parent instanceof LicensePlanFeature) {
			return nobody;
		}
		if (parent instanceof FloatingLicensePack) {
			return nobody;
		}
		if (parent instanceof PersonalLicensePack) {
			return nobody;
		}
		return delegate.getChildren(parent);
	}

	@Override
	public Object getParent(Object element) {
		return delegate.getParent(element);
	}

	@Override
	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

}
