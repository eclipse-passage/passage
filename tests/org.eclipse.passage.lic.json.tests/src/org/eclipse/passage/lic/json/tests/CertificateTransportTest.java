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
package org.eclipse.passage.lic.json.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.ZonedDateTime;
import java.util.Map;

import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.api.restrictions.Restriction;
import org.eclipse.passage.lic.internal.json.AcquiredExaminationCertificate;
import org.eclipse.passage.lic.internal.json.JsonObjectMapper;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("restriction")
public class CertificateTransportTest {

	@Test
	public void examinationNotPassed() {
		try {
			ZonedDateTime time = ZonedDateTime.now();
			CertificateTestData data = new CertificateTestData();
			String serialized = mapper().writeValueAsString(new AcquiredExaminationCertificate(
					Map.of(data.requirement(), data.permission()), data.restrictions(), time));
			ExaminationCertificate certificate = mapper().readValue(serialized, ExaminationCertificate.class);
			assertEquals(time, certificate.stamp());
			for (Restriction restriction : certificate.restrictions()) {
				assertEquals(data.restriction().product().version(), restriction.product().version());
				assertEquals(data.restriction().product().identifier(), restriction.product().identifier());
				assertEquals(data.restriction().reason().code(), restriction.reason().code());
				assertEquals(data.restriction().reason().explanation(), restriction.reason().explanation());
				assertEquals(data.restriction().unsatisfiedRequirement().feature().identifier(),
						restriction.unsatisfiedRequirement().feature().identifier());
				assertEquals(data.restriction().unsatisfiedRequirement().feature().name(),
						restriction.unsatisfiedRequirement().feature().name());
				assertEquals(data.restriction().unsatisfiedRequirement().feature().provider(),
						restriction.unsatisfiedRequirement().feature().provider());
				assertEquals(data.restriction().unsatisfiedRequirement().feature().version(),
						restriction.unsatisfiedRequirement().feature().version());
				assertEquals(data.restriction().unsatisfiedRequirement().source(),
						restriction.unsatisfiedRequirement().source());
				assertEquals(data.restriction().unsatisfiedRequirement().restrictionLevel().identifier(),
						restriction.unsatisfiedRequirement().restrictionLevel().identifier());
			}
			for (Requirement entry : certificate.satisfied()) {
				Permission permission = certificate.satisfaction(entry);
				assertEquals(data.permission().product().identifier(), permission.product().identifier());
				assertEquals(data.permission().product().version(), permission.product().version());
				assertEquals(data.condition().feature(), permission.condition().feature());
				assertEquals(data.condition().versionMatch().version(),
						permission.condition().versionMatch().version());
				assertEquals(data.condition().versionMatch().rule().identifier(),
						permission.condition().versionMatch().rule().identifier());
				assertEquals(data.condition().evaluationInstructions().expression(),
						permission.condition().evaluationInstructions().expression());
				assertEquals(data.condition().evaluationInstructions().type(),
						permission.condition().evaluationInstructions().type());
			}
		} catch (JsonProcessingException e) {
			fail();
		}
	}

	private ObjectMapper mapper() {
		return new JsonObjectMapper().get();
	}

}
