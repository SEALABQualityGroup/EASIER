/**
 */
package logicalSpecification.antipatterns.performance;

import logicalSpecification.MultipleValuedParameter;
import logicalSpecification.SingleValuedParameter;

import org.eclipse.uml2.uml.Operation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ep PA</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link logicalSpecification.antipatterns.performance.EpPA#getF_overDemandMVP <em>Fover Demand MVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.EpPA#getF_underDemandMVP <em>Funder Demand MVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.EpPA#getF_overDemandProbSVP <em>Fover Demand Prob SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.EpPA#getF_demandSumProbSVP <em>Fdemand Sum Prob SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.EpPA#getF_underDemandProbSVP <em>Funder Demand Prob SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.EpPA#getF_hwUtilSVP <em>Fhw Util SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.EpPA#getF_serviceResponseTimeSVP <em>Fservice Response Time SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.EpPA#getT_maxOverDemandMVP <em>Tmax Over Demand MVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.EpPA#getT_minUnderDemandMVP <em>Tmin Under Demand MVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.EpPA#getT_demandsProbSVP <em>Tdemands Prob SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.EpPA#getT_maxHwUtilSVP <em>Tmax Hw Util SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.EpPA#getT_maxServiceResponseTimeSVP <em>Tmax Service Response Time SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.EpPA#getContextualElement <em>Contextual Element</em>}</li>
 * </ul>
 *
 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getEpPA()
 * @model
 * @generated
 */
