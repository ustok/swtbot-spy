/******************************************************
 * Copyright 2012 by Ustok.org.
 * All rights reserved.
 ******************************************************/

package org.ustok.swtbot.spy.test.testcode;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

/**
 * Test viewpart for SWT Bot spy.
 * 
 * @author Ingo Mohr
 * @created Dec 23, 2012
 */
public class TestViewPart extends ViewPart {

	/** control on the view */
	private Composite fControl;

	public TestViewPart() {
	}

	@Override
	public void createPartControl(Composite pParent) {
		fControl = new TestControl(pParent, SWT.NONE);
		fControl.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
	}

	@Override
	public void setFocus() {
		if (fControl != null) {
			fControl.setFocus();
		}
	}

}
