package controller;

import patternEtat.EtatStart;
import patternEtat.EtatStop;
import utils.AgentAction;
import utils.ColorSnake;
import view.ViewCommand;
import model.Agent;
import model.InputMap;
import model.Snake;
import model.SnakeGame;
import view.PanelSnakeGame;
import view.ViewSnakeGame;
import java.awt.event.*;
import java.util.ArrayList;

public class ControllerSnakeGame extends AbstractController{
	InputMap inputMap;
	PanelSnakeGame panel;
	ViewSnakeGame viewSnakeGame;
	ViewCommand vc;
	public ControllerSnakeGame() {
		String fileName="layouts/smallArena.lay";
		try {
			inputMap = new InputMap(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setInitMap(inputMap,fileName);
		this.keySnakeDirection();
	}

	public void setInitMap(InputMap inputMap,String fileName){
		boolean changeMap=false;
		if(game!=null){
			changeMap=true;
		}
		game=new SnakeGame(500,inputMap,fileName);
		panel=new PanelSnakeGame(inputMap.getSizeX(),inputMap.getSizeY(), inputMap.get_walls(), inputMap.getStart_snakes(), inputMap.getStart_items());	
		if(!changeMap){
			viewSnakeGame=new ViewSnakeGame(panel);
			this.changeEtat(new EtatStop());
			vc=  new ViewCommand(this);
			game.ajouterObservateur(viewSnakeGame);
			game.ajouterObservateur(vc);
			game.init();
		}else{
			viewSnakeGame.setPanel(panel);
			this.changeEtat(new EtatStop());
			game.ajouterObservateur(viewSnakeGame);
			game.ajouterObservateur(vc);
			game.init();
		}
	}

	public void changeMap(){

	}

	public void keySnakeDirection(){
		ControllerSnakeGame cs = this;
		viewSnakeGame.getjFrame().addKeyListener (new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			if(!((SnakeGame)game).isPlay()){
				cs.play();
	        	cs.changeEtat(new EtatStart());
				vc.changeEtat();
			}
			int keyCode = e.getKeyCode();
			ArrayList<Agent> agents = ((SnakeGame)game).getSnakes();
			for(Agent a : agents){
				Snake s = (Snake)a;
				if(s.getColor()==ColorSnake.Green){
					switch(keyCode){
						case 37:
							if(s.getLastAgentAction()==AgentAction.MOVE_RIGHT && s.getPosition().size()>1){
								
							}else{
								s.setLastAgentAction(AgentAction.MOVE_LEFT);
							}
							break;
						case 38:
							if(s.getLastAgentAction()==AgentAction.MOVE_DOWN && s.getPosition().size()>1)
							{}else{
							s.setLastAgentAction(AgentAction.MOVE_UP);
							}
							break;
						case 39:
							if(s.getLastAgentAction()==AgentAction.MOVE_LEFT && s.getPosition().size()>1)
							{}else{
							s.setLastAgentAction(AgentAction.MOVE_RIGHT);
							}
							break;
						case 40:
							if(s.getLastAgentAction()==AgentAction.MOVE_UP && s.getPosition().size()>1)
							{}else{
							s.setLastAgentAction(AgentAction.MOVE_DOWN);
							}
							break;
						
					}
				}
			
				if(s.getColor()==ColorSnake.Red){
					switch(keyCode){
						case 81:
							if(s.getLastAgentAction()==AgentAction.MOVE_RIGHT && s.getPosition().size()>1){
									
							}else{
							s.setLastAgentAction(AgentAction.MOVE_LEFT);
							}
							break;
						case 90:
							if(s.getLastAgentAction()==AgentAction.MOVE_DOWN && s.getPosition().size()>1)
							{}else{
							s.setLastAgentAction(AgentAction.MOVE_UP);
							}
							break;
						case 68:
							if(s.getLastAgentAction()==AgentAction.MOVE_LEFT && s.getPosition().size()>1)
							{}else{
							s.setLastAgentAction(AgentAction.MOVE_RIGHT);
							}
							break;
						case 83:
							if(s.getLastAgentAction()==AgentAction.MOVE_UP && s.getPosition().size()>1)
							{}else{
							s.setLastAgentAction(AgentAction.MOVE_DOWN);
							}
							break;
						
					}
				}
			}
			}
		});
	}

}