package restrizioniSpecifiche;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import restrizioniIstanze.qnElemTypes.ElemTypeNorm;
import restrizioniIstanze.structure.ArchiTypeNorm;
//import restrizioniSpecifiche.secondRelease.Specifiche2;
import specificheAEmilia.AEIdecl;
import specificheAEmilia.AEIident;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.ArchiElemInstances;
import specificheAEmilia.ArchiType;
import specificheAEmilia.ElemType;
import specificheAEmilia.ORinteractions;
import specificheAEmilia.OutputInteractions;
import utilities.ErrorMessage;
import valutazione.normalization.normalizeParts.NormalizeAll;
import valutazione.scope.ScopeArchiType;

public abstract class ASpecificheRules {

	protected ArchiType archiType;
//	protected List<Specifiche2> listaSpecifiche;

	protected boolean errorOccurred;

	protected ErrorMessage errorMessage = new ErrorMessage(1);

	/**
	 * Restituisce true se e solo se outpute'una or-interazione di output di
	 * elemType.
	 *
	 * @param elemType
	 * @param output
	 * @return
	 */
	public static boolean isOrOutput(ElemType elemType, String output) {
		AETinteractions tinteractions = elemType.getInteractions();
		OutputInteractions outputInteractions = tinteractions.getOuIn();
		if (outputInteractions == null)
			return false;
		ORinteractions rinteractions = outputInteractions.getOr();
		if (rinteractions == null)
			return false;
		String[] strings = rinteractions.getActions();
		CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<String>(strings);
		if (copyOnWriteArrayList.contains(output))
			return true;
		else
			return false;
	}
//
//	public List<Specifiche2> getListaSpecifiche() {
//		return listaSpecifiche;
//	}

	/**
	 * Viene utilizzato per la normalizzazione del tipo architetturale.
	 * 
	 * @param archiType2
	 * @return
	 * @throws RestrizioniSpecException
	 */
	protected ArchiType normalizzaTipo(ArchiType archiType2) throws RestrizioniSpecException {
		try {
			ScopeArchiType scopeArchiType = new ScopeArchiType(archiType2, 2);
			if (scopeArchiType.isErrorOccurred()) {
				String string = "Normalizing error for " + archiType2.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = scopeArchiType.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
			NormalizeAll normalizeAll = new NormalizeAll(scopeArchiType, 2);
			ArchiTypeNorm archiType = normalizeAll.normalizeAll();
			if (normalizeAll.isErrorOccurred()) {
				String string = "Normalizing error for " + archiType2.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeAll.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
			}
			return archiType.getNewArchiType();
		} catch (Exception e) {
			throw new RestrizioniSpecException(e);
		}
	}

	/**
	 * Restituisce il nome dell'AET relativo a aeIident e presente in list.
	 *
	 * @return
	 */
	public static String getAETFromAEI(List<AEIdecl> list, AEIident aeIident) {
		// prelevo l'aet di aeIident
		String string2 = null;
		for (AEIdecl idecl : list) {
			AEIident aeIident2 = idecl.getAeIident();
			if (aeIident.equals(aeIident2))
				string2 = idecl.getAET();
		}
		return string2;
	}

	/**
	 * Restituisce una lista di ElemTypeNorm a partire da list, tale che ogni
	 * ElemTypeNorm contenuta ha il tipo di elemento architetturale new con nome
	 * contenuto in list2.
	 *
	 * @param list
	 * @param list2
	 * @return
	 */
	public static List<ElemTypeNorm> getElemTypeNormFromAETs(List<ElemTypeNorm> list, List<String> list2) {
		List<ElemTypeNorm> list3 = new ArrayList<ElemTypeNorm>();
		for (String string : list2) {
			for (ElemTypeNorm elemTypeNorm : list) {
				ElemType elemType = elemTypeNorm.getNewElemType();
				String string2 = elemType.getHeader().getName();
				if (string2.equals(string))
					list3.add(elemTypeNorm);
			}
		}
		return list3;
	}

	/**
	 * Restituisce l'ElemTypeNorm presente in list, che ha come nome di tipo di
	 * elemento architetturale uguale a string.
	 *
	 * @param list
	 * @param string
	 * @return
	 */
	public static ElemTypeNorm getElemTypeFromName(List<ElemTypeNorm> list, String string) {
		// si preleva l'l'ElemTypeNorm relativa a string
		for (ElemTypeNorm elemTypeNorm : list) {
			ElemType elemType2 = elemTypeNorm.getNewElemType();
			String string2 = elemType2.getHeader().getName();
			if (string.equals(string2))
				return elemTypeNorm;
		}
		return null;
	}

	/**
	 * Restituisce i nome degli AET contenuti in list.
	 *
	 * @param list
	 * @return
	 */
	public static List<String> getAETsFromAEIDecls(List<AEIdecl> list) {
		List<String> list2 = new ArrayList<String>();
		for (AEIdecl idecl : list) {
			list2.add(idecl.getAET());
		}
		return list2;
	}

	/**
	 * Restituisce una lista di dichiarazioni di istanze corrispondenti a
	 * collection.
	 *
	 * @param collection
	 * @param archiElemInstances
	 * @return
	 * @throws RestrizioniSpecException
	 */
	public static List<AEIdecl> getAEIsFromNames(Collection<AEIident> collection, ArchiElemInstances archiElemInstances)
			throws RestrizioniSpecException {
		try {
			List<AEIdecl> list2 = new ArrayList<AEIdecl>();
			AEIdecl[] idecls = archiElemInstances.getAEIdeclSeq();
			for (AEIident aeIident : collection) {
				for (int i = 0; i < idecls.length; i++) {
					AEIident aeIident2 = idecls[i].getAeIident();
					if (aeIident.equals(aeIident2)) {
						list2.add(idecls[i]);
					}
				}
			}
			return list2;
		} catch (Exception e) {
			throw new RestrizioniSpecException(e);
		}
	}

	/**
	 * Restituisce la lista di dichiarazioni di istanze del tipo di elemento
	 * architetturale di nome string.
	 *
	 * @param string
	 * @param archiElemInstances
	 * @return
	 * @throws RestrizioniSpecException
	 */
	public static List<AEIdecl> getAEIsFromAET(String string, ArchiElemInstances archiElemInstances)
			throws RestrizioniSpecException {
		try {
			List<AEIdecl> list2 = new ArrayList<AEIdecl>();
			AEIdecl[] idecls = archiElemInstances.getAEIdeclSeq();
			for (int i = 0; i < idecls.length; i++) {
				String string2 = idecls[i].getAET();
				if (string.equals(string2)) {
					list2.add(idecls[i]);
				}
			}
			return list2;
		} catch (Exception e) {
			throw new RestrizioniSpecException(e);
		}
	}

	/**
	 * Restituisce una lista di istanze presenti nelle interazioni di list.
	 *
	 * @param list
	 * @return
	 */
	public static List<AEIident> getInstances(List<Interaction> list) {
		List<AEIident> list2 = new ArrayList<AEIident>();
		for (Interaction interaction : list) {
			AEIident aeIident = interaction.getInstance();
			list2.add(aeIident);
		}
		return list2;
	}

	public ASpecificheRules() {
		super();
	}

	public boolean isErrorOccurred() {
		return errorOccurred;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

}