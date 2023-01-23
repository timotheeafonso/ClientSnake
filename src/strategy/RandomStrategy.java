package strategy;

import java.util.ArrayList;
import java.util.Random;

import model.Agent;
import model.InputMap;
import model.Snake;
import utils.AgentAction;
import utils.Position;

public class RandomStrategy implements GameStrategy {

    public boolean isLegalMove(Snake snake,AgentAction action, InputMap inputMap) {
		
        int x=snake.getPosition().get(0).getX();
		int y=snake.getPosition().get(0).getY();
		boolean legal =true;
		switch(action){
			case MOVE_DOWN:
                if(y<inputMap.getSizeY()-1)
                legal=!inputMap.get_walls()[x][y+1];
                break;
			case MOVE_LEFT:
                if(x>0)
                legal=!inputMap.get_walls()[x-1][y];
				break;
			case MOVE_RIGHT:
                if(x<inputMap.getSizeX()-1)
                legal=!inputMap.get_walls()[x+1][y];
				break;
			case MOVE_UP:
                if(y>0)
                legal=!inputMap.get_walls()[x][y-1];
				break;
		}
        if(snake.getInvincible()<20){
            legal=true;
        }

    	return legal;
	}

    @Override
    public Position getNewPosition(Snake s,InputMap inputMap,ArrayList<Agent> agents) {
        boolean legal=false;
        ArrayList<Integer> possible = new ArrayList<Integer>();
        possible.add(0);
        possible.add(1);
        possible.add(2);
        possible.add(3);
        int x=s.getPosition().get(0).getX();
        int y=s.getPosition().get(0).getY();
        Integer i=null;
        AgentAction a=null;
        while(!legal && possible.size()>0){
            if(a!=null && i!=null){
                possible.remove(i);
                x=s.getPosition().get(0).getX();
                y=s.getPosition().get(0).getY();
            }
            if(possible.size()>0){
                Random r = new Random();
                int index=r.nextInt((possible.size()-1 - 0) + 1) + 0;
                switch(possible.get(index)){
                    case 0:
                        i=0;
                        a=AgentAction.MOVE_DOWN;
                        if(y==inputMap.getSizeY()-1){
                            y=0;
                        }else{
                            y+=1;
                        }
                        break;
                    case 1:
                        i=1;
                        if(x==0){
                            x=inputMap.getSizeX()-1;
                        }else{
                            x-=1;
                        }
                        a=AgentAction.MOVE_LEFT;
                        break;
                    case 2:
                        i=2;
                        if(x==inputMap.getSizeX()-1){
                            x=0;
                        }else{
                            x+=1;
                        }
                        a=AgentAction.MOVE_RIGHT;
                        break;
                    case 3:
                        i=3;
                        if(y==0){
                            y=inputMap.getSizeY()-1;
                        }else{
                            y-=1;
                        }
                        a=AgentAction.MOVE_UP;
                        break;
                }
                legal=isLegalMove(s, a, inputMap);
                for (Position p : s.getPosition()){
                    if(p.getX()==x && p.getY()==y){
                        legal=false;
                    }
                }
                for(Agent ag : agents){
                    Snake s2 = (Snake)ag;
                    if(s2.getColor()!=s.getColor()){
                        for(Position p2 : s2.getPosition()){
                            if(x==p2.getX() && y==p2.getY()){
                                legal=false;
                            }
                        }
                    }
                }
            }else{
                x=0;
                y=0;
            }

        }
        
        Position p = new Position(x, y);
        int index=agents.indexOf(s);
        s.setLastAgentAction(a);
        inputMap.getStart_snakes().get(index).setLastAction(a);
        return p;
    }
    
}
