/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.base.version;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.version.SemanticVersion;

/**
 * <p>
 * Builds a {@linkplain SemanticVersion} instance of the given {@code source}.
 * </p>
 * <p>
 * Provides the famous {@code default} version in case of any {@code source}
 * inconsistency.
 * </p>
 * 
 * @since 2.1
 */
public final class SafeVersion extends BaseVersion {

	private final Object source;
	private static String seprators = "\\."; //$NON-NLS-1$

	public SafeVersion(Object source) {
		Objects.requireNonNull(source, "SafeVersion::source"); //$NON-NLS-1$
		this.source = source;
	}

	@Override
	public String value() {
		return semantic().toString();
	}

	@Override
	public SemanticVersion semantic() {
		String version = source.toString().trim();
		List<String> segments = Arrays.stream(version.split(seprators))//
				.map(String::trim)//
				.collect(Collectors.toList());
		Optional<String> qualifier = (segments.size() > 3) //
				? Optional.of(segments.get(3)) //
				: Optional.empty();
		return build( //
				extractSegment(segments, 0), // major
				extractSegment(segments, 1), // minor
				extractSegment(segments, 2), // service
				qualifier);
	}

	private int extractSegment(List<String> split, int index) {
		if (split.size() > index) {
			try {
				return Integer.parseInt(split.get(index));
			} catch (Exception e) {
				// ignore
			}
		}
		return 0;
	}

	private SemanticVersion build(int major, int minor, int service, Optional<String> qualifier) {
		return new BaseSemanticVersion(//
				major, //
				minor, //
				service, //
				qualifier.isPresent() && (major + minor + service > 0) ? qualifier : Optional.empty());
	}

}
