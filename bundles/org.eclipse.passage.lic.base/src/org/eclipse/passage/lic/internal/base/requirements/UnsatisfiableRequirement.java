package org.eclipse.passage.lic.internal.base.requirements;

import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.requirements.Feature;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel;

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
 */
@SuppressWarnings("restriction")
public final class UnsatisfiableRequirement implements Supplier<Requirement> {

	private final String description;
	private final Object source;

	/**
	 * @param description to be used as {@code feature name}, descriptive
	 * @param source      physical source of an configuration error, to be used as a
	 *                    {@code requirement definition source}
	 */
	public UnsatisfiableRequirement(String description, Object source) {
		this.description = description;
		this.source = source;
	}

	@Override
	public Requirement get() {
		Feature feature = new BaseFeature( //
				Long.toHexString(System.currentTimeMillis()), //
				"0.0.0", //$NON-NLS-1$
				description, //
				"Passage License Management"); //$NON-NLS-1$
		return new BaseRequirement(feature, new RestrictionLevel.Error(), source);
	}

}
