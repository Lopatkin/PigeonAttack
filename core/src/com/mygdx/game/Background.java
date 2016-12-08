package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Administrator on 08.12.16.
 */
public class Background {
    class Pony {
        private Vector2 position;
        private float speed;

        public Pony(){
            speed = 1.0f + (float)Math.random()*5.0f;
            position = new Vector2((float)Math.random()*784, (float)Math.random()*150);
        }

        public void update(){
            position.x -= speed ;
            if(position.x<-220){
                position.x = 800;
                position.y= (float)Math.random()*150;
            }
        }
    }

    private Texture texture;
    private Texture texturePony;
    private final int PONYS_COUNT = 5;
    private Pony[] ponys;

    public Background(){
        texture = new Texture("bg.png");
        texturePony = new Texture("pony.png");
        ponys = new Pony[PONYS_COUNT];
        for (int i=0; i < PONYS_COUNT; i++){
            ponys[i] = new Pony();
        }
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, 0, 0);
        for (int i=0; i < PONYS_COUNT; i++){
            batch.draw(texturePony, ponys[i].position.x, ponys[i].position.y);
        }
    }

    public void update(){
        for (int i=0; i < PONYS_COUNT; i++) {
            ponys[i].update();
        }
    }
}
