package strategy;

import java.util.ArrayList;

import model.Agent;
import model.InputMap;
import model.Snake;
import utils.AgentAction;
import utils.Position;

public interface GameStrategy {
    public static void getPosition(Position p,AgentAction action){
        switch(action){
            case MOVE_UP:
                p.setX(p.getX()+1);
                break;
            case MOVE_DOWN:
                p.setX(p.getX()-1);
                break;
            case MOVE_LEFT:
                p.setX(p.getY()-1);
                break;
            case MOVE_RIGHT:
                p.setX(p.getY()+1);
                break;
            }
    }
    
    public  Position getNewPosition(Snake s,InputMap inputMap,ArrayList<Agent> agents);
}
