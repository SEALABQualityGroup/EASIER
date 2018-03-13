/**
 * 
 */
package scanSpecAEmilia.scanIntestazione;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CopyOnWriteArrayList;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.ScanIdent;
import specificheAEmilia.Header;
import specificheAEmilia.ParamDeclaration;
import utilities.ErrorMessage;

/**
 * @author Mirko
 *
 */
class ScanIntestBEHAV1 {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanIntestBEHAV1(int depth) 
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
	 * ad un'intestazione della prima equazione comportamentale
	 * di un tipo di elemento architetturale.
	 *
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isIntestBEHAV1(String specifiche)
		{
		/*
		 * L'intestazione della prima equazione comportamentale
		 * ha la seguente sintassi:
		 *
		 * <identifier> "(" <init_var_formal_par_decl_sequence> ";"
		 * <local_var_decl_sequence> ")"
		 */
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*\\(\\s*");
		// ie'l'identificatore per la prima equazione
		// comportamentale
		String i = null;
		try {
			i = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not header of behavioral equation";
			this.errorMessage.setErrorMessage(string);
			String string2 = "identifier expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		s.useDelimiter("\\s*\\;\\s*");
		try {
			s.skip("\\s*\\(\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not header of behavioral equation";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\"(\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		// pars1e'una sequenza di dichiarazioni di parametri
		// formali variabili inizializzati
		String pars1 = null;
		try {
			pars1 = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not header of behavioral equation";
			this.errorMessage.setErrorMessage(string);
			String string2 = "sequence of inizialized parameters declarations expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		s.useDelimiter("\\s*\\)\\s*\\z");
		try {
			s.skip("\\s*\\;\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not header of behavioral equation";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\";\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		// pars2e'una sequenza di variabili locali
		// inizializzate
		String pars2 = null;
		try {
			pars2 = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not header of behavioral equation";
			this.errorMessage.setErrorMessage(string);
			String string2 = "sequence of local variables declarations expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		try {
			s.skip("\\s*\\)\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not header of behavioral equation";
			this.errorMessage.setErrorMessage(string);
			String string2 = "\")\" expected";
			ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
			errorMessage.setErrorMessage(string2);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			list.add(errorMessage);
			return false;
			}
		ScanIdent scanIdent = new ScanIdent(this.depth + 1);
		ScanVarInitSeq scanVarInitSeq = new ScanVarInitSeq(this.depth + 1);
		ScanLocalSeq scanLocalSeq = new ScanLocalSeq(this.depth + 1);
		if (!scanIdent.isIdent(i))
			{
			String string = specifiche + " is not header of behavioral equation";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanIdent.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		if (!scanVarInitSeq.isVarInitSeq(pars1))
			{
			String string = specifiche + " is not header of behavioral equation";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanVarInitSeq.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		if (!scanLocalSeq.isLocalSeq(pars2))
			{
			String string = specifiche + " is not header of behavioral equation";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanLocalSeq.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto Header, includendo informazioni
	 * ottenute attraverso la scannerizzazione dell'intestazione
	 * della prima equazione comportamentale di un tipo di
	 * elemento architetturale.
	 *
	 * @param specifiche - oggetto String.
	 * @return un oggetto Header.
	 * @throws ScanException
	 */
	public Header scanIntestBEHAV1(String specifiche)
		throws ScanException
		{
		try {
			/*
			 * L'intestazione della prima equazione comportamentale ha
			 * la seguente sintassi:
			 *
			 * <identifier> "(" <init_var_formal_par_decl_sequence> ";"
			 *  <local_var_decl_sequence> ")"
			 */
			Header i = new Header();
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\(\\s*");
			// ide'l'identificatore per la prima equazione
			// comportamentale
			String id = s.next();
			s.skip("\\s*\\(\\s*");
			ScanIdent scanIdent = new ScanIdent(this.depth + 1);
			i.setName(scanIdent.scanIdent(id));
			s.useDelimiter("\\s*\\;\\s*");
			// pars1e'una sequenza di dichiarazioni di parametri
			// formali variabili inizializzati
			String pars1 = s.next();
			s.useDelimiter("\\s*\\)\\s*\\z");
			s.skip("\\s*\\;\\s*");
			// pars2e'una sequenza di variabili locali
			// inizializzate
			String pars2 = s.next();
			s.skip("\\s*\\)\\s*\\z");
			ScanDeclParSeq scanDeclParSeq = new ScanDeclParSeq(this.depth + 1);
			ScanDeclParSeq scanDeclParSeq2 = new ScanDeclParSeq(this.depth + 1);
			ParamDeclaration[] paramDeclarations = scanDeclParSeq.scanDeclParSeq(pars1);
			ParamDeclaration[] paramDeclarations2 = scanDeclParSeq2.scanDeclParSeq(pars2);
			CopyOnWriteArrayList<ParamDeclaration> copyOnWriteArrayList = new CopyOnWriteArrayList<ParamDeclaration>(
					paramDeclarations);
			CopyOnWriteArrayList<ParamDeclaration> copyOnWriteArrayList2 = new CopyOnWriteArrayList<ParamDeclaration>(
					paramDeclarations2);
			copyOnWriteArrayList.addAll(copyOnWriteArrayList2);
			ParamDeclaration[] paramDeclarations3 = new ParamDeclaration[copyOnWriteArrayList
					.size()];
			copyOnWriteArrayList.toArray(paramDeclarations3);
			i.setParameters(paramDeclarations3);
			return i;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
