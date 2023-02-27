package model;

import java.io.Serializable;
import java.util.ArrayList;


import utils.FeaturesItem;
import utils.FeaturesSnake;
import utils.Position;

public class StateGame implements Serializable{
	public ArrayList<FeaturesSnake> snakes ;
	public ArrayList<FeaturesItem> items ;
    public int sizeX;
    public int sizeY;
    public boolean[][] walls;
    public int turn;
    public int maxTurn;

    public StateGame(){}
    public StateGame(ArrayList<FeaturesSnake> snakes,ArrayList<FeaturesItem> items,int sizeX, int sizeY, boolean[][] walls,int turn,int maxTurn)
    {
        this.snakes=snakes;
        this.items=items;
        this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.walls = walls;	
        this.turn=turn;
        this.maxTurn=maxTurn;
    }

    public String toString(){
        String msg="";
        for(FeaturesSnake fs : snakes){
            for(Position p : fs.getPositions()){
                msg+=p.toString();
           }
           msg+="\n";
        }
        return msg;
    }
}
