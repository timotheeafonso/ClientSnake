package view;

import java.awt.*;
import javax.swing.*;

import controller.ControllerSnakeGame;

import java.awt.event.*;


public class Connexion extends JFrame {
  
    private JLabel userLabel, passLabel, connexionFalse;
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;

    public Connexion(){
    
        setTitle("Connexion Snake Game");
        setSize(1000, 100);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
       
        Dimension windowSize = getSize();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();
		int dx = centerPoint.x - windowSize.width  /2;
		int dy = centerPoint.y - windowSize.height ;
		setLocation(dx, dy);	

        connexionFalse = new JLabel("");
        userLabel = new JLabel("Nom d'utilisateur :");
        passLabel = new JLabel("Mot de passe :");
        userField = new JTextField(15);
        passField = new JPasswordField(15);
        loginButton = new JButton("Se connecter");
    
        add(userLabel);
        add(userField);
        add(passLabel);
        add(passField);
        add(loginButton);
        add(connexionFalse); 
    
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                char[] password = passField.getPassword();

                if(checkConnexion(username, password)){
                    ControllerSnakeGame c=new ControllerSnakeGame();
                    dispose();
                }else{
                    JFrame jFrame = new JFrame();
                    JOptionPane.showMessageDialog(jFrame, "Saisie Incorrecte");
                }
            }
        });
    
        setVisible(true);
    }

    boolean checkConnexion(String pseudo, char[] passW){
        //TODO connexion serveur
        return true; 
    }
  
}
