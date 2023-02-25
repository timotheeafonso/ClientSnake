package view;
import java.awt.*;
import javax.swing.*;


public class ViewSimpleGame{
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

	
	
}
