package scanSpecAEmilia;

import java.util.List;
import java.util.NoSuchElementException;

import personalScanner.MyScanner;
import scanSpecAEmilia.scanArchiAttac.ScanArchiAttac;
import scanSpecAEmilia.scanArchiElemInstances.ScanArchiElemInstances;
import scanSpecAEmilia.scanArchiInteractions.ScanArchiInteractions;
import specificheAEmilia.ArchiAttachments;
import specificheAEmilia.ArchiElemInstances;
import specificheAEmilia.ArchiInteractions;
import specificheAEmilia.ArchiTopology;
import utilities.ErrorMessage;


/**
 * Classe utilizzata per scannerizzare ogni parte della
 * dichiarazione della tipologia di un tipo architetturale,
 * dettata dalla grammatica AEmilia.
 *
 * @author Mirko Email: <a href="mailto:mirkoflamminj@virgilio.it">mirkoflamminj@virgilio.it</a>
 * @version 1.0
 */

/*
 * La seconda sezione di una specifica AEmilia ha la seguente
 * sintassi:
 *
 * "ARCHI_TOPOLOGY" <AEIs> <architectural_interactions> <architectural_attachments>
 *
 * <AEIs>e'la dichiarazione delle Istanze di elementi
 * architetturali ed ha la seguente forma:
 *
 *  "ARCHI_ELEM_INSTANCES" <AEI_decl_sequence>
 *
 *  dove <AEI_decl_sequence>e'una sequenza non vuota di
 *  dichiarazioni AEI  separate da punti e virgola, ognuna delle
 *  seguenti forme:
 *
 *  <AEI_decl> ::= <identifier> ["[" <expr> "]"] ":" <identifier> "(" <pe_expr_sequence> ")"
 *  | "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
 *  <identifier> "[" <expr> "]" ":" <identifier> "(" <pe_expr_sequence> ")"
 *
 *  <architectural_interactions> ha la seguente sintassi:
 *
 *  "ARCHI_INTERACTIONS" <pe_architectural_interaction_decl>
 *
 *  dove <pe architectural interaction decl>e'o void o una
 *  sequenza non vuota di  dichiarazioni di interazioni
 *  architetturali separate da punti e virgola,  ognuna della
 *  seguente forma:
 *
 *  <architectural_interaction_decl> ::= <identifier> ["[" <expr> "]"] "." <identifier>
 *  | "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
 *  <identifier> "[" <expr> "]" "." <identifier>
 *
 *  <architectural_attachments> ha la seguente sintassi:
 *
 *  "ARCHI_ATTACHMENTS" <pe_architectural_attachment_decl>
 *
 *  dove <pe_architectural_attachment_decl>e'o void o una
 *  sequenza non vuota di  dichiarazioni di legami architetturali
 *  separate da punti e virgola, ognuna della seguente forma:
 *
 *  <architectural_attachment_decl> ::= "FROM" <identifier> ["[" <expr> "]"] "." <identifier>
 *  "TO" <identifier> ["[" <expr> "]"] "." <identifier>
 *  | "FOR_ALL" <identifier> "IN" <expr> ".." <expr>
 *  ["AND" "FOR_ALL" <identifier> "IN" <expr> ".." <expr>]
 *  "FROM" <identifier> ["[" <expr> "]"] "." <identifier>
 *  "TO" <identifier> ["[" <expr> "]"] "." <identifier>
 *
 */

public class ScanArchiTopology {

	private int depth;
	private ErrorMessage errorMessage;
	
