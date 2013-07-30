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

package com.bsencan.openchess;

import com.badlogic.gdx.Game;
import com.bsencan.openchess.screens.MainMenuScreen;

/**
 * Main game class. Used just for transitioning between different screens.
 * 
 * @author Baris Sencan
 */
public class OpenChess extends Game {

	/**
	 * Provides global access to the active <code>Game</code> instance.
	 */
	public static Game game;

	/**
	 * Unit width of the screen. Everything in this game is rendered in game
	 * units instead of pixels for scalability. This variable determines the
	 * width of the screen in terms of game units. It is important to note that
	 * each chess piece is 1 unit wide and 1 unit long.
	 */
	public static final int UWIDTH = 8;

	@Override
	public void create() {
		game = this;
		this.setScreen(new MainMenuScreen());
	}

}
