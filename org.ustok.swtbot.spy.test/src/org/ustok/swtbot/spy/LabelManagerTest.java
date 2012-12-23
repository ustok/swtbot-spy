/******************************************************
 * Copyright 2012 by Ustok.org.
 * All rights reserved.
 ******************************************************/

package org.ustok.swtbot.spy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.ustok.swtbot.internal.spy.ILabelManager;
import org.ustok.swtbot.internal.spy.LabelManager;

/**
 * Test for {@link LabelManager}.
 * 
 * @author Ingo Mohr
 * @created Dec 23, 2012
 */
public class LabelManagerTest {
	
	@Test
	public void testInit() {
		ILabelManager manager = new LabelManager();
		assertEquals(0, manager.getMouseListeners().size());
		assertEquals(false, manager.hasLabels());
		manager.deactivateLabels(); // should do nothing
	}

}

