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
package org.eclipse.passage.loc.internal.features.core;

import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.lic.emf.edit.DomainRegistryAccess;
import org.eclipse.passage.lic.runtime.features.FeaturesRegistry;
import org.osgi.service.component.annotations.Component;

@Component(property = { DomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + FeaturesRegistry.DOMAIN_NAME })
public final class FeatureSetClassifierInitializer implements ClassifierInitializer {
	@Override
	public String newObjectIdentifier() {
		return "new.feature.set"; //$NON-NLS-1$ ;
	}

	@Override
	public String newObjectName() {
		return "New Feature Set";
	}

	@Override
	public String newFileName() {
		return "new_feature_set"; //$NON-NLS-1$ ;
	}

	@Override
	public String newObjectTitle() {
		return "Feature Set";
	}

	@Override
	public String newObjectMessage() {
		return "New Feature Set";
	}

	@Override
	public String newFileMessage() {
		return "Please specify a file name to store feature data";
	}
}