/******************************************************
 * Copyright 2011-2012 by Ustok.org.
 * All rights reserved.
 ******************************************************/

package org.ustok.swtbot.internal.spy;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.ustok.swtbot.internal.spy.listener.SwtBotSpyKeyListener;
import org.ustok.swtbot.internal.spy.listener.SwtBotSpyMouseListener;

/**
 * The SwtBotSpy shows labels with the SWT bot identifiers when hotkey CTRL+SHIFT+Q is pressed.
 * <p>
 * Once a spy is created, it needs to be called to run.
 * </p>
 * 
 * @author Ingo Mohr
 * @created Dec 23, 2012
 */
public class SwtBotSpy {

	/**
	 * Creates a new spy.
	 */
	public SwtBotSpy() {
	}

	/**
	 * Runs the spy.
	 */
	public void run() {
		ILabelManager labelManager = new LabelManager();
		labelManager.addMouseListener(new SwtBotSpyMouseListener());

		Listener keyListener = new SwtBotSpyKeyListener(labelManager);
		Display.getDefault().addFilter(SWT.KeyDown, keyListener);
	}

}