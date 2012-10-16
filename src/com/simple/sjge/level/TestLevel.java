package com.simple.sjge.level;

import com.simple.sjge.ai.AIPath;
import com.simple.sjge.ai.packages.BouncingAI;
import com.simple.sjge.collision.BBox;
import com.simple.sjge.entities.AITestEntity;
import com.simple.sjge.entities.AITestEntity2;
import com.simple.sjge.entities.DamageableTestEntity;
import com.simple.sjge.entities.DamagingTestEntity;
import com.simple.sjge.entities.Entity;
import com.simple.sjge.entities.PathAITestEntity;
import com.simple.sjge.entities.ShieldBarTestEntity;
import com.simple.sjge.gfx.Screen;

public class TestLevel extends Level {

	public TestLevel(int width, int height, Screen screen) {
		super(width, height, screen);
		generate();
	}

	public void generate() {
		BBoxes.clear();
		entities.clear();
		addBBox(new BBox(100, 100, 250, 20));
		addBBox(new BBox(600, 100, 250, 20));
		addBBox(new BBox(600, 200, 250, 20));
		addBBox(new BBox(100, 450, 250, 8));
		addBBox(new BBox(600, 450, 250, 8));
		addBBox(new BBox(0, 0, width, 1));
		addBBox(new BBox(0, 0, 1, height));
		addBBox(new BBox(width - 1, 0, 1, height));
		addBBox(new BBox(0, height - 1, width, 1));
		//addEntity(new Player(100, 70, 16, 16, this));
		addEntity(new AITestEntity(500, 448, 16, 16, this));
		addEntity(new DamageableTestEntity(450, 105, 16, 16, this));
		addEntity(new DamageableTestEntity(500, 105, 16, 16, this));
		addEntity(new AITestEntity2(700, 150, 16, 16, this));
		addEntity(new DamagingTestEntity(462, 146, 16, 16, this, 300, 100, 5, 25));
		addEntity(new DamagingTestEntity(500, 146, 16, 16, this, 200, 100, 5, 45));
		addEntity(new ShieldBarTestEntity(222, 300, 16, 16, this));
		addEntity(new PathAITestEntity(100, 500, 16, 16, this));
		Entity offLevelEntity;
		addEntity(offLevelEntity = new AITestEntity(-20, 0, 16, 16, this));
		offLevelEntity.setShouldTick(false);
		Entity entity = new DamageableTestEntity(width / 2, height / 2, 16, 16, this);
		entity.setAIPackage(new BouncingAI(entity));
		addEntity(entity);
		AIPath a = new AIPath(this);
		a.addPath(100, 500);
		a.addPath(400, 500);
		a.addPath(400, 700);
		a.addPath(650, 700);
		a.addPath(650, 450);
		setAIPath(a);
	}

	public void render() {
		super.render();
	}

	public void tick() {
		super.tick();
	}

}
