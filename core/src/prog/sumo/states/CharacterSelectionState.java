package prog.sumo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ArraySelection;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CharacterSelectionState extends State {

    Texture homeTex;
    Texture playTex;

    Texture redTex;
    Texture blueTex;



    Drawable homeDraw;
    Drawable playDraw;
    Drawable redDraw;
    Drawable blueDraw;



    ImageButton homeB;
    ImageButton playB;

    ImageButton redB;

    ImageButton blueB;

    Stage stage;

    SpriteBatch batch;
    BitmapFont font;

    Array<Integer> selectedCharacters;


    public CharacterSelectionState(GameStateManager gsm) {
        super(gsm);
        homeTex = new Texture("home.png");
        playTex = new Texture("play.png");
        redTex = new Texture("redplayer.png");
        blueTex = new Texture("blueplayer.png");

        homeDraw = new TextureRegionDrawable(homeTex);
        playDraw = new TextureRegionDrawable(playTex);
        redDraw = new TextureRegionDrawable(redTex);
        blueDraw = new TextureRegionDrawable(blueTex);


        homeB = new ImageButton(homeDraw);
        playB = new ImageButton(playDraw);
        redB =  new ImageButton(redDraw);
        blueB =  new ImageButton(blueDraw);

        stage = new Stage();
        stage.addActor(playB);
        stage.addActor(homeB);
        stage.addActor(redB);
        stage.addActor(blueB);


        playB.setPosition(Gdx.graphics.getWidth() - playB.getWidth(),
                Gdx.graphics.getHeight() / 2 - playB.getHeight() / 2);
        playB.setTransform(true);

        homeB.setPosition(homeB.getWidth() / 3,
                Gdx.graphics.getHeight() / 2 - homeB.getHeight() / 2);
        homeB.setTransform(true);

        redB.setPosition(Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 + 180);
        redB.setTransform(true);

        blueB.setPosition(Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - 400);
        blueB.setTransform(true);

        Gdx.input.setInputProcessor(stage);

        playB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("playB");
            }
        });
        homeB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("homeB");
            }
        });

        redB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("redB");
            }
        });
        blueB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("blueB");
            }
        });


        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(5f);
        font.setColor(1, 0, 0, 1);

    }

    @Override
    protected final void handleInput(String name) {
        if (name.equals("playB")) {
            gsm.set(new PlayState(gsm));
        }
        if (name.equals("homeB")) {
            gsm.set(new MainMenuState(gsm));
        }
        else {
            System.out.println(name);
            selectedCharacters.add(getCharacterValue(name));
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
        batch.begin();
        font.draw(batch, "Choose your character!", Gdx.graphics.getWidth()/5 ,
                Gdx.graphics.getHeight() / 2 - homeB.getHeight());
        batch.end();


    }

    @Override
    public final void dispose() {
        homeTex.dispose();
        playTex.dispose();
        redTex.dispose();
        blueTex.dispose();


    }

    public Integer getCharacterValue(String c) {
        Integer value = 0;
        if (c == "redB"){
            value = 0;
        }
        if (c == "blueB"){
            value = 1;

        }
        return value;
    }

}


