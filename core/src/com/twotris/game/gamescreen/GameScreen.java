package com.twotris.game.gamescreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameScreen {
	GameScreenManager gsm;
	public GameScreen (GameScreenManager gsm) {
		this.gsm  = gsm;
		init();
	}

	public abstract void init();
	public abstract void update (float dt);
	public abstract void render(SpriteBatch sb);
}