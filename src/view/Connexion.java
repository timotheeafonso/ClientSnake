package view;

import java.awt.*;
import javax.swing.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import controller.ControllerSnakeGame;
import model.StateGame;

import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class Connexion extends JFrame {
  
    private JLabel userLabel, passLabel, connexionFalse;
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private Socket so;
    //private ControllerSnakeGame c;
    private ViewSnakeGame vue;
    private PanelSnakeGame panel;
    String s="localhost";
    int p=2627;
    boolean estConnecte = false;
    boolean ispress = false;
    int keyCode;


    public Connexion() throws IOException{
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
        add(connexionFalse); 
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ispress=true;

        }});
        add(loginButton);


        while(!estConnecte){
                if(ispress){
                        String username = userField.getText();
                        char[] password = passField.getPassword();

                        if(checkConnexion(username, password)){
                            estConnecte=true;
                            //c= new ControllerSnakeGame();
                            dispose();
                            /* 
                            int tour=0;
                            int keyCode=0;
                            vue=new ViewSnakeGame();
                            while(tour<5){
                                    tour++;
                                    //keyCode=c.getKeyCode();
                                    
                                    try{
                                        System.out.println("Ecriture key");
                                        Socket so=new Socket(s, p);
                                        OutputStream outputStream = so.getOutputStream();
                                
                                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                                        String msg="keycode";
                                        //StateGame sg = new StateGame(keyCode,cs.inputMap.getStart_snakes(),cs.inputMap.getStart_items());
                                        
                                        //Gson gson = new Gson();
                                        //String json = gson.toJson(sg);
                                        objectOutputStream.writeObject(msg);
                                        objectOutputStream.writeObject(keyCode);

                                        //Socket so=new Socket(s, p);
                                        System.out.println("Lecture StateGame");
                                        InputStream inputStream = so.getInputStream();
                                        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                                        Object msg1 = objectInputStream.readObject();
                                        String msg1str = (String) msg1;
                                        Gson gson = new Gson();
                                        StateGame sg = gson.fromJson(msg1str, StateGame.class);   
                                        panel = new PanelSnakeGame(sg.sizeX, sg.sizeY, sg.walls,sg.snakes, sg.items); 
                                        System.out.println(sg.sizeX);
                                        vue.setPanel(panel); 
                                        
                                        */
                                        /* 
                                        JsonParser parser = new JsonParser();
                                        JsonObject userJson = parser.parse(json).getAsJsonObject();
                                        Gson gson = new Gson();
                                        User user = gson.fromJson(userJson, User.class);*/
                                        /* 
                                        System.out.println(sg.toString());  
                                        Thread.sleep(5000);
                                    } catch (Exception z) {
                                        z.printStackTrace();
                                    }
                                    //c.getViewSnakeGame().actualiser(c.getGame());*/
                            //}
                        }else{
                            JFrame jFrame = new JFrame();
                            JOptionPane.showMessageDialog(jFrame, "Saisie Incorrecte");
                        }
                }
                System.out.println(estConnecte);
                //ispress=false;
            }
            if(estConnecte){
                    int tour=0;
                    vue=new ViewSnakeGame();
                    
                    vue.getjFrame().addKeyListener (new KeyAdapter() {
                        public void keyPressed(KeyEvent e) {
                        keyCode = e.getKeyCode();
                    }});

                    while(tour<100){



                            tour++;
                            
                            try{
                                System.out.println("Ecriture key");
                                Socket so=new Socket(s, p);
                                OutputStream outputStream = so.getOutputStream();
                        
                                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                                String msg="keycode";
                                //StateGame sg = new StateGame(keyCode,cs.inputMap.getStart_snakes(),cs.inputMap.getStart_items());
                                
                                //Gson gson = new Gson();
                                //String json = gson.toJson(sg);
                                objectOutputStream.writeObject(msg);
                                objectOutputStream.writeObject(keyCode);

                                //Socket so=new Socket(s, p);
                                System.out.println("Lecture StateGame");
                                InputStream inputStream = so.getInputStream();
                                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                                Object msg1 = objectInputStream.readObject();
                                String msg1str = (String) msg1;
                                Gson gson = new Gson();
                                StateGame sg = gson.fromJson(msg1str, StateGame.class);   
                                panel = new PanelSnakeGame(sg.sizeX, sg.sizeY, sg.walls,sg.snakes, sg.items); 
                                System.out.println(sg.sizeX);
                                vue.setPanel(panel); 
                                

                                /* 
                                JsonParser parser = new JsonParser();
                                JsonObject userJson = parser.parse(json).getAsJsonObject();
                                Gson gson = new Gson();
                                User user = gson.fromJson(userJson, User.class);*/
                                System.out.println(sg.toString());  
                                Thread.sleep(5000);
                            } catch (Exception z) {
                                z.printStackTrace();
                            }
                        //c.getViewSnakeGame().actualiser(c.getGame());
                }
            }
        
    }

    boolean checkConnexion(String id, char[] passW){
        
       
        String pseudo= id;
        char[] mdp = passW;
        BufferedReader entree;
        String retour = "";
    
        try{// on connecte un socket
            System.out.println("Ecriture connexion");
            so=new Socket(s, p);
            OutputStream outputStream = so.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(pseudo);
            objectOutputStream.writeObject(mdp);
            System.out.println("Lecture connexion");
            entree = new BufferedReader(new InputStreamReader(so.getInputStream()));
            retour = entree.readLine();
            System.out.println(retour);
            entree.close();
            outputStream.close();
            so.close();

        } catch(UnknownHostException e) {System.out.println(e);}
        catch (IOException e) {System.out.println("Aucun serveur n’est rattaché au port ");}
         
        if(retour.equals("200")){
            return true;
        }else{
            return false;
        }
        
    }
  
}
