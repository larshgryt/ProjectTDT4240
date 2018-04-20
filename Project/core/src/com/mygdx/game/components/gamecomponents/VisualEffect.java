package com.mygdx.game.components.gamecomponents;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;
import com.mygdx.game.sprites.Animation;
import com.mygdx.game.states.State;

public class VisualEffect extends Component {

    Animation animation;
    boolean loops;
    float maxFrameTime;
    float time;
    float maxTime;
    State state;

    public VisualEffect(State state, Animation animation, float maxFrameTime){
        super();
        this.animation = animation;
        animation.setMaxFrameTime(maxFrameTime);
        time = 0;
        maxTime = maxFrameTime * animation.getFrameCount();
        loops = false;
        this.state = state;
        this.maxFrameTime = maxFrameTime;
    }

    @Override
    public void update(float dt){
        super.update(dt);
        if(time > maxTime){
            if(state != null){
                state.removeComponent(this);
            }
            dispose();
        }
        else{
            time += dt;
        }
    }

    public VisualEffect getInstance(State state){
        Animation a = animation.copy();
        a.reset();
        return new VisualEffect(state, a, maxFrameTime);
    }

    @Override
    public void render(SpriteBatch sb) {
        animation.render(sb,position.x, position.y,width,height,angle);
    }

    @Override
    public void dispose() {
        animation.dispose();
    }
}
