package com.simple.sjge.engine;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.simple.sjge.util.Debug;

public class iWindowListener implements WindowListener {
	
	protected Engine engine;

	public iWindowListener(Engine engine) {
		this.engine = engine;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		Engine.GAME_RUNNING = false;
		Debug.p("Stopping!");
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		

	}
	
}
