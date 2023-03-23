package prog.sumo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public final class SumoBattleTapGame extends ApplicationAdapter {
    /**
     * The ScoreAPI implementation to be used for managing player scores.
     */
    private final ScoreAPI scoreApi;
    /**
     * The SpriteBatch object used for drawing images to the screen.
     */
    private SpriteBatch batch;
    /**
     * The Texture object used for drawing the main game image to the screen.
     */
    private Texture img;

    /**
     * Constructs a new SumoBattleTapGame instance with the given ScoreAPI
     * implementation.
     *
     * @param scoreApiParam The ScoreAPI implementation to be used for managing
     *                      player scores.
     */
    public SumoBattleTapGame(final ScoreAPI scoreApiParam) {
        this.scoreApi = scoreApiParam;
    }

    /**
     * Called when the game is first created. Initializes the SpriteBatch and
     * Texture objects. To safely extend this class, override this method and
     * call super.create().
     */
    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }

    /**
     * Called on each frame render. Clears the screen, then draws the main game
     * image. To safely extend this class, override this method and call
     * super.render().
     */
    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

    /**
     * Called when the game is being disposed. Releases resources held by the
     * SpriteBatch and Texture objects. To safely extend this class, override
     * this method and call super.dispose().
     */
    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
