package org.eclipse.passage.lic.internal.base.conditions.mining;

import java.nio.file.Path;
import java.util.Collection;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionMiningException;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.base.i18n.BaseMessages;
import org.eclipse.passage.lic.internal.base.io.FileCollection;
import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;
import org.eclipse.passage.lic.internal.base.io.PathFromLicensedProduct;

@SuppressWarnings("restriction")
public abstract class LocalConditions implements MinedConditions {

	protected final StringServiceId id;
	private final KeyKeeperRegistry keys;
	private final StreamCodecRegistry codecs;

	protected LocalConditions(StringServiceId id, KeyKeeperRegistry keys, StreamCodecRegistry codecs) {
		this.id = id;
		this.keys = keys;
		this.codecs = codecs;
	}

	@Override
	public final StringServiceId id() {
		return id;
	}

	@Override
	public final Collection<Condition> all(LicensedProduct product) throws ConditionMiningException {
		return conditions(licenses(product));
	}

	private Collection<Path> licenses(LicensedProduct product) throws ConditionMiningException {
		try {
			return new FileCollection(new PathFromLicensedProduct(this::base, product),
					new PassageFileExtension.LicenseEncrypted()).get();
		} catch (LicensingException e) {
			throw new ConditionMiningException(//
					String.format(BaseMessages.getString("PathConditionMiner.failure"), id, product), e); //$NON-NLS-1$
		}
	}

	private Collection<Condition> conditions(Collection<Path> sources) throws ConditionMiningException {
		return null;

	}

	protected abstract Path base();

}
