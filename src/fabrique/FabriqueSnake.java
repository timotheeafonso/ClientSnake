package fabrique;

import java.util.ArrayList;

import model.Agent;
import model.InputMap;
import model.Snake;
import utils.FeaturesSnake;
import utils.Position;

public class FabriqueSnake extends FabriqueAgent{

    @Override
    public ArrayList<Agent> createAgent(InputMap inputMap) {
        ArrayList<Agent> snakes = new ArrayList<Agent>();
        for( FeaturesSnake start_snakes : inputMap.getStart_snakes()) {			
			ArrayList<Position> positions=start_snakes.getPositions();
			Snake s=new Snake(positions,start_snakes.getColorSnake());	
			snakes.add(s);
		}
        return snakes;
    }
    
}
