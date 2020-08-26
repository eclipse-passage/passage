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
package org.eclipse.passage.lic.internal.json;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

@SuppressWarnings("restriction")
public final class CertificateDeserializer extends StdDeserializer<ExaminationCertificate> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4764283882751022188L;

	CertificateDeserializer(Class<ExaminationCertificate> vc) {
		super(vc);
	}

	@Override
	public ExaminationCertificate deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode root = p.getCodec().readTree(p);
		ArrayNode permissions = (ArrayNode) root.get("permissions"); //$NON-NLS-1$
		ArrayNode restrictions = (ArrayNode) root.get("restrictions"); //$NON-NLS-1$
		ArrayNode requirements = (ArrayNode) root.get("requirements"); //$NON-NLS-1$
		return new AcquiredExaminationCertificate(
				satisfied(collection(requirements, new Json.LicensingRequirement()),
						collection(permissions, new Json.LicensingPermission())),
				collection(restrictions, new Json.LicensingRestriction()), new Json.Date().apply(root.get("stamp"))); //$NON-NLS-1$
	}

	private <T, U extends Json<T>> List<T> collection(ArrayNode serialized, U deserializer) {
		return StreamSupport.stream(serialized.spliterator(), false) //
				.map(deserializer) //
				.collect(Collectors.toList());
	}

	private Map<Requirement, Permission> satisfied(List<Requirement> keys, List<Permission> values) {
		return IntStream.range(0, keys.size()).boxed() //
				.collect(Collectors.toMap(keys::get, values::get));
	}

}
