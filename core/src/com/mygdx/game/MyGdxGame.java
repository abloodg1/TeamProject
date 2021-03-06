package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.End.EndState;
import com.mygdx.game.states.Slice.SliceState;
import com.mygdx.game.states.Title.TitleState;
import com.mygdx.game.utils.Handler;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	private Handler handler;
	private SliceState sliceState;
	private TitleState titleState;
	private EndState endState;
	private Texture background;

	private static final float SCREEN_WIDTH = 800;
	private static final float SCREEN_HEIGHT = 1600;

	OrthographicCamera camera;


	@Override
	public void create() {

		handler = Handler.getInstance();
		handler.screenWidth = SCREEN_WIDTH;
		handler.screenHeight = SCREEN_HEIGHT;
		Preferences preferences = Gdx.app.getPreferences("My Preferences");
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
		handler.camera = camera;
		batch = new SpriteBatch();
		sliceState = new SliceState();
		titleState = new TitleState();
		endState = new EndState(preferences);
		handler.sliceState = sliceState;
		handler.titleState = titleState;
		handler.endState = endState;
		handler.setActiveState(titleState);


	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		handler.activeState.render(batch);
		handler.activeState.tick();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
