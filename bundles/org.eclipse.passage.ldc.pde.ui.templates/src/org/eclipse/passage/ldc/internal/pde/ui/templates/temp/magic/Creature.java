package org.eclipse.passage.ldc.internal.pde.ui.templates.temp.magic;

public final class Creature {

	private final String name;
	private final Shape shape;

	Creature(String name, Shape shape) {
		this.name = name;
		this.shape = shape;
	}

	public String name() {
		return name;
	}

	public Shape shape() {
		return shape;
	}

	@Override
	public String toString() {
		return name + " the " + shape.name().toLowerCase(); //$NON-NLS-1$
	}

}
