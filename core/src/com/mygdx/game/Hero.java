package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    private Texture texture;
    private Vector2 position;
    private float speed;
    private int fireCounter;
    private int fireRate;

    public Hero(Vector2 position){
        this.position = position;
        texture = new Texture("Edinorog.png");
        speed = 4.0f;
        fireRate = 15;
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, position.x, position.y);
    }

    public void update(){
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            position.y += speed;
            if(position.y > 500) position.y = 500;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            position.y -= speed;
            if(position.y < -50) position.y = -50;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            position.x -= speed;
            if(position.x < -150) position.x = -150;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            position.x += speed;
            if(position.x > 585) position.x = 585;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            fireCounter++;
            if(fireCounter > fireRate) {
                fireCounter = 0;
                for (int i = 0; i < MyGdxGame.radugas.length; i++) {
                    if(!MyGdxGame.radugas[i].isActive()) {
                        MyGdxGame.radugas[i].setup(position.x+165, position.y+88);
                        break;
                    }
                }
            }

        }
}
}
