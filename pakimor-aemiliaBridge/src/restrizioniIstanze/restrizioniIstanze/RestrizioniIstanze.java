/**
 * 
 */
package restrizioniIstanze.restrizioniIstanze;

import java.util.ArrayList;
import java.util.List;

import restrizioniIstanze.RestrizioniIstanzeException;
import restrizioniIstanze.qnElemTypes.ElemTypeNorm;
import restrizioniIstanze.qnElemTypes.ElemTypeNormFCB;
import restrizioniIstanze.qnElemTypes.ElemTypeNormFPNB;
import restrizioniIstanze.qnElemTypes.ElemTypeNormFPWB;
import restrizioniIstanze.qnElemTypes.ElemTypeNormJP;
import restrizioniIstanze.qnElemTypes.ElemTypeNormRPNB;
import restrizioniIstanze.qnElemTypes.ElemTypeNormRPWB;
import restrizioniIstanze.qnElemTypes.ElemTypeNormSCAP;
import restrizioniIstanze.qnElemTypes.ElemTypeNormSPNB;
import restrizioniIstanze.qnElemTypes.ElemTypeNormSPWB;
import restrizioniIstanze.qnElemTypes.ElemTypeNormUB;
import restrizioniIstanze.qnElemTypes.ElemTypeNormUPAP;
import specificheAEmilia.AEIdecl;
import specificheAEmilia.ArchiElemInstances;
import specificheAEmilia.ArchiInteractions;
import specificheAEmilia.ArchiTopology;
import specificheAEmilia.ArchiType;
import specificheAEmilia.ElemType;
import specificheAEmilia.Header;
import specificheAEmilia.InteractionDecl;
import utilities.ErrorMessage;
import valutazione.normalization.normalizeParts.NormalizeElemTypeFromAEI;
import valutazione.scope.ScopeArchiType;
import equivalenzaComportamentale.secondRelease.Equivalenza2;
import equivalenzaComportamentale.secondRelease.EquivalenzaArriviFiniti2;
import equivalenzaComportamentale.secondRelease.EquivalenzaArriviInfiniti2;
import equivalenzaComportamentale.secondRelease.EquivalenzaBufferIllimitato2;
import equivalenzaComportamentale.secondRelease.EquivalenzaBufferLimitato2;
import equivalenzaComportamentale.secondRelease.EquivalenzaForkConBuffer2;
import equivalenzaComportamentale.secondRelease.EquivalenzaForkSenzaBuffer2;
import equivalenzaComportamentale.secondRelease.EquivalenzaJoin2;
import equivalenzaComportamentale.secondRelease.EquivalenzaRoutingConBuffer2;
import equivalenzaComportamentale.secondRelease.EquivalenzaRoutingSenzaBuffer2;
import equivalenzaComportamentale.secondRelease.EquivalenzaServizioConBuffer2;
import equivalenzaComportamentale.secondRelease.EquivalenzaServizioSenzaBuffer2;

/**
 * @author Mirko
 *
 */
public class RestrizioniIstanze {

	protected ArchiType archiType;

	protected boolean errorOccurred;

	private ErrorMessage errorMessage = new ErrorMessage(1);

	private List<ElemTypeNorm> elemTypeNormList;

	private ScopeArchiType scopeArchiType;

