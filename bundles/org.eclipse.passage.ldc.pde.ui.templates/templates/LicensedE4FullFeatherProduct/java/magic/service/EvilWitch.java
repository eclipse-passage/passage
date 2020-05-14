package $packageName$.magic.service;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = { //
		"licensing.feature.identifier=EvilWitch", //
		"licensing.feature.name=Evil Witch", //
		"licensing.restriction.level=info", //
		"licensing.feature.version=13.4.1", //
		"licensing.feature.provider=Universe" //
})
public final class EvilWitch implements PrinceToFrogMagic {

	private MagicForces forces;

	@Override
	public void turn(Creature creature) {
		// the power of a witch is the ability to appeal to the universe's magic forces
		forces.turn(creature, Shape.FROG);
	}

	@Reference
	public void contractWithMagicForces(MagicForces universe) {
		this.forces = universe;
	}

	@Override
	public String purpose() {
		return "Turn to frog"; //$NON-NLS-1$
	}
}
