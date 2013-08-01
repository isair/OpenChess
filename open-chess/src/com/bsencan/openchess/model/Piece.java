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
	protected Array<Move> validMoves = new Array<Move>();

	/**
	 * An array that contains moves for the chess piece that are valid only when
	 * used for capturing other pieces.
	 */
	protected Array<Move> captureOnlyMoves = new Array<Move>();

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
		this.setBounds(x, y, 1, 1);
		this.isWhite = isWhite;
		this.textureRegion = Assets.gameAtlas.findRegion(regionName);
	}

	/**
	 * Returns tiles on a board that can be accessed by this
	 * <code>Piece<code> instance according to its <code>validMoves</code>
	 * array.
	 * 
	 * @param board
	 *            The <code>Board</code> instance to fetch tiles from.
	 * @param checkFriendly
	 * @return Resulting tile array.
	 */
	public Array<Tile> getValidMoveTiles(Board board, boolean checkFriendly) {
		Array<Tile> tiles = new Array<Tile>();
		int x = (int) this.getX();
		int y = (int) this.getY();

		for (Move move : this.validMoves) {
			boolean isLooping = true;

			for (int i = 1; isLooping; i++) {
				int tx = x + (move.xOffset * i); // Tile x.
				int ty = y
						+ ((this.isWhite ? move.yOffset : -move.yOffset) * i); // Tile
																				// y.

				if ((tx > -1) && (tx < 8) && (ty > -1) && (ty < 8)) {
					Tile tile = board.getTileAt(tx, ty);
					Piece otherPiece = board.getPieceAt(tx, ty);

					if (otherPiece != null) {

						if ((!checkFriendly || (otherPiece.isWhite != this.isWhite))
								&& this.canCaptureWithMove) {
							tiles.add(tile);
						}
						isLooping = false;
					} else {
						tiles.add(tile);
					}
				} else {
					isLooping = false;
				}

				if (!move.continuous) {
					isLooping = false;
				}
			}
		}
		return tiles;
	}

	/**
	 * Returns tiles on a board that can be accessed by this
	 * <code>Piece<code> instance according to its <code>captureOnlyMoves</code>
	 * array.
	 * 
	 * @param board
	 *            The <code>Board</code> instance to fetch tiles from.
	 * @param check
	 *            Check the validity of the capturing move before adding the
	 *            tile to the list. e.g: If a capturable piece is present.
	 * @return Resulting tile array.
	 */
	public Array<Tile> getCaptureOnlyTiles(Board board, boolean check) {
		Array<Tile> tiles = new Array<Tile>();
		int x = (int) this.getX();
		int y = (int) this.getY();

		for (Move move : this.captureOnlyMoves) {
			int tx = x + move.xOffset; // Tile x.
			int ty = y + (this.isWhite ? move.yOffset : -move.yOffset); // Tile
																		// y.
			if ((tx > -1) && (tx < 8) && (ty > -1) && (ty < 8)) {
				Tile tile = board.getTileAt(tx, ty);
				Piece otherPiece = board.getPieceAt(tx, ty);

				if (!check
						|| ((otherPiece != null) && (otherPiece.isWhite != this.isWhite))) {
					tiles.add(tile);
				}
			}
		}
		return tiles;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(this.textureRegion, this.getX(), this.getY(), 1, 1);
	}

}
