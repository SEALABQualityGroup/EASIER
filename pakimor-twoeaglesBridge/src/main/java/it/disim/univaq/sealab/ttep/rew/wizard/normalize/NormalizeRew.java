/**
 * 
 */
package it.disim.univaq.sealab.ttep.rew.wizard.normalize;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import it.disim.univaq.sealab.ttep.rew.classes.MeasureDef;
import it.disim.univaq.sealab.ttep.rew.classes.MeasureDefInd;
import it.disim.univaq.sealab.ttep.rew.classes.RewSpec;
import it.disim.univaq.sealab.ttep.rew.wizard.normalize.structure.MeasureDefNorm;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.scope.ScopeArchiType;

/**
 * @author Mirko
 *
 */
public class NormalizeRew {
	private ScopeArchiType scopeArchiType;
	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;

	public NormalizeRew(ScopeArchiType scopeArchiType, int depth) {
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
	}

	public RewSpec normalizeRew(RewSpec rewSpec) throws NormalizeException {
		List<MeasureDef> measureDefs = rewSpec.getMeasureDefs();
		List<MeasureDef> measureDefs2 = new ArrayList<MeasureDef>();
		for (MeasureDef measureDef : measureDefs) {
			if (measureDef instanceof MeasureDefInd) {
				MeasureDefInd measureDefInd = (MeasureDefInd) measureDef;
				NormalizeMeasureDefInd normalizeMeasureDefInd = new NormalizeMeasureDefInd(this.scopeArchiType,
						this.depth + 1);
				List<MeasureDef> measureDefs3 = normalizeMeasureDefInd.normalizeMeasureDefInd(measureDefInd);
				if (normalizeMeasureDefInd.isErrorOccurred()) {
					String string = "Normalizing error for " + measureDefInd.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeMeasureDefInd.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
				}
				measureDefs2.addAll(measureDefs3);
			} else {
				NormalizeMeasureDef normalizeMeasureDef = new NormalizeMeasureDef(this.depth + 1);
				TreeMap<String, ValueIdentExpr> treeMap = this.scopeArchiType.getScope();
				MeasureDefNorm measureDefNorm2 = normalizeMeasureDef.normalizeMeasureDef(measureDef, treeMap);
				if (normalizeMeasureDef.isErrorOccurred()) {
					String string = "Normalizing error for " + measureDef.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeMeasureDef.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
				}
				MeasureDef measureDef2 = measureDefNorm2.getNewMeasureDef();
				measureDefs2.add(measureDef2);
			}
		}
		RewSpec rewSpec2 = new RewSpec(measureDefs2);
		return rewSpec2;
	}

	public boolean isErrorOccurred() {
		return errorOccurred;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
}