	public ScanArchiTopology(int depth) 
		{
		super();
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Restituisce true se e solo se specifiche corrisponde
	 * alla dichiarazione della tipologia di un tipo architetturale
	 * secondo la grammatica AEmilia.
	 * @param specifiche - oggetto String.
	 * @return un valore booleano.
	 */

	public boolean isArchiTopology(String specifiche)
		{
		String aeis = new String();
		String archiInter = new String();
		String archiAttac = new String();
		MyScanner s = new MyScanner(specifiche);
		try {
			s.skip("\\s*ARCHI_TOPOLOGY\\s*");
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of architectural topology";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "\"ARCHI_TOPOLOGY\" expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		s.useDelimiter("\\s*ARCHI_INTERACTIONS\\s*");
		// aeise'una sequenza di istanze di elementi
		// architetturali
		try {
			aeis = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of architectural topology";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "sequence of architectural element instaces declarations expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		s.useDelimiter("\\s*ARCHI_ATTACHMENTS\\s*");
		// archiIntere'una sequenza di interazioni
		// architetturali
		try {
			archiInter = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of architectural topology";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "sequence of architectural interactions declarations expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		s.useDelimiter("\\s*\\z");
		// archiAttace'una sequenza di collegamenti
		// tra elementi architetturali
		try {
			archiAttac = s.next();
			}
		catch (NoSuchElementException noSuchElementException)
			{
			String string = specifiche + " is not declaration of architectural topology";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage2 = new ErrorMessage(this.depth + 1);
			String string2 = "sequence of architectural attachments declarations expected";
			errorMessage2.setErrorMessage(string2);
			list.add(errorMessage2);
			return false;					
			}
		ScanArchiElemInstances scanArchiElemInstances = new ScanArchiElemInstances(this.depth + 1);
		ScanArchiInteractions scanArchiInteractions = new ScanArchiInteractions(this.depth + 1);
		ScanArchiAttac scanArchiAttac = new ScanArchiAttac(this.depth + 1);
		if (!scanArchiElemInstances.isArchiElemInstances(aeis))
			{
			String string = specifiche + " is not declaration of architectural topology";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanArchiElemInstances.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		if (!scanArchiInteractions.isArchiInteractions(archiInter))
			{
			String string = specifiche + " is not declaration of architectural topology";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanArchiInteractions.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		if (!scanArchiAttac.isArchiAttac(archiAttac))
			{
			String string = specifiche + " is not declaration of architectural topology";
			this.errorMessage.setErrorMessage(string);
			List<ErrorMessage> list = this.errorMessage.getCauses();
			ErrorMessage errorMessage = scanArchiAttac.getErrorMessage();
			list.add(errorMessage);
			return false;
			}
		return true;
		}

	/**
	 * Crea un oggetto ArchiTopology, includendo informazioni
	 * ottenute attraverso la scannerizzazione di specifiche.
	 * @param specifiche - oggetto String.
	 * @return un oggetto ArchiTopology.
	 * @throws ScanException
	 */
	public ArchiTopology scanArchiTopology(String specifiche)
		throws ScanException
		{
		try {
			ArchiElemInstances aeio = new ArchiElemInstances();
			ArchiInteractions archiIntero = new ArchiInteractions();
			ArchiAttachments archiAttaco = new ArchiAttachments();
			ArchiTopology at = new ArchiTopology();
			String aeis = new String();
			String archiInters = new String();
			String archiAttacs = new String();
			MyScanner s = new MyScanner(specifiche);
			s.skip("\\s*ARCHI_TOPOLOGY\\s*");
			s.useDelimiter("\\s*ARCHI_INTERACTIONS\\s*");
			// aeise'una sequenza di istanze di elementi
			// architetturali
			aeis = s.next();
			s.useDelimiter("\\s*ARCHI_ATTACHMENTS\\s*");
			// archiInterse'una sequenza di interazioni
			// architetturali
			archiInters = s.next();
			s.useDelimiter("\\s*\\z");
			// archiAttacse'una sequenza di collegamenti
			// tra elementi architetturali
			archiAttacs = s.next();
			ScanArchiElemInstances scanArchiElemInstances = new ScanArchiElemInstances(this.depth + 1);
			ScanArchiInteractions scanArchiInteractions = new ScanArchiInteractions(this.depth + 1);
			ScanArchiAttac scanArchiAttac = new ScanArchiAttac(this.depth + 1);
			aeio = scanArchiElemInstances.scanArchiElemInstances(aeis);
			archiIntero = scanArchiInteractions
					.scanArchiInteractions(archiInters);
			archiAttaco = scanArchiAttac.scanArchiAttac(archiAttacs);
			at.setAEIs(aeio);
			at.setArchiInteractions(archiIntero);
			at.setAttachments(archiAttaco);
			return at;
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
