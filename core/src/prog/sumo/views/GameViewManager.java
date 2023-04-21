package prog.sumo.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameViewManager {
    private final Stack<View> views;

    public GameViewManager() {
        views = new Stack<>();
    }

    /**
     * This method pushes a new View onto the stack. Subclasses can use this
     * method to manage View transitions.
     *
     * @param view The View to be pushed onto the stack.
     */
    public void push(View view) {
        views.push(view);
    }

    /**
     * This method pops the current view from the stack. Subclasses can use this
     * method to manage view transitions.
     */
    public void pop() {
        views.pop().dispose();
    }

    /**
     * This method replaces the current View with a new View. Subclasses can use
     * this method to manage View transitions.
     *
     * @param view The new View to replace the current View.
     */
    public void set(View view) {
        views.pop().dispose();
        views.push(view);
    }

    /**
     * This method updates the view of the application based on the elapsed
     * time. Subclasses should override this method to provide their own update
     * logic.
     *
     * @param dt The elapsed time since the last update, in seconds.
     */
    public void update(float dt) {
        views.peek().update(dt);
    }

    /**
     * This method is responsible for rendering the current view to the screen.
     * Subclasses should override this method to provide their own rendering
     * logic.
     *
     * @param sb The SpriteBatch used for rendering.
     */
    public void render(SpriteBatch sb) {
        views.peek().render(sb);
    }
}
