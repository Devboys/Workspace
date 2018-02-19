package com.gdxtest1.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gdxtest1.game.GameClass;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "hello world";
        config.useGL30 = false;
        config.width = 700;
        config.height = 700;
        new LwjglApplication(new GameClass(), config);
    }
}
