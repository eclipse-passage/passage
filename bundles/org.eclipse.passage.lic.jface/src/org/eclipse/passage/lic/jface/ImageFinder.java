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
package org.eclipse.passage.lic.jface;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class ImageFinder {

	private static final ImageDescriptor[] NO_IMAGE_DESCRIPTORS = new ImageDescriptor[0];

	public static URL getUrl(String value, Bundle definingBundle) {
		try {
			if (value != null) {
				return new URL(value);
			}
		} catch (MalformedURLException e) {
			if (definingBundle != null) {
				return FileLocator.find(definingBundle, new Path(value));
			}
		}
		return null;
	}

	public static List<URL> getUrls(String value, Bundle definingBundle, String separator) {
		if (value == null) {
			return Collections.emptyList();
		}

		StringTokenizer tokens = new StringTokenizer(value, separator);
		List<URL> list = new ArrayList<>(10);
		while (tokens.hasMoreTokens()) {
			String segment = tokens.nextToken().trim();
			URL url = getUrl(segment, definingBundle);
			if (url != null) {
				list.add(url);
			}
		}

		return list;
	}

	public static ImageDescriptor getImage(String value, Bundle definingBundle) {
		URL url = getUrl(value, definingBundle);
		return url == null ? null : ImageDescriptor.createFromURL(url);
	}

	public static ImageDescriptor[] getImages(String value, Bundle definingBundle) {
		List<URL> urls = getUrls(value, definingBundle, ","); //$NON-NLS-1$
		if (urls.isEmpty()) {
			return NO_IMAGE_DESCRIPTORS;
		}
		ImageDescriptor[] images = new ImageDescriptor[urls.size()];
		for (int i = 0; i < images.length; ++i) {
			images[i] = ImageDescriptor.createFromURL(urls.get(i));
		}
		return images;
	}

	/**
	 * Returns a URL for the given path in the given bundle. Returns
	 * <code>null</code> if the URL could not be computed or created.
	 *
	 * @see FileLocator#find(Bundle, org.eclipse.core.runtime.IPath)
	 *
	 * @param container the {@link Bundle} or {@link Class} from the bundle
	 * @param value     the path of the image file in the given bundle or the string
	 *                  to parse the {@link URL} from
	 * @return a URL for the given path or null. The actual form of the returned URL
	 *         is not specified.
	 * 
	 * @since 0.6
	 */
	public static URL locate(Object container, String value) {
		try {
			if (value != null) {
				return new URL(value);
			}
		} catch (MalformedURLException e) {
			Bundle bundle = null;
			if (container instanceof Bundle) {
				bundle = (Bundle) container;
			} else if (container instanceof Class<?>) {
				bundle = FrameworkUtil.getBundle((Class<?>) container);
			}
			if (bundle != null) {
				return FileLocator.find(bundle, new Path(value));
			}
		}
		return null;
	}

	/**
	 * Returns a {@link List}<{@link URL}> for the given value in the given bundle,
	 * the value will be split with the given delimiter.
	 *
	 * @see ResourceLocator#createImageDescriptors(Object, String, String)
	 *
	 * @param container the {@link Bundle} or {@link Class} from the bundle
	 * @param value     the string that contains delimited paths or URL strings
	 * @param delimeter the delimiting regular expression
	 * @return a list of URLs
	 * 
	 * @since 0.6
	 */
	public static List<URL> locate(Object container, String value, String delimeter) {
		if (value == null) {
			return Collections.emptyList();
		}
		return Stream.of(value.split(delimeter)).map(v -> locate(container, v)).filter(Objects::nonNull)
				.collect(Collectors.toList());
	}

	/**
	 * Creates and returns a new image descriptor for the given path in the given
	 * bundle.
	 *
	 * @see ImageDescriptor#createFromURL(URL)
	 *
	 * @param container the {@link Bundle} or {@link Class} from the bundle
	 * @param value     the path of the image file in the given bundle or the string
	 *                  to parse the {@link URL} from
	 * @return a new image descriptor
	 * 
	 * @since 0.6
	 */
	public static Optional<ImageDescriptor> createImageDescriptor(Object container, String value) {
		URL locate = locate(container, value);
		return locate == null ? Optional.empty() : Optional.of(ImageDescriptor.createFromURL(locate));
	}

	/**
	 * Creates and returns a {@link List}<{@link ImageDescriptor}> for the given
	 * value in the given bundle, the value will be split with the given delimiter.
	 *
	 * @see ImageDescriptor#createFromURL(URL)
	 * @see Window#setDefaultImages(org.eclipse.swt.graphics.Image[])
	 *
	 * @param container the {@link Bundle} or {@link Class} from the bundle
	 * @param value     the string that contains delimited paths or URL strings
	 * @param delimeter the delimiting regular expression
	 * @return a list of new image descriptor
	 * 
	 * @since 0.6
	 */
	public static List<ImageDescriptor> createImageDescriptors(Object container, String value, String delimeter) {
		return locate(container, value, delimeter).stream().map(u -> ImageDescriptor.createFromURL(u))
				.collect(Collectors.toList());
	}

}
