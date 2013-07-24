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

/**
 * A {@link Renderer} for {@link GameScreen}.
 * 
 * @author Baris Sencan
 */
public class GameRenderer implements Renderer {
	
	public GameRenderer() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.3f, .3f, .4f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void setSize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
