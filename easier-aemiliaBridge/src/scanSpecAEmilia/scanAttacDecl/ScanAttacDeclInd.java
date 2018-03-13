/**
 * 
 */
package scanSpecAEmilia.scanAttacDecl;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.ScanIdent;
import scanSpecAEmilia.scanExp.ScanExp;
import specificheAEmilia.AttacDecl;
import specificheAEmilia.AttacDeclInd;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanAttacDeclInd {

	private int depth;
	private ErrorMessage errorMessage;

	public ScanAttacDeclInd(int depth) 
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
	 * alla dichiarazione indicizzata di collegamenti tra elementi
	 * architetturali secondo la grammatica AEmilia.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isAttacDeclInd(String specifiche)
		{
		/*
		 * Una dichiarazione di collegamento architetturale ha la seguente forma:
		 *
		 * <architectural_attachment_decl> ::= "FOR_ALL" <identifier> "IN" <expr>
		 * ".." <expr> ["AND" "FOR_ALL" <identifier> "IN" <expr> ".." <expr>]
		 * "FROM" <identifier> ["[" <expr> "]"] "." <identifier>
		 * "TO" <identifier> ["[" <expr> "]"] "." <identifier>
		 */
		String indice1 = new String();
		String ExprInizio1 = new String();
		String ExprFine1 = new String();
		String indice2 = new String();
		String ExprInizio2 = new String();
		String ExprFine2 = new String();
		String r = new String();
		MyScanner s = new MyScanner(specifiche);
		try {
			s.skip("\\s*FOR_ALL\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not indexed attachments declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"FOR_ALL\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		s.useDelimiter("\\s*IN\\s*");
		// indice1 contiene il primo indice
		try {
			indice1 = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not indexed attachments declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "first index expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		s.useDelimiter("\\s*\\.\\.\\s*");
		try {
			s.skip("\\s*IN\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not indexed attachments declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"IN\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		// espressione iniziale del primo indice
		try {
			ExprInizio1 = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not indexed attachments declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "initial expression of first index expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		s.useDelimiter("\\s*FROM\\s*");
		try {
			s.skip("\\s*\\.\\.\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not indexed attachments declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"..\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		// espressione finale del primo indice
		try {
			ExprFine1 = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not indexed attachments declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "ending expression of first index expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		// puo' esserci una seconda indicizzazione
		if (ExprFine1.contains("AND"))
			{
			MyScanner s1 = new MyScanner(ExprFine1);
			s1.useDelimiter("\\s*AND\\s*");
			try {
				ExprFine1 = s1.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not indexed attachments declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "ending expression of first index expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
			s1.useDelimiter("\\s*IN\\s*");
			try {
				s1.skip("\\s*AND\\s*FOR_ALL\\s*");
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not indexed attachments declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "\"AND FOR_ALL\" expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
			// secondo indice
			try {
				indice2 = s1.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not indexed attachments declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "second index expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
			s1.useDelimiter("\\s*\\.\\.\\s*");
			try {
				s1.skip("\\s*IN\\s*");
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not indexed attachments declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "\"IN\" expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
			// espressione iniziale per il secondo indice
			try {
				ExprInizio2 = s1.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not indexed attachments declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "initial expression of second index expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
			s1.useDelimiter("\\s*\\z");
			try {
				s1.skip("\\s*\\.\\.\\s*");
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not indexed attachments declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "\"..\" expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
			// espressione finale per il secondo indice
			try {
				ExprFine2 = s1.next();
				}
			catch (NoSuchElementException noSuchElementException)
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not indexed attachments declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
				String string2 = "ending expression of second index expected";
				errorMessage2.setErrorMessage(string2);
				list.add(errorMessage2);
				return false;
				}
			}
		s.useDelimiter("\\s*\\z");
		try {
			r = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not indexed attachments declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "attachment declaration expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;
			}
		ScanIdent scanIdent = new ScanIdent(this.depth + 1);
		if (!scanIdent.isIdent(indice1))
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not indexed attachment declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = scanIdent.getErrorMessage();
			list.add(errorMessage2);
			return false;
			}
		ScanExp scanExp = new ScanExp(this.depth + 1);
		if (!scanExp.isEspressione(ExprInizio1))
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not indexed attachment declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = scanExp.getErrorMessage();
			list.add(errorMessage2);
			return false;
			}
		ScanExp scanExp2 = new ScanExp(this.depth + 1); 
		if (!scanExp2.isEspressione(ExprFine1))
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not indexed attachment declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = scanExp2.getErrorMessage();
			list.add(errorMessage2);
			return false;
			}
		ScanAttacDeclP scanAttacDeclP = new ScanAttacDeclP(this.depth + 1);
		if (!scanAttacDeclP.isAttacDeclP(r))
			{
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " is not indexed attachment declaration";
			this.errorMessage.setErrorMessage(string);
			ErrorMessage errorMessage2 = scanAttacDeclP.getErrorMessage();
			list.add(errorMessage2);
			return false;
			}
		if (!indice2.equals("")) 
			{
			ScanIdent scanIdent2 = new ScanIdent(this.depth + 1);
			if (!scanIdent2.isIdent(indice2))
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not indexed attachment declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = scanIdent2.getErrorMessage();
				list.add(errorMessage2);
				return false;
				}
			}
		if (!ExprInizio2.equals("")) 
			{
			ScanExp scanExp3 = new ScanExp(this.depth + 1);
			if (!scanExp3.isEspressione(ExprInizio2))
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not indexed attachment declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = scanExp3.getErrorMessage();
				list.add(errorMessage2);
				return false;
				}
			}
		if (!ExprFine2.equals("")) 
			{
			ScanExp scanExp3 = new ScanExp(this.depth + 1);
			if (!scanExp3.isEspressione(ExprFine2))
				{
				List<ErrorMessage> list = this.errorMessage.getCauses();
				String string = specifiche + " is not indexed attachment declaration";
				this.errorMessage.setErrorMessage(string);
				ErrorMessage errorMessage2 = scanExp3.getErrorMessage();
				list.add(errorMessage2);
				return false;
				}
			}
		return true;
		}

	/**
	 * Crea un oggetto AttacDeclInd come una dichiarazione
	 * indicizzata di collegamenti tra elementi architetturali,
	 * includendo informazioni ottenute attraverso la
	 * scannerizzazione di specifiche.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto ArchiElemTypes.
	 * @throws ScanException
	 */
	public AttacDeclInd scanAttacDeclInd(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * Una dichiarazione di collegamento architetturale ha la seguente forma:
			 *
			 * <architectural_attachment_decl> ::= "FOR_ALL" <identifier> "IN" <expr>
			 * ".." <expr> ["AND" "FOR_ALL" <identifier> "IN" <expr> ".." <expr>]
			 * "FROM" <identifier> ["[" <expr> "]"] "." <identifier>
			 * "TO" <identifier> ["[" <expr> "]"] "." <identifier>
			 */
			AttacDeclInd adi = new AttacDeclInd();
			String indice1 = new String();
			String ExprInizio1 = new String();
			String ExprFine1 = new String();
			String indice2 = null;
			String ExprInizio2 = null;
			String ExprFine2 = null;
			String r = new String();
			MyScanner s = new MyScanner(specifiche);
			s.skip("\\s*FOR_ALL\\s*");
			s.useDelimiter("\\s*IN\\s*");
			// indice1 contiene il primo indice
			indice1 = s.next();
			s.useDelimiter("\\s*\\.\\.\\s*");
			s.skip("\\s*IN\\s*");
			// espressione iniziale del primo indice
			ExprInizio1 = s.next();
			s.useDelimiter("\\s*FROM\\s*");
			s.skip("\\s*\\.\\.\\s*");
			// espressione finale del primo indice
			ExprFine1 = s.next();
			// puo' esserci una seconda indicizzazione
			if (ExprFine1.contains("AND")) {
				MyScanner s1 = new MyScanner(ExprFine1);
				s1.useDelimiter("\\s*AND\\s*");
				ExprFine1 = s1.next();
				s1.useDelimiter("\\s*IN\\s*");
				s1.skip("\\s*AND\\s*FOR_ALL\\s*");
				// secondo indice
				indice2 = s1.next();
				s1.useDelimiter("\\s*\\.\\.\\s*");
				s1.skip("\\s*IN\\s*");
				// espressione iniziale per il secondo indice
				ExprInizio2 = s1.next();
				s1.useDelimiter("\\s*\\z");
				s1.skip("\\s*\\.\\.\\s*");
				// espressione finale per il secondo indice
				ExprFine2 = s1.next();
			}
			s.useDelimiter("\\s*\\z");
			r = s.next();
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			ScanExp scanExp = new ScanExp(this.depth + 1);
			adi.setIndex1(scanIdent.scanIdent(indice1));
			adi.setBeginningExpr1(scanExp.scanEspressione(ExprInizio1));
			adi.setEndingExpr1(scanExp.scanEspressione(ExprFine1));
			ScanAttacDeclP scanAttacDeclP = new ScanAttacDeclP(this.depth + 1);
			AttacDecl ad = scanAttacDeclP.scanAttacDeclP(r);
			adi.setOutputAei(ad.getOutputAei());
			adi.setOutputInteraction(ad.getOutputInteraction());
			adi.setInputInteraction(ad.getInputInteraction());
			adi.setInputAei(ad.getInputAei());
			if (!(indice2 == null))
				adi.setIndex2(scanIdent.scanIdent(indice2));
			if (!(ExprInizio2 == null))
				adi.setBeginningExpr2(scanExp.scanEspressione(ExprInizio2));
			if (!(ExprFine2 == null))
				adi.setEndingExpr2(scanExp.scanEspressione(ExprFine2));
			return adi;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
