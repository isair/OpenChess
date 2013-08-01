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

package com.bsencan.openchess.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Array;
import com.bsencan.openchess.model.Board;
import com.bsencan.openchess.model.Piece;
import com.bsencan.openchess.model.Tile;

/**
 * This class is under heavy development. As it keeps changing with rapid speed,
 * it'll only receive proper javadoc comments when it's close to completion.
 * 
 * @author Baris Sencan
 */
public class BoardController extends ActorGestureListener {

	private final Board board;
	private final Array<Tile> highlightedTiles = new Array<Tile>();

	public BoardController(Board board) {
		this.board = board;
	}

	@Override
	public void tap(InputEvent event, float x, float y, int count, int button) {
		Actor target = event.getTarget(); // Tapped actor.
		int tx = (int) target.getX(); // Tapped tile x.
		int ty = (int) target.getY(); // Tapped tile y.

		if (target.getClass().getSuperclass().equals(Piece.class)) {
			Piece piece = (Piece) target;

			if ((((this.board.round % 2) == 0) && piece.isWhite)
					|| (((this.board.round % 2) == 1) && !piece.isWhite)) {
				this.selectPiece(piece);
			} else {
				this.movePiece(this.board.selectedPiece, tx, ty);
			}
		} else {
			this.movePiece(this.board.selectedPiece, tx, ty);
		}
	}

	private void movePiece(Piece piece, int x, int y) {

		/* Check move validity. */
		if ((piece == null) || !this.board.getTileAt(x, y).isHighlighted) {
			return;
		}

		int xOld = (int) piece.getX();
		int yOld = (int) piece.getY();

		/* Remove highlights. */
		this.removeMoveHighlights();

		/* Capture. */
		if (this.board.getPieceAt(x, y) != null) {
			this.board.removePieceAt(x, y);
		}

		/* Move. */
		this.board.relocatePieceAt(xOld, yOld, x, y);
		this.board.selectedPiece.moved();

		// TODO: Special move checks. (En passant, etc.)

		/* Deselect and advance round. */
		this.board.selectedPiece = null;
		this.board.round++;
	}

	private void selectPiece(Piece piece) {
		this.removeMoveHighlights();
		this.board.selectedPiece = piece;
		this.addMoveHighlightsForPiece(piece);
	}

	// TODO: Complete before writing javadoc comments for this.
	private void addMoveHighlightsForPiece(Piece piece) {
		Array<Tile> tiles = piece.getValidMoveTiles(this.board, true);

		tiles.addAll(piece.getCaptureOnlyTiles(this.board, true));

		for (Tile tile : tiles) {
			int tx = (int) tile.getX();
			int ty = (int) tile.getY();

			/* Make sure the move doesn't put the king in check. */
			if (this.board.isMoveSafe(piece, tx, ty)) {
				this.highlightedTiles.add(tile);
				tile.isHighlighted = true;
			}
		}
	}

	private void removeMoveHighlights() {

		while (this.highlightedTiles.size > 0) {
			this.highlightedTiles.pop().isHighlighted = false;
		}
	}

}
