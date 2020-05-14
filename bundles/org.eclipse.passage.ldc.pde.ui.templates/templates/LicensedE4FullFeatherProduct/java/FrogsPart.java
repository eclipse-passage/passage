package $packageName$;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.model.application.MApplication;
import $packageName$.magic.Creatures;
import $packageName$.magic.service.FrogToPrinceMagic;
import $packageName$.magic.service.Magic;
import $packageName$.magic.service.Shape;
import org.eclipse.swt.widgets.Composite;

public class FrogsPart {

	@PostConstruct
	public void postConstruct(Composite parent, MApplication application) {
		Creatures creatures = application.getContext().get(Creatures.class);
		Magic magic = application.getContext().get(FrogToPrinceMagic.class);
		new CreaturesView(creatures, Shape.FROG, magic, "GoodWitch").install(parent); //$NON-NLS-1$
	}

}