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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * <code>Assets</code> class is responsible for loading and disposing of all
 * game assets.
 * 
 * @author Baris Sencan
 */
public class Assets {
	
	public static Skin skin;
	public static Music menuMusic;
	public static TextureAtlas gameAtlas;
	
	/**
	 * Loads all assets required by {@link MainMenuScreen}.
	 */
	public static void loadMainMenu() {
		skin = new Skin(Gdx.files.internal("skin.json"));
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("music/fortress.ogg"));
	}
	
	/**
	 * Disposes of all assets required by {@link MainMenuScreen}.
	 */
	public static void disposeMainMenu() {
		skin.dispose();
		menuMusic.dispose();
	}
	
	/**
	 * Loads all assets required by {@link GameScreen}.
	 */
	public static void loadGame() {
		gameAtlas = new TextureAtlas(Gdx.files.internal("atlases/open-chess-atlas.atlas"));
	}
	
	/**
	 * Disposes of all assets required by {@link GameScreen}.
	 */
	public static void disposeGame() {
		gameAtlas.dispose();
	}
}
