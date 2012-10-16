package com.simple.sjge.entities;

import com.simple.sjge.ai.packages.UpDownAI;
import com.simple.sjge.entities.bars.HealthBar;
import com.simple.sjge.entities.bars.ShieldBar;
import com.simple.sjge.entities.extensions.Shield;
import com.simple.sjge.level.Level;

public class ShieldBarTestEntity extends Entity {
	
	HealthBar healthbar;
	ShieldBar shieldBar;
	private int dir = 0;
	private int health = 100;
	Shield shield;

	public ShieldBarTestEntity(int x, int y, int w, int h, Level level) {
		super(x, y, w, h, level);
		healthbar = new HealthBar(this, 100);
		shield = new Shield(this, 100, 0, 120);
		shieldBar = new ShieldBar(shield);
		setAIPackage(new UpDownAI(this));
	}


	public void tick() {
		shield.tick();
		if (dir == 0) {
			this.health--;
			if (this.health <= 0)
				dir = 1;
		}
		if (dir == 1) {
			this.health++;
			if (this.health >= 100)
				dir = 0;
		}
		super.tick();
		
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public Shield getShield() {
		return shield;
	}
	
	public void render() {
		healthbar.render();
		shieldBar.render();
		super.render();
	}
	
	

}
