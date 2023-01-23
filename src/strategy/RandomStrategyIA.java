package strategy;

import java.util.ArrayList;
import model.Agent;
import model.InputMap;
import model.Snake;
import utils.AgentAction;
import utils.FeaturesItem;
import utils.ItemType;
import utils.Position;

public class RandomStrategyIA implements GameStrategy {

    public boolean isLegalMove(Snake snake,Position p , InputMap inputMap) {
		
        
		boolean legal =true;
        boolean legal2 =true;

        if(p.getX()>inputMap.getSizeX()-1){
            p.setX(0);
        }
        if(p.getX()<0){
            p.setX(inputMap.getSizeX()-1);
        }
        if(p.getY()>inputMap.getSizeY()-1){
            p.setY(0);
        }
        if(p.getY()<0){
            p.setY(inputMap.getSizeY()-1);
        }

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
   
    	return (legal && legal2);
	}

    public boolean caselibre(Snake s,Position p,ArrayList<Agent> agents,Position pPomme,ArrayList<Position> pPoison){
        boolean libre =true;
        boolean libre2=true;
        for(Agent a : agents){
            Snake s2 = (Snake)a;
            if(s2.getColor()!=s.getColor()){
                for(Position p2 : s2.getPosition()){
                    if(p.getX()==p2.getX() && p.getY()==p2.getY()){
                        libre=false;
                    }
                }
                if(s2.getPosition().size()>=s.getPosition().size()){
                    if(Math.abs(s2.getPosition().get(0).getX()-p.getX())<=1 && Math.abs(s2.getPosition().get(0).getY()-p.getY())<=1){
                        libre=false;
                    }

                }
            }
        }

        for(Position p2 : pPoison){
            if(p2.getX()==p.getX() && p2.getY()==p.getY()){
                libre2=false;
            }
        }

        return libre && libre2;
    }

    @Override
    public Position getNewPosition(Snake s,InputMap inputMap,ArrayList<Agent> agents) {
        int piX=0;
        int piY=0;
        int psX=s.getPosition().get(0).getX();
        int psY=s.getPosition().get(0).getY(); 
        int newPX=psX;
        int newPY=psY;
        Position pPomme=null;
        AgentAction action = s.getLastAgentAction();
        ArrayList<Position> pPoison=new ArrayList<Position>(); 
        for(FeaturesItem fi : inputMap.getStart_items()){
            if(fi.getItemType()==ItemType.APPLE){
                piX=fi.getX();
                piY=fi.getY();
                pPomme=new Position(piX, piY);
            }
            if(fi.getItemType()==ItemType.SICK_BALL){
                pPoison.add(new Position(fi.getX(), fi.getY()));
            }
        }
        // Poison = random
        if(s.getVie()<20){
            RandomStrategy rd = new RandomStrategy();
            return rd.getNewPosition(s, inputMap, agents); 

        // Wall
        }else if(inputMap.get_walls()[0][0] && s.getInvincible()>=19){
            if(piX<psX  && s.contient(new Position(psX-1,psY))==false && caselibre(s,new Position(psX-1,psY),agents,pPomme,pPoison)){
                    newPX=psX-1;
                    action=AgentAction.MOVE_LEFT;
            }else if(piX>psX && s.contient(new Position(psX+1,psY))==false && caselibre(s,new Position(psX+1,psY),agents,pPomme,pPoison)){
                newPX=psX+1;
                action=AgentAction.MOVE_RIGHT;
            }else if(piY<psY && s.contient(new Position(psX,psY-1))==false && caselibre(s,new Position(psX,psY-1),agents,pPomme,pPoison)){
                newPY=psY-1;
                action=AgentAction.MOVE_UP;
            }else if(piY>psY && s.contient(new Position(psX,psY+1))==false && caselibre(s,new Position(psX,psY+1),agents,pPomme,pPoison)){
                newPY=psY+1;
                action=AgentAction.MOVE_DOWN;
            }else{
                RandomStrategy rd = new RandomStrategy();
                return rd.getNewPosition(s, inputMap, agents); 
            }
        // No wall
        }else{
            boolean moove=false;
            if(piX<psX){
                if(Math.abs(piX-psX)>inputMap.getSizeX()-Math.abs(piX-psX) && caselibre(s,new Position(psX+1,psY),agents,pPomme,pPoison) && s.contient(new Position(psX+1,psY))==false){
                    newPX=psX+1;
                    action=AgentAction.MOVE_RIGHT;
                    moove=true;
                }else{
                    if(caselibre(s,new Position(psX-1,psY),agents,pPomme,pPoison) && s.contient(new Position(psX-1,psY))==false){
                        newPX=psX-1;
                        action=AgentAction.MOVE_LEFT;
                        moove=true;
                    }
                }
            }else if(piX>psX){
                if(Math.abs(piX-psX)>inputMap.getSizeX()-Math.abs(piX-psX) && caselibre(s,new Position(psX-1,psY),agents,pPomme,pPoison) && s.contient(new Position(psX-1,psY))==false){
                    newPX=psX-1;
                    moove=true;
                    action=AgentAction.MOVE_LEFT;
                }else{
                    if(caselibre(s,new Position(psX+1,psY),agents,pPomme,pPoison) && s.contient(new Position(psX+1,psY))==false){
                        newPX=psX+1;
                        action=AgentAction.MOVE_RIGHT;
                        moove=true;
                    }
                }
            }else if(piY<psY){
                if(Math.abs(piY-psY)>inputMap.getSizeY()-Math.abs(piY-psY) && caselibre(s,new Position(psX,psY+1),agents,pPomme,pPoison)&& s.contient(new Position(psX,psY+1))==false){
                    newPY=psY+1;
                    action=AgentAction.MOVE_DOWN;
                    moove=true;
                }else{
                    if(caselibre(s,new Position(psX,psY-1),agents,pPomme,pPoison)&& s.contient(new Position(psX,psY-1))==false){
                        newPY=psY-1;
                        action=AgentAction.MOVE_UP;
                        moove=true;
                    }
                }
            }else if(piY>psY){
                if(Math.abs(piY-psY)>inputMap.getSizeY()-Math.abs(piY-psY) && caselibre(s,new Position(psX,psY-1),agents,pPomme,pPoison)&& s.contient(new Position(psX,psY-1))==false){
                    newPY=psY-1;
                    action=AgentAction.MOVE_UP;
                    moove=true;
                }else{
                    if(caselibre(s,new Position(psX,psY+1),agents,pPomme,pPoison)&& s.contient(new Position(psX,psY+1))==false){
                        newPY=psY+1;
                        action=AgentAction.MOVE_DOWN;
                        moove=true;
                    }
                }
            }
            if(!moove){
                RandomStrategy rd = new RandomStrategy();
                return rd.getNewPosition(s, inputMap, agents);
            }
        }

        Position p =new Position(newPX, newPY);
        int index=agents.indexOf(s);
        inputMap.getStart_snakes().get(index).setLastAction(action);
        
        if(isLegalMove(s,p, inputMap)){
            return p;
        }else{
            if(s.getInvincible()<20){
                return p;
            }else{
                return new Position(0,0);        
            }
        }
    }
    
}
