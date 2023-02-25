package controller;

import patternEtat.Etat;

public abstract class AbstractController {
	Etat etat;
	long time = 500;


	public  void setSpeed(double speed) {
		time = Double.valueOf(speed*100).longValue();
	}
	
	public void changeEtat(Etat etat) {
        this.etat = etat;
    }
	
	public Etat getEtat() {
		return etat;
	}
	public long getTime() {
		return time;
	}

}
