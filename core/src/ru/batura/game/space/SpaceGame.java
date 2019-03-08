package ru.batura.game.space;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import ru.batura.game.space.screens.GameScreen;
import ru.batura.game.space.utils.Assets;

public class SpaceGame extends Game {
	Texture img;
	
	@Override
	public void create () {
		Assets.instance.init(new AssetManager());
		Assets.instance.getMainRes();
		setGameStcreen();
	}

	public void setGameStcreen() {
		setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		//update(dt);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		getScreen().render(dt);

	}
	
	@Override
	public void dispose () {
	}
}
