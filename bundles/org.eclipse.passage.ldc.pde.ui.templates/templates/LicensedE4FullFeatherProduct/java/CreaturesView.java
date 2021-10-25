package $packageName$;

import java.util.Objects;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import $packageName$.magic.Creatures;
import $packageName$.magic.service.Creature;
import $packageName$.magic.service.Magic;
import $packageName$.magic.service.Shape;
import org.eclipse.passage.lic.equinox.EquinoxPassage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

public final class CreaturesView {

	private final Creatures creatures;
	private final Shape shape;
	private final Magic magic;
	private final String feature;

	//FIXME: evolve constructor parameters: describe the magic by a single interface
	CreaturesView(Creatures creatures, Shape shape, Magic magic, String feature) {
		Objects.requireNonNull(creatures);
		Objects.requireNonNull(shape);
		Objects.requireNonNull(magic);
		Objects.requireNonNull(feature);
		this.creatures = creatures;
		this.shape = shape;
		this.magic = magic;
		this.feature = feature;
	}

	public void install(Composite parent) {
		Composite owner = owner(parent);
		installTitle(owner);
		ListViewer list = targetsList(owner);
		Composite tools = magicTools(owner);
		Button turn = magicWand(tools);
		Text info = magicInfo(tools);
		installDynamics(list, turn, info);
		installData(list, turn, info);
	}

	private void installTitle(Composite owner) {
		Label label = new Label(owner, SWT.NONE);
		label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
		label.setFont(titleFont(owner));
		label.setForeground(owner.getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY));
		label.setText(String.format("Creatures of %s shape", shape.name().toLowerCase())); //$NON-NLS-1$
	}

	private void installDynamics(ListViewer list, Button turn, Text info) {
		((List) list.getControl()).addSelectionListener( //
				SelectionListener.widgetSelectedAdapter(e -> updateMagicTools(list, turn, info)));
		creatures.listenToMutation((original, mutated) -> {
			list.refresh(true);
			if (mutated.shape() == shape) {
				list.setSelection(new StructuredSelection(mutated));
			}
			updateMagicTools(list, turn, info);
		});
		turn.addSelectionListener(SelectionListener.widgetSelectedAdapter(e -> {
			Creature target = (Creature) ((IStructuredSelection) list.getSelection()).getFirstElement();
			magic.turn(target);
		}));
	}

	private void installData(ListViewer list, Button turn, Text info) {
		list.setInput(creatures);
		updateMagicTools(list, turn, info);
	}

	private void updateMagicTools(ListViewer list, Button turn, Text info) {
		if (magicIsProperlyPayedFor()) {
			info.setText(String.format("Passage allows to use %s", feature)); //$NON-NLS-1$
			info.setBackground(info.getDisplay().getSystemColor(SWT.COLOR_CYAN));
			turn.setEnabled(!list.getSelection().isEmpty());
		} else {
			info.setText(String.format("%s is not properly payed", feature)); //$NON-NLS-1$
			info.setBackground(info.getDisplay().getSystemColor(SWT.COLOR_MAGENTA));
			turn.setEnabled(false);
		}
	}

	private Composite owner(Composite parent) {
		Composite owner = new Composite(parent, SWT.NONE);
		owner.setLayout(new GridLayout(1, true));
		owner.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		owner.setFont(listFont(parent));
		return owner;
	}

	private Text magicInfo(Composite tools) {
		Text info = new Text(tools, SWT.MULTI | SWT.WRAP | SWT.READ_ONLY | SWT.BORDER);
		GridData layout = new GridData(SWT.FILL, SWT.CENTER, true, false);
		layout.minimumHeight = 30;
		info.setLayoutData(layout);
		info.setFont(textFont(tools));
		return info;
	}

	private Button magicWand(Composite tools) {
		Button turn = new Button(tools, SWT.PUSH);
		turn.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
		turn.setFont(buttonFont(tools));
		turn.setText("\u2605 " + magic.purpose()); //$NON-NLS-1$
		return turn;

	}

	private Composite magicTools(Composite owner) {
		Composite tools = new Composite(owner, SWT.NONE);
		tools.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		tools.setLayout(new GridLayout(1, false));
		return tools;
	}

	private ListViewer targetsList(Composite owner) {
		ListViewer list = new ListViewer(owner, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		list.setContentProvider(new CreaturesContentProvider(shape));
		list.setLabelProvider(new CreatureLabelProvider());
		list.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		list.getControl().setFont(listFont(owner));
		return list;
	}

	private Font listFont(Control parent) {
		return new Font(parent.getFont().getDevice(), "Sans serif", 16, SWT.NORMAL); //$NON-NLS-1$
	}

	private Font buttonFont(Control parent) {
		return new Font(parent.getFont().getDevice(), "Sans serif", 12, SWT.BOLD); //$NON-NLS-1$
	}

	private Font textFont(Control parent) {
		return new Font(parent.getFont().getDevice(), "Sans serif", 12, SWT.ITALIC); //$NON-NLS-1$
	}

	private Font titleFont(Control parent) {
		return new Font(parent.getFont().getDevice(), "Serif", 12, SWT.ITALIC); //$NON-NLS-1$
	}

	private boolean magicIsProperlyPayedFor() {
		return new EquinoxPassage().canUse(feature);
	}

}
