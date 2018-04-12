package com.mygdx.game.components.menucomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GdxGame;
import com.mygdx.game.components.Component;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Grid extends Component{

    private ArrayList<ImageButton> squares;
    private float squareHeight;
    private float squareWidth;
    private int rowCount;
    private int columnCount;


    private ArrayList<ActionListener> listeners;

    public Grid(List<ImageButton> buttons){
        super();
        squares = new ArrayList<ImageButton>();
        for (ImageButton t : buttons){
            squares.add(t);
        }
        squareWidth = squares.get(0).getWidth();
        squareHeight = squares.get(0).getHeight();
        int rest = squares.size()%4;
        rowCount = (squares.size() - rest) / 4;
        if(squares.size()>=4){
            width = squareWidth*4;
            columnCount = 4;
            if (rest>0){
                height = (rowCount+1)*squareHeight;
            }
            else{
                height = rowCount*squareHeight;
            }

        }
        if(squares.size()<4){
            width = buttons.size()*squareWidth;
            columnCount = buttons.size();
            height = squareHeight;
        }
    }
    public float getSquareHeight() {
        return squareHeight;
    }

    public float getSquareWidth() {
        return squareWidth;
    }

    public ImageButton getSquare(int pos){
        return squares.get(pos);
    }

    public void positionGrid(boolean positive){
        float c;
        if(positive){c=1;}
        else{c=-(2*width);}
        float gridX = c*position.x;
        float gridY= c*position.y;
       for (int i = 0; i<squares.size(); i++){
            if (i!=0 && i%4==0){
                gridY =- squareHeight;
                gridX = position.x;
            }
            squares.get(i).setPosition(gridX,gridY);
            gridX =+ squareWidth;
        }
        }

    public void update(){
        if(Gdx.input.justTouched()){
            float posX = Gdx.input.getX();
            float posY = GdxGame.HEIGHT - Gdx.input.getY();

            if(posX > position.x && posX < position.x + width && posY > position.y && posY < position.y + height){

            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        float gridX = position.x;
        float gridY=position.y;
       for (int i = 0; i<squares.size(); i++){

           if (i!=0 && i%4==0){
               gridY =- squareHeight;
               gridX = position.x;
           }

           squares.get(i).setPosition(gridX,gridY);
           sb.draw(squares.get(i).getTexture(),squares.get(i).getPosition().x,squares.get(i).getPosition().y,squareWidth,squareHeight);
           gridX =+ squareWidth;
        }

    }

    @Override
    public void dispose() {
        for (ImageButton t : squares){
            t.dispose();
        }
    }
}
