package prog.sumo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PlayState extends State {
    Texture settingsWheel;
    Texture player1Tex;
    Texture player2Tex;

    Sprite player1sprite;
    Sprite player2sprite;
    ShapeRenderer shapeRenderer;

    Drawable settingsWheelDrawable;

    ImageButton settingsB;

    Stage stage;

    Window setPop;
    Window tutorial;

    TextButton xMenu;

    TextButton tutButton;
    TextButton xTutorial;

    Skin uiSkin = new Skin(Gdx.files.internal("skin/level-plane-ui.json"));

    public PlayState(GameStateManager gsm) {
        super(gsm);
        shapeRenderer = new ShapeRenderer();
        settingsWheel = new Texture("settingswheel.png");
        player1Tex = new Texture("purplehand.png");
        player2Tex = new Texture("greenhand.png");
        player1sprite = new Sprite(player1Tex);
        player2sprite = new Sprite(player2Tex);

        settingsWheelDrawable = new TextureRegionDrawable(settingsWheel);

        settingsB = new ImageButton(settingsWheelDrawable);

        stage = new Stage();
        stage.addActor(settingsB);

        settingsB.setPosition(Gdx.graphics.getWidth() - settingsB.getWidth(),
                Gdx.graphics.getHeight() / 2 - settingsB.getHeight() / 2);
        settingsB.setTransform(true);

        Gdx.input.setInputProcessor(stage);


        tutorial = new Window("Tutorial", uiSkin, "default");
        tutorial.add("how you play the game");
        tutorial.scaleBy(5f);
        xTutorial = new TextButton("Back", uiSkin, "small-1");
        tutorial.add(xTutorial);


        setPop = new Window("Settings", uiSkin, "default");
        setPop.add("hello");
        setPop.scaleBy(5f);
        setPop.setPosition(10, Gdx.graphics.getHeight() / 2);


        xMenu = new TextButton("Back to game", uiSkin, "small-1");
        tutButton = new TextButton("Read tutorial", uiSkin, "small-2");
        setPop.add(xMenu);
        setPop.add(tutButton);


        settingsB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("settingsB");
            }
        });
        xMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("xMenu");
            }
        });
        tutButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("tutButton");
            }
        });
        xTutorial.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("xTut");
            }
        });
    }

    @Override
    protected final void handleInput(String name) {
        if (name.equals("settingsB")) {
            //gsm.set(new MainMenuState(gsm));
            stage.addActor(setPop);
        }
        if (name.equals("xMenu")) {
            stage.addAction(Actions.removeActor(setPop));
        }
        if (name.equals("tutButton")) {
            stage.addActor(tutorial);
        }
        if (name.equals("xTut")) {
            stage.addAction(Actions.removeActor(tutorial));
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
        sb.draw(player1sprite,
                Gdx.graphics.getWidth() / 2 - player1sprite.getWidth() / 2, 0);
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
