package Core;

import Input.TestInputHandler;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

/** LibGDX main thread. Handles rendering and is also the start-point for the update thread.*/
public class PlayerInputTestThread extends ApplicationAdapter {

    @Override
    public void create () {
        Gdx.input.setInputProcessor(new TestInputHandler());
    }

    @Override
    public void render () {}

    @Override
    public void dispose () {}

    @Override
    public void resize(int width, int height){}

}
