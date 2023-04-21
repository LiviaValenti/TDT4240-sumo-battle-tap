package prog.sumo;

import com.badlogic.gdx.graphics.Texture;

public class Character {
    private final String name;
    private final Texture texture;

    public Character(String characterName, Texture characterTexture) {
        this.name = characterName;
        this.texture = characterTexture;
    }

    public String getName() {
        return name;
    }

    public Texture getTexture() {
        return texture;
    }
}
