package patternEtat;

public class EtatFin  extends Etat{

	public EtatFin() {
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
		return false;
	}

	@Override
	public boolean onStep() {
		return false;
	}

}
