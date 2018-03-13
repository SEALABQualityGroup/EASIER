package scanSpecAEmilia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ScanNoExtFile 
	{
	
	public static int errors;
	public static int warnings;
	
	/**
	 * Dato un oggetto File senza estensione il metodo
	 * imposta errors e warnings secondo tale file.
	 * Restituisce true se e solo se il numero degli errori e dei warningse'zero.
	 * 
	 * @param NoExt - oggetto File.
	 * @return un valore booleano.
	 */
	public static boolean scanNoExtFile(File lis)
		throws FileNotFoundException
		{
		try {
			Scanner s1 = new Scanner(lis);
			s1.useDelimiter("\\s*error(s)?");
			int errs = s1.nextInt();
			s1.useDelimiter("\\s*warning(s)?");
			s1.skip("\\s*error(s)?,\\s*");
			int wars = s1.nextInt();
			errors = errs;
			warnings = wars;
			// se il numero di errori o di warningse'maggiore di
			// 0 si restituisce false
			if (errs > 0 || wars > 0)
				return false;
			return true;
			}
		catch (InputMismatchException inputMismatchException)
			{
			return false;
			}
		}
	
	/**
	 * Genera una stringa da una specifica AEmilia senza
	 * commenti e caratteri di fine linea.
	 *
	 * @param aem - oggetto File.
	 * @return un oggetto String.
	 */
	public static String generaStringa(File aem)
		throws FileNotFoundException
		{
		String ris = new String();
		Scanner s1 = new Scanner(aem);
		s1.useDelimiter("\\z");
		// si eliminano i commenti e i caratteri di fine
		// linea di ogni riga di codice
		while (s1.hasNextLine())
			{
			// si preleva una linea e si scannerizza
			String string = s1.nextLine();
			Scanner scanner = new Scanner(string);
			scanner.useDelimiter("\\z");
			// la linea non deve iniziare con un commento
			// altrimenti viene considerato il prossimo next invece
			// di considerare la stringa vuota
			if (!scanner.hasNext("%(.)*"))
				{
				scanner.useDelimiter("%");
				// una linea puo' essre vuota
				if (scanner.hasNext())
					{
					// si aggiunge la stringa prima dei commenti o
					// caratteri di fine linea
					String string2 = scanner.next();
					ris = ris + string2;
					}
				}
			}
		return ris;
		}	
	}
