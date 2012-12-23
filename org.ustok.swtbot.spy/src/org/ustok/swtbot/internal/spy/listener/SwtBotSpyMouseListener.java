/******************************************************
 * Copyright 2012 by Ustok.org.
 * All rights reserved.
 ******************************************************/

package org.ustok.swtbot.internal.spy.listener;

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

/**
 * Mouse listener to process double click events.
 * 
 * @author Ingo Mohr
 * @created Dec 23, 2012
 */
public class SwtBotSpyMouseListener extends MouseAdapter {

	@Override
	public void mouseDoubleClick(MouseEvent pEvent) {
		if (pEvent.widget instanceof Label) {
			Label label = (Label) pEvent.widget;
			String text = label.getText();
			Transfer[] transfers = new Transfer[]{TextTransfer.getInstance()};

			Clipboard clipboard = new Clipboard(Display.getDefault());
			clipboard.setContents(new Object[]{text}, transfers);
			clipboard.dispose();
		}
	}

}
