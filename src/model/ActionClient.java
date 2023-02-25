package model;

public class ActionClient {
    private int idClient;
    private int key; 
    private long time;
    private String fileName;
    private int strat;
    private boolean pause;
	private boolean restart;
	private boolean step;
	private boolean play;

    public ActionClient() {
    }


    public ActionClient(int idClient, int key, long time,String fileName, int strat, boolean play, boolean pause, boolean step,boolean restart) {
        this.idClient = idClient;
        this.key = key;
        this.time = time;
        this.fileName=fileName;
        this.strat=strat;     
        this.play=play;
        this.pause=pause;
        this.step=step;
        this.restart=restart;  
    }


    public int getIdClient() {
        return idClient;
    }


    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }


    public int getKey() {
        return key;
    }


    public void setKey(int key) {
        this.key = key;
    }


    public long getTime() {
        return time;
    }


    public void setTime(long time) {
        this.time = time;
    }


    public String getFileName() {
        return fileName;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public int getStrat() {
        return strat;
    }


    public void setStrat(int strat) {
        this.strat = strat;
    }


    public boolean isPause() {
        return pause;
    }


    public void setPause(boolean pause) {
        this.pause = pause;
    }


    public boolean isRestart() {
        return restart;
    }


    public void setRestart(boolean restart) {
        this.restart = restart;
    }


    public boolean isStep() {
        return step;
    }


    public void setStep(boolean step) {
        this.step = step;
    }


    public boolean isPlay() {
        return play;
    }


    public void setPlay(boolean play) {
        this.play = play;
    }
    

    
}
