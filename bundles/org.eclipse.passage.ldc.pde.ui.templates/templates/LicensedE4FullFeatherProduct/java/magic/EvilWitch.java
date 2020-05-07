package $packageName$.magic;

import org.osgi.service.component.annotations.Component;

@Component(property = { //
		"licensing.feature.identifier=EvilWitch", //
		"licensing.feature.name=Evil Witch", //
		"licensing.restriction.level=info", //
		"licensing.feature.version=13.4.1", //
		"licensing.feature.provider=Universe" //
})
public final class EvilWitch implements PrinceToFrogMagic {

}
