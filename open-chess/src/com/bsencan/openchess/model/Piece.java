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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.bsencan.openchess.Assets;
import com.bsencan.openchess.OpenChess;

/**
 * <code>Piece</code> is a simple {@link Actor} implementation of a chess piece.
 * 
 * @author Baris Sencan
 */
public class Piece extends Actor {

	public boolean isWhite;
	public boolean canCaptureWithMove = true; // Can this piece capture an enemy
												// piece using one of its normal
												// moves?

	/**
	 * An array that contains valid moves for the chess piece.
	 */
	public Array<Move> validMoves = new Array<Move>();

	/**
	 * An array that contains moves for the chess piece that are valid only when
	 * used for capturing other pieces.
	 */
	public Array<Move> captureOnlyMoves = new Array<Move>();

	private final TextureRegion textureRegion;

	/**
	 * Creates a new chess piece on a tile.
	 * 
	 * @param x
	 *            Horizontal index of the tile.
	 * @param y
	 *            Vertical index of the tile.
	 * @param isWhite
	 *            Determines whether the chess piece is white or black.
	 * @param regionName
	 *            Name of the <code>TextureRegion</code> from the game atlas
	 *            that graphically represents this chess piece.
	 */
	public Piece(int x, int y, boolean isWhite, String regionName) {
		int ps = OpenChess.PSIZE;

		this.setBounds(x * ps, y * ps, ps, ps);
		this.isWhite = isWhite;
		this.textureRegion = Assets.gameAtlas.findRegion(regionName);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(this.textureRegion, this.getX(), this.getY(),
				this.getWidth(), this.getHeight());
	}

}
