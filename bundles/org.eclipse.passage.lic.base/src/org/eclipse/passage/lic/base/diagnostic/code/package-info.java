/* *****************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/

/**
 * <ul>
 * General license cycle errors
 * <li>1xx - errors begotten by framework/services configuration or behavior,
 * begotten by wrong product-under-license passage-equipment. Configuration part
 * of these errors are to be fixed on a product-under-licensing development
 * side.
 * <ul>
 * <li>100 - no framework found. Critical.</li>
 * <li>101 - service cannot operate due to the insufficient configuration or
 * severe construction error</li>
 * <li>102 - no service of demanded type. Usually means error in the Framework
 * configuration. Thus, severe.</li>
 * <li>103 - service failed on a morsel. In most cases bearable.</li>
 * <li>104 - service failed because of infrastructure denial. Service cannot
 * finish operation because of IO error, connection troubles or other outer
 * obstacle.</li>
 * <li>110 - several implementations of Framework has been found. Critical.</li>
 * <li>151 - no requirements defined for a feature on in product as a whole -
 * the feature is unknown to Passage. In the worst case it is a sign of an error
 * in licensing demands declaration.</li>
 * </ul>
 * </li>
 * <li>2xx - reserved</li>
 * <li>3xx - reserved</li>
 * <li>4xx - errors begotten by user data (license internals, credentials,
 * settings, etc) - the things a user of a product-under-licensing can change,
 * usually by requesting/purchasing new valid license packs, accepting demanded
 * license agreements, etc.
 * <ul>
 * <li>401 - invalid license: a license cannot be applied as contains corrupted
 * data or data that are not readable with the active access cycle
 * configuration</li>
 * <li>402 - license is not started: start of a license validity period lies in
 * the future</li>
 * <li>403 - license expired: end of a license validity period lies in the
 * past</li>
 * <li>404 - license does not match: a condition specified is not met</li>
 * <li>405 - insufficient license coverage. Analytical error, reports that among
 * requested features there are some that do not have proper license
 * permissions.</li>
 * <li>406 - invalid content of attendant license package file (floating license
 * access file, etc)</li>
 * <li>407 - absence of an expected attendant license package file (floating
 * license access file, etc)</li>
 * <li>408 - a feature is not covered by a license (there is no licensing
 * conditions found for it), but has not-critical restriction level (for
 * instance @{code info}, {@code warning}) and thus can be used without
 * license.</li>
 * <li>409 - a license source does not provide relevant conditions (info)</li>
 * <li>410 - no licenses found in a source directory (info)</li>
 * <li>411 - no data of a particular type found (info)</li>
 * <li>412 - a licensing agreement has not been actively accepted by the product
 * user</li>
 * <li>413 - foreign license: suggested license file cannot be read with the
 * product/library key (bearable)
 * </ul>
 * </li>
 * <li>5xx - reserved</li>
 * <li>6xx - floating server response codes</li>
 * </ul>
 */
package org.eclipse.passage.lic.base.diagnostic.code;
