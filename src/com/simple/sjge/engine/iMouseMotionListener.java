package com.simple.sjge.engine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import com.simple.sjge.gui.Gui;
import com.simple.sjge.gui.controls.GuiButton;
import com.simple.sjge.gui.controls.GuiTextField;
import com.simple.sjge.level.Level;

public class iMouseMotionListener implements MouseMotionListener {

	protected Engine engine;
	
	public static int lastX, lastY;
	
	public iMouseMotionListener(Engine engine) {
		this.engine = engine;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Gui gui = engine.currentGui;
		if ((gui == null && engine.level != null)) { 
			Level l = engine.level;
			int x = l.xOffset;
			x += (e.getX() - lastX);
			int y = l.yOffset;
			y += (e.getY() - lastY);
			lastX = e.getX();
			lastY = e.getY();
			l.setOffset(x, y);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void mouseMoved(MouseEvent e) {
		int i = e.getX();
		int j = e.getY();
		Gui gui = engine.currentGui;
		if (gui != null) { 
			ArrayList controls = gui.controls;
			for (int l = 0; l < controls.size(); l++) {
				Gui control = (Gui)controls.get(l);
				if (control instanceof GuiButton) {
					GuiButton button = (GuiButton)control;
					if (button.enabled && i > button.x && i < (button.x + button.w) && j > button.y && j < (button.y + button.h)) {
						button.isMouseOver = true;
					}
					else {
						button.isMouseOver = false;
					}
				}
				else if (control instanceof GuiTextField) {
					
				}
			}
		}
	}

}
