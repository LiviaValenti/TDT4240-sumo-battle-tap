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

public class ScoreBoardState extends State {

    Texture scoboTitle;
    Texture backButtonTex;
    Texture angelTex;
    Texture eggplantTex;
    Texture constructionTex;
    Texture deafTex;

    Drawable backButtonDrawable;

    ImageButton backB;

    Stage stage;
    SpriteBatch batch;
    BitmapFont font;

    public ScoreBoardState(GameStateManager gsm) {
        super(gsm);
        scoboTitle = new Texture("scoreboardHeadline.png");
        angelTex = new Texture("angelCharacter.png");
        constructionTex = new Texture("constructionworker.png");
        deafTex = new Texture("deafwoman.png");
        eggplantTex = new Texture("eggplant.png");
        backButtonTex = new Texture("back.png");


        backButtonDrawable = new TextureRegionDrawable(backButtonTex);

        backB = new ImageButton(backButtonDrawable);

        backB.setPosition(backB.getWidth(),
                Gdx.graphics.getHeight() - backB.getHeight() * 5);
        backB.setTransform(true);
        backB.setScale(4f);

        stage = new Stage();
        stage.addActor(backB);

        Gdx.input.setInputProcessor(stage);

        backB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleInput("backB");
            }
        });

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(5f);
        font.setColor(1, 0, 0, 1);
    }

    @Override
    protected final void handleInput(String name) {
        if (name.equals("backB")) {
            gsm.set(new MainMenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public final void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(252 / 255f, 231 / 255f, 239 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act();
        sb.begin();
        sb.draw(scoboTitle,
                Gdx.graphics.getWidth() / 2 - scoboTitle.getWidth() / 2,
                Gdx.graphics.getHeight() - scoboTitle.getHeight());
        sb.draw(angelTex, angelTex.getWidth() / 2,
                Gdx.graphics.getHeight() - scoboTitle.getHeight()
                        - angelTex.getHeight());
        sb.draw(constructionTex, angelTex.getWidth() / 2,
                Gdx.graphics.getHeight() - scoboTitle.getHeight()
                        - angelTex.getHeight() * 2 - angelTex.getHeight() / 2);
        sb.draw(eggplantTex, angelTex.getWidth() / 2,
                Gdx.graphics.getHeight() - scoboTitle.getHeight()
                        - angelTex.getHeight() * 4);
        sb.draw(deafTex, angelTex.getWidth() / 2,
                Gdx.graphics.getHeight() - scoboTitle.getHeight()
                        - angelTex.getHeight() * 5 - angelTex.getHeight() / 2);
        sb.end();
        batch.begin();
        font.draw(batch, "200 points", Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() - scoboTitle.getHeight()
                        - angelTex.getHeight() / 2);
        batch.end();
    }

    @Override
    public final void dispose() {
        scoboTitle.dispose();
        backButtonTex.dispose();
        angelTex.dispose();
        eggplantTex.dispose();
        constructionTex.dispose();
        deafTex.dispose();
        font.dispose();
    }
}
