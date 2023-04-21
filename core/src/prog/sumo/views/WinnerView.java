package prog.sumo.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Timer;

import prog.sumo.SumoBattleTapGame;
import prog.sumo.models.Player;

public class WinnerView extends View {

    private final Player winner;
    ShapeRenderer shapeRenderer;
    private BitmapFont fontForWinner; // Font to draw the winner

    public WinnerView(GameViewManager gameViewManager, Player winner) {
        super(gameViewManager);
        this.winner = winner;
        shapeRenderer = new ShapeRenderer();
        SumoBattleTapGame.getScoreApi()
                .incrementScore(winner.getCharacter().getName());
    }

    @Override
    protected void handleInput(String name) {

    }

    @Override
    public void update(float dt) {

    }

    /**
     * Renders the winner screen. To safely extend this class, override this
     * method and call super.render(sb) at the end of the overriding method.
     *
     * @param sb the SpriteBatch used for rendering
     */
    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(252 / 255f, 231 / 255f, 239 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (fontForWinner == null) {
            fontForWinner = new BitmapFont();
            fontForWinner.getData().setScale(12f);
        }
        Matrix4 originalMatrix =
                sb.getTransformMatrix().cpy(); // Save the original matrix
        sb.setTransformMatrix(new Matrix4().setToRotation(0, 0, 1, 90));
        sb.begin();
        fontForWinner.setColor(Color.BLACK);
        fontForWinner.draw(sb,
                "Winner is " + this.winner.getCharacter().getName(),
                (Gdx.graphics.getWidth() + (fontForWinner.getCapHeight())) / 2f,
                -fontForWinner.getCapHeight());
        sb.end();
        sb.setTransformMatrix(originalMatrix); // Restore the original matrix
        Timer.schedule(new Timer.Task() {
            public void run() {
                gvm.set(new ScoreBoardView(gvm,
                        winner.getCharacter().getName()));


            }
        }, 3);
    }

    @Override
    public void dispose() {

    }
}
