package prog.sumo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class CharacterSelectionState extends State {

    Texture homeTex;
    Texture playTex;

    Drawable homeDraw;
    Drawable playDraw;

    ImageButton homeB;
    ImageButton playB;

    Stage stage;

    SpriteBatch batch;
    BitmapFont font;

    public CharacterSelectionState(GameStateManager gsm) {
        super(gsm);
        homeTex = new Texture("home.png");
        playTex = new Texture("play.png");

        homeDraw = new TextureRegionDrawable(homeTex);
        playDraw = new TextureRegionDrawable(playTex);

        homeB = new ImageButton(homeDraw);
        playB = new ImageButton(playDraw);

        stage = new Stage();
        stage.addActor(playB);
        stage.addActor(homeB);

        playB.setPosition(Gdx.graphics.getWidth() - playB.getWidth(),
                Gdx.graphics.getHeight() / 2 - playB.getHeight() / 2);
        playB.setTransform(true);

        homeB.setPosition(homeB.getWidth() / 3,
                Gdx.graphics.getHeight() / 2 - homeB.getHeight() / 2);
        homeB.setTransform(true);


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
        font.draw(batch, "Choose your character!", 0,
                Gdx.graphics.getHeight() / 2 - homeB.getHeight() * 2);
        batch.end();


    }

    @Override
    public final void dispose() {
        homeTex.dispose();
        playTex.dispose();
    }
}
