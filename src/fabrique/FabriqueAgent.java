package fabrique;

import java.util.ArrayList;

import model.Agent;
import model.InputMap;

public abstract class FabriqueAgent{
    public abstract  ArrayList<Agent> createAgent(InputMap inputMap);
}