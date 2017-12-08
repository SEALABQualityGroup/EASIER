package scanSpecAEmilia.scanExp;

import java.util.List;

import scanSpecAEmilia.ScanException;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;

/**
 * Classe utilizzata per scannerizzare ogni parte di un'espressione, dettata
 * dalla grammatica AEmilia. Le espressioni con un'operatore binario sono tutte
 * associative sinistre.
 *
 * @author Mirko Email: <a href=
 *         "mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

public class ScanExp {

	private int depth;
	private ErrorMessage errorMessage;

	// funzioni non implementate
	public static final String MODULO = "\\s*mod\\s*\\(.*";
	public static final String VALORE_ASSOLUTO = "\\s*abs\\s*\\(.*";
	public static final String TETTO = "\\s*ceil\\s*\\(.*";
	public static final String BASE = "\\s*floor\\s*\\(.*";
	public static final String MINIMO = "\\s*min\\s*\\(.*";
	public static final String MASSIMO = "\\s*max\\s*\\(.*";
	public static final String POTENZA = "\\s*power\\s*\\(.*";
	public static final String POTENZA_E = "\\s*epower\\s*\\(.*";
	public static final String LOGARITMO_E = "\\s*loge\\s*\\(.*";
	public static final String LOGARITMO_10 = "\\s*log10\\s*\\(.*";
	public static final String RADICE_QUADRATA = "\\s*sqrt\\s*\\(.*";
	public static final String SENO = "\\s*sin\\s*\\(.*";
	public static final String COSENO = "\\s*cos\\s*\\(.*";

	// generatori di numeri pseudo-casuali
	public static final String C_UNIFORM = "\\s*c_uniform\\s*\\(.*";
	public static final String ERLANG = "\\s*erlang\\s*\\(.*";
	public static final String GAMMA = "\\s*gamma\\s*\\(.*";
	public static final String EXPONENTIAL = "\\s*exponential\\s*\\(.*";
	public static final String WEIBULL = "\\s*weibull\\s*\\(.*";
	public static final String BETA = "\\s*beta\\s*\\(.*";
	public static final String NORMAL = "\\s*normal\\s*\\(.*";
	public static final String PARETO = "\\s*pareto\\s*\\(.*";
	public static final String B_PARETO = "\\s*b_pareto\\s*\\(.*";
	public static final String D_UNIFORM = "\\s*d_uniform\\s*\\(.*";
	public static final String BERNOULLI = "\\s*bernoulli\\s*\\(.*";
	public static final String BINOMIAL = "\\s*binomial\\s*\\(.*";
	public static final String POISSON = "\\s*poisson\\s*\\(.*";
	public static final String NEG_BINOMIAL = "\\s*neg_binomial\\s*\\(.*";
	public static final String GEOMETRIC = "\\s*geometric\\s*\\(.*";
	public static final String PASCAL = "\\s*pascal\\s*\\(.*";

	// espressioni per le liste

	// espressioni per gli array

	// espressioni per i records
	public static final String COSTRUZIONE_RECORD = "\\s*record_cons\\s*\\(.*";
	public static final String LETTURA_CAMPO_RECORD = "\\s*get\\s*\\(.*";
	public static final String SCRITTURA_CAMPO_RECORD = "\\s*put\\s*\\(.*";

	/*
	 * Grammatica per il parsing delle espressioni
	 *
	 * E -> Eb | Ea | El | Earr | Erecord | "first" "(" id ")" | "read" "(" E "," id
	 * ")" | "get" "(" id "," id ")" Eb -> Eb "&&" Ebt | Eb "||" Ebt | Ebt Ebt -> id
	 * | true | false | Ea "<" Ea | Ea "<=" Ea | Ea ">" Ea | Ea ">=" Ea | Ea "=" Ea
	 * | Ea "!=" Ea | "(" Eb ")" | "!"Ebt Ea -> Ea "+" Eat | Ea "e'" Eat | Eat Eat ->
	 * Eat "*" Eatf | Eat "/" Eatf | Eatf Eatf -> "(" Ea ")" | id | digits |
	 * digits"."digits | "mod" "(" Ea "," Ea ")" | "abs" "(" Ea ")" | "ceil" "(" Ea
	 * ")" | "floor" "(" Ea ")" | "min" "(" Ea "," Ea ")" | "max" "(" Ea "," Ea ")"
	 * | "power" "(" Ea "," Ea ")" | "epower" "(" Ea ")" | "loge" "(" Ea ")" |
	 * "log10" "(" Ea ")" | "sqrt" "(" Ea ")" | "sin" "(" Ea ")" | "cos" "(" Ea ")"
	 * | "c_uniform" "(" Ea "," Ea ")" | "erlang" "(" Ea "," Ea ")" | "gamma" "(" Ea
	 * "," Ea ")" | "exponential" "(" Ea ")" | "weibull" "(" Ea "," Ea ")" | "beta"
	 * "(" Ea "," Ea ")" | "normal" "(" Ea "," Ea ")" | "pareto" "(" Ea ")" |
	 * "b_pareto" "(" Ea "," Ea "," Ea ")" | "d_uniform" "(" Ea "," Ea ")" |
	 * "bernoulli" "(" Ea "," Ea "," Ea ")" | "binomial" "(" Ea "," Ea ")" |
	 * "poisson" "(" Ea ")" | "neg_binomial" "(" Ea "," Ea ")" | "geometric" "(" Ea
	 * ")" | "pascal" "(" Ea "," Ea ")" | "length" "(" El ")" El -> "list_cons" "("
	 * E* ")" | "tail" "(" id ")" | "concat" "(" id "," id ")" | "insert" "(" E ","
	 * id ")" | "remove" "(" E "," id ")" Earr -> "array_cons" "(" E* ")" | "write"
	 * "(" E "," E "," id ")" Erecord -> "record_cons" "(" E* ")" | "put" "(" id ","
	 * E "," id ")"
	 */

	public ScanExp(int depth) {
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	/**
	 * Restituisce true se e solo se specifiche corrisponde ad un'espressione.
	 *
	 * @param specifiche
	 *            - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isEspressione(String specifiche) {
		/* MODELLED */
		/*
		 * E -> Eb | Ea | El | Earr | Erecord | "first" "(" id ")" | "read" "(" E "," id
		 * ")" | "get" "(" id "," id ")"
		 */
		ScanEa scanEa = new ScanEa(this.depth + 1);
		ScanEb scanEb = new ScanEb(this.depth + 1);
		ScanEl scanEl = new ScanEl(this.depth + 1);
		ScanEarr scanEarr = new ScanEarr(this.depth + 1);
		ScanErecord scanErecord = new ScanErecord(this.depth + 1);
		ScanFirst scanFirst = new ScanFirst(this.depth + 1);
		ScanRead scanRead = new ScanRead(this.depth + 1);
		ScanGet scanGet = new ScanGet(this.depth + 1);
		if (isImplementato(specifiche)) {
			if (!(scanEa.isEa(specifiche) || scanEb.isEb(specifiche) || scanEl.isEl(specifiche)
					|| scanEarr.isEarr(specifiche) || scanErecord.isErecord(specifiche) || scanFirst.isFirst(specifiche)
					|| scanRead.isRead(specifiche) || scanGet.isGet(specifiche))) {
				String string = specifiche + " is not expression";
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage1 = scanEa.getErrorMessage();
				ErrorMessage errorMessage2 = scanEarr.getErrorMessage();
				ErrorMessage errorMessage3 = scanEb.getErrorMessage();
				ErrorMessage errorMessage4 = scanEl.getErrorMessage();
				ErrorMessage errorMessage5 = scanErecord.getErrorMessage();
				ErrorMessage errorMessage6 = scanFirst.getErrorMessage();
				ErrorMessage errorMessage7 = scanGet.getErrorMessage();
				ErrorMessage errorMessage8 = scanRead.getErrorMessage();
				list.add(errorMessage1);
				list.add(errorMessage2);
				list.add(errorMessage3);
				list.add(errorMessage4);
				list.add(errorMessage5);
				list.add(errorMessage6);
				list.add(errorMessage7);
				list.add(errorMessage8);
				return false;
			}
			return true;
		} else {
			String string = specifiche + " is not expression";
			this.errorMessage.setErrorMessage(string);
			String string2 = specifiche + " is expression not implemented";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
		}
	}

	/**
	 * Crea un oggetto Expression, ottenuto attraverso la scannerizzazione di
	 * specifiche, secondo la grammatica AEmilia.
	 *
	 * @param specifiche
	 *            - oggetto String.
	 * @return un oggetto Expression.
	 * @throws ScanException
	 */
	public Expression scanEspressione(String specifiche) throws ScanException {
		ScanEa scanEa = new ScanEa(this.depth + 1);
		ScanEb scanEb = new ScanEb(this.depth + 1);
		ScanEl scanEl = new ScanEl(this.depth + 1);
		ScanEarr scanEarr = new ScanEarr(this.depth + 1);
		ScanErecord scanErecord = new ScanErecord(this.depth + 1);
		ScanFirst scanFirst = new ScanFirst(this.depth + 1);
		ScanRead scanRead = new ScanRead(this.depth + 1);
		ScanGet scanGet = new ScanGet(this.depth + 1);
		try {
			/*
			 * E -> Eb | Ea | El | Earr | Erecord | "first" "(" id ")" | "read" "(" E "," id
			 * ")" | "get" "(" id "," id ")"
			 */
			if (specifiche.matches(ScanExp.B_PARETO))
				throw new ScanException("Pseudo-random number generators not realized");
			else if (specifiche.matches(ScanExp.BASE))
				throw new ScanException("the floor function not realized");
			else if (specifiche.matches(ScanExp.BERNOULLI))
				throw new ScanException("Pseudo-random number generators not realized");
			else if (specifiche.matches(ScanExp.BETA))
				throw new ScanException("Pseudo-random number generators not realized");
			else if (specifiche.matches(ScanExp.BINOMIAL))
				throw new ScanException("Pseudo-random number generators not realized");
			else if (specifiche.matches(ScanExp.C_UNIFORM))
				throw new ScanException("Pseudo-random number generators not realized");
			else if (specifiche.matches(ScanExp.COSENO))
				throw new ScanException("coseno function not realized");
			else if (specifiche.matches(ScanExp.D_UNIFORM))
				throw new ScanException("Pseudo-random number generators not realized");
			else if (specifiche.matches(ScanExp.ERLANG))
				throw new ScanException("Pseudo-random number generators not realized");
			else if (specifiche.matches(ScanExp.EXPONENTIAL))
				throw new ScanException("Pseudo-random number generators not realized");
			else if (specifiche.matches(ScanExp.GAMMA))
				throw new ScanException("Pseudo-random number generators not realized");
			else if (specifiche.matches(ScanExp.GEOMETRIC))
				throw new ScanException("Pseudo-random number generators not realized");
			else if (specifiche.matches(ScanExp.LOGARITMO_10))
				throw new ScanException("base-10 logatithm not realized");
			else if (specifiche.matches(ScanExp.LOGARITMO_E))
				throw new ScanException("natural logarithm not realized");
			else if (specifiche.matches(ScanExp.MASSIMO))
				throw new ScanException("maximum function not realized");
			else if (specifiche.matches(ScanExp.MINIMO))
				throw new ScanException("minimum function not realized");
			else if (specifiche.matches(ScanExp.MODULO))
				throw new ScanException("modulus function not realized");
			else if (specifiche.matches(ScanExp.NEG_BINOMIAL))
				throw new ScanException("Pseudo-random number generators not realized");
			else if (specifiche.matches(ScanExp.NORMAL))
				throw new ScanException("Pseudo-random number generators not realized");
			else if (specifiche.matches(ScanExp.PARETO))
				throw new ScanException("Pseudo-random number generators not realized");
			else if (specifiche.matches(ScanExp.PASCAL))
				throw new ScanException("Pseudo-random number generators not realized");
			else if (specifiche.matches(ScanExp.POISSON))
				throw new ScanException("Pseudo-random number generators not realized");
			else if (specifiche.matches(ScanExp.POTENZA))
				throw new ScanException("power function not realized");
			else if (specifiche.matches(ScanExp.POTENZA_E))
				throw new ScanException("power of e function not realized");
			else if (specifiche.matches(ScanExp.RADICE_QUADRATA))
				throw new ScanException("square root function not realized");
			else if (specifiche.matches(ScanExp.SENO))
				throw new ScanException("sine function not realized");
			else if (specifiche.matches(ScanExp.TETTO))
				throw new ScanException("floor function not realized");
			else if (specifiche.matches(ScanExp.VALORE_ASSOLUTO))
				throw new ScanException("absolute value function not realized");
			else if (specifiche.matches(ScanExp.WEIBULL))
				throw new ScanException("Pseudo-random number generators not realized");
			else if (scanEa.isEa(specifiche))
				return scanEa.scanEa(specifiche);
			else if (scanEb.isEb(specifiche))
				return scanEb.scanEb(specifiche);
			else if (scanEl.isEl(specifiche))
				return scanEl.scanEl(specifiche);
			else if (scanEarr.isEarr(specifiche))
				return scanEarr.scanEarr(specifiche);
			else if (scanErecord.isErecord(specifiche))
				return scanErecord.scanErecord(specifiche);
			else if (scanFirst.isFirst(specifiche))
				return scanFirst.scanFirst(specifiche);
			else if (scanRead.isRead(specifiche))
				return scanRead.scanRead(specifiche);
			else if (scanGet.isGet(specifiche))
				return scanGet.scanGet(specifiche);
			else
				return null;
		} catch (Exception e) {
			throw new ScanException(e);
		}
	}

	private boolean isImplementato(String specifiche) {
		if (specifiche.matches(ScanExp.B_PARETO))
			return false;
		else if (specifiche.matches(ScanExp.BASE))
			return false;
		else if (specifiche.matches(ScanExp.BERNOULLI))
			return false;
		else if (specifiche.matches(ScanExp.BETA))
			return false;
		else if (specifiche.matches(ScanExp.BINOMIAL))
			return false;
		else if (specifiche.matches(ScanExp.C_UNIFORM))
			return false;
		else if (specifiche.matches(ScanExp.COSENO))
			return false;
		else if (specifiche.matches(ScanExp.D_UNIFORM))
			return false;
		else if (specifiche.matches(ScanExp.ERLANG))
			return false;
		else if (specifiche.matches(ScanExp.EXPONENTIAL))
			return false;
		else if (specifiche.matches(ScanExp.GAMMA))
			return false;
		else if (specifiche.matches(ScanExp.GEOMETRIC))
			return false;
		else if (specifiche.matches(ScanExp.LOGARITMO_10))
			return false;
		else if (specifiche.matches(ScanExp.LOGARITMO_E))
			return false;
		else if (specifiche.matches(ScanExp.MASSIMO))
			return false;
		else if (specifiche.matches(ScanExp.MINIMO))
			return false;
		else if (specifiche.matches(ScanExp.MODULO))
			return false;
		else if (specifiche.matches(ScanExp.NEG_BINOMIAL))
			return false;
		else if (specifiche.matches(ScanExp.NORMAL))
			return false;
		else if (specifiche.matches(ScanExp.PARETO))
			return false;
		else if (specifiche.matches(ScanExp.PASCAL))
			return false;
		else if (specifiche.matches(ScanExp.POISSON))
			return false;
		else if (specifiche.matches(ScanExp.POTENZA))
			return false;
		else if (specifiche.matches(ScanExp.POTENZA_E))
			return false;
		else if (specifiche.matches(ScanExp.RADICE_QUADRATA))
			return false;
		else if (specifiche.matches(ScanExp.SENO))
			return false;
		else if (specifiche.matches(ScanExp.TETTO))
			return false;
		else if (specifiche.matches(ScanExp.VALORE_ASSOLUTO))
			return false;
		else if (specifiche.matches(ScanExp.WEIBULL))
			return false;
		else
			return true;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
}