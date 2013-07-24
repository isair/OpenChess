package com.bsencan.openchess;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Open Chess";
		cfg.useGL20 = false;
		cfg.width = 960;
		cfg.height = 540;
		
		new LwjglApplication(new OpenChess(), cfg);
	}
}
