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
package org.eclipse.passage.lic.base.tests;

import java.io.File;

import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.conditions.ConditionMiner;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.eclipse.passage.lic.runtime.requirements.LicensingRequirement;
import org.eclipse.passage.lic.runtime.requirements.RequirementResolver;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class LicensningBaseTests {

	/**
	 * Passed through maven-surefire-plugin configuration
	 */
	private static final String MVN_PROJECT_OUTPUT_PROPERTY = "project.build.directory"; //$NON-NLS-1$

	private static final String MVN_PROJECT_OUTPUT_VALUE = "target"; //$NON-NLS-1$

	@Rule
	public TemporaryFolder baseFolder = new TemporaryFolder(new File(resolveOutputDirName()));

	public static String resolveOutputDirName() {
		String userDir = System.getProperty("user.dir"); //$NON-NLS-1$
		String defaultValue = userDir + File.separator + MVN_PROJECT_OUTPUT_VALUE;
		String outDir = System.getProperty(MVN_PROJECT_OUTPUT_PROPERTY, defaultValue);
		return outDir;
	}

	public static RequirementResolver createRequirementResolver(Iterable<LicensingRequirement> resolved) {
		return new RequirementResolver() {

			@Override
			public Iterable<LicensingRequirement> resolveLicensingRequirements(LicensingConfiguration configuration) {
				return resolved;
			}
		};
	}

	public static ConditionMiner createConditionMiner(Iterable<LicensingCondition> mined) {
		return new ConditionMiner() {

			@Override
			public Iterable<LicensingCondition> extractLicensingConditions(LicensingConfiguration configuration) {
				return mined;
			}
		};
	}

}
