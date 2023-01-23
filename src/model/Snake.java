package model;

import java.util.ArrayList;

import utils.AgentAction;
import utils.ColorSnake;
import utils.Position;

public class Snake extends Agent {
	private ColorSnake color;
	private int vie=20;
	private int invincible=20;
	private AgentAction lastAgentAction;
	
	public Snake(ArrayList<Position> position, ColorSnake color) {
		this.position =position;
		this.color = color;
		this.lastAgentAction=null;
	}
	
	public boolean contient(Position position){
		boolean cont=false;
		int i=0;
		for(Position p : this.position){
			if(i>0){
				if(p.getX()==position.getX() && p.getY()==position.getY()){
					cont=true;
				}
			}
			i++;
		}
		return cont;
	}
	public String toString() {
		return position.toString()+" : "+color;
	}


	public void incremente(){
		if(vie<20)
		this.vie++;
		if(invincible<20)
		this.invincible++;
	}

	public void setVie(int vie) {
		this.vie = vie;
	}

	public ColorSnake getColor() {
		return color;
	}

	public int getVie() {
		return vie;
	}

	public void setColor(ColorSnake color) {
		this.color = color;
	}

	public int getInvincible() {
		return invincible;
	}

	public void setInvincible(int invincible) {
		this.invincible = invincible;
	}

	public AgentAction getLastAgentAction() {
		return lastAgentAction;
	}

	public void setLastAgentAction(AgentAction lastAgentAction) {
		this.lastAgentAction = lastAgentAction;
	}
	
	
}
