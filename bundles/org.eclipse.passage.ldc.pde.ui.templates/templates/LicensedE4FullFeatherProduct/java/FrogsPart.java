package $packageName$;

import javax.annotation.PostConstruct;

import $packageName$.magic.Shape;
import org.eclipse.swt.widgets.Composite;

public final class FrogsPart {
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		new CreaturesView(Shape.FROG).install(parent);
	}

}