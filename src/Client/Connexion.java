package Client;

import java.awt.*;
import javax.swing.*;
import com.google.gson.Gson;
import controller.ControllerSnakeGame;
import model.ActionClient;
import model.StateGame;
import utils.ColorSnake;
import view.PanelSnakeGame;
import view.ViewSnakeGame;
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
    private ViewSnakeGame vue;
    private PanelSnakeGame panel;
    String s="localhost";
    int p=2627;
    boolean estConnecte = false;
    boolean ispress = false;
    int keyCode;
    int idClient;
    long time;
    String fileName;
    int strat;
    boolean fin;
    int score;

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

        score=0;
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
                int conn = checkConnexion(username, password);
                if(conn==200){
                    estConnecte=true;
                    dispose();
                }else{
                    JFrame jFrame = new JFrame();
                    if(conn==401){
                        JOptionPane.showMessageDialog(jFrame, "Identifiant ou mot de passe incorrecte");
                        ispress=false;
                    }
                    if(conn==300){
                        JOptionPane.showMessageDialog(jFrame, "Utilisateur déja connecté");
                        ispress=false;
                    }
                }
                    
            }
            try {
                Thread.sleep(25);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        if(estConnecte){
            ControllerSnakeGame controllerSnakeGame = new ControllerSnakeGame();
            vue = controllerSnakeGame.getViewSnakeGame();
            String oldsg = "";
            Gson gson = new Gson();
            String oldFileName=fileName;
            fin = controllerSnakeGame.isQuit();

            

            while(!fin){
                    
                try{
                    Socket so=new Socket(s, p);
                    OutputStream outputStream = so.getOutputStream();    
                    String msg="keycode";
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                    objectOutputStream.writeObject(msg);
                    keyCode=controllerSnakeGame.getKeyCode();
                    time=controllerSnakeGame.getTime();
                    fileName=controllerSnakeGame.getFileName();
                    strat=controllerSnakeGame.getStrat();
            
                    ActionClient ac;
                    if(fileName!=oldFileName){
                            ac = new ActionClient(this.idClient,keyCode,time,fileName,strat,controllerSnakeGame.isPlay(),controllerSnakeGame.isPause(),controllerSnakeGame.isStep(),controllerSnakeGame.isRestart(),score);
                            oldFileName=fileName;
                            controllerSnakeGame.resetButton();

                    }else{
                            ac = new ActionClient(this.idClient,keyCode,time,null,strat,controllerSnakeGame.isPlay(),controllerSnakeGame.isPause(),controllerSnakeGame.isStep(),controllerSnakeGame.isRestart(),score);
                            controllerSnakeGame.resetButton();
                    }
                    String json = gson.toJson(ac);
                    objectOutputStream.writeObject(json);
                    InputStream inputStream = so.getInputStream();

                    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                    Object msg1 = objectInputStream.readObject();
                    String msg1str = (String) msg1;
                    if(!msg1str.equals(oldsg)){
                        StateGame sg = gson.fromJson(msg1str, StateGame.class); 
                        panel = new PanelSnakeGame(sg.sizeX, sg.sizeY, sg.walls,sg.snakes, sg.items); 
                        vue.setPanel(panel); 
                        controllerSnakeGame.setTurn(sg.turn);
                        controllerSnakeGame.setMaxTurn(sg.maxTurn);
                        controllerSnakeGame.getVc().actualiser(sg.snakes);
                        oldsg=msg1str;
                        if(sg.snakes.size()>0){
                            if(sg.snakes.get(0).getColorSnake().equals(ColorSnake.Green)){
                                if(sg.snakes.get(0).getPositions().size()>score)
                                    score=sg.snakes.get(0).getPositions().size();
                            }
                        }
                    }
                    fin = controllerSnakeGame.isQuit();
                    if(fin){
                        inputStream.close();
                        outputStream.close();
                        so.close();
                        System.exit(0);
                    }
                    

                } catch (Exception z) {
                    z.printStackTrace();
                }
                

            }
            
        }
        
    }

    int checkConnexion(String id, char[] passW){
        
       
        String pseudo= id;
        char[] mdp = passW;
        BufferedReader entree;
        String retour = "";
    
        try{// on connecte un socket
            so=new Socket(s, p);
            OutputStream outputStream = so.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(pseudo);
            PrintWriter sortie;
            sortie = new PrintWriter(so.getOutputStream(), true);
            sortie.println(mdp);
            //objectOutputStream.writeObject(mdp);
            entree = new BufferedReader(new InputStreamReader(so.getInputStream()));
            retour = entree.readLine();
            if(retour.equals("200")){
                this.idClient = Integer.parseInt(entree.readLine());
            }
            entree.close();
            sortie.close();
            outputStream.close();
            so.close();

        } catch(UnknownHostException e) {System.out.println(e);}
        catch (IOException e) {System.out.println("Aucun serveur n’est rattaché au port ");}
         System.out.println(retour);
        return Integer.parseInt(retour);
        
    }
  
}
