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
package org.eclipse.passage.lic.jface.viewers;

import org.eclipse.core.runtime.Adapters;
import org.eclipse.jface.resource.ColorDescriptor;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;

public class LicensingLabelProvider extends LabelProvider implements ITableLabelProvider, IStyledLabelProvider,
		IColorProvider, IFontProvider, ITableColorProvider, ITableFontProvider {

	private ResourceManager resourceManager;

	@Override
	public final String getText(Object element) {
		LicensingViewerAdapter adapter = getAdapter(element);
		if (adapter == null) {
			return ""; //$NON-NLS-1$
		}
		String label = adapter.getLabel(element);
		return decorateText(label, element);
	}

	@Override
	public final Image getImage(Object element) {
		LicensingViewerAdapter adapter = getAdapter(element);
		if (adapter == null) {
			return null;
		}
		ImageDescriptor descriptor = adapter.getImageDescriptor(element);
		if (descriptor == null) {
			return null;
		}
		descriptor = decorateImage(descriptor, element);
		return (Image) getResourceManager().get(descriptor);
	}

	@Override
	public Color getBackground(Object element) {
		LicensingViewerAdapter adapter = getAdapter(element);
		if (adapter == null) {
			return null;
		}
		RGB descriptor = adapter.getBackground(element);
		if (descriptor == null) {
			return null;
		}
		return (Color) getResourceManager().get(ColorDescriptor.createFrom(descriptor));
	}

	@Override
	public Color getForeground(Object element) {
		LicensingViewerAdapter adapter = getAdapter(element);
		if (adapter == null) {
			return null;
		}
		RGB descriptor = adapter.getForeground(element);
		if (descriptor == null) {
			return null;
		}
		return (Color) getResourceManager().get(ColorDescriptor.createFrom(descriptor));
	}

	@Override
	public Font getFont(Object element) {
		LicensingViewerAdapter adapter = getAdapter(element);
		if (adapter == null) {
			return null;
		}
		FontData descriptor = adapter.getFont(element);
		if (descriptor == null) {
			return null;
		}
		return (Font) getResourceManager().get(FontDescriptor.createFrom(descriptor));
	}

	@Override
	public StyledString getStyledText(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		LicensingViewerAdapter adapter = getAdapter(element);
		if (adapter == null) {
			return ""; //$NON-NLS-1$
		}
		String label = adapter.getLabel(element, columnIndex);
		if (label == null) {
			return getText(element);
		}
		return decorateText(label, element);
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		LicensingViewerAdapter adapter = getAdapter(element);
		if (adapter == null) {
			return null;
		}
		ImageDescriptor descriptor = adapter.getImageDescriptor(element, columnIndex);
		if (descriptor == null) {
			return getImage(element);
		}
		descriptor = decorateImage(descriptor, element);
		return (Image) getResourceManager().get(descriptor);
	}

	@Override
	public Color getBackground(Object element, int columnIndex) {
		return getBackground(element);
	}

	@Override
	public Color getForeground(Object element, int columnIndex) {
		return getForeground(element);
	}

	@Override
	public Font getFont(Object element, int columnIndex) {
		return getFont(element);
	}

	@Override
	public void dispose() {
		if (resourceManager != null) {
			resourceManager.dispose();
		}
		resourceManager = null;
		super.dispose();
	}

	/**
	 * Returns the implementation of LicensingViewerAdapter for the given object.
	 * 
	 * @param o the object to look up.
	 * @return LicensingViewerAdapter or<code>null</code> if the adapter is not
	 *         defined or the object is not adaptable.
	 */
	protected final LicensingViewerAdapter getAdapter(Object o) {
		return Adapters.adapt(o, LicensingViewerAdapter.class);
	}

	/**
	 * Lazy load the resource manager
	 *
	 * @return The resource manager, create one if necessary
	 */
	private ResourceManager getResourceManager() {
		if (resourceManager == null) {
			resourceManager = new LocalResourceManager(JFaceResources.getResources());
		}

		return resourceManager;
	}

	/**
	 * Returns an image descriptor that is based on the given descriptor, but
	 * decorated with additional information relating to the state of the provided
	 * object.
	 *
	 * Subclasses may reimplement this method to decorate an object's image.
	 *
	 * @param input   The base image to decorate.
	 * @param element The element used to look up decorations.
	 * @return the resuling ImageDescriptor.
	 * @see org.eclipse.jface.resource.CompositeImageDescriptor
	 */
	protected ImageDescriptor decorateImage(ImageDescriptor input, Object element) {
		return input;
	}

	/**
	 * Returns a label that is based on the given label, but decorated with
	 * additional information relating to the state of the provided object.
	 *
	 * Subclasses may implement this method to decorate an object's label.
	 * 
	 * @param input   The base text to decorate.
	 * @param element The element used to look up decorations.
	 * @return the resulting text
	 */
	protected String decorateText(String input, Object element) {
		return input;
	}

}
