package org.eclipse.passage.lbc.internal.api;

/**
 * Represents a license that was loaded to the floating server to be operated.
 * 
 * @since 1.0
 */
public interface BoundLicense {

	// Condition identifier
	String identifier();

	// Grant's capacity
	int capacity();

	// How many times it was taken
	int taken();

	// returns true if it is takeable and false if it is not.
	boolean takeable();

}
