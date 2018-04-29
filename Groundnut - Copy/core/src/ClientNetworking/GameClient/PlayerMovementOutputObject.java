package ClientNetworking.GameClient;

import Input.InputSource;

import java.io.Serializable;

public class PlayerMovementOutputObject implements Serializable, InputSource {

    private static PlayerMovementOutputObject instance = null;
    //prevent instantiation.
    private PlayerMovementOutputObject(){}
    //make singleton TEMPORARY
    public static PlayerMovementOutputObject getInstance(){
        if(instance == null){
            instance = new PlayerMovementOutputObject();
        }
        return instance;
    }


    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
}
