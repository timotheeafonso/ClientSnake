package view;
import java.awt.*;

import javax.swing.*;
import model.SimpleGame;
import model.SnakeGame;
import patternObservateur.Observable;
import patternObservateur.Observateur;

public class ViewSnakeGame implements Observateur{
	private JFrame jFrame;
	private PanelSnakeGame panel;
	
	public JFrame getjFrame() {
		return jFrame;
	}

	public PanelSnakeGame getPanel() {
		return panel;
	}

	public ViewSnakeGame(PanelSnakeGame panel) {
		jFrame = new JFrame();
		this.panel=panel;
		jFrame.setTitle("Game");

		int panelX=panel.getSizeX();
		int panelY=panel.getSizeY();
		int raport=1;
		int tailleX=panelX*500/panelX;
		int tailleY=panelY*500/panelY;
		if(panelX>panelY){
			raport=panelX/panelY;
			tailleX=panelX*500*raport/panelX;
		}else if(panelX<panelY){
			raport=panelY/panelX;
			tailleY=panelY*500*raport/panelY;
		}
		

		jFrame.setSize(new Dimension(tailleX,tailleY));
		Dimension windowSize = jFrame.getSize();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();
		int dx = centerPoint.x - windowSize.width / 2 ;
		int dy = centerPoint.y - windowSize.height / 2 - 350;
		jFrame.setLocation(dx, dy);		
		jFrame.setVisible(true);
		jFrame.add(this.panel);
	}

	@Override
	public void actualiser(Observable o) {
		if(o instanceof SimpleGame) {
			SnakeGame snakeGame = (SnakeGame) o;
			panel.updateInfoGame(snakeGame.geInputMap().getStart_snakes(),snakeGame.geInputMap().getStart_items());
			panel.repaint();
			
		}
	}

	public void setPanel(PanelSnakeGame panel2) {
		jFrame.remove(panel);
		this.panel=panel2;
		jFrame.add(panel);
		jFrame.setVisible(true);
		int panelX=panel.getSizeX();
		int panelY=panel.getSizeY();
		int raport=1;
		int tailleX=panelX*500/panelX;
		int tailleY=panelY*500/panelY;
		if(panelX>panelY){
			raport=panelX/panelY;
			tailleX=panelX*500*raport/panelX;
		}else if(panelX<panelY){
			raport=panelY/panelX;
			tailleY=panelY*500*raport/panelY;
		}
		

		jFrame.setSize(new Dimension(tailleX,tailleY));
		panel.repaint();
	}

	
	
}
