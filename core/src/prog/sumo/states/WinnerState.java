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

public class WinnerState extends State {

    private final String winner;
    ShapeRenderer shapeRenderer;
    private BitmapFont font; // Font to draw the countdown
    private BitmapFont fontForWinner; // Font to draw the winner
    private SpriteBatch spriteBatch;

    public WinnerState(GameStateManager gameStateManager, String winner) {
        super(gameStateManager);
        this.winner = winner;
        shapeRenderer = new ShapeRenderer();
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
            spriteBatch = new SpriteBatch();
        }
        spriteBatch.setTransformMatrix(
                new Matrix4().setToRotation(0, 0, 1, 90));
        spriteBatch.begin();
        fontForWinner.setColor(Color.BLACK);
        fontForWinner.draw(spriteBatch, "Winner is " + this.winner,
                (Gdx.graphics.getWidth() + (fontForWinner.getCapHeight())) / 2f,
                -fontForWinner.getCapHeight());
        spriteBatch.end();
        Timer.schedule(new Timer.Task() {
            public void run() {
                gsm.set(new ScoreBoardState(gsm, winner));
                

            }
        }, 3);
    }

    @Override
    public void dispose() {

    }
}
