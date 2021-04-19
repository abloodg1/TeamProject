package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.Slice.SliceState;
import com.mygdx.game.utils.Handler;

import java.util.ArrayList;

import javax.xml.soap.Text;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	private Handler handler;
	private SliceState sliceState;

	private static final float SCREEN_WIDTH = 800;
	private static final float SCREEN_HEIGHT = 1600;

	OrthographicCamera camera;


	@Override
	public void create() {

		handler = Handler.getInstance();

		handler.screenWidth = SCREEN_WIDTH;
		handler.screenHeight = SCREEN_HEIGHT;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
		handler.camera = camera;

		batch = new SpriteBatch();

		sliceState = new SliceState();
		handler.setActiveState(sliceState);

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
