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

package com.bsencan.openchess.view;

/**
 * An interface for all classes that needs to do rendering to a screen.
 * 
 * @author Baris Sencan
 */
public interface Renderer {

	/**
	 * Should be called when rendering needs to be done.
	 * 
	 * @param delta
	 *            The time in seconds since the last render.
	 */
	public void render(float delta);

	/**
	 * Should be called when device screen is resized.
	 * 
	 * @param width
	 *            The new width in pixels.
	 * @param height
	 *            The new height in pixels.
	 */
	public void setSize(int width, int height);

	/**
	 * Should be called when the renderer needs to release all resources.
	 */
	public void dispose();
}
