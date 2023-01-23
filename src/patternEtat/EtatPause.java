package patternEtat;


public class EtatPause extends Etat{

	public EtatPause() {
		super();
	}

	@Override
	public boolean onStart() {
		return true;
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
		return true;
	}

}
