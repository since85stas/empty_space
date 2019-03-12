package ru.batura.game.space.dialogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import ru.batura.game.space.map.Room;
import ru.batura.game.space.screens.GameScreen;

public class ActonDialog extends Dialog {
    GameScreen gameScreen;

    public int WIDTH =(int)( Gdx.graphics.getWidth()*0.90);
    public int HEIGHT =(int)( Gdx.graphics.getHeight()*0.6);

    public ActonDialog(String title, WindowStyle windowStyle) {
        super(title, windowStyle);
    }

    public ActonDialog(String title, Skin skin, Room room, GameScreen gameScreen) {
        super(title, skin);
        this.gameScreen = gameScreen;
        getContentTable().defaults().width(WIDTH - 0.05f*WIDTH);

        String descrStr = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "dsddddddddddddddddddddddddddddddddddddddddddddddddddddddsffs";
        Label descrLable = new Label(descrStr,skin,"small");
        descrLable.setWrap(true);
        getContentTable().add(descrLable);

        getContentTable().row();
        getContentTable().row();
        String var = "1: var 1" ;
        Label varLable = new Label(var,skin,"small");
        varLable.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("action dialog","label 1");
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        varLable.setWrap(true);
        getContentTable().add(varLable);

        getContentTable().row();
        var = "2: var 2" ;
        varLable = new Label(var,skin,"small");
        varLable.setWrap(true);
        getContentTable().add(varLable);

    }

    @Override
    public float getPrefWidth() {
        return WIDTH;
    }

    @Override
    public float getPrefHeight() {
        return HEIGHT;
    }

    @Override
    protected void result(Object object) {

    }

    @Override
    public void hide() {
        super.hide();
    }
}
