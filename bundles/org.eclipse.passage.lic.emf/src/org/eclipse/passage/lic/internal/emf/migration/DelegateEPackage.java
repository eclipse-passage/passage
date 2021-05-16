package org.eclipse.passage.lic.internal.emf.migration;

import java.util.function.Function;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * @since 2.0
 */
public final class DelegateEPackage implements Function<String, DelegatingEPackage> {

	@Override
	public DelegatingEPackage apply(String nsUri) {
		EPackage existing = EPackage.Registry.INSTANCE.getEPackage(nsUri);
		if (existing instanceof DelegatingEPackage) {
			return (DelegatingEPackage) existing;
		} else {
			DelegatingEPackage delegating = new DelegatingEPackage(nsUri);
			DelegatingEFactory factory = delegating.getDelegatingEFactory();
			if (existing != null) {
				EList<EClassifier> classifiers = existing.getEClassifiers();
				for (EClassifier eClassifier : classifiers) {
					if (eClassifier instanceof EClass) {
						EClass to = (EClass) eClassifier;
						EClass from = EcoreUtil.copy(to);
						delegating.getEClassifiers().add(from);
						factory.delegateEClass(existing.getEFactoryInstance(), from, to);
					}
				}
			}
			EPackage.Registry.INSTANCE.put(nsUri, delegating);
			return delegating;
		}
	}

}
