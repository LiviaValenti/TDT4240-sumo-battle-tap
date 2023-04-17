package prog.sumo.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import prog.sumo.Player;

public class PlayState extends State {
    private final float COUNTDOWN_TIME = 3f; // Countdown time in seconds
    private float timeElapsed = 0f; // Time elapsed since countdown started
    private BitmapFont font; // Font to draw the countdown
    private BitmapFont fontForScore; // Font to draw the countdown
    Texture settingsWheel;
    Texture player1Tex;
    Texture player2Tex;

    Sprite player1sprite;
    Sprite player2sprite;
    ShapeRenderer shapeRenderer;

    Drawable settingsWheelDrawable;
    private float countdownStartTime;

    ImageButton settingsB;

    Stage stage;
    Player player1;
    Player player2;

    // The following should be changed to Player objects when that part is ready
    String winnerOfTheRound = "";
    String winnerOfTheGame = "";

    int roundCounter;
    private final static int MAX_ROUNDS = 3;
    boolean isGameOver;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        shapeRenderer = new ShapeRenderer();
        settingsWheel = new Texture("settingswheel.png");
        player1Tex = new Texture("purplehand.png");
        player2Tex = new Texture("greenhand.png");
        player1sprite = new Sprite(player1Tex);
        player2sprite = new Sprite(player2Tex);

        settingsWheelDrawable = new TextureRegionDrawable(settingsWheel);
        // Player 1 starts from the top
        player1 = new Player(player1Tex, 0);
        // Player 2 starts from the bottom
        player2 = new Player(player2Tex, 1);
        settingsB = new ImageButton(settingsWheelDrawable);
        roundCounter = 0;
        stage = new Stage();
        stage.addActor(settingsB);

        settingsB.setPosition(Gdx.graphics.getWidth() - settingsB.getWidth(),
                Gdx.graphics.getHeight() / 2f - settingsB.getHeight() / 2);
        settingsB.setTransform(true);

        Gdx.input.setInputProcessor(stage);
        //     roundIsOver = false;
        isGameOver = false;
        countdownStartTime = 0f;

