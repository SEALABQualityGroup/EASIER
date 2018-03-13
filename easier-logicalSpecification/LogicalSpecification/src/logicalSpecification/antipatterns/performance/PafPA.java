/**
 */
package logicalSpecification.antipatterns.performance;

import logicalSpecification.MultipleValuedParameter;
import logicalSpecification.SingleValuedParameter;

import org.eclipse.uml2.uml.Operation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Paf PA</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link logicalSpecification.antipatterns.performance.PafPA#getF_resDemandMVP <em>Fres Demand MVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.PafPA#getF_opProbExecSVP <em>Fop Prob Exec SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.PafPA#getF_hwUtilSVP <em>Fhw Util SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.PafPA#getF_serviceThroughputSVP <em>Fservice Throughput SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.PafPA#getT_maxResDemandMVP <em>Tmax Res Demand MVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.PafPA#getT_opProbExecSVP <em>Top Prob Exec SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.PafPA#getT_maxHwUtilSVP <em>Tmax Hw Util SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.PafPA#getT_minServiceThroughputSVP <em>Tmin Service Throughput SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.PafPA#getContextualElement <em>Contextual Element</em>}</li>
 * </ul>
 *
 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getPafPA()
 * @model
 * @generated
 */
public interface PafPA extends PerformanceAntipattern {
	/**
	 * Returns the value of the '<em><b>Fres Demand MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fres Demand MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fres Demand MVP</em>' reference.
	 * @see #setF_resDemandMVP(MultipleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getPafPA_F_resDemandMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getF_resDemandMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.PafPA#getF_resDemandMVP <em>Fres Demand MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fres Demand MVP</em>' reference.
	 * @see #getF_resDemandMVP()
	 * @generated
	 */
	void setF_resDemandMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Fop Prob Exec SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fop Prob Exec SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fop Prob Exec SVP</em>' reference.
	 * @see #setF_opProbExecSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getPafPA_F_opProbExecSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getF_opProbExecSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.PafPA#getF_opProbExecSVP <em>Fop Prob Exec SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fop Prob Exec SVP</em>' reference.
	 * @see #getF_opProbExecSVP()
	 * @generated
	 */
	void setF_opProbExecSVP(SingleValuedParameter value);

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
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getPafPA_F_hwUtilSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getF_hwUtilSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.PafPA#getF_hwUtilSVP <em>Fhw Util SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fhw Util SVP</em>' reference.
	 * @see #getF_hwUtilSVP()
	 * @generated
	 */
	void setF_hwUtilSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Fservice Throughput SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fservice Throughput SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fservice Throughput SVP</em>' reference.
	 * @see #setF_serviceThroughputSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getPafPA_F_serviceThroughputSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getF_serviceThroughputSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.PafPA#getF_serviceThroughputSVP <em>Fservice Throughput SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fservice Throughput SVP</em>' reference.
	 * @see #getF_serviceThroughputSVP()
	 * @generated
	 */
	void setF_serviceThroughputSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Tmax Res Demand MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tmax Res Demand MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tmax Res Demand MVP</em>' reference.
	 * @see #setT_maxResDemandMVP(MultipleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getPafPA_T_maxResDemandMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getT_maxResDemandMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.PafPA#getT_maxResDemandMVP <em>Tmax Res Demand MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tmax Res Demand MVP</em>' reference.
	 * @see #getT_maxResDemandMVP()
	 * @generated
	 */
	void setT_maxResDemandMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Top Prob Exec SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Top Prob Exec SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Top Prob Exec SVP</em>' reference.
	 * @see #setT_opProbExecSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getPafPA_T_opProbExecSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getT_opProbExecSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.PafPA#getT_opProbExecSVP <em>Top Prob Exec SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Top Prob Exec SVP</em>' reference.
	 * @see #getT_opProbExecSVP()
	 * @generated
	 */
	void setT_opProbExecSVP(SingleValuedParameter value);

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
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getPafPA_T_maxHwUtilSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getT_maxHwUtilSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.PafPA#getT_maxHwUtilSVP <em>Tmax Hw Util SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tmax Hw Util SVP</em>' reference.
	 * @see #getT_maxHwUtilSVP()
	 * @generated
	 */
	void setT_maxHwUtilSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Tmin Service Throughput SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tmin Service Throughput SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tmin Service Throughput SVP</em>' reference.
	 * @see #setT_minServiceThroughputSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getPafPA_T_minServiceThroughputSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getT_minServiceThroughputSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.PafPA#getT_minServiceThroughputSVP <em>Tmin Service Throughput SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tmin Service Throughput SVP</em>' reference.
	 * @see #getT_minServiceThroughputSVP()
	 * @generated
	 */
	void setT_minServiceThroughputSVP(SingleValuedParameter value);

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
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getPafPA_ContextualElement()
	 * @model dataType="logicalSpecification.actions.UML.Operation" required="true"
	 * @generated
	 */
	Operation getContextualElement();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.PafPA#getContextualElement <em>Contextual Element</em>}' attribute.
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
	 * @model T_maxResDemandRequired="true" T_opProbExecRequired="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='List&lt;Parameter&gt; blobParams = new ArrayList&lt;&gt;();\n\n\t\tsetF_resDemandMVP(Manager.getInstance(null).createMultipleValuedParameter(\n\t\t\t\tOclStringManager.getInstance(null).getResDemandQuery(getContextualElement())));\n\t\tblobParams.add(getF_resDemandMVP());\n\n\t\tsetT_maxResDemandMVP(Manager.getInstance(null).createMultipleValuedParameter(String.valueOf(T_maxResDemand)));\n\t\tblobParams.add(getT_maxResDemandMVP());\n\n\t\tsetF_opProbExecSVP(Manager.getInstance(null).createSingleValueParameter(\n\t\t\t\tOclStringManager.getInstance(null).getOpProbExecQuery(getContextualElement())));\n\t\tblobParams.add(getF_opProbExecSVP());\n\n\t\tsetT_opProbExecSVP(Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_opProbExec)));\n\t\tblobParams.add(getT_opProbExecSVP());\n\n\t\tif (getF_hwUtilSVP() != null)\n\t\t\tif (getF_hwUtilSVP().getResolvingExpr() != null || !getF_hwUtilSVP().getResolvingExpr().equals(\"\"))\n\t\t\t\tgetF_hwUtilSVP().setResolvingExpr(null);\n\n\t\tif (getT_maxHwUtilSVP() != null)\n\t\t\tif (getT_maxHwUtilSVP().getResolvingExpr() != null || !getT_maxHwUtilSVP().getResolvingExpr().equals(\"\"))\n\t\t\t\tgetT_maxHwUtilSVP().setResolvingExpr(null);\n\n\t\tif (getF_serviceThroughputSVP() != null)\n\t\t\tif (getF_serviceThroughputSVP().getResolvingExpr() != null\n\t\t\t\t\t|| !getF_serviceThroughputSVP().getResolvingExpr().equals(\"\"))\n\t\t\t\tgetF_serviceThroughputSVP().setResolvingExpr(null);\n\n\t\tif (getT_minServiceThroughputSVP() != null)\n\t\t\tif (getT_minServiceThroughputSVP().getResolvingExpr() != null\n\t\t\t\t\t|| !getT_minServiceThroughputSVP().getResolvingExpr().equals(\"\"))\n\t\t\t\tgetT_minServiceThroughputSVP().setResolvingExpr(null);\n\n\t\tgetParameters().addAll(blobParams);'"
	 * @generated
	 */
	void setParametersForPartialDetection(double T_maxResDemand, double T_opProbExec);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model T_maxResDemandRequired="true" T_opProbExecRequired="true" T_maxHwUtilRequired="true" T_minServiceThroughputRequired="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='List&lt;Parameter&gt; blobParams = new ArrayList&lt;&gt;();\n\n\t\tsetF_resDemandMVP(Manager.getInstance(null).createMultipleValuedParameter(\n\t\t\t\tOclStringManager.getInstance(null).getResDemandQuery(getContextualElement())));\n\t\tblobParams.add(getF_resDemandMVP());\n\n\t\tsetT_maxResDemandMVP(Manager.getInstance(null).createMultipleValuedParameter(String.valueOf(T_maxResDemand)));\n\t\tblobParams.add(getT_maxResDemandMVP());\n\n\t\tsetF_opProbExecSVP(Manager.getInstance(null).createSingleValueParameter(\n\t\t\t\tOclStringManager.getInstance(null).getOpProbExecQuery(getContextualElement())));\n\t\tblobParams.add(getF_opProbExecSVP());\n\n\t\tsetT_opProbExecSVP(Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_opProbExec)));\n\t\tblobParams.add(getT_opProbExecSVP());\n\n\t\tsetF_hwUtilSVP(Manager.getInstance(null)\n\t\t\t\t.createSingleValueParameter(OclStringManager.getInstance(null).getHwUtilQuery(getContextualElement())));\n\t\tblobParams.add(getF_hwUtilSVP());\n\n\t\tsetT_maxHwUtilSVP(Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxHwUtil)));\n\t\tblobParams.add(getT_maxHwUtilSVP());\n\n\t\tsetF_serviceThroughputSVP(Manager.getInstance(null).createSingleValueParameter(\n\t\t\t\tOclStringManager.getInstance(null).getServiceThroughputQuery(getContextualElement())));\n\t\tblobParams.add(getF_serviceThroughputSVP());\n\n\t\tsetT_minServiceThroughputSVP(\n\t\t\t\tManager.getInstance(null).createSingleValueParameter(String.valueOf(T_minServiceThroughput)));\n\t\tblobParams.add(getT_minServiceThroughputSVP());\n\n\t\tgetParameters().addAll(blobParams);'"
	 * @generated
	 */
	void setParametersForFullDetection(double T_maxResDemand, double T_opProbExec, double T_maxHwUtil, double T_minServiceThroughput);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='FOLSpecification pafCF = LogicalSpecificationFactory.eINSTANCE.createFOLSpecification();\n\t\tpafCF.setName(\"PafFormula\");\n\n\t\tAndOperator pafCFAnd = LogicalSpecificationFactory.eINSTANCE.createAndOperator();\n\n\t\tGreaterEqualOperator pafCFAndGeq = LogicalSpecificationFactory.eINSTANCE.createGreaterEqualOperator();\n\t\tpafCFAndGeq.setLhs(getF_resDemandMVP());\n\t\tpafCFAndGeq.setRhs(getT_maxResDemandMVP());\n\t\tpafCFAnd.getArguments().add(pafCFAndGeq);\n\n\t\tEqualOperator pafCFAndEq = LogicalSpecificationFactory.eINSTANCE.createEqualOperator();\n\t\tpafCFAndEq.setLhs(getF_opProbExecSVP());\n\t\tpafCFAndEq.setRhs(getT_opProbExecSVP());\n\t\tpafCFAnd.getArguments().add(pafCFAndEq);\n\n\t\tOrOperator pafCFAndOr = LogicalSpecificationFactory.eINSTANCE.createOrOperator();\n\t\tGreaterEqualOperator pafCFAndOrGeq = LogicalSpecificationFactory.eINSTANCE.createGreaterEqualOperator();\n\t\tpafCFAndOrGeq.setLhs(getF_hwUtilSVP());\n\t\tpafCFAndOrGeq.setRhs(getT_maxHwUtilSVP());\n\t\tLessOperator pafCFAndOrLe = LogicalSpecificationFactory.eINSTANCE.createLessOperator();\n\t\tpafCFAndOrLe.setLhs(getF_serviceThroughputSVP());\n\t\tpafCFAndOrLe.setRhs(getT_minServiceThroughputSVP());\n\t\tpafCFAndOr.getArguments().add(pafCFAndOrGeq);\n\t\tpafCFAndOr.getArguments().add(pafCFAndOrLe);\n\t\tpafCFAnd.getArguments().add(pafCFAndOr);\n\n\t\tpafCF.setRootOperator(pafCFAnd);\n\t\tsetFormula(pafCF);'"
	 * @generated
	 */
	void createFormula();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" T_maxResDemandRequired="true" T_opProbExecRequired="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='int occurrences = 0;\n\t\tsetParametersForPartialDetection(T_maxResDemand, T_opProbExec);\n\t\tcreateFormula();\n\t\tList&lt;Element&gt; operations = ((Model)Manager.getInstance(UMLManager.getInstance())).getModel().getNestedPackages().get(0).getNestedPackages()\n\t\t\t\t.get(0).allOwnedElements();\n\t\tfor (Element o : operations) {\n\t\t\tif (o instanceof Operation &amp;&amp; o.getAppliedStereotype(\"GaAcqStep\") != null) {\n\t\t\t\ttry {\n\t\t\t\t\tboolean app = Manager.getInstance(null)\n\t\t\t\t\t\t\t.evaluateOperator((AndOperator) getFormula().getRootOperator(), (Operation) o);\n\t\t\t\t\tif (app)\n\t\t\t\t\t\toccurrences++;\n\t\t\t\t} catch (ParserException e) {\n\t\t\t\t\t// TODO Auto-generated catch block\n\t\t\t\t\te.printStackTrace();\n\t\t\t\t}\n\t\t\t}\n\t\t}\n\t\treturn occurrences;'"
	 * @generated
	 */
	int partialDetection(double T_maxResDemand, double T_opProbExec);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" T_maxResDemandRequired="true" T_opProbExecRequired="true" T_maxHwUtilRequired="true" T_minServiceThroughputRequired="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='int occurrences = 0;\n\t\tsetParametersForFullDetection(T_maxResDemand, T_opProbExec, T_maxHwUtil, T_minServiceThroughput);\n\t\tcreateFormula();\n\t\tList&lt;Element&gt; operations = ((Model) Manager.getInstance(UMLManager.getInstance())).getModel()\n\t\t\t\t.getNestedPackages().get(0).getNestedPackages().get(0).allOwnedElements();\n\t\tfor (Element o : operations) {\n\t\t\tif (o instanceof Operation &amp;&amp; o.getAppliedStereotype(\"GaAcqStep\") != null) {\n\t\t\t\ttry {\n\t\t\t\t\tboolean app = Manager.getInstance(null)\n\t\t\t\t\t\t\t.evaluateOperator((AndOperator) getFormula().getRootOperator(), (Operation) o);\n\t\t\t\t\tif (app)\n\t\t\t\t\t\toccurrences++;\n\t\t\t\t} catch (ParserException e) {\n\t\t\t\t\t// TODO Auto-generated catch block\n\t\t\t\t\te.printStackTrace();\n\t\t\t\t}\n\t\t\t}\n\t\t}\n\t\treturn occurrences;'"
	 * @generated
	 */
	int fullDetection(double T_maxResDemand, double T_opProbExec, double T_maxHwUtil, double T_minServiceThroughput);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='super.log();'"
	 * @generated
	 */
	void log();

} // PafPA
