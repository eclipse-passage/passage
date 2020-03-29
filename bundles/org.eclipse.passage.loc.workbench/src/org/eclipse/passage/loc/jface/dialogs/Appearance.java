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
package org.eclipse.passage.loc.jface.dialogs;

import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.swt.graphics.Image;

/**
 * Encapsulates dialog appearance to reduce the number of arguments in methods
 * 
 * @since 0.6
 *
 */
public final class Appearance {

	private final String title;
	private final Supplier<Image> image;
	private final LabelProvider labels;

	/**
	 * Creates the dialog appearance descriptor with the given non-<code>null</code>
	 * title, default image supplier and default label provider will be used
	 * 
	 * @param title title for dialog, must not be <code>null</code>
	 * 
	 */
	public Appearance(String title) {
		this(title, () -> LicensingImages.getImageRegistry().get(LicensingImages.IMG_DEFAULT));
	}

	/**
	 * Creates the dialog appearance descriptor with the given non-<code>null</code>
	 * title and image, default label provider will be used
	 * 
	 * @param title title for dialog, must not be <code>null</code>
	 * @param image image supplier for dialog, must not be <code>null</code>
	 * 
	 */
	public Appearance(String title, Supplier<Image> image) {
		this(title, image, new LabelProvider());
	}

	/**
	 * Creates the dialog appearance descriptor with the given non-<code>null</code>
	 * title, image supplier, and label provider
	 * 
	 * @param title  title for dialog, must not be <code>null</code>
	 * @param image  supplier of the image for dialog, must not be <code>null</code>
	 * @param labels label provider for dialog, must not be <code>null</code>
	 * 
	 */
	public Appearance(String title, Supplier<Image> image, LabelProvider labels) {
		Objects.requireNonNull(title, WorkbenchMessages.Appearance_e_null_title);
		Objects.requireNonNull(image, WorkbenchMessages.Appearance_e_null_image);
		Objects.requireNonNull(labels, WorkbenchMessages.Appearance_e_null_labels);
		this.title = title;
		this.image = image;
		this.labels = labels;
	}

	/**
	 * The title to use for dialog's shell
	 * 
	 * @return non-<code>null</code> title
	 */
	public String title() {
		return title;
	}

	/**
	 * The image supplier to use for dialog's shell
	 * 
	 * @return non-<code>null</code> image supplier
	 */
	public Supplier<Image> image() {
		return image;
	}

	/**
	 * The {@link LabelProvider} to use for dialog
	 * 
	 * @return non-<code>null</code> label provider
	 */
	public LabelProvider labelProvider() {
		return labels;
	}

}
