package prog.sumo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import prog.sumo.views.GameViewManager;
import prog.sumo.views.MainMenuView;

public final class SumoBattleTapGame extends ApplicationAdapter {
    public static final String TITLE = "Sumo";
    /**
     * The ScoreAPI implementation to be used for managing player scores.
     */
    private static ScoreAPI scoreApi;
    /**
     * The SpriteBatch object used for drawing images to the screen.
     */
    private SpriteBatch spriteBatch;
    private GameViewManager gameViewManager;

    /**
     * Constructs a new SumoBattleTapGame instance with the given ScoreAPI
     * implementation.
     *
     * @param scoreApiParam The ScoreAPI implementation to be used for managing
     *                      player scores.
     */
    public SumoBattleTapGame(final ScoreAPI scoreApiParam) {
        scoreApi = scoreApiParam;
    }

    public static ScoreAPI getScoreApi() {
        return scoreApi;
    }

    /**
     * Called when the game is first created. Initializes the SpriteBatch and
     * Texture objects. To safely extend this class, override this method and
     * call super.create().
     */
    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        gameViewManager = new GameViewManager();
        gameViewManager.push(new MainMenuView(gameViewManager));
    }

    /**
     * Called on each frame render. Clears the screen, then draws the main game
     * image. To safely extend this class, override this method and call
     * super.render().
     */
    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);
        gameViewManager.update(Gdx.graphics.getDeltaTime());
        gameViewManager.render(spriteBatch);
    }

    /**
     * Called when the game is being disposed. Releases resources held by the
     * SpriteBatch and Texture objects. To safely extend this class, override
     * this method and call super.dispose().
     */
    @Override
    public void dispose() {
        spriteBatch.dispose();
    }
}
