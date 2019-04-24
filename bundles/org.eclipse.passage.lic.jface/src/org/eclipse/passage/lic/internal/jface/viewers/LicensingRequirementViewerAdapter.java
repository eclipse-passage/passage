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
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.jface.viewers.LicensingViewerBasis;
import org.eclipse.passage.lic.jface.viewers.RestrictionRepresenters;
import org.eclipse.passage.lic.runtime.inspector.FeatureInspector;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionVerdict;
import org.eclipse.swt.graphics.RGB;

public class LicensingRequirementViewerAdapter extends LicensingViewerBasis {

	protected static final int INDEX_STATUS = 0;
	protected static final int INDEX_PROVIDER = 1;
	protected static final int INDEX_NAME = 2;
	protected static final int INDEX_VERSION = 3;
	protected static final int INDEX_IDENTIFIER = 4;
	protected static final int INDEX_LEVEL = 5;

	public LicensingRequirementViewerAdapter(FeatureInspector inspectorOffice) {
		super(inspectorOffice);
	}

	@Override
	public String getLabel(Object element) {
		if (element instanceof LicensingRequirement) {
			LicensingRequirement requirement = (LicensingRequirement) element;
			return requirement.getFeatureName();
		}
		return super.getLabel(element);
	}

	@Override
	public String getLabel(Object element, int columnIndex) {
		if (element instanceof LicensingRequirement) {
			LicensingRequirement requirement = (LicensingRequirement) element;
			switch (columnIndex) {
			case INDEX_STATUS:
				return ""; //$NON-NLS-1$
			case INDEX_PROVIDER:
				return requirement.getFeatureProvider();
			case INDEX_NAME:
				return requirement.getFeatureName();
			case INDEX_VERSION:
				return requirement.getFeatureVersion();
			case INDEX_IDENTIFIER:
				return requirement.getFeatureIdentifier();
			case INDEX_LEVEL:
				return requirement.getRestrictionLevel();
			default:
				break;
			}
		}
		return super.getLabel(element, columnIndex);
	}

	@Override
	public ImageDescriptor getImageDescriptor(Object element, int columnIndex) {
		if (element instanceof LicensingRequirement) {
			LicensingRequirement requirement = (LicensingRequirement) element;
			Iterable<RestrictionVerdict> restrictions = getRestrictions(requirement.getFeatureIdentifier());
			String imageKey = RestrictionRepresenters.resolveImageKey(restrictions);
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
		if (element instanceof LicensingRequirement) {
			LicensingRequirement requirement = (LicensingRequirement) element;
			Iterable<RestrictionVerdict> restrictions = getRestrictions(requirement.getFeatureIdentifier());
			return RestrictionRepresenters.resolveRGB(restrictions);
		}
		return super.getBackground(element);
	}

}
