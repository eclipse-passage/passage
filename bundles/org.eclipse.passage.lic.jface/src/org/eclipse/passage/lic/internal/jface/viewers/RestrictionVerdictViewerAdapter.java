/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.jface.viewers;

import java.util.Optional;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.passage.lic.api.inspector.FeatureInspector;
import org.eclipse.passage.lic.api.requirements.LicensingRequirement;
import org.eclipse.passage.lic.api.restrictions.RestrictionVerdict;
import org.eclipse.passage.lic.internal.jface.i18n.JFaceMessages;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.jface.viewers.LicensingViewerBasis;
import org.eclipse.passage.lic.jface.viewers.RestrictionRepresenters;
import org.eclipse.swt.graphics.RGB;

public class RestrictionVerdictViewerAdapter extends LicensingViewerBasis {

	protected static final int INDEX_STATUS = 0;
	protected static final int INDEX_PROVIDER = 1;
	protected static final int INDEX_NAME = 2;
	protected static final int INDEX_VERSION = 3;
	protected static final int INDEX_VERDICT = 4;

	public RestrictionVerdictViewerAdapter(FeatureInspector inspectorOffice) {
		super(inspectorOffice);
	}

	@Override
	public String getLabel(Object element) {
		return Optional.ofNullable(element)//
				.filter(RestrictionVerdict.class::isInstance)//
				.map(RestrictionVerdict.class::cast)//
				.map(RestrictionVerdict::getLicensingRequirement)//
				.map(LicensingRequirement::getFeatureName)//
				.orElseGet(() -> super.getLabel(element));
	}

	@Override
	public String getLabel(Object element, int columnIndex) {
		if (element instanceof RestrictionVerdict) {
			RestrictionVerdict verdict = (RestrictionVerdict) element;
			LicensingRequirement requirement = verdict.getLicensingRequirement();
			switch (columnIndex) {
			case INDEX_STATUS:
				return ""; //$NON-NLS-1$
			case INDEX_PROVIDER:
				return requirement.getFeatureProvider();
			case INDEX_NAME:
				return requirement.getFeatureName();
			case INDEX_VERSION:
				return requirement.getFeatureVersion();
			case INDEX_VERDICT:
				return getVerdictMessage(verdict);
			default:
				break;
			}
		}
		return super.getLabel(element, columnIndex);
	}

	private String getVerdictMessage(RestrictionVerdict verdict) {
		int code = verdict.getRestrictionCode();
		if (code == 0) {
			return JFaceMessages.RestrictionVerdictViewerAdapter_msg_valid_license;
		}
		if (code == 401) {
			return JFaceMessages.RestrictionVerdictViewerAdapter_msg_invalid_license;
		}
		if (code == 416) {
			return JFaceMessages.RestrictionVerdictViewerAdapter_msg_not_started;
		}
		if (code == 417) {
			return JFaceMessages.RestrictionVerdictViewerAdapter_msg_expired;
		}
		return String.valueOf(code);
	}

	@Override
	public ImageDescriptor getImageDescriptor(Object element, int columnIndex) {
		if (INDEX_STATUS == columnIndex) {
			return Optional.ofNullable(element)//
					.filter(RestrictionVerdict.class::isInstance)//
					.map(RestrictionVerdict.class::cast)//
					.map(RestrictionRepresenters::resolveImageKey)//
					.map(LicensingImages::getImageDescriptor)//
					.orElseGet(() -> super.getImageDescriptor(element, columnIndex));
		}
		return super.getImageDescriptor(element, columnIndex);
	}

	@Override
	public RGB getBackground(Object element) {
		return Optional.ofNullable(element)//
				.filter(RestrictionVerdict.class::isInstance)//
				.map(RestrictionVerdict.class::cast)//
				.map(RestrictionRepresenters::resolveRGB)//
				.orElseGet(() -> super.getBackground(element));
	}

}
