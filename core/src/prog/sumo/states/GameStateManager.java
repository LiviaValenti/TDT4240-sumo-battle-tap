package prog.sumo.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();
    }

    /**
     * This method pushes a new state onto the stack. Subclasses can use this
     * method to manage state transitions.
     *
     * @param state The state to be pushed onto the stack.
     */
    public void push(State state) {
        states.push(state);
    }

    /**
     * This method pops the current state from the stack. Subclasses can use
     * this method to manage state transitions.
     */
    public void pop() {
        states.pop().dispose();
    }

    /**
     * This method replaces the current state with a new state. Subclasses can
     * use this method to manage state transitions.
     *
     * @param state The new state to replace the current state.
     */
    public void set(State state) {
        states.pop().dispose();
        states.push(state);
    }

    /**
     * This method updates the state of the application based on the elapsed
     * time. Subclasses should override this method to provide their own update
     * logic.
     *
     * @param dt The elapsed time since the last update, in seconds.
     */
    public void update(float dt) {
        states.peek().update(dt);
    }

    /**
     * This method is responsible for rendering the current state to the screen.
     * Subclasses should override this method to provide their own rendering
     * logic.
     *
     * @param sb The SpriteBatch used for rendering.
     */
    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }
}
