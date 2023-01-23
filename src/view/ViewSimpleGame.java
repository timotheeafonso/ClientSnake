package view;
import java.awt.*;
import javax.swing.*;

import model.SimpleGame;
import patternObservateur.Observable;
import patternObservateur.Observateur;


public class ViewSimpleGame implements Observateur{
	protected JFrame jFrame;
	protected JLabel label;
	
	public ViewSimpleGame() {
		jFrame = new JFrame();
		
		jFrame.setTitle("Game");
		jFrame.setSize(new Dimension(1000, 600));
		Dimension windowSize = jFrame.getSize();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();
		int dx = centerPoint.x - windowSize.width / 2 ;
		int dy = centerPoint.y - windowSize.height / 2 - 350;
		jFrame.setLocation(dx, dy);
		
		jFrame.setVisible(true);
		label= new JLabel("",JLabel.CENTER);
		jFrame.add(label);
	}

	@Override
	public void actualiser(Observable o) {
		if(o instanceof SimpleGame) {
			SimpleGame simpleGame = (SimpleGame) o;
			label.setText(Integer.toString(simpleGame.getTurn()));
		}
	}
	
}
