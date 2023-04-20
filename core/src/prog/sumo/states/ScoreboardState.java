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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;

import prog.sumo.SumoBattleTapGame;
import prog.sumo.singletons.ScoreAPI;

public class ScoreboardState extends State {

    Texture scoboTitle;
    Texture backButtonTex;
    Drawable backButtonDrawable;
    ImageButton backB;
    Stage stage;
    BitmapFont font;
    private final Map<String, Long> scores;

    public ScoreboardState(GameStateManager gsm) {
        super(gsm);

        scores = new LinkedHashMap<>();
        ScoreAPI scoreAPI = SumoBattleTapGame.getScoreApi();
        scoreAPI.subscribeToScores(scores);

        scoboTitle = new Texture("scoreboardHeadline.png");

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
                Gdx.graphics.getWidth() / 2f - scoboTitle.getWidth() / 2f,
                Gdx.graphics.getHeight() - scoboTitle.getHeight());

        final float xOffset =
                Gdx.graphics.getWidth() / 2f - scoboTitle.getWidth() / 2f;
        float yOffset = Gdx.graphics.getHeight() - scoboTitle.getHeight();
        ListIterator<Map.Entry<String, Long>> iterator =
                new ArrayList<Map.Entry<String, Long>>(
                        scores.entrySet()).listIterator(
                        scores.size());
        while (iterator.hasPrevious()) {
            Map.Entry<String, Long> score = iterator.previous();
            font.draw(sb, score.getKey(), xOffset, yOffset);
            font.draw(sb, score.getValue().toString(),
                    Gdx.graphics.getWidth() - xOffset, yOffset);
            yOffset -= font.getLineHeight() * 1.5f;
        }
        sb.end();
    }

    @Override
    public final void dispose() {
        scoboTitle.dispose();
        backButtonTex.dispose();
        font.dispose();
    }
}
