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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bsencan.openchess.Assets;
import com.bsencan.openchess.OpenChess;
import com.bsencan.openchess.model.pieces.*;

/**
 * Chess board. Also manages all pieces, movements and win conditions in the
 * game. Simply put, manages all game logic.
 * 
 * @author Baris Sencan
 */
public class Board extends Table {
	
	private TextureRegion textureRegion;
	
	public Board() {
		setBounds(0, 0, OpenChess.UWIDTH, OpenChess.UWIDTH);
		setClip(true);
		textureRegion = Assets.gameAtlas.findRegion("board");
		
		/* Add pawns. */
		for (int i = 0; i < 8; i++) {
			addActor(new Pawn(i, 1, true));
			addActor(new Pawn(i, 6, false));
		}
		
		/* Add rooks. */
		addActor(new Rook(0, 0, true));
		addActor(new Rook(7, 0, true));
		addActor(new Rook(0, 7, false));
		addActor(new Rook(7, 7, false));
		
		/* Add knights. */
		addActor(new Knight(1, 0, true));
		addActor(new Knight(6, 0, true));
		addActor(new Knight(1, 7, false));
		addActor(new Knight(6, 7, false));
		
		/* Add bishops. */
		addActor(new Bishop(2, 0, true));
		addActor(new Bishop(5, 0, true));
		addActor(new Bishop(2, 7, false));
		addActor(new Bishop(5, 7, false));
		
		/* Add queens. */
		addActor(new Queen(3, 0, true));
		addActor(new Queen(3, 7, false));
		
		/* Add kings. */
		addActor(new King(4, 0, true));
		addActor(new King(4, 7, false));
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(textureRegion, 0, 0, OpenChess.UWIDTH, OpenChess.UWIDTH);    // Draw self.
		super.draw(batch, parentAlpha);                                         // Draw children.
	}
}
