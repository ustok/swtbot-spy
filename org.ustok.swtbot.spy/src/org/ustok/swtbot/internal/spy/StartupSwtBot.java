/******************************************************
 * Copyright 2011-2012 by Ustok.org.
 * All rights reserved.
 ******************************************************/

package org.ustok.swtbot.internal.spy;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;

/**
 * Extension invoked during the startup process.
 * 
 * <p>
 * Will launch the {@link SwtBotSpy}.
 * </p>
 * 
 * @author thomas
 * @created Dec 23, 2012
 */
public class StartupSwtBot implements IStartup {

	@Override
	public void earlyStartup() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				SwtBotSpy spy = new SwtBotSpy();
				spy.run();
			}
		});
	}

}
