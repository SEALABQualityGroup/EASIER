/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Behavior;

import metamodel.mmaemilia.Elem;
import metamodel.mmaemilia.ElemType;
import metamodel.mmaemilia.LocalInteraction;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.Behavior.Action#getName <em>Name</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.Action#getActThroughtput <em>Act Throughtput</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.Action#getActUtilization <em>Act Utilization</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.Action#getActResponseTime <em>Act Response Time</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.Action#getType <em>Type</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.Action#getRate <em>Rate</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.Action#getIs <em>Is</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.Action#getBelongs <em>Belongs</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.Action#getActThDistr <em>Act Th Distr</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.Action#getActRespTimeDistr <em>Act Resp Time Distr</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.Action#getActUtilDistr <em>Act Util Distr</em>}</li>
 *   <li>{@link metamodel.mmaemilia.Behavior.Action#getElem <em>Elem</em>}</li>
 * </ul>
 *
 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getAction()
 * @model
 * @generated
 */
public interface Action extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getAction_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Behavior.Action#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Act Throughtput</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Act Throughtput</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Act Throughtput</em>' attribute.
	 * @see #setActThroughtput(float)
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getAction_ActThroughtput()
	 * @model
	 * @generated
	 */
	float getActThroughtput();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Behavior.Action#getActThroughtput <em>Act Throughtput</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Act Throughtput</em>' attribute.
	 * @see #getActThroughtput()
	 * @generated
	 */
	void setActThroughtput(float value);

	/**
	 * Returns the value of the '<em><b>Act Utilization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Act Utilization</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Act Utilization</em>' attribute.
	 * @see #setActUtilization(float)
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getAction_ActUtilization()
	 * @model
	 * @generated
	 */
	float getActUtilization();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Behavior.Action#getActUtilization <em>Act Utilization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Act Utilization</em>' attribute.
	 * @see #getActUtilization()
	 * @generated
	 */
	void setActUtilization(float value);

	/**
	 * Returns the value of the '<em><b>Act Response Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Act Response Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Act Response Time</em>' attribute.
	 * @see #setActResponseTime(float)
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getAction_ActResponseTime()
	 * @model
	 * @generated
	 */
	float getActResponseTime();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Behavior.Action#getActResponseTime <em>Act Response Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Act Response Time</em>' attribute.
	 * @see #getActResponseTime()
	 * @generated
	 */
	void setActResponseTime(float value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' containment reference.
	 * @see #setType(ActionType)
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getAction_Type()
	 * @model containment="true"
	 * @generated
	 */
	ActionType getType();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Behavior.Action#getType <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' containment reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(ActionType value);

	/**
	 * Returns the value of the '<em><b>Rate</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rate</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rate</em>' containment reference.
	 * @see #setRate(ActionRate)
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getAction_Rate()
	 * @model containment="true"
	 * @generated
	 */
	ActionRate getRate();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Behavior.Action#getRate <em>Rate</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rate</em>' containment reference.
	 * @see #getRate()
	 * @generated
	 */
	void setRate(ActionRate value);

	/**
	 * Returns the value of the '<em><b>Is</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is</em>' containment reference.
	 * @see #setIs(LocalInteraction)
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getAction_Is()
	 * @model containment="true"
	 * @generated
	 */
	LocalInteraction getIs();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Behavior.Action#getIs <em>Is</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is</em>' containment reference.
	 * @see #getIs()
	 * @generated
	 */
	void setIs(LocalInteraction value);

	/**
	 * Returns the value of the '<em><b>Belongs</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Belongs</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Belongs</em>' reference.
	 * @see #setBelongs(ElemType)
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getAction_Belongs()
	 * @model
	 * @generated
	 */
	ElemType getBelongs();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Behavior.Action#getBelongs <em>Belongs</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Belongs</em>' reference.
	 * @see #getBelongs()
	 * @generated
	 */
	void setBelongs(ElemType value);

	/**
	 * Returns the value of the '<em><b>Act Th Distr</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Act Th Distr</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Act Th Distr</em>' attribute list.
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getAction_ActThDistr()
	 * @model
	 * @generated
	 */
	EList<String> getActThDistr();

	/**
	 * Returns the value of the '<em><b>Act Resp Time Distr</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Act Resp Time Distr</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Act Resp Time Distr</em>' attribute list.
	 * @see #isSetActRespTimeDistr()
	 * @see #unsetActRespTimeDistr()
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getAction_ActRespTimeDistr()
	 * @model default="" unsettable="true"
	 * @generated
	 */
	EList<String> getActRespTimeDistr();

	/**
	 * Unsets the value of the '{@link metamodel.mmaemilia.Behavior.Action#getActRespTimeDistr <em>Act Resp Time Distr</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetActRespTimeDistr()
	 * @see #getActRespTimeDistr()
	 * @generated
	 */
	void unsetActRespTimeDistr();

	/**
	 * Returns whether the value of the '{@link metamodel.mmaemilia.Behavior.Action#getActRespTimeDistr <em>Act Resp Time Distr</em>}' attribute list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Act Resp Time Distr</em>' attribute list is set.
	 * @see #unsetActRespTimeDistr()
	 * @see #getActRespTimeDistr()
	 * @generated
	 */
	boolean isSetActRespTimeDistr();

	/**
	 * Returns the value of the '<em><b>Act Util Distr</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Act Util Distr</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Act Util Distr</em>' attribute list.
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getAction_ActUtilDistr()
	 * @model
	 * @generated
	 */
	EList<String> getActUtilDistr();

	/**
	 * Returns the value of the '<em><b>Elem</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elem</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elem</em>' reference.
	 * @see #setElem(Elem)
	 * @see metamodel.mmaemilia.Behavior.BehaviorPackage#getAction_Elem()
	 * @model
	 * @generated
	 */
	Elem getElem();

	/**
	 * Sets the value of the '{@link metamodel.mmaemilia.Behavior.Action#getElem <em>Elem</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Elem</em>' reference.
	 * @see #getElem()
	 * @generated
	 */
	void setElem(Elem value);

} // Action
