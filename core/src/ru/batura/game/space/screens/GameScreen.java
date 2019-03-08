package ru.batura.game.space.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import ru.batura.game.space.SpaceGame;
import ru.batura.game.space.logic.GameLogic;
import ru.batura.game.space.map.Map;

public class GameScreen implements Screen {

    private SpaceGame game;
    public GameLogic logic;
    private  int screenWidth;
    private int  screenHeight;

    private SpriteBatch batch;

    public Map map;

    public GameScreen (SpaceGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        logic = new GameLogic(game,this);
        screenWidth = Gdx.graphics.getWidth();
        screenHeight  = Gdx.graphics.getHeight();
        map = new Map(new Vector2(screenWidth/2,screenHeight/2),150,150,4);

        startGame();
    }

    private void startGame() {
        logic.startGame();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        map.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
