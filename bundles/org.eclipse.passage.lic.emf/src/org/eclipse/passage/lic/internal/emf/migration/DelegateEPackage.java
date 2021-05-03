package org.eclipse.passage.lic.internal.emf.migration;

import java.util.HashMap;
import java.util.Map;
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
			if (existing != null) {
				Map<EClass, EClass> wrapped = new HashMap<>();
				EList<EClassifier> classifiers = existing.getEClassifiers();
				for (EClassifier eClassifier : classifiers) {
					if (eClassifier instanceof EClass) {
						EClass eClass = (EClass) eClassifier;
						EClass key = EcoreUtil.copy(eClass);
						wrapped.put(key, eClass);
					}
				}
				delegating.getEClassifiers().addAll(wrapped.keySet());
				DelegatingEFactory factory = delegating.getDelegatingEFactory();
				factory.addEClassDelegate(existing.getEFactoryInstance(), wrapped);
			}
			EPackage.Registry.INSTANCE.put(nsUri, delegating);
			return delegating;
		}
	}

}
