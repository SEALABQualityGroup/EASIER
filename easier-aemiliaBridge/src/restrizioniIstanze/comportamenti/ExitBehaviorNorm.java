/**
 * 
 */
package restrizioniIstanze.comportamenti;

import java.util.HashMap;
import java.util.List;

import specificheAEmilia.AEmiliaBase;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.Expression;

/**
 * @author Almaviva
 *
 */
public abstract class ExitBehaviorNorm 
	implements AEmiliaBase 
	{

	public abstract BehavProcess getBehavProcess();

	public abstract HashMap<String, Double> getProbRoutingMap();

	public abstract List<Expression> getProbRoutingPrios();
	
	}
