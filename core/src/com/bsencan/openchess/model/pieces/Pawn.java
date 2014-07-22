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

package com.bsencan.openchess.model.pieces;

import com.bsencan.openchess.model.Board;
import com.bsencan.openchess.model.Move;
import com.bsencan.openchess.model.Piece;

/**
 * Represents a pawn chess piece. See {@link Piece}.
 * 
 * @author Baris Sencan
 */
public class Pawn extends Piece {

	public Pawn(int x, int y, boolean isWhite) {
		super(x, y, isWhite, isWhite ? "white-pawn" : "black-pawn");
		this.canCaptureWithMove = false;

		/* Add valid moves. */
		this.validMoves.add(new Move(0, 1, false));
		this.validMoves.add(new Move(0, 2, false));

		/* Add capture-only moves. */
		this.captureOnlyMoves.add(new Move(1, 1, false));
		this.captureOnlyMoves.add(new Move(-1, 1, false));
	}

	@Override
	public void moved() {
		Board board = (Board) this.getParent();
		int x = (int) this.getX();
		int y = (int) this.getY();

		/* Ensure pawns can move 2 tiles forward the first time only. */
		if (this.validMoves.size == 2) {
			this.validMoves.pop();
		}

		/* Pawn promotion. */
		if ((this.isWhite && (y == 7)) || (!this.isWhite && (y == 0))) {
			board.removePieceAt(x, y);
			board.addPiece(new Queen(x, y, this.isWhite));
		}
	}
}
