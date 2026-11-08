package org.eclipse.passage.lic.internal.base.inspection;

import java.util.function.Supplier;

import org.eclipse.passage.lic.api.EvaluationType;

public final class TrustedEvaluationType implements Supplier<EvaluationType> {

	@Override
	public EvaluationType get() {
		return new EvaluationType.Of("trusted"); //$NON-NLS-1$
	}

}
