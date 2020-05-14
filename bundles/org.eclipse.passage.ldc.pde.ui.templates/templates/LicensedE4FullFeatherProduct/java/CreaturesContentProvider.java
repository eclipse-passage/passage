package $packageName$;

import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import $packageName$.magic.Creatures;
import $packageName$.magic.service.Creature;
import $packageName$.magic.service.Shape;

final class CreaturesContentProvider implements IStructuredContentProvider {

	private final Predicate<Creature> shape;
	private final Comparator<Creature> comparator;

	CreaturesContentProvider(Shape shape) {
		this.shape = new Shape.Of(shape);
		this.comparator = (first, second) -> first.name().compareTo(second.name());
	}

	@Override
	public Object[] getElements(Object input) {
		return ((Creatures) input).creatures().stream() //
				.filter(shape) //
				.sorted(comparator) //
				.collect(Collectors.toList()) //
				.toArray();
	}

}
