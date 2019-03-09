package org.eclipse.passage.lic.internal.equinox.conditions;

import java.nio.file.Path;
import java.util.Map;

import org.eclipse.passage.lic.base.conditions.BasePathConditionMiner;
import org.eclipse.passage.lic.equinox.io.EquinoxPaths;
import org.eclipse.passage.lic.runtime.ConditionMiner;
import org.eclipse.passage.lic.runtime.io.LicensingConditionTransport;
import org.eclipse.passage.lic.runtime.registry.KeyKeeperRegistry;
import org.eclipse.passage.lic.runtime.registry.StreamCodecRegistry;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component
public class InstallLocationConditionMiner extends BasePathConditionMiner implements ConditionMiner {

	@Reference
	@Override
	public void bindKeyKeeperRegistry(KeyKeeperRegistry registry) {
		super.bindKeyKeeperRegistry(registry);
	}

	@Override
	public void unbindKeyKeeperRegistry(KeyKeeperRegistry registry) {
		super.unbindKeyKeeperRegistry(registry);
	}

	@Reference
	@Override
	public void bindStreamCodecRegistry(StreamCodecRegistry registry) {
		super.bindStreamCodecRegistry(registry);
	}

	@Override
	public void unbindStreamCodecRegistry(StreamCodecRegistry registry) {
		super.unbindStreamCodecRegistry(registry);
	}

	@Override
	@Reference(cardinality = ReferenceCardinality.AT_LEAST_ONE)
	public void bindConditionDescriptorTransport(LicensingConditionTransport transport,
			Map<String, Object> properties) {
		super.bindConditionDescriptorTransport(transport, properties);
	}

	@Override
	public void unbindConditionDescriptorTransport(LicensingConditionTransport transport,
			Map<String, Object> properties) {
		super.unbindConditionDescriptorTransport(transport, properties);
	}

	@Override
	protected Path getBasePath() {
		return EquinoxPaths.resolveInstallBasePath();
	}

}
