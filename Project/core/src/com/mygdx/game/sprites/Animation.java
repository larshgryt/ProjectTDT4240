package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Animation implements Sprite{

    private Array<Texture> frames;      // Array of animation frames as Textures.
    private float maxFrameTime;         // Max time a frame will be shown.
    private float currentFrameTime;     // How long the current frame has been shown.
    private int frameCount;             // Number of total frames.
    private int frame;                  // The index of the current frame.
    private boolean horizontalFlip;     // Whether the animation should flip horizontally when rendered.
    private boolean verticalFlip;       // Whether the animation should flip vertically when rendered.

    /* Creates a new animation using the given array of frames. */
    public Animation(Array<Texture> frames, float maxFrameTime){
        this.frames = frames;
        frameCount = frames.size - 1;
        this.maxFrameTime = maxFrameTime;
        frame = 0;
        horizontalFlip = false;
        verticalFlip = false;
    }

    /* Creates an empty animation with no frames. */
    public Animation(float maxFrameTime){
        this.frames = new Array<Texture>();
        frameCount = 0;
        this.maxFrameTime = maxFrameTime;
        frame = 0;
        horizontalFlip = false;
        verticalFlip = false;
    }

    public float getWidth(){
        return getFrame().getWidth();
    }
    public float getHeight(){
        return getFrame().getHeight();
    }

    public void setHorizontalFlip(boolean flip){
        horizontalFlip = flip;
    }
    public void setVerticalFlip(boolean flip){
        verticalFlip = flip;
    }

    /* Removes all frames from the animation. */
    public void clearFrames(){
        this.frames.clear();
        frameCount = 0;
        frame = 0;
    }

    /* Adds the texture to the end of the frame array. */
    public void addFrame(Texture texture){
        this.frames.add(texture);
        frameCount ++;
    }

    /* Returns the current frame of the animation. */
    public Texture getFrame() {
        return frames.get(frame);
    }


    /* Renders the animation on the given SpriteBatch, at the given location with the given
    dimensions.
     */
    @Override
    public void render(SpriteBatch sb, float x, float y, float width, float height, float angle) {
        sb.draw(getFrame(), x, y, width/2, height/2, width, height, 1, 1,
                (float)Math.toDegrees(angle),0, 0, getFrame().getWidth(),
                getFrame().getHeight(), horizontalFlip, verticalFlip);
    }

    @Override
    public void render(SpriteBatch sb, float x, float y, float width, float height, float angle, float originX, float originY) {
        sb.draw(getFrame(), x, y, originX, originY, width, height, 1, 1,
                (float)Math.toDegrees(angle),0, 0, getFrame().getWidth(),
                getFrame().getHeight(), horizontalFlip, verticalFlip);
    }

    @Override
    public void update(float dt) {
        if(frames.size > 0){
            currentFrameTime += dt;
            if (currentFrameTime >= maxFrameTime) {
                frame++;
                currentFrameTime = 0;
            }
            if (frame >= frameCount) {
                frame = 0;
            }
        }
    }

    @Override
    public void dispose(){
        for(Texture t: frames){
            t.dispose();
        }
    }

    @Override
    public void reset(){
        frame = 0;
        currentFrameTime = 0;
    }
}

