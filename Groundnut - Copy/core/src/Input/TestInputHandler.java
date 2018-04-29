package Input;

import ClientNetworking.GameClient.PlayerMovementOutputObject;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class TestInputHandler extends InputAdapter{

    PlayerMovementOutputObject outputObject = PlayerMovementOutputObject.getInstance();

    @Override
    public boolean keyDown(int keyCode){
        switch (keyCode){
            case Input.Keys.W:
                outputObject.up = true;
                break;
            case Input.Keys.S:
                outputObject.down = true;
                break;
            case Input.Keys.D:
                outputObject.right = true;
                break;
            case Input.Keys.A:
                outputObject.left = true;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keyCode){
        switch (keyCode){
            case Input.Keys.W:
                outputObject.up = false;
                break;
            case Input.Keys.S:
                outputObject.down = false;
                break;
            case Input.Keys.D:
                outputObject.right = false;
                break;
            case Input.Keys.A:
                outputObject.left = false;
                break;
        }
        return true;
    }
}
