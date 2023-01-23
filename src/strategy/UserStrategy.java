package strategy;

import java.util.ArrayList;

import model.Agent;
import model.InputMap;
import model.Snake;
import utils.AgentAction;
import utils.Position;

public class UserStrategy implements GameStrategy {

    public boolean isLegalMove(Snake snake,Position p , InputMap inputMap) {
		
        
		boolean legal =true;
        boolean legal2 =true;

        for (Position ps : snake.getPosition()){
            if(ps.getX()==p.getX() && ps.getY()==p.getY()){
                legal2=false;
            }
        }

        if(p.getY()<inputMap.getSizeY()-1)
        legal=!inputMap.get_walls()[p.getX()][p.getY()];
        if(p.getX()>0)
        legal=!inputMap.get_walls()[p.getX()][p.getY()];
        if(p.getX()<inputMap.getSizeX()-1)
        legal=!inputMap.get_walls()[p.getX()][p.getY()];
        if(p.getY()>0)
        legal=!inputMap.get_walls()[p.getX()][p.getY()];
        

        if(snake.getInvincible()<20){
            legal=true;
        }
    	return (legal && legal2);
	}

    @Override
    public Position getNewPosition(Snake s, InputMap inputMap, ArrayList<Agent> agents) {
        AgentAction action = s.getLastAgentAction();
        int x=s.getPosition().get(0).getX();
        int y=s.getPosition().get(0).getY();
        if(action!=null){
            switch(action){
                case MOVE_UP:
                    if(y==0)
                        y=inputMap.getSizeY()-1;
                    else
                        y-=1;
                    break;
                case MOVE_DOWN:
                    if(y==inputMap.getSizeY()-1)
                        y=0;
                    else
                        y+=1;
                    break;
                case MOVE_LEFT:
                    if(x==0)
                        x=inputMap.getSizeX()-1;
                    else
                        x-=1;
                    break;
                case MOVE_RIGHT:
                    if(x==inputMap.getSizeX()-1)
                        x=0;
                    else
                        x+=1;
                    break;        
            }

            Position p =new Position(x, y);
            int index=agents.indexOf(s);
            inputMap.getStart_snakes().get(index).setLastAction(action);
            
            
            if(isLegalMove(s,p, inputMap)){
                return p;
            }else{
                
                return new Position(0,0);        
            }
        }else{
            return  s.getPosition().get(0);
        }
    }
    
}
