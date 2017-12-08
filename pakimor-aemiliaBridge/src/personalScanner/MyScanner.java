package personalScanner;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Ha metodi equivalenti alla classe java.util.Scanner, che 
 * possono essere utilizzati, a differenza della classe Scanner, 
 * per la scannerizzazzione di stringhe senza alcun limite di 
 * buffer.
 * 
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class MyScanner {

	/**
	 * Delimitatore per la scannerizzazione.
	 */
	private Pattern DelimitatoreAttuale;
	
	/**
	 * Stringa contenente il precedente match incontrato.
	 */
	private String PrecNext;
	
	/**
	 * Un motore che esegue operazioni di match su una 
	 * sequenza di caratteri.
	 */
	private Matcher m;
	
	/**
	 * Crea un oggetto MyScanner con input da scannerizzare
	 * fornito come input.
	 * @param input - oggetto String.
	 */
	public MyScanner(String input) 
		{
		// imposta il match precedente alla stringa vuoto
		this.PrecNext = new String();
		// assegna il delimitatore di default (spazio bianco) allo 
		// scanner
		this.DelimitatoreAttuale = Pattern.compile("\\p{javaWhitespace}+");
		// crea il matcher per la scannerizzazione dell'input
		// a seconda dei delimitatori presenti
		this.m = this.DelimitatoreAttuale.matcher(input);
		}
	
	/**
	 * Imposta il pattern di delimitazione di questo MyScanner
	 * ad un pattern costruito dall'oggetto String specificato.
	 * @param delimitatoreAttuale - oggetto String.
	 * @throws PatternSyntaxException - eccezione sollevata per
	 * indicare un errore di sintassi in un pattern (espressione
	 * regolare).
	 */
	public void useDelimiter(String delimitatoreAttuale) 
	throws PatternSyntaxException
		{
		// assegna un delimitatore ad un oggetto MyScanner
		this.DelimitatoreAttuale = Pattern.compile(delimitatoreAttuale);
		// assegna un nuovo delimitatore al matcher
		this.m.usePattern(Pattern.compile(delimitatoreAttuale));
		}
	
	/**
	 * Trova e restituisce il prossimo token completo da questo
	 * MyScanner. Un token completoe'preceduto e seguito da 
	 * input che fa match con il pattern delimitatore.
	 * @return un oggetto String.
	 * @throws - NoSuchElementException se non ci sono altri token
	 * da scansionare.
	 */
	public String next() throws NoSuchElementException
		{
		// buffer che contiene il prossimo token
		StringBuffer ris = new StringBuffer("");
		// buffer che contiene il resto dell'input
		StringBuffer sb = new StringBuffer("");
		// stringhe che contiene il prossimo token
		String st = new String();
		// stringa che contiene l'input da resettare
		String res = new String();
		// si controlla se c'� un delimitatore nell'input
		// osservare che il finde'contestale
		if (this.m.find())
			{
			// si assegna il prossimo token a st
			this.m.appendReplacement(ris, "");
			st = new String(ris);
			// si assegna il delimitatore a res
			res = this.m.group();
			if (st.equals("")) 
				{
				// se il tokene'la stringa vuota si controlla 
				// se c'� un'altro delimitatore
				if (this.m.find())
					{
					this.m.appendReplacement(ris, "");
					st = new String(ris);
					res = this.m.group();
					// si mette in sb il resto della stringa 
					this.m.appendTail(sb);
					}
				// se non c'� un altro delimitatore si considera
				// il resto dell'input come token
				else 			
					{
					this.m.appendTail(ris);
					st = new String(ris);
					if (st.equals(""))
						throw new NoSuchElementException();
					}
				}
			else this.m.appendTail(sb);
			}
		// restituire la coda in caso di non find del delimitatore
		else
			{
			this.m.appendTail(ris);
			st = new String(ris);
			if (st.equals(""))
				throw new NoSuchElementException();
			}
		this.m.reset(res+new String(sb));		
		this.PrecNext = st;
		return st;
		}
	
	/**
	 * Restituisce il prossimo token se fa match il pattern costruito
	 * dalla stringa specificata. Se il match ha successo, lo scanner
	 * avanza a seconda dell'input che ha fatto match con il pattern. 
	 * @param s - oggetto String.
	 * @return il prossimo token.
	 * @throws NoSuchElementException - se none'disponibile nessun token.
	 * @throws InputMismatchException - indica che il token restituito
	 * non fa match con il tipo di token atteso.
	 */
	public String next(String s) throws NoSuchElementException, 
	InputMismatchException
		{
		// buffer che contiene il prossimo token
		StringBuffer ris = new StringBuffer("");
		// buffer che contiene il resto dell'input
		StringBuffer sb = new StringBuffer("");
		// stringhe che contiene il prossimo token
		String st = new String();
		// stringa che contiene l'input da resettare
		String res = new String();
		// si controlla se c'� un delimitatore nell'input
		// osservare che il finde'contestale
		if (this.m.find())
			{
			// si assegna il prossimo token a st
			this.m.appendReplacement(ris, "");
			st = new String(ris);
			// si assegna il delimitatore a res
			res = this.m.group();
			if (st.equals("")) 
				{
				// se il tokene'la stringa vuota si controlla 
				// se c'� un'altro delimitatore
				if (this.m.find())
					{
					this.m.appendReplacement(ris, "");
					st = new String(ris);
					res = this.m.group();
					// si mette in sb il resto della stringa 
					this.m.appendTail(sb);
					}
				// se non c'� un altro delimitatore si considera
				// il resto dell'input come token
				else 			
					{
					this.m.appendTail(ris);
					st = new String(ris);
					if (st.equals(""))
						throw new NoSuchElementException();
					}
				}
			else this.m.appendTail(sb);
			}
		// restituire la coda in caso di non find del delimitatore
		else
			{
			this.m.appendTail(ris);
			st = new String(ris);
			if (st.equals(""))
				throw new NoSuchElementException();
			}
		this.m.reset(res+new String(sb));		
		if (st.matches(s)) 
			{
			this.PrecNext = st;
			return st;
			}
		else throw new InputMismatchException();
		}
	
	/**
	 * Restituisce true se e solo se questo scanner ha un'altro 
	 * token nel suo input. Lo scanner non avanza nell'input.
	 * @return un valore booleano.
	 */
	public boolean hasNext()
		{
		// risultato del metodo
		boolean b = true;
		// buffer che contiene il prossimo token
		StringBuffer ris = new StringBuffer("");
		// buffer che contiene il resto dell'input
		StringBuffer sb = new StringBuffer("");
		// stringhe che contiene il prossimo token
		String st = new String();
		// stringa che contiene l'input da resettare
		String res = new String();
		// si controlla se c'� un delimitatore nell'input
		// osservare che il finde'contestale
		if (this.m.find())
			{
			// si assegna il prossimo token a st
			this.m.appendReplacement(ris, "");
			st = new String(ris);
			// si assegna l'input scannerizzato res
			res = st + this.m.group();
			if (st.equals("")) 
				{
				// se il tokene'la stringa vuota si controlla 
				// se c'� un'altro delimitatore
				if (this.m.find())
					{
					this.m.appendReplacement(ris, "");
					st = new String(ris);
					res = res + st + this.m.group();
					// si mette in sb il resto della stringa 
					this.m.appendTail(sb);
					res = res + new String(sb);
					b = b && true;
					}
				// se non c'� un altro delimitatore si considera
				// il resto dell'input come token
				else 			
					{
					this.m.appendTail(ris);
					st = new String(ris);
					res = res + st;
					if (st.equals("")) b = b && false;
					else b = b && true;
					}
				}
			else 
				{
				this.m.appendTail(sb);
				res = res + new String(sb);
				b = b && true;
				}
			}
		// restituire la coda in caso di non find del delimitatore
		else
			{
			this.m.appendTail(ris);
			st = new String(ris);
			res = res + st;
			if (st.equals("")) b = b && false;
			else b = b && true;
			}
		this.m.reset(res);		
		return b;
		}
	
	/**
	 * Restituisce true se e solo se il prossimo token incontra il 
	 * pattern costruito dalla stringa specificata. Lo scanner non 
	 * avanza nell'input.
	 * @param s - una stringa che specifica il pattern da
	 * scansionare.
	 * @return un valore booleano.
	 */
	public boolean hasNext(String s) 
		{
		// risultato del metodo
		boolean b = true;
		// buffer che contiene il prossimo token
		StringBuffer ris = new StringBuffer("");
		// buffer che contiene il resto dell'input
		StringBuffer sb = new StringBuffer("");
		// stringhe che contiene il prossimo token
		String st = new String();
		// stringa che contiene l'input da resettare
		String res = new String();
		// si controlla se c'� un delimitatore nell'input
		// osservare che il finde'contestale
		if (this.m.find())
			{
			// si assegna il prossimo token a st
			this.m.appendReplacement(ris, "");
			st = new String(ris);
			// si assegna l'input scannerizzato res
			res = st + this.m.group();
			if (st.equals("")) 
				{
				// se il tokene'la stringa vuota si controlla 
				// se c'� un'altro delimitatore
				if (this.m.find())
					{
					this.m.appendReplacement(ris, "");
					st = new String(ris);
					res = res + st + this.m.group();
					// si mette in sb il resto della stringa 
					this.m.appendTail(sb);
					res = res + new String(sb);
					b = b && true;
					}
				// se non c'� un altro delimitatore si considera
				// il resto dell'input come token
				else 			
					{
					this.m.appendTail(ris);
					st = new String(ris);
					res = res + st;
					if (st.equals("")) b = b && false;
					else b = b && true;
					}
				}
			else 
				{
				this.m.appendTail(sb);
				res = res + new String(sb);
				b = b && true;
				}
			}
		// restituire la coda in caso di non find del delimitatore
		else
			{
			this.m.appendTail(ris);
			st = new String(ris);
			res = res + st;
			if (st.equals("")) b = b && false;
			else b = b && true;
			}
		this.m.reset(res);
		// hasNext di scanner da false se si fa matching di "" con ""
		if (st.matches(s)) b = b && true;		
		else b = b && false;
		return b;
		}

	/**
	 * Salta l'input che fa match con un pattern costruito dalla
	 * stringa specificata.
	 * @param s - una stringa che specifica il pattern da
	 * saltare. 
	 * @return questo oggetto MyScanner aggiornato secondo
	 * l'operazione di skip.
	 * @throws NoSuchElementException
	 */
	public MyScanner skip(String s) throws NoSuchElementException
		{
		// buffer che contiene l'input rimanente da scannerizzare
		StringBuffer sb = new StringBuffer();
		// stringa che contiene l'input rimanente da scannerizzare
		String st = new String();
		this.m.appendTail(sb);
		st = new String(sb);
		// si effettua un'operazione di match di s con il
		// resto dell'input
		if (st.matches(s+"(.)*"))
			{
			// ris contiene l'input rimasto dopo un'operazione
			// di match
			String ris = st.replaceFirst(s,"");
			int fineSkip = st.length() - ris.length();
			// skippede'il token scannerizzato
			String skipped = st.substring(0,fineSkip);
			this.PrecNext = skipped;
			this.m.reset(ris);
			}
		else throw new NoSuchElementException();
		return this;
		}

	/**
	 * Restituisce il precedente token scansionato da s.
	 * @param s - oggetto MyScanner. 
	 * @return un oggetto String.
	 */
	public static String precMatch(MyScanner s) 
		{
		return s.PrecNext;
		}

	/**
	 * Restituisce il precedente token scansionato da s.
	 * @param s - oggetto Scanner.
	 * @return un oggetto String.
	 * @throws IllegalStateException - se questo scannere'chiuso.
	 */
	public static String precMatch(Scanner s) 
	throws IllegalStateException
		{
		// mr contiene il risultato di un'operazione di match
		MatchResult mr = s.match();
		return mr.group();
		}
}