package prog.sumo.models;

import com.badlogic.gdx.graphics.Texture;

public class Character {
    private final String name;
    private final Texture texture;

    public Character(String name, Texture texture) {
        this.name = name;
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public Texture getTexture() {
        return texture;
    }
}