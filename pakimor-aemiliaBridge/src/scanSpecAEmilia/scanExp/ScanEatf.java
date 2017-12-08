/**
 * 
 */
package scanSpecAEmilia.scanExp;

import java.util.List;

import scanSpecAEmilia.ScanException;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanEatf {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanEatf(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}

	/**
	 * Restituisce true se e solo se specifiche corrisponde
	 * ad un termine di un'espressione aritmetica.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isEatf(String specifiche)
		{
		/*
		 * Eatf -> "(" Ea ")" | id | digits | digits"."digits | "mod" "(" Ea "," 	Ea ")" | 
		 * "abs" "(" Ea ")" | "ceil" "(" Ea ")" | "floor" "(" Ea 	")"
		 * | "min" "(" Ea "," Ea ")" | "max" "(" Ea "," Ea ")" | "power" 	"(" Ea "," Ea ")" 
		 * | "epower" "(" Ea ")" | "loge" "(" Ea ")" | 	"log10" "(" Ea ")" | "sqrt" "(" Ea ")" | "sin" "(" Ea ")" 
		 * | "cos" "(" Ea ")" | "c_uniform" "(" Ea "," Ea ")" | "erlang" 	"(" Ea "," Ea ")" | "gamma" "(" Ea "," Ea ")" 
		 * | "exponential" 	"(" Ea ")" | "weibull" "(" Ea "," Ea ")" | "beta" "(" Ea "," 	Ea ")" | "normal" "(" Ea "," Ea ")" 	
		 * | "pareto" "(" Ea ")" 	| "b_pareto" "(" Ea "," Ea "," Ea ")" | "d_uniform" "(" Ea "," 	Ea ")" 
		 * | "bernoulli" "(" Ea "," Ea "," Ea ")" | "binomial" "(" 	Ea "," Ea ")" | "poisson" "(" Ea ")" 
		 * | "neg_binomial" "(" Ea 	"," Ea ")" | "geometric" "(" Ea ")" | "pascal" "(" Ea "," Ea 	")"  
		 * | "length" "(" id ")"
		 */
		ScanReal scanReal = new ScanReal(this.depth + 1);
		ScanInteger scanInteger = new ScanInteger(this.depth + 1);
		ScanIdentExpr scanIdentExpr = new ScanIdentExpr(this.depth + 1);
		ScanLength scanLength = new ScanLength(this.depth + 1);
		ScanParEa scanParEa = new ScanParEa(this.depth + 1);
		if (!(scanReal.isReal(specifiche) ||
		scanInteger.isInteger(specifiche) ||
		scanIdentExpr.isIdentExpr(specifiche) ||
		scanLength.isLength(specifiche) ||
		scanParEa.isParEa(specifiche)))
			{
			String string = specifiche + " is not arithmetic expression term";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage1 = scanReal.getErrorMessage();
			ErrorMessage errorMessage2 = scanInteger.getErrorMessage();
			ErrorMessage errorMessage3 = scanIdentExpr.getErrorMessage();
			ErrorMessage errorMessage4 = scanLength.getErrorMessage();
			ErrorMessage errorMessage5 = scanParEa.getErrorMessage();
			list.add(errorMessage1);
			list.add(errorMessage2);
			list.add(errorMessage3);
			list.add(errorMessage4);
			list.add(errorMessage5);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto Expression, ottenuto attraverso la
	 * scannerizzazione di un termine di un'espressione
	 * aritmetica.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Expression.
	 * @throws ScanException
	 */
	public Expression scanEatf(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * Eatf -> "(" Ea ")" | id | digits | digits"."digits | "mod" "(" Ea "," 	Ea ")" | 
			 * "abs" "(" Ea ")" | "ceil" "(" Ea ")" | "floor" "(" Ea 	")"
			 * | "min" "(" Ea "," Ea ")" | "max" "(" Ea "," Ea ")" | "power" 	"(" Ea "," Ea ")" 
			 * | "epower" "(" Ea ")" | "loge" "(" Ea ")" | 	"log10" "(" Ea ")" | "sqrt" "(" Ea ")" | "sin" "(" Ea ")" 
			 * | "cos" "(" Ea ")" | "c_uniform" "(" Ea "," Ea ")" | "erlang" 	"(" Ea "," Ea ")" | "gamma" "(" Ea "," Ea ")" 
			 * | "exponential" 	"(" Ea ")" | "weibull" "(" Ea "," Ea ")" | "beta" "(" Ea "," 	Ea ")" | "normal" "(" Ea "," Ea ")" 	
			 * | "pareto" "(" Ea ")" 	| "b_pareto" "(" Ea "," Ea "," Ea ")" | "d_uniform" "(" Ea "," 	Ea ")" 
			 * | "bernoulli" "(" Ea "," Ea "," Ea ")" | "binomial" "(" 	Ea "," Ea ")" | "poisson" "(" Ea ")" 
			 * | "neg_binomial" "(" Ea 	"," Ea ")" | "geometric" "(" Ea ")" | "pascal" "(" Ea "," Ea 	")"  
			 * | "length" "(" id ")"
			 */
			ScanReal scanReal = new ScanReal(this.depth + 1);
			ScanInteger scanInteger = new ScanInteger(this.depth + 1);
			ScanParEa scanParEa = new ScanParEa(this.depth + 1);
			ScanLength scanLength = new ScanLength(this.depth + 1);
			ScanIdentExpr scanIdentExpr = new ScanIdentExpr(this.depth + 1);
			if (scanParEa.isParEa(specifiche))
				return scanParEa.scanParEa(specifiche);
			else if (scanInteger.isInteger(specifiche))
				return scanInteger.scanInteger(specifiche);
			else if (scanReal.isReal(specifiche))
				return scanReal.scanReal(specifiche);
			else if (scanLength.isLength(specifiche))
				return scanLength.scanLength(specifiche);
			else if (scanIdentExpr.isIdentExpr(specifiche))
				return scanIdentExpr.scanIdentExpr(specifiche);
			else
				return null;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}

}
