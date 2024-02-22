package org.eclipse.passage.loc.operator.internal.gear.services;

import java.util.HashMap;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.passage.loc.internal.api.EditingDomainSource;

public final class PassageEditingDomainSource implements EditingDomainSource {

	@Override
	public EditingDomain create() {
		return new AdapterFactoryEditingDomain(
				new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE), //
				new BasicCommandStack(), //
				new HashMap<>()//
		);
	}

}
