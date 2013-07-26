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

package com.bsencan.openchess.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.bsencan.openchess.Assets;
import com.bsencan.openchess.OpenChess;

/**
 * TODO: Javadocs. Movements methods. Etc.
 * 
 * @author Baris Sencan
 */
public class Piece extends Actor {
	
	public int x;
	public int y;
	public boolean isWhite;
	
	/**
	 * An array that contains valid moves for the chess piece.
	 */
	protected Array<Vector2> validMoves = new Array<Vector2>();
	
	private TextureRegion textureRegion;
	
	public Piece(int x, int y, boolean isWhite, String regionName) {
		this.x = x;
		this.y = y;
		this.isWhite = isWhite;
		textureRegion = Assets.gameAtlas.findRegion(regionName);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		int m = OpenChess.PSIZE;
		
		super.draw(batch, parentAlpha);
		batch.draw(textureRegion, x * m, y * m, OpenChess.PSIZE, OpenChess.PSIZE);
	}
}
