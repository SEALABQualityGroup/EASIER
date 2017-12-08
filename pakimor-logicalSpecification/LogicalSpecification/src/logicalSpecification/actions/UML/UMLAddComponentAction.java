/**
 */
package logicalSpecification.actions.UML;

import logicalSpecification.MultipleValuedParameter;
import logicalSpecification.SingleValuedParameter;

import org.eclipse.emf.common.util.EList;

import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Node;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Add Component Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddComponentAction#getComponentToAddSVP <em>Component To Add SVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddComponentAction#getTargetNodesMVP <em>Target Nodes MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddComponentAction#getDeployedCompsMVP <em>Deployed Comps MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddComponentAction#getAllCompsMVP <em>All Comps MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddComponentAction#getAllNodesMVP <em>All Nodes MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddComponentAction#getUmlCompToAdd <em>Uml Comp To Add</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddComponentAction#getUmlSourcePackage <em>Uml Source Package</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.UMLAddComponentAction#getUmlTargetNodes <em>Uml Target Nodes</em>}</li>
 * </ul>
 *
 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddComponentAction()
 * @model
 * @generated
 */
public interface UMLAddComponentAction extends UMLAddAction {
	/**
	 * Returns the value of the '<em><b>Component To Add SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component To Add SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component To Add SVP</em>' reference.
	 * @see #setComponentToAddSVP(SingleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddComponentAction_ComponentToAddSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getComponentToAddSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddComponentAction#getComponentToAddSVP <em>Component To Add SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component To Add SVP</em>' reference.
	 * @see #getComponentToAddSVP()
	 * @generated
	 */
	void setComponentToAddSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Target Nodes MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Nodes MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Nodes MVP</em>' reference.
	 * @see #setTargetNodesMVP(MultipleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddComponentAction_TargetNodesMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getTargetNodesMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddComponentAction#getTargetNodesMVP <em>Target Nodes MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Nodes MVP</em>' reference.
	 * @see #getTargetNodesMVP()
	 * @generated
	 */
	void setTargetNodesMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Deployed Comps MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deployed Comps MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deployed Comps MVP</em>' reference.
	 * @see #setDeployedCompsMVP(MultipleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddComponentAction_DeployedCompsMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getDeployedCompsMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddComponentAction#getDeployedCompsMVP <em>Deployed Comps MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deployed Comps MVP</em>' reference.
	 * @see #getDeployedCompsMVP()
	 * @generated
	 */
	void setDeployedCompsMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>All Comps MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Comps MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Comps MVP</em>' reference.
	 * @see #setAllCompsMVP(MultipleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddComponentAction_AllCompsMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getAllCompsMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddComponentAction#getAllCompsMVP <em>All Comps MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Comps MVP</em>' reference.
	 * @see #getAllCompsMVP()
	 * @generated
	 */
	void setAllCompsMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>All Nodes MVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Nodes MVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Nodes MVP</em>' reference.
	 * @see #setAllNodesMVP(MultipleValuedParameter)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddComponentAction_AllNodesMVP()
	 * @model required="true"
	 * @generated
	 */
	MultipleValuedParameter getAllNodesMVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddComponentAction#getAllNodesMVP <em>All Nodes MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Nodes MVP</em>' reference.
	 * @see #getAllNodesMVP()
	 * @generated
	 */
	void setAllNodesMVP(MultipleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Uml Comp To Add</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uml Comp To Add</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uml Comp To Add</em>' attribute.
	 * @see #setUmlCompToAdd(Component)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddComponentAction_UmlCompToAdd()
	 * @model dataType="logicalSpecification.actions.UML.Component" required="true"
	 * @generated
	 */
	Component getUmlCompToAdd();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddComponentAction#getUmlCompToAdd <em>Uml Comp To Add</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uml Comp To Add</em>' attribute.
	 * @see #getUmlCompToAdd()
	 * @generated
	 */
	void setUmlCompToAdd(Component value);

	/**
	 * Returns the value of the '<em><b>Uml Source Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uml Source Package</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uml Source Package</em>' attribute.
	 * @see #setUmlSourcePackage(org.eclipse.uml2.uml.Package)
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddComponentAction_UmlSourcePackage()
	 * @model dataType="logicalSpecification.actions.UML.Package" required="true"
	 * @generated
	 */
	org.eclipse.uml2.uml.Package getUmlSourcePackage();

	/**
	 * Sets the value of the '{@link logicalSpecification.actions.UML.UMLAddComponentAction#getUmlSourcePackage <em>Uml Source Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uml Source Package</em>' attribute.
	 * @see #getUmlSourcePackage()
	 * @generated
	 */
	void setUmlSourcePackage(org.eclipse.uml2.uml.Package value);

	/**
	 * Returns the value of the '<em><b>Uml Target Nodes</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.uml2.uml.Node}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uml Target Nodes</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uml Target Nodes</em>' attribute list.
	 * @see logicalSpecification.actions.UML.UMLPackage#getUMLAddComponentAction_UmlTargetNodes()
	 * @model dataType="logicalSpecification.actions.UML.Node" required="true"
	 * @generated
	 */
	EList<Node> getUmlTargetNodes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='super.log(); \nif(getUmlCompToAdd() != null)\n\tController.logger_.info(getUmlCompToAdd().toString());'"
	 * @generated
	 */
	void log();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='setUmlCompToAdd(UMLFactory.eINSTANCE.createComponent());\n\t\tgetUmlCompToAdd().setName(\"newComp\" + Math.random());\n\t\tgetUmlCompToAdd().setPackage(getUmlSourcePackage());\n\t\tsetParameters();\n\t\tdeployOn(getUmlTargetNodes());'"
	 * @generated
	 */
	void execute();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model targetsDataType="logicalSpecification.actions.UML.Node" targetsMany="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='Artifact art;\n\t\tfor (Node target : targets) {\n\t\t\t\n\t\t\tPackage targetPackage = target.getPackage();\n\t\t\t\n\t\t\tart = UMLFactory.eINSTANCE.createArtifact();\n\t\t\ttargetPackage.getPackagedElements().add(art);\n\t\t\tart.setName(getUmlCompToAdd().getName() + \"_Artifact\");\n\t\t\tart.createManifestation(getUmlCompToAdd().getName() + \"_Manifestation\", getUmlCompToAdd());\n\t\t\tart.setPackage(targetPackage);\n\t\t\t\n\t\t\tDeployment deploy = target.createDeployment(getUmlCompToAdd().getName() + \"_Deployment\");\n\t\t\tdeploy.getDeployedArtifacts().add(art);\n\t\t\ttarget.getDeployments().add(deploy);\n\t\t}'"
	 * @generated
	 */
	void deployOn(EList<Node> targets);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='// ACTION add PARAMETERS\n\t\tList&lt;Parameter&gt; addParams = new ArrayList&lt;&gt;();\n\t\t// FIXME le add non dovrebbero avere come attributo l\'oggetto da creare\n\n\t\t// setCompToAddSVP(Manager.createSingleValueParameter(\"Component.allInstances()-&gt;select(c\n\t\t// | c.name = \'Pippo Node\')\"));\n\t\t// addParams.add(getCompToAddSVP());\n\n\t\tif (umlCompToAdd != null) {\n\t\t\tsetComponentToAddSVP(Manager.getInstance(UMLManager.getInstance()).createSingleValueParameter(\n\t\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t\t.getComponentQuery(umlCompToAdd)));\n\t\t\taddParams.add(getComponentToAddSVP());\n\t\t}\n\n\t\tsetTargetNodesMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getNodesQuery(umlTargetNodes)));\n\t\taddParams.add(getTargetNodesMVP());\n\n\t\t// List&lt;Component&gt; list_of_random_components = new\n\t\t// ArrayList&lt;Component&gt;();\n\t\t// list_of_random_components = OclManager.getRandomComponents();\n\t\t// setDeployedCompsMVP(Manager.createMultipleValuedParameter(OclStringManager.getComponentsQuery(list_of_random_components)));\n\t\t// addParams.add(getDeployedCompsMVP());\n\n\t\tsetDeployedCompsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getAllDeployedElementsQuery(getUmlTargetNodes())));\n\t\taddParams.add(getDeployedCompsMVP());\n\n\t\tsetAllNodesMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllNodesQuery()));\n\t\taddParams.add(getAllNodesMVP());\n\n\t\tsetAllCompsMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(\n\t\t\t\t((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))\n\t\t\t\t\t\t.getAllComponentsQuery()));\n\t\taddParams.add(getAllCompsMVP());\n\n\t\tgetParameters().addAll(addParams);'"
	 * @generated
	 */
	void setParameters();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='\tPreCondition preCondition = LogicalSpecificationFactory.eINSTANCE.createPreCondition();\n\n\t\tFOLSpecification addPreSpecification = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification(\"AddComponentPreCondition\");\n\n\t\tAndOperator addPreAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\n\t\t// NotOperator addPreAndNot = Manager.createNotOperator();\n\t\t// ExistsOperator addPreAndNotExists =\n\t\t// Manager.createExistsOperator(getCompToAddSVP(), getAllCompsMVP());\n\t\t//\n\t\t// addPreAndNot.setArgument(addPreAndNotExists);\n\t\t// addPreAnd.getArguments().add(addPreAndNot);\n\n\t\tForAllOperator addPreAndForall = Manager.getInstance(UMLManager.getInstance()).createForAllOperator(getTargetNodesMVP());\n\t\tExistsOperator addPreAndForallExists = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getAllNodesMVP());\n\t\taddPreAndForall.setArgument(addPreAndForallExists);\n\t\taddPreAnd.getArguments().add(addPreAndForall);\n\n\t\taddPreSpecification.setRootOperator(addPreAnd);\n\t\tpreCondition.setConditionFormula(addPreSpecification);\n\t\tsetPre(preCondition);'"
	 * @generated
	 */
	void createPreCondition();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='PostCondition addPost = LogicalSpecificationFactory.eINSTANCE.createPostCondition();\n\n\t\tFOLSpecification addPostSpec = Manager.getInstance(UMLManager.getInstance()).createFOLSpectification(\"AddNodePostcondition\");\n\n\t\tAndOperator addPostAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\n\t\tExistsOperator addPostAndExistsInC = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getComponentToAddSVP(), getAllCompsMVP());\n\n\t\taddPostAnd.getArguments().add(addPostAndExistsInC);\n\n\t\tForAllOperator addPostAndForallTargets = Manager.getInstance(UMLManager.getInstance()).createForAllOperator(getTargetNodesMVP());\n\t\tAndOperator addPostAndForallTargetsAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();\n\t\tExistsOperator addPostAndForallTargetsExists = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getAllNodesMVP());\n\t\taddPostAndForallTargetsAnd.getArguments().add(addPostAndForallTargetsExists);\n\n\t\t// TODO getDeployedComps \350 l\'insieme delle Component deployate sui\n\t\t// target Nodes. Non va bene: la condition dice che\n\t\t// su ogni target Node ci deve stare la Component aggiunta. Con l\'unione\n\t\t// basta che stia su uno dei target...\n\t\tExistsOperator addPostAndExistsInD = Manager.getInstance(UMLManager.getInstance()).createExistsOperator(getComponentToAddSVP(),\n\t\t\t\tgetDeployedCompsMVP());\n\n\t\taddPostAndForallTargetsAnd.getArguments().add(addPostAndExistsInD);\n\t\taddPostAndForallTargets.setArgument(addPostAndForallTargetsAnd);\n\t\taddPostAnd.getArguments().add(addPostAndForallTargets);\n\n\t\taddPostSpec.setRootOperator(addPostAnd);\n\t\taddPost.setConditionFormula(addPostSpec);\n\t\tsetPost(addPost);'"
	 * @generated
	 */
	void createPostCondition();

} // UMLAddComponentAction
