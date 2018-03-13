/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package mapmeasurestoindices.impl;

import mapmeasurestoindices.AeiMeasure;
import mapmeasurestoindices.IndexType;
import mapmeasurestoindices.MapmeasurestoindicesPackage;

import metamodel.mmaemilia.ArchiElemInstance;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Aei Measure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link mapmeasurestoindices.impl.AeiMeasureImpl#getIndex <em>Index</em>}</li>
 *   <li>{@link mapmeasurestoindices.impl.AeiMeasureImpl#getAei <em>Aei</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AeiMeasureImpl extends EObjectImpl implements AeiMeasure {
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
	 * The cached value of the '{@link #getAei() <em>Aei</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAei()
	 * @generated
	 * @ordered
	 */
	protected ArchiElemInstance aei;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AeiMeasureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MapmeasurestoindicesPackage.Literals.AEI_MEASURE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MapmeasurestoindicesPackage.AEI_MEASURE__INDEX, oldIndex, index));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArchiElemInstance getAei() {
		if (aei != null && aei.eIsProxy()) {
			InternalEObject oldAei = (InternalEObject)aei;
			aei = (ArchiElemInstance)eResolveProxy(oldAei);
			if (aei != oldAei) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapmeasurestoindicesPackage.AEI_MEASURE__AEI, oldAei, aei));
			}
		}
		return aei;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArchiElemInstance basicGetAei() {
		return aei;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAei(ArchiElemInstance newAei) {
		ArchiElemInstance oldAei = aei;
		aei = newAei;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapmeasurestoindicesPackage.AEI_MEASURE__AEI, oldAei, aei));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MapmeasurestoindicesPackage.AEI_MEASURE__INDEX:
				return getIndex();
			case MapmeasurestoindicesPackage.AEI_MEASURE__AEI:
				if (resolve) return getAei();
				return basicGetAei();
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
			case MapmeasurestoindicesPackage.AEI_MEASURE__INDEX:
				setIndex((IndexType)newValue);
				return;
			case MapmeasurestoindicesPackage.AEI_MEASURE__AEI:
				setAei((ArchiElemInstance)newValue);
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
			case MapmeasurestoindicesPackage.AEI_MEASURE__INDEX:
				setIndex(INDEX_EDEFAULT);
				return;
			case MapmeasurestoindicesPackage.AEI_MEASURE__AEI:
				setAei((ArchiElemInstance)null);
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
			case MapmeasurestoindicesPackage.AEI_MEASURE__INDEX:
				return index != INDEX_EDEFAULT;
			case MapmeasurestoindicesPackage.AEI_MEASURE__AEI:
				return aei != null;
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

} //AeiMeasureImpl
