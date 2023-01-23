package controller;

import model.Game;
import patternEtat.Etat;

public abstract class AbstractController {
	Game game;
	Etat etat;
	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public  void restart() {
		game.init();
		game.pause();
	}
	public  void step() {
		game.step();

	}
	public  void play() {
		game.launch();
		
	}
	public void pause() {
		game.pause();
		

	}
	public  void setSpeed(double speed) {
		game.setTime(Double.valueOf(speed*100).longValue());
	}
	
	public void changeEtat(Etat etat) {
        this.etat = etat;
    }
	
	public Etat getEtat() {
		return etat;
	}

}
