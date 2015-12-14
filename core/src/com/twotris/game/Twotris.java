package com.twotris.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.twotris.game.gamescreen.GameScreenManager;


public class Twotris extends ApplicationAdapter {

	private GameScreenManager gsm;

	public void create () {
		gsm = GameScreenManager.getInstance();
	}

	public void render () {

		gsm.update();
	}

}
