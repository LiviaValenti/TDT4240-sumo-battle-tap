package prog.sumo.views;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class View {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameViewManager gsm;

    public View(GameViewManager gameViewManager) {
        this.gsm = gameViewManager;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput(String name);

    public abstract void update(float dt);

    public abstract void render(SpriteBatch sb);

    public abstract void dispose();
}
