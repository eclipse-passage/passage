package $packageName$.magic;

import $packageName$.magic.service.Creature;

@FunctionalInterface
public interface MutationListener {

	void mutationHappenned(Creature original, Creature mutated);

}