        settingsB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("settingsB");
            }
        });
    }

    @Override
    protected final void handleInput(String name) {
        if (name.equals("settingsB")) {
            gsm.set(new MainMenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {
    }

    public String getWinnerOfTheGame() {
        return winnerOfTheGame;
    }

    private void incrementScoreOfWinner() {
        // TODO: winner is a string now, but needs to be changed to the Player object
        // We need to determine how to keep track of the winner of the round. But that is another task
        if (winnerOfTheRound.equals("Player1")) {
            player1.incrementScore();
        } else {
            player2.incrementScore();
        }
    }

    // The following method should be used when a round is finished
    public void whenRoundFinished(SpriteBatch sb) {
        incrementRoundCounter();
        incrementScoreOfWinner();
        checkIfGameIsFinished();
        if (!isGameOver) {
            this.timeElapsed = 0f;
            this.countdownStartTime = this.timeElapsed;
            showCountdown(sb);
        }
    }

    private void incrementRoundCounter() {
        roundCounter++;
    }

    public void checkIfGameIsFinished() {
        // to avoid running unneccessary code in simplest code
        if (roundCounter == MAX_ROUNDS) {
            isGameOver = true;
        } else {
            // if either player has higher score than half of max rounds, the game is over
            int breakpoint = (int) Math.floor(MAX_ROUNDS / 2f);
            if (player1.getScore() > breakpoint) {
                isGameOver = true;
                winnerOfTheGame = "Player1";
            } else if (player2.getScore() > breakpoint) {
                winnerOfTheGame = "Player2";
                isGameOver = true;
            }
        }
    }

    public void whenGameIsFinished(SpriteBatch sb) {
        if (isGameOver) {
            gsm.set(new WinnerState(gsm, winnerOfTheGame));
        }
    }

    private void drawScore(SpriteBatch sb) {
        Matrix4 originalMatrix =
                sb.getTransformMatrix().cpy(); // Save the original matrix
        sb.begin();
        sb.setTransformMatrix(
                new Matrix4().setToRotation(0, 0, 1, 90));
        if (fontForScore == null) {
            fontForScore = new BitmapFont();
            fontForScore.getData().setScale(5f);
        }
        fontForScore.setColor(Color.BLACK);
        fontForScore.draw(sb, "" + player1.getScore(),
                Gdx.graphics.getWidth() + (fontForScore.getCapHeight() * 2),
                -fontForScore.getCapHeight());
        fontForScore.draw(sb, "-",
                Gdx.graphics.getWidth() + (fontForScore.getCapHeight()),
                -fontForScore.getCapHeight());
        fontForScore.draw(sb, "" + player2.getScore(),
                Gdx.graphics.getWidth(),
                -fontForScore.getCapHeight());
        sb.end();
        sb.setTransformMatrix(originalMatrix); // Restore the original matrix

    }

    private void drawGame(SpriteBatch sb) {

        Gdx.gl.glClearColor(252 / 255f, 231 / 255f, 239 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.circle(Gdx.graphics.getWidth() / 2f,
                Gdx.graphics.getHeight() / 2f,
                Gdx.graphics.getWidth() / 2f + 20);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(255 / 255f, 236 / 255f, 136 / 255f, 1);
        shapeRenderer.circle(Gdx.graphics.getWidth() / 2f,
                Gdx.graphics.getHeight() / 2f,
                Gdx.graphics.getWidth() / 2f - 70);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.circle(Gdx.graphics.getWidth() + 10,
                Gdx.graphics.getHeight() / 2f, 160);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.circle(-10, Gdx.graphics.getHeight() / 2f, 160);
        shapeRenderer.end();
        drawScore(sb);
        sb.begin();
        // sb.draw(settingsWheel, Gdx.graphics.getWidth() -
        // settingsWheel.getWidth(),
        // Gdx.graphics.getHeight()/2-settingsWheel.getHeight()/2 );
        sb.draw(player1sprite,
                Gdx.graphics.getWidth() / 2f - player1sprite.getWidth() / 2, 0);
        sb.draw(player2sprite,
                Gdx.graphics.getWidth() / 2f - player2sprite.getWidth() / 2,
                Gdx.graphics.getHeight() - player2sprite.getHeight());

        sb.end();
        stage.draw();
        stage.act();
    }

    private void showCountdown(SpriteBatch sb) {
        float countdownEndtime = countdownStartTime + COUNTDOWN_TIME;
        // Draw game in background
        drawGame(sb);

        // Draw the background and overlay
        Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 0.69f);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        shapeRenderer.end();

        // Update the countdown timer
        timeElapsed += Gdx.graphics.getDeltaTime();
        if (timeElapsed >= COUNTDOWN_TIME) {
            // Countdown finished, switch to game screen
            // gsm.set(new GameScreen(gsm));
            Gdx.app.log("PlayState", "Countdown finished");
        }
        // Draw the countdown
        int countdownNumber = (int) (countdownEndtime - timeElapsed) + 1;
        if (font == null) {
            font = new BitmapFont();
            font.getData().setScale(12f);
        }
        sb.begin();
        font.draw(sb, Integer.toString(countdownNumber),
                Gdx.graphics.getWidth() / 2f,
                Gdx.graphics.getHeight() / 2f);
        sb.end();
    }

    @Override
    public final void render(SpriteBatch sb) {
        if (timeElapsed < COUNTDOWN_TIME) {
            showCountdown(sb);

        } else {
            if (!isGameOver && player2.getScore() < 3) {
                whenRoundFinished(sb);
                whenGameIsFinished(sb);

            }
        }
    }

    @Override
    public final void dispose() {
        settingsWheel.dispose();
        player1Tex.dispose();
        player2Tex.dispose();
    }
}
