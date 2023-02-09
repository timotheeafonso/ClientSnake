package controller;

import patternEtat.EtatStart;
import patternEtat.EtatStop;
import utils.AgentAction;
import utils.ColorSnake;
import view.ViewCommand;
import model.Agent;
import model.InputMap;
import model.Item;
import model.Snake;
import model.SnakeGame;
import model.StateGame;
import view.PanelSnakeGame;
import view.ViewSnakeGame;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import com.google.gson.Gson; 




public class ControllerSnakeGame extends AbstractController implements Serializable{
	InputMap inputMap;
	PanelSnakeGame panel;
	ViewSnakeGame viewSnakeGame;
	ViewCommand vc;
	int keyCode;
	

	public int getKeyCode() {
		return keyCode;
	}

	public ViewSnakeGame getViewSnakeGame() {
		return viewSnakeGame;
	}

	public ViewCommand getVc() {
		return vc;
	}



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


	public void keySnakeDirection(){
		ControllerSnakeGame cs = this;

		viewSnakeGame.getjFrame().addKeyListener (new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			if(!((SnakeGame)game).isPlay()){
				cs.play();
	        	cs.changeEtat(new EtatStart());
				vc.changeEtat();
			}
			keyCode = e.getKeyCode();
			ArrayList<Agent> agents = ((SnakeGame)game).getSnakes();
			ArrayList<Agent> items = ((SnakeGame)game).getItems();

			/* 
			String dest="localhost";
			int p=2627;		

			Agent [] tab = new Agent[agents.size()];
			for (int i=0;i<agents.size();i++){
				tab[i]=agents.get(i);
			}*/
			/* 
			try{// on connecte un socket
				//Socket so = new Socket(dest, p);
				Socket so=new Socket(s, p);
				OutputStream outputStream = so.getOutputStream();
		
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
				String msg="keycode";
				//StateGame sg = new StateGame(keyCode,cs.inputMap.getStart_snakes(),cs.inputMap.getStart_items());
				
				//Gson gson = new Gson();
				//String json = gson.toJson(sg);
				objectOutputStream.writeObject(msg);
				objectOutputStream.writeObject(keyCode);
				outputStream.close();

				
	
			} catch(UnknownHostException exc) {System.out.println(e);}
			catch (IOException exc) {System.out.println("Aucun serveur n’est rattaché au port ");}

			*/

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