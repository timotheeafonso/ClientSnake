package model;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

import java.util.ArrayList;


import utils.AgentAction;
import utils.ColorSnake;
import utils.FeaturesItem;
import utils.FeaturesSnake;
import utils.ItemType;
import utils.Position;



public class InputMap implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private String filename;
	private int size_x;
	private int size_y;
	
	private boolean walls[][];


	private ArrayList<FeaturesSnake> start_snakes ;
	private ArrayList<FeaturesItem> start_items ;

	
	private BufferedReader buffer;
	
	ColorSnake[] colorSnake = {ColorSnake.Green,ColorSnake.Red};
	

	public InputMap(String filename) throws Exception{
		
		this.filename = filename;
		
		try{
		
		InputStream flux =new FileInputStream(filename); 
		InputStreamReader lecture =new InputStreamReader(flux);
		buffer = new BufferedReader(lecture);
		
		String ligne;

		int nbX=0;
		int nbY=0;

		while ((ligne = buffer.readLine())!=null)
		{
			ligne = ligne.trim();
			if (nbX==0) {nbX = ligne.length();}
			else if (nbX != ligne.length()) throw new Exception("Toutes les lignes doivent avoir la même longueur");
			nbY++;
		}			
		buffer.close(); 
			
		size_x = nbX;
		size_y = nbY;
		
		walls = new boolean [size_x][size_y];
	
		flux = new FileInputStream(filename); 
		lecture = new InputStreamReader(flux);
		buffer = new BufferedReader(lecture);
		int y=0;
	
		
		start_snakes = new ArrayList<FeaturesSnake>();
		start_items = new ArrayList<FeaturesItem>();
				
		int id = 0;
		
		while ((ligne=buffer.readLine())!=null)
		{
			ligne=ligne.trim();

			for(int x=0;x<ligne.length();x++)
			{
				
				if (ligne.charAt(x)=='%') 
					walls[x][y]=true; 
					
				else walls[x][y]=false;
				

		
				if (ligne.charAt(x)=='S' ) {
					
					ArrayList<Position> pos = new ArrayList<Position>();
					pos.add(new Position(x,y));
					
					
					start_snakes.add(new FeaturesSnake(pos, AgentAction.MOVE_DOWN,colorSnake[id%colorSnake.length], false, false));	
					id++;
				}
				
				if (ligne.charAt(x)=='A') {
				
					
					start_items.add(new FeaturesItem(x, y, ItemType.APPLE));
					
					
				}
				
				if (ligne.charAt(x)=='B') {
				
					
					start_items.add(new FeaturesItem(x, y, ItemType.BOX));
					
					
				}

				if (ligne.charAt(x)=='Y') {
				
					
					start_items.add(new FeaturesItem(x, y, ItemType.SICK_BALL));
					
					
				}
				
				if (ligne.charAt(x)=='M') {
				
					
					start_items.add(new FeaturesItem(x, y, ItemType.INVINCIBILITY_BALL));
					
					
				}
				
			}
			y++;
		}	
		
		buffer.close(); 
		

		}catch (Exception e){
			System.out.println("Erreur : "+e.getMessage());
		}

		
	}
	

	
	public int getSizeX() {return(size_x);}
	public int getSizeY() {return(size_y);}
	


	
	public String getFilename(){
		return filename;
	}

	public boolean[][] get_walls() {
		return walls;
	}
	
	
	public ArrayList<FeaturesSnake> getStart_snakes() {
		return start_snakes;
	}

	public ArrayList<FeaturesItem> getStart_items() {
		return start_items;
	}

	public  void setStart_items(ArrayList<FeaturesItem> fi) {
		this.start_items=fi;
	}


	
}
