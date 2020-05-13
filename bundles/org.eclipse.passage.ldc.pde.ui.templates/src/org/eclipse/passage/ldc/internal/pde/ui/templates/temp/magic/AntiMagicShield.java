package org.eclipse.passage.ldc.internal.pde.ui.templates.temp.magic;

import java.util.HashSet;
import java.util.Set;

final class AntiMagicShield {

	private final Set<Creature> creatures = new HashSet<>();

	void cover(Creature creature) {
		creatures.add(creature);
	}

	void uncover(Creature creature) {
		creatures.remove(creature);
	}

	boolean covered(Creature creature) {
		return creatures.contains(creature);
	}

	int amont() {
		return creatures.size();
	}

}
