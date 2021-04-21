package org.eclipse.passage.lic.internal.licenses.model;

import java.util.function.Function;

import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.api.ProductRef;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;

public final class EnsureProductRef implements Function<LicensePack, ProductRef> {

	private final LicensesFactory factory;

	public EnsureProductRef() {
		factory = LicensesFactory.eINSTANCE;
	}

	@Override
	public ProductRef apply(LicensePack pack) {
		ProductRef product = pack.getProduct();
		if (product == null) {
			product = factory.createProductRef();
			pack.setProduct(product);
		}
		return product;
	}

}
