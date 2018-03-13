/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package mapmeasurestoindices.impl;

import java.util.Collection;

import mapmeasurestoindices.ActionMeasure;
import mapmeasurestoindices.AeiMeasure;
import mapmeasurestoindices.ArchiIntMeasure;
import mapmeasurestoindices.MapmeasurestoindicesPackage;
import mapmeasurestoindices.MeasureMapping;

import metamodel.mmaemilia.Expressions.Expression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Measure Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link mapmeasurestoindices.impl.MeasureMappingImpl#getMeasureName <em>Measure Name</em>}</li>
 *   <li>{@link mapmeasurestoindices.impl.MeasureMappingImpl#getSelector <em>Selector</em>}</li>
 *   <li>{@link mapmeasurestoindices.impl.MeasureMappingImpl#getInstances <em>Instances</em>}</li>
 *   <li>{@link mapmeasurestoindices.impl.MeasureMappingImpl#getActions <em>Actions</em>}</li>
 *   <li>{@link mapmeasurestoindices.impl.MeasureMappingImpl#getArchiInteractions <em>Archi Interactions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MeasureMappingImpl extends EObjectImpl implements MeasureMapping {
	/**
	 * The default value of the '{@link #getMeasureName() <em>Measure Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeasureName()
	 * @generated
	 * @ordered
	 */
	protected static final String MEASURE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMeasureName() <em>Measure Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeasureName()
	 * @generated
	 * @ordered
	 */
	protected String measureName = MEASURE_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSelector() <em>Selector</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelector()
	 * @generated
	 * @ordered
	 */
	protected Expression selector;

	/**
	 * The cached value of the '{@link #getInstances() <em>Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstances()
	 * @generated
	 * @ordered
	 */
	protected EList<AeiMeasure> instances;

	/**
	 * The cached value of the '{@link #getActions() <em>Actions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActions()
	 * @generated
	 * @ordered
	 */
	protected EList<ActionMeasure> actions;

	/**
	 * The cached value of the '{@link #getArchiInteractions() <em>Archi Interactions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArchiInteractions()
	 * @generated
	 * @ordered
	 */
	protected EList<ArchiIntMeasure> archiInteractions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MeasureMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MapmeasurestoindicesPackage.Literals.MEASURE_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMeasureName() {
		return measureName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMeasureName(String newMeasureName) {
		String oldMeasureName = measureName;
		measureName = newMeasureName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapmeasurestoindicesPackage.MEASURE_MAPPING__MEASURE_NAME, oldMeasureName, measureName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getSelector() {
		return selector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSelector(Expression newSelector, NotificationChain msgs) {
		Expression oldSelector = selector;
		selector = newSelector;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapmeasurestoindicesPackage.MEASURE_MAPPING__SELECTOR, oldSelector, newSelector);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSelector(Expression newSelector) {
		if (newSelector != selector) {
			NotificationChain msgs = null;
			if (selector != null)
				msgs = ((InternalEObject)selector).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapmeasurestoindicesPackage.MEASURE_MAPPING__SELECTOR, null, msgs);
			if (newSelector != null)
				msgs = ((InternalEObject)newSelector).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MapmeasurestoindicesPackage.MEASURE_MAPPING__SELECTOR, null, msgs);
			msgs = basicSetSelector(newSelector, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapmeasurestoindicesPackage.MEASURE_MAPPING__SELECTOR, newSelector, newSelector));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AeiMeasure> getInstances() {
		if (instances == null) {
			instances = new EObjectContainmentEList<AeiMeasure>(AeiMeasure.class, this, MapmeasurestoindicesPackage.MEASURE_MAPPING__INSTANCES);
		}
		return instances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ActionMeasure> getActions() {
		if (actions == null) {
			actions = new EObjectContainmentEList<ActionMeasure>(ActionMeasure.class, this, MapmeasurestoindicesPackage.MEASURE_MAPPING__ACTIONS);
		}
		return actions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ArchiIntMeasure> getArchiInteractions() {
		if (archiInteractions == null) {
			archiInteractions = new EObjectContainmentEList<ArchiIntMeasure>(ArchiIntMeasure.class, this, MapmeasurestoindicesPackage.MEASURE_MAPPING__ARCHI_INTERACTIONS);
		}
		return archiInteractions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__SELECTOR:
				return basicSetSelector(null, msgs);
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__INSTANCES:
				return ((InternalEList<?>)getInstances()).basicRemove(otherEnd, msgs);
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__ACTIONS:
				return ((InternalEList<?>)getActions()).basicRemove(otherEnd, msgs);
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__ARCHI_INTERACTIONS:
				return ((InternalEList<?>)getArchiInteractions()).basicRemove(otherEnd, msgs);
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
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__MEASURE_NAME:
				return getMeasureName();
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__SELECTOR:
				return getSelector();
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__INSTANCES:
				return getInstances();
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__ACTIONS:
				return getActions();
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__ARCHI_INTERACTIONS:
				return getArchiInteractions();
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
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__MEASURE_NAME:
				setMeasureName((String)newValue);
				return;
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__SELECTOR:
				setSelector((Expression)newValue);
				return;
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__INSTANCES:
				getInstances().clear();
				getInstances().addAll((Collection<? extends AeiMeasure>)newValue);
				return;
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__ACTIONS:
				getActions().clear();
				getActions().addAll((Collection<? extends ActionMeasure>)newValue);
				return;
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__ARCHI_INTERACTIONS:
				getArchiInteractions().clear();
				getArchiInteractions().addAll((Collection<? extends ArchiIntMeasure>)newValue);
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
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__MEASURE_NAME:
				setMeasureName(MEASURE_NAME_EDEFAULT);
				return;
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__SELECTOR:
				setSelector((Expression)null);
				return;
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__INSTANCES:
				getInstances().clear();
				return;
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__ACTIONS:
				getActions().clear();
				return;
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__ARCHI_INTERACTIONS:
				getArchiInteractions().clear();
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
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__MEASURE_NAME:
				return MEASURE_NAME_EDEFAULT == null ? measureName != null : !MEASURE_NAME_EDEFAULT.equals(measureName);
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__SELECTOR:
				return selector != null;
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__INSTANCES:
				return instances != null && !instances.isEmpty();
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__ACTIONS:
				return actions != null && !actions.isEmpty();
			case MapmeasurestoindicesPackage.MEASURE_MAPPING__ARCHI_INTERACTIONS:
				return archiInteractions != null && !archiInteractions.isEmpty();
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
		result.append(" (measureName: ");
		result.append(measureName);
		result.append(')');
		return result.toString();
	}

} //MeasureMappingImpl
