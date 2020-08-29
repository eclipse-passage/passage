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
package org.eclipse.passage.lbc.json;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.passage.lbc.internal.base.ReleaseReport;
import org.eclipse.passage.lbc.internal.base.ReleaseReport.Verdict;
import org.eclipse.passage.lic.internal.json.Json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

@SuppressWarnings("restriction")
public class ReleaseReportDeserializer extends StdDeserializer<ReleaseReport> {

	/**
	 * generated
	 */
	private static final long serialVersionUID = -2780539387686503904L;

	protected ReleaseReportDeserializer(Class<ReleaseReport> type) {
		super(type);
	}

	@Override
	public ReleaseReport deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode root = p.getCodec().readTree(p);
		ArrayNode nodes = (ArrayNode) root.get("verdicts"); //$NON-NLS-1$
		List<Verdict> parsed = StreamSupport.stream(nodes.spliterator(), false).map(this::verdict)
				.collect(Collectors.toList());
		return new ReleaseReport(parsed);
	}

	private Verdict verdict(JsonNode node) {
		return new Verdict(new Json.LicensingCondition().apply(node.get("condition")), //$NON-NLS-1$
				node.get("verdict").booleanValue()); //$NON-NLS-1$
	}

}
