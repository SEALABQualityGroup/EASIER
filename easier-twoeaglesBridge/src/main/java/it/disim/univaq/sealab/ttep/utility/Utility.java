package it.disim.univaq.sealab.ttep.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import utilities.MetodiVari;

public class Utility {
	/**
	 * Lancia il programma desiderato (TTKernel) passando
	 * l'opzione opportuna e i path dei file in input e in output.
	 * 
	 * @param description Messaggio mostrato nella finestra
	 * @param twoTowersKernelPath Path del kernel di twotowers
	 * @param twoTowersOption Parametro di twotowers
	 * @param aemFilePath Path del file aem
	 * @param rewFilePath del file punto rew
	 * @param destinationFilePath Path dei file di output
	 */
	public static void execute_ase(final String twoTowersKernelPath, final String twoTowersOption, final String aemFilePath, final String rewFilePath, final String destinationFilePath){
		//ASE qui invoca TwoTowers passandogli i parametriper eseguire la GuassianEliminationSRBMC
		try {
			Process process = new ProcessBuilder(twoTowersKernelPath, twoTowersOption, aemFilePath, rewFilePath, destinationFilePath+".val").start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
