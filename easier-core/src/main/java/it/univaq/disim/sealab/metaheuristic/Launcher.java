package it.univaq.disim.sealab.metaheuristic;

import java.io.IOException;

import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;

public class Launcher {

	public static void main(String[] args) {
		Controller controller = new Controller(args);
		
		try {
			controller.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