public interface EpPA extends PerformanceAntipattern {
	/**
	 * Returns the value of the '<em><b>Fover Demand MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fover Demand MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fover Demand MVP</em>' reference.
	 * @see #setF_overDemandMVP(MultipleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getEpPA_F_overDemandMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getF_overDemandMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.EpPA#getF_overDemandMVP <em>Fover Demand MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fover Demand MVP</em>' reference.
	 * @see #getF_overDemandMVP()
	 * @generated
	 */
	void setF_overDemandMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Funder Demand MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Funder Demand MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Funder Demand MVP</em>' reference.
	 * @see #setF_underDemandMVP(MultipleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getEpPA_F_underDemandMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getF_underDemandMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.EpPA#getF_underDemandMVP <em>Funder Demand MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funder Demand MVP</em>' reference.
	 * @see #getF_underDemandMVP()
	 * @generated
	 */
	void setF_underDemandMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Fover Demand Prob SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fover Demand Prob SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fover Demand Prob SVP</em>' reference.
	 * @see #setF_overDemandProbSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getEpPA_F_overDemandProbSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getF_overDemandProbSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.EpPA#getF_overDemandProbSVP <em>Fover Demand Prob SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fover Demand Prob SVP</em>' reference.
	 * @see #getF_overDemandProbSVP()
	 * @generated
	 */
	void setF_overDemandProbSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Fdemand Sum Prob SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fdemand Sum Prob SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fdemand Sum Prob SVP</em>' reference.
	 * @see #setF_demandSumProbSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getEpPA_F_demandSumProbSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getF_demandSumProbSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.EpPA#getF_demandSumProbSVP <em>Fdemand Sum Prob SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fdemand Sum Prob SVP</em>' reference.
	 * @see #getF_demandSumProbSVP()
	 * @generated
	 */
	void setF_demandSumProbSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Funder Demand Prob SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Funder Demand Prob SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Funder Demand Prob SVP</em>' reference.
	 * @see #setF_underDemandProbSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getEpPA_F_underDemandProbSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getF_underDemandProbSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.EpPA#getF_underDemandProbSVP <em>Funder Demand Prob SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funder Demand Prob SVP</em>' reference.
	 * @see #getF_underDemandProbSVP()
	 * @generated
	 */
	void setF_underDemandProbSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Fhw Util SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fhw Util SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fhw Util SVP</em>' reference.
	 * @see #setF_hwUtilSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getEpPA_F_hwUtilSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getF_hwUtilSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.EpPA#getF_hwUtilSVP <em>Fhw Util SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fhw Util SVP</em>' reference.
	 * @see #getF_hwUtilSVP()
	 * @generated
	 */
	void setF_hwUtilSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Fservice Response Time SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fservice Response Time SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fservice Response Time SVP</em>' reference.
	 * @see #setF_serviceResponseTimeSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getEpPA_F_serviceResponseTimeSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getF_serviceResponseTimeSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.EpPA#getF_serviceResponseTimeSVP <em>Fservice Response Time SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fservice Response Time SVP</em>' reference.
	 * @see #getF_serviceResponseTimeSVP()
	 * @generated
	 */
	void setF_serviceResponseTimeSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Tmax Over Demand MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tmax Over Demand MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tmax Over Demand MVP</em>' reference.
	 * @see #setT_maxOverDemandMVP(MultipleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getEpPA_T_maxOverDemandMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getT_maxOverDemandMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.EpPA#getT_maxOverDemandMVP <em>Tmax Over Demand MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tmax Over Demand MVP</em>' reference.
	 * @see #getT_maxOverDemandMVP()
	 * @generated
	 */
	void setT_maxOverDemandMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Tmin Under Demand MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tmin Under Demand MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tmin Under Demand MVP</em>' reference.
	 * @see #setT_minUnderDemandMVP(MultipleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getEpPA_T_minUnderDemandMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getT_minUnderDemandMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.EpPA#getT_minUnderDemandMVP <em>Tmin Under Demand MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tmin Under Demand MVP</em>' reference.
	 * @see #getT_minUnderDemandMVP()
	 * @generated
	 */
	void setT_minUnderDemandMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Tdemands Prob SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tdemands Prob SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tdemands Prob SVP</em>' reference.
	 * @see #setT_demandsProbSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getEpPA_T_demandsProbSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getT_demandsProbSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.EpPA#getT_demandsProbSVP <em>Tdemands Prob SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tdemands Prob SVP</em>' reference.
	 * @see #getT_demandsProbSVP()
	 * @generated
	 */
	void setT_demandsProbSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Tmax Hw Util SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tmax Hw Util SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tmax Hw Util SVP</em>' reference.
	 * @see #setT_maxHwUtilSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getEpPA_T_maxHwUtilSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getT_maxHwUtilSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.EpPA#getT_maxHwUtilSVP <em>Tmax Hw Util SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tmax Hw Util SVP</em>' reference.
	 * @see #getT_maxHwUtilSVP()
	 * @generated
	 */
	void setT_maxHwUtilSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Tmax Service Response Time SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tmax Service Response Time SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tmax Service Response Time SVP</em>' reference.
	 * @see #setT_maxServiceResponseTimeSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getEpPA_T_maxServiceResponseTimeSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getT_maxServiceResponseTimeSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.EpPA#getT_maxServiceResponseTimeSVP <em>Tmax Service Response Time SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tmax Service Response Time SVP</em>' reference.
	 * @see #getT_maxServiceResponseTimeSVP()
	 * @generated
	 */
	void setT_maxServiceResponseTimeSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Contextual Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contextual Element</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contextual Element</em>' attribute.
	 * @see #setContextualElement(Operation)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getEpPA_ContextualElement()
	 * @model dataType="logicalSpecification.actions.UML.Operation" required="true"
	 * @generated
	 */
	Operation getContextualElement();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.EpPA#getContextualElement <em>Contextual Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contextual Element</em>' attribute.
	 * @see #getContextualElement()
	 * @generated
	 */
	void setContextualElement(Operation value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model T_maxOverDemandRequired="true" T_minUnderDemandRequired="true" T_demandsProbRequired="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='List&lt;Parameter&gt; blobParams = new ArrayList&lt;&gt;();\n\n\t\tsetF_overDemandMVP(Manager.getInstance(null).createMultipleValuedParameter(\n\t\t\t\tOclStringManager.getInstance(null).getOverDemandQuery(getContextualElement())));\n\t\tblobParams.add(getF_overDemandMVP());\n\n\t\tsetT_maxOverDemandMVP(Manager.getInstance(null).createMultipleValuedParameter(String.valueOf(T_maxOverDemand)));\n\t\tblobParams.add(getT_maxOverDemandMVP());\n\n\t\tsetF_underDemandMVP(Manager.getInstance(null).createMultipleValuedParameter(\n\t\t\t\tOclStringManager.getInstance(null).getUnderDemandQuery(getContextualElement())));\n\t\tblobParams.add(getF_underDemandMVP());\n\n\t\tsetT_minUnderDemandMVP(\n\t\t\t\tManager.getInstance(null).createMultipleValuedParameter(String.valueOf(T_minUnderDemand)));\n\t\tblobParams.add(getT_minUnderDemandMVP());\n\n\t\tsetF_overDemandProbSVP(Manager.getInstance(null).createSingleValueParameter(\n\t\t\t\tOclStringManager.getInstance(null).getOverDemandProbQuery(getContextualElement())));\n\t\tblobParams.add(getF_overDemandProbSVP());\n\n\t\tsetF_underDemandProbSVP(Manager.getInstance(null).createSingleValueParameter(\n\t\t\t\tOclStringManager.getInstance(null).getUnderDemandProbQuery(getContextualElement())));\n\t\tblobParams.add(getF_underDemandProbSVP());\n\n\t\tsetT_demandsProbSVP(Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_demandsProb)));\n\t\tblobParams.add(getT_demandsProbSVP());\n\n\t\tif (getF_hwUtilSVP() != null)\n\t\t\tif (getF_hwUtilSVP().getResolvingExpr() != null || !getF_hwUtilSVP().getResolvingExpr().equals(\"\"))\n\t\t\t\tgetF_hwUtilSVP().setResolvingExpr(null);\n\n\t\tif (getT_maxHwUtilSVP() != null)\n\t\t\tif (getT_maxHwUtilSVP().getResolvingExpr() != null || !getT_maxHwUtilSVP().getResolvingExpr().equals(\"\"))\n\t\t\t\tgetT_maxHwUtilSVP().setResolvingExpr(null);\n\n\t\tif (getF_serviceResponseTimeSVP() != null)\n\t\t\tif (getF_serviceResponseTimeSVP().getResolvingExpr() != null\n\t\t\t\t\t|| !getF_serviceResponseTimeSVP().getResolvingExpr().equals(\"\"))\n\t\t\t\tgetF_serviceResponseTimeSVP().setResolvingExpr(null);\n\n\t\tif (getT_maxServiceResponseTimeSVP() != null)\n\t\t\tif (getT_maxServiceResponseTimeSVP().getResolvingExpr() != null\n\t\t\t\t\t|| !getT_maxServiceResponseTimeSVP().getResolvingExpr().equals(\"\"))\n\t\t\t\tgetT_maxServiceResponseTimeSVP().setResolvingExpr(null);\n\n\t\tgetParameters().addAll(blobParams);'"
	 * @generated
	 */
	void setParametersForPartialDetection(double T_maxOverDemand, double T_minUnderDemand, double T_demandsProb);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model T_maxOverDemandRequired="true" T_minUnderDemandRequired="true" T_demandsProbRequired="true" T_maxHwUtilRequired="true" T_maxServiceResponseTimeRequired="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='List&lt;Parameter&gt; blobParams = new ArrayList&lt;&gt;();\n\n\t\tsetF_overDemandMVP(Manager.getInstance(null).createMultipleValuedParameter(\n\t\t\t\tOclStringManager.getInstance(null).getOverDemandQuery(getContextualElement())));\n\t\tblobParams.add(getF_overDemandMVP());\n\n\t\tsetT_maxOverDemandMVP(Manager.getInstance(null).createMultipleValuedParameter(String.valueOf(T_maxOverDemand)));\n\t\tblobParams.add(getT_maxOverDemandMVP());\n\n\t\tsetF_underDemandMVP(Manager.getInstance(null).createMultipleValuedParameter(\n\t\t\t\tOclStringManager.getInstance(null).getUnderDemandQuery(getContextualElement())));\n\t\tblobParams.add(getF_underDemandMVP());\n\n\t\tsetT_minUnderDemandMVP(\n\t\t\t\tManager.getInstance(null).createMultipleValuedParameter(String.valueOf(T_minUnderDemand)));\n\t\tblobParams.add(getT_minUnderDemandMVP());\n\n\t\tsetF_overDemandProbSVP(Manager.getInstance(null).createSingleValueParameter(\n\t\t\t\tOclStringManager.getInstance(null).getOverDemandProbQuery(getContextualElement())));\n\t\tblobParams.add(getF_overDemandProbSVP());\n\n\t\tsetF_underDemandProbSVP(Manager.getInstance(null).createSingleValueParameter(\n\t\t\t\tOclStringManager.getInstance(null).getUnderDemandProbQuery(getContextualElement())));\n\t\tblobParams.add(getF_underDemandProbSVP());\n\n\t\tsetT_demandsProbSVP(Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_demandsProb)));\n\t\tblobParams.add(getT_demandsProbSVP());\n\n\t\tsetF_hwUtilSVP(Manager.getInstance(null)\n\t\t\t\t.createSingleValueParameter(OclStringManager.getInstance(null).getHwUtilQuery(getContextualElement())));\n\t\tblobParams.add(getF_hwUtilSVP());\n\n\t\tsetT_maxHwUtilSVP(Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxHwUtil)));\n\t\tblobParams.add(getT_maxHwUtilSVP());\n\n\t\tsetF_serviceResponseTimeSVP(Manager.getInstance(null).createSingleValueParameter(\n\t\t\t\tOclStringManager.getInstance(null).getServiceResponseTimeQuery(getContextualElement())));\n\t\tblobParams.add(getF_serviceResponseTimeSVP());\n\n\t\tsetT_maxServiceResponseTimeSVP(\n\t\t\t\tManager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxServiceResponseTime)));\n\t\tblobParams.add(getT_maxServiceResponseTimeSVP());\n\n\t\tgetParameters().addAll(blobParams);'"
	 * @generated
	 */
	void setParametersForFullDetection(double T_maxOverDemand, double T_minUnderDemand, double T_demandsProb, double T_maxHwUtil, double T_maxServiceResponseTime);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='FOLSpecification epCF = LogicalSpecificationFactory.eINSTANCE.createFOLSpecification();\n\t\tepCF.setName(\"EpFormula\");\n\n\t\tAndOperator epCFAnd = LogicalSpecificationFactory.eINSTANCE.createAndOperator();\n\n\t\tGreaterEqualOperator epAndGeq = LogicalSpecificationFactory.eINSTANCE.createGreaterEqualOperator();\n\t\tepAndGeq.setLhs(getF_overDemandMVP());\n\t\tepAndGeq.setRhs(getT_maxOverDemandMVP());\n\t\tepCFAnd.getArguments().add(epAndGeq);\n\n\t\tLessOperator blobCFAndLe = LogicalSpecificationFactory.eINSTANCE.createLessOperator();\n\t\tblobCFAndLe.setLhs(getF_underDemandMVP());\n\t\tblobCFAndLe.setRhs(getT_minUnderDemandMVP());\n\t\tepCFAnd.getArguments().add(blobCFAndLe);\n\n\t\tEqualOperator epCFAndEq = LogicalSpecificationFactory.eINSTANCE.createEqualOperator();\n\t\tepCFAndEq.setLhs(getF_demandSumProbSVP());\n\t\tepCFAndEq.setRhs(getT_demandsProbSVP());\n\t\tepCFAnd.getArguments().add(epCFAndEq);\n\n\t\tOrOperator epCFAndOr = LogicalSpecificationFactory.eINSTANCE.createOrOperator();\n\t\tGreaterEqualOperator epCFAndOrGeq = LogicalSpecificationFactory.eINSTANCE.createGreaterEqualOperator();\n\t\tepCFAndOrGeq.setLhs(getF_hwUtilSVP());\n\t\tepCFAndOrGeq.setRhs(getT_maxHwUtilSVP());\n\t\tGreaterOperator epCFAndOrGr = LogicalSpecificationFactory.eINSTANCE.createGreaterOperator();\n\t\tepCFAndOrGr.setLhs(getF_serviceResponseTimeSVP());\n\t\tepCFAndOrGr.setRhs(getT_maxServiceResponseTimeSVP());\n\t\tepCFAndOr.getArguments().add(epCFAndOrGeq);\n\t\tepCFAndOr.getArguments().add(epCFAndOrGr);\n\t\tepCFAnd.getArguments().add(epCFAndOr);\n\n\t\tepCF.setRootOperator(epCFAnd);\n\t\tsetFormula(epCF);'"
	 * @generated
	 */
	void createFormula();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" T_maxOverDemandRequired="true" T_minUnderDemandRequired="true" T_demandsProbRequired="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='int occurrences = 0;\n\t\tsetParametersForPartialDetection(T_maxOverDemand, T_minUnderDemand, T_demandsProb);\n\t\tcreateFormula();\n\t\tList&lt;Element&gt; operations = ((Model)Manager.getInstance(UMLManager.getInstance())).getModel().getNestedPackages().get(0).getNestedPackages()\n\t\t\t\t.get(0).allOwnedElements();\n\t\tfor (Element o : operations) {\n\t\t\tif (o instanceof Operation &amp;&amp; o.getAppliedStereotype(\"GaAcqStep\") != null) {\n\t\t\t\ttry {\n\t\t\t\t\tboolean app = Manager.getInstance(null)\n\t\t\t\t\t\t\t.evaluateOperator((AndOperator) getFormula().getRootOperator(), (Operation) o);\n\t\t\t\t\tif (app)\n\t\t\t\t\t\toccurrences++;\n\t\t\t\t} catch (ParserException e) {\n\t\t\t\t\t// TODO Auto-generated catch block\n\t\t\t\t\te.printStackTrace();\n\t\t\t\t}\n\t\t\t}\n\t\t}\n\t\treturn occurrences;'"
	 * @generated
	 */
	int partialDetection(double T_maxOverDemand, double T_minUnderDemand, double T_demandsProb);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" T_maxOverDemandRequired="true" T_minUnderDemandRequired="true" T_demandsProbRequired="true" T_maxHwUtilRequired="true" T_maxServiceResponseTimeRequired="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='int occurrences = 0;\n\t\tsetParametersForFullDetection(T_maxOverDemand, T_minUnderDemand, T_demandsProb, T_maxHwUtil,\n\t\t\t\tT_maxServiceResponseTime);\n\t\tcreateFormula();\n\t\tList&lt;Element&gt; operations =((Model)Manager.getInstance(UMLManager.getInstance())).getModel().getNestedPackages().get(0).getNestedPackages()\n\t\t\t\t.get(0).allOwnedElements();\n\t\tfor (Element o : operations) {\n\t\t\tif (o instanceof Operation &amp;&amp; o.getAppliedStereotype(\"GaAcqStep\") != null) {\n\t\t\t\ttry {\n\t\t\t\t\tboolean app = Manager.getInstance(null)\n\t\t\t\t\t\t\t.evaluateOperator((AndOperator) getFormula().getRootOperator(), (Operation) o);\n\t\t\t\t\tif (app)\n\t\t\t\t\t\toccurrences++;\n\t\t\t\t} catch (ParserException e) {\n\t\t\t\t\t// TODO Auto-generated catch block\n\t\t\t\t\te.printStackTrace();\n\t\t\t\t}\n\t\t\t}\n\t\t}\n\t\treturn occurrences;'"
	 * @generated
	 */
	int fullDetection(double T_maxOverDemand, double T_minUnderDemand, double T_demandsProb, double T_maxHwUtil, double T_maxServiceResponseTime);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='super.log();'"
	 * @generated
	 */
	void log();

} // EpPA
