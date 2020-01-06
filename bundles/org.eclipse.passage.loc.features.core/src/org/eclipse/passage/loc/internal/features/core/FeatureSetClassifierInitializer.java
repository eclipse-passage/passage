/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.loc.internal.features.core;

import org.eclipse.passage.lic.emf.edit.ClassifierInitializer;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.features.core.Features;
import org.eclipse.passage.loc.internal.features.core.i18n.FeaturesCoreMessages;
import org.osgi.service.component.annotations.Component;

@Component(property = { EditingDomainRegistryAccess.PROPERTY_DOMAIN_NAME + '=' + Features.DOMAIN_NAME })
public final class FeatureSetClassifierInitializer implements ClassifierInitializer {
	@Override
	public String newObjectIdentifier() {
		return "new.feature.set"; //$NON-NLS-1$ ;
	}

	@Override
	public String newObjectName() {
		return FeaturesCoreMessages.FeatureSetClassifierInitializer_new_feature_set_name;
	}

	@Override
	public String newFileName() {
		return "new_feature_set"; //$NON-NLS-1$ ;
	}

	@Override
	public String newObjectTitle() {
		return FeaturesCoreMessages.FeatureSetClassifierInitializer_feature_set;
	}

	@Override
	public String newObjectMessage() {
		return FeaturesCoreMessages.FeatureSetClassifierInitializer_new_feature_set_message;
	}

	@Override
	public String newFileMessage() {
		return FeaturesCoreMessages.FeatureSetClassifierInitializer_new_file_message;
	}
}