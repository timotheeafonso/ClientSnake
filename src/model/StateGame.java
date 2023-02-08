package model;

import java.io.Serializable;
import java.util.ArrayList;


import utils.FeaturesItem;
import utils.FeaturesSnake;
import utils.Position;

public class StateGame implements Serializable{
	public ArrayList<FeaturesSnake> snakes ;
	public ArrayList<FeaturesItem> items ;
    //public InputMap map;
    // Default constructor
    public StateGame(){}
    public StateGame(ArrayList<FeaturesSnake> snakes,ArrayList<FeaturesItem> items)
    {
        this.snakes=snakes;
        this.items=items;
     //   this.map=map;
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
