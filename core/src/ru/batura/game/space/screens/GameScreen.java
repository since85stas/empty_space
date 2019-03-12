package ru.batura.game.space.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Json;
import ru.batura.game.space.SpaceGame;
import ru.batura.game.space.dialogs.ActonDialog;
import ru.batura.game.space.logic.GameLogic;
import ru.batura.game.space.map.Map;
import ru.batura.game.space.map.RoomsFactory;
import ru.batura.game.space.player.Hero;
import ru.batura.game.space.utils.Assets;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class GameScreen implements Screen {

    private static final String TAG = GameScreen.class.getName();

    private SpaceGame game;
    public GameLogic logic;
    public Hero hero;
    private int  screenWidth;
    private int  screenHeight;

    private SpriteBatch batch;
    private Stage stage;

    public Map map;

    Skin mySkin;
    Label hpLable;
    Label sanityLable;

    public GameScreen (SpaceGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        logic = new GameLogic(game,this);
        screenWidth = Gdx.graphics.getWidth();
        screenHeight  = Gdx.graphics.getHeight();
        map = new Map(new Vector2(screenWidth/2,screenHeight/2),200,200,6, stage, this);
        hero = new Hero();

        mySkin = Assets.instance.skinAssets.skin;
        sanityLable = new Label(Integer.toString(hero.sanity),mySkin , "default");
        hpLable = new Label(Integer.toString(hero.health),mySkin , "default");

        // вывод для здоровья и разума
        VerticalGroup timeLableGroup = new VerticalGroup();
        timeLableGroup.setPosition(0,0);
        timeLableGroup.setSize(100, 200);
        Label titleLable = new Label("hp", mySkin, "small");
//        hpLable = new Label(Integer.toString(digit), mySkin, "game");
        float size1 = titleLable.getHeight();
        float size2 = hpLable.getHeight();
//        lableItemHeight = size1 + size2;
        timeLableGroup.addActor(titleLable);
        timeLableGroup.addActor(hpLable);

        VerticalGroup scoreLableGroup = new VerticalGroup();
        scoreLableGroup.setPosition(200,0);
        scoreLableGroup.setSize(100, 200);
        titleLable = new Label("san", mySkin, "small");
        scoreLableGroup.addActor(titleLable);
        scoreLableGroup.addActor(sanityLable);

        stage.addActor(scoreLableGroup);
        stage.addActor(timeLableGroup);


        startGame();
    }

    public void updateHeroStats() {
        sanityLable.setText(Integer.toString(hero.sanity));
        hpLable.setText(Integer.toString(hero.health));
    }


    private void startGame() {
        logic.startGame();

        Json json = new Json();
        RoomsFactory f = json.fromJson(RoomsFactory.class,Gdx.files.internal("roomsDescr/rooms_int.json"));
        Gdx.app.log(TAG,"json load");
//        ActonDialog dialog = new ActonDialog("text",mySkin,this);
//        dialog.show(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        map.render(batch);
        batch.end();

        stage.act();
        stage.draw();
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
