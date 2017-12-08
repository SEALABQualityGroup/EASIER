package scanSpecAEmilia.scanTipoDato;

import java.util.List;

import scanSpecAEmilia.ScanException;
import specificheAEmilia.DataType;
import utilities.ErrorMessage;

/**
 * Classe utilizzata per scannerizzare un tipo di dato,
 * in accordo con la grammatica AEmilia.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

/*
 * <data_type> ::= <normal_type>
 * | <special_type>
 * <normal_type> ::= "integer"
 * | "integer" "(" <expr> ".." <expr> ")"
 * | "real"
 * | "boolean"
 * | "list" "(" <normal_type> ")"
 * | "array" "(" <expr> "," <normal_type> ")"
 * | "record" "(" <field_decl_sequence> ")"
 * <special_type> ::= "prio"
 * | "rate"
 * | "weight"
 *
 * dove <field_decl_sequence>e'una sequenza non vuota di dichiarazioni di campi
 * separati da virgole del tipo: <data_type> <identifier>.
 * Il tipo prio denota l'insieme delle priorità delle azioni passive e immediate, che
 * coincide con l'insieme degli interi positivi.
 * Il tipo rate denota l'insieme dei tassi azioni temporizzate esponenzialmente, che
 * coincide con l'insieme dei reali positivi.
 * Il tipo weight denota l'insieme dei pesi azioni passive e immediate, che coincidono
 * con l'insieme dei reali positivi.
 *
 * IMPLEMENTARE LE LISTE GLI ARRAY E I RECORDS
 */

public class ScanTipoDato {

	private int depth;
	private ErrorMessage errorMessage;
	
	// costanti per i tipi di dato non implementati

	public ScanTipoDato(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se specifiche corrisponde
	 * ad un tipo di dato, secondo la grammatica AEmilia.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isTipoDato(String specifiche)
		{
		ScanNormalType scanNormalType = new ScanNormalType(this.depth + 1);
		ScanSpecialType scanSpecialType = new ScanSpecialType(this.depth + 1);
		if (!(scanNormalType.isNormalType(specifiche) || scanSpecialType.isSpecialType(specifiche)))
			{
			String string = specifiche + " is not data type";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanNormalType.getErrorMessage();
			ErrorMessage errorMessage2 = scanSpecialType.getErrorMessage();
			list.add(errorMessage);
			list.add(errorMessage2);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto DataType attraverso la scannerizzazione
	 * di specifiche, secondo la grammatica AEmilia.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto DataType.
	 * @throws ScanException
	 */

	public DataType scanTipoDato(String specifiche)
		throws ScanException
		{
		DataType t = new DataType();
		ScanNormalType scanNormalType = new ScanNormalType(this.depth + 1);
		ScanSpecialType scanSpecialType = new ScanSpecialType(this.depth + 1);
		if (scanNormalType.isNormalType(specifiche)) t = scanNormalType.scanNormalType(specifiche);
		else if (scanSpecialType.isSpecialType(specifiche)) t = scanSpecialType.scanSpecialType(specifiche);
		else return null;
		return t;
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}
	}