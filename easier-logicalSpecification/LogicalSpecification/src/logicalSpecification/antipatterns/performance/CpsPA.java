/**
 */
package logicalSpecification.antipatterns.performance;

import logicalSpecification.MultipleValuedParameter;
import logicalSpecification.SingleValuedParameter;

import org.eclipse.uml2.uml.Node;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cps PA</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link logicalSpecification.antipatterns.performance.CpsPA#getF_queueLengthSVP <em>Fqueue Length SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.CpsPA#getF_overUtilSVP <em>Fover Util SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.CpsPA#getF_underUtilSVP <em>Funder Util SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.CpsPA#getF_opDemandMVP <em>Fop Demand MVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.CpsPA#getT_maxQueueLengthSVP <em>Tmax Queue Length SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.CpsPA#getT_maxOverUtilSVP <em>Tmax Over Util SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.CpsPA#getT_minUnderUtilSVP <em>Tmin Under Util SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.CpsPA#getT_maxOpDemandMVP <em>Tmax Op Demand MVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.CpsPA#getContextualElement <em>Contextual Element</em>}</li>
 * </ul>
 *
 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getCpsPA()
 * @model
 * @generated
 */
public interface CpsPA extends PerformanceAntipattern {
	/**
	 * Returns the value of the '<em><b>Fqueue Length SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fqueue Length SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fqueue Length SVP</em>' reference.
	 * @see #setF_queueLengthSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getCpsPA_F_queueLengthSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getF_queueLengthSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.CpsPA#getF_queueLengthSVP <em>Fqueue Length SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fqueue Length SVP</em>' reference.
	 * @see #getF_queueLengthSVP()
	 * @generated
	 */
	void setF_queueLengthSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Fover Util SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fover Util SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fover Util SVP</em>' reference.
	 * @see #setF_overUtilSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getCpsPA_F_overUtilSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getF_overUtilSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.CpsPA#getF_overUtilSVP <em>Fover Util SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fover Util SVP</em>' reference.
	 * @see #getF_overUtilSVP()
	 * @generated
	 */
	void setF_overUtilSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Funder Util SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Funder Util SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Funder Util SVP</em>' reference.
	 * @see #setF_underUtilSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getCpsPA_F_underUtilSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getF_underUtilSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.CpsPA#getF_underUtilSVP <em>Funder Util SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funder Util SVP</em>' reference.
	 * @see #getF_underUtilSVP()
	 * @generated
	 */
	void setF_underUtilSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Fop Demand MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fop Demand MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fop Demand MVP</em>' reference.
	 * @see #setF_opDemandMVP(MultipleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getCpsPA_F_opDemandMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getF_opDemandMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.CpsPA#getF_opDemandMVP <em>Fop Demand MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fop Demand MVP</em>' reference.
	 * @see #getF_opDemandMVP()
	 * @generated
	 */
	void setF_opDemandMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Tmax Queue Length SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tmax Queue Length SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tmax Queue Length SVP</em>' reference.
	 * @see #setT_maxQueueLengthSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getCpsPA_T_maxQueueLengthSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getT_maxQueueLengthSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.CpsPA#getT_maxQueueLengthSVP <em>Tmax Queue Length SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tmax Queue Length SVP</em>' reference.
	 * @see #getT_maxQueueLengthSVP()
	 * @generated
	 */
	void setT_maxQueueLengthSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Tmax Over Util SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tmax Over Util SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tmax Over Util SVP</em>' reference.
	 * @see #setT_maxOverUtilSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getCpsPA_T_maxOverUtilSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getT_maxOverUtilSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.CpsPA#getT_maxOverUtilSVP <em>Tmax Over Util SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tmax Over Util SVP</em>' reference.
	 * @see #getT_maxOverUtilSVP()
	 * @generated
	 */
	void setT_maxOverUtilSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Tmin Under Util SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tmin Under Util SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tmin Under Util SVP</em>' reference.
	 * @see #setT_minUnderUtilSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getCpsPA_T_minUnderUtilSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getT_minUnderUtilSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.CpsPA#getT_minUnderUtilSVP <em>Tmin Under Util SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tmin Under Util SVP</em>' reference.
	 * @see #getT_minUnderUtilSVP()
	 * @generated
	 */
	void setT_minUnderUtilSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Tmax Op Demand MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tmax Op Demand MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tmax Op Demand MVP</em>' reference.
	 * @see #setT_maxOpDemandMVP(MultipleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getCpsPA_T_maxOpDemandMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getT_maxOpDemandMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.CpsPA#getT_maxOpDemandMVP <em>Tmax Op Demand MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tmax Op Demand MVP</em>' reference.
	 * @see #getT_maxOpDemandMVP()
	 * @generated
	 */
	void setT_maxOpDemandMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Contextual Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contextual Element</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contextual Element</em>' attribute.
	 * @see #setContextualElement(Node)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getCpsPA_ContextualElement()
	 * @model dataType="logicalSpecification.actions.UML.Node" required="true"
	 * @generated
	 */
	Node getContextualElement();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.CpsPA#getContextualElement <em>Contextual Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contextual Element</em>' attribute.
	 * @see #getContextualElement()
	 * @generated
	 */
	void setContextualElement(Node value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model T_maxOpDemandRequired="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='List&lt;Parameter&gt; blobParams = new ArrayList&lt;&gt;();\n\n\t\tif (getF_queueLengthSVP() != null)\n\t\t\tif (getF_queueLengthSVP().getResolvingExpr() != null\n\t\t\t\t\t|| !getF_queueLengthSVP().getResolvingExpr().equals(\"\"))\n\t\t\t\tgetF_queueLengthSVP().setResolvingExpr(null);\n\n\t\tif (getT_maxQueueLengthSVP() != null)\n\t\t\tif (getT_maxQueueLengthSVP().getResolvingExpr() != null\n\t\t\t\t\t|| !getT_maxQueueLengthSVP().getResolvingExpr().equals(\"\"))\n\t\t\t\tgetT_maxQueueLengthSVP().setResolvingExpr(null);\n\n\t\tif (getF_overUtilSVP() != null)\n\t\t\tif (getF_overUtilSVP().getResolvingExpr() != null || !getF_overUtilSVP().getResolvingExpr().equals(\"\"))\n\t\t\t\tgetF_overUtilSVP().setResolvingExpr(null);\n\n\t\tif (getT_maxOverUtilSVP() != null)\n\t\t\tif (getT_maxOverUtilSVP().getResolvingExpr() != null\n\t\t\t\t\t|| !getT_maxOverUtilSVP().getResolvingExpr().equals(\"\"))\n\t\t\t\tgetT_maxOverUtilSVP().setResolvingExpr(null);\n\n\t\tif (getF_underUtilSVP() != null)\n\t\t\tif (getF_underUtilSVP().getResolvingExpr() != null || !getF_underUtilSVP().getResolvingExpr().equals(\"\"))\n\t\t\t\tgetF_underUtilSVP().setResolvingExpr(null);\n\n\t\tif (getT_minUnderUtilSVP() != null)\n\t\t\tif (getT_minUnderUtilSVP().getResolvingExpr() != null\n\t\t\t\t\t|| !getT_minUnderUtilSVP().getResolvingExpr().equals(\"\"))\n\t\t\t\tgetT_minUnderUtilSVP().setResolvingExpr(null);\n\n\t\t// if(getF_opDemandMVP() != null)\n\t\t// if(getF_opDemandMVP().getResolvingExpr() != null ||\n\t\t// !getF_opDemandMVP().getResolvingExpr().equals(\"\"))\n\t\t// getF_opDemandMVP().setResolvingExpr(null);\n\n\t\tsetF_opDemandMVP(Manager.getInstance(null).createMultipleValuedParameter(\n\t\t\t\tOclStringManager.getInstance(null).getOpDemandQuery(getContextualElement())));\n\t\tblobParams.add(getF_opDemandMVP());\n\n\t\t// if(getT_maxOpDemandMVP() != null)\n\t\t// if(getT_maxOpDemandMVP().getResolvingExpr() != null ||\n\t\t// !getT_maxOpDemandMVP().getResolvingExpr().equals(\"\"))\n\t\t// getT_maxOpDemandMVP().setResolvingExpr(null);\n\n\t\tsetT_maxOpDemandMVP(Manager.getInstance(null).createMultipleValuedParameter(String.valueOf(T_maxOpDemand)));\n\t\tblobParams.add(getT_maxOpDemandMVP());\n\n\t\tgetParameters().addAll(blobParams);'"
	 * @generated
	 */
	void setParametersForPartialDetection(double T_maxOpDemand);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model T_maxQueueLengthRequired="true" T_maxOverUtilRequired="true" T_minUnderUtilRequired="true" T_maxOpDemandRequired="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='List&lt;Parameter&gt; blobParams = new ArrayList&lt;&gt;();\n\n\t\tsetF_queueLengthSVP(Manager.getInstance(null).createSingleValueParameter(\n\t\t\t\tOclStringManager.getInstance(null).getQueueLengthQuery(getContextualElement())));\n\t\tblobParams.add(getF_queueLengthSVP());\n\n\t\tsetT_maxQueueLengthSVP(Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxQueueLength)));\n\t\tblobParams.add(getT_maxQueueLengthSVP());\n\n\t\tsetF_overUtilSVP(Manager.getInstance(null).createSingleValueParameter(\n\t\t\t\tOclStringManager.getInstance(null).getOverUtilQuery(getContextualElement())));\n\t\tblobParams.add(getF_overUtilSVP());\n\n\t\tsetT_maxOverUtilSVP(Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxOverUtil)));\n\t\tblobParams.add(getT_maxOverUtilSVP());\n\n\t\tsetF_underUtilSVP(Manager.getInstance(null).createSingleValueParameter(\n\t\t\t\tOclStringManager.getInstance(null).getUnderUtilQuery(getContextualElement())));\n\t\tblobParams.add(getF_underUtilSVP());\n\n\t\tsetT_minUnderUtilSVP(Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_minUnderUtil)));\n\t\tblobParams.add(getT_minUnderUtilSVP());\n\n\t\tsetF_opDemandMVP(Manager.getInstance(null).createMultipleValuedParameter(\n\t\t\t\tOclStringManager.getInstance(null).getOpDemandQuery(getContextualElement())));\n\t\tblobParams.add(getF_opDemandMVP());\n\n\t\tsetT_maxOpDemandMVP(Manager.getInstance(null).createMultipleValuedParameter(String.valueOf(T_maxOpDemand)));\n\t\tblobParams.add(getT_maxOpDemandMVP());\n\n\t\tgetParameters().addAll(blobParams);'"
	 * @generated
	 */
	void setParametersForFullDetection(double T_maxQueueLength, double T_maxOverUtil, double T_minUnderUtil, double T_maxOpDemand);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='FOLSpecification cpsCF = LogicalSpecificationFactory.eINSTANCE.createFOLSpecification();\n\t\tcpsCF.setName(\"CpsFormula\");\n\n\t\tAndOperator cpsCFAnd = LogicalSpecificationFactory.eINSTANCE.createAndOperator();\n\n\t\tGreaterEqualOperator cpsCFAndGeq1 = LogicalSpecificationFactory.eINSTANCE.createGreaterEqualOperator();\n\t\tcpsCFAndGeq1.setLhs(getF_queueLengthSVP());\n\t\tcpsCFAndGeq1.setRhs(getT_maxQueueLengthSVP());\n\t\tcpsCFAnd.getArguments().add(cpsCFAndGeq1);\n\n\t\tAndOperator cpsCFAndAnd = LogicalSpecificationFactory.eINSTANCE.createAndOperator();\n\t\tGreaterEqualOperator cpsCFAndAndGeq = LogicalSpecificationFactory.eINSTANCE.createGreaterEqualOperator();\n\t\tcpsCFAndAndGeq.setLhs(getF_overUtilSVP());\n\t\tcpsCFAndAndGeq.setRhs(getT_maxOverUtilSVP());\n\t\tcpsCFAndAnd.getArguments().add(cpsCFAndAndGeq);\n\t\tLessOperator cpsCFAndAndLe = LogicalSpecificationFactory.eINSTANCE.createLessOperator();\n\t\tcpsCFAndAndLe.setLhs(getF_underUtilSVP());\n\t\tcpsCFAndAndLe.setRhs(getT_minUnderUtilSVP());\n\t\tcpsCFAndAnd.getArguments().add(cpsCFAndAndLe);\n\t\tcpsCFAnd.getArguments().add(cpsCFAndAnd);\n\n\t\tGreaterEqualOperator cpsCFAndGeq2 = LogicalSpecificationFactory.eINSTANCE.createGreaterEqualOperator();\n\t\tcpsCFAndGeq2.setLhs(getF_opDemandMVP());\n\t\tcpsCFAndGeq2.setRhs(getT_maxOpDemandMVP());\n\t\tcpsCFAnd.getArguments().add(cpsCFAndGeq2);\n\n\t\tcpsCF.setRootOperator(cpsCFAnd);\n\t\tsetFormula(cpsCF);'"
	 * @generated
	 */
	void createFormula();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" T_maxOpDemandRequired="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='int occurrences = 0;\n\t\tsetParametersForPartialDetection(T_maxOpDemand);\n\t\tcreateFormula();\n\t\tList&lt;Element&gt; nodes = ((Model)Manager.getInstance(UMLManager.getInstance())).getModel().getNestedPackages().get(0).getNestedPackages().get(0)\n\t\t\t\t.allOwnedElements();\n\t\tfor (Element n : nodes) {\n\t\t\tif (n instanceof Component &amp;&amp; n.getAppliedStereotype(\"GaExecHost\") != null) {\n\t\t\t\ttry {\n\t\t\t\t\tboolean app = Manager.getInstance(null)\n\t\t\t\t\t\t\t.evaluateOperator((AndOperator) getFormula().getRootOperator(), (Node) n);\n\t\t\t\t\tif (app)\n\t\t\t\t\t\toccurrences++;\n\t\t\t\t} catch (ParserException e) {\n\t\t\t\t\t// TODO Auto-generated catch block\n\t\t\t\t\te.printStackTrace();\n\t\t\t\t}\n\t\t\t}\n\t\t}\n\t\treturn occurrences;'"
	 * @generated
	 */
	int partialDetection(double T_maxOpDemand);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" T_maxQueueLengthRequired="true" T_maxOverUtilRequired="true" T_minUnderUtilRequired="true" T_maxOpDemandRequired="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='int occurrences = 0;\n\t\tsetParametersForFullDetection(T_maxQueueLength, T_maxOverUtil, T_minUnderUtil, T_maxOpDemand);\n\t\tcreateFormula();\n\t\tList&lt;Element&gt; nodes = ((Model)Manager.getInstance(UMLManager.getInstance())).getModel().getNestedPackages().get(0).getNestedPackages().get(0)\n\t\t\t\t.allOwnedElements();\n\t\tfor (Element n : nodes) {\n\t\t\tif (n instanceof Node &amp;&amp; n.getAppliedStereotype(\"GaExecHost\") != null) {\n\t\t\t\ttry {\n\t\t\t\t\tboolean app = Manager.getInstance(null)\n\t\t\t\t\t\t\t.evaluateOperator((AndOperator) getFormula().getRootOperator(), (Node) n);\n\t\t\t\t\tif (app)\n\t\t\t\t\t\toccurrences++;\n\t\t\t\t} catch (ParserException e) {\n\t\t\t\t\t// TODO Auto-generated catch block\n\t\t\t\t\te.printStackTrace();\n\t\t\t\t}\n\t\t\t}\n\t\t}\n\t\treturn occurrences;'"
	 * @generated
	 */
	int fullDetection(double T_maxQueueLength, double T_maxOverUtil, double T_minUnderUtil, double T_maxOpDemand);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='super.log();'"
	 * @generated
	 */
	void log();

} // CpsPA
