/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.loc.api.tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.passage.loc.internal.api.ZeroOneMany;
import org.junit.Test;

@SuppressWarnings("restriction")
public class ZeroOneManyTest {

	@Test
	public void testZero() throws Exception {
		ZeroOneMany<Object> zom = new ZeroOneMany<>(() -> Collections.emptyList());
		Optional<Object> zero = zom.choose(() -> null, null);
		assertEquals(false, zero.isPresent());
	}

	@Test
	public void testCreated() throws Exception {
		Optional<Object> single = Optional.of(new Object());
		ZeroOneMany<Object> zom = new ZeroOneMany<>(() -> Collections.emptyList());
		Optional<Object> one = zom.choose(() -> single, null);
		assertEquals(single, one);
	}

	@Test
	public void testOne() throws Exception {
		Object single = new Object();
		ZeroOneMany<Object> zom = new ZeroOneMany<>(() -> Collections.singleton(single));
		Optional<Object> one = zom.choose(null, null);
		assertEquals(single, one.get());
	}

	@Test
	public void testMany() throws Exception {
		Object first = new Object();
		Object second = new Object();
		ZeroOneMany<Object> zom = new ZeroOneMany<>(() -> Arrays.asList(first, second));
		Optional<Object> one = zom.choose(null, source -> select(source, 1));
		assertEquals(second, one.get());
	}

	private Optional<Object> select(Iterable<Object> source, int index) {
		Stream<Object> stream = StreamSupport.stream(source.spliterator(), false);
		return Optional.of(stream.collect(Collectors.toList()).get(index));
	}

}
