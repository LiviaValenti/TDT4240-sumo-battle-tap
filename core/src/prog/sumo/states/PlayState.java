package prog.sumo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

import prog.sumo.sprites.Player;

public class PlayState extends State {
    Texture settingsWheel;
    Texture player1Tex;
    Texture player2Tex;

    Sprite player1sprite;
    Sprite player2sprite;

    private Player player1game;
    private Player player2game;

    ShapeRenderer shapeRenderer;

    Drawable settingsWheelDrawable;
    Drawable player1Drawable;


    ImageButton settingsB;
    ImageButton player1;

    Stage stage;

    public PlayState(GameStateManager gsm, ArrayList<Integer> selectedC) {
        super(gsm);
        shapeRenderer = new ShapeRenderer();
        settingsWheel = new Texture("settingswheel.png");
        player1Tex = new Texture("purplehand.png");
        player2Tex = new Texture("greenhand.png");
        player1sprite = new Sprite(player1Tex);
        player2sprite = new Sprite(player2Tex);
        //player1game = new Player();

        settingsWheelDrawable = new TextureRegionDrawable(settingsWheel);
        player1Drawable = new TextureRegionDrawable(player1Tex);


        settingsB = new ImageButton(settingsWheelDrawable);
        player1 = new ImageButton(player1Drawable);

        stage = new Stage();
        stage.addActor(settingsB);
        stage.addActor(player1);


        settingsB.setPosition(Gdx.graphics.getWidth() - settingsB.getWidth(),
                Gdx.graphics.getHeight() / 2 - settingsB.getHeight() / 2);
        settingsB.setTransform(true);

        Gdx.input.setInputProcessor(stage);


        player1.setPosition(Gdx.graphics.getWidth() / 2 - player1.getWidth()/2,
                0);
        player1.setTransform(true);

        Gdx.input.setInputProcessor(stage);

        settingsB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("settingsB");
            }
        });

        player1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("player1");
            }
        });

    }

    @Override
    protected final void handleInput(String name) {
        if (name.equals("settingsB")) {
            gsm.set(new MainMenuState(gsm));
        }
        if (name.equals("player1")) {
            // Move player in gamestate
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
        shapeRenderer.circle(Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() / 2, Gdx.graphics.getWidth() / 2 + 20);
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
        //sb.draw(settingsWheel, Gdx.graphics.getWidth() -
        // settingsWheel.getWidth(),
        // Gdx.graphics.getHeight()/2-settingsWheel.getHeight()/2 );
        //sb.draw(player1sprite,
         //       Gdx.graphics.getWidth() / 2 - player1sprite.getWidth() / 2, 0);
        sb.draw(player2sprite,
                Gdx.graphics.getWidth() / 2 - player2sprite.getWidth() / 2,
                Gdx.graphics.getHeight() - player2sprite.getHeight());
        sb.end();
        stage.draw();
        stage.act();
    }

    @Override
    public final void dispose() {
        settingsWheel.dispose();
        player1Tex.dispose();
        player2Tex.dispose();
    }
}
