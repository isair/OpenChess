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

import com.bsencan.openchess.model.Piece;

/**
 * Represents a bishop chess piece.
 * 
 * @author Baris Sencan
 */
public class Bishop extends Piece {

	public Bishop(int x, int y, boolean isWhite) {
		super(x, y, isWhite, isWhite ? "white-bishop" : "black-bishop");
	}

}
