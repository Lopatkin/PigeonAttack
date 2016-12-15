package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background bg;
	Hero hero;
	private final int PIG_COUNT = 5;
	Pigeon[] pigeons;
	private final int MAX_RADUGAS = 100;
	public static Raduga[] radugas;
	Texture texRaduga;
	private final int MAX_FXES = 20;
	public static MyFX[] fxes;

	@Override
	public void create() {
		batch = new SpriteBatch();
		bg = new Background();
		hero = new Hero(new Vector2(100, 100));
		pigeons = new Pigeon[PIG_COUNT];
		for (int i = 0; i < PIG_COUNT; i++) {
			pigeons[i] = new Pigeon();
		}
		radugas = new Raduga[MAX_RADUGAS];
		for (int i = 0; i < MAX_RADUGAS; i++) {
			radugas[i] = new Raduga();
		}
		texRaduga = new Texture("Raduga.png");
		fxes = new MyFX[MAX_FXES];
		for (int i = 0; i < MAX_FXES; i++) {
			fxes[i] = new MyFX();
		}
	}

	@Override
	public void render() {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1); // RGBA
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		bg.render(batch);
		hero.render(batch);
		for (int i = 0; i < PIG_COUNT; i++) {
			pigeons[i].render(batch);
		}
		for (int i = 0; i < MAX_RADUGAS; i++) {
			if (radugas[i].isActive())
				batch.draw(texRaduga, radugas[i].getPosition().x, radugas[i].getPosition().y);
		}
		for (int i = 0; i < MAX_FXES; i++) {
			fxes[i].render(batch);
		}
		batch.end();
	}

	public void update() {
		bg.update();
		hero.update();
		for (int i = 0; i < PIG_COUNT; i++) {
			pigeons[i].update();
		}
		for (int i = 0; i < MAX_RADUGAS; i++) {
			if (radugas[i].isActive())
				radugas[i].update();
		}
		for (int i = 0; i < MAX_RADUGAS; i++) {
			if (radugas[i].isActive()) {
				for (int j = 0; j < PIG_COUNT; j++) {
					if (pigeons[j].getRect().contains(radugas[i].getPosition())) {
						pigeons[j].recreate();
						radugas[i].destroy();

						for (int k = 0; k < MAX_FXES; k++) {
							if (!fxes[k].isActive()) {
								fxes[k].setup(radugas[i].getPosition().x, radugas[i].getPosition().y);
								break;
							}
						}

						break;
					}
				}
			}
		}
		for (int i = 0; i < MAX_FXES; i++) {
			fxes[i].update();
		}
	}
}
