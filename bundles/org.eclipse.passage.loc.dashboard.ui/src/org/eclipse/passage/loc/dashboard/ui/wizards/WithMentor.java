package org.eclipse.passage.loc.dashboard.ui.wizards;

import java.util.Optional;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecp.view.spi.model.VViewModelProperties;
import org.eclipse.passage.loc.internal.api.LocObjectMentorProperty;
import org.eclipse.passage.loc.internal.api.LocObjectMentorshipService;

public final class WithMentor {

	private final EObject target;
	private final IEclipseContext context;

	public WithMentor(EObject target, IEclipseContext context) {
		this.target = target;
		this.context = context;
	}

	public void inProperties(VViewModelProperties properties) {
		mentor().ifPresent(mentor -> properties.addNonInheritableProperty(new LocObjectMentorProperty().get(), mentor));
	}

	private Optional<String> mentor() {
		return Optional.ofNullable(context.get(LocObjectMentorshipService.class))//
				.flatMap(service -> service.mentor(target));
	}
}
