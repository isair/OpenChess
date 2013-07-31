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

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.bsencan.openchess.OpenChess;
import com.bsencan.openchess.controller.BoardController;
import com.bsencan.openchess.model.pieces.Bishop;
import com.bsencan.openchess.model.pieces.King;
import com.bsencan.openchess.model.pieces.Knight;
import com.bsencan.openchess.model.pieces.Pawn;
import com.bsencan.openchess.model.pieces.Queen;
import com.bsencan.openchess.model.pieces.Rook;

/**
 * A chess board with pieces on it. Every instance of <code>Board</code> is
 * actually a playable chess game.
 * 
 * @author Baris Sencan
 */
public class Board extends Table {

	public Piece selectedPiece;
	public int round;

	/**
	 * Pointers to tiles for easy access.
	 */
	private final Tile[][] tiles = new Tile[8][8];

	/**
	 * Pointers to pieces for easy access.
	 */
	private final Piece[][] pieces = new Piece[8][8];

	/* -- Getters -- */

	public Tile getTileAt(int x, int y) {
		return this.tiles[x][y];
	}

	public Piece getPieceAt(int x, int y) {
		return this.pieces[x][y];
	}

	/* -- Getters -- */

	/**
	 * Creates an empty chess board then populates it.
	 */
	public Board() {
		/* Basic board setup. */
		this.setBounds(0, 0, OpenChess.UWIDTH, OpenChess.UWIDTH);
		this.setClip(true);
		this.addListener(new BoardController(this));

		/* Add tiles. */
		for (int i = 0; i < 8; i++) {

			for (int j = 0; j < 8; j++) {
				this.tiles[i][j] = new Tile(i, j, ((i + j) % 2) == 0);
				this.addActor(this.tiles[i][j]);
			}
		}

		this.populate();
	}

	/**
	 * Populates the <code>Board</code> with chess pieces in order to prepare it
	 * for a standard game of chess.
	 */
	public void populate() {
		/* Add pawns. */
		for (int i = 0; i < 8; i++) {
			this.addPiece(new Pawn(i, 1, true));
			this.addPiece(new Pawn(i, 6, false));
		}

		/* Add rooks. */
		this.addPiece(new Rook(0, 0, true));
		this.addPiece(new Rook(7, 0, true));
		this.addPiece(new Rook(0, 7, false));
		this.addPiece(new Rook(7, 7, false));

		/* Add knights. */
		this.addPiece(new Knight(1, 0, true));
		this.addPiece(new Knight(6, 0, true));
		this.addPiece(new Knight(1, 7, false));
		this.addPiece(new Knight(6, 7, false));

		/* Add bishops. */
		this.addPiece(new Bishop(2, 0, true));
		this.addPiece(new Bishop(5, 0, true));
		this.addPiece(new Bishop(2, 7, false));
		this.addPiece(new Bishop(5, 7, false));

		/* Add queens. */
		this.addPiece(new Queen(3, 0, true));
		this.addPiece(new Queen(3, 7, false));

		/* Add kings. */
		this.addPiece(new King(4, 0, true));
		this.addPiece(new King(4, 7, false));
	}

	/**
	 * Places a chess piece on this <code>Board</code>.
	 * 
	 * @param piece
	 *            Piece to place.
	 */
	public void addPiece(Piece piece) {
		this.addActor(piece);
		this.pieces[(int) piece.getX()][(int) piece.getY()] = piece;
	}

	/**
	 * Changes the location of a piece. Doesn't check if a piece exists at the
	 * given location.
	 * 
	 * @param xOld
	 *            Horizontal index of the piece's current tile.
	 * @param yOld
	 *            Vertical index of the piece's current tile.
	 * @param x
	 *            Horizontal index of the piece's new tile.
	 * @param y
	 *            Vertical index of the piece's new tile.
	 */
	public void relocatePieceAt(int xOld, int yOld, int x, int y) {
		this.pieces[x][y] = this.pieces[xOld][yOld];
		this.pieces[xOld][yOld] = null;
	}

	/**
	 * Removes a piece that is on a given tile location.
	 * 
	 * @param x
	 *            Horizontal index of the tile.
	 * @param y
	 *            Vertical index of the tile.
	 */
	public void removePieceAt(int x, int y) {
		Piece piece = this.pieces[x][y];

		if (piece != null) {
			piece.remove();
			this.pieces[x][y] = null;
		}
	}

	/**
	 * Checks if a tile is safe against any capture moves that can be made by an
	 * enemy piece.
	 * 
	 * @param x
	 *            Horizontal index of the tile.
	 * @param y
	 *            Vertical index of the tile.
	 * @param forWhite
	 *            Are we checking tile safety for white pieces?
	 * @return True if tile is safe.
	 */
	public boolean isTileSafe(int x, int y, boolean forWhite) {

		for (Piece[] row : this.pieces) {

			for (Piece piece : row) {

				/*
				 * If piece belongs to the opponent then check if it threatens
				 * this tile by tracing its possible capture moves.
				 */
				if ((piece != null) && (piece.isWhite != forWhite)) {
					Array<Tile> threatenedTiles;

					if (piece.canCaptureWithMove) {
						threatenedTiles = piece.getValidMoveTiles(this);
						threatenedTiles.addAll(piece.getCaptureOnlyTiles(this,
								false));
					} else {
						threatenedTiles = piece
								.getCaptureOnlyTiles(this, false);
					}

					for (Tile tile : threatenedTiles) {

						if ((x == tile.getX()) && (y == tile.getY())) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

}
