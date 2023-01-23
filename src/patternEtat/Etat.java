package patternEtat;

public abstract class Etat {
	
	Etat(){
	}
	
    public abstract boolean onStart();
    public abstract boolean onRestart();
    public abstract boolean onPause();
    public abstract boolean onStep();
}
