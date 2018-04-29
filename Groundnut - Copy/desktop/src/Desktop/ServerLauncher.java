package Desktop;

import Core.GameThread;
import Scenes.GameStateManager;
import ServerNetworking.GameServer.ServerHandler;
import ServerNetworking.GameServer.ServerInputThread;
import ServerNetworking.GameServer.ServerOutputThread;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.io.IOException;

public class ServerLauncher {

    public static void main(String[] args){

        //start serverside simulation
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new GameThread(), config);

        //setup input/output threads
        ServerHandler serverHandler = new ServerHandler();

        ServerOutputThread serverOutput = null;
        try {
            serverOutput = new ServerOutputThread();
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverOutput.start();

        ServerInputThread serverInput = new ServerInputThread();
        serverInput.start();
    }
}