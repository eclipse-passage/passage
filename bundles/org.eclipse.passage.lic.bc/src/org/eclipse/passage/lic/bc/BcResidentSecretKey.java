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
package org.eclipse.passage.lic.bc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.jcajce.JcaPGPSecretKeyRingCollection;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.internal.bc.i18n.BcMessages;

/**
 * Look for a {@code secret key} in the given {@code residence} input stream,
 * which is supposed to be begotten by a key ring file.
 */
final class BcResidentSecretKey {

	private final InputStream residence;
	private final String owner;

	BcResidentSecretKey(InputStream residence, String owner) {
		this.residence = residence;
		this.owner = owner;
	}

	PGPSecretKey get() throws LicensingException {
		return stream(rings()) //
				.map(PGPSecretKeyRing::getSecretKeys)//
				.map(this::find) //
				.filter(Optional::isPresent)//
				.map(Optional::get) //
				.findFirst()//
				.orElseThrow(() -> //
				new LicensingException(BcMessages.getString("BcStreamCodec_encode_error_no_key"))); //$NON-NLS-1$
	}

	private Iterator<PGPSecretKeyRing> rings() throws LicensingException {
		try (InputStream decoder = PGPUtil.getDecoderStream(residence)) {
			return new JcaPGPSecretKeyRingCollection(decoder).getKeyRings();
		} catch (IOException | PGPException e) {
			throw new LicensingException(BcMessages.getString("BcStreamCodec_encode_error_no_key"), e); //$NON-NLS-1$
		}
	}

	private Optional<PGPSecretKey> find(Iterator<PGPSecretKey> keys) {
		return stream(keys)//
				.filter(PGPSecretKey::isSigningKey) //
				.filter(this::signedByOwner)//
				.findFirst();
	}

	private boolean signedByOwner(PGPSecretKey key) {
		return stream(key.getUserIDs())//
				.anyMatch(signer -> Objects.equals(signer, owner));
	}

	private <T> Stream<T> stream(Iterator<T> iterator) {
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
	}

}