	public RestrizioniIstanze(List<Equivalenza2> list, ArchiType archiType, boolean qnCheck)
			throws RestrizioniIstanzeException {
		/* MODELED */
		super();
		try {
			NormalizzaTipo normalizzaTipo = new NormalizzaTipo();
			// si normalizza il tipo architetturale
			this.archiType = normalizzaTipo.normalizzaTipo(archiType);
			if (normalizzaTipo.isErrorOccurred()) {
				// 1
				String string = "Generating instances error for " + archiType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list3 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizzaTipo.getErrorMessage();
				list3.add(errorMessage);
				this.errorOccurred = true;
				return;
			}
			ScopeArchiType scopeArchiType = normalizzaTipo.getScopeArchiType();
			this.scopeArchiType = scopeArchiType;
			// verifichiamo che non ci siano interazioni architetturali
			ArchiTopology archiTopology = this.archiType.getTopologia();
			ArchiInteractions archiInteractions = archiTopology.getArchiInteractions();
			InteractionDecl[] interactionDecls = archiInteractions.getInteractions();
			if (qnCheck) {
				if (interactionDecls != null) {
					// 2
					String string = "Generating instances error for " + archiType.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list3 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = new ErrorMessage(2);
					String string2 = "there are architectural interactions declarations";
					errorMessage.setErrorMessage(string2);
					list3.add(errorMessage);
					this.errorOccurred = true;
					return;
				}
			}
			// per istanziare gli oggetti Specifiche si costruisce la lista di tutti gli
			// oggetti
			// IEquivalenza, wrappando ogni istanza di un tipo di elemento architetturale
			// dichiarato
			ArchiElemInstances archiElemInstances = archiTopology.getAEIs();
			AEIdecl[] idecls = archiElemInstances.getAEIdeclSeq();
			NormalizeElemTypeFromAEI normalizeElemTypeFromAEI = new NormalizeElemTypeFromAEI(scopeArchiType, 2);
			List<ElemTypeNorm> list2 = new ArrayList<ElemTypeNorm>();
			for (AEIdecl idecl : idecls) {
				ElemTypeNorm elemType;
				elemType = normalizeElemTypeFromAEI.normalizeElemTypeFromAEI(idecl);
				if (normalizeElemTypeFromAEI.isErrorOccurred()) {
					// 3
					String string = "Generating instances error for " + idecl.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list3 = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeElemTypeFromAEI.getErrorMessage();
					list3.add(errorMessage);
					this.errorOccurred = true;
					return;
				}
				String string = idecl.getAET();
				if (qnCheck) {
					for (Equivalenza2 equivalenza : list) {
						ElemType elemType2 = equivalenza.getEt();
						Header header = elemType2.getHeader();
						String string2 = header.getName();
						if (string.equals(string2)) {
							if (equivalenza instanceof EquivalenzaArriviFiniti2) {
								EquivalenzaArriviFiniti2 equivalenzaArriviFiniti2 = (EquivalenzaArriviFiniti2) equivalenza;
								ElemTypeNormSCAP elemTypeNormSCAP = new ElemTypeNormSCAP(elemType, 2);
								elemTypeNormSCAP.setEquivalenzaArriviFiniti2(equivalenzaArriviFiniti2);
								elemType = elemTypeNormSCAP;
							} else if (equivalenza instanceof EquivalenzaArriviInfiniti2) {
								EquivalenzaArriviInfiniti2 equivalenzaArriviInfiniti2 = (EquivalenzaArriviInfiniti2) equivalenza;
								ElemTypeNormUPAP elemTypeNormUPAP = new ElemTypeNormUPAP(elemType, 2);
								elemTypeNormUPAP.setEquivalenzaArriviInfiniti2(equivalenzaArriviInfiniti2);
								elemType = elemTypeNormUPAP;
							} else if (equivalenza instanceof EquivalenzaBufferIllimitato2) {
								EquivalenzaBufferIllimitato2 equivalenzaBufferIllimitato2 = (EquivalenzaBufferIllimitato2) equivalenza;
								ElemTypeNormUB elemTypeNormUB = new ElemTypeNormUB(elemType, 2);
								elemTypeNormUB.setEquivalenzaBufferIllimitato2(equivalenzaBufferIllimitato2);
								elemType = elemTypeNormUB;
							} else if (equivalenza instanceof EquivalenzaBufferLimitato2) {
								EquivalenzaBufferLimitato2 equivalenzaBufferLimitato2 = (EquivalenzaBufferLimitato2) equivalenza;
								ElemTypeNormFCB elemTypeNormFCB = new ElemTypeNormFCB(elemType, 2);
								elemTypeNormFCB.setEquivalenzaBufferLimitato2(equivalenzaBufferLimitato2);
								elemType = elemTypeNormFCB;
							} else if (equivalenza instanceof EquivalenzaForkConBuffer2) {
								EquivalenzaForkConBuffer2 equivalenzaForkConBuffer2 = (EquivalenzaForkConBuffer2) equivalenza;
								ElemTypeNormFPWB elemTypeNormFPWB = new ElemTypeNormFPWB(elemType, 2);
								elemTypeNormFPWB.setEquivalenzaForkConBuffer2(equivalenzaForkConBuffer2);
								elemType = elemTypeNormFPWB;
							} else if (equivalenza instanceof EquivalenzaForkSenzaBuffer2) {
								EquivalenzaForkSenzaBuffer2 equivalenzaForkSenzaBuffer2 = (EquivalenzaForkSenzaBuffer2) equivalenza;
								ElemTypeNormFPNB elemTypeNormFPNB = new ElemTypeNormFPNB(elemType, 2);
								elemTypeNormFPNB.setEquivalenzaForkSenzaBuffer2(equivalenzaForkSenzaBuffer2);
								elemType = elemTypeNormFPNB;
							} else if (equivalenza instanceof EquivalenzaJoin2) {
								EquivalenzaJoin2 equivalenzaJoin2 = (EquivalenzaJoin2) equivalenza;
								ElemTypeNormJP elemTypeNormJP = new ElemTypeNormJP(elemType, 2);
								elemTypeNormJP.setEquivalenzaJoin2(equivalenzaJoin2);
								elemType = elemTypeNormJP;
							} else if (equivalenza instanceof EquivalenzaRoutingConBuffer2) {
								EquivalenzaRoutingConBuffer2 equivalenzaRoutingConBuffer2 = (EquivalenzaRoutingConBuffer2) equivalenza;
								ElemTypeNormRPWB elemTypeNormRPWB = new ElemTypeNormRPWB(elemType, 2);
								elemTypeNormRPWB.setEquivalenzaRoutingConBuffer2(equivalenzaRoutingConBuffer2);
								elemType = elemTypeNormRPWB;
							} else if (equivalenza instanceof EquivalenzaRoutingSenzaBuffer2) {
								EquivalenzaRoutingSenzaBuffer2 equivalenzaRoutingSenzaBuffer2 = (EquivalenzaRoutingSenzaBuffer2) equivalenza;
								ElemTypeNormRPNB elemTypeNormRPNB = new ElemTypeNormRPNB(elemType, 2);
								elemTypeNormRPNB.setEquivalenzaRoutingSenzaBuffer2(equivalenzaRoutingSenzaBuffer2);
								elemType = elemTypeNormRPNB;
							} else if (equivalenza instanceof EquivalenzaServizioConBuffer2) {
								EquivalenzaServizioConBuffer2 equivalenzaServizioConBuffer2 = (EquivalenzaServizioConBuffer2) equivalenza;
								ElemTypeNormSPWB elemTypeNormSPWB = new ElemTypeNormSPWB(elemType, 2);
								elemTypeNormSPWB.setEquivalenzaServizioConBuffer2(equivalenzaServizioConBuffer2);
								elemType = elemTypeNormSPWB;
							} else if (equivalenza instanceof EquivalenzaServizioSenzaBuffer2) {
								EquivalenzaServizioSenzaBuffer2 equivalenzaServizioSenzaBuffer2 = (EquivalenzaServizioSenzaBuffer2) equivalenza;
								ElemTypeNormSPNB elemTypeNormSPNB = new ElemTypeNormSPNB(elemType, 2);
								elemTypeNormSPNB.setEquivalenzaServizioSenzaBuffer2(equivalenzaServizioSenzaBuffer2);
								elemType = elemTypeNormSPNB;
							}
						}
					}
				}
				list2.add(elemType);
			}
			this.elemTypeNormList = list2;
			InstanceNamesCheck instanceNamesCheck = new InstanceNamesCheck(this.elemTypeNormList, list, 2);
			if (!instanceNamesCheck.instanceNamesCheck()) {
				// 4
				String string = "Generating instances error for " + archiType.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list3 = this.errorMessage.getCauses();
				ErrorMessage errorMessage = instanceNamesCheck.getErrorMessage();
				list3.add(errorMessage);
				return;
			}
		} catch (Exception e) {
			throw new RestrizioniIstanzeException(e);
		}
	}

	public boolean isErrorOccurred() {
		return errorOccurred;
	}

	public List<ElemTypeNorm> getElemTypeNormList() {
		return elemTypeNormList;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public ScopeArchiType getScopeArchiType() {
		return scopeArchiType;
	}

	public ArchiType getArchiType() {
		return archiType;
	}
}
