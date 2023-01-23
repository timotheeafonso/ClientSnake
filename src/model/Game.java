package model;
import java.util.ArrayList;
import patternObservateur.Observable;
import patternObservateur.Observateur;

public abstract class Game implements Runnable,Observable{
	private int turn;
	private int maxturn;
	private boolean isRunning;
	private boolean play;
	private Thread thread;
	private long time = 500;
	private ArrayList<Observateur> listObs;
	protected int nbJoueur;

	
	public Game(int maxturn) {
		this.maxturn = maxturn;
		listObs=new ArrayList<Observateur>();
	}

	public void init() {
		turn=0;
		isRunning=true;
		play=false;
		initializeGame();
		notifierObservateurs();
	}
		
	public void step() {
		if(gameContinue() && turn<maxturn) {
			turn++;
			takeTurn();
			notifierObservateurs();
		}else {
			isRunning=false;
			play=false;
		}
	}
	
	public void pause() {
		play=false;
	}
	
	public void run() {
		while(play) {
			step();
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	public abstract void initializeGame();	
	public abstract void takeTurn();
	public abstract boolean gameContinue();	
	public abstract void gameOver();

	public ArrayList<Observateur> getListObs() {
		return listObs;
	}
	
	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getMaxturn() {
		return maxturn;
	}

	public void setMaxturn(int maxturn) {
		this.maxturn = maxturn;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public boolean isPlay() {
		return play;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	

	public void setPlay(boolean play) {
		this.play = play;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public void launch() {
		play=true;
		thread = new Thread(this);
		thread.start();
	}

	public int getNbJoueur() {
		return nbJoueur;
	}

	
}
