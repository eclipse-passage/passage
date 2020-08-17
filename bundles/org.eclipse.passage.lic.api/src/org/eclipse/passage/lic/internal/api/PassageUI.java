package org.eclipse.passage.lic.internal.api;

/**
 * <p>
 * Framework-dependent service supplies a functionally-sufficient flow of
 * license coverage testing and on-the-fly improving.
 * </p>
 * <p>
 * Which inevitably means end user involvement through dialogs exposing current
 * licensing status, failures diagnostic, import license facilities, etc
 * </p>
 */
public interface PassageUI {

	/**
	 * <p>
	 * Triggers a potentially heavy, long running process, UI-dependent and
	 * user-dependent process aimed in checking license coverage and asking user for
	 * improvements, if there it does not cover the feature demanded.
	 * </p>
	 * <p>
	 * Checks runtime-existing license coverage and if it sufficient - returns
	 * {@code true}, otherwise <i>tries to solve this problem</i>. It exposes the
	 * current licensing status to the end user and offers license import/request
	 * facilities. If a new license has been imported on-the-fly, then re-runs
	 * access cycle. Repeats these steps until either imported license covers the
	 * {@code feature} demanded or the end user declines to improve the license
	 * coverage.
	 * </p>
	 * 
	 * @param feature identifier of a feature desired to be checked for license
	 *                coverage
	 * @return {@code true} if, at the end of the day, the coverage is sufficient
	 *         for the {@code feature} utilization. Returns {@code false} if
	 *         existing coverage is not enough and the end user failed to improve
	 *         it.
	 */
	boolean canUse(String feature);

}
