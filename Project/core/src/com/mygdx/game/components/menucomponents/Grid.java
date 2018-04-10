package com.mygdx.game.components.menucomponents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Component;

import java.util.ArrayList;
import java.util.List;

public class Grid extends Component{

    private ArrayList<Texture> squares;
    private float squareHeight;
    private float squareWidth;
    private int rowCount;
    private int columnCount;


    public Grid(List<Texture> textures){
        super();
        squares = new ArrayList<Texture>();
        for (Texture t : textures){
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
            width = textures.size()*squareWidth;
            columnCount = textures.size();
            height = squareHeight;
        }


    }

    public float getSquareHeight() {
        return squareHeight;
    }

    public float getSquareWidth() {
        return squareWidth;
    }

    public Texture getSquare(int pos){
        return squares.get(pos);
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
           sb.draw(squares.get(i),gridX,gridY,squareWidth,squareHeight);
           gridX =+ squareWidth;
        }

    }

    @Override
    public void dispose() {
        for (Texture t : squares){
            t.dispose();
        }
    }
}
