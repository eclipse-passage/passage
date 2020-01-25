/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
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
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.DashboardUiMessages;
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
	private Link show;

	public void createControl(Composite parent, String label, Image image) {
		text = createTextBlock(parent, label, image);
		decoration = new ControlDecoration(text, SWT.TOP | SWT.LEFT);
		show = new Link(parent, SWT.NONE);
		show.setText(DashboardUiMessages.DashboardPanelBlock_show_text);
	}

	public void configureShow(String tooltip, SelectionListener listener) {
		show.setToolTipText(tooltip);
		show.addSelectionListener(listener);
	}

	protected Text createTextBlock(Composite parent, String label, Image image) {
		Label imageLabel = new Label(parent, SWT.NONE);
		imageLabel.setImage(image);
		Label blockLabel = new Label(parent, SWT.NONE);
		{
			GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
			data.widthHint = 100;
			blockLabel.setLayoutData(data);
		}
		blockLabel.setText(label);
		Text blockText = new Text(parent, SWT.READ_ONLY);
		{
			GridData data = new GridData(SWT.FILL, SWT.FILL, false, false);
			data.widthHint = 50;
			data.horizontalIndent = 5;
			blockText.setLayoutData(data);
		}
		return blockText;
	}

	protected void decorateTextBlock(String warningPattern, String infoPattern, long count, ControlDecoration controlDecoration) {
		FieldDecorationRegistry registry = FieldDecorationRegistry.getDefault();
		if (count > 0) {
			Image image = registry.getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage();
			controlDecoration.setImage(image);
			controlDecoration.setDescriptionText(String.format(infoPattern, count));
		} else {
			Image image = registry.getFieldDecoration(FieldDecorationRegistry.DEC_WARNING).getImage();
			controlDecoration.setImage(image);
			controlDecoration.setDescriptionText(String.format(warningPattern, count));
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
		show.setEnabled(count > 0);
		decorateTextBlock(warning, info, count, decoration);
	}

}
