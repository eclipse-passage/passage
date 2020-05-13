package org.eclipse.passage.ldc.internal.pde.ui.templates.temp;

import javax.annotation.PostConstruct;

import org.eclipse.passage.ldc.internal.pde.ui.templates.temp.magic.Shape;
import org.eclipse.swt.widgets.Composite;

public class FrogsPart {

	@PostConstruct
	public void postConstruct(Composite parent) {
		new CreaturesView(Shape.FROG).install(parent);
	}

}