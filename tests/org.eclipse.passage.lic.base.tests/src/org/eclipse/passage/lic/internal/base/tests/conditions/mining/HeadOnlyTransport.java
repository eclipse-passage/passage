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
package org.eclipse.passage.lic.internal.base.tests.conditions.mining;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationInstructions;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.api.conditions.ValidityPeriod;
import org.eclipse.passage.lic.internal.api.conditions.VersionMatch;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.base.conditions.BaseCondition;
import org.eclipse.passage.lic.internal.base.conditions.BaseEvaluationInstructions;
import org.eclipse.passage.lic.internal.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.internal.base.conditions.BaseVersionMatch;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleDefault;
import org.eclipse.passage.lic.internal.base.tests.conditions.mining.LocalConditionsTest.Spy;

@SuppressWarnings("restriction")
final class HeadOnlyTransport implements ConditionTransport {

	private final Spy spy;

	HeadOnlyTransport(Spy spy) {
		this.spy = spy;
	}

	@Override
	public ContentType id() {
		return new ContentType.Xml(); // to be bound by mining - it only uses this content type
	}

	@Override
	public Collection<Condition> read(InputStream input) throws IOException {
		Collection<Condition> conditions = new ArrayList<>();
		try (LineNumberReader lines = new LineNumberReader(new InputStreamReader(input))) {
			for (String line = lines.readLine(); line != null; line = lines.readLine()) {
				spy.transportLine();
				conditions.add(new BaseCondition(//
						line.trim(), commonVersion(), commonPeriod(), commonInstructions()));
			}
		}
		return conditions;
	}

	@Override
	public void write(Collection<Condition> conditions, OutputStream output) throws IOException {
		throw new UnsupportedOperationException();
	}

	private VersionMatch commonVersion() {
		return new BaseVersionMatch("1.0.0", new MatchingRuleDefault()); //$NON-NLS-1$
	}

	private ValidityPeriod commonPeriod() {
		return new BaseValidityPeriodClosed(new Date(12345678), new Date(23456789));
	}

	private EvaluationInstructions commonInstructions() {
		return new BaseEvaluationInstructions(new EvaluationType.Hardware(), "none"); //$NON-NLS-1$
	}

}
