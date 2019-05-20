package org.eclipse.passage.loc.internal.products.ui;

import org.eclipse.osgi.util.NLS;

public class ProductsUiMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.passage.loc.internal.products.ui.ProductsUiMessages"; //$NON-NLS-1$
	public static String ProductExportHandler_message_error;
	public static String ProductExportHandler_title_error;
	public static String ProductExportHandler_title_ok;
	public static String ProductsUi_select_product;
	public static String ProductsUi_select_product_line;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, ProductsUiMessages.class);
	}

	private ProductsUiMessages() {
	}
}
