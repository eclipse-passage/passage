package $packageName$.magic.service;

import java.util.function.Predicate;

public enum Shape {

	PRINCE, FROG;

	public static final class Of implements Predicate<Creature> {
		private final Shape shape;

		public Of(Shape shape) {
			this.shape = shape;
		}

		@Override
		public boolean test(Creature creature) {
			return creature.shape().equals(shape);
		}

	}

}
