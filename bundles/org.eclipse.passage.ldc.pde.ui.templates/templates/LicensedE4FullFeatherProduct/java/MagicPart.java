package $packageName$;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public final class MagicPart {
	@Inject
	private IEclipseContext context;

	@Inject
	public MagicPart() {

	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		Label label = new Label(parent, SWT.None);
		label.setText("Magic field: context is " + context); //$NON-NLS-1$

	}

}