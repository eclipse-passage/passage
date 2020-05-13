package org.eclipse.passage.ldc.internal.pde.ui.templates.temp;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.passage.ldc.internal.pde.ui.templates.temp.magic.Creatures;
import org.eclipse.passage.ldc.internal.pde.ui.templates.temp.magic.Shape;
import org.eclipse.passage.ldc.internal.pde.ui.templates.temp.magic.Universe;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public final class CreaturesView {

	private final Creatures creatures;
	private final Shape shape;

	CreaturesView(Shape shape) {
		this.creatures = Universe.instance;
		this.shape = shape;
	}

	public void install(Composite parent) {
		ListViewer list = installControl(parent);
		installDynamics(list);
	}

	private ListViewer installControl(Composite parent) {
		ListViewer list = new ListViewer(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		list.setContentProvider(new CreaturesContentProvider(shape));
		list.setLabelProvider(new CreatureLabelProvider());
		list.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		list.getControl().setFont(font(parent));
		list.setInput(creatures);
		return list;
	}

	private Font font(Control parent) {
		return new Font(parent.getFont().getDevice(), "Sans serif", 22, SWT.NORMAL); //$NON-NLS-1$
	}

	private void installDynamics(ListViewer list) {
		creatures.listenToMutation(() -> list.refresh(true));
	}

}
