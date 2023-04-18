package prog.sumo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Timer;

import jdk.tools.jmod.Main;
import prog.sumo.Player;
import prog.sumo.SumoBattleTapGame;

public class WinnerState extends State {

    private final Player winner;
    ShapeRenderer shapeRenderer;
    private BitmapFont font; // Font to draw the countdown
    private BitmapFont fontForWinner; // Font to draw the winner
    private SpriteBatch spriteBatch;

    public WinnerState(GameStateManager gameStateManager, Player winner) {
        super(gameStateManager);
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
        sb.setTransformMatrix(
                new Matrix4().setToRotation(0, 0, 1, 90));
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
                gsm.set(new ScoreBoardState(gsm,
                        winner.getCharacter().getName()));


            }
        }, 3);
    }

    @Override
    public void dispose() {

    }
}
