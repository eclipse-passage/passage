/*
  Copyright (c) 2018, 2020 ArSysOp
  <p>
  This program and the accompanying materials are made available under the
  terms of the Eclipse Public License 2.0 which is available at
  https://www.eclipse.org/legal/epl-2.0/.
  <p>
  SPDX-License-Identifier: EPL-2.0
  <p>
  Contributors:
  ArSysOp - initial API and implementation
 */

/**
 * <h1>Access Management</h1>
 * <h2> In a sentence</h2> <p>
 * {@link org.eclipse.passage.lic.api.access.AccessManager} serves the only purpose:
 * a program under licensing, been run, can ask AM,
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
 * <ol> The whole process from <i>can the user use the feature</i> query to an answer is called <i>access cycle</i>,
 * and it consists of 5 steps.
 * <li><b>requirements resolution</b><br/>
 * AM uses {@code Requirement}s to define at runtime, which <b>feature</b> in a <b>program</b> is under licensing.
 * These {@code Requirement}s are given by the precise installation of a <b>program under licensing</b>.
 * So the first thing AM does is finding out, is the actual program installation possesses any
 * <i>protect this feature</i> instructions in the first place.
 * To do so, AM appeals to all registered {@code RequirementResolver}s and gets a set of {@code Requirement}s
 * for further analysis</li>
 *
 * <li><b>conditions mining</b><br/>
 * Then AM needs to figure out, what the <b>license</b> that the <b>user</b> has, allows to do.
 * AM goes to each of {@code ConditionMiner}s it have at ones disposal
 * and gets set of {@code LicensingCondition}s.
 * {@code ConditionMiner}s look for them in <i>the license</i> files, on a <i>floating server</i> or mining sources of other types.
 * </li>
 *
 * <li><b>conditions evaluation</b><br/>
 * These {@code Condition}s are static definitions of which functions can be executed under which terms.
 * At a <b>program</b> runtime AM <i>evaluates</i> each {@code Condition} to a precise notion of
 * has this {@code Condition} been met at the <b>program</b> runtime or not.
 * The fact <i>condition terms are currently met</i> is expressed by a time-limited {@code Permission}.
 * So, at this step, for each {@code Condition} AM asks all {@code PermissionEmitter}s if someone can issue
 * a {@code Permission} for this {@code Condition}. </li>
 *
 * <li><b>permission examining</b><br/>
 * At this point AM has both actual {@code LicensingRequirement}s and valid {@code FeaturePermission}s.
 * This information is enough to make a decision about the start-up question:
 * can <b>the user</b> access to <b>the feature</b> <i>now</i>. <br/>
 * Here {@code PermissionExaminer} comes to play: it goes through
 * {@code FeaturePermission}s and checks if any of them satisfies some of {@code LicensingRequirement},
 * and then this latter ones are considered satisfied and do not participate in further actions.
 * All unsatisfied {@code LicensingRequirement}s left at the end of the work, are translated to a set of
 * {@code RestrictionVerdict}s. </li>
 *
 *
 * <li><b>restriction execution</b><br/>
 * If here AM has an empty set of {@code RestrictionVerdict}s (all {@code LicensingRequirement}s are satisfied),
 * then the <b>user</b> can access the <b>feature</b>. Otherwise, <i>some actions must be taken</i>,
 * and each {@code RestrictionVerdict} describes such an action.
 * {@code RestrictionExecutor} is the service responsible for these actions execution:
 * if can popup a warning, expose license dialog or even shut the whole program down. </li>
 * </ol>
 * <p>
 * <p>
 * <br/><h2>In details</h2>
 * <br/><h3>1. Requirements</h3>
 * <br/><h4>Where do they come from?</h4>
 * <p> They come from a <b>program</b> installation. {@code Requirement}s are declared by developers.
 * A {@code Requirement} says: a user can exploit this part (<b>feature</b>) of a <b>program</b> only if has a proper license,
 * unless the usage is <i>restricted</i>.
 * </p>
 * <br/><h4>How are they defined?</h4>
 * <p>AM operates Requirement in the form of {@link org.eclipse.passage.lic.api.requirements.LicensingRequirement} interface.</p>
 * <ul> A Requirements is
 *     <li>identification information of a <b>feature</b> under licensing</li>
 *     <li>restriction level (like <i>warn</i> or <i>fatal</i>) to be applied in case the terms of the <b>feature</b> usage are not met</li>
 * </ul>
 * <p>
 * <br/><h4>How to get them?</h4>
 * <p>
 *     Each implementation of {@link org.eclipse.passage.lic.api.requirements.RequirementResolver} interface,
 * registered properly at the <b>program</b> runtime, provides Requirements it is responsible for.
 * Each {@code Resolver} is designed to read a particular type of physical sources.
 * For example MANIFEST.MF, OSGi components manifest or other forms of annotations. </p>
 * <p>
 * <p>
 * <br/><h3>2. Conditions</h3>
 * <br/><h4>Where do they come from?</h4>
 * <p>{@code Condition}s are mined from a license information of sorts, like license file or floating server.
 * In other words, {@code Condition}s are more or less those things that a user payed his money for. </p>
 * <br/><h4>How are they defined?</h4>
 * <p>{@code Condition} notion is embodied in {@link org.eclipse.passage.lic.api.conditions.LicensingCondition} interface.
 * It exhaustively describes what feature and under which circumstances can be used. </p>
 * <ul>
 *     <li>feature identifier and version matcher</li>
 *     <li>general validity period </li>
 *     <li>type of the condition: is it time-based condition, condition for a specific hardware set, or somethings else;
 *     this type is crucial data, because defines the way the condition will be <i>evaluated</i> at the program runtime. </li>
 *     <li>all additional information necessary for the condition evaluation, is highly type-dependant. </li>
 * </ul>
 * <br/><h4>How to get them?</h4>
 * <p> There should be number of {@link org.eclipse.passage.lic.api.conditions.ConditionMiner}s instances at the program runtime,
 * collected in {@link org.eclipse.passage.lic.api.conditions.ConditionMinerRegistry}.
 * Each {@code Miner} quarries {@code Condition}s from it's particular source according to it's own logic.
 * When AM collects @{code Condition}s, it queries all registered {@code Miner}s and aggregate all gained results.
 * </p>
 * <p>
 * <p>
 * <br/><h3>3. Permissions</h3>
 * <br/><h4>Where do they come from?</h4>
 * <p>{@code Permission} are created by Passage at runtime as a result of a {@code Condition} <i>evaluation</i>.
 * A {@code Condition} declares, what terms must be met, and <i>evaluation</i> finds out
 * if these terms are actually  met under the current program runtime or not.
 * If met - a {@code Permission} for this {@code Condition} is <i>emitted</i>.</p>
 * <br/><h4>How are they defined?</h4>
 * <p>{@code Permission} concept is shaped to {@link org.eclipse.passage.lic.api.access.FeaturePermission} interface.
 * It points to the original {@code Condition} and has validity period. </p>
 * <br/><h4>>How to get them?</h4>
 * <p>An {@link org.eclipse.passage.lic.api.access.PermissionEmitter} interface
 * is responsible for {@code Condition}s evaluation and {@code Permission} emission logic implementation.
 * {@link org.eclipse.passage.lic.api.access.PermissionEmitterRegistry} aggregates all {@code Emitter}s
 * in the system. AM communicates with the {@code Registry} and then asks all registered
 * {@code Emitter}s to evaluate each of {@code Condition}s mined at the previous phase. </p>
 * <p>
 * <p>
 * <br/><h3>4. Permission examining</h3>
 * <br/><h4>Who does the examining?</h4>
 * <p>The process of examining must be implemented by an instance of {@link org.eclipse.passage.lic.api.access.PermissionExaminer},
 * registered in the system properly. </p>
 * <br/><h4>How the examining is performed?</h4>
 * <p>{@code PermissionExaminer} takes the {@code requirements} in one hand and the {@code permissions} in the other one
 * and carefully checks those {@code requirement}s one by one, if each one is satisfied by the given set of {@code permissions}.</p>
 * <p>A fully satisfied {@code requirement} falls out of the further consideration.
 * Thus, at the end of the day, all unsatisfied {@code requirement}s are found.
 * For each of those a {@link org.eclipse.passage.lic.api.restrictions.RestrictionVerdict} is created.</p>
 * <br/><h4>What is the result of the examining?</h4>
 * <p>A {@code restriction} describes exhaustively the case of license insufficiency.
 * Examining ends up with the set of such {@code restriction}s, empty in the perfect case. </p>
 * <p>Roughly speaking, the examine says if the given {@code permissions} suffice to cover the given {@code requirements} or not.</p>
 * <p>
 * <p>
 * <br/><h3>4. Restriction execution</h3>
 * <br/><h4>Who does the execution?</h4>
 * <p>There can be several instances of {@link org.eclipse.passage.lic.api.restrictions.RestrictionExecutor} interface. </p>
 * <br/><h4>How the execution is performed?</h4>
 * <p>For a single {@code restriction} each registered {@code executor} is involved.
 * There should be no meta-information analysis before asking an {@code executor} to do it's work.
 * Some {@code executor}s can appear to be ok with a particular {@code restriction},
 * others (potentially several) can have something to do about it.
 * Nevertheless, each registered {@code executor} will face each {@code restriction}.</p>
 * <p>Such a behaviour is designed to prevent avoiding execution by registering a phony "i'm totally ok"-executor. </p>
 * <br/><h4>What is the result of the execution?</h4>
 * <p>{@code Executor} chose a behaviour according to the {@code restriction level} of the {@code restriction} under processing.</p>
 * <ul> This can be, for example, one of the following
 *     <li>{@code info} means an information message somehow will be exposed to the <b>user</b> without pausing the current user's workflow</li>
 *     <li>{@code warn} will pause the user workflow and explain license insufficiency, for instance by a license dialog </li>
 *     <li>{@code error} totally prohibits to use the <b>feature</b> under licensing, so the current workflow will be stopped</li>
 *     <li>{@code fatal} prescribes to destroy the whole environment, close the <b>program</b></li>
 * </ul>
 *
 * @since 0.4.0
 */

package org.eclipse.passage.lic.api;
