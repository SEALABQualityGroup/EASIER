/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package metamodel.mmaemilia.Headers.impl;

import metamodel.mmaemilia.Headers.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class HeadersFactoryImpl extends EFactoryImpl implements HeadersFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static HeadersFactory init() {
		try {
			HeadersFactory theHeadersFactory = (HeadersFactory)EPackage.Registry.INSTANCE.getEFactory(HeadersPackage.eNS_URI);
			if (theHeadersFactory != null) {
				return theHeadersFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new HeadersFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HeadersFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case HeadersPackage.AT_HEADER: return createAT_Header();
			case HeadersPackage.CONST_INIT: return createConstInit();
			case HeadersPackage.ET_HEADER: return createET_Header();
			case HeadersPackage.CONST: return createConst();
			case HeadersPackage.BEHAV_HEADER: return createBehavHeader();
			case HeadersPackage.LEFT_SIDE: return createLeftSide();
			case HeadersPackage.RIGHT_SIDE: return createRightSide();
			case HeadersPackage.VAR_INIT: return createVarInit();
			case HeadersPackage.VAR: return createVar();
			case HeadersPackage.LOCAL: return createLocal();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AT_Header createAT_Header() {
		AT_HeaderImpl aT_Header = new AT_HeaderImpl();
		return aT_Header;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstInit createConstInit() {
		ConstInitImpl constInit = new ConstInitImpl();
		return constInit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ET_Header createET_Header() {
		ET_HeaderImpl eT_Header = new ET_HeaderImpl();
		return eT_Header;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Const createConst() {
		ConstImpl const_ = new ConstImpl();
		return const_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehavHeader createBehavHeader() {
		BehavHeaderImpl behavHeader = new BehavHeaderImpl();
		return behavHeader;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LeftSide createLeftSide() {
		LeftSideImpl leftSide = new LeftSideImpl();
		return leftSide;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RightSide createRightSide() {
		RightSideImpl rightSide = new RightSideImpl();
		return rightSide;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarInit createVarInit() {
		VarInitImpl varInit = new VarInitImpl();
		return varInit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Var createVar() {
		VarImpl var = new VarImpl();
		return var;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Local createLocal() {
		LocalImpl local = new LocalImpl();
		return local;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HeadersPackage getHeadersPackage() {
		return (HeadersPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static HeadersPackage getPackage() {
		return HeadersPackage.eINSTANCE;
	}

} //HeadersFactoryImpl
