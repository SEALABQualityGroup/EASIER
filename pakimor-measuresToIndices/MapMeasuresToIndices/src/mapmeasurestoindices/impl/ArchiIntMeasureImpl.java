/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package mapmeasurestoindices.impl;

import mapmeasurestoindices.ArchiIntMeasure;
import mapmeasurestoindices.IndexType;
import mapmeasurestoindices.MapmeasurestoindicesPackage;

import metamodel.mmaemilia.ArchitecturalInteraction;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Archi Int Measure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link mapmeasurestoindices.impl.ArchiIntMeasureImpl#getIndex <em>Index</em>}</li>
 *   <li>{@link mapmeasurestoindices.impl.ArchiIntMeasureImpl#getArchiInteraction <em>Archi Interaction</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArchiIntMeasureImpl extends EObjectImpl implements ArchiIntMeasure {
	/**
	 * The default value of the '{@link #getIndex() <em>Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndex()
	 * @generated
	 * @ordered
	 */
	protected static final IndexType INDEX_EDEFAULT = IndexType.RESPONSE_TIME;

	/**
	 * The cached value of the '{@link #getIndex() <em>Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndex()
	 * @generated
	 * @ordered
	 */
	protected IndexType index = INDEX_EDEFAULT;

	/**
	 * The cached value of the '{@link #getArchiInteraction() <em>Archi Interaction</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArchiInteraction()
	 * @generated
	 * @ordered
	 */
	protected ArchitecturalInteraction archiInteraction;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArchiIntMeasureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MapmeasurestoindicesPackage.Literals.ARCHI_INT_MEASURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IndexType getIndex() {
		return index;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndex(IndexType newIndex) {
		IndexType oldIndex = index;
		index = newIndex == null ? INDEX_EDEFAULT : newIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapmeasurestoindicesPackage.ARCHI_INT_MEASURE__INDEX, oldIndex, index));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArchitecturalInteraction getArchiInteraction() {
		if (archiInteraction != null && archiInteraction.eIsProxy()) {
			InternalEObject oldArchiInteraction = (InternalEObject)archiInteraction;
			archiInteraction = (ArchitecturalInteraction)eResolveProxy(oldArchiInteraction);
			if (archiInteraction != oldArchiInteraction) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapmeasurestoindicesPackage.ARCHI_INT_MEASURE__ARCHI_INTERACTION, oldArchiInteraction, archiInteraction));
			}
		}
		return archiInteraction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArchitecturalInteraction basicGetArchiInteraction() {
		return archiInteraction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArchiInteraction(ArchitecturalInteraction newArchiInteraction) {
		ArchitecturalInteraction oldArchiInteraction = archiInteraction;
		archiInteraction = newArchiInteraction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapmeasurestoindicesPackage.ARCHI_INT_MEASURE__ARCHI_INTERACTION, oldArchiInteraction, archiInteraction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MapmeasurestoindicesPackage.ARCHI_INT_MEASURE__INDEX:
				return getIndex();
			case MapmeasurestoindicesPackage.ARCHI_INT_MEASURE__ARCHI_INTERACTION:
				if (resolve) return getArchiInteraction();
				return basicGetArchiInteraction();
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
			case MapmeasurestoindicesPackage.ARCHI_INT_MEASURE__INDEX:
				setIndex((IndexType)newValue);
				return;
			case MapmeasurestoindicesPackage.ARCHI_INT_MEASURE__ARCHI_INTERACTION:
				setArchiInteraction((ArchitecturalInteraction)newValue);
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
			case MapmeasurestoindicesPackage.ARCHI_INT_MEASURE__INDEX:
				setIndex(INDEX_EDEFAULT);
				return;
			case MapmeasurestoindicesPackage.ARCHI_INT_MEASURE__ARCHI_INTERACTION:
				setArchiInteraction((ArchitecturalInteraction)null);
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
			case MapmeasurestoindicesPackage.ARCHI_INT_MEASURE__INDEX:
				return index != INDEX_EDEFAULT;
			case MapmeasurestoindicesPackage.ARCHI_INT_MEASURE__ARCHI_INTERACTION:
				return archiInteraction != null;
		}
		return super.eIsSet(featureID);
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
		result.append(" (index: ");
		result.append(index);
		result.append(')');
		return result.toString();
	}

} //ArchiIntMeasureImpl
