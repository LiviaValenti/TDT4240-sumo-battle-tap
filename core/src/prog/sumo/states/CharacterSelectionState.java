package prog.sumo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.HashMap;
import java.util.Map;

import prog.sumo.Character;

public class CharacterSelectionState extends State {

    private final String[] colors =
            new String[] {"red", "green", "pink", "purple", "blue", "orange"};

    // Initialize textures and drawables
    private final Texture[] buttonTextures =
            new Texture[] {new Texture("home.png"), new Texture("play.png"),
                    new Texture("redplayer.png"),
                    new Texture("greenplayer.png"),
                    new Texture("pinkplayer.png"),
                    new Texture("purpleplayer.png"),
                    new Texture("blueplayer.png"),
                    new Texture("orangeplayer.png"),
            };
    private final ImageButton[] buttons =
            new ImageButton[buttonTextures.length];

    // Stage, sprite batch, and font
    private final Stage stage = new Stage();
    private final BitmapFont font = new BitmapFont();

    // Map for player hash
    private final Map<Integer, String> playerHash = new HashMap<>();
    private final Map<String, Texture> characterTextures = new HashMap<>();


    private int firstPlayerX = 0;
    private int secondPlayerX = 0;


    public CharacterSelectionState(GameStateManager gsm) {
        super(gsm);

        for (String color : colors) {
            characterTextures.put(color,
                    new Texture(color + "player.png"));
        }

        for (int i = 0; i < buttonTextures.length; i++) {
            Drawable[] buttonDrawables = new Drawable[buttonTextures.length];
            buttonDrawables[i] = new TextureRegionDrawable(buttonTextures[i]);
            buttons[i] = new ImageButton(buttonDrawables[i]);
            // Waiting to add the Play button to the stage
            if (i != 1) {
                stage.addActor(buttons[i]);
            }
        }

        // Set button positions
        buttons[1].setPosition(Gdx.graphics.getWidth() - buttons[1].getWidth(),
                Gdx.graphics.getHeight() / 2f - buttons[1].getHeight() / 2);
        buttons[0].setPosition(buttons[0].getWidth() / 3,
                Gdx.graphics.getHeight() / 2f - buttons[0].getHeight() / 2);
        buttons[2].setPosition(Gdx.graphics.getWidth() / 2f - 100,
                Gdx.graphics.getHeight() / 2f + 400);
        buttons[3].setPosition(Gdx.graphics.getWidth() / 2f + 220,
                Gdx.graphics.getHeight() / 2f + 400);
        buttons[4].setPosition(Gdx.graphics.getWidth() / 4f - 150,
                Gdx.graphics.getHeight() / 2f + 400);
        buttons[5].setPosition(Gdx.graphics.getWidth() / 2f - 100,
                Gdx.graphics.getHeight() / 2f - 600);
        buttons[6].setPosition(Gdx.graphics.getWidth() / 2f + 220,
                Gdx.graphics.getHeight() / 2f - 600);
        buttons[7].setPosition(Gdx.graphics.getWidth() / 4f - 150,
                Gdx.graphics.getHeight() / 2f - 600);

        // Set input processor
        Gdx.input.setInputProcessor(stage);

        // Set button names with loop and using color array
        for (int i = 2; i < buttons.length; i++) {
            buttons[i].setName(colors[i - 2] + "player.png");
        }

        // Add listeners to buttons
        buttons[1].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("playB");
            }
        });
        buttons[0].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("homeB");
            }
        });
        for (int i = 2; i < buttons.length; i++) {
            buttons[i].addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    handleInput(actor.getName());
                }
            });
        }
    }

    @Override
    protected final void handleInput(String name) {
        if (name.equals("playB")) {
            Character player1Character =
                    new Character(playerHash.get(1), characterTextures.get(
                            playerHash.get(1)));
            Character player2Character = new Character(playerHash.get(0),
                    characterTextures.get(playerHash.get(0)));
            gsm.set(new PlayState(gsm, player1Character, player2Character));
        }
        if (name.equals("homeB")) {
            gsm.set(new MainMenuState(gsm));
        }

        // If name equals the first half of the color array,
        // add key 1 to the playerHash map
        for (int i = 0; i < colors.length / 2; i++) {
            if (name.equals(colors[i] + "player.png")) {
                playerHash.put(1, colors[i]);
                // Set the position of xCoordinates to the
                // x position of the button
                secondPlayerX = (int) buttons[i + 2].getX();
            }
        }

        // If name equals the second half of the color array,
        // add key 0 to the playerHash map
        for (int i = colors.length / 2; i < colors.length; i++) {
            if (name.equals(colors[i] + "player.png")) {
                playerHash.put(0, colors[i]);
                //Set the position of xCoordinates to the
                // x position of the button
                firstPlayerX = (int) buttons[i + 2].getX();
            }
        }

        // If both players have chosen a color,
        // the play button is added to the stage
        if (playerHash.size() == 2) {
            stage.addActor(buttons[1]);
        }
    }

    @Override
    public final void update(float dt) {

    }

    @Override
    public final void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(252 / 255f, 231 / 255f, 239 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act();
        sb.begin();
        font.getData().setScale(4f);
        font.setColor(0, 0, 0, 1);
        font.draw(sb, "Player 1: Choose your character!",
                Gdx.graphics.getWidth() / 8f,
                Gdx.graphics.getHeight() / 2f - 250);
        sb.end();

        Matrix4 originalMatrix = sb.getProjectionMatrix().cpy();
        Matrix4 rotatedMatrix =
                new Matrix4(originalMatrix).rotate(new Vector3(0, 0, 1), 180);
        sb.setProjectionMatrix(rotatedMatrix);
        sb.begin();
        font.draw(sb, "Player 2: Choose your character!",
                -Gdx.graphics.getWidth() / 8f - 850,
                -(Gdx.graphics.getHeight() / 2f + 300));
        sb.end();
        sb.setProjectionMatrix(originalMatrix);
        sb.begin();
        // If the map contains a key 0,
        // draw an X on top of the player's character
        if (playerHash.containsKey(0)) {
            font.getData().setScale(25f);
            font.draw(sb, "O", firstPlayerX - 20,
                    Gdx.graphics.getHeight() / 2f - 300);
        }
        //If the map contains a key 1,
        // draw a black rectangle around the player's character
        if (playerHash.containsKey(1)) {
            //Set font size to 10
            font.getData().setScale(25f);
            font.draw(sb, "O", secondPlayerX - 20,
                    Gdx.graphics.getHeight() / 2f + 700);
        }

        sb.end();
    }

    @Override
    public final void dispose() {
        for (Texture t : buttonTextures) {
            t.dispose();
        }
        stage.dispose();
        font.dispose();
    }

}


