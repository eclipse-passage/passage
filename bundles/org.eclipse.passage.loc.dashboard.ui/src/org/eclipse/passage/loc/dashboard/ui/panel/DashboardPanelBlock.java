/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.dashboard.ui.panel;

import java.util.stream.StreamSupport;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;

public class DashboardPanelBlock {

	private String warning;
	private String info;

	private Text text;
	private ControlDecoration decoration;
	private Link edit;

	public void createControl(Composite parent, String label, Image image) {
		text = createTextBlock(parent, label, image);
		decoration = new ControlDecoration(text, SWT.TOP | SWT.LEFT);
		edit = new Link(parent, SWT.NONE);
		edit.setText("<a>Edit</a>"); //$NON-NLS-1$
	}

	public void configureEdit(String tooltip, SelectionListener listener) {
		edit.setToolTipText(tooltip);
		edit.addSelectionListener(listener);
	}

	protected Text createTextBlock(Composite parent, String label, Image image) {
		Label imageLabel = new Label(parent, SWT.NONE);
		imageLabel.setImage(image);
		Label textLabel = new Label(parent, SWT.NONE);
		{
			GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
			data.widthHint = 100;
			textLabel.setLayoutData(data);
		}
		textLabel.setText(label);
		Text text = new Text(parent, SWT.READ_ONLY);
		{
			GridData data = new GridData(SWT.FILL, SWT.FILL, false, false);
			data.widthHint = 50;
			data.horizontalIndent = 5;
			text.setLayoutData(data);
		}
		return text;
	}

	protected void decorateTextBlock(String warning, String info, long count, ControlDecoration decoration) {
		FieldDecorationRegistry registry = FieldDecorationRegistry.getDefault();
		if (count > 0) {
			Image image = registry.getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage();
			decoration.setImage(image);
			decoration.setDescriptionText(String.format(info, count));
		} else {
			Image image = registry.getFieldDecoration(FieldDecorationRegistry.DEC_WARNING).getImage();
			decoration.setImage(image);
			decoration.setDescriptionText(String.format(warning, count));
		}
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public void setWarning(String warning) {
		this.warning = warning;
	}
	
	public void update(Iterable<?> iterable) {
		update(StreamSupport.stream(iterable.spliterator(), false).count());
	}

	public void update(long count) {
		String value = String.valueOf(count);
		if (value.equals(text.getText())) {
			return;
		}
		text.setText(value);
		edit.setEnabled(count > 0);
		decorateTextBlock(warning, info, count, decoration);
	}

}
