/******************************************************
 * Copyright 2012 by Ustok.org.
 * All rights reserved.
 ******************************************************/

package org.ustok.swtbot.spy.test.testcode;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * SWTBot Spy test control.
 * 
 * @author Ingo Mohr
 * @created Dec 23, 2012
 */
public class TestControl extends Composite {

	public TestControl(Composite pParent, int pStyle) {
		super(pParent, pStyle);
		create();
	}

	private void create() {
		setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

		Label label = new Label(this, SWT.NONE);
		label.setText("Name");
		installSWTBotId(label, "label_id");

		Text text = new Text(this, SWT.BORDER);
		installSWTBotId(text, "text_id");
		text.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

		Label labelNoId = new Label(this, SWT.NONE);
		labelNoId.setText("<no id>");
	}

	private void installSWTBotId(Control pControl, String pKey) {
		pControl.setData("org.eclipse.swtbot.widget.key", pKey);
	}
}
