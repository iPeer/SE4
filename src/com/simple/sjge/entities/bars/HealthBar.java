package com.simple.sjge.entities.bars;

import java.awt.Color;
import java.awt.Graphics2D;

import com.simple.sjge.engine.Engine;
import com.simple.sjge.entities.Entity;
import com.simple.sjge.gfx.Colour;

public class HealthBar {

	protected Entity owner;
	public int length = 30;
	public int height = 10;
	private int maxHealth = 100;

	public HealthBar(Entity owner, int maxHealth) {
		this.owner = owner;
		this.maxHealth = maxHealth;
	}

	public void render() {
		Graphics2D g = Engine.getGraphicsInstance();
		g.setColor(Colour.WHITE);
		g.drawRect((owner.x + owner.level.xOffset) + (owner.w - (this.length + 2)) / 2, (owner.y + owner.level.yOffset) - this.height - 2, this.length + 2, this.height - 2);
		Color c = Colour.GREEN;
//		
//		if (red < 255)
//			red += increment;
//		if (red >= 255)
//			red = 255;
//		if (red <= 0)
//			red = 0;
//		if (red == 255) {
//			green -= increment;
//			if (green >= 255)
//				green = 255;
//			if (green <= 0)
//				green = 0;
//		}
//		c = new Colour(red, green, 0);
		int hp = owner.getHealth();
		if (hp > maxHealth*.66)
			c = Colour.GREEN;
		if (hp < maxHealth*.66)
			c = Colour.ORANGE;
		if (hp < maxHealth*.33)
			c = Colour.RED;
	
		g.setColor(c);
		g.fillRect((owner.x + owner.level.xOffset) + (owner.w - (this.length - 2)) / 2, (owner.y + owner.level.yOffset) - this.height, (this.length*owner.getHealth()/maxHealth) - 1, this.height - 5);
		if (Engine.DEBUG_ENABLED)
			g.drawString(Integer.toString(owner.getHealth()), (owner.x + owner.level.xOffset) + this.length - 3, (owner.y + owner.level.yOffset) - 4);
	}

}
