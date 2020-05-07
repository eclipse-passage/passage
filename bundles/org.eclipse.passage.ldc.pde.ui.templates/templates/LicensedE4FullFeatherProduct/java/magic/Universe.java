package $packageName$.magic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Universe implements Creatures {

	private final Set<Creature> creatures;

	public static Universe instance = new Universe();

	private Universe() {
		creatures = Stream.of(//
				new Creature("Charming", Shape.PRINCE), //$NON-NLS-1$
				new Creature("Harold", Shape.FROG))//$NON-NLS-1$
				.collect(Collectors.toSet());
	}

	@Override
	public Set<Creature> creatures() {
		return creatures;
	}

}
