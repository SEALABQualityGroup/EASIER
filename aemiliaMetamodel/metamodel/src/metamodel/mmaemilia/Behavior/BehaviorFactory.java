/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Behavior;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see metamodel.mmaemilia.Behavior.BehaviorPackage
 * @generated
 */
public interface BehaviorFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BehaviorFactory eINSTANCE = metamodel.mmaemilia.Behavior.impl.BehaviorFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Behavior</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Behavior</em>'.
	 * @generated
	 */
	Behavior createBehavior();

	/**
	 * Returns a new object of class '<em>Behav Equation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Behav Equation</em>'.
	 * @generated
	 */
	BehavEquation createBehavEquation();

	/**
	 * Returns a new object of class '<em>Process Term</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Process Term</em>'.
	 * @generated
	 */
	ProcessTerm createProcessTerm();

	/**
	 * Returns a new object of class '<em>Stop</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stop</em>'.
	 * @generated
	 */
	Stop createStop();

	/**
	 * Returns a new object of class '<em>Choice Process</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Choice Process</em>'.
	 * @generated
	 */
	ChoiceProcess createChoiceProcess();

	/**
	 * Returns a new object of class '<em>Action Process</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Action Process</em>'.
	 * @generated
	 */
	ActionProcess createActionProcess();

	/**
	 * Returns a new object of class '<em>Behav Process</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Behav Process</em>'.
	 * @generated
	 */
	BehavProcess createBehavProcess();

	/**
	 * Returns a new object of class '<em>Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Action</em>'.
	 * @generated
	 */
	Action createAction();

	/**
	 * Returns a new object of class '<em>Action Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Action Type</em>'.
	 * @generated
	 */
	ActionType createActionType();

	/**
	 * Returns a new object of class '<em>Action Input</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Action Input</em>'.
	 * @generated
	 */
	ActionInput createActionInput();

	/**
	 * Returns a new object of class '<em>Action Output</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Action Output</em>'.
	 * @generated
	 */
	ActionOutput createActionOutput();

	/**
	 * Returns a new object of class '<em>Rate Exp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rate Exp</em>'.
	 * @generated
	 */
	RateExp createRateExp();

	/**
	 * Returns a new object of class '<em>Rate Inf</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rate Inf</em>'.
	 * @generated
	 */
	RateInf createRateInf();

	/**
	 * Returns a new object of class '<em>Rate Pas</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rate Pas</em>'.
	 * @generated
	 */
	RatePas createRatePas();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	BehaviorPackage getBehaviorPackage();

} //BehaviorFactory
