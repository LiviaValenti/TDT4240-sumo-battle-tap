package tdt4240.group2.states;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import tdt4240.group2.SumoBattleTapGame;

public class MainMenuState extends State{
    Texture playButton;
    Texture scoreBoardButton;
    Texture tutorialButton;
    Texture logo;

    Sprite playButtonSprite;
    Sprite scoreBoardButtonSprite;
    Sprite tutorialButtonSprite;



    public MainMenuState(GameStateManager gsm) {
        super(gsm);
        playButton = new Texture("playbutton.png");
        scoreBoardButton = new Texture("scoreBoard.png");
        tutorialButton = new Texture("tutorialButton.png");
        logo = new Texture("logo.png");
        playButtonSprite = new Sprite(playButton);
        scoreBoardButtonSprite = new Sprite(scoreBoardButton);
        tutorialButtonSprite = new Sprite(tutorialButton);

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            int x1 = Gdx.input.getX();
            int y1 = Gdx.input.getY();
            Vector3 input = new Vector3(x1, y1, 0);
            cam.unproject(input);
            if(playButtonSprite.getBoundingRectangle().contains(input.x, input.y)) {
                gsm.set(new PlayState(gsm));
            }
        }
    }


    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render (SpriteBatch sb) {
        Gdx.gl.glClearColor(252/255f,231/255f,239/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();
        sb.draw(logo, Gdx.graphics.getWidth()/2-logo.getWidth()/2,Gdx.graphics.getHeight()-logo.getHeight()*2);
        sb.draw(playButtonSprite, Gdx.graphics.getWidth()/2-700/2,Gdx.graphics.getHeight()/2, 700, 220);
        sb.draw(scoreBoardButtonSprite,Gdx.graphics.getWidth()/2-700/2,Gdx.graphics.getHeight()/2- 220, 700, 220);
        sb.draw(tutorialButtonSprite,Gdx.graphics.getWidth()/2-700/2,Gdx.graphics.getHeight()/2- 220*2, 700, 220);
        sb.end();

    }

    @Override
    public void dispose() {
        playButton.dispose();
        scoreBoardButton.dispose();
        tutorialButton.dispose();
        logo.dispose();
    }

}
