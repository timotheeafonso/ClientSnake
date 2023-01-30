package model;

import java.util.ArrayList;
import java.util.Random;

import fabrique.FabriqueItem;
import fabrique.FabriqueSnake;
import strategy.GameStrategy;
import strategy.RandomStrategyIA;
import utils.FeaturesItem;
import utils.FeaturesSnake;
import utils.ItemType;
import utils.Position;

public class SnakeGame extends SimpleGame {
	int instance=0;
	InputMap inputMap;
	ArrayList<Agent> snakes;
	ArrayList<Agent> items;
	GameStrategy strategy;
	String mapName;
	
	public SnakeGame(int maxturn,InputMap inputMap,String fileName) {
		super(maxturn);
		this.inputMap=inputMap;
		snakes= new ArrayList<Agent>();
		items= new ArrayList<Agent>();
		strategy = new RandomStrategyIA();
		this.mapName=fileName;
	}

	@Override
	public void initializeGame() {
		super.initializeGame();		
		try {
			inputMap=new InputMap(mapName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		FabriqueSnake fs = new FabriqueSnake();
		FabriqueItem fi = new FabriqueItem();
		snakes= fs.createAgent(inputMap);
		items= fi.createAgent(inputMap);
		if(snakes.size()>1)
		this.nbJoueur=2;
		else
		this.nbJoueur=1;
	}
	
	
	
	public void moveAgent() {
			ArrayList<Agent> agentASup= new ArrayList<Agent>();
			for(Agent agent : snakes){
				Snake s= (Snake)agent;
				ArrayList<Position> newPositions = new ArrayList<Position>();
				for(int i=0;i<agent.getPosition().size();i++){
						if(i==0){
							newPositions.add(strategy.getNewPosition(s,inputMap,snakes));
						}else{
							newPositions.add(agent.getPosition().get(i-1));
						}
				}
				// chexk si snake dans wall
				if(newPositions.get(0).getX()==0 && newPositions.get(0).getY()==0){
					agentASup.add(agent);
				}else{
					s.setPosition(newPositions);
				}
			}
			for(Agent a : agentASup){
				snakes.remove(a);
			}
			checkPosition();
			updateMap();
	}

	public boolean compare(Agent agent1, Agent agent2){
		boolean contact=false;
		for (Position p1 : agent1.getPosition()){
			for(Position p2 : agent2.getPosition()){
				if(p1.getX()==p2.getX() && p1.getY()==p2.getY()){
					contact=true;
				}
			}
		}
		return contact;
	}

	public void checkPosition(){
		ArrayList<Agent> agentASup= new ArrayList<Agent>();
		ArrayList<Item> newItem = new ArrayList<Item>();

		int i=0;
		for(Agent agent : snakes){
			if(i==0){
				//Ckeck si un snake a perdu
				for(Agent agent2 : snakes){
					if(!((Snake)agent).getColor().equals(((Snake)agent2).getColor())){
						if(compare(agent,agent2)){
							if(agent.getPosition().size()>agent2.getPosition().size()){
								agentASup.add(agent2);
							}else if(agent.getPosition().size()<agent2.getPosition().size()){
								agentASup.add(agent);
							}else{
								agentASup.add(agent);
								agentASup.add(agent2);
							}
						}
					}
				}
			}
			i++;
			//Ckeck si un item a ete manger
			for(Agent item : items){
				if(((Item)item).getItemType().equals(ItemType.APPLE) && ((Snake)agent).getPosition().get(0).getX()==((Item)item).getPosition().get(0).getX() && ((Snake)agent).getPosition().get(0).getY()==((Item)item).getPosition().get(0).getY()){
					//if(((Snake)agent).getInvincible()==20){
						if(((Snake)agent).getVie()==20){
							actionItem(agent, item ,newItem);
						}
					//}
				}
				if(((Item)item).getItemType()==ItemType.SICK_BALL && ((Snake)agent).getPosition().get(0).getX()==((Item)item).getPosition().get(0).getX() && ((Snake)agent).getPosition().get(0).getY()==((Item)item).getPosition().get(0).getY()){
					if(((Snake)agent).getInvincible()==20){
						((Snake)agent).setVie(0);
					}
					agentASup.add(item);
				}
				if(((Item)item).getItemType()==ItemType.INVINCIBILITY_BALL && ((Snake)agent).getPosition().get(0).getX()==((Item)item).getPosition().get(0).getX() && ((Snake)agent).getPosition().get(0).getY()==((Item)item).getPosition().get(0).getY()){
					if(((Snake)agent).getVie()==20){
						((Snake)agent).setInvincible(0);
						agentASup.add(item);
					}
				}
				if(((Item)item).getItemType()==ItemType.BOX && ((Snake)agent).getPosition().get(0).getX()==((Item)item).getPosition().get(0).getX() && ((Snake)agent).getPosition().get(0).getY()==((Item)item).getPosition().get(0).getY()){
					if(((Snake)agent).getVie()==20){
						Random rand = new Random();
						int effet = rand.nextInt(2);
						if(effet==0){
							((Snake)agent).setInvincible(0);
						}else{
							((Snake)agent).setVie(0);
						}
						agentASup.add(item);
					}
				}
			}
			((Snake)agent).incremente();
		}
		for(Item item : newItem){
			items.add(item);
		}
		// Supprime snake eliminer
		for(Agent a : agentASup){
			if(a instanceof Snake){
				if(((Snake)a).getInvincible()==20){
					snakes.remove(a);
				}
			}else{
				items.remove(a);
			}
		}
	}

	public Position genererRandomPosition(){
					Random rand = new Random();
					int rand_x =0;
					int rand_y =0;
					boolean existItem=false;
					do{
						existItem=false;
						rand_x = rand.nextInt(inputMap.getSizeX()-2)+1;
        				rand_y = rand.nextInt(inputMap.getSizeY()-2)+1;	
						for(Agent item2 : items){
							if(rand_x == item2.getPosition().get(0).getX() && rand_y == item2.getPosition().get(0).getY() ){
								existItem=true;
							}
						}
						
						for(Agent s : snakes){
							for(Position psnake : s.getPosition()){
								if(rand_x == psnake.getX() && rand_y == psnake.getY() ){
									existItem=true;
								}
							}
						}
					}while(existItem);
		Position p =new Position(rand_x,rand_y);
		return p;
	}

	public void actionItem(Agent agent,Agent item,ArrayList<Item> newItem){
					ArrayList<Position> newPi= new ArrayList<Position>();
					Position p = (inputMap.getStart_snakes().get(snakes.indexOf(agent))).getPositions().get(agent.getPosition().size()-1);
					agent.addUnePosition(p);
						
					Position pi=genererRandomPosition();
					newPi.add(pi);
					item.setPosition(newPi);	

					Random rand = new Random();
					int proba = rand.nextInt(4); // proba 0.25
					if(proba==1){
						Position psi=genererRandomPosition();
						int proba2 = rand.nextInt(3);
						if(proba2==0){
							Item specialItem = new Item(psi,ItemType.SICK_BALL);
							newItem.add(specialItem);
						}else if(proba2==1){
							Item specialItem = new Item(psi,ItemType.INVINCIBILITY_BALL);
							newItem.add(specialItem);
						}else{
							Item specialItem = new Item(psi,ItemType.BOX);
							newItem.add(specialItem);
						}
					}		
	}

	public void updateMap(){
		ArrayList<FeaturesSnake> fsASup= new ArrayList<FeaturesSnake>();
		for(FeaturesSnake fs : this.geInputMap().getStart_snakes()){
			boolean exist=false;
			for(Agent snake : getSnakes()){
				if(fs.getColorSnake().equals(((Snake)snake).getColor())){
					fs.setPositions(((Snake)snake).getPosition());
					if(((Snake)snake).getVie()!=20){
						fs.setSick(true);
					}else{
						fs.setSick(false);
					}
					if(((Snake)snake).getInvincible()!=20){
						fs.setInvincible(true);
					}else{
						fs.setInvincible(false);
					}
					exist=true;
				}
			}
			if(!exist){
				fsASup.add(fs);
			}
		}
		for(FeaturesSnake fs : fsASup){
			this.geInputMap().getStart_snakes().remove(fs);
		}

		ArrayList<FeaturesItem> arrayItems = new ArrayList<FeaturesItem>();
		for(Agent item : items){
			FeaturesItem fi = new FeaturesItem(item.getPosition().get(0).getX(), item.getPosition().get(0).getY(), ((Item)item).getItemType());
			arrayItems.add(fi);
		}
		inputMap.setStart_items(arrayItems);
	
		if(this.getSnakes().isEmpty()){
			this.gameOver();
		}
	}

	@Override
	public void takeTurn() {
		if(!snakes.isEmpty()){
			moveAgent();

		}
	}



	@Override
	public void gameOver() {
		System.out.println("Game Over");
		this.setRunning(false);
		this.setPlay(false);
	}

	public ArrayList<Agent> getSnakes(){
		return snakes;
	}

	public ArrayList<Agent> getItems(){
		return items;
	}

	public InputMap geInputMap(){
		return inputMap;
	}

	public void setStrategy(GameStrategy strategy) {
		this.strategy = strategy;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
		try {
			this.inputMap=new InputMap(mapName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
