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
 * Represents a king chess piece. See {@link Piece}.
 * 
 * @author Baris Sencan
 */
public class King extends Piece {

	public King(int x, int y, boolean isWhite) {
		super(x, y, isWhite, isWhite ? "white-king" : "black-king");

		/* Add valid moves. */
		this.validMoves.add(new Move(0, 1, false));
		this.validMoves.add(new Move(1, 1, false));
		this.validMoves.add(new Move(1, 0, false));
		this.validMoves.add(new Move(1, -1, false));
		this.validMoves.add(new Move(0, -1, false));
		this.validMoves.add(new Move(-1, -1, false));
		this.validMoves.add(new Move(-1, 0, false));
		this.validMoves.add(new Move(-1, 1, false));

		/* Castling moves. */
		this.validMoves.add(new Move(2, 0, false));
		this.validMoves.add(new Move(-2, 0, false));
	}

	@Override
	public void moved() {
		int x = (int) this.getX();

		/* Handle castling. */
		if (this.validMoves.size == 10) {
			this.validMoves.pop();
			this.validMoves.pop();

			if ((x == 6) || (x == 2)) {
				Board board = (Board) this.getParent();
				int y = (int) this.getY();
				Piece rook = (x == 2) ? board.getPieceAt(0, y) : board
						.getPieceAt(7, y);

				if ((rook == null) || !(rook instanceof Rook)) {
					return;
				}

				if (x == 2) {
					board.relocatePieceAt(0, y, 3, y);
				} else {
					board.relocatePieceAt(7, y, 5, y);
				}
			}
		}
	}

}
