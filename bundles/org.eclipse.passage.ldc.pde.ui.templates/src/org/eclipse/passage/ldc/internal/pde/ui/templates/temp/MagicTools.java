package org.eclipse.passage.ldc.internal.pde.ui.templates.temp;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.passage.ldc.internal.pde.ui.templates.temp.magic.Creature;
import org.eclipse.passage.ldc.internal.pde.ui.templates.temp.magic.Magic;
import org.eclipse.passage.ldc.internal.pde.ui.templates.temp.magic.Shape;
import org.eclipse.passage.ldc.internal.pde.ui.templates.temp.magic.Universe;
import org.eclipse.passage.lic.internal.equinox.EquinoxPassage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

@SuppressWarnings("restriction")
public final class MagicTools implements ISelectionChangedListener {

	private final Magic magic;
	private Button frog;
	private Button prince;

	public MagicTools() {
		magic = Universe.instance;
	}

	public void install(Composite parent) {
		Composite owner = owner(parent);
		installFiller(owner);
		installEvilMagic(owner);
		installGoodMagic(owner);
		installFiller(owner);
		updateEnablement(Shape.NONE);
	}

	private Composite owner(Composite parent) {
		Composite owner = new Composite(parent, SWT.NONE);
		owner.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, true));
		owner.setLayout(new GridLayout(1, false));
		return owner;
	}

	private void installFiller(Composite parent) {
		new Label(parent, SWT.NONE).setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, true));
	}

	private void installEvilMagic(Composite parent) {
		frog = button(parent);
		frog.setText("To frog"); //$NON-NLS-1$
		// frog.addSelectionListener(SelectionListener.widgetSelectedAdapter( e->
		// magic.turn(creature, shape)));
	}

	private void installGoodMagic(Composite parent) {
		prince = button(parent);
		prince.setText("To prince"); //$NON-NLS-1$
	}

	private Button button(Composite parent) {
		Button turn = new Button(parent, SWT.PUSH);
		turn.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
		return button(parent);
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		Creature target = (Creature) ((IStructuredSelection) event.getSelection()).getFirstElement();
		updateEnablement(target.shape());
	}

	private void updateEnablement(Shape shape) {
		prince.setEnabled(shape == Shape.FROG && goodWitchIsPayed());
		frog.setEnabled(shape == Shape.PRINCE && evilWitchIsPayed());
	}

	/**
	 * Check if feature with {@code licensing.feature.identifier}
	 * <i>"EvilWitch"</i>, declared by {@code magic.EvilWitch} OSGi component is
	 * payed for and Passage allows it be to used.
	 */
	private boolean evilWitchIsPayed() {
		return new EquinoxPassage().canUse("EvilWitch"); //$NON-NLS-1$
	}

	/**
	 * Check if feature with {@code licensing.feature.identifier}
	 * <i>"GoodWitch"</i>, declared by {@code magic.GoodWitch} OSGi component is
	 * payed for and Passage allows it be to used.
	 */
	private boolean goodWitchIsPayed() {
		return new EquinoxPassage().canUse("GoodWitch"); //$NON-NLS-1$
	}

}
