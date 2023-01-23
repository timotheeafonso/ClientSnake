package fabrique;

import java.util.ArrayList;

import model.Agent;
import model.InputMap;
import model.Item;
import utils.FeaturesItem;
import utils.Position;

public class FabriqueItem extends FabriqueAgent{
    @Override
    public ArrayList<Agent> createAgent(InputMap inputMap) {
        ArrayList<Agent> items = new ArrayList<Agent>();
        for( FeaturesItem start_items : inputMap.getStart_items()) {
			Position p = new Position(start_items.getX(),start_items.getY());
			Item i=new Item(p,start_items.getItemType());	
			items.add(i);      
        }
        return items;
    }
    
}
