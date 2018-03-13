package restrizioniSpecifiche.interfaces;

import restrizioniSpecifiche.RestrizioniSpecException;

/**
 * Interfaccia che contiene il metodo isCompliantSpecificRules, che viene utilizzato per verificare che 
 * ogni oggetto della classe che implementa questa interfaccia soddisfa le restrizioni specifiche. 
 * 
 * @author Mirko
 */
public interface ICompliantSpecificRules 
	{
	/**
	 * Restituisce true se e solo se sono soddisfatte le regole di restrizione specifica.
	 * 
	 * @return
	 * @throws RestrizioniSpecException
	 */
	public boolean isCompliantSpecificRules() 
		throws RestrizioniSpecException;
	
	}
