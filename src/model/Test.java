package model;

import java.io.IOException;

import controller.ControllerSnakeGame;
import view.Connexion;

public class Test{

	public static void main(String[] args) {
		try {
			new Connexion();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
