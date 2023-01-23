package view;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import javax.swing.JPanel;

import utils.*;


/** 
 * Classe qui permet de charger d'afficher le panneau du jeu à partir d'une carte et de listes d'agents avec leurs positions.
 * 
 */


public class PanelSnakeGame extends JPanel{

	private static final long serialVersionUID = 1L;

	
	protected Color ground_Color= new Color(0,0,0);


	private int sizeX;
	private int sizeY;

	private int fen_x;
	private int fen_y;
	
	private double stepx;
	private double stepy;
	
	
	float[] contraste = { 0, 0, 0, 1.0f };


	protected ArrayList<FeaturesSnake> featuresSnakes = new ArrayList<FeaturesSnake>();	
	protected ArrayList<FeaturesItem> featuresItems = new ArrayList<FeaturesItem>();

	
	private boolean[][] walls;
	
	
	int cpt;

	public PanelSnakeGame(int sizeX, int sizeY, boolean[][] walls, ArrayList<FeaturesSnake> featuresSnakes, ArrayList<FeaturesItem> featuresItems) {

		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.walls = walls;	
		this.featuresSnakes = featuresSnakes;
		this.featuresItems = featuresItems;
				
	}

	public void paint(Graphics g){

		fen_x = getSize().width;
		fen_y = getSize().height;

		this.stepx = fen_x/(double)sizeX;
		this.stepy = fen_y/(double)sizeY;
		
		g.setColor(ground_Color);
		g.fillRect(0, 0,fen_x,fen_y);

		double position_x=0;

		for(int x=0; x<sizeX; x++)
		{
			double position_y = 0 ;
			
			for(int y=0; y<sizeY; y++)
			{
				if (walls[x][y]){

					try {
						Image img = ImageIO.read(new File("./images/wall.png"));
						g.drawImage(img, (int)position_x, (int)position_y, (int)stepx, (int)stepy, this);
						
					} catch (IOException e) {
						e.printStackTrace();
						
					}
				}

				position_y+=stepy;				
			}
			position_x+=stepx;
		}

		for(int i = 0; i < featuresSnakes.size(); i++){
			paint_Snake(g,featuresSnakes.get(i));	
		}

		for(int i = 0; i < featuresItems.size(); i++){
			paint_Item(g,featuresItems.get(i));	
		}
			
		cpt++;
	}


	void paint_Snake(Graphics g, FeaturesSnake featuresSnake)
	{

		ArrayList<Position> positions = featuresSnake.getPositions();
		
		AgentAction lastAction = featuresSnake.getLastAction();

		
		BufferedImage img = null;
		
		
		

		double pos_x;
		double pos_y;
		
		int cpt_img = -1;
		

		
		for(int i = 0; i < positions.size(); i++) {
			
			pos_x=positions.get(i).getX()*stepx;
			pos_y=positions.get(i).getY()*stepy;


			if(i == 0) {
				switch (lastAction) {
				case MOVE_UP:
					cpt_img = 0;
					break;
				case MOVE_DOWN:
					cpt_img = 1;	
					break;
				case MOVE_RIGHT:
					cpt_img = 2;
					break;		
				case MOVE_LEFT:
					cpt_img = 3;
					break;

				default:
					break;
				}
				
			} else {	
				cpt_img = 4;
			}
			
			try {
				if(featuresSnake.getColorSnake() == ColorSnake.Green) {
					img = ImageIO.read(new File("./images/snake_green_" + cpt_img + ".png"));
				} else if(featuresSnake.getColorSnake() == ColorSnake.Red ) {
					img = ImageIO.read(new File("./images/snake_red_" + cpt_img + ".png"));	
				}
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			float [] scales = new float[]{1 ,1, 1, 1.0f };
			
			if (featuresSnake.isInvincible())
		
				scales = new float[]{3 ,0.75f, 3, 1.0f };
	
			if (featuresSnake.isSick())
				scales = new float[]{1.5f ,1.5f, 0.75f, 1.0f };
			
			
			RescaleOp op = new RescaleOp(scales, contraste, null);
			img = op.filter( img, null);
			
			
			if(img != null) {
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			}
			
			
			
		}
			
		
	}

	

	void paint_Item(Graphics g, FeaturesItem featuresItem){



		int x = featuresItem.getX();
		int y = featuresItem.getY();

		double pos_x=x*stepx;
		double pos_y=y*stepy;

		if (featuresItem.getItemType() == ItemType.APPLE) {
			try {
				Image img = ImageIO.read(new File("./images/apple.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (featuresItem.getItemType() == ItemType.BOX) {
			try {
				Image img = ImageIO.read(new File("./images/mysteryBox.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		if (featuresItem.getItemType() == ItemType.SICK_BALL) {
			try {
				Image img = ImageIO.read(new File("./images/sickBall.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (featuresItem.getItemType() == ItemType.INVINCIBILITY_BALL) {
			try {
				Image img = ImageIO.read(new File("./images/invicibleBall.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}



	public void updateInfoGame( ArrayList<FeaturesSnake> featuresSnakes , ArrayList<FeaturesItem> featuresItems) {
		
		this.featuresSnakes = featuresSnakes;
		this.featuresItems = featuresItems;
	
	}


	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}





}
