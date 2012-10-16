package com.simple.sjge.engine;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.simple.sjge.gui.GuiConsole;

public class iMouseWheelListener implements MouseWheelListener {
	
	protected Engine engine;

	public iMouseWheelListener(Engine engine) {
		this.engine = engine;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (engine.currentGui instanceof GuiConsole)
			Engine.guiConsole.historyOffset += e.getWheelRotation();
	}

}
