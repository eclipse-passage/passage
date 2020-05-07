package $packageName$.magic;

import org.osgi.service.component.annotations.Component;

@Component(property = { //
		"licensing.feature.identifier=GoodWitch", //
		"licensing.restriction.level=fatal" //
})
public final class GoodWitch implements FrogToPrinceMagic {

}
