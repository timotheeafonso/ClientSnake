package controller;

import patternEtat.EtatStart;
import patternEtat.EtatStop;
import view.ViewCommand;
import view.ViewSnakeGame;
import java.awt.event.*;
import java.io.*;
import java.awt.Frame;

public class ControllerSnakeGame extends AbstractController implements Serializable{
	
	ViewSnakeGame viewSnakeGame;
	ViewCommand vc;
	int keyCode;
	String fileName;
	int strat;
	boolean pause;
	boolean restart;
	boolean step;
	boolean play;
	int turn;
	int maxTurn;
	boolean quit;

	public ControllerSnakeGame() {
		strat=0;
		viewSnakeGame=new ViewSnakeGame();
		this.changeEtat(new EtatStop());
		keyCode=0;
		vc=  new ViewCommand(this);
		quit=false;

		viewSnakeGame.getjFrame().addKeyListener (new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			keyCode = e.getKeyCode();
			if(etat instanceof EtatStop && strat==0){
				changeEtat(new EtatStart());
			}
		}});

		viewSnakeGame.getjFrame().addWindowListener(listener);
		vc.getjFrame().addWindowListener(listener);

		pause=false;
		play=false;
		step=false;
		restart=false;
	}


	WindowListener listener = new WindowAdapter() {
		public void windowClosing(WindowEvent evt) {
		   quit=true;
		}
	 };


	public int getKeyCode() {
		return keyCode;
	}

	public void setFileName(String fileName) {
		this.fileName=fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setStrategy(int strat) {
		this.strat=strat;
	}


	public ViewSnakeGame getViewSnakeGame() {
		return viewSnakeGame;
	}

	public ViewCommand getVc() {
		return vc;
	}


	public  void restart() {
		restart=true;
	}
	public  void step() {
		step=true;

	}
	public void play() {
		play=true;
		
	}
	public void pause() {
		pause=true;
		
	}

	public int getStrat() {
		return strat;
	}

	public boolean isPause() {
		return pause;
	}

	public boolean isRestart() {
		return restart;
	}

	public boolean isStep() {
		return step;
	}

	public boolean isPlay() {
		return play;
	}

    public void resetButton() {
		pause=false;
		play=false;
		step=false;
		restart=false;
    }

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getMaxTurn() {
		return maxTurn;
	}

	public void setMaxTurn(int maxTurn) {
		this.maxTurn = maxTurn;
	}

	public boolean isQuit() {
		return quit;
	}

	
    
}