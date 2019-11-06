/*
  Copyright (c) 2018-2019 ArSysOp
  <p>
  This program and the accompanying materials are made available under the
  terms of the Eclipse Public License 2.0 which is available at
  http://www.eclipse.org/legal/epl-2.0.
  <p>
  SPDX-License-Identifier: EPL-2.0
  <p>
  Contributors:
  ArSysOp - initial API and implementation
 */

/**
 * <h1>Access Management</h1>
 * <h2> In a sentence</h2> <p>
 * AM serves the only purpose: a program under licensing, been run, can ask AM,
 * if it is allowed to use a particular feature <i>now</i>,
 * and the answer will be more or less simple <i>yes</i> or <i>no</i>.
 * </p>
 * <p/>
 * <h2>In a paragraph</h2><p>
 * A program, been run, is asked by it's <b>user</b> to access a particular <b>feature</b>,
 * which has been implemented as a <b>feature under licensing</b> by it's developers.<br/>
 * Here Passage invokes AM to define, can the user exploit the feature or not.
 * </p>
 *
 * <ul>
 * <li>AM uses {@code Requirement}s to define at runtime, which <b>feature</b> in a <b>program</b> is under licensing.
 * These {@code Requirement}s are given by the precise installation of a <b>program under licensing</b>.
 * So the first thing AM does is finding out, is the actual program installation possesses any
 * <i>protect this feature</i> instructions in the first place.
 * To do so, AM appeals to all registered {@code RequirementResolver}s and gets a set of {@code Requirement}s
 * for further analysis</li>
 *
 * <li>Then AM needs to figure out, what the <b>license</b> that the <b>user</b> has, allows to do.
 * AM goes to each of {@code ConditionMiner}s it have at ones disposal
 * and gets set of {@code LicensingCondition}s.
 * {@code ConditionMiner}s look for them in <i>the license</i> files, on a <i>floating server</i> or mining sources of other types.
 * </li>
 *
 * <li>These {@link org.eclipse.passage.lic.api.conditions.LicensingCondition}s are static definitions of what can be done under what conditions.
 * At <b>the program</b> runtime AM <i>evaluates</i> each <i>Condition</i> to a precise notion of
 * is this <i>Condition</i> is met at <b>the program</b> runtime or not. So, at this step, for each <i>Condition</i> AM asks
 * its {@link org.eclipse.passage.lic.api.access.PermissionEmitter}s if it can issue
 * a {@link org.eclipse.passage.lic.api.access.FeaturePermission} for this <i>Condition</i></li>
 * <li>At this point AM has both
 * actual {@link org.eclipse.passage.lic.api.requirements.LicensingRequirement}s
 * and valid {@link org.eclipse.passage.lic.api.access.FeaturePermission}s.
 * This information is enough to make a decision about the start-up question: can <b>the user</b> access to <b>the feature</b> <i>now</i>.
 * Here {@link org.eclipse.passage.lic.api.access.PermissionExaminer} comes to play: it goes through
 * {@link org.eclipse.passage.lic.api.access.FeaturePermission}s and if it satisfies any of
 * {@link org.eclipse.passage.lic.api.requirements.LicensingRequirement}, the latter one is considered satisfied and does not participate in further actions.
 * All unsatisfied {@link org.eclipse.passage.lic.api.requirements.LicensingRequirement}s left at the and of the work, are translated to a set of
 * {@link org.eclipse.passage.lic.api.restrictions.RestrictionVerdict}s. </li>
 * <li>If here AM has an empty set of {@link org.eclipse.passage.lic.api.restrictions.RestrictionVerdict}s (all {@link org.eclipse.passage.lic.api.requirements.LicensingRequirement}s are satisfied),
 * then <b>the user</b> can access <b>the feature</b>. Otherwise, <i>some actions must be taken</i>, and each verdict describes such an action.
 * {@link org.eclipse.passage.lic.api.restrictions.RestrictionExecutor} is the service responsible for these actions execution:
 * if can popup a warning, expose license dialog or even shut the whole program down. </li>
 * </ul>
 * <p>
 * <p>
 * <br/><h2>In details</h2>
 * <br/><h3>Requirements</h3>
 * <br/><h4>Where do they come from?</h4>
 * <p>
 *     They come from a <b>program</b> installation. {@code Requirement}s are declared by developers.
 *     A {@code Requirement} says: a user can exploit this part (<b>feature</b>) of a <b>program</b> only if has a proper license,
 *     unless the usage is <i>restricted</i>.
 * </p>
 * <br/><h4>How are they defined?</h4>
 * <p>AM operates Requirement in the form of {@link org.eclipse.passage.lic.api.requirements.LicensingRequirement} interface.</p>
 * <ul> A Requirements is
 *     <li>identification information of a <b>feature</b> under licensing</li>
 *     <li>restriction level (like <i>warn</i> or <i>fatal</i>) to be applied in case the terms of the <b>feature</b> usage are not met</li>
 * </ul>
 * <p>
 * <br/><h4>How to get them?</h4>
 * <p> Each implementation of {@link org.eclipse.passage.lic.api.requirements.RequirementResolver} interface,
 * registered properly at the <b>program</b> runtime, provides Requirements it is responsible for.
 * Each {@code Resolver} is designed to read a particular type of physical sources.
 * For example MANIFEST.MF, OSGi components manifest or other forms of annotations. </p>
 * <p>
 * <br/><h3>Conditions</h3>
 * <br/><h4>Where do they come from?</h4>
 * <p>{@code Condition}s are mined from a license information of sorts, like license file or floating server.
 * In other words, {@code Condition}s are more or less those things that a user payed his money for. </p>
 * <br/><h4>How are they defined?</h4>
 * <p>{@code Condition} notion is embodied in {@link org.eclipse.passage.lic.api.conditions.LicensingCondition} interface.
 * It exhaustively describes what feture and under which circumstances can be used. </p>
 * <ul>
 *     <li>feature identifier and version matcher</li>
 *     <li>general validity period </li>
 *     <li>type of the condition: is it time-based condition, condition for a specific hardware set, or somethings else;
 *     this type is crucial data, because defines the way the condition will be <i>evaluated</i> at the program runtime. </li>
 *     <li>all additional information necessary for the condition evaluation, is highly type-dependant. </li>
 * </ul>
 * <br/><h4>How to get them?</h4>
 * <p>There should be number of {@link org.eclipse.passage.lic.api.conditions.ConditionMiner}s instances at the program runtime,
 * collected in {@link org.eclipse.passage.lic.api.conditions.ConditionMinerRegistry}.
 * Each {@code Miner} quarries {@code Condition}s from it's particular source according to it's own logic.
 * When AM collects @{code Condition}s, it queries all registered {@code Miner}s and aggregate all gained results.
 * </p>
 *
 * @since 0.4.0
 */

package org.eclipse.passage.lic.api;