package $packageName$;

import javax.annotation.PostConstruct;

import $packageName$.magic.Shape;
import org.eclipse.swt.widgets.Composite;

public final class PrincesPart {
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		new CreaturesView(Shape.PRINCE).install(parent);
	}
	
}