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
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bsencan.openchess.Assets;
import com.bsencan.openchess.screens.MainMenuScreen;

/**
 * A {@link Renderer} for {@link MainMenuScreen}.
 * 
 * @author Baris Sencan
 */
public class MainMenuRenderer implements Renderer {

	private final Stage stage = new Stage(new FitViewport(480, 320));

	private Label titleLabel;
	private Label startLabel;

	public MainMenuRenderer() {
		this.initUI();
	}

	/**
	 * Initializes UI elements.
	 */
	public void initUI() {
		this.titleLabel = new Label("Open Chess", Assets.skin, "title");
		this.titleLabel.setWrap(true);

		this.startLabel = new Label("Tap to Play", Assets.skin);
		this.startLabel.setWrap(true);

		this.stage.addActor(this.titleLabel);
		this.stage.addActor(this.startLabel);
	}

	/**
	 * Positions UI elements.
	 */
	public void positionUI() {
		float cX = this.stage.getWidth() / 2; // X coordinate of screen's
												// center.
		float cY = this.stage.getHeight() / 2; // Y coordinate of screen's
												// center.

		this.titleLabel.setPosition(cX - (this.titleLabel.getWidth() / 2), cY);
		this.startLabel.setPosition(cX - (this.startLabel.getWidth() / 2), cY
				- this.startLabel.getHeight());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.3f, .3f, .4f, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		this.stage.draw();
	}

	@Override
	public void setSize(int width, int height) {
		this.stage.getViewport().update(width, height);
		this.positionUI();
		Gdx.graphics.requestRendering();
	}

	@Override
	public void dispose() {
		this.stage.dispose();
	}

}
