/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.internal.inspector.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.passage.lic.base.LicensingNamespaces;
import org.eclipse.passage.lic.runtime.inspector.RestrictionInpector;
import org.osgi.service.component.annotations.Component;

@Component
public class RestrictionLevelInspector implements RestrictionInpector {
	
	private final List<String> supportedLevels = new ArrayList<>();
	
	public RestrictionLevelInspector() {
		supportedLevels.add(LicensingNamespaces.ATTRIBUTE_LEVEL_WARN);
		supportedLevels.add(LicensingNamespaces.ATTRIBUTE_LEVEL_ERROR);
		supportedLevels.add(LicensingNamespaces.ATTRIBUTE_LEVEL_FATAL);
	}
	
	@Override
	public Iterable<String> getSupportedLevels() {
		return Collections.unmodifiableList(supportedLevels);
	}

	@Override
	public String getDefaultLevel() {
		return LicensingNamespaces.ATTRIBUTE_LEVEL_DEFAULT;
	}

}
