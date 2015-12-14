package com.twotris.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.twotris.game.gamescreen.GameScreenManager;


public class Twotris extends ApplicationAdapter {

	private GameScreenManager gsm;

	public void create () {
		gsm = GameScreenManager.getInstance();

		Gdx.input.setInputProcessor(new InputProcessor() {
			@Override
			public boolean keyDown(int keycode) {
				gsm.keyDown(keycode);
				return false;
			}

			@Override
			public boolean keyUp(int keycode) {
				gsm.keyUp(keycode);
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				return false;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				return false;
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				return false;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				return false;
			}

			@Override
			public boolean scrolled(int amount) {
				return false;
			}
		});

	}

	public void render () {

		gsm.update();
	}

}
