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
 * <h4> In a sentence</h4> <p>
 * AM serves the only purpose: a program under licence protection, been run, can ask AM,
 * if it is allowed to use a particular feature <i>now</i>,
 * and the answer will be more or less simple <i>yes</i> or <i>no</i>.
 * </p>
 * <p/>
 * <h4>In a paragraph</h4><p>
 * A program, been run, is asked by it's <b>user</b> to access a particular <b>feature</b>,
 * which has been implemented as a <i>feature under licence protection</i> by it's developers.<br/>
 * Here Passage invokes AM to define, can the user exploit the feature or not.
 * </p>
 *
 * <ul>
 * <li>AM uses <i>Requirement</i>s to define at runtime, which feature can be used under which circumstances.
 * These are given by this precise installation of the program under licence protection.
 * So the first thing AM does is finding out, is the actual program installation possesses any
 * <i>protect this feature</i> instructions in the first place.
 * To do this, AM appeals to all registered {@link org.eclipse.passage.lic.api.requirements.RequirementResolver}s and gets
 * a set of {@link org.eclipse.passage.lic.api.requirements.LicensingRequirement}s</li>
 * <li>Then AM needs to figure out, what the <b>licence</b> that the <b>user</b> acquired, allows to do.
 * AM goes to each of {@link org.eclipse.passage.lic.api.conditions.ConditionMiner}s it have at ones disposal
 * and gets set of {@link org.eclipse.passage.lic.api.conditions.LicensingCondition}s.
 * {@link org.eclipse.passage.lic.api.conditions.ConditionMiner}s look for them in <i>the licence</i> files,
 * on a <i>floating server</i> or mining sources of another types.
 * </li>
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
 *
 *
 * <h4>In details</h4>
 * <p></p>
 * <p>
 * Access manager:
 * input -> Requirements from runtime.
 * out -> Restriction ("no"). Positive solution is by default.
 * <p>
 * <p>
 * <p>
 * 1) Requirement - a unit under lic protection
 * == productFeatureId (featureId +version) additional data
 * <p>
 * <p>
 * 2) Condition (LIcensingCondition) - part of lic data, bought and send to you by operator.
 * "  по чевергам ноября на win32 ты можешь запускать эту фичу"
 * Connected with productFeatureId
 * <p>
 * 3) FeaturePermission = a condition evaluated against the current tuntime environment. Has binary answer: yes, you can use it; no, you cannot use it; And a timeout for "yes"
 * <p>
 * <p>
 * 4) PermissionExaminer.
 * Checks if all the given requirements can be covered by all the gained permissions. Return set of restrictions for those requirements, that have not been covered.
 * <p>
 * 5)  RestrictionVerdict:  "no". Positive verdict if the absence of verdict
 * <p>
 * =====================
 * Where conditions comes from
 * =====================
 * ConditionMiner (many instances) - all these guys are responsible for gaining granted Condition from all the source we support (file lic, floating server, etc)
 * <p>
 * =====
 * where requrements comes from
 * =====
 * RequirementResolver
 * A program under licensing comes to a particular place (like action), that is !implemented as the one that must be paid for!.
 * <p>
 * Here this !implementation! is asking: "can we use this (some feature id)"?
 * <p>
 * This request comes to AccessManager. This super-compoenents asks all registered RequrementResolvers: it there anybody who asked to protect this (the featureId)?
 * ReqResolvers (now) read theis sources (manifests, xmls) and returns the answer: Requirement instances. If there is none - the answer to the program is "yes"
 * <p>
 * <p>
 * ===
 * How conditions are evaludated against the current runtime
 * ===
 * LicCondition has a !type!: is it a hardware token permission.
 * <p>
 * Access manager survay all it's PermissionEmmiters to get the one Emitter is registered for this particular !type!. If there no Emitter - the AccessManagement will restrict the feture usage inally. Let's explore the case the Emitter for this !type! is found.
 * <p>
 * This emitter checks LicCondition ::conditionExpression: if each of them is satisfied at the current euntime environment. In case of success, it emitts a new FeaturePermission for this Condition.
 * <p>
 * <p>
 * <p>
 * <p>
 * ===
 * access manager frow
 * ===
 * input: productFeatureId: there is an action in the program under licensing, which !is implemented! as the one who must been bought. And the program flow comes to this action. And !the implementation! asks the AM: can we proceed?
 * <p>
 * out: let it go | no, you must do these (RestrictionVerdicts)
 * <p>
 * - ask RequirementResolvers (some outer registry), if anyone protects the feature at all. If any - they will return Requirement instance.
 * - ask ConditionMiners, if we have bought something for the feature. They return set of LicCondition
 * - evaluate LiConditions against the current runtime env, like grounding. All obsolete or unrelated LicConditions are filtered out here. Permissions are the output.
 * - examin is these permissions are enough to satisfy all the requirements.
 * - if enough - just let it go
 * - if not - set of RestrictionVerdict is the output.
 * <p>
 * <p>
 * ====
 * restruction executor
 * ===
 * verdict:
 * - info: message
 * - warning: pause the cycle (dialog), proceed
 * - error: block the scenario
 * - fatal: destroy the env
 * <p>
 * All executors are asked, no metadata is involved. To prohibit easing of executing, just
 *
 * @since 0.4.0
 */

package org.eclipse.passage.lic.api;