package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Pigeon {
    private static Texture texture;
    private Vector2 position;
    private float speed;
    private Rectangle rect;

    public Rectangle getRect() {
        return rect;
    }

    public Pigeon() {
        if (texture == null)
            texture = new Texture("Pigeon.png");
        speed = 1.0f + (float) Math.random() * 4.0f;
        position = new Vector2(784 + (float) Math.random() * 784, 200 + (float) Math.random() * 300);
        rect = new Rectangle(position.x, position.y, 60, 60);
    }

    public void update() {
        position.x -= speed;
        if (position.x < -120) {
            recreate();
        }
        rect.x = position.x;
        rect.y = position.y;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void recreate() {
        position.x = 784 + (float) Math.random() * 784;
        position.y = 200 + (float) Math.random() * 300;
    }
}
