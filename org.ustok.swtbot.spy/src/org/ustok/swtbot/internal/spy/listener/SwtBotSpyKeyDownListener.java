/******************************************************
 * Copyright 2012 by Ustok.org.
 * All rights reserved.
 ******************************************************/

package org.ustok.swtbot.internal.spy.listener;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.ustok.swtbot.internal.spy.ILabelManager;

/**
 * Key listener to process key-down events.
 * 
 * @author Ingo Mohr
 * @created Dec 23, 2012
 */
public class SwtBotSpyKeyDownListener implements Listener {

	/** label manager */
	private final ILabelManager fLabelManager;

	/**
	 * Creates a new listener.
	 * 
	 * @param pLabelManager
	 *        the label manager to use. Cannot be <code>null</code>.
	 */
	public SwtBotSpyKeyDownListener(ILabelManager pLabelManager) {
		Assert.isNotNull(pLabelManager);
		fLabelManager = pLabelManager;
	}

	@Override
	public void handleEvent(Event pEvent) {
		int stateMask = SWT.SHIFT | SWT.CTRL;
		if (pEvent.keyCode == 'q' && (pEvent.stateMask & stateMask) == stateMask) {

			if (getLabelManager().hasLabels()) {
				getLabelManager().deactivateLabels();
			} else {
				Shell activeShell = Display.getDefault().getActiveShell();
				getLabelManager().activateLabels(activeShell);
			}
		}
	}

	/**
	 * Returns the label manager.
	 * 
	 * @return label manager. Never <code>null</code>.
	 */
	public ILabelManager getLabelManager() {
		return fLabelManager;
	}

}
