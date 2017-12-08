/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package mapmeasurestoindices;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see mapmeasurestoindices.MapmeasurestoindicesPackage
 * @generated
 */
public interface MapmeasurestoindicesFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MapmeasurestoindicesFactory eINSTANCE = mapmeasurestoindices.impl.MapmeasurestoindicesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Measure Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Measure Mapping</em>'.
	 * @generated
	 */
	MeasureMapping createMeasureMapping();

	/**
	 * Returns a new object of class '<em>Measure To Archi Elem Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Measure To Archi Elem Instance</em>'.
	 * @generated
	 */
	MeasureToArchiElemInstance createMeasureToArchiElemInstance();

	/**
	 * Returns a new object of class '<em>Measure To Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Measure To Action</em>'.
	 * @generated
	 */
	MeasureToAction createMeasureToAction();

	/**
	 * Returns a new object of class '<em>Rew Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rew Mapping</em>'.
	 * @generated
	 */
	RewMapping createRewMapping();

	/**
	 * Returns a new object of class '<em>Action Measure</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Action Measure</em>'.
	 * @generated
	 */
	ActionMeasure createActionMeasure();

	/**
	 * Returns a new object of class '<em>Aei Measure</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Aei Measure</em>'.
	 * @generated
	 */
	AeiMeasure createAeiMeasure();

	/**
	 * Returns a new object of class '<em>Archi Int Measure</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Archi Int Measure</em>'.
	 * @generated
	 */
	ArchiIntMeasure createArchiIntMeasure();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	MapmeasurestoindicesPackage getMapmeasurestoindicesPackage();

} //MapmeasurestoindicesFactory
