package org.eclipse.passage.ldc.internal.pde.ui.templates.temp.magic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Universe implements Creatures, Magic {

	public static Universe instance = new Universe();
	private final Set<Creature> creatures;

	private final List<MutationListener> listeners = new ArrayList<>();

	private Universe() {
		creatures = Stream.of(//
				new Creature("Charming", Shape.PRINCE), //$NON-NLS-1$
				new Creature("Harold", Shape.PRINCE), //$NON-NLS-1$
				new Creature("Michigan J.", Shape.FROG), //$NON-NLS-1$
				new Creature("Ed Bighead", Shape.FROG), //$NON-NLS-1$
				new Creature("Kermit", Shape.FROG), //$NON-NLS-1$
				new Creature("Robin", Shape.FROG), //$NON-NLS-1$
				new Creature("Jason Funderburker", Shape.FROG), //$NON-NLS-1$
				new Creature("Kek of Egypt", Shape.FROG), //$NON-NLS-1$
				new Creature("Naveen", Shape.PRINCE))//$NON-NLS-1$
				.collect(Collectors.toSet());
	}

	@Override
	public Set<Creature> creatures() {
		return creatures;
	}

	@Override
	public void listenToMutation(MutationListener ear) {
		listeners.add(ear);
	}

	@Override
	public void turn(Creature creature, Shape shape) {
		creatures.remove(creature);
		creatures.add(new Creature(creature.name(), shape));
		fireMutationHappenned();
	}

	private void fireMutationHappenned() {
		listeners.forEach(MutationListener::mutationHappenned);
	}

}
