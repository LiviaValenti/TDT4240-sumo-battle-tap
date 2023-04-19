package prog.sumo.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.Map;

import prog.sumo.models.Player;

public class PlayView extends View {
    public static Texture char1, char2;
    public static int battleCircleHeight = Gdx.graphics.getHeight() / 2;
    public static int battleCircleRadius = Gdx.graphics.getWidth() / 2 + 20;
    private final Player player1;
    private final Player player2;
    Texture settingsWheel, windowTex, backTex, quitTex, tutorialTex, tutWinTex,
            writtenTutTex, back2Tex;
    Texture hand1Tex, hand2Tex;
    ShapeRenderer shapeRenderer;
    Drawable settingsWheelDrawable, windowDraw, backDraw, quitDraw,
            tutorialDraw, tutWinDraw, writtenTutDraw, back2Draw;
    Drawable player1Drawable, player2Drawable;
    ImageButton settingsB, quitB, backB, tutB, writtenTutB, back2B;
    ImageButton hand1, hand2;
    Stage stage;
    Window pinkWindow, orangeWindow;

    public PlayView(GameViewManager gsm, Map<Integer, String> playerHash) {

        super(gsm);

        shapeRenderer = new ShapeRenderer();

        settingsWheel = new Texture("settingswheel.png");
        hand1Tex = new Texture("greenhand.png");
        hand2Tex = new Texture("purplehand.png");
        char1 = new Texture(playerHash.get(0));
        char2 = new Texture(playerHash.get(1));
        windowTex = new Texture("pinkWindow.png");
        quitTex = new Texture("quitGame.png");
        backTex = new Texture("backToGame.png");
        tutorialTex = new Texture("tutorialOrangeButton.png");
        tutWinTex = new Texture("orangeWindow.png");
        writtenTutTex = new Texture("writtenTutorial.png");
        back2Tex = new Texture("back2.png");

        settingsWheelDrawable = new TextureRegionDrawable(settingsWheel);
        player1Drawable = new TextureRegionDrawable(hand1Tex);
        player2Drawable = new TextureRegionDrawable(hand2Tex);
        windowDraw = new TextureRegionDrawable(windowTex);
        quitDraw = new TextureRegionDrawable(quitTex);
        backDraw = new TextureRegionDrawable(backTex);
        tutorialDraw = new TextureRegionDrawable(tutorialTex);
        tutWinDraw = new TextureRegionDrawable(tutWinTex);
        writtenTutDraw = new TextureRegionDrawable(writtenTutTex);
        back2Draw = new TextureRegionDrawable(back2Tex);

        settingsB = new ImageButton(settingsWheelDrawable);
        quitB = new ImageButton(quitDraw);
        quitB.setTransform(true);
        quitB.setScale(2f);
        backB = new ImageButton(backDraw);
        backB.setTransform(true);
        backB.setScale(2f);
        tutB = new ImageButton(tutorialDraw);
        tutB.setTransform(true);
        tutB.setScale(2f);
        hand1 = new ImageButton(player1Drawable);
        hand2 = new ImageButton(player2Drawable);
        writtenTutB = new ImageButton(writtenTutDraw);
        writtenTutB.setTransform(true);
        writtenTutB.setScale(2f);
        back2B = new ImageButton(back2Draw);
        back2B.setTransform(true);
        back2B.setScale(2f);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(settingsB);
        stage.addActor(hand1);
        stage.addActor(hand2);

        player1 = new Player(char1, 1);
        player2 = new Player(char2, 0);

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
        quitB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("quitB");
            }
        });

        backB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("backB");
            }
        });
        tutB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("tutB");
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
        back2B.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("back2");
            }
        });
        pinkWindow = new Window("Settings",
                new Window.WindowStyle(new BitmapFont(), new Color(0, 0, 0, 0),
                        windowDraw)) {
            public void result(Object obj) {
                System.out.println("result " + obj);
            }
        };
        orangeWindow = new Window("Tutorial",
                new Window.WindowStyle(new BitmapFont(), new Color(0, 0, 0, 0),
                        tutWinDraw)) {
            public void result(Object obj) {
                System.out.println("result " + obj);
            }
        };

        Table table = new Table();
        // Add buttons to the table
        table.add(tutB).padRight(Gdx.graphics.getWidth() / 2 + 30);
        table.row().padTop(200); // Create a new row
        table.add(backB).padRight(Gdx.graphics.getWidth() / 2 + 30);
        table.row().padTop(185); // Create a new row
        table.add(quitB).padRight(Gdx.graphics.getWidth() / 2 + 30);

        pinkWindow.setResizable(true);
        pinkWindow.add(table);
        pinkWindow.pack();

        Table table2 = new Table();

        table2.add(writtenTutB).padRight(Gdx.graphics.getWidth() / 2 + 40)
                .padTop(550);
        table2.row().padTop(300);
        table2.add(back2B).padRight(Gdx.graphics.getWidth() / 2 + 30);

        //table2.add(back2B).padRight(Gdx.graphics.getWidth()/ 2 +30);
        orangeWindow.add(table2);
        orangeWindow.pack();

    }

    @Override
    protected final void handleInput(String name) {
        switch (name) {
            case "settingsB":
                //gsm.set(new MainMenuView(gsm));
                stage.addActor(pinkWindow);
                break;
            case "player1":
                //Calling the movePlayer method from the Player class
                player1.movePlayer(player2);
                break;
            case "player2":
                //Calling the movePlayer method from the Player class
                player2.movePlayer(player1);
                break;
            default:
                // handle invalid input
                break;
            case "tutB":

                stage.addActor(orangeWindow);
                break;
            case "quitB":
                gsm.set(new MainMenuView(gsm));
                break;
            case "backB":
                stage.addAction(Actions.removeActor(pinkWindow));
                break;
            case "back2":
                stage.addAction(Actions.removeActor(orangeWindow));
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
        sb.draw(char1, Gdx.graphics.getWidth() / 2 - char1.getWidth() / 2,
                player1.getPosition());
        sb.draw(char2, Gdx.graphics.getWidth() / 2 - char2.getWidth() / 2,
                player2.getPosition());
        sb.end();

        stage.draw();
        stage.act();
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
