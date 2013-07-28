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

/**
 * Defines a type of move that a chess piece can make.
 * 
 * @author Baris Sencan
 */
public class Move {

	public int xOffset;
	public int yOffset;
	public boolean continuous;

	/**
	 * Creates a move. When creating a new <code>Move</code> instance, keep in
	 * mind that they are created according to white chess pieces. Y coordinate
	 * inversion for black pieces is done automatically.
	 * 
	 * @param xOffset
	 *            Horizontal tile offset.
	 * @param yOffset
	 *            Vertical tile offset.
	 * @param continuous
	 *            If true, than xOffset and yOffset are interpreted as unit
	 *            vector components which show a direction that can be moved on
	 *            continuously.
	 */
	public Move(int xOffset, int yOffset, boolean continuous) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.continuous = continuous;
	}
}
