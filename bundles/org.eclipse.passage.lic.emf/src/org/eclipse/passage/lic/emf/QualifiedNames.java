package org.eclipse.passage.lic.emf;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;

/**
 * Provides qualified names for cross-EPackage operations
 * 
 * @since 2.0
 */
public interface QualifiedNames {

	String caseEClass(EClass eClass);

	String caseEDataType(EDataType eDataType);

}
