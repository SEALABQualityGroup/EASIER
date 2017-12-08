/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Headers;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see metamodel.mmaemilia.Headers.HeadersPackage
 * @generated
 */
public interface HeadersFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	HeadersFactory eINSTANCE = metamodel.mmaemilia.Headers.impl.HeadersFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>AT Header</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>AT Header</em>'.
	 * @generated
	 */
	AT_Header createAT_Header();

	/**
	 * Returns a new object of class '<em>Const Init</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Const Init</em>'.
	 * @generated
	 */
	ConstInit createConstInit();

	/**
	 * Returns a new object of class '<em>ET Header</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>ET Header</em>'.
	 * @generated
	 */
	ET_Header createET_Header();

	/**
	 * Returns a new object of class '<em>Const</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Const</em>'.
	 * @generated
	 */
	Const createConst();

	/**
	 * Returns a new object of class '<em>Behav Header</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Behav Header</em>'.
	 * @generated
	 */
	BehavHeader createBehavHeader();

	/**
	 * Returns a new object of class '<em>Left Side</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Left Side</em>'.
	 * @generated
	 */
	LeftSide createLeftSide();

	/**
	 * Returns a new object of class '<em>Right Side</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Right Side</em>'.
	 * @generated
	 */
	RightSide createRightSide();

	/**
	 * Returns a new object of class '<em>Var Init</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Var Init</em>'.
	 * @generated
	 */
	VarInit createVarInit();

	/**
	 * Returns a new object of class '<em>Var</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Var</em>'.
	 * @generated
	 */
	Var createVar();

	/**
	 * Returns a new object of class '<em>Local</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Local</em>'.
	 * @generated
	 */
	Local createLocal();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	HeadersPackage getHeadersPackage();

} //HeadersFactory
