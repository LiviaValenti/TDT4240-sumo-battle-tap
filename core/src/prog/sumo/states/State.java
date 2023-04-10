package prog.sumo.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    public State(GameStateManager gameStateManager) {
        this.gsm = gameStateManager;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput(String name);

    public abstract void update(float dt);

    public abstract void render(SpriteBatch sb);

    public abstract void dispose();
}
