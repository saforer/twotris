package com.twotris.game.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreenManager {
    static GameScreenManager instance;

    GameScreen currentScreen;
    SpriteBatch sb;

    public static GameScreenManager getInstance() {
        if (instance == null) {
            instance = new GameScreenManager();
        }
        return instance;
    }

    public GameScreenManager() {
        sb = new SpriteBatch();
        currentScreen = new MainMenu(instance);
    }

    public void update() {
        //Clear old before new
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();

        currentScreen.update(Gdx.graphics.getDeltaTime());
        currentScreen.render(sb);

        sb.end();
    }

    public void moveToScreen(int i) {
        switch (i) {
            default:
            case 0:
                currentScreen = new MainMenu(instance);
                break;
            case 1:
                currentScreen = new RunningScreen(instance);
                break;
            case 2:
                currentScreen = new GameOverScreen(instance);
                break;
        }

            currentScreen.init();
    }
}