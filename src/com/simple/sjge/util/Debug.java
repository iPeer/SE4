package com.simple.sjge.util;

import com.simple.sjge.gui.GuiConsole;

public class Debug {
	
	static java.io.PrintStream o = System.out;

	public static void p(String s) {
		GuiConsole.console.addConsoleMessage(s);
	}
	
	public static void p(int i) {
		o.println(i);
	}
	
	public static void p(Boolean b) {
		o.println(b);
	}
	
	public static void p(double d) {
		o.println(d);
	}
	
	public static void p(float f) {
		o.println(f);
	}
	
	public static void p(long l) {
		o.println(l);
	}

	public static void e(String s) {
		GuiConsole.console.addConsoleError(s);
	}
	
}
