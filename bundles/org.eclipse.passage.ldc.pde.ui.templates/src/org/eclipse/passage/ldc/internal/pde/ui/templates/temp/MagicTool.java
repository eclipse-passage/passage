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
final class MagicTool implements ISelectionChangedListener {

	private final Magic magic;
	private final Shape target;
	private final String feature;
	private Button spell;

	MagicTool(Shape target, String feature) {
		this.target = target;
		this.feature = feature;
		this.magic = Universe.instance;
	}

	public void install(Composite parent) {
		Composite owner = owner(parent);
		installInfo(owner);
		installMagic(owner);
		updateEnablement(Shape.NONE);
	}

	private Composite owner(Composite parent) {
		Composite owner = new Composite(parent, SWT.NONE);
		owner.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, true));
		owner.setLayout(new GridLayout(1, false));
		return owner;
	}

	private void installInfo(Composite parent) {
		new Label(parent, SWT.NONE).setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, true));
	}

	private void installMagic(Composite parent) {
		spell = button(parent);
		spell.setText("To frog"); //$NON-NLS-1$
		// frog.addSelectionListener(SelectionListener.widgetSelectedAdapter( e->
		// magic.turn(creature, shape)));
	}

	private Button button(Composite parent) {
		Button turn = new Button(parent, SWT.PUSH);
		turn.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
		return button(parent);
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		Creature creature = (Creature) ((IStructuredSelection) event.getSelection()).getFirstElement();
		updateEnablement(creature.shape());
	}

	private void updateEnablement(Shape shape) {
		spell.setEnabled(shape != target && witchIsProperlyPayed());
	}

	/**
	 * Check if the magic {@code feature} with the configured
	 * {@code licensing.feature.identifier} (like <i>"EvilWitch"</i>), declared by
	 * an OSGi component is payed for and Passage allows it be to used.
	 */
	private boolean witchIsProperlyPayed() {
		return new EquinoxPassage().canUse(feature);
	}

}
