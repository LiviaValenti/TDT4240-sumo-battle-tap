package prog.sumo.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameViewManager {
    private final Stack<View> views;

    public GameViewManager() {
        views = new Stack<View>();
    }

    /**
     * This method pushes a new view onto the stack. Subclasses can use this
     * method to manage view transitions.
     *
     * @param view The view to be pushed onto the stack.
     */
    public void push(View view) {
        views.push(view);
    }

    /**
     * This method pops the current state from the stack. Subclasses can use
     * this method to manage state transitions.
     */
    public void pop() {
        views.pop().dispose();
    }

    /**
     * This method replaces the current view with a new view. Subclasses can use
     * this method to manage view transitions.
     *
     * @param view The new view to replace the current view.
     */
    public void set(View view) {
        views.pop().dispose();
        views.push(view);
    }

    /**
     * This method updates the state of the application based on the elapsed
     * time. Subclasses should override this method to provide their own update
     * logic.
     *
     * @param dt The elapsed time since the last update, in seconds.
     */
    public void update(float dt) {
        views.peek().update(dt);
    }

    /**
     * This method is responsible for rendering the current state to the screen.
     * Subclasses should override this method to provide their own rendering
     * logic.
     *
     * @param sb The SpriteBatch used for rendering.
     */
    public void render(SpriteBatch sb) {
        views.peek().render(sb);
    }
}
