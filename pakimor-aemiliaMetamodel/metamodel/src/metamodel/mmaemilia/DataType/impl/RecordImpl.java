/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.DataType.impl;

import java.util.Collection;

import metamodel.mmaemilia.DataType.DataTypePackage;
import metamodel.mmaemilia.DataType.Record;

import metamodel.mmaemilia.Headers.Var;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Record</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metamodel.mmaemilia.DataType.impl.RecordImpl#getField_decl_seq <em>Field decl seq</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RecordImpl extends NormalImpl implements Record {
	/**
	 * The cached value of the '{@link #getField_decl_seq() <em>Field decl seq</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getField_decl_seq()
	 * @generated
	 * @ordered
	 */
	protected EList<Var> field_decl_seq;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RecordImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DataTypePackage.Literals.RECORD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Var> getField_decl_seq() {
		if (field_decl_seq == null) {
			field_decl_seq = new EObjectContainmentEList<Var>(Var.class, this, DataTypePackage.RECORD__FIELD_DECL_SEQ);
		}
		return field_decl_seq;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DataTypePackage.RECORD__FIELD_DECL_SEQ:
				return ((InternalEList<?>)getField_decl_seq()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DataTypePackage.RECORD__FIELD_DECL_SEQ:
				return getField_decl_seq();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DataTypePackage.RECORD__FIELD_DECL_SEQ:
				getField_decl_seq().clear();
				getField_decl_seq().addAll((Collection<? extends Var>)newValue);
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
			case DataTypePackage.RECORD__FIELD_DECL_SEQ:
				getField_decl_seq().clear();
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
			case DataTypePackage.RECORD__FIELD_DECL_SEQ:
				return field_decl_seq != null && !field_decl_seq.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //RecordImpl
