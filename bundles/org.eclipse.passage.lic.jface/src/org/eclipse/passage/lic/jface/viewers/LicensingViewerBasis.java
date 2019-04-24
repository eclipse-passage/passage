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
package org.eclipse.passage.lic.jface.viewers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.passage.lic.api.inspector.FeatureCase;
import org.eclipse.passage.lic.api.inspector.FeatureInspector;
import org.eclipse.passage.lic.api.requirements.LicensingRequirement;
import org.eclipse.passage.lic.api.restrictions.RestrictionVerdict;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;

public class LicensingViewerBasis implements LicensingViewerAdapter {

	private final FeatureInspector featureInspector;

	private final Map<String, FeatureCase> featureCases = new HashMap<>();

	public LicensingViewerBasis(FeatureInspector featureInspector) {
		this.featureInspector = featureInspector;
	}

	@Override
	public String getLabel(Object element) {
		return element == null ? "" : element.toString();//$NON-NLS-1$
	}

	@Override
	public String getLabel(Object element, int columnIndex) {
		return getLabel(element);
	}

	@Override
	public ImageDescriptor getImageDescriptor(Object element) {
		return null;
	}

	@Override
	public ImageDescriptor getImageDescriptor(Object element, int columnIndex) {
		return getImageDescriptor(element);
	}

	@Override
	public FontData getFont(Object element) {
		return null;
	}

	@Override
	public FontData getFont(Object element, int columnIndex) {
		return getFont(element);
	}

	@Override
	public RGB getBackground(Object element) {
		return null;
	}

	@Override
	public RGB getBackground(Object element, int columnIndex) {
		return getBackground(element);
	}

	@Override
	public RGB getForeground(Object element) {
		return null;
	}

	@Override
	public RGB getForeground(Object element, int columnIndex) {
		return getForeground(element);
	}

	@SuppressWarnings("resource")
	protected Iterable<LicensingRequirement> getRequirements(String featureIdentifier) {
		FeatureCase found = featureCases.computeIfAbsent(featureIdentifier,
				item -> featureInspector.inspectFeatures(featureIdentifier));
		return found.getRequirements();
	}

	@SuppressWarnings("resource")
	protected Iterable<RestrictionVerdict> getRestrictions(String featureIdentifier) {
		FeatureCase found = featureCases.computeIfAbsent(featureIdentifier,
				item -> featureInspector.inspectFeatures(featureIdentifier));
		return found.getRestrictions();
	}

	@Override
	public void dispose() {
		featureCases.values().forEach(item -> item.close());
	}

}
