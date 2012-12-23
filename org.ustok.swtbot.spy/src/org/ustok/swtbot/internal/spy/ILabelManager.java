/******************************************************
 * Copyright 2012 by Ustok.org.
 * All rights reserved.
 ******************************************************/

package org.ustok.swtbot.internal.spy;

import java.util.List;

import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;

/**
 * Manages SWT labels. Responsible for creating and disposing fully working widgets to show the desired spy information on.
 * 
 * @author Ingo Mohr
 * @created Dec 23, 2012
 */
public interface ILabelManager {

	/**
	 * Removes and disposes all labels from their corresponding parent control.
	 */
	public void deactivateLabels();

	/**
	 * Activates the widget labels for all bottom-level controls on the given parent composite.
	 * 
	 * @param pParent
	 *        the parent composite. Cannot be <code>null</code>.
	 */
	public void activateLabels(Composite pParent);

	/**
	 * Adds the given mouse listener.
	 * <p>
	 * All mouse listeners will be added to the labels on activation.
	 * </p>
	 * 
	 * @param pListener
	 *        the listener to add.
	 */
	public void addMouseListener(MouseListener pListener);

	/**
	 * Returns all mouse listeners that hav been added to the manager.
	 * 
	 * @return mouse listeners. Possibly an empty list. Never <code>null</code>.
	 */
	public List<MouseListener> getMouseListeners();

	/**
	 * Returns <code>true</code> if there are currently activated labels.
	 * 
	 * @return <code>true</code> if there are labels. <code>false</code> otherwise.
	 */
	public boolean hasLabels();

}
