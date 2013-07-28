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

	private final Tile[][] tiles = new Tile[8][8];
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

		/* Add pawns. */
		for (int i = 0; i < 8; i++) {
			this.addActor(new Pawn(i, 1, true));
			this.addActor(new Pawn(i, 6, false));
		}

		/* Add rooks. */
		this.addActor(new Rook(0, 0, true));
		this.addActor(new Rook(7, 0, true));
		this.addActor(new Rook(0, 7, false));
		this.addActor(new Rook(7, 7, false));

		/* Add knights. */
		this.addActor(new Knight(1, 0, true));
		this.addActor(new Knight(6, 0, true));
		this.addActor(new Knight(1, 7, false));
		this.addActor(new Knight(6, 7, false));

		/* Add bishops. */
		this.addActor(new Bishop(2, 0, true));
		this.addActor(new Bishop(5, 0, true));
		this.addActor(new Bishop(2, 7, false));
		this.addActor(new Bishop(5, 7, false));

		/* Add queens. */
		this.addActor(new Queen(3, 0, true));
		this.addActor(new Queen(3, 7, false));

		/* Add kings. */
		this.addActor(new King(4, 0, true));
		this.addActor(new King(4, 7, false));
	}
}
