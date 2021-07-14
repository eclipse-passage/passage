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

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.version.SemanticVersion;

/**
 * 
 * @since 2.1
 */
public final class BaseSemanticVersion implements SemanticVersion {

	private final int major;
	private final int minor;
	private final int service;
	private final Optional<String> qualifier;

	public BaseSemanticVersion(int major, int minor, int service, Optional<String> qualifier) {
		Objects.requireNonNull(qualifier, "SemanticVersion::qualifier"); //$NON-NLS-1$
		this.major = major;
		this.minor = minor;
		this.service = service;
		this.qualifier = qualifier;
	}

	public BaseSemanticVersion(int major, int minor, int service, String qualifier) {
		this(major, minor, service, Optional.ofNullable(qualifier));
	}

	public BaseSemanticVersion(int major, int minor, int service) {
		this(major, minor, service, Optional.empty());
	}

	@Override
	public int major() {
		return major;
	}

	@Override
	public int minor() {
		return minor;
	}

	@Override
	public int service() {
		return service;
	}

	@Override
	public boolean hasQualifier() {
		return qualifier.isPresent();
	}

	@Override
	public String qualifier() {
		return qualifier.get();
	}

	@Override
	public String toString() {
		String numerical = new NumericalVersion(this).get().stream()//
				.map(i -> i.toString())//
				.collect(Collectors.joining(".")); //$NON-NLS-1$
		return numerical + (hasQualifier() ? '.' + qualifier() : ""); //$NON-NLS-1$
	}

}
