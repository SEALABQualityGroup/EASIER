package scanSpecAEmilia;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import utilities.ErrorMessage;

/**
 * Classe utilizzata per scannerizzare un identificatore.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class ScanIdent {

	private int depth;
	private ErrorMessage errorMessage;
	
	private static String[] keywords = {
		"ARCHI_TYPE",
		"ARCHI_ELEM_TYPES",
		"ELEM_TYPE",
		"BEHAVIOR",
		"INPUT_INTERACTIONS",
		"OUTPUT_INTERACTIONS",
		"UNI",
		"AND",
		"OR",
		"ARCHI_TOPOLOGY",
		"ARCHI_ELEM_INSTANCES",
		"ARCHI_INTERACTIONS",
		"ARCHI_ATTACHMENTS",
		"FROM",
		"TO",
		"BEHAV_VARIATIONS",
		"BEHAV_HIDINGS",
		"HIDE",
		"INTERNALS",
		"INTERACTIONS",
		"ALL",
		"BEHAV_RESTRICTIONS",
		"RESTRICT",
		"OBS_INTERNALS",
		"OBS_INTERACTIONS",
		"ALL_OBSERVABLES",
		"BEHAV_RENAMINGS",
		"RENAME",
		"AS",
		"FOR_ALL",
		"IN",
		"END",
		"const",
		"local",
		"stop",
		"invisible",
		"exp",
		"inf",
		"choice",
		"cond",
		"void",
		"prio",
		"rate",
		"weight",
		"integer",
		"real",
		"mod",
		"min",
		"max",
		"abs",
		"ceil",
		"floor",
		"power",
		"epower",
		"loge",
		"log10",
		"sqrt",
		"sin",
		"cos",
		"c_uniform",
		"erlang",
		"gamma",
		"exponential",
		"weibull",
		"beta",
		"normal",
		"pareto",
		"b_pareto",
		"d_uniform",
		"bernoulli",
		"binomial",
		"poisson",
		"neg_binomial",
		"geometric",
		"pascal",
		"boolean",
		"true",
		"false",
		"list",
		"list_cons",
		"first",
		"tail",
		"concat",
		"insert",
		"remove",
		"length",
		"array",
		"array_cons",
		"read",
		"write",
		"record",
		"record_cons",
		"get",
		"put"
	};
		
	public ScanIdent(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se specifiche corrisponde
	 * ad un identificatore.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */

	public boolean isIdent(String specifiche)
		{
		/* MODELLED */
		Scanner s = new Scanner(specifiche);
		s.useDelimiter("\\s*\\z");
		try {
			s.skip("\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not identifier";
			this.errorMessage.setErrorMessage(string);
			String string2 = "sequence of zero o more whitespace character expected";
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			errorMessage2.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage2);
			return false;
			}
		if (s.hasNext("[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)"))
			{
			String string11 = null;
			try {
				string11 = s.next("[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)");
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not identifier";
				this.errorMessage.setErrorMessage(string);
				String string2 = "identifier expected";
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				errorMessage2.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage2);
				return false;
				}
			CopyOnWriteArrayList<String> copyOnWriteArrayList = 
				new CopyOnWriteArrayList<String>(keywords);
			try {
				s.skip("\\s*\\z");
				}
			catch (NoSuchElementException noSuchElementException)
				{
				String string = specifiche + " is not identifier";
				this.errorMessage.setErrorMessage(string);
				String string2 = "sequence of zero o more whitespace character expected";
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				errorMessage2.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage2);
				return false;
				}
			if (copyOnWriteArrayList.contains(string11))
				{
				String string = specifiche + " is not identifier";
				this.errorMessage.setErrorMessage(string);
				String string2 = specifiche + " is keyword";
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				errorMessage2.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage2);
				return false;
				}
			else 
				return true;
			}
		else
			{
			String string = specifiche + " is not identifier";
			this.errorMessage.setErrorMessage(string);
			String string2 = "identifier expected";
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			errorMessage2.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage2);
			return false;
			}
		}

	/**
	 * Crea un oggetto String, ottenute attraverso la
	 * scannerizzazione di un identificatore.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto String.
	 * @throws ScanException
	 */
	public String scanIdent(String specifiche)
		throws ScanException
		{
		try {
			Scanner s = new Scanner(specifiche);
			String identificatore = new String();
			s.skip("\\s*");
			identificatore = s.next("[a-zA-Z_&&[^0-9<>=!(),;]](\\w*)");
			s.skip("\\s*");
			return identificatore;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}
	}
