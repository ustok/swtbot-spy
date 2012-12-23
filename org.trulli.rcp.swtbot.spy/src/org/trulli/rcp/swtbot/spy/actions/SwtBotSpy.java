package org.trulli.rcp.swtbot.spy.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * The SwtBotSpy adds labels a
 * 
 * @see IWorkbenchWindowActionDelegate
 */
public class SwtBotSpy {

	/** the key down listener */
	private final Listener fListenerKeyDown;

	/** the key up listener */
	private final Listener fListenerKeyUp;

	/** mouse listener */
	private final MouseListener fListenerMouse;

	/** currently added labels */
	private final List<Label> fLabels = new ArrayList<Label>();

	/**
	 * The constructor. Must be invoked within the UI Thread.
	 */
	public SwtBotSpy() {
		fListenerKeyDown = createListenerKeyDown();
		fListenerKeyUp = createListenerKeyUp();
		fListenerMouse = createListenerMouse();
		Display.getDefault().addFilter(SWT.KeyDown, fListenerKeyDown);
		Display.getDefault().addFilter(SWT.KeyUp, fListenerKeyUp);
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

	public void dispose() {
	}

	public void init(IWorkbenchWindow window) {
	}

	private Listener createListenerKeyDown() {
		return new Listener() {
			@Override
			public void handleEvent(Event pEvent) {
				int stateMask = SWT.SHIFT | SWT.CTRL;
				if (pEvent.keyCode == 'q'
						&& (pEvent.stateMask & stateMask) == stateMask
						&& fLabels.isEmpty()) {
					Shell activeShell = Display.getDefault().getActiveShell();
					addLabels(activeShell);
				}
			}
		};
	}

	private Listener createListenerKeyUp() {
		return new Listener() {
			@Override
			public void handleEvent(Event event) {
				removeLabels();
			}
		};
	}

	private void addLabels(Composite pComposite) {
		for (Control child : pComposite.getChildren()) {
			if (child instanceof Composite) {
				addLabels((Composite) child);
			} else {
				Object rawData = child
						.getData(SwtBotSpyPlatformConstants.WIDGET_ID_KEY);
				if (rawData instanceof String) {
					String text = (String) rawData;
					final int height = 20;
					Rectangle bounds = child.getBounds();
					int locationY = Math.max(0, bounds.height / 2 - height / 2)
							+ bounds.y;
					Label label = new Label(pComposite, SWT.BORDER);
					label.setBounds(bounds.x, locationY, bounds.width, height);
					label.setText(text);
					label.moveAbove(child);
					label.setBackground(Display.getDefault().getSystemColor(
							SWT.COLOR_INFO_BACKGROUND));
					label.setForeground(Display.getDefault().getSystemColor(
							SWT.COLOR_INFO_FOREGROUND));
					label.addMouseListener(fListenerMouse);

					fLabels.add(label);
				}
			}
		}
	}

	private void removeLabels() {
		if (!fLabels.isEmpty()) {
			for (Label label : fLabels) {
				Composite parent = label.getParent();
				label.dispose();
				parent.redraw();
			}
			fLabels.clear();
		}
	}

	private MouseListener createListenerMouse() {
		return new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent pEvent) {
				if (pEvent.widget instanceof Label) {
					Label label = (Label) pEvent.widget;
					Clipboard clipboard = new Clipboard(Display.getDefault());
					clipboard.setContents(new Object[] { label.getText() },
							new Transfer[] { TextTransfer.getInstance() });
					clipboard.dispose();
				}
			}
		};
	}
}