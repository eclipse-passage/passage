package $packageName$;

import org.eclipse.jface.viewers.ListViewer;
import $packageName$.magic.Creatures;
import $packageName$.magic.Shape;
import $packageName$.magic.Universe;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

public final class CreaturesView {

	private final Creatures creatures;
	private final Shape shape;

	CreaturesView(Shape shape) {
		this.creatures = Universe.instance;
		this.shape = shape;
	}

	public void install(Composite parent) {
		ListViewer list = new ListViewer(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		list.setContentProvider(new CreaturesContentProvider(shape));
		list.setLabelProvider(new CreatureLabelProvider());
		list.setInput(creatures);
		list.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}

}

