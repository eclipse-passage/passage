package $packageName$;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.model.application.MApplication;
import $packageName$.magic.Creatures;
import $packageName$.magic.service.Magic;
import $packageName$.magic.service.PrinceToFrogMagic;
import $packageName$.magic.service.Shape;
import org.eclipse.swt.widgets.Composite;

public class PrincesPart {

	@PostConstruct
	public void postConstruct(Composite parent, MApplication application) {
		Creatures creatures = application.getContext().get(Creatures.class);
		Magic magic = application.getContext().get(PrinceToFrogMagic.class);
		new CreaturesView(creatures, Shape.PRINCE, magic, "EvilWitch").install(parent); //$NON-NLS-1$
	}

}