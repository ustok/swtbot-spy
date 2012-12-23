package org.trulli.rcp.swtbot.spy.actions;
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
 */
public class StartupSwtBot implements IStartup {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void earlyStartup() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				new SwtBotSpy();				
			}
		});
	}
	
	

}
