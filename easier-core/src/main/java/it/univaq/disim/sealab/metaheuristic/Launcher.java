package it.univaq.disim.sealab.metaheuristic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import it.univaq.disim.sealab.metaheuristic.evolutionary.Controller;

public class Launcher {

	public static void main(String[] args) {

		Properties prop = new Properties();
		try {
			prop.load(getConfigFile(args[0]));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Enumeration<String> enums = (Enumeration<String>) prop.propertyNames();
	    while (enums.hasMoreElements()) {
//	      String key = enums.nextElement();
	      String cfgFile = prop.getProperty(enums.nextElement());
	      Controller controller = new Controller(cfgFile);
			try {
				controller.run();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	private static FileInputStream getConfigFile(String filename) throws FileNotFoundException {
		try {
			filename = new java.io.File(".").getCanonicalPath() + filename;
		} catch (IOException e) {
			e.printStackTrace();
		}
//		logger_.info("Config_file is set to " + filename);
		return new FileInputStream(filename);
	}
}
