/*
* Copyright 2013 Baris Sencan (baris.sencan@me.com)
*
* Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
* License. You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
* BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
* governing permissions and limitations under the License.
*/

package com.bsencan.openchess.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.bsencan.openchess.view.GameRenderer;

/**
 * 
 * @author Baris Sencan
 */
public class GameScreen implements Screen, GestureListener {
	
	private Game game;
	
	private GameRenderer renderer;
	
	/**
	 * Screen constructor.
	 * 
	 * @param game The parent {@link Game} instance.
	 */
	public GameScreen(Game game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		renderer.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		renderer.setSize(width, height);
	}

	@Override
	public void show() {
		renderer = new GameRenderer();
	}

	@Override
	public void hide() {
		renderer.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {} // Never called automatically.

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
