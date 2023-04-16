package prog.sumo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.Map;

import prog.sumo.Player;

public class PlayState extends State {
    Texture settingsWheel;
    Texture hand1Tex, hand2Tex;
    Texture car1, car2;
    ShapeRenderer shapeRenderer;
    Drawable settingsWheelDrawable;
    Drawable player1Drawable, player2Drawable;
    ImageButton settingsB;
    ImageButton hand1, hand2;
    Stage stage;

    public static int battleCircleHeight = Gdx.graphics.getHeight() / 2;
    public static int battleCircleRadius = Gdx.graphics.getWidth() / 2 + 20;

    private Player player1game;
    private Player player2game;

    public PlayState(GameStateManager gsm, Map<Integer, String> playerHash) {

        super(gsm);

        shapeRenderer = new ShapeRenderer();

        settingsWheel = new Texture("settingswheel.png");
        hand1Tex = new Texture("purplehand.png");
        hand2Tex = new Texture("greenhand.png");
        car1 = new Texture(playerHash.get(0));
        car2 = new Texture(playerHash.get(1));

        settingsWheelDrawable = new TextureRegionDrawable(settingsWheel);
        player1Drawable = new TextureRegionDrawable(hand1Tex);
        player2Drawable = new TextureRegionDrawable(hand2Tex);

        settingsB = new ImageButton(settingsWheelDrawable);
        hand1 = new ImageButton(player1Drawable);
        hand2 = new ImageButton(player2Drawable);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(settingsB);
        stage.addActor(hand1);
        stage.addActor(hand2);

        player1game = new Player(car1, 1);
        player2game = new Player(car2, 0);

        settingsB.setPosition(Gdx.graphics.getWidth() - settingsB.getWidth(),
                Gdx.graphics.getHeight() / 2 - settingsB.getHeight() / 2);
        hand1.setPosition(Gdx.graphics.getWidth() / 2 - hand1.getWidth() / 2,
                0);
        hand2.setPosition(Gdx.graphics.getWidth() / 2 - hand2.getWidth() / 2,
                Gdx.graphics.getHeight() - hand2.getHeight());

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
                player1game.movePlayer(player2game);
                break;
            case "player2":
                //Calling the movePlayer method from the Player class
                player2game.movePlayer(player1game);
                break;
            default:
                // handle invalid input
                break;
        }
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public final void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(252 / 255f, 231 / 255f, 239 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.circle(Gdx.graphics.getWidth() / 2, battleCircleHeight,
                battleCircleRadius);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(255 / 255f, 236 / 255f, 136 / 255f, 1);
        shapeRenderer.circle(Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() / 2, Gdx.graphics.getWidth() / 2 - 70);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.circle(Gdx.graphics.getWidth() + 10,
                Gdx.graphics.getHeight() / 2, 160);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.circle(-10, Gdx.graphics.getHeight() / 2, 160);
        shapeRenderer.end();

        sb.begin();
        sb.draw(car1, Gdx.graphics.getWidth() / 2 - car1.getWidth() / 2,
                player1game.getPosition());
        sb.draw(car2, Gdx.graphics.getWidth() / 2 - car2.getWidth() / 2,
                player2game.getPosition());
        sb.end();

        stage.draw();
        stage.act();
    }

    @Override
    public final void dispose() {
        settingsWheel.dispose();
        hand1Tex.dispose();
        hand2Tex.dispose();
        car1.dispose();
        car2.dispose();
        stage.dispose();
    }

}
