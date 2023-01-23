package patternEtat;


public class EtatStart extends Etat  {

	public EtatStart() {
		super();
	}

	@Override
	public boolean onStart() {
		return false;
	}

	@Override
	public boolean onRestart() {
		return true;
	}

	@Override
	public boolean onPause() {
		return true;
	}

	@Override
	public boolean onStep() {
		return false;
	}

}
