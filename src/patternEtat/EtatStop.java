package patternEtat;

public class EtatStop extends Etat{

	public EtatStop() {
		super();
	}

	@Override
	public boolean onStart() {
		return true;
	}

	@Override
	public boolean onRestart() {
		return false;
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
