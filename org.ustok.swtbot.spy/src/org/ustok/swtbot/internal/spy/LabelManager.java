/******************************************************
 * Copyright 2012 by Ustok.org.
 * All rights reserved.
 ******************************************************/

package org.ustok.swtbot.internal.spy;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * Implementation for {@link ILabelManager}.
 * 
 * @author Ingo Mohr
 * @created Dec 23, 2012
 */
public class LabelManager implements ILabelManager {

	/** mouse listeners */
	private final List<MouseListener> fListMouseListeners = new ArrayList<MouseListener>();

	/** currently added labels */
	private final List<Label> fListLabels = new ArrayList<Label>();

	@Override
	public void deactivateLabels() {
		synchronized (this) {
			if (!fListLabels.isEmpty()) {
				for (Label label : fListLabels) {
					Composite parent = label.getParent();
					label.dispose();
					parent.redraw();
				}
				fListLabels.clear();
			}
		}
	}

	@Override
	public void activateLabels(Composite pComposite) {
		synchronized (this) {
			for (Control child : pComposite.getChildren()) {
				internalActivateLabel(child);

				if (child instanceof ToolBar) {
					for (ToolItem item : ((ToolBar) child).getItems()) {
						internalActivateLabel(item);
					}
				}

				if (child instanceof Composite) {
					activateLabels((Composite) child);
				}
			}
		}
	}

	@Override
	public void addMouseListener(MouseListener pListener) {
		if (pListener != null) {
			fListMouseListeners.add(pListener);
		}
	}

	@Override
	public List<MouseListener> getMouseListeners() {
		return fListMouseListeners;
	}

	@Override
	public boolean hasLabels() {
		return !fListLabels.isEmpty();
	}

	private void internalActivateLabel(Control pControl) {
		Object rawData = pControl.getData(SwtBotSpyPlatformConstants.WIDGET_ID_KEY);
		if (rawData instanceof String) {
			Composite parent = pControl.getParent();
			String text = (String) rawData;
			final int height = 20;
			Rectangle bounds = pControl.getBounds();
			int locationY = Math.max(0, bounds.height / 2 - height / 2) + bounds.y;
			Label label = new Label(parent, SWT.BORDER);
			label.setBounds(bounds.x, locationY, bounds.width, height);
			label.setText(text);
			label.setToolTipText("ID: <" + text + ">. Doubleclick to copy.");
			label.moveAbove(pControl);
			label.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
			label.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_INFO_FOREGROUND));
			for (MouseListener listener : getMouseListeners()) {
				label.addMouseListener(listener);
			}

			fListLabels.add(label);
		}
	}

	private void internalActivateLabel(ToolItem pToolItem) {
		// duplicate code is rubbish, but it's ok for now. The complete Spy UI will be redesigned.
		
		Object rawData = pToolItem.getData(SwtBotSpyPlatformConstants.WIDGET_ID_KEY);
		if (rawData instanceof String) {
			Composite parent = pToolItem.getParent();
			String text = (String) rawData;
			final int height = 20;
			Rectangle bounds = pToolItem.getBounds();
			int locationY = Math.max(0, bounds.height / 2 - height / 2) + bounds.y;
			Label label = new Label(parent, SWT.BORDER);
			label.setBounds(bounds.x, locationY, bounds.width, height);
			label.setText(text);
			label.setToolTipText("ID: <" + text + ">. Doubleclick to copy.");
			label.moveAbove(pToolItem.getParent());
			label.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
			label.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_INFO_FOREGROUND));
			for (MouseListener listener : getMouseListeners()) {
				label.addMouseListener(listener);
			}

			fListLabels.add(label);
		}
	}

}
