package $packageName$;

import java.util.stream.Collectors;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import $packageName$.magic.Creatures;
import $packageName$.magic.Shape;

final class CreaturesContentProvider implements IStructuredContentProvider {
	
	private final Shape shape;

	CreaturesContentProvider(Shape shape) {
		this.shape = shape;
	}

	@Override
	public Object[] getElements(Object input) {
		return ((Creatures) input).creatures().stream() //
				.filter(creature -> shape.equals(creature.shape())) //
				.collect(Collectors.toList())//
				.toArray();
	}

}
