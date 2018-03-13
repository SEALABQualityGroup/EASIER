/**
 */
package logicalSpecification.actions.UML.impl;

import java.lang.reflect.InvocationTargetException;

import logicalSpecification.MultipleValuedParameter;
import logicalSpecification.SingleValuedParameter;

import logicalSpecification.actions.UML.UMLDeleteNodeAction;
import logicalSpecification.actions.UML.UMLPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.uml2.uml.Node;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Delete Node Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link logicalSpecification.actions.UML.impl.UMLDeleteNodeActionImpl#getNodeToDelSVP <em>Node To Del SVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.impl.UMLDeleteNodeActionImpl#getAllNodesMVP <em>All Nodes MVP</em>}</li>
 *   <li>{@link logicalSpecification.actions.UML.impl.UMLDeleteNodeActionImpl#getUmlNodeToDel <em>Uml Node To Del</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UMLDeleteNodeActionImpl extends UMLDeleteActionImpl implements UMLDeleteNodeAction {
	/**
	 * The cached value of the '{@link #getNodeToDelSVP() <em>Node To Del SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeToDelSVP()
	 * @generated
	 * @ordered
	 */
	protected SingleValuedParameter nodeToDelSVP;

	/**
	 * The cached value of the '{@link #getAllNodesMVP() <em>All Nodes MVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllNodesMVP()
	 * @generated
	 * @ordered
	 */
	protected MultipleValuedParameter allNodesMVP;

	/**
	 * The default value of the '{@link #getUmlNodeToDel() <em>Uml Node To Del</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUmlNodeToDel()
	 * @generated
	 * @ordered
	 */
	protected static final Node UML_NODE_TO_DEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUmlNodeToDel() <em>Uml Node To Del</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUmlNodeToDel()
	 * @generated
	 * @ordered
	 */
	protected Node umlNodeToDel = UML_NODE_TO_DEL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UMLDeleteNodeActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UMLPackage.Literals.UML_DELETE_NODE_ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter getNodeToDelSVP() {
		if (nodeToDelSVP != null && nodeToDelSVP.eIsProxy()) {
			InternalEObject oldNodeToDelSVP = (InternalEObject)nodeToDelSVP;
			nodeToDelSVP = (SingleValuedParameter)eResolveProxy(oldNodeToDelSVP);
			if (nodeToDelSVP != oldNodeToDelSVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UMLPackage.UML_DELETE_NODE_ACTION__NODE_TO_DEL_SVP, oldNodeToDelSVP, nodeToDelSVP));
			}
		}
		return nodeToDelSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter basicGetNodeToDelSVP() {
		return nodeToDelSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNodeToDelSVP(SingleValuedParameter newNodeToDelSVP) {
		SingleValuedParameter oldNodeToDelSVP = nodeToDelSVP;
		nodeToDelSVP = newNodeToDelSVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UMLPackage.UML_DELETE_NODE_ACTION__NODE_TO_DEL_SVP, oldNodeToDelSVP, nodeToDelSVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultipleValuedParameter getAllNodesMVP() {
		if (allNodesMVP != null && allNodesMVP.eIsProxy()) {
			InternalEObject oldAllNodesMVP = (InternalEObject)allNodesMVP;
			allNodesMVP = (MultipleValuedParameter)eResolveProxy(oldAllNodesMVP);
			if (allNodesMVP != oldAllNodesMVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UMLPackage.UML_DELETE_NODE_ACTION__ALL_NODES_MVP, oldAllNodesMVP, allNodesMVP));
			}
		}
		return allNodesMVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultipleValuedParameter basicGetAllNodesMVP() {
		return allNodesMVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAllNodesMVP(MultipleValuedParameter newAllNodesMVP) {
		MultipleValuedParameter oldAllNodesMVP = allNodesMVP;
		allNodesMVP = newAllNodesMVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UMLPackage.UML_DELETE_NODE_ACTION__ALL_NODES_MVP, oldAllNodesMVP, allNodesMVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getUmlNodeToDel() {
		return umlNodeToDel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUmlNodeToDel(Node newUmlNodeToDel) {
		Node oldUmlNodeToDel = umlNodeToDel;
		umlNodeToDel = newUmlNodeToDel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UMLPackage.UML_DELETE_NODE_ACTION__UML_NODE_TO_DEL, oldUmlNodeToDel, umlNodeToDel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void log() {
		super.log(); 
//				Controller.logger_.info(getUmlNodeToDel().getName());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void execute() {
		umlNodeToDel.destroy();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameters() {
//		List<Parameter> delNodeParams = new ArrayList<>();
//		
//				setNodeToDelSVP(Manager.getInstance(UMLManager.getInstance()).createSingleValueParameter(
//						((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager()))
//								.getNodeQuery(getUmlNodeToDel())));
//				delNodeParams.add(getNodeToDelSVP());
//		
//				setAllNodesMVP(Manager.getInstance(UMLManager.getInstance()).createMultipleValuedParameter(
//						((OclUMLStringManager) OclStringManager.getInstance(new OclUMLStringManager())).getAllNodesQuery()));
//				delNodeParams.add(getAllNodesMVP());
//		
//				getParameters().addAll(delNodeParams);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPreCondition() {
//		PreCondition preCondition = Manager.getInstance(UMLManager.getInstance()).createPreCondition();
//		
//				FOLSpecification specification = Manager.getInstance(UMLManager.getInstance())
//						.createFOLSpectification("DeleteNodePreCondition");
//		
//				AndOperator preAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();
//		
//				ExistsOperator existsOperator = Manager.getInstance(UMLManager.getInstance())
//						.createExistsOperator(getNodeToDelSVP(), getAllNodesMVP());
//		
//				preAnd.getArguments().add(existsOperator);
//		
//				specification.setRootOperator(preAnd);
//				preCondition.setConditionFormula(specification);
//				setPre(preCondition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPostCondition() {
//		PostCondition postCondition = Manager.getInstance(UMLManager.getInstance()).createPostCondition();
//				FOLSpecification specification = Manager.getInstance(UMLManager.getInstance())
//						.createFOLSpectification("DeleteNodePostCondition");
//		
//				AndOperator preAnd = Manager.getInstance(UMLManager.getInstance()).createAndOperator();
//		
//				NotOperator notOperator = Manager.getInstance(UMLManager.getInstance()).createNotOperator();
//				ExistsOperator existsOperator = Manager.getInstance(UMLManager.getInstance())
//						.createExistsOperator(getNodeToDelSVP(), getAllNodesMVP());
//		
//				notOperator.setArgument(existsOperator);
//				preAnd.getArguments().add(notOperator);
//		
//				specification.setRootOperator(preAnd);
//				postCondition.setConditionFormula(specification);
//				setPost(postCondition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UMLPackage.UML_DELETE_NODE_ACTION__NODE_TO_DEL_SVP:
				if (resolve) return getNodeToDelSVP();
				return basicGetNodeToDelSVP();
			case UMLPackage.UML_DELETE_NODE_ACTION__ALL_NODES_MVP:
				if (resolve) return getAllNodesMVP();
				return basicGetAllNodesMVP();
			case UMLPackage.UML_DELETE_NODE_ACTION__UML_NODE_TO_DEL:
				return getUmlNodeToDel();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case UMLPackage.UML_DELETE_NODE_ACTION__NODE_TO_DEL_SVP:
				setNodeToDelSVP((SingleValuedParameter)newValue);
				return;
			case UMLPackage.UML_DELETE_NODE_ACTION__ALL_NODES_MVP:
				setAllNodesMVP((MultipleValuedParameter)newValue);
				return;
			case UMLPackage.UML_DELETE_NODE_ACTION__UML_NODE_TO_DEL:
				setUmlNodeToDel((Node)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case UMLPackage.UML_DELETE_NODE_ACTION__NODE_TO_DEL_SVP:
				setNodeToDelSVP((SingleValuedParameter)null);
				return;
			case UMLPackage.UML_DELETE_NODE_ACTION__ALL_NODES_MVP:
				setAllNodesMVP((MultipleValuedParameter)null);
				return;
			case UMLPackage.UML_DELETE_NODE_ACTION__UML_NODE_TO_DEL:
				setUmlNodeToDel(UML_NODE_TO_DEL_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case UMLPackage.UML_DELETE_NODE_ACTION__NODE_TO_DEL_SVP:
				return nodeToDelSVP != null;
			case UMLPackage.UML_DELETE_NODE_ACTION__ALL_NODES_MVP:
				return allNodesMVP != null;
			case UMLPackage.UML_DELETE_NODE_ACTION__UML_NODE_TO_DEL:
				return UML_NODE_TO_DEL_EDEFAULT == null ? umlNodeToDel != null : !UML_NODE_TO_DEL_EDEFAULT.equals(umlNodeToDel);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case UMLPackage.UML_DELETE_NODE_ACTION___LOG:
				log();
				return null;
			case UMLPackage.UML_DELETE_NODE_ACTION___EXECUTE:
				execute();
				return null;
			case UMLPackage.UML_DELETE_NODE_ACTION___SET_PARAMETERS:
				setParameters();
				return null;
			case UMLPackage.UML_DELETE_NODE_ACTION___CREATE_PRE_CONDITION:
				createPreCondition();
				return null;
			case UMLPackage.UML_DELETE_NODE_ACTION___CREATE_POST_CONDITION:
				createPostCondition();
				return null;
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (umlNodeToDel: ");
		result.append(umlNodeToDel);
		result.append(')');
		return result.toString();
	}

} //UMLDeleteNodeActionImpl
