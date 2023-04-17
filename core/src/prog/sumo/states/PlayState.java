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

import java.util.Map;


public class PlayState extends State {
    private final float COUNTDOWN_TIME = 3f; // Countdown time in seconds
    private float timeElapsed = 0f; // Time elapsed since countdown started
    private BitmapFont font; // Font to draw the countdown
    private BitmapFont fontForScore; // Font to draw the countdown
    private SpriteBatch spriteBatch; // SpriteBatch to draw the countdown
    public static Texture char1, char2;
    public static int battleCircleHeight = Gdx.graphics.getHeight() / 2;
    public static int battleCircleRadius = Gdx.graphics.getWidth() / 2 + 20;

    public static int startPosition1, startPosition2;
    Texture settingsWheel;
    Texture hand1Tex, hand2Tex;
    ShapeRenderer shapeRenderer;
    Drawable settingsWheelDrawable;
    Drawable player1Drawable, player2Drawable;
    private float countdownStartTime;
    private boolean roundIsOver;

    ImageButton settingsB;
    ImageButton hand1, hand2;
    Stage stage;
    private final Player player1;
    private final Player player2;

    // The following should be changed to Player objects when that part is ready
    String winnerOfTheRound = "";
    String winnerOfTheGame = "";

    int roundCounter;
    private final static int MAX_ROUNDS = 2;
    boolean isGameOver;

    public PlayState(GameStateManager gsm, Map<Integer, String> playerHash) {

        super(gsm);
        roundIsOver = false;
        shapeRenderer = new ShapeRenderer();
        settingsWheel = new Texture("settingswheel.png");
        hand1Tex = new Texture("greenhand.png");
        hand2Tex = new Texture("purplehand.png");
        char1 = new Texture(playerHash.get(0));
        char2 = new Texture(playerHash.get(1));

        settingsWheelDrawable = new TextureRegionDrawable(settingsWheel);
        player1Drawable = new TextureRegionDrawable(hand1Tex);
        player2Drawable = new TextureRegionDrawable(hand2Tex);

        settingsB = new ImageButton(settingsWheelDrawable);
        hand1 = new ImageButton(player1Drawable);
        hand2 = new ImageButton(player2Drawable);


        roundCounter = 1;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(settingsB);
        stage.addActor(hand1);
        stage.addActor(hand2);
        startPosition1 = Gdx.graphics.getHeight() / 2 + battleCircleRadius
                - char1.getHeight();
        startPosition2 = Gdx.graphics.getHeight() / 2 - battleCircleRadius;

        player1 = new Player(char1, 1);
        player2 = new Player(char2, 0);

        settingsB.setPosition(Gdx.graphics.getWidth() - settingsB.getWidth(),
                Gdx.graphics.getHeight() / 2 - settingsB.getHeight() / 2);
        hand1.setPosition(Gdx.graphics.getWidth() / 2 - hand1.getWidth() / 2,
                0);
        hand2.setPosition(Gdx.graphics.getWidth() / 2 - hand2.getWidth() / 2,
                Gdx.graphics.getHeight() - hand2.getHeight());
        isGameOver = false;
        countdownStartTime = 0f;
        settingsB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("settingsB");
            }
        });

        hand1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("player1");
            }
        });

        hand2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("player2");
            }
        });
    }

    @Override
    protected final void handleInput(String name) {
        switch (name) {
            case "settingsB":
                gsm.set(new MainMenuState(gsm));
                break;
            case "player1":
                //Calling the movePlayer method from the Player class
                player1.movePlayer(player2);
                isRoundOver();
                break;
            case "player2":
                //Calling the movePlayer method from the Player class
                player2.movePlayer(player1);
                isRoundOver();
                break;
            default:
                // handle invalid input
                break;
        }
    }


    public void isRoundOver() {
        if (player1.getPosition() + player1.getTexture().getHeight() / 2 > startPosition1) {

            //Increment score for player 2
            player1.setPosition(startPosition2);
            player2.setPosition(startPosition1);
            whenRoundFinished();
            roundIsOver = true;

        } else if (player2.getPosition() - player2.getTexture().getHeight() / 2 < startPosition2) {
            //Increment score for player 1
            player1.setPosition(startPosition2);
            player2.setPosition(startPosition1);
            whenRoundFinished();
            roundIsOver = true;
        }
        roundIsOver = false;
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
    public void whenRoundFinished() {
        incrementRoundCounter();
        incrementScoreOfWinner();

        checkIfGameIsFinished();
        Gdx.app.log("round", "" + roundCounter);
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
                whenGameIsFinished();

                winnerOfTheGame = "Player1";
            } else if (player2.getScore() > breakpoint) {
                winnerOfTheGame = "Player2";
                isGameOver = true;
                whenGameIsFinished();
            }
        }
    }

    public void whenGameIsFinished() {
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
        shapeRenderer.circle(Gdx.graphics.getWidth() / 2, battleCircleHeight,
                battleCircleRadius);
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
        sb.draw(char1, Gdx.graphics.getWidth() / 2 - char1.getWidth() / 2,
                player1.getPosition());
        sb.draw(char2, Gdx.graphics.getWidth() / 2 - char2.getWidth() / 2,
                player2.getPosition());
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
        drawGame(sb);
        if (timeElapsed < COUNTDOWN_TIME) {
            showCountdown(sb);
        }
    }

    @Override
    public final void dispose() {
        settingsWheel.dispose();
        hand1Tex.dispose();
        hand2Tex.dispose();
        char1.dispose();
        char2.dispose();
        stage.dispose();
    }

}
