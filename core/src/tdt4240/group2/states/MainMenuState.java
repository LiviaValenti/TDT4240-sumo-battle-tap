package tdt4240.group2.states;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import tdt4240.group2.SumoBattleTapGame;

public class MainMenuState extends State{
    Texture playButtonTex;
    Texture scoreBoardButtonTex;
    Texture tutorialButtonTex;
    Texture logo;

    Drawable playButtonDrawable;
    Drawable scoreBoardButtonDrawable;
    Drawable tutorialButtonDrawable;

    Stage stage;

    ImageButton playButton;
    ImageButton scoreBoardButton;
    ImageButton tutorialButton;

    Dialog tutPop;

    ImageButton closeTut;


    public MainMenuState( GameStateManager gsm) {
        super(gsm);
        playButtonTex = new Texture("play.png");
        scoreBoardButtonTex = new Texture("scoreBoard.png");
        tutorialButtonTex = new Texture("tutorialButton.png");
        logo = new Texture("logo.png");

        playButtonDrawable = new TextureRegionDrawable(playButtonTex);
        scoreBoardButtonDrawable = new TextureRegionDrawable(scoreBoardButtonTex);
        tutorialButtonDrawable = new TextureRegionDrawable(tutorialButtonTex);

        playButton = new ImageButton(playButtonDrawable);
        scoreBoardButton = new ImageButton(scoreBoardButtonDrawable);
        tutorialButton = new ImageButton(tutorialButtonDrawable);


        playButton.setPosition(Gdx.graphics.getWidth()/2- scoreBoardButton.getWidth(), tutorialButton.getHeight()+ scoreBoardButton.getHeight()*4);
        playButton.setTransform(true);
        playButton.setScale(2f);
        scoreBoardButton.setPosition(Gdx.graphics.getWidth()/2- scoreBoardButton.getWidth(), tutorialButton.getHeight()+ scoreBoardButton.getHeight()*2);
        scoreBoardButton.setTransform(true);
        scoreBoardButton.setScale(2f);
        tutorialButton.setPosition(Gdx.graphics.getWidth()/2- scoreBoardButton.getWidth(), tutorialButton.getHeight());
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

        tutPop = new Dialog("Tutorial", new Window.WindowStyle(new BitmapFont(),new Color(0,0,0,0), playButtonDrawable)) {
            public void result(Object obj) {
                System.out.println("result "+obj);
            }
        };
        tutPop.text("yes", new Label.LabelStyle(new BitmapFont(), new Color(0,0,0,0)));
        tutPop.setResizable(true);
       // tutPop.scaleBy(5f);
        tutPop.setPosition(0, Gdx.graphics.getHeight()/2);
        tutPop.pack();
        float tutX = tutPop.getOriginX()+tutPop.getWidth();
        float tutY = tutPop.getOriginY()+tutPop.getHeight();



        closeTut = new ImageButton(scoreBoardButtonDrawable);
        closeTut.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("closeT");
            }
        });

        closeTut.setTransform(true);
        closeTut.setScale(0.5f);
        closeTut.setPosition(tutX, tutY);
        tutPop.button(closeTut);

       // tutPop.add(closeTut);

    }


    @Override
    protected void handleInput(String name) {
        if (name.equals("playB")) {
            gsm.set(new CharacterSelectionState(gsm));
        }
        if (name.equals("scoreBoardB")) {
            gsm.set(new ScoreBoardState(gsm));
        }
        if (name.equals("tutorialB")) {
            stage.addActor(tutPop);
        }
        if (name.equals("closeT")) {
            stage.addAction(Actions.removeActor(tutPop));
        }

    }


    @Override
    public void update(float dt) {

    }

    @Override
    public void render (SpriteBatch sb) {
        Gdx.gl.glClearColor(252/255f,231/255f,239/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act();
        sb.begin();
        sb.draw(logo, Gdx.graphics.getWidth()/2-logo.getWidth()/2,Gdx.graphics.getHeight()-logo.getHeight()*2);
        sb.end();
    }

    @Override
    public void dispose() {
        playButtonTex.dispose();
        scoreBoardButtonTex.dispose();
        tutorialButtonTex.dispose();
        logo.dispose();
    }

}
