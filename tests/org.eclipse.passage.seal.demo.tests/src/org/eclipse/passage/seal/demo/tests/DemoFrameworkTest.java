package org.eclipse.passage.seal.demo.tests;

import org.eclipse.passage.lic.internal.api.Framework;

import java.util.Optional;

import org.eclipse.passage.lic.api.tests.FrameworkContractTest;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.seal.internal.demo.DemoFrameworkSupplier;

@SuppressWarnings("restriction")
public final class DemoFrameworkTest extends FrameworkContractTest{

	@Override
	protected Optional<Framework> framework() {
		return new DemoFrameworkSupplier().get();
	}

	@Override
	protected boolean readOnly(Registry<?, ?> registry) {
		return false;
	}

}
