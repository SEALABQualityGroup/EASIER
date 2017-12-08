/**
 */
package logicalSpecification.antipatterns.performance;

import logicalSpecification.SingleValuedParameter;

import org.eclipse.uml2.uml.Component;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Blob PA</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link logicalSpecification.antipatterns.performance.BlobPA#getF_numClientConnectsSVP <em>Fnum Client Connects SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.BlobPA#getF_numSupplierConnectsSVP <em>Fnum Supplier Connects SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.BlobPA#getF_numSentMsgsSVP <em>Fnum Sent Msgs SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.BlobPA#getF_numReceivedMsgsSVP <em>Fnum Received Msgs SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.BlobPA#getF_hwUtilSVP <em>Fhw Util SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.BlobPA#getF_netUtilSVP <em>Fnet Util SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.BlobPA#getT_maxClientConnectsSVP <em>Tmax Client Connects SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.BlobPA#getT_maxSupplierConnectsSVP <em>Tmax Supplier Connects SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.BlobPA#getT_maxSentMsgsSVP <em>Tmax Sent Msgs SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.BlobPA#getT_maxReceivedMsgsSVP <em>Tmax Received Msgs SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.BlobPA#getT_maxHwUtilSVP <em>Tmax Hw Util SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.BlobPA#getT_minNetUtilSVP <em>Tmin Net Util SVP</em>}</li>
 *   <li>{@link logicalSpecification.antipatterns.performance.BlobPA#getContextualElement <em>Contextual Element</em>}</li>
 * </ul>
 *
 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getBlobPA()
 * @model
 * @generated
 */
public interface BlobPA extends PerformanceAntipattern {
	/**
	 * Returns the value of the '<em><b>Fnum Client Connects SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fnum Client Connects SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fnum Client Connects SVP</em>' reference.
	 * @see #setF_numClientConnectsSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getBlobPA_F_numClientConnectsSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getF_numClientConnectsSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.BlobPA#getF_numClientConnectsSVP <em>Fnum Client Connects SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fnum Client Connects SVP</em>' reference.
	 * @see #getF_numClientConnectsSVP()
	 * @generated
	 */
	void setF_numClientConnectsSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Fnum Supplier Connects SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fnum Supplier Connects SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fnum Supplier Connects SVP</em>' reference.
	 * @see #setF_numSupplierConnectsSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getBlobPA_F_numSupplierConnectsSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getF_numSupplierConnectsSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.BlobPA#getF_numSupplierConnectsSVP <em>Fnum Supplier Connects SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fnum Supplier Connects SVP</em>' reference.
	 * @see #getF_numSupplierConnectsSVP()
	 * @generated
	 */
	void setF_numSupplierConnectsSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Fnum Sent Msgs SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fnum Sent Msgs SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fnum Sent Msgs SVP</em>' reference.
	 * @see #setF_numSentMsgsSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getBlobPA_F_numSentMsgsSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getF_numSentMsgsSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.BlobPA#getF_numSentMsgsSVP <em>Fnum Sent Msgs SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fnum Sent Msgs SVP</em>' reference.
	 * @see #getF_numSentMsgsSVP()
	 * @generated
	 */
	void setF_numSentMsgsSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Fnum Received Msgs SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fnum Received Msgs SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fnum Received Msgs SVP</em>' reference.
	 * @see #setF_numReceivedMsgsSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getBlobPA_F_numReceivedMsgsSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getF_numReceivedMsgsSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.BlobPA#getF_numReceivedMsgsSVP <em>Fnum Received Msgs SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fnum Received Msgs SVP</em>' reference.
	 * @see #getF_numReceivedMsgsSVP()
	 * @generated
	 */
	void setF_numReceivedMsgsSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Fhw Util SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fhw Util SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fhw Util SVP</em>' reference.
	 * @see #setF_hwUtilSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getBlobPA_F_hwUtilSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getF_hwUtilSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.BlobPA#getF_hwUtilSVP <em>Fhw Util SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fhw Util SVP</em>' reference.
	 * @see #getF_hwUtilSVP()
	 * @generated
	 */
	void setF_hwUtilSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Fnet Util SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fnet Util SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fnet Util SVP</em>' reference.
	 * @see #setF_netUtilSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getBlobPA_F_netUtilSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getF_netUtilSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.BlobPA#getF_netUtilSVP <em>Fnet Util SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fnet Util SVP</em>' reference.
	 * @see #getF_netUtilSVP()
	 * @generated
	 */
	void setF_netUtilSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Tmax Client Connects SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tmax Client Connects SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tmax Client Connects SVP</em>' reference.
	 * @see #setT_maxClientConnectsSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getBlobPA_T_maxClientConnectsSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getT_maxClientConnectsSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.BlobPA#getT_maxClientConnectsSVP <em>Tmax Client Connects SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tmax Client Connects SVP</em>' reference.
	 * @see #getT_maxClientConnectsSVP()
	 * @generated
	 */
	void setT_maxClientConnectsSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Tmax Supplier Connects SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tmax Supplier Connects SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tmax Supplier Connects SVP</em>' reference.
	 * @see #setT_maxSupplierConnectsSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getBlobPA_T_maxSupplierConnectsSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getT_maxSupplierConnectsSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.BlobPA#getT_maxSupplierConnectsSVP <em>Tmax Supplier Connects SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tmax Supplier Connects SVP</em>' reference.
	 * @see #getT_maxSupplierConnectsSVP()
	 * @generated
	 */
	void setT_maxSupplierConnectsSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Tmax Sent Msgs SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tmax Sent Msgs SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tmax Sent Msgs SVP</em>' reference.
	 * @see #setT_maxSentMsgsSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getBlobPA_T_maxSentMsgsSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getT_maxSentMsgsSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.BlobPA#getT_maxSentMsgsSVP <em>Tmax Sent Msgs SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tmax Sent Msgs SVP</em>' reference.
	 * @see #getT_maxSentMsgsSVP()
	 * @generated
	 */
	void setT_maxSentMsgsSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Tmax Received Msgs SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tmax Received Msgs SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tmax Received Msgs SVP</em>' reference.
	 * @see #setT_maxReceivedMsgsSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getBlobPA_T_maxReceivedMsgsSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getT_maxReceivedMsgsSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.BlobPA#getT_maxReceivedMsgsSVP <em>Tmax Received Msgs SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tmax Received Msgs SVP</em>' reference.
	 * @see #getT_maxReceivedMsgsSVP()
	 * @generated
	 */
	void setT_maxReceivedMsgsSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Tmax Hw Util SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tmax Hw Util SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tmax Hw Util SVP</em>' reference.
	 * @see #setT_maxHwUtilSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getBlobPA_T_maxHwUtilSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getT_maxHwUtilSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.BlobPA#getT_maxHwUtilSVP <em>Tmax Hw Util SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tmax Hw Util SVP</em>' reference.
	 * @see #getT_maxHwUtilSVP()
	 * @generated
	 */
	void setT_maxHwUtilSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Tmin Net Util SVP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tmin Net Util SVP</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tmin Net Util SVP</em>' reference.
	 * @see #setT_minNetUtilSVP(SingleValuedParameter)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getBlobPA_T_minNetUtilSVP()
	 * @model required="true"
	 * @generated
	 */
	SingleValuedParameter getT_minNetUtilSVP();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.BlobPA#getT_minNetUtilSVP <em>Tmin Net Util SVP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tmin Net Util SVP</em>' reference.
	 * @see #getT_minNetUtilSVP()
	 * @generated
	 */
	void setT_minNetUtilSVP(SingleValuedParameter value);

	/**
	 * Returns the value of the '<em><b>Contextual Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contextual Element</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contextual Element</em>' attribute.
	 * @see #setContextualElement(Component)
	 * @see logicalSpecification.antipatterns.performance.PerformancePackage#getBlobPA_ContextualElement()
	 * @model dataType="logicalSpecification.actions.UML.Component" required="true"
	 * @generated
	 */
	Component getContextualElement();

	/**
	 * Sets the value of the '{@link logicalSpecification.antipatterns.performance.BlobPA#getContextualElement <em>Contextual Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contextual Element</em>' attribute.
	 * @see #getContextualElement()
	 * @generated
	 */
	void setContextualElement(Component value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model T_maxClientConnectsRequired="true" T_maxSupplierConnectsRequired="true" T_maxSentMsgsRequired="true" T_maxReceivedMsgsRequired="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='List&lt;Parameter&gt; blobParams = new ArrayList&lt;&gt;();\n\n\t\tsetF_numClientConnectsSVP(Manager.getInstance(null).createSingleValueParameter(\n\t\t\t\t(OclStringManager.getInstance(null)).getClientConnectsQuery(getContextualElement())));\n\t\tblobParams.add(getF_numClientConnectsSVP());\n\n\t\tsetT_maxClientConnectsSVP(\n\t\t\t\tManager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxClientConnects)));\n\t\tblobParams.add(getT_maxClientConnectsSVP());\n\n\t\tsetF_numSupplierConnectsSVP(Manager.getInstance(null).createSingleValueParameter(\n\t\t\t\t(OclStringManager.getInstance(null)).getSupplierConnectsQuery(getContextualElement())));\n\t\tblobParams.add(getF_numSupplierConnectsSVP());\n\n\t\tsetT_maxSupplierConnectsSVP(\n\t\t\t\tManager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxSupplierConnects)));\n\t\tblobParams.add(getT_maxSupplierConnectsSVP());\n\n\t\tsetF_numSentMsgsSVP(Manager.getInstance(null).createSingleValueParameter(\n\t\t\t\t(OclStringManager.getInstance(null)).getNumSentMsgsQuery(getContextualElement())));\n\t\tblobParams.add(getF_numSentMsgsSVP());\n\n\t\tsetT_maxSentMsgsSVP(Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxSentMsgs)));\n\t\tblobParams.add(getT_maxSentMsgsSVP());\n\n\t\tsetF_numReceivedMsgsSVP(Manager.getInstance(null).createSingleValueParameter(\n\t\t\t\t(OclStringManager.getInstance(null)).getNumReceivedMsgsQuery(getContextualElement())));\n\t\tblobParams.add(getF_numReceivedMsgsSVP());\n\n\t\tsetT_maxReceivedMsgsSVP(\n\t\t\t\tManager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxReceivedMsgs)));\n\t\tblobParams.add(getT_maxReceivedMsgsSVP());\n\n\t\tif (getF_hwUtilSVP() != null)\n\t\t\tif (getF_hwUtilSVP().getResolvingExpr() != null || !getF_hwUtilSVP().getResolvingExpr().equals(\"\"))\n\t\t\t\tgetF_hwUtilSVP().setResolvingExpr(null);\n\n\t\tif (getT_maxHwUtilSVP() != null)\n\t\t\tif (getT_maxHwUtilSVP().getResolvingExpr() != null || !getT_maxHwUtilSVP().getResolvingExpr().equals(\"\"))\n\t\t\t\tgetT_maxHwUtilSVP().setResolvingExpr(null);\n\n\t\tif (getF_netUtilSVP() != null)\n\t\t\tif (getF_netUtilSVP().getResolvingExpr() != null || !getF_netUtilSVP().getResolvingExpr().equals(\"\"))\n\t\t\t\tgetF_netUtilSVP().setResolvingExpr(null);\n\n\t\tif (getT_minNetUtilSVP() != null)\n\t\t\tif (getT_minNetUtilSVP().getResolvingExpr() != null || !getT_minNetUtilSVP().getResolvingExpr().equals(\"\"))\n\t\t\t\tgetT_minNetUtilSVP().setResolvingExpr(null);\n\n\t\tgetParameters().addAll(blobParams);'"
	 * @generated
	 */
	void setParametersForPartialDetection(double T_maxClientConnects, double T_maxSupplierConnects, double T_maxSentMsgs, double T_maxReceivedMsgs);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model T_maxClientConnectsRequired="true" T_maxSupplierConnectsRequired="true" T_maxSentMsgsRequired="true" T_maxReceivedMsgsRequired="true" T_maxHwUtilRequired="true" T_minNetUtilRequired="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='List&lt;Parameter&gt; blobParams = new ArrayList&lt;&gt;();\n\n\t\tsetF_numClientConnectsSVP(Manager.getInstance(null).createSingleValueParameter(\n\t\t\t\tOclStringManager.getInstance(null).getClientConnectsQuery(getContextualElement())));\n\t\tblobParams.add(getF_numClientConnectsSVP());\n\n\t\tsetT_maxClientConnectsSVP(\n\t\t\t\tManager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxClientConnects)));\n\t\tblobParams.add(getT_maxClientConnectsSVP());\n\n\t\tsetF_numSupplierConnectsSVP(Manager.getInstance(null).createSingleValueParameter(\n\t\t\t\tOclStringManager.getInstance(null).getSupplierConnectsQuery(getContextualElement())));\n\t\tblobParams.add(getF_numSupplierConnectsSVP());\n\n\t\tsetT_maxSupplierConnectsSVP(\n\t\t\t\tManager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxSupplierConnects)));\n\t\tblobParams.add(getT_maxSupplierConnectsSVP());\n\n\t\tsetF_numSentMsgsSVP(Manager.getInstance(null).createSingleValueParameter(\n\t\t\t\tOclStringManager.getInstance(null).getNumSentMsgsQuery(getContextualElement())));\n\t\tblobParams.add(getF_numSentMsgsSVP());\n\n\t\tsetT_maxSentMsgsSVP(Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxSentMsgs)));\n\t\tblobParams.add(getT_maxSentMsgsSVP());\n\n\t\tsetF_numReceivedMsgsSVP(Manager.getInstance(null).createSingleValueParameter(\n\t\t\t\tOclStringManager.getInstance(null).getNumReceivedMsgsQuery(getContextualElement())));\n\t\tblobParams.add(getF_numReceivedMsgsSVP());\n\n\t\tsetT_maxReceivedMsgsSVP(\n\t\t\t\tManager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxReceivedMsgs)));\n\t\tblobParams.add(getT_maxReceivedMsgsSVP());\n\n\t\tsetF_hwUtilSVP(Manager.getInstance(null)\n\t\t\t\t.createSingleValueParameter(OclStringManager.getInstance(null).getHwUtilQuery(getContextualElement())));\n\t\tblobParams.add(getF_hwUtilSVP());\n\n\t\tsetT_maxHwUtilSVP(Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_maxHwUtil)));\n\t\tblobParams.add(getT_maxHwUtilSVP());\n\n\t\tsetF_netUtilSVP(Manager.getInstance(null).createSingleValueParameter(\n\t\t\t\tOclStringManager.getInstance(null).getNetUtilQuery(getContextualElement())));\n\t\tblobParams.add(getF_netUtilSVP());\n\n\t\tsetT_minNetUtilSVP(Manager.getInstance(null).createSingleValueParameter(String.valueOf(T_minNetUtil)));\n\t\tblobParams.add(getT_minNetUtilSVP());\n\n\t\tgetParameters().addAll(blobParams);'"
	 * @generated
	 */
	void setParametersForFullDetection(double T_maxClientConnects, double T_maxSupplierConnects, double T_maxSentMsgs, double T_maxReceivedMsgs, double T_maxHwUtil, double T_minNetUtil);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='FOLSpecification blobCF = LogicalSpecificationFactory.eINSTANCE.createFOLSpecification();\n\t\tblobCF.setName(\"BlobFormula\");\n\n\t\tAndOperator blobCFAnd = LogicalSpecificationFactory.eINSTANCE.createAndOperator();\n\n\t\tOrOperator blobCFAndOr1 = LogicalSpecificationFactory.eINSTANCE.createOrOperator();\n\t\tGreaterEqualOperator blobCFAndOr1Geq1 = LogicalSpecificationFactory.eINSTANCE.createGreaterEqualOperator();\n\t\tblobCFAndOr1Geq1.setLhs(getF_numClientConnectsSVP());\n\t\tblobCFAndOr1Geq1.setRhs(getT_maxClientConnectsSVP());\n\t\tGreaterEqualOperator blobCFAndOr1Geq2 = LogicalSpecificationFactory.eINSTANCE.createGreaterEqualOperator();\n\t\tblobCFAndOr1Geq2.setLhs(getF_numSupplierConnectsSVP());\n\t\tblobCFAndOr1Geq2.setRhs(getT_maxSupplierConnectsSVP());\n\t\tblobCFAndOr1.getArguments().add(blobCFAndOr1Geq1);\n\t\tblobCFAndOr1.getArguments().add(blobCFAndOr1Geq2);\n\n\t\tOrOperator blobCFAndOr2 = LogicalSpecificationFactory.eINSTANCE.createOrOperator();\n\t\tGreaterEqualOperator blobCFAndOr2Geq1 = LogicalSpecificationFactory.eINSTANCE.createGreaterEqualOperator();\n\t\tblobCFAndOr2Geq1.setLhs(getF_numSentMsgsSVP());\n\t\tblobCFAndOr2Geq1.setRhs(getT_maxSentMsgsSVP());\n\t\tGreaterEqualOperator blobCFAndOr2Geq2 = LogicalSpecificationFactory.eINSTANCE.createGreaterEqualOperator();\n\t\tblobCFAndOr2Geq2.setLhs(getF_numReceivedMsgsSVP());\n\t\tblobCFAndOr2Geq2.setRhs(getT_maxReceivedMsgsSVP());\n\t\tblobCFAndOr2.getArguments().add(blobCFAndOr2Geq1);\n\t\tblobCFAndOr2.getArguments().add(blobCFAndOr2Geq2);\n\n\t\tOrOperator blobCFAndOr3 = LogicalSpecificationFactory.eINSTANCE.createOrOperator();\n\t\tGreaterEqualOperator blobCFAndOr3Geq1 = LogicalSpecificationFactory.eINSTANCE.createGreaterEqualOperator();\n\t\tblobCFAndOr3Geq1.setLhs(getF_hwUtilSVP());\n\t\tblobCFAndOr3Geq1.setRhs(getT_maxHwUtilSVP());\n\t\tLessEqualOperator blobCFAndOr3Geq2 = LogicalSpecificationFactory.eINSTANCE.createLessEqualOperator();\n\t\tblobCFAndOr3Geq2.setLhs(getF_netUtilSVP());\n\t\tblobCFAndOr3Geq2.setRhs(getT_minNetUtilSVP());\n\t\tblobCFAndOr3.getArguments().add(blobCFAndOr3Geq1);\n\t\tblobCFAndOr3.getArguments().add(blobCFAndOr3Geq2);\n\n\t\tblobCFAnd.getArguments().add(blobCFAndOr1);\n\t\tblobCFAnd.getArguments().add(blobCFAndOr2);\n\t\tblobCFAnd.getArguments().add(blobCFAndOr3);\n\t\tblobCF.setRootOperator(blobCFAnd);\n\t\tsetFormula(blobCF);'"
	 * @generated
	 */
	void createFormula();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" T_maxClientConnectsRequired="true" T_maxSupplierConnectsRequired="true" T_maxSentMsgsRequired="true" T_maxReceivedMsgsRequired="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='int occurrences = 0;\n\t\tsetParametersForPartialDetection(T_maxClientConnects, T_maxSupplierConnects, T_maxSentMsgs, T_maxReceivedMsgs);\n\t\tcreateFormula();\n\t\tList&lt;Element&gt; components = ((Model) Manager.getInstance(UMLManager.getInstance()).getModel())\n\t\t\t\t.getNestedPackages().get(0).getNestedPackages().get(0).allOwnedElements();\n\t\tfor (Element c : components) {\n\t\t\tif (c instanceof Component &amp;&amp; c.getAppliedStereotype(\"PaRunTInstance\") != null) {\n\t\t\t\ttry {\n\t\t\t\t\tboolean app = Manager.getInstance(null)\n\t\t\t\t\t\t\t.evaluateOperator((AndOperator) getFormula().getRootOperator(), (Component) c);\n\t\t\t\t\tif (app)\n\t\t\t\t\t\toccurrences++;\n\t\t\t\t} catch (ParserException e) {\n\t\t\t\t\t// TODO Auto-generated catch block\n\t\t\t\t\te.printStackTrace();\n\t\t\t\t}\n\t\t\t}\n\t\t}\n\t\treturn occurrences;'"
	 * @generated
	 */
	int partialDetection(double T_maxClientConnects, double T_maxSupplierConnects, double T_maxSentMsgs, double T_maxReceivedMsgs);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" T_maxClientConnectsRequired="true" T_maxSupplierConnectsRequired="true" T_maxSentMsgsRequired="true" T_maxReceivedMsgsRequired="true" T_maxHwUtilRequired="true" T_minNetUtilRequired="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='int occurrences = 0;\n\t\tsetParametersForFullDetection(T_maxClientConnects, T_maxSupplierConnects, T_maxSentMsgs, T_maxReceivedMsgs,\n\t\t\t\tT_maxHwUtil, T_minNetUtil);\n\t\tcreateFormula();\n\t\tList&lt;Element&gt; components = ((Model) Manager.getInstance(UMLManager.getInstance()).getModel())\n\t\t\t\t.getNestedPackages().get(0).getNestedPackages().get(0).allOwnedElements();\n\t\tfor (Element c : components) {\n\t\t\tif (c instanceof Component &amp;&amp; c.getAppliedStereotype(\"PaRunTInstance\") != null) {\n\t\t\t\ttry {\n\t\t\t\t\tboolean app = Manager.getInstance(null)\n\t\t\t\t\t\t\t.evaluateOperator((AndOperator) getFormula().getRootOperator(), (Component) c);\n\t\t\t\t\tif (app)\n\t\t\t\t\t\toccurrences++;\n\t\t\t\t} catch (ParserException e) {\n\t\t\t\t\t// TODO Auto-generated catch block\n\t\t\t\t\te.printStackTrace();\n\t\t\t\t}\n\t\t\t}\n\t\t}\n\t\treturn occurrences;'"
	 * @generated
	 */
	int fullDetection(double T_maxClientConnects, double T_maxSupplierConnects, double T_maxSentMsgs, double T_maxReceivedMsgs, double T_maxHwUtil, double T_minNetUtil);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='super.log();'"
	 * @generated
	 */
	void log();

} // BlobPA
