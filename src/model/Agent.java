package model;

import java.io.Serializable;
import java.util.ArrayList;

import utils.Position;

public abstract class Agent implements Serializable {
    protected ArrayList<Position> position;

    public ArrayList<Position> getPosition() {
        return position;
    }

    public void setPosition(ArrayList<Position> position) {
        this.position = position;
    } 


    public void addUnePosition(Position position) {
        this.position.add(position);
    } 
    
}
