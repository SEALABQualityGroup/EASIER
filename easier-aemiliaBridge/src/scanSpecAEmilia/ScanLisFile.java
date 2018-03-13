package scanSpecAEmilia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import personalScanner.MyScanner;

/**
 * Tale classe viene utilizzata per generare la specifica
 * AEmilia, ottenuta dopo la compilazione di uno *.aem.
 * Viene restituito un messaggio di errore o di warning,
 * nel caso in cui si riscontrano problemi
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */
public class ScanLisFile {

	/**
	 * Dato un oggetto File con estensione .lis il metodo
	 * restituisce un array di due stringhe. La prima stringa
	 * contiene la specifica AEmilia
	 * se non ci sono errori. Se ci sono errori, la prima
	 * stringae'vuota. La seconda stringa contiene un
	 * messaggio di presenza di warnings o errori. Se non ci
	 * sono errori o warnings, tale stringae'vuota.
	 *
	 * @param lis - oggetto File.
	 * @return un array di oggetti String di lunghezza due.
	 */
	public static String[] scanLisFile(File lis)
		{
		String[] ris = {"",""};
		try
			{
			Scanner s1 = new Scanner(lis);
			// seleziona il messaggio sul numero di errori
			String errori = s1.findInLine(">>>>\\s*\\d+\\s*error(s)?");
			String commento = s1.findInLine("%");
			while (errori == null || commento != null)
				{
				@SuppressWarnings("unused")
				String string = s1.nextLine();
				commento = s1.findInLine("%");
				errori = s1.findInLine(">>>>\\s*\\d+\\s*error(s)?");
				}
			// seleziona il messaggio sul numero di warning
			String warnings =
				s1.findInLine(">>>>\\s*\\d+\\s*warning(s)?");
			while (warnings == null)
				{
				s1.nextLine();
				warnings =
					s1.findInLine(">>>>\\s*\\d+\\s*warning(s)?");
				}
			s1 = new Scanner(errori);
			s1.useDelimiter("\\s*error(s)?");
			s1.skip("\\s*>>>>\\s*");
			int errs = s1.nextInt();
			s1 = new Scanner(warnings);
			s1.useDelimiter("\\s*warning(s)?");
			s1.skip("\\s*>>>>\\s*");
			int wars = s1.nextInt();
			// se il numero di errorie'maggiore di
			// 0 si restituisce un messaggio di errore
			if (errs > 0)
				{
				ris[1] = "Il file "+lis.getAbsolutePath()
					+" contains error. ";
				return ris;
				}
			// se il numero di warnigse'maggiore di
			// 0 si restituisce un messaggio di warning
			if (wars > 0) ris[1] = "The file "
				+lis.getAbsolutePath()
				+" contains "
				+"warnings. ";
			// si eliminano: i numeri di linea, i
			// messaggi di errore e di warnings
			s1 = new Scanner(lis);
			while (s1.hasNextLine())
				{
				String linea = s1.nextLine();
				Scanner s2 = new Scanner(linea);
				if (s2.hasNext("\\(\\d*\\)"))
					{
					s2.skip("\\(\\d*\\)");
					s2.useDelimiter("\\z");
					if (s2.hasNext())
						ris[0] = ris[0] + s2.next() + "\n";
					}
				}
			return ris;
			}
		catch (FileNotFoundException e)
			{
			ris[0] = new String();
			ris[1] = "The file "+lis.getAbsolutePath()+
			" do not exist.";
			return ris;
			}
		catch (NoSuchElementException e)
			{
			ris[0] = new String();
			ris[1] = "The file "+lis.getAbsolutePath()+
			" is not a valid .lis";
			return ris;
			}
		}

	/**
	 * Genera una stringa da una specifica AEmilia senza
	 * commenti e caratteri di fine linea.
	 *
	 * @param lisCommentato - oggetto String.
	 * @return un oggetto String.
	 */
	public static String generaStringa(String lisCommentato)
		{
		String ris = new String();
		MyScanner s1 = new MyScanner(lisCommentato);
		s1.useDelimiter("\\n");
		// si eliminano i commenti e i caratteri di fine
		// linea di ogni riga di codice
		while (s1.hasNext())
			{
			String prossimo = s1.next();
			Scanner s2 = new Scanner(prossimo);
			s2.useDelimiter("%");
			ris = ris + s2.next();
			}
		return ris;
		}
}
