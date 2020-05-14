package $packageName$.magic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import $packageName$.magic.service.Creature;
import $packageName$.magic.service.MagicForces;
import $packageName$.magic.service.Shape;
import org.osgi.service.component.annotations.Component;

@Component
public final class Universe implements Creatures, MagicForces {

	private final Set<Creature> creatures;
	private final List<MutationListener> listeners = new ArrayList<>();

	public Universe() {
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
		Creature mutated = new Creature(creature.name(), shape);
		creatures.add(mutated);
		fireMutationHappenned(creature, mutated);
	}

	private void fireMutationHappenned(Creature creature, Creature mutated) {
		listeners.forEach(l -> l.mutationHappenned(creature, mutated));
	}

}
