package prog.sumo.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MainMenuView extends View {
    Texture playButtonTex, scoreBoardButtonTex, tutorialButtonTex, logo,
            windowTex, writtenTutTex, back2Tex;
    Drawable playButtonDrawable, scoreBoardButtonDrawable, windowDraw,
            writtenTutDraw, back2Draw, tutorialButtonDrawable;
    Stage stage;
    ImageButton playButton, scoreBoardButton, tutorialButton;
    Window tutPop;
    ImageButton writtenTutB, back2B;

    public MainMenuView(GameViewManager gsm) {
        super(gsm);
        playButtonTex = new Texture("newGame.png");
        scoreBoardButtonTex = new Texture("scoreBoard.png");
        tutorialButtonTex = new Texture("tutorialButton.png");
        logo = new Texture("logo.png");
        windowTex = new Texture("orangeWindow.png");
        writtenTutTex = new Texture("writtenTutorial.png");
        back2Tex = new Texture("back2.png");

        playButtonDrawable = new TextureRegionDrawable(playButtonTex);
        scoreBoardButtonDrawable =
                new TextureRegionDrawable(scoreBoardButtonTex);
        tutorialButtonDrawable = new TextureRegionDrawable(tutorialButtonTex);
        windowDraw = new TextureRegionDrawable(windowTex);
        writtenTutDraw = new TextureRegionDrawable(writtenTutTex);
        back2Draw = new TextureRegionDrawable(back2Tex);

        playButton = new ImageButton(playButtonDrawable);
        scoreBoardButton = new ImageButton(scoreBoardButtonDrawable);
        tutorialButton = new ImageButton(tutorialButtonDrawable);
        writtenTutB = new ImageButton(writtenTutDraw);
        writtenTutB.setTransform(true);
        writtenTutB.setScale(2f);
        back2B = new ImageButton(back2Draw);
        back2B.setTransform(true);
        back2B.setScale(2f);

        playButton.setPosition(
                Gdx.graphics.getWidth() / 2 - scoreBoardButton.getWidth(),
                tutorialButton.getHeight() + scoreBoardButton.getHeight() * 4);
        playButton.setTransform(true);
        playButton.setScale(2f);
        scoreBoardButton.setPosition(
                Gdx.graphics.getWidth() / 2 - scoreBoardButton.getWidth(),
                tutorialButton.getHeight() + scoreBoardButton.getHeight() * 2);
        scoreBoardButton.setTransform(true);
        scoreBoardButton.setScale(2f);
        tutorialButton.setPosition(
                Gdx.graphics.getWidth() / 2 - scoreBoardButton.getWidth(),
                tutorialButton.getHeight());
        tutorialButton.setTransform(true);
        tutorialButton.setScale(2f);

        stage = new Stage();
        stage.addActor(playButton);
        stage.addActor(scoreBoardButton);
        stage.addActor(tutorialButton);

        Gdx.input.setInputProcessor(stage);

        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("playB");
            }
        });
        scoreBoardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("scoreBoardB");
            }
        });
        tutorialButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("tutorialB");
            }
        });
        back2B.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("back2");
            }
        });

        tutPop = new Window("Tutorial",
                new Window.WindowStyle(new BitmapFont(), new Color(0, 0, 0, 0),
                        windowDraw)) {
            public void result(Object obj) {
                System.out.println("result " + obj);
            }
        };

        Table table2 = new Table();

        table2.add(writtenTutB).padRight(Gdx.graphics.getWidth() / 2 + 40)
                .padTop(550);
        table2.row().padTop(300);
        table2.add(back2B).padRight(Gdx.graphics.getWidth() / 2 + 30);

        tutPop.add(table2);
        tutPop.pack();


    }

    @Override
    protected final void handleInput(String name) {
        if (name.equals("playB")) {
            gsm.set(new CharacterSelectionView(gsm));
        }
        if (name.equals("scoreBoardB")) {
            gsm.set(new ScoreBoardView(gsm));
        }
        if (name.equals("tutorialB")) {
            stage.addActor(tutPop);
        }
        if (name.equals("back2")) {
            stage.addAction(Actions.removeActor(tutPop));
        }

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public final void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(252 / 255f, 231 / 255f, 239 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        sb.draw(logo, Gdx.graphics.getWidth() / 2 - logo.getWidth() / 2,
                Gdx.graphics.getHeight() - logo.getHeight() * 2);
        sb.end();
        stage.draw();
        stage.act();
    }

    @Override
    public final void dispose() {
        playButtonTex.dispose();
        scoreBoardButtonTex.dispose();
        tutorialButtonTex.dispose();
        logo.dispose();
    }
}
