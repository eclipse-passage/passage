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
package org.eclipse.passage.lic.jface.resource;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class LicensingImages {

	public static final String IMG_DEFAULT = "IMG_DEFAULT"; //$NON-NLS-1$

	public static final String IMG_LEVEL_OK = "IMG_LEVEL_OK"; //$NON-NLS-1$
	public static final String IMG_LEVEL_INFO = "IMG_LEVEL_INFO"; //$NON-NLS-1$
	public static final String IMG_LEVEL_WARN = "IMG_LEVEL_WARN"; //$NON-NLS-1$
	public static final String IMG_LEVEL_ERROR = "IMG_LEVEL_ERROR"; //$NON-NLS-1$
	public static final String IMG_LEVEL_FATAL = "IMG_LEVEL_FATAL"; //$NON-NLS-1$

	public static final String IMG_EXPORT = "IMG_EXPORT"; //$NON-NLS-1$
	public static final String IMG_IMPORT = "IMG_IMPORT"; //$NON-NLS-1$

	public static final String IMG_INSPECTOR = "IMG_INSPECTOR"; //$NON-NLS-1$

	/**
	 * The path to the icons in the resources.
	 */
	public static final String PATH_ICONS = "$nl$/icons/";//$NON-NLS-1$

	/**
	 * Enabled toolbar icons.
	 */
	public static final String PATH_ETOOL = PATH_ICONS + "etool16/"; //$NON-NLS-1$

	/**
	 * Disabled toolbar icons.
	 */
	public static final String PATH_DTOOL = PATH_ICONS + "dtool16/"; //$NON-NLS-1$

	/**
	 * View icons.
	 */
	public static final String PATH_EVIEW = PATH_ICONS + "eview16/"; //$NON-NLS-1$

	/**
	 * Overlay icons.
	 */
	public static final String PATH_OVERLAY = PATH_ICONS + "ovr16/"; //$NON-NLS-1$

	/**
	 * Model object icons.
	 */
	public static final String PATH_OBJECT = PATH_ICONS + "obj16/"; //$NON-NLS-1$

	/**
	 * Wizard banners.
	 */
	public static final String PATH_WIZBAN = PATH_ICONS + "wizban/"; //$NON-NLS-1$

	/**
	 * The Licensing image registry; <code>null</code> until lazily initialized.
	 */
	private static ImageRegistry imageRegistry = null;

	/**
	 * Returns the image in Licensing image registry with the given key, or
	 * <code>null</code> if none. Convenience method equivalent to
	 *
	 * <pre>
	 * LicensingImages.getImageRegistry().get(key)
	 * </pre>
	 *
	 * @param key the key
	 * @return the image, or <code>null</code> if none
	 */
	public static Image getImage(String key) {
		return getImageRegistry().get(key);
	}

	/**
	 * Returns the descriptor in Licensing image registry with the given key, or
	 * <code>null</code> if none. Convenience method equivalent to
	 *
	 * <pre>
	 * LicensingImages.getImageRegistry().getDescriptor(key)
	 * </pre>
	 *
	 * @param key the key
	 * @return the descriptor, or <code>null</code> if none
	 */
	public static ImageDescriptor getImageDescriptor(String key) {
		return getImageRegistry().getDescriptor(key);
	}

	/**
	 * Returns the image registry for Licensing.
	 * <p>
	 * Note that the static convenience methods <code>getImage</code> and
	 * <code>getImageDescriptor</code> are also provided on this class.
	 * </p>
	 * 
	 * @see LicensingImages#getImage(String)
	 * @see LicensingImages#getImageDescriptor(String)
	 * 
	 * @return the Licensing image registry
	 */
	public static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry(JFaceResources.getResources(Display.getCurrent()));
			initializeDefaultImages();
		}
		return imageRegistry;
	}

	/**
	 * Initialize default images in Licensing image registry.
	 *
	 */
	private static void initializeDefaultImages() {
		Map<String, String> paths = new HashMap<>();
		paths.put(IMG_DEFAULT, PATH_OBJECT + "passage.png"); //$NON-NLS-1$
		paths.put(IMG_LEVEL_OK, PATH_OBJECT + "ok.png"); //$NON-NLS-1$
		paths.put(IMG_LEVEL_INFO, PATH_OBJECT + "info.png"); //$NON-NLS-1$
		paths.put(IMG_LEVEL_WARN, PATH_OBJECT + "warn.png"); //$NON-NLS-1$
		paths.put(IMG_LEVEL_ERROR, PATH_OBJECT + "error.png"); //$NON-NLS-1$
		paths.put(IMG_LEVEL_FATAL, PATH_OBJECT + "fatal.png"); //$NON-NLS-1$

		paths.put(IMG_EXPORT, PATH_ETOOL + "export.png"); //$NON-NLS-1$
		paths.put(IMG_IMPORT, PATH_ETOOL + "import.png"); //$NON-NLS-1$
		paths.put(IMG_INSPECTOR, PATH_ETOOL + "hw-inspector.png"); //$NON-NLS-1$
		declareImages(LicensingImages.class, paths);
	}

	/**
	 * Declares a Licensing images for the key-> path {@link Map}. May only be
	 * called from a UI thread.
	 * 
	 * @param location the {@link Class} where the image is relative to
	 * @param paths    the the key-> path {@link Map} (the path is relative to
	 *                 location)
	 * 
	 * @return the status of the operation
	 */
	public static IStatus declareImages(Class<?> location, Map<String, String> paths) {
		ImageRegistry registry = getImageRegistry();
		Bundle bundle = null;
		try {
			bundle = FrameworkUtil.getBundle(location);
		} catch (Throwable exception) {
			// Test to see if OSGI is present
		}
		Set<Entry<String, String>> entries = paths.entrySet();
		for (Entry<String, String> entry : entries) {
			String key = entry.getKey();
			String path = entry.getValue();
			ImageDescriptor descriptor = null;
			if (bundle != null) {
				URL url = FileLocator.find(bundle, new Path(path), null);
				if (url != null) {
					descriptor = ImageDescriptor.createFromURL(url);
				}
			}
			if (descriptor == null) {
				descriptor = ImageDescriptor.createFromFile(location, path);
			}
			if (descriptor != null) {
				registry.put(key, descriptor);
			}
		}
		return Status.OK_STATUS;
	}

}
