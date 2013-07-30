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
import com.bsencan.openchess.model.Board;
import com.bsencan.openchess.model.Move;
import com.bsencan.openchess.model.Piece;
import com.bsencan.openchess.model.Tile;

/**
 * This class is under heavy development. As it keeps changing with rapid speed,
 * it'll only receive proper Javadoc and normal comments when it's close to
 * completion.
 * 
 * @author Baris Sencan
 */
public class BoardController extends ActorGestureListener {

	private final Board board;

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

	private void movePiece(Piece selectedPiece, int x, int y) {

		/* Check movement validity. */
		if ((selectedPiece == null)
				|| !this.board.getTileAt(x, y).isHighlighted) {
			return;
		}

		int xOld = (int) selectedPiece.getX();
		int yOld = (int) selectedPiece.getY();

		/* Remove highlights. */
		this.toggleMoveHighlightsForPiece(selectedPiece);

		/* Capture. */
		if (this.board.getPieceAt(x, y) != null) {
			this.board.removePieceAt(x, y);
		}

		/* Move. */
		this.board.relocatePieceAt(xOld, yOld, x, y);
		selectedPiece.setX(x);
		selectedPiece.setY(y);

		/* Deselect and advance round. */
		this.board.selectedPiece = null;
		this.board.round++;
	}

	private void selectPiece(Piece piece) {

		if (this.board.selectedPiece != null) {
			this.toggleMoveHighlightsForPiece(this.board.selectedPiece);
		}
		this.board.selectedPiece = piece;
		this.toggleMoveHighlightsForPiece(piece);
	}

	// TODO: Optimize before writing javadoc comments for this.
	private void toggleMoveHighlightsForPiece(Piece piece) {
		int x = (int) piece.getX();
		int y = (int) piece.getY();

		for (Move move : piece.validMoves) {
			boolean isLooping = true;

			for (int i = 1; isLooping; i++) {
				int hX = x + (move.xOffset * i); // Highlight x.
				int hY = y
						+ ((piece.isWhite ? move.yOffset : -move.yOffset) * i); // Highlight
																				// y.

				if ((hX > -1) && (hX < 8) && (hY > -1) && (hY < 8)) {
					Tile tile = this.board.getTileAt(hX, hY);
					Piece otherPiece = this.board.getPieceAt(hX, hY);

					if (otherPiece != null) {

						if ((otherPiece.isWhite != piece.isWhite)
								&& piece.canCaptureWithMove) {
							tile.isHighlighted = !tile.isHighlighted;
						}
						isLooping = false;
					} else {
						tile.isHighlighted = !tile.isHighlighted;
					}
				} else {
					isLooping = false;
				}

				if (!move.continuous) {
					isLooping = false;
				}
			}
		}

		for (Move move : piece.captureOnlyMoves) {
			int hX = x + move.xOffset; // Highlight x.
			int hY = y + (piece.isWhite ? move.yOffset : -move.yOffset); // Highlight
																			// y.
			if ((hX > -1) && (hX < 8) && (hY > -1) && (hY < 8)) {
				Tile tile = this.board.getTileAt(hX, hY);
				Piece otherPiece = this.board.getPieceAt(hX, hY);

				if ((otherPiece != null)
						&& (otherPiece.isWhite != piece.isWhite)) {

					tile.isHighlighted = !tile.isHighlighted;
				}
			}
		}
	}

}
