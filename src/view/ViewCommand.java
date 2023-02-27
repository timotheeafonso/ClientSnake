package view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import controller.ControllerSnakeGame;
import patternEtat.EtatFin;
import patternEtat.EtatPause;
import patternEtat.EtatStart;
import patternEtat.EtatStop;
import utils.ColorSnake;
import utils.FeaturesSnake;

public class ViewCommand{
	
	private JFrame jFrame;
	private JSlider slider;
	private JLabel labelTurn;
	private JButton restartButton;
	private JButton pauseButton;
	private JButton stepButton;
	private JButton playButton;
	private ControllerSnakeGame controller;
	private JLabel labelSnake1;
	private JLabel labelSnake2;
	private JLabel labelVieSnake1;
	private JLabel labelVieSnake2;
	
	public ViewCommand(ControllerSnakeGame controller) {
		
		this.controller=controller;
		jFrame = new JFrame();
		jFrame.setTitle("Commande");
		jFrame.setSize(new Dimension(1000, 500));
		Dimension windowSize = jFrame.getSize();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();
		int dx = centerPoint.x - windowSize.width / 2 ;
		int dy = centerPoint.y - windowSize.height / 2 +700;
		jFrame.setLocation(dx, dy);
		
		JPanel command = new JPanel(new GridLayout(2,1));
		JPanel snakes = new JPanel(new GridLayout(1,2));

		JPanel snake1 = new JPanel(new GridLayout(3,1));
		JPanel snake2 = new JPanel(new GridLayout(3,1));
		labelSnake1 = new JLabel("",JLabel.CENTER);		
		labelSnake2 = new JLabel("",JLabel.CENTER);
		labelVieSnake1 = new JLabel("",JLabel.CENTER);
		labelVieSnake2 = new JLabel("",JLabel.CENTER);
		snake1.add(labelSnake1);
		snake1.add(labelVieSnake1);
		snake2.add(labelSnake2);
		snake2.add(labelVieSnake2);
		snakes.add(snake1);
		snakes.add(snake2);


		JPanel main = new JPanel(new GridLayout(2,1));
		JPanel top = new JPanel(new GridLayout(1,4));
		JPanel bottom = new JPanel(new GridLayout(1,3));
		JPanel radioGroup = new JPanel(new GridLayout(2,1));

		slider = new JSlider(1, 10); 
        slider.setPaintTicks(true); 
        slider.setPaintLabels(true); 
        slider.setMajorTickSpacing(1);
        
		labelTurn = new JLabel("Turn: ",JLabel.CENTER);
		JLabel labelSlider = new JLabel("Number of turns per seconde: ",JLabel.CENTER);

		Icon restartIcon = new ImageIcon("icons/icon_restart.png");
		restartButton = new JButton(restartIcon);
		
		Icon pauseIcon = new ImageIcon("icons/icon_pause.png");
		pauseButton = new JButton(pauseIcon);
		
		Icon stepIcon = new ImageIcon("icons/icon_step.png");
		stepButton = new JButton(stepIcon);
		
		Icon playIcon = new ImageIcon("icons/icon_play.png");
		playButton = new JButton(playIcon);
		
		JRadioButton r1=new JRadioButton("Random Strategy");    
		JRadioButton r2=new JRadioButton("User Strategy");    
		ButtonGroup bg=new ButtonGroup(); 
		
		JButton button2 = new JButton("Open Map");
		


		bg.add(r1);bg.add(r2); 
		radioGroup.add(r1);   
		radioGroup.add(r2);
		r2.setSelected(true);
		top.add(restartButton);
		top.add(playButton);
		top.add(stepButton);
		top.add(pauseButton);
		bottom.add(slider,labelSlider);
		bottom.add(labelTurn);
		bottom.add(radioGroup);
		bottom.add(button2);
		main.add(top);
		main.add(bottom);
		command.add(snakes);
		command.add(main);
		jFrame.add(command);

		jFrame.setVisible(true);
		
		changeEtat();

		button2.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
				JFileChooser j = new JFileChooser(new File("./layouts"));
				int r = j.showOpenDialog(null);
				if (r == JFileChooser.APPROVE_OPTION){
					String fileName=j.getSelectedFile().getAbsolutePath();
					controller.setFileName(fileName);
					try {
						controller.pause();
	        			controller.changeEtat(new EtatPause());
	        			changeEtat();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
	        }
	    });

		pauseButton.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        		controller.pause();
	        		controller.changeEtat(new EtatPause());
	        		changeEtat();

	        }
	    });
		
		playButton.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        		controller.play();
	        		controller.changeEtat(new EtatStart());
	        		changeEtat();
	        }
	    });
		
		restartButton.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        		controller.restart();
	        		controller.changeEtat(new EtatStop());
	        		changeEtat();
	        }
	    });
		
		stepButton.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        		controller.step();
	        		controller.changeEtat(new EtatPause());
	        		changeEtat();
	        }
	    });
		
		slider.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent e) {
	        	double speed=((JSlider)e.getSource()).getValue();
	            controller.setSpeed(speed);
	         }
	      });

		ActionListener sliceActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
			  if(r1.isSelected()){
					controller.setStrategy(1);
			  }else{
					controller.setStrategy(0);
				
			  }
			}
		  };

		  r1.addActionListener(sliceActionListener);
		  r2.addActionListener(sliceActionListener);
				
	}

 
	public void actualiser(ArrayList<FeaturesSnake> snakes) {
			labelTurn.setText("Turn: "+controller.getTurn()+" / "+controller.getMaxTurn());
			
			boolean red=false;
			boolean green = false;
			for(FeaturesSnake a : snakes){
				if(a.getColorSnake()==ColorSnake.Green){
					green=true;
					labelSnake1.setText("Snake 1");
					labelVieSnake1.setText("Vie: "+Integer.toString(a.getPositions().size()));
				}else{
					red=true;
					labelSnake2	.setText("Snake 2");
					labelVieSnake2.setText("Vie: "+Integer.toString(a.getPositions().size()));
				}
			}

			if(!red){
				labelSnake2.setText("");
				labelVieSnake2.setText("");
			}

			if(!green){
				labelSnake1.setText("");
				labelVieSnake1.setText("");
			}
			
			if(snakes.isEmpty()){
				controller.changeEtat(new EtatFin());
				changeEtat();
			}
		
	}
	
	public void changeEtat() {
		pauseButton.setEnabled(controller.getEtat().onPause());
		playButton.setEnabled(controller.getEtat().onStart());
		stepButton.setEnabled(controller.getEtat().onStep());
		restartButton.setEnabled(controller.getEtat().onRestart());
	}


	public JFrame getjFrame() {
		return jFrame;
	}
	
}
