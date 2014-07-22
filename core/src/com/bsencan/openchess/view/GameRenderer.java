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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bsencan.openchess.Assets;
import com.bsencan.openchess.OpenChess;
import com.bsencan.openchess.model.Board;
import com.bsencan.openchess.screens.GameScreen;

/**
 * A {@link Renderer} for {@link GameScreen}.
 * 
 * @author Baris Sencan
 */
public class GameRenderer implements Renderer {

	private final Stage stage = new Stage(new FitViewport(8, 10));
	private Table hud;
	private TextButton resetButton;

	public GameRenderer(Board board) {
		Gdx.input.setInputProcessor(this.stage);
		this.stage.addActor(board);
		this.initUI();
	}

	private void initUI() {
		this.hud = new Table(Assets.skin);

		this.resetButton = new TextButton(" Reset Game ", Assets.skin);
		this.resetButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				OpenChess.game.setScreen(new GameScreen());
			}
		});

		this.hud.add(this.resetButton);
		this.hud.setTransform(true);
		this.hud.setScale(1 / this.resetButton.getHeight());
		this.hud.setPosition(4, 9);
		this.stage.addActor(this.hud);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.3f, .3f, .4f, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		this.stage.draw();
	}

	@Override
	public void setSize(int width, int height) {
		this.stage.getViewport().update(width, height, false);
		Gdx.graphics.requestRendering();
	}

	@Override
	public void dispose() {
		this.stage.dispose();
	}

}
