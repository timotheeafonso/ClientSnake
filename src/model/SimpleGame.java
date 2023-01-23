package model;
import patternObservateur.Observateur;

public class SimpleGame extends Game {
	
	public SimpleGame(int maxturn) {
		super(maxturn);
	}

	@Override
	public void initializeGame() {
		
	}

	@Override
	public void takeTurn() {
		System.out.println("Tour "+getTurn()+" du jeux en cour");
	}

	@Override
	public boolean gameContinue() {
		return this.isRunning();
	}

	@Override
	public void gameOver() {
		
	}

	@Override
	public void ajouterObservateur(Observateur o) {
		getListObs().add(o);
	}

	@Override
	public void supprimerObservateur(Observateur o) {
		getListObs().remove(o);
		
	}

	@Override
	public void notifierObservateurs() {
		for(int i=0;i<getListObs().size();i++){
                Observateur o = getListObs().get(i);
                o.actualiser(this);
        }
	}



}
