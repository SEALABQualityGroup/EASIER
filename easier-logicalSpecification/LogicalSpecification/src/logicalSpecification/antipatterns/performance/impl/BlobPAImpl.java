/**
 */
package logicalSpecification.antipatterns.performance.impl;

import java.lang.reflect.InvocationTargetException;

import logicalSpecification.SingleValuedParameter;

import logicalSpecification.antipatterns.performance.BlobPA;
import logicalSpecification.antipatterns.performance.PerformancePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.uml2.uml.Component;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Blob PA</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.BlobPAImpl#getF_numClientConnectsSVP <em>Fnum Client Connects SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.BlobPAImpl#getF_numSupplierConnectsSVP <em>Fnum Supplier Connects SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.BlobPAImpl#getF_numSentMsgsSVP <em>Fnum Sent Msgs SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.BlobPAImpl#getF_numReceivedMsgsSVP <em>Fnum Received Msgs SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.BlobPAImpl#getF_hwUtilSVP <em>Fhw Util SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.BlobPAImpl#getF_netUtilSVP <em>Fnet Util SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.BlobPAImpl#getT_maxClientConnectsSVP <em>Tmax Client Connects SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.BlobPAImpl#getT_maxSupplierConnectsSVP <em>Tmax Supplier Connects SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.BlobPAImpl#getT_maxSentMsgsSVP <em>Tmax Sent Msgs SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.BlobPAImpl#getT_maxReceivedMsgsSVP <em>Tmax Received Msgs SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.BlobPAImpl#getT_maxHwUtilSVP <em>Tmax Hw Util SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.BlobPAImpl#getT_minNetUtilSVP <em>Tmin Net Util SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.impl.BlobPAImpl#getContextualElement <em>Contextual Element</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BlobPAImpl extends PerformanceAntipatternImpl implements BlobPA {
	/**
	 * The cached value of the '{@link #getF_numClientConnectsSVP() <em>Fnum Client Connects SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getF_numClientConnectsSVP()
	 * @generated
	 * @ordered
	 */
	protected SingleValuedParameter f_numClientConnectsSVP;

	/**
	 * The cached value of the '{@link #getF_numSupplierConnectsSVP() <em>Fnum Supplier Connects SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getF_numSupplierConnectsSVP()
	 * @generated
	 * @ordered
	 */
	protected SingleValuedParameter f_numSupplierConnectsSVP;

	/**
	 * The cached value of the '{@link #getF_numSentMsgsSVP() <em>Fnum Sent Msgs SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getF_numSentMsgsSVP()
	 * @generated
	 * @ordered
	 */
	protected SingleValuedParameter f_numSentMsgsSVP;

	/**
	 * The cached value of the '{@link #getF_numReceivedMsgsSVP() <em>Fnum Received Msgs SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getF_numReceivedMsgsSVP()
	 * @generated
	 * @ordered
	 */
	protected SingleValuedParameter f_numReceivedMsgsSVP;

	/**
	 * The cached value of the '{@link #getF_hwUtilSVP() <em>Fhw Util SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getF_hwUtilSVP()
	 * @generated
	 * @ordered
	 */
	protected SingleValuedParameter f_hwUtilSVP;

	/**
	 * The cached value of the '{@link #getF_netUtilSVP() <em>Fnet Util SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getF_netUtilSVP()
	 * @generated
	 * @ordered
	 */
	protected SingleValuedParameter f_netUtilSVP;

	/**
	 * The cached value of the '{@link #getT_maxClientConnectsSVP() <em>Tmax Client Connects SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getT_maxClientConnectsSVP()
	 * @generated
	 * @ordered
	 */
	protected SingleValuedParameter t_maxClientConnectsSVP;

	/**
	 * The cached value of the '{@link #getT_maxSupplierConnectsSVP() <em>Tmax Supplier Connects SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getT_maxSupplierConnectsSVP()
	 * @generated
	 * @ordered
	 */
	protected SingleValuedParameter t_maxSupplierConnectsSVP;

	/**
	 * The cached value of the '{@link #getT_maxSentMsgsSVP() <em>Tmax Sent Msgs SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getT_maxSentMsgsSVP()
	 * @generated
	 * @ordered
	 */
	protected SingleValuedParameter t_maxSentMsgsSVP;

	/**
	 * The cached value of the '{@link #getT_maxReceivedMsgsSVP() <em>Tmax Received Msgs SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getT_maxReceivedMsgsSVP()
	 * @generated
	 * @ordered
	 */
	protected SingleValuedParameter t_maxReceivedMsgsSVP;

	/**
	 * The cached value of the '{@link #getT_maxHwUtilSVP() <em>Tmax Hw Util SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getT_maxHwUtilSVP()
	 * @generated
	 * @ordered
	 */
	protected SingleValuedParameter t_maxHwUtilSVP;

	/**
	 * The cached value of the '{@link #getT_minNetUtilSVP() <em>Tmin Net Util SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getT_minNetUtilSVP()
	 * @generated
	 * @ordered
	 */
	protected SingleValuedParameter t_minNetUtilSVP;

	/**
	 * The default value of the '{@link #getContextualElement() <em>Contextual Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContextualElement()
	 * @generated
	 * @ordered
	 */
	protected static final Component CONTEXTUAL_ELEMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContextualElement() <em>Contextual Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContextualElement()
	 * @generated
	 * @ordered
	 */
	protected Component contextualElement = CONTEXTUAL_ELEMENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BlobPAImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PerformancePackage.Literals.BLOB_PA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter getF_numClientConnectsSVP() {
		if (f_numClientConnectsSVP != null && f_numClientConnectsSVP.eIsProxy()) {
			InternalEObject oldF_numClientConnectsSVP = (InternalEObject)f_numClientConnectsSVP;
			f_numClientConnectsSVP = (SingleValuedParameter)eResolveProxy(oldF_numClientConnectsSVP);
			if (f_numClientConnectsSVP != oldF_numClientConnectsSVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.BLOB_PA__FNUM_CLIENT_CONNECTS_SVP, oldF_numClientConnectsSVP, f_numClientConnectsSVP));
			}
		}
		return f_numClientConnectsSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter basicGetF_numClientConnectsSVP() {
		return f_numClientConnectsSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setF_numClientConnectsSVP(SingleValuedParameter newF_numClientConnectsSVP) {
		SingleValuedParameter oldF_numClientConnectsSVP = f_numClientConnectsSVP;
		f_numClientConnectsSVP = newF_numClientConnectsSVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.BLOB_PA__FNUM_CLIENT_CONNECTS_SVP, oldF_numClientConnectsSVP, f_numClientConnectsSVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter getF_numSupplierConnectsSVP() {
		if (f_numSupplierConnectsSVP != null && f_numSupplierConnectsSVP.eIsProxy()) {
			InternalEObject oldF_numSupplierConnectsSVP = (InternalEObject)f_numSupplierConnectsSVP;
			f_numSupplierConnectsSVP = (SingleValuedParameter)eResolveProxy(oldF_numSupplierConnectsSVP);
			if (f_numSupplierConnectsSVP != oldF_numSupplierConnectsSVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.BLOB_PA__FNUM_SUPPLIER_CONNECTS_SVP, oldF_numSupplierConnectsSVP, f_numSupplierConnectsSVP));
			}
		}
		return f_numSupplierConnectsSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter basicGetF_numSupplierConnectsSVP() {
		return f_numSupplierConnectsSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setF_numSupplierConnectsSVP(SingleValuedParameter newF_numSupplierConnectsSVP) {
		SingleValuedParameter oldF_numSupplierConnectsSVP = f_numSupplierConnectsSVP;
		f_numSupplierConnectsSVP = newF_numSupplierConnectsSVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.BLOB_PA__FNUM_SUPPLIER_CONNECTS_SVP, oldF_numSupplierConnectsSVP, f_numSupplierConnectsSVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter getF_numSentMsgsSVP() {
		if (f_numSentMsgsSVP != null && f_numSentMsgsSVP.eIsProxy()) {
			InternalEObject oldF_numSentMsgsSVP = (InternalEObject)f_numSentMsgsSVP;
			f_numSentMsgsSVP = (SingleValuedParameter)eResolveProxy(oldF_numSentMsgsSVP);
			if (f_numSentMsgsSVP != oldF_numSentMsgsSVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.BLOB_PA__FNUM_SENT_MSGS_SVP, oldF_numSentMsgsSVP, f_numSentMsgsSVP));
			}
		}
		return f_numSentMsgsSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter basicGetF_numSentMsgsSVP() {
		return f_numSentMsgsSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setF_numSentMsgsSVP(SingleValuedParameter newF_numSentMsgsSVP) {
		SingleValuedParameter oldF_numSentMsgsSVP = f_numSentMsgsSVP;
		f_numSentMsgsSVP = newF_numSentMsgsSVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.BLOB_PA__FNUM_SENT_MSGS_SVP, oldF_numSentMsgsSVP, f_numSentMsgsSVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter getF_numReceivedMsgsSVP() {
		if (f_numReceivedMsgsSVP != null && f_numReceivedMsgsSVP.eIsProxy()) {
			InternalEObject oldF_numReceivedMsgsSVP = (InternalEObject)f_numReceivedMsgsSVP;
			f_numReceivedMsgsSVP = (SingleValuedParameter)eResolveProxy(oldF_numReceivedMsgsSVP);
			if (f_numReceivedMsgsSVP != oldF_numReceivedMsgsSVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.BLOB_PA__FNUM_RECEIVED_MSGS_SVP, oldF_numReceivedMsgsSVP, f_numReceivedMsgsSVP));
			}
		}
		return f_numReceivedMsgsSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter basicGetF_numReceivedMsgsSVP() {
		return f_numReceivedMsgsSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setF_numReceivedMsgsSVP(SingleValuedParameter newF_numReceivedMsgsSVP) {
		SingleValuedParameter oldF_numReceivedMsgsSVP = f_numReceivedMsgsSVP;
		f_numReceivedMsgsSVP = newF_numReceivedMsgsSVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.BLOB_PA__FNUM_RECEIVED_MSGS_SVP, oldF_numReceivedMsgsSVP, f_numReceivedMsgsSVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter getF_hwUtilSVP() {
		if (f_hwUtilSVP != null && f_hwUtilSVP.eIsProxy()) {
			InternalEObject oldF_hwUtilSVP = (InternalEObject)f_hwUtilSVP;
			f_hwUtilSVP = (SingleValuedParameter)eResolveProxy(oldF_hwUtilSVP);
			if (f_hwUtilSVP != oldF_hwUtilSVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.BLOB_PA__FHW_UTIL_SVP, oldF_hwUtilSVP, f_hwUtilSVP));
			}
		}
		return f_hwUtilSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter basicGetF_hwUtilSVP() {
		return f_hwUtilSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setF_hwUtilSVP(SingleValuedParameter newF_hwUtilSVP) {
		SingleValuedParameter oldF_hwUtilSVP = f_hwUtilSVP;
		f_hwUtilSVP = newF_hwUtilSVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.BLOB_PA__FHW_UTIL_SVP, oldF_hwUtilSVP, f_hwUtilSVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter getF_netUtilSVP() {
		if (f_netUtilSVP != null && f_netUtilSVP.eIsProxy()) {
			InternalEObject oldF_netUtilSVP = (InternalEObject)f_netUtilSVP;
			f_netUtilSVP = (SingleValuedParameter)eResolveProxy(oldF_netUtilSVP);
			if (f_netUtilSVP != oldF_netUtilSVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.BLOB_PA__FNET_UTIL_SVP, oldF_netUtilSVP, f_netUtilSVP));
			}
		}
		return f_netUtilSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter basicGetF_netUtilSVP() {
		return f_netUtilSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setF_netUtilSVP(SingleValuedParameter newF_netUtilSVP) {
		SingleValuedParameter oldF_netUtilSVP = f_netUtilSVP;
		f_netUtilSVP = newF_netUtilSVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.BLOB_PA__FNET_UTIL_SVP, oldF_netUtilSVP, f_netUtilSVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter getT_maxClientConnectsSVP() {
		if (t_maxClientConnectsSVP != null && t_maxClientConnectsSVP.eIsProxy()) {
			InternalEObject oldT_maxClientConnectsSVP = (InternalEObject)t_maxClientConnectsSVP;
			t_maxClientConnectsSVP = (SingleValuedParameter)eResolveProxy(oldT_maxClientConnectsSVP);
			if (t_maxClientConnectsSVP != oldT_maxClientConnectsSVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.BLOB_PA__TMAX_CLIENT_CONNECTS_SVP, oldT_maxClientConnectsSVP, t_maxClientConnectsSVP));
			}
		}
		return t_maxClientConnectsSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter basicGetT_maxClientConnectsSVP() {
		return t_maxClientConnectsSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setT_maxClientConnectsSVP(SingleValuedParameter newT_maxClientConnectsSVP) {
		SingleValuedParameter oldT_maxClientConnectsSVP = t_maxClientConnectsSVP;
		t_maxClientConnectsSVP = newT_maxClientConnectsSVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.BLOB_PA__TMAX_CLIENT_CONNECTS_SVP, oldT_maxClientConnectsSVP, t_maxClientConnectsSVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter getT_maxSupplierConnectsSVP() {
		if (t_maxSupplierConnectsSVP != null && t_maxSupplierConnectsSVP.eIsProxy()) {
			InternalEObject oldT_maxSupplierConnectsSVP = (InternalEObject)t_maxSupplierConnectsSVP;
			t_maxSupplierConnectsSVP = (SingleValuedParameter)eResolveProxy(oldT_maxSupplierConnectsSVP);
			if (t_maxSupplierConnectsSVP != oldT_maxSupplierConnectsSVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.BLOB_PA__TMAX_SUPPLIER_CONNECTS_SVP, oldT_maxSupplierConnectsSVP, t_maxSupplierConnectsSVP));
			}
		}
		return t_maxSupplierConnectsSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter basicGetT_maxSupplierConnectsSVP() {
		return t_maxSupplierConnectsSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setT_maxSupplierConnectsSVP(SingleValuedParameter newT_maxSupplierConnectsSVP) {
		SingleValuedParameter oldT_maxSupplierConnectsSVP = t_maxSupplierConnectsSVP;
		t_maxSupplierConnectsSVP = newT_maxSupplierConnectsSVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.BLOB_PA__TMAX_SUPPLIER_CONNECTS_SVP, oldT_maxSupplierConnectsSVP, t_maxSupplierConnectsSVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter getT_maxSentMsgsSVP() {
		if (t_maxSentMsgsSVP != null && t_maxSentMsgsSVP.eIsProxy()) {
			InternalEObject oldT_maxSentMsgsSVP = (InternalEObject)t_maxSentMsgsSVP;
			t_maxSentMsgsSVP = (SingleValuedParameter)eResolveProxy(oldT_maxSentMsgsSVP);
			if (t_maxSentMsgsSVP != oldT_maxSentMsgsSVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.BLOB_PA__TMAX_SENT_MSGS_SVP, oldT_maxSentMsgsSVP, t_maxSentMsgsSVP));
			}
		}
		return t_maxSentMsgsSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter basicGetT_maxSentMsgsSVP() {
		return t_maxSentMsgsSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setT_maxSentMsgsSVP(SingleValuedParameter newT_maxSentMsgsSVP) {
		SingleValuedParameter oldT_maxSentMsgsSVP = t_maxSentMsgsSVP;
		t_maxSentMsgsSVP = newT_maxSentMsgsSVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.BLOB_PA__TMAX_SENT_MSGS_SVP, oldT_maxSentMsgsSVP, t_maxSentMsgsSVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter getT_maxReceivedMsgsSVP() {
		if (t_maxReceivedMsgsSVP != null && t_maxReceivedMsgsSVP.eIsProxy()) {
			InternalEObject oldT_maxReceivedMsgsSVP = (InternalEObject)t_maxReceivedMsgsSVP;
			t_maxReceivedMsgsSVP = (SingleValuedParameter)eResolveProxy(oldT_maxReceivedMsgsSVP);
			if (t_maxReceivedMsgsSVP != oldT_maxReceivedMsgsSVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.BLOB_PA__TMAX_RECEIVED_MSGS_SVP, oldT_maxReceivedMsgsSVP, t_maxReceivedMsgsSVP));
			}
		}
		return t_maxReceivedMsgsSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter basicGetT_maxReceivedMsgsSVP() {
		return t_maxReceivedMsgsSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setT_maxReceivedMsgsSVP(SingleValuedParameter newT_maxReceivedMsgsSVP) {
		SingleValuedParameter oldT_maxReceivedMsgsSVP = t_maxReceivedMsgsSVP;
		t_maxReceivedMsgsSVP = newT_maxReceivedMsgsSVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.BLOB_PA__TMAX_RECEIVED_MSGS_SVP, oldT_maxReceivedMsgsSVP, t_maxReceivedMsgsSVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter getT_maxHwUtilSVP() {
		if (t_maxHwUtilSVP != null && t_maxHwUtilSVP.eIsProxy()) {
			InternalEObject oldT_maxHwUtilSVP = (InternalEObject)t_maxHwUtilSVP;
			t_maxHwUtilSVP = (SingleValuedParameter)eResolveProxy(oldT_maxHwUtilSVP);
			if (t_maxHwUtilSVP != oldT_maxHwUtilSVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.BLOB_PA__TMAX_HW_UTIL_SVP, oldT_maxHwUtilSVP, t_maxHwUtilSVP));
			}
		}
		return t_maxHwUtilSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter basicGetT_maxHwUtilSVP() {
		return t_maxHwUtilSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setT_maxHwUtilSVP(SingleValuedParameter newT_maxHwUtilSVP) {
		SingleValuedParameter oldT_maxHwUtilSVP = t_maxHwUtilSVP;
		t_maxHwUtilSVP = newT_maxHwUtilSVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.BLOB_PA__TMAX_HW_UTIL_SVP, oldT_maxHwUtilSVP, t_maxHwUtilSVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter getT_minNetUtilSVP() {
		if (t_minNetUtilSVP != null && t_minNetUtilSVP.eIsProxy()) {
			InternalEObject oldT_minNetUtilSVP = (InternalEObject)t_minNetUtilSVP;
			t_minNetUtilSVP = (SingleValuedParameter)eResolveProxy(oldT_minNetUtilSVP);
			if (t_minNetUtilSVP != oldT_minNetUtilSVP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.BLOB_PA__TMIN_NET_UTIL_SVP, oldT_minNetUtilSVP, t_minNetUtilSVP));
			}
		}
		return t_minNetUtilSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleValuedParameter basicGetT_minNetUtilSVP() {
		return t_minNetUtilSVP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setT_minNetUtilSVP(SingleValuedParameter newT_minNetUtilSVP) {
		SingleValuedParameter oldT_minNetUtilSVP = t_minNetUtilSVP;
		t_minNetUtilSVP = newT_minNetUtilSVP;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.BLOB_PA__TMIN_NET_UTIL_SVP, oldT_minNetUtilSVP, t_minNetUtilSVP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component getContextualElement() {
		return contextualElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContextualElement(Component newContextualElement) {
		Component oldContextualElement = contextualElement;
		contextualElement = newContextualElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.BLOB_PA__CONTEXTUAL_ELEMENT, oldContextualElement, contextualElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParametersForPartialDetection(final double T_maxClientConnects, final double T_maxSupplierConnects, final double T_maxSentMsgs, final double T_maxReceivedMsgs) {
		List<Parameter> blobParams = new ArrayList<>();
		
				setF_numClientConnectsSVP(Manager.getInstance(null).createSingleValueParameter(
						(OclStringManager.getInstance(null)).getClientConnectsQuery(getContextualElement())));
				blobParams.add(getF_numClientConnectsSVP());
		
				setT_maxClientConnectsSVP(
						Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxClientConnects)));
				blobParams.add(getT_maxClientConnectsSVP());
		
				setF_numSupplierConnectsSVP(Manager.getInstance(null).createSingleValueParameter(
						(OclStringManager.getInstance(null)).getSupplierConnectsQuery(getContextualElement())));
				blobParams.add(getF_numSupplierConnectsSVP());
		
				setT_maxSupplierConnectsSVP(
						Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxSupplierConnects)));
				blobParams.add(getT_maxSupplierConnectsSVP());
		
				setF_numSentMsgsSVP(Manager.getInstance(null).createSingleValueParameter(
						(OclStringManager.getInstance(null)).getNumSentMsgsQuery(getContextualElement())));
				blobParams.add(getF_numSentMsgsSVP());
		
				setT_maxSentMsgsSVP(Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxSentMsgs)));
				blobParams.add(getT_maxSentMsgsSVP());
		
				setF_numReceivedMsgsSVP(Manager.getInstance(null).createSingleValueParameter(
						(OclStringManager.getInstance(null)).getNumReceivedMsgsQuery(getContextualElement())));
				blobParams.add(getF_numReceivedMsgsSVP());
		
				setT_maxReceivedMsgsSVP(
						Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxReceivedMsgs)));
				blobParams.add(getT_maxReceivedMsgsSVP());
		
				if (getF_hwUtilSVP() != null)
					if (getF_hwUtilSVP().getResolvingExpr() != null || !getF_hwUtilSVP().getResolvingExpr().equals(""))
						getF_hwUtilSVP().setResolvingExpr(null);
		
				if (getT_maxHwUtilSVP() != null)
					if (getT_maxHwUtilSVP().getResolvingExpr() != null || !getT_maxHwUtilSVP().getResolvingExpr().equals(""))
						getT_maxHwUtilSVP().setResolvingExpr(null);
		
				if (getF_netUtilSVP() != null)
					if (getF_netUtilSVP().getResolvingExpr() != null || !getF_netUtilSVP().getResolvingExpr().equals(""))
						getF_netUtilSVP().setResolvingExpr(null);
		
				if (getT_minNetUtilSVP() != null)
					if (getT_minNetUtilSVP().getResolvingExpr() != null || !getT_minNetUtilSVP().getResolvingExpr().equals(""))
						getT_minNetUtilSVP().setResolvingExpr(null);
		
				getParameters().addAll(blobParams);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParametersForFullDetection(final double T_maxClientConnects, final double T_maxSupplierConnects, final double T_maxSentMsgs, final double T_maxReceivedMsgs, final double T_maxHwUtil, final double T_minNetUtil) {
		List<Parameter> blobParams = new ArrayList<>();
		
				setF_numClientConnectsSVP(Manager.getInstance(null).createSingleValueParameter(
						OclStringManager.getInstance(null).getClientConnectsQuery(getContextualElement())));
				blobParams.add(getF_numClientConnectsSVP());
		
				setT_maxClientConnectsSVP(
						Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxClientConnects)));
				blobParams.add(getT_maxClientConnectsSVP());
		
				setF_numSupplierConnectsSVP(Manager.getInstance(null).createSingleValueParameter(
						OclStringManager.getInstance(null).getSupplierConnectsQuery(getContextualElement())));
				blobParams.add(getF_numSupplierConnectsSVP());
		
				setT_maxSupplierConnectsSVP(
						Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxSupplierConnects)));
				blobParams.add(getT_maxSupplierConnectsSVP());
		
				setF_numSentMsgsSVP(Manager.getInstance(null).createSingleValueParameter(
						OclStringManager.getInstance(null).getNumSentMsgsQuery(getContextualElement())));
				blobParams.add(getF_numSentMsgsSVP());
		
				setT_maxSentMsgsSVP(Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxSentMsgs)));
				blobParams.add(getT_maxSentMsgsSVP());
		
				setF_numReceivedMsgsSVP(Manager.getInstance(null).createSingleValueParameter(
						OclStringManager.getInstance(null).getNumReceivedMsgsQuery(getContextualElement())));
				blobParams.add(getF_numReceivedMsgsSVP());
		
				setT_maxReceivedMsgsSVP(
						Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxReceivedMsgs)));
				blobParams.add(getT_maxReceivedMsgsSVP());
		
				setF_hwUtilSVP(Manager.getInstance(null)
						.createSingleValueParameter(OclStringManager.getInstance(null).getHwUtilQuery(getContextualElement())));
				blobParams.add(getF_hwUtilSVP());
		
				setT_maxHwUtilSVP(Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxHwUtil)));
				blobParams.add(getT_maxHwUtilSVP());
		
				setF_netUtilSVP(Manager.getInstance(null).createSingleValueParameter(
						OclStringManager.getInstance(null).getNetUtilQuery(getContextualElement())));
				blobParams.add(getF_netUtilSVP());
		
				setT_minNetUtilSVP(Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_minNetUtil)));
				blobParams.add(getT_minNetUtilSVP());
		
				getParameters().addAll(blobParams);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createFormula() {
		FOLSpecification blobCF = LogicalSpecificationFactory.eINSTANCE.createFOLSpecification();
				blobCF.setName("BlobFormula");
		
				AndOperator blobCFAnd = LogicalSpecificationFactory.eINSTANCE.createAndOperator();
		
				OrOperator blobCFAndOr1 = LogicalSpecificationFactory.eINSTANCE.createOrOperator();
				GreaterEqualOperator blobCFAndOr1Geq1 = LogicalSpecificationFactory.eINSTANCE.createGreaterEqualOperator();
				blobCFAndOr1Geq1.setLhs(getF_numClientConnectsSVP());
				blobCFAndOr1Geq1.setRhs(getT_maxClientConnectsSVP());
				GreaterEqualOperator blobCFAndOr1Geq2 = LogicalSpecificationFactory.eINSTANCE.createGreaterEqualOperator();
				blobCFAndOr1Geq2.setLhs(getF_numSupplierConnectsSVP());
				blobCFAndOr1Geq2.setRhs(getT_maxSupplierConnectsSVP());
				blobCFAndOr1.getArguments().add(blobCFAndOr1Geq1);
				blobCFAndOr1.getArguments().add(blobCFAndOr1Geq2);
		
				OrOperator blobCFAndOr2 = LogicalSpecificationFactory.eINSTANCE.createOrOperator();
				GreaterEqualOperator blobCFAndOr2Geq1 = LogicalSpecificationFactory.eINSTANCE.createGreaterEqualOperator();
				blobCFAndOr2Geq1.setLhs(getF_numSentMsgsSVP());
				blobCFAndOr2Geq1.setRhs(getT_maxSentMsgsSVP());
				GreaterEqualOperator blobCFAndOr2Geq2 = LogicalSpecificationFactory.eINSTANCE.createGreaterEqualOperator();
				blobCFAndOr2Geq2.setLhs(getF_numReceivedMsgsSVP());
				blobCFAndOr2Geq2.setRhs(getT_maxReceivedMsgsSVP());
				blobCFAndOr2.getArguments().add(blobCFAndOr2Geq1);
				blobCFAndOr2.getArguments().add(blobCFAndOr2Geq2);
		
				OrOperator blobCFAndOr3 = LogicalSpecificationFactory.eINSTANCE.createOrOperator();
				GreaterEqualOperator blobCFAndOr3Geq1 = LogicalSpecificationFactory.eINSTANCE.createGreaterEqualOperator();
				blobCFAndOr3Geq1.setLhs(getF_hwUtilSVP());
				blobCFAndOr3Geq1.setRhs(getT_maxHwUtilSVP());
				LessEqualOperator blobCFAndOr3Geq2 = LogicalSpecificationFactory.eINSTANCE.createLessEqualOperator();
				blobCFAndOr3Geq2.setLhs(getF_netUtilSVP());
				blobCFAndOr3Geq2.setRhs(getT_minNetUtilSVP());
				blobCFAndOr3.getArguments().add(blobCFAndOr3Geq1);
				blobCFAndOr3.getArguments().add(blobCFAndOr3Geq2);
		
				blobCFAnd.getArguments().add(blobCFAndOr1);
				blobCFAnd.getArguments().add(blobCFAndOr2);
				blobCFAnd.getArguments().add(blobCFAndOr3);
				blobCF.setRootOperator(blobCFAnd);
				setFormula(blobCF);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int partialDetection(final double T_maxClientConnects, final double T_maxSupplierConnects, final double T_maxSentMsgs, final double T_maxReceivedMsgs) {
		int occurrences = 0;
				setParametersForPartialDetection(T_maxClientConnects, T_maxSupplierConnects, T_maxSentMsgs, T_maxReceivedMsgs);
				createFormula();
				List<Element> components = ((Model) Manager.getInstance(UMLManager.getInstance()).getModel())
						.getNestedPackages().get(0).getNestedPackages().get(0).allOwnedElements();
				for (Element c : components) {
					if (c instanceof Component && c.getAppliedStereotype("PaRunTInstance") != null) {
						try {
							boolean app = Manager.getInstance(null)
									.evaluateOperator((AndOperator) getFormula().getRootOperator(), (Component) c);
							if (app)
								occurrences++;
						} catch (ParserException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				return occurrences;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int fullDetection(final double T_maxClientConnects, final double T_maxSupplierConnects, final double T_maxSentMsgs, final double T_maxReceivedMsgs, final double T_maxHwUtil, final double T_minNetUtil) {
		int occurrences = 0;
				setParametersForFullDetection(T_maxClientConnects, T_maxSupplierConnects, T_maxSentMsgs, T_maxReceivedMsgs,
						T_maxHwUtil, T_minNetUtil);
				createFormula();
				List<Element> components = ((Model) Manager.getInstance(UMLManager.getInstance()).getModel())
						.getNestedPackages().get(0).getNestedPackages().get(0).allOwnedElements();
				for (Element c : components) {
					if (c instanceof Component && c.getAppliedStereotype("PaRunTInstance") != null) {
						try {
							boolean app = Manager.getInstance(null)
									.evaluateOperator((AndOperator) getFormula().getRootOperator(), (Component) c);
							if (app)
								occurrences++;
						} catch (ParserException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				return occurrences;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void log() {
		super.log();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PerformancePackage.BLOB_PA__FNUM_CLIENT_CONNECTS_SVP:
				if (resolve) return getF_numClientConnectsSVP();
				return basicGetF_numClientConnectsSVP();
			case PerformancePackage.BLOB_PA__FNUM_SUPPLIER_CONNECTS_SVP:
				if (resolve) return getF_numSupplierConnectsSVP();
				return basicGetF_numSupplierConnectsSVP();
			case PerformancePackage.BLOB_PA__FNUM_SENT_MSGS_SVP:
				if (resolve) return getF_numSentMsgsSVP();
				return basicGetF_numSentMsgsSVP();
			case PerformancePackage.BLOB_PA__FNUM_RECEIVED_MSGS_SVP:
				if (resolve) return getF_numReceivedMsgsSVP();
				return basicGetF_numReceivedMsgsSVP();
			case PerformancePackage.BLOB_PA__FHW_UTIL_SVP:
				if (resolve) return getF_hwUtilSVP();
				return basicGetF_hwUtilSVP();
			case PerformancePackage.BLOB_PA__FNET_UTIL_SVP:
				if (resolve) return getF_netUtilSVP();
				return basicGetF_netUtilSVP();
			case PerformancePackage.BLOB_PA__TMAX_CLIENT_CONNECTS_SVP:
				if (resolve) return getT_maxClientConnectsSVP();
				return basicGetT_maxClientConnectsSVP();
			case PerformancePackage.BLOB_PA__TMAX_SUPPLIER_CONNECTS_SVP:
				if (resolve) return getT_maxSupplierConnectsSVP();
				return basicGetT_maxSupplierConnectsSVP();
			case PerformancePackage.BLOB_PA__TMAX_SENT_MSGS_SVP:
				if (resolve) return getT_maxSentMsgsSVP();
				return basicGetT_maxSentMsgsSVP();
			case PerformancePackage.BLOB_PA__TMAX_RECEIVED_MSGS_SVP:
				if (resolve) return getT_maxReceivedMsgsSVP();
				return basicGetT_maxReceivedMsgsSVP();
			case PerformancePackage.BLOB_PA__TMAX_HW_UTIL_SVP:
				if (resolve) return getT_maxHwUtilSVP();
				return basicGetT_maxHwUtilSVP();
			case PerformancePackage.BLOB_PA__TMIN_NET_UTIL_SVP:
				if (resolve) return getT_minNetUtilSVP();
				return basicGetT_minNetUtilSVP();
			case PerformancePackage.BLOB_PA__CONTEXTUAL_ELEMENT:
				return getContextualElement();
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
			case PerformancePackage.BLOB_PA__FNUM_CLIENT_CONNECTS_SVP:
				setF_numClientConnectsSVP((SingleValuedParameter)newValue);
				return;
			case PerformancePackage.BLOB_PA__FNUM_SUPPLIER_CONNECTS_SVP:
				setF_numSupplierConnectsSVP((SingleValuedParameter)newValue);
				return;
			case PerformancePackage.BLOB_PA__FNUM_SENT_MSGS_SVP:
				setF_numSentMsgsSVP((SingleValuedParameter)newValue);
				return;
			case PerformancePackage.BLOB_PA__FNUM_RECEIVED_MSGS_SVP:
				setF_numReceivedMsgsSVP((SingleValuedParameter)newValue);
				return;
			case PerformancePackage.BLOB_PA__FHW_UTIL_SVP:
				setF_hwUtilSVP((SingleValuedParameter)newValue);
				return;
			case PerformancePackage.BLOB_PA__FNET_UTIL_SVP:
				setF_netUtilSVP((SingleValuedParameter)newValue);
				return;
			case PerformancePackage.BLOB_PA__TMAX_CLIENT_CONNECTS_SVP:
				setT_maxClientConnectsSVP((SingleValuedParameter)newValue);
				return;
			case PerformancePackage.BLOB_PA__TMAX_SUPPLIER_CONNECTS_SVP:
				setT_maxSupplierConnectsSVP((SingleValuedParameter)newValue);
				return;
			case PerformancePackage.BLOB_PA__TMAX_SENT_MSGS_SVP:
				setT_maxSentMsgsSVP((SingleValuedParameter)newValue);
				return;
			case PerformancePackage.BLOB_PA__TMAX_RECEIVED_MSGS_SVP:
				setT_maxReceivedMsgsSVP((SingleValuedParameter)newValue);
				return;
			case PerformancePackage.BLOB_PA__TMAX_HW_UTIL_SVP:
				setT_maxHwUtilSVP((SingleValuedParameter)newValue);
				return;
			case PerformancePackage.BLOB_PA__TMIN_NET_UTIL_SVP:
				setT_minNetUtilSVP((SingleValuedParameter)newValue);
				return;
			case PerformancePackage.BLOB_PA__CONTEXTUAL_ELEMENT:
				setContextualElement((Component)newValue);
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
			case PerformancePackage.BLOB_PA__FNUM_CLIENT_CONNECTS_SVP:
				setF_numClientConnectsSVP((SingleValuedParameter)null);
				return;
			case PerformancePackage.BLOB_PA__FNUM_SUPPLIER_CONNECTS_SVP:
				setF_numSupplierConnectsSVP((SingleValuedParameter)null);
				return;
			case PerformancePackage.BLOB_PA__FNUM_SENT_MSGS_SVP:
				setF_numSentMsgsSVP((SingleValuedParameter)null);
				return;
			case PerformancePackage.BLOB_PA__FNUM_RECEIVED_MSGS_SVP:
				setF_numReceivedMsgsSVP((SingleValuedParameter)null);
				return;
			case PerformancePackage.BLOB_PA__FHW_UTIL_SVP:
				setF_hwUtilSVP((SingleValuedParameter)null);
				return;
			case PerformancePackage.BLOB_PA__FNET_UTIL_SVP:
				setF_netUtilSVP((SingleValuedParameter)null);
				return;
			case PerformancePackage.BLOB_PA__TMAX_CLIENT_CONNECTS_SVP:
				setT_maxClientConnectsSVP((SingleValuedParameter)null);
				return;
			case PerformancePackage.BLOB_PA__TMAX_SUPPLIER_CONNECTS_SVP:
				setT_maxSupplierConnectsSVP((SingleValuedParameter)null);
				return;
			case PerformancePackage.BLOB_PA__TMAX_SENT_MSGS_SVP:
				setT_maxSentMsgsSVP((SingleValuedParameter)null);
				return;
			case PerformancePackage.BLOB_PA__TMAX_RECEIVED_MSGS_SVP:
				setT_maxReceivedMsgsSVP((SingleValuedParameter)null);
				return;
			case PerformancePackage.BLOB_PA__TMAX_HW_UTIL_SVP:
				setT_maxHwUtilSVP((SingleValuedParameter)null);
				return;
			case PerformancePackage.BLOB_PA__TMIN_NET_UTIL_SVP:
				setT_minNetUtilSVP((SingleValuedParameter)null);
				return;
			case PerformancePackage.BLOB_PA__CONTEXTUAL_ELEMENT:
				setContextualElement(CONTEXTUAL_ELEMENT_EDEFAULT);
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
			case PerformancePackage.BLOB_PA__FNUM_CLIENT_CONNECTS_SVP:
				return f_numClientConnectsSVP != null;
			case PerformancePackage.BLOB_PA__FNUM_SUPPLIER_CONNECTS_SVP:
				return f_numSupplierConnectsSVP != null;
			case PerformancePackage.BLOB_PA__FNUM_SENT_MSGS_SVP:
				return f_numSentMsgsSVP != null;
			case PerformancePackage.BLOB_PA__FNUM_RECEIVED_MSGS_SVP:
				return f_numReceivedMsgsSVP != null;
			case PerformancePackage.BLOB_PA__FHW_UTIL_SVP:
				return f_hwUtilSVP != null;
			case PerformancePackage.BLOB_PA__FNET_UTIL_SVP:
				return f_netUtilSVP != null;
			case PerformancePackage.BLOB_PA__TMAX_CLIENT_CONNECTS_SVP:
				return t_maxClientConnectsSVP != null;
			case PerformancePackage.BLOB_PA__TMAX_SUPPLIER_CONNECTS_SVP:
				return t_maxSupplierConnectsSVP != null;
			case PerformancePackage.BLOB_PA__TMAX_SENT_MSGS_SVP:
				return t_maxSentMsgsSVP != null;
			case PerformancePackage.BLOB_PA__TMAX_RECEIVED_MSGS_SVP:
				return t_maxReceivedMsgsSVP != null;
			case PerformancePackage.BLOB_PA__TMAX_HW_UTIL_SVP:
				return t_maxHwUtilSVP != null;
			case PerformancePackage.BLOB_PA__TMIN_NET_UTIL_SVP:
				return t_minNetUtilSVP != null;
			case PerformancePackage.BLOB_PA__CONTEXTUAL_ELEMENT:
				return CONTEXTUAL_ELEMENT_EDEFAULT == null ? contextualElement != null : !CONTEXTUAL_ELEMENT_EDEFAULT.equals(contextualElement);
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
			case PerformancePackage.BLOB_PA___SET_PARAMETERS_FOR_PARTIAL_DETECTION__DOUBLE_DOUBLE_DOUBLE_DOUBLE:
				setParametersForPartialDetection((Double)arguments.get(0), (Double)arguments.get(1), (Double)arguments.get(2), (Double)arguments.get(3));
				return null;
			case PerformancePackage.BLOB_PA___SET_PARAMETERS_FOR_FULL_DETECTION__DOUBLE_DOUBLE_DOUBLE_DOUBLE_DOUBLE_DOUBLE:
				setParametersForFullDetection((Double)arguments.get(0), (Double)arguments.get(1), (Double)arguments.get(2), (Double)arguments.get(3), (Double)arguments.get(4), (Double)arguments.get(5));
				return null;
			case PerformancePackage.BLOB_PA___CREATE_FORMULA:
				createFormula();
				return null;
			case PerformancePackage.BLOB_PA___PARTIAL_DETECTION__DOUBLE_DOUBLE_DOUBLE_DOUBLE:
				return partialDetection((Double)arguments.get(0), (Double)arguments.get(1), (Double)arguments.get(2), (Double)arguments.get(3));
			case PerformancePackage.BLOB_PA___FULL_DETECTION__DOUBLE_DOUBLE_DOUBLE_DOUBLE_DOUBLE_DOUBLE:
				return fullDetection((Double)arguments.get(0), (Double)arguments.get(1), (Double)arguments.get(2), (Double)arguments.get(3), (Double)arguments.get(4), (Double)arguments.get(5));
			case PerformancePackage.BLOB_PA___LOG:
				log();
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

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (contextualElement: ");
		result.append(contextualElement);
		result.append(')');
		return result.toString();
	}

} //BlobPAImpl
