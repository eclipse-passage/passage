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
import java.time.format.DateTimeFormatter;

import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.api.restrictions.Restriction;
import org.eclipse.passage.lic.internal.json.AcquiredExaminationCertificate;
import org.eclipse.passage.lic.internal.json.JsonObjectMapper;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CertificateTransportTest {

	private final CertificateTestDataSupplier supplier = new CertificateTestDataSupplier();

	@Test
	public void examinationNotPassed() {
		try {
			ZonedDateTime time = ZonedDateTime.now();
			String serialized = mapper().writeValueAsString(new AcquiredExaminationCertificate(supplier.permissions(),
					supplier.restrictions(), DateTimeFormatter.ISO_ZONED_DATE_TIME.format(time)));
			System.out.println(serialized);
			ExaminationCertificate certificate = mapper().readValue(serialized, ExaminationCertificate.class);
			assertEquals(false, certificate.examinationPassed());
			assertEquals(time, certificate.stamp());
			for (Restriction restriction : certificate.restrictions()) {
				assertEquals(supplier.restriction().product().version(), restriction.product().version());
				assertEquals(supplier.restriction().product().identifier(), restriction.product().identifier());
				assertEquals(supplier.restriction().reason().code(), restriction.reason().code());
				assertEquals(supplier.restriction().reason().explanation(), restriction.reason().explanation());
				assertEquals(supplier.restriction().unsatisfiedRequirement().feature().identifier(),
						restriction.unsatisfiedRequirement().feature().identifier());
				assertEquals(supplier.restriction().unsatisfiedRequirement().feature().name(),
						restriction.unsatisfiedRequirement().feature().name());
				assertEquals(supplier.restriction().unsatisfiedRequirement().feature().provider(),
						restriction.unsatisfiedRequirement().feature().provider());
				assertEquals(supplier.restriction().unsatisfiedRequirement().feature().version(),
						restriction.unsatisfiedRequirement().feature().version());
				assertEquals(supplier.restriction().unsatisfiedRequirement().source(),
						restriction.unsatisfiedRequirement().source());
				assertEquals(supplier.restriction().unsatisfiedRequirement().restrictionLevel().identifier(),
						restriction.unsatisfiedRequirement().restrictionLevel().identifier());
			}
			for (Permission permission : certificate.participants()) {
				assertEquals(supplier.permission().product().identifier(), permission.product().identifier());
				assertEquals(supplier.permission().product().version(), permission.product().version());
				assertEquals(supplier.condition().feature(), permission.condition().feature());
				assertEquals(supplier.condition().versionMatch().version(),
						permission.condition().versionMatch().version());
				assertEquals(supplier.condition().versionMatch().rule().identifier(),
						permission.condition().versionMatch().rule().identifier());
				assertEquals(supplier.condition().evaluationInstructions().expression(),
						permission.condition().evaluationInstructions().expression());
				assertEquals(supplier.condition().evaluationInstructions().type(),
						permission.condition().evaluationInstructions().type());
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void examinationPassed() {
		try {
			ZonedDateTime time = ZonedDateTime.now();
			String serialized = mapper().writeValueAsString(new AcquiredExaminationCertificate(supplier.permissions(),
					supplier.emptyRestrictions(), DateTimeFormatter.ISO_ZONED_DATE_TIME.format(time)));
			ExaminationCertificate certificate = mapper().readValue(serialized, ExaminationCertificate.class);
			assertEquals(true, certificate.examinationPassed());
		} catch (JsonProcessingException e) {
			fail();
		}
	}

	private ObjectMapper mapper() {
		return new JsonObjectMapper().get();
	}

}
