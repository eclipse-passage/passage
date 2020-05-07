package $packageName$.magic;

import java.util.Collections;
import java.util.Set;

public interface Creatures {

	Set<Creature> creatures();

	public final class None implements Creatures {

		@Override
		public Set<Creature> creatures() {
			return Collections.emptySet();
		}

	}
}
