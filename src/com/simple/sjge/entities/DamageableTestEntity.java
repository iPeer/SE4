package com.simple.sjge.entities;

import com.simple.sjge.entities.bars.HealthBar;
import com.simple.sjge.entities.bars.ShieldBar;
import com.simple.sjge.entities.extensions.Shield;
import com.simple.sjge.level.Level;

public class DamageableTestEntity extends AITestEntity {

	public DamageableTestEntity(int x, int y, int w, int h, Level level) {
		super(x, y, w, h, level);
		health = 1000;
		shield = new Shield(this, 1000, 200, 500);
		healthBar = new HealthBar(this, health);
		shieldBar = new ShieldBar(shield);
	}
	
	public void tick() {
		shield.tick();
		super.tick();
	}
	
	public void render() {
		super.render();
		healthBar.render();
		shieldBar.render();
	}
	
	public void damage(int i) {
		if (!shield.isEmpty()) {
			float a = shield.damage(i);
			if (a > 0)
				damage((int)a);
		}
		else {
			this.health -= i;
			shield.resetRecharge();
		}
		if (this.health <= 0)
			level.removeEntity(this);
	}
	
//	public int getHealth() {
//		return this.health;
//	}
//	
//	public Shield getShield() {
//		return this.shield;
//	}



}