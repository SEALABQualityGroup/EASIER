/**
 * 
 */
package scanSpecAEmilia.scanIntestazione;

import personalScanner.MyScanner;
import scanSpecAEmilia.ScanException;
import specificheAEmilia.ParamDeclaration;

/**
 * @author Mirko
 *
 */
class ScanDeclParSeq {

	private int depth;

	public ScanDeclParSeq(int depth) 
		{
		super();
		this.depth = depth;
		}


	/**
	 * Scannerizza una sequenza di dichiarazioni di parametri,
	 * generando un array di oggetti ParamDeclaration. Il parametro
	 * void viene sostituito con null.
	 *
	 * @param specifiche - oggetto String.
	 * @return un array di oggetti ParamDeclaration.
	 * @throws ScanException
	 */
	public ParamDeclaration[] scanDeclParSeq(String specifiche)
		throws ScanException
		{
		try {
			ParamDeclaration[] dics = null;
			MyScanner s = new MyScanner(specifiche);
			s.useDelimiter("\\s*\\z");
			if (s.hasNext("\\s*void\\s*")) {
				dics = new ParamDeclaration[1];
				return dics;
			} else {
				s.useDelimiter("\\s*\\,\\s*");
				// si conta il numero di dichiarazioni di parametri
				int cont = 0;
				String string = new String();
				while (s.hasNext()) {
					string = s.next();
					ScanDeclPar scanDeclPar = new ScanDeclPar(this.depth + 1);
					if (!scanDeclPar.isDeclPar(string)) {
						string = string + ",";
						// caso in cui none'stata ancora individuata una dichiarazione di parametro
						// a causa di una possibile dichiarazione di una lista
						while (s.hasNext()) {
							string = string + s.next();
							ScanDeclPar scanDeclPar2 = new ScanDeclPar(this.depth + 1);
							if (!scanDeclPar2.isDeclPar(string)) {
								string = string + ",";
								continue;
							} else {
								break;
							}
						}
						cont++;
					} else
						cont++;
				}
				dics = new ParamDeclaration[cont];
				s = new MyScanner(specifiche);
				s.useDelimiter("\\s*\\,\\s*");
				// si scannerizza la sequenza di dichiarazioni
				// di parametri
				for (int i = 0; i < cont; i++) {
					String st = s.next();
					ScanDeclPar scanDeclPar = new ScanDeclPar(this.depth + 1);
					if (!scanDeclPar.isDeclPar(st)) {
						st = st + ",";
						// caso in cui none'stata ancora individuata una dichiarazione di parametro
						// a causa di una possibile dichiarazione di una lista
						while (s.hasNext()) {
							st = st + s.next();
							ScanDeclPar scanDeclPar2 = new ScanDeclPar(this.depth + 1);
							if (!scanDeclPar2.isDeclPar(st)) {
								st = st + ",";
								continue;
							} else {
								break;
							}
						}
						ScanDeclPar scanDeclPar2 = new ScanDeclPar(this.depth + 1);
						dics[i] = scanDeclPar2.scanDeclPar(st);
					} else
						{
						ScanDeclPar scanDeclPar2 = new ScanDeclPar(this.depth + 1);
						dics[i] = scanDeclPar2.scanDeclPar(st);
						}
				}
				return dics;
			}
			} 
		catch (Exception e) 
			{
			throw new ScanException(e);
			}
		}
}
