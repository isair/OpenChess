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

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

/**
 * Generates an atlas from game textures. Should be run whenever there is a
 * change in the <code>textures</code> folder that needs to be reflected in the
 * game.
 * 
 * @author Baris Sencan
 */
public class TexturePacker {

	public static void main(String[] args) {
		Settings settings = new Settings();
		String source = "../textures";
		String destination = "../open-chess-android/assets/atlases";
		String atlasName = "open-chess-atlas";

		settings.maxWidth = 512;
		settings.maxHeight = 512;
		TexturePacker2.process(settings, source, destination, atlasName);
	}

}
