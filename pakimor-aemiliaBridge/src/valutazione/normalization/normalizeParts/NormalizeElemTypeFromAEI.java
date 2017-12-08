/**
 * 
 */
package valutazione.normalization.normalizeParts;

import java.util.List;

import restrizioniIstanze.qnElemTypes.ElemTypeNorm;
import restrizioniIstanze.structure.HeaderNorm;
import restrizioniIstanze.structure.ProcessTermNorm;
import specificheAEmilia.AEIdecl;
import specificheAEmilia.AEIident;
import specificheAEmilia.BehavEquation;
import specificheAEmilia.ElemType;
import specificheAEmilia.Header;
import specificheAEmilia.ProcessTerm;
import utilities.ErrorMessage;
import valutazione.Find;
import valutazione.normalization.NormalizeException;
import valutazione.scope.ScopeArchiType;

/**
 * @author Mirko
 *
 */
public class NormalizeElemTypeFromAEI {

	private ScopeArchiType scopeArchiType;
	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;
	
	public NormalizeElemTypeFromAEI(ScopeArchiType scopeArchiType,int depth) 
		{
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}

	/**
	 * Normalizza un tipo di elemento architetturale, a partire
	 * dalla dichiarazione di una sua istanza. Viene normalizzato il termine di processo
	 * solo con lo scope relativo all'intestazione del tipo di elemento architetturale,
	 * cioe' non si considerano gli identificatori presenti nelle intestazioni
	 * delle equazioni comportamentali.
	 *
	 * @param aeid
	 * @return l'elemento architetturale normalizzato
	 * @throws NormalizeException
	 */
	public ElemTypeNorm normalizeElemTypeFromAEI(AEIdecl aeid)
			throws NormalizeException {
			try {
				// si preleva il tipo di elemento architetturale a partire da aeid
				AEIident aeIident = aeid.getAeIident();
				ElemType et = Find.getElemTypeFromAei(aeIident,
						this.scopeArchiType.getAt());
				if (et == null) 
					{
					// 1
					String string = "Normalizing error for " + aeid;
					this.errorMessage.setErrorMessage(string);
					String string2 = aeIident + " have not relative "
							+ "architectural element type";
					ErrorMessage errorMessage = new ErrorMessage(this.depth + 1);
					errorMessage.setErrorMessage(string2);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				ElemType clone = et.copy();
				// si normalizzano i comportamenti del tipo di elemento
				// architetturale
				BehavEquation[] bes = clone.getBehavior().getBehaviors();
				for (int i = 0; i < bes.length; i++) 
					{
					BehavEquation beact = bes[i].copy();
					// si normalizza l'intestazione del comportamento
					NormalizeBehavHeaderFromAEI normalizeBehavHeaderFromAEI = new NormalizeBehavHeaderFromAEI(this.scopeArchiType,this.depth + 1);
					HeaderNorm headerNorm = normalizeBehavHeaderFromAEI.normalizeBehavHeaderFromAEI(aeid,
							beact.getBehavHeader());
					if (normalizeBehavHeaderFromAEI.isErrorOccurred()) 
						{
						// 2
						String string = "Normalizing error for " + aeid;
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeBehavHeaderFromAEI.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					Header header = headerNorm.getNewHeader();
					bes[i].setBehavHeader(header);
					// si normalizza il termine di processo del comportamento
					NormalizeProcessTermFromAEI normalizeProcessTermFromAEI = new NormalizeProcessTermFromAEI(this.scopeArchiType,this.depth + 1);
					ProcessTermNorm processTerm = normalizeProcessTermFromAEI.normalizeProcessTermFromAEI(
							aeid, beact, et);
					if (normalizeProcessTermFromAEI.isErrorOccurred()) 
						{
						// 3
						String string = "Normalizing error for " + aeid;
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeProcessTermFromAEI.getErrorMessage();
						list.add(errorMessage);
						this.errorOccurred = true;
						return null;
						}
					ProcessTerm processTerm2 = processTerm.getNewProcessTerm();
					bes[i].setTermineProcesso(processTerm2);
					}
				ElemTypeNorm elemTypeNorm = new ElemTypeNorm();
				elemTypeNorm.setOldElemType(et);
				elemTypeNorm.setNewElemType(clone);
				elemTypeNorm.setAEIdecl(aeid);
				return elemTypeNorm;
				} 
			catch (Exception e) 
				{
				throw new NormalizeException(e);
				}
			}

	public boolean isErrorOccurred() {
		return errorOccurred;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	
	
}
