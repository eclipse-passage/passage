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
package org.eclipse.passage.lic.internal.jface.viewers;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.passage.lic.api.requirements.LicensingRequirement;
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.jface.viewers.ConditionRepresenters;
import org.eclipse.passage.lic.jface.viewers.LicensingViewerBasis;
import org.eclipse.passage.lic.jface.viewers.RequirementLabels;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.eclipse.passage.lic.runtime.inspector.FeatureInspector;
import org.eclipse.swt.graphics.RGB;

public class LicensingConditionViewerAdapter extends LicensingViewerBasis {

	protected static final int INDEX_STATUS = 0;
	protected static final int INDEX_PROVIDER = 1;
	protected static final int INDEX_NAME = 2;
	protected static final int INDEX_IDENTIFIER = 3;
	protected static final int INDEX_VALID_FROM = 4;
	protected static final int INDEX_VALID_UNTIL = 5;
	protected static final int INDEX_MATCH_VERSION = 6;
	protected static final int INDEX_MATCH_RULE = 7;
	protected static final int INDEX_CONDITION_TYPE = 8;

	public LicensingConditionViewerAdapter(FeatureInspector inspectorOffice) {
		super(inspectorOffice);
	}

	@Override
	public String getLabel(Object element) {
		if (element instanceof LicensingCondition) {
			LicensingCondition condition = (LicensingCondition) element;
			return condition.getFeatureIdentifier();
		}
		return super.getLabel(element);
	}

	@Override
	public String getLabel(Object element, int columnIndex) {
		if (element instanceof LicensingCondition) {
			LicensingCondition condition = (LicensingCondition) element;
			String featureIdentifier = condition.getFeatureIdentifier();
			Iterable<LicensingRequirement> requirements = getRequirements(featureIdentifier);
			switch (columnIndex) {
			case INDEX_STATUS:
				return ""; //$NON-NLS-1$
			case INDEX_PROVIDER:
				return RequirementLabels.getFeatureProvider(requirements);
			case INDEX_NAME:
				return RequirementLabels.getFeatureName(requirements);
			case INDEX_IDENTIFIER:
				return condition.getFeatureIdentifier();
			case INDEX_VALID_FROM:
				return LicensingProperties.DATE_FORMAT.format(condition.getValidFrom());
			case INDEX_VALID_UNTIL:
				return LicensingProperties.DATE_FORMAT.format(condition.getValidUntil());
			case INDEX_MATCH_VERSION:
				// FIXME: human readable
				return condition.getMatchVersion();
			case INDEX_MATCH_RULE:
				// FIXME: human readable
				return condition.getMatchRule();
			case INDEX_CONDITION_TYPE:
				// FIXME: human readable
				return condition.getConditionType();
			default:
				break;
			}
		}
		return super.getLabel(element, columnIndex);
	}

	@Override
	public ImageDescriptor getImageDescriptor(Object element, int columnIndex) {
		if (element instanceof LicensingCondition) {
			LicensingCondition condition = (LicensingCondition) element;
			String imageKey = ConditionRepresenters.resolveImageKey(condition);
			switch (columnIndex) {
			case INDEX_STATUS:
				return LicensingImages.getImageDescriptor(imageKey);
			default:
				break;
			}
		}
		return super.getImageDescriptor(element, columnIndex);
	}

	@Override
	public RGB getBackground(Object element) {
		if (element instanceof LicensingCondition) {
			LicensingCondition condition = (LicensingCondition) element;
			return ConditionRepresenters.resolveRGB(condition);
		}
		return super.getBackground(element);
	}

}
