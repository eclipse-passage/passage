/*******************************************************************************
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
package org.eclipse.passage.lic.base.requirements;

import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.requirements.Feature;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.RestrictionLevel;

/**
 * <p>
 * In case of any system/configuration error on {@link Requirement} resolution
 * we cannot afford access cycle to stay with just an empty collection of
 * {@link Requirement} - that would mean there is no feature declared for
 * licensing.
 * </p>
 * <p>
 * Instead, if something went wrong, we beget an unsatisfiable requirement to
 * publish either a system failure or a sabotage attempt - to prevent access
 * cycle from successful completion.
 * </p>
 * 
 * @since 2.1
 */
public final class UnsatisfiableRequirement implements Supplier<Requirement> {

	private final String description;
	private final Object source;

	/**
	 * @param description to be used as {@code feature name}, descriptive
	 * @param source      physical source of an configuration error, to be used as a
	 *                    {@code requirement definition source}
	 */
	public UnsatisfiableRequirement(String description, Object source) {
		Objects.requireNonNull(description, "Description cannot be null"); //$NON-NLS-1$
		Objects.requireNonNull(source, "Source cannot be null"); //$NON-NLS-1$
		this.description = description;
		this.source = source;
	}

	@Override
	public Requirement get() {
		Feature feature = new BaseFeature( //
				Long.toHexString(System.currentTimeMillis()), //
				"0.0.0", //$NON-NLS-1$ // FIXME: rework on LicensingVersions elimination
				description, //
				"Passage License Management"); //$NON-NLS-1$ // FIXME: i18n
		return new BaseRequirement(feature, new RestrictionLevel.Error(), source);
	}

}
