package com.mygdx.game.components.gamecomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GdxGame;

//simple OrthographicCamera that handles zooming and moves viewport based on key input given a stage (takes in Sprite)
public class GameCamera {

    private OrthographicCamera camera;
    private Sprite stage;

    public void GameCamera(Sprite stage){
        this.stage = stage;
        stage.setSize(GdxGame.WIDTH,GdxGame.HEIGHT);
        float w = GdxGame.WIDTH;
        float h = GdxGame.HEIGHT;

        camera = new OrthographicCamera(w,h);
        camera.update();
    }

    public void handleInput(){
        //zoom in
        if (Gdx.input.isKeyPressed(Input.Keys.Q)){
            camera.zoom += 0.02;
        }
        //zoom out
        if (Gdx.input.isKeyPressed(Input.Keys.E)){
            camera.zoom -= 0.02;
        }
        //move camera to left
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            camera.translate(-3,0,0);
        }
        // move camera to right
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            camera.translate(3,0,0);
        }
        //move camera up
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
               camera.translate(0,3,0);
        }
        //move camera down
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            camera.translate(0,-3,0);
        }

        camera.zoom = 100f;
        camera.position.x = 0;
        camera.position.y = 0;
    }

    //basic method for resizing when the display size changes, always shows 30 units in X-axis
    public void resize(int width, int height){
        camera.viewportWidth = 30f;
        camera.viewportHeight = 30f * height/width;
        camera.update();
    }

    public void dispose(){
        stage.getTexture().dispose();
    }

    public void render(SpriteBatch sb){
        handleInput();
        camera.update();
        sb.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        stage.draw(sb);
        sb.end();
    }



}
