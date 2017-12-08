/**
 * 
 */
package specificheAEmilia;


/**
 * Rappresenta un record. In AEmilia ha la seguente sintassi:
 * <pre>
 * <code>
 * "record" "(" &lt;field_decl_sequence&gt; ")", 
 * </code>
 * </pre>
 * dove &lt;field_decl_sequence&gt;e'una sequenza non vuota
 * di dichiarazioni di campi separati da virgole, ognuna
 * corrispondente ad una dichiarazione di variabile.
 * Da notaree'che per le operazioni di get e put dobbiamo rappresentare un record tramite identificatore
 * 
 * @author Mirko
 *
 */
public class RecordType 
	extends NormalType 
	{

	private VariableDeclaration[] variableDeclarations;

	public VariableDeclaration[] getVariableDeclarations() 
		{
		return variableDeclarations;
		}

	public void setVariableDeclarations(
			VariableDeclaration[] variableDeclarations) 
		{
		this.variableDeclarations = variableDeclarations;
		}

	@Override
	public RecordType copy() 
		{
		RecordType recordType = new RecordType();
		if (getVariableDeclarations() != null)
			{
			recordType.setVariableDeclarations(new VariableDeclaration[getVariableDeclarations().length]);
			for (int i = 0; i < getVariableDeclarations().length; i++)
				{
				recordType.getVariableDeclarations()[i] = getVariableDeclarations()[i].copy();
				}
			}
		return recordType;
		}

	@Override
	public void print() 
		{
		System.out.println("RecordType object");
		super.print();
		System.out.println("Field declarations:");
		// stampa le dichiarazioni dei campi
		for (int i=0; i < getVariableDeclarations().length; i++)
			{
			System.out.print("Field declaration number ");
			System.out.print(i);
			System.out.println(":");
			getVariableDeclarations()[i].print();
			}
		}

	@Override
	public boolean equals(Object arg0) 
		{
		if (!(arg0 instanceof RecordType)) return false;
		RecordType recordType = (RecordType)arg0;
		boolean ris = true;
		// confronta le dichiarazioni di campi,
		// cosiderando anche il caso in cui non ci sono
		// dichiarazioni per i due oggetti confrontati
		if (getVariableDeclarations() != null && recordType.getVariableDeclarations() != null)
			{
			if (getVariableDeclarations().length == recordType.getVariableDeclarations().length)
				{
				for (int i = 0; i < getVariableDeclarations().length; i++)
					{
					ris = ris &&
						getVariableDeclarations()[i].equals(recordType.getVariableDeclarations()[i]);
					}
				return ris;
				}
			else return false;
			}
		else if (getVariableDeclarations() == null && recordType.getVariableDeclarations() == null)
			return ris;
		else return false;
		}
	
	@Override
	public String toString() 
		{
		String string = "record (";
		for (int i = 0; i < getVariableDeclarations().length - 1; i++)
			{
			string = string + getVariableDeclarations()[i].toString() + ",";
			}
		string = string + getVariableDeclarations()[getVariableDeclarations().length - 1].toString() + ")";
		return string;
		}
	}
