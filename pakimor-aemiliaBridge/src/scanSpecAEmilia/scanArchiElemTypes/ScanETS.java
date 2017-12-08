/**
 * 
 */
package scanSpecAEmilia.scanArchiElemTypes;

import java.util.List;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanElemType;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.ElemType;
import utilities.ErrorMessage;

/**
 * @author Jimmy
 *
 */
class ScanETS {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanETS(int depth) 
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
	 * Restituisce true se e solo se specifichee'una sequenza non vuota di
	 * dichiarazioni di tipi di elementi architetturali.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */
	public boolean isETS(String specifiche)
		{
		// specifiche deve iniziare con ELEM_TYPE
		if (!specifiche.matches("\\s*ELEM_TYPE\\s*(.)+"))
			{
			String string1 = specifiche + " is not sequence of archtectural element types";
			this.errorMessage.setErrorMessage(string1);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = specifiche + " must begin with \"ELEM_TYPE\"";
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			errorMessage2.setErrorMessage(string);
			list.add(errorMessage2);
			return false;
			}
		MyScanner s = new MyScanner(specifiche);
		s.useDelimiter("\\s*ELEM_TYPE\\s*");
		String et = new String();
		// si verifica se specifichee'composta da una
		// sequenza di dichiarazioni di tipi di elementi
		// architetturali
		int c = 0;
		while (s.hasNext())
			{
			et = "ELEM_TYPE "+s.next();
			ScanElemType scanElemType = new ScanElemType(this.depth + 1);
			if (!scanElemType.isElemType(et)) 
				{
				String string1 = specifiche + " is not sequence of archtectural element types";
				this.errorMessage.setErrorMessage(string1);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage2 = scanElemType.getErrorMessage();
				list.add(errorMessage2);				
				return false;
				}
			c++;
			}
		if (c == 0) 
			{
			String string1 = specifiche + " is not sequence of archtectural element types";
			this.errorMessage.setErrorMessage(string1);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			String string = "not empty sequence expected";
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			errorMessage2.setErrorMessage(string);
			list.add(errorMessage2);
			return false;
			}
		return true;
		}

	/**
	 * Scannerizza una sequenza non vuota di dichiarazioni di tipi
	 * di elementi architetturali, generando un array di oggetti
	 * ElemType.
	 * @param specifiche - oggetto String.
	 * @return un array di oggetti ElemType.
	 * @throws ScanException
	 */
	public ElemType[] scanETS(String specifiche)
		throws ScanException
		{
		try {
			ElemType[] aets = null;
			MyScanner s = new MyScanner(specifiche);
			s.skip("\\s*ELEM_TYPE\\s*");
			s.useDelimiter("\\s*ELEM_TYPE\\s*");
			String et = new String();
			int c = 0;
			// si contano le dichiarazioni di tipi elemento
			while (s.hasNext()) {
				et = s.next();
				c++;
			}
			// il numero di tipi di elementi architetturali
			// deve essere maggiore di zero
			aets = new ElemType[c];
			s = new MyScanner(specifiche);
			s.useDelimiter("\\s*ELEM_TYPE\\s*");
			// si scannerizzano i tipi di elementi
			for (int i = 0; i < c; i++) {
				et = "ELEM_TYPE " + s.next();
				ScanElemType scanElemType = new ScanElemType(this.depth + 1);
				aets[i] = scanElemType.scanElemType(et);
			}
			return aets;
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
