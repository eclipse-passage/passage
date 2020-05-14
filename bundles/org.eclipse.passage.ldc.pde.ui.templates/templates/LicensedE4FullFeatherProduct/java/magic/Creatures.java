package $packageName$.magic;

import java.util.Collections;
import java.util.Set;

import $packageName$.magic.service.Creature;

public interface Creatures {

	Set<Creature> creatures();

	void listenToMutation(MutationListener ear);

	public final class None implements Creatures {

		@Override
		public Set<Creature> creatures() {
			return Collections.emptySet();
		}

		@Override
		public void listenToMutation(MutationListener ear) {
			// do nothing: no mutation is going to happen here
		}

	}
}
