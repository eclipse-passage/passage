package org.eclipse.passage.lic.internal.api.requirements;

import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;

public interface ResolvedRequirementsRegistry extends Supplier<Registry<StringServiceId, ResolvedRequirements>> {

}
