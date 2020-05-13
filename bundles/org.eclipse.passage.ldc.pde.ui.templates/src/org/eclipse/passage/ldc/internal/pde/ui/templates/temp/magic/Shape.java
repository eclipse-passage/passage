package org.eclipse.passage.ldc.internal.pde.ui.templates.temp.magic;

import java.util.function.Predicate;

public enum Shape {

	PRINCE, FROG, NONE;

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
