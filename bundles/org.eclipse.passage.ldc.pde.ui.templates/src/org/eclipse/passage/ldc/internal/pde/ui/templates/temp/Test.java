package org.eclipse.passage.ldc.internal.pde.ui.templates.temp;

import org.eclipse.passage.ldc.internal.pde.ui.templates.temp.magic.Shape;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public final class Test {

	public static void main(String[] a) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Magic"); //$NON-NLS-1$
		shell.setSize(900, 600);
		shell.setLayout(new GridLayout(1, true));
		Composite owner = new Composite(shell, SWT.BORDER);
		owner.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		owner.setLayout(new GridLayout(2, true));
		installUi(owner);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	private static void installMagic(Composite parent) {
		new MagicTools().install(parent);
	}

	private static void installUi(Composite parent) {
		new CreaturesView(Shape.PRINCE).install(parent);
		installMagic(parent);
		new CreaturesView(Shape.FROG).install(parent);
	}
}
