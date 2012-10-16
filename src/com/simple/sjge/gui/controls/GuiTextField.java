package com.simple.sjge.gui.controls;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import com.simple.sjge.engine.Engine;
import com.simple.sjge.engine.Keyboard;
import com.simple.sjge.gfx.Colour;
import com.simple.sjge.gfx.Screen;
import com.simple.sjge.gui.Gui;

public class GuiTextField extends Gui {

	private final int xPos;
	private final int yPos;
	private final int width;
	private final int height;

	private int tickCount;
	private int carretOffset = 0;
	private boolean isHighlighted = false;

	public boolean isFocused;
	public boolean isEnabled;
	public String text;

	public GuiTextField(int x, int y, String text) {
		this(x, y, 200, 40, text);
	}

	public GuiTextField(int x, int y, int w, int h, String text) {
		this.xPos = x;
		this.yPos = y;
		this.width = w;
		this.height = h;
		this.text = text;
		this.isFocused = false;
		this.isEnabled = true;
	}

	public void tick() {
		tickCount++;
	}

	public void render() {
		Graphics2D g = Engine.getGraphicsInstance();
		Screen s = Engine.getInstance().screenInstance();
		if (isEnabled)
			g.setColor(Colour.BLACK);
		else
			g.setColor(Colour.DARK_GRAY);
		g.fillRect(this.xPos, this.yPos, this.width, this.height);
		g.setColor(new Colour(0x999999));
		g.drawRect(this.xPos, this.yPos, this.width, this.height);
		boolean flag = isFocused && (tickCount / 20) % 2 == 0;
		if (isHighlighted) {
			Rectangle2D a = g.getFontMetrics().getStringBounds(this.text, g);
			g.setColor(Colour.WHITE);
			g.fillRect(xPos + 3,  yPos + 3, (int)a.getWidth(), (int)a.getHeight());
			g.setColor(Colour.BLUE);
		}
		else
			g.setColor(isEnabled ? Colour.WHITE : Colour.GRAY);

		if (carretOffset == 0)
			s.drawString((flag ? "|" : " ")+this.text, xPos + 3, yPos + 15);
		else if (carretOffset == this.text.length())
			s.drawString(this.text+(flag ? "|" : ""), xPos + 3, yPos + 15);
		else
			s.drawString(this.text.substring(0, carretOffset)+(flag ? "|" : " ")+this.text.substring(carretOffset), xPos + 3, yPos + 15);
	}

	public void setEnabled(boolean b) {
		this.isEnabled = b;
	}

	public void setFocused(boolean b) {
		this.isFocused = b;
	}

	public void setText(String t, boolean flag) {
		this.text = t;
		carretOffset = this.text.length();
	}

	public void setText(String t) {
		this.text = t;
	}

	public void keyTyped(KeyEvent e) {
		int i = e.getKeyCode();
		char c = e.getKeyChar();
		if (i == Keyboard.VK_A && e.isControlDown()) {
			isHighlighted = true;
		}

		else if ((i == Keyboard.VK_C || i == Keyboard.VK_X) && e.isControlDown()) {
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			StringSelection contents = new StringSelection(this.text);
			clipboard.setContents(contents, null);
			if (i == Keyboard.VK_X)
				setText("", true);
			if (isHighlighted)
				isHighlighted = false;
		}

		else if (i == Keyboard.VK_V && e.isControlDown()) {
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable contents = clipboard.getContents(null);
			if ((contents == null) || !contents.isDataFlavorSupported(DataFlavor.stringFlavor))
				return;
			try {
				String a = (String)contents.getTransferData(DataFlavor.stringFlavor);
				if (carretOffset == 0) {
					setText(a+this.text);
					carretOffset += a.length();
				}
				else if (carretOffset == this.text.length())
					setText(this.text+a, true);
				else {
					setText(this.text.substring(0, carretOffset)+a+this.text.substring(carretOffset));
					carretOffset += a.length();
				}
				if (isHighlighted)
					isHighlighted = false;
			} catch (UnsupportedFlavorException | IOException e1) { }

		}
		else if (i == Keyboard.VK_LEFT) {
			if (carretOffset > 0)
				carretOffset--;
			if (isHighlighted)
				isHighlighted = false;
		}
		else if (i == Keyboard.VK_RIGHT) {
			if (carretOffset < this.text.length())
				carretOffset++;
			if (isHighlighted)
				isHighlighted = false;
		}
		else if (i == Keyboard.VK_HOME || i == Keyboard.VK_END) {
			if (i == Keyboard.VK_HOME)
				carretOffset = 0;
			else
				carretOffset = this.text.length();
			if (isHighlighted)
				isHighlighted = false;
		}
		else if (i == Keyboard.VK_BACK_SPACE && this.text.length() > 0)
			if (isHighlighted) {
				isHighlighted = false;
				setText("", true);
			}
			else if (carretOffset == this.text.length())
				setText(this.text.substring(0, this.text.length() - 1), true);
			else if (carretOffset == 0)
				return;
			else {
				setText(this.text.substring(0, carretOffset - 1)+this.text.substring(carretOffset));
				carretOffset--;
			}

		else if (i == Keyboard.VK_DELETE)
			if (isHighlighted) {
				isHighlighted = false;
				setText("", true);
			}
			else if (carretOffset == 0 && this.text.length() > 0)
				setText(this.text.substring(1));
			else if (carretOffset == this.text.length())
				return;
			else
				if (carretOffset + 1 == this.text.length())
					return;
				else
					setText(this.text.substring(0, carretOffset)+this.text.substring(carretOffset + 1));

		else if (Engine.allowedCharacters.contains(Character.toString(c).toLowerCase()) || i == Keyboard.VK_SPACE) {
			if (isHighlighted) {
				isHighlighted = false;
				setText("", true);
			}
			if (carretOffset == 0)
				setText(c+this.text);
			else if (carretOffset == this.text.length())
				setText(this.text+c);
			else
				setText(this.text.substring(0, carretOffset)+c+this.text.substring(carretOffset));
			carretOffset++;
		}
		else
			return;
	}

	@Deprecated
	public void keyTyped(char c, int i) {
		if (i == Keyboard.VK_BACK_SPACE && this.text.length() > 0)
			setText(this.text.substring(0, this.text.length() - 1));
		else if (Engine.allowedCharacters.contains(Character.toString(c).toLowerCase()) || i == Keyboard.VK_SPACE)
			setText(this.text+c);
		else
			return;
	}

	public void mousePressed(int x, int y) {
		boolean flag = this.isEnabled && x > this.xPos && x < (this.xPos + this.width) && y < (this.yPos + this.height) && y > this.yPos;
		setFocused(flag);
		if (flag) {
			if (this.text.length() == 0)
				return;
			String a = this.text.substring(0, 1);
			FontMetrics fm = Engine.getGraphicsInstance().getFontMetrics();
			int i = 0;
			try {
				while (fm.stringWidth(a) < x) {
					i++;
					a = this.text.substring(0, i);
				}
			}
			catch (StringIndexOutOfBoundsException e) { i++; }
			carretOffset = i - 2;
		}
	}

}
