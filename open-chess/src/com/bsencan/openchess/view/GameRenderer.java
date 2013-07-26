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

package com.bsencan.openchess.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bsencan.openchess.OpenChess;
import com.bsencan.openchess.model.Board;

/**
 * A {@link Renderer} for {@link GameScreen}.
 * 
 * @author Baris Sencan
 */
public class GameRenderer implements Renderer {
	
	private Stage stage;
	private OrthographicCamera camera;
	
	public GameRenderer(Board board) {
		stage = new Stage();
		camera = new OrthographicCamera();
		stage.setCamera(camera);
		stage.addActor(board);
		// TODO: Init UI.
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.3f, .3f, .4f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.draw();
		// TODO: Draw UI.
	}

	@Override
	public void setSize(int width, int height) {
		// TODO: Position UI.
		camera.viewportWidth = OpenChess.UWIDTH;
		camera.viewportHeight = camera.viewportWidth * ((float) height / width);
		camera.position.set(OpenChess.UWIDTH / 2, camera.viewportHeight / 2, 0);
		camera.update();
	}

	@Override
	public void dispose() {
		// TODO: Dispose UI.
		stage.dispose();
	}

}
