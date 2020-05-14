package $packageName$.magic.service;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = { //
		"licensing.feature.identifier=GoodWitch", //
		"licensing.restriction.level=fatal" //
})
public final class GoodWitch implements FrogToPrinceMagic {

	private MagicForces forces;

	@Override
	public void turn(Creature creature) {
		// the power of a witch is the ability to appeal to the universe's magic forces
		forces.turn(creature, Shape.PRINCE);
	}

	@Reference
	public void contractWithForces(MagicForces universe) {
		this.forces = universe;
	}

	@Override
	public String purpose() {
		return "Turn to prince"; //$NON-NLS-1$
	}

}
