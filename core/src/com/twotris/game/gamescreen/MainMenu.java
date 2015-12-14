package com.twotris.game.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Forer on 12/14/2015.
 */
public class MainMenu extends GameScreen {
    BitmapFont font;
    GlyphLayout layout = new GlyphLayout();
    boolean started = false;
    int selectedMenuIcon = 0;

    public MainMenu(GameScreenManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        font = new BitmapFont();
    }

    @Override
    public void update(float dt) {
        if (!started && buttonsPressed()) {
            GameScreenManager.getInstance().moveToScreen(1);
        }



    }

    @Override
    public void render(SpriteBatch sb) {
        layout.setText(font, "Welcome to Twotris");
        font.draw(sb, "Welcome to Twotris", (Gdx.graphics.getWidth() - layout.width) / 2, (Gdx.graphics.getHeight() / 2) + 100);

        if (!started) {
            String toDisplay = "Press Z + X to start";
            layout.setText(font, toDisplay);
            font.draw(sb, toDisplay, (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() / 2);
        }
    }

    public boolean buttonsPressed() {
        if (Gdx.input.isKeyPressed(Input.Keys.Z) && Gdx.input.isKeyJustPressed(Input.Keys.X)) return true;
        if (Gdx.input.isKeyJustPressed(Input.Keys.Z) && Gdx.input.isKeyPressed(Input.Keys.X)) return true;
        return false;
    }
}
