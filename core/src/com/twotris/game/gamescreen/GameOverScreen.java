package com.twotris.game.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Created by Forer on 12/14/2015.
 */
public class GameOverScreen extends GameScreen{
    BitmapFont font = new BitmapFont();
    GlyphLayout layout = new GlyphLayout();
    public GameOverScreen(GameScreenManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {

    }

    @Override
    public void update(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.Z) && Gdx.input.isKeyPressed(Input.Keys.X)) {
            GameScreenManager.getInstance().moveToScreen(0);
        }
    }

    @Override
    public void keyDown(int key) {

    }

    @Override
    public void keyUp(int key) {

    }

    @Override
    public void render(SpriteBatch sb) {
        String toDisplay = "Game Over, Press Z + X to start again";
        layout.setText(font, toDisplay);
        font.draw(sb, toDisplay, (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() / 2);
    }
}
