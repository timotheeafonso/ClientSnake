package Client;

import java.io.IOException;

public class InstanceClient{

	public static void main(String[] args) {
		try {
			new Connexion();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
