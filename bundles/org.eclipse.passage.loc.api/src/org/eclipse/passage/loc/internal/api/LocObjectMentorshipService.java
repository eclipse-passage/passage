package org.eclipse.passage.loc.internal.api;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;

public interface LocObjectMentorshipService {

	/**
	 * @return name of a mentor, if object has one.
	 */
	Optional<String> mentor(EObject object);

}
