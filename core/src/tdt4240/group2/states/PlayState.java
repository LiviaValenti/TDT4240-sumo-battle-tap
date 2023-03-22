package tdt4240.group2.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PlayState extends State{
    Texture settingsWheel;
    Texture player1Tex;
    Texture player2Tex;

    Sprite player1sprite;
    Sprite player2sprite;
    ShapeRenderer shapeRenderer;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        shapeRenderer = new ShapeRenderer();
        settingsWheel = new Texture("settingswheel.png");
        player1Tex = new Texture("purplehand.png");
        player2Tex = new Texture("greenhand.png");
        player1sprite = new Sprite(player1Tex) ;
        player2sprite = new Sprite(player2Tex)    ;
      }

    @Override
    protected void handleInput(String name) {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(252/255f,231/255f,239/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.circle(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth()/2+20);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(255/255f,236/255f,136/255f, 1);
        shapeRenderer.circle(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth()/2-70);
        shapeRenderer.end();  
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);                                                                     
        shapeRenderer.setColor(1, 1, 1, 1);                                                                                      
        shapeRenderer.circle(Gdx.graphics.getWidth()+10, Gdx.graphics.getHeight()/2, 160);
        shapeRenderer.end();                                                                                                     
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.circle(-10, Gdx.graphics.getHeight()/2, 160);
        shapeRenderer.end();
        sb.begin();
        sb.draw(settingsWheel, Gdx.graphics.getWidth()-settingsWheel.getWidth(), Gdx.graphics.getHeight()/2-settingsWheel.getHeight()/2 );
        sb.draw(player1sprite, Gdx.graphics.getWidth()/2-player1sprite.getWidth()/2, 0);
        sb.draw(player2sprite, Gdx.graphics.getWidth()/2-player2sprite.getWidth()/2, Gdx.graphics.getHeight()-player2sprite.getHeight())     ;
        sb.end();
    }
    @Override
    public void dispose() {

    }
}
