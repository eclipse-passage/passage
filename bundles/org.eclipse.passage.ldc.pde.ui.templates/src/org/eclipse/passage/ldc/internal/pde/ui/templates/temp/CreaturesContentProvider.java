package org.eclipse.passage.ldc.internal.pde.ui.templates.temp;

import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.passage.ldc.internal.pde.ui.templates.temp.magic.Creature;
import org.eclipse.passage.ldc.internal.pde.ui.templates.temp.magic.Creatures;
import org.eclipse.passage.ldc.internal.pde.ui.templates.temp.magic.Shape;

final class CreaturesContentProvider implements IStructuredContentProvider {

	private final Predicate<Creature> shape;

	CreaturesContentProvider(Shape shape) {
		this.shape = new Shape.Of(shape);
	}

	@Override
	public Object[] getElements(Object input) {
		return ((Creatures) input).creatures().stream() //
				.filter(shape) //
				.collect(Collectors.toList()) //
				.toArray();
	}

}
