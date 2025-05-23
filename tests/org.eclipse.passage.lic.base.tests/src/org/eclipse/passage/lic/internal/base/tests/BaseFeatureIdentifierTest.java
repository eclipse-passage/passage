package org.eclipse.passage.lic.internal.base.tests;

import org.eclipse.passage.lic.api.FeatureIdentifier;
import org.eclipse.passage.lic.api.tests.FeatureIdentifierContractTest;
import org.eclipse.passage.lic.base.BaseFeatureIdentifier;

@SuppressWarnings("restriction")
public final class BaseFeatureIdentifierTest extends FeatureIdentifierContractTest {

	@Override
	protected FeatureIdentifier featureIdentifier(String input) {
		return new BaseFeatureIdentifier(input);
	}

}
