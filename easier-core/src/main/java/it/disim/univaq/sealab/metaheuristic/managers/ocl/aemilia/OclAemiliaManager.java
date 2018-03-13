package it.disim.univaq.sealab.metaheuristic.managers.ocl.aemilia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.ExpressionInOCL;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;

import it.disim.univaq.sealab.metaheuristic.managers.Manager;
import it.disim.univaq.sealab.metaheuristic.managers.aemilia.AemiliaManager;
import it.disim.univaq.sealab.metaheuristic.managers.ocl.OclManager;
import metamodel.mmaemilia.AEmiliaSpecification;
import metamodel.mmaemilia.ArchiElemInstance;
import metamodel.mmaemilia.ArchitecturalInteraction;
import metamodel.mmaemilia.mmaemiliaPackage;
import metamodel.mmaemilia.Behavior.Action;

public class OclAemiliaManager extends OclManager {

	// private static class ManagerHolder {
	// private static final OclAemiliaManager INSTANCE = new OclAemiliaManager();
	// }
	//
	// public static OclAemiliaManager getInstance() {
	// OclAemiliaManager instance = ManagerHolder.INSTANCE;
	// return instance;
	// }

	private HashSet<Object> getHashSet(String query) {
		HashSet<Object> hashSetQuery = null;
		try {
			hashSetQuery = (HashSet<Object>) evaluateOCL(query, Manager.getInstance(null).getModel());
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashSetQuery;
	}
	
	private HashSet<Object> getHashSet(String query, EObject model) {
		HashSet<Object> hashSetQuery = null;
		try {
			hashSetQuery = (HashSet<Object>) evaluateOCL(query, model);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashSetQuery;
	}

//	/* AEMILIA */
	@SuppressWarnings({ "unchecked", "static-access" })
	private HashSet<Object> getHashSetFromAEmilia(String query) {
		HashSet<Object> hashSetQuery = null;
		try {
			hashSetQuery = (HashSet<Object>) evaluateOCL(query, Manager.getInstance(null).getModel());
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashSetQuery;
	}
	/////////////

	@Override
	public HashSet<EObject> evaluateQuery(String query, EObject model) {
		return (HashSet<EObject>) getQueryResult(query, model);
	}
	
//	@SuppressWarnings({ "unchecked", "static-access" })
//	protected HashSet<EObject> getQueryResult(String query) {
//		Object queryResult = null;
//		HashSet<EObject> hashSet = null;
//		try {
//			hashSet = new HashSet<EObject>();
//			queryResult = evaluateOCL(query, model);
//			if (queryResult instanceof HashSet && !((HashSet<EObject>) queryResult).isEmpty()) {
//				Iterator<EObject> iterator = ((HashSet<EObject>) queryResult).iterator();
//				// check values
//				while (iterator.hasNext()) {
//					EObject res = iterator.next();
//					if (res instanceof ArchiElemInstance) {
//						hashSet.add((ArchiElemInstance) res);
//					}
//				}
//			} else if(queryResult instanceof ArrayList) {
//				hashSet.addAll((Collection<? extends EObject>) queryResult);
//			}
//			else if (queryResult instanceof ArchiElemInstance) {
//				hashSet.add((ArchiElemInstance) queryResult);
//			}
//		} catch (
//
//		ParserException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return hashSet;
//	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	protected HashSet<EObject> getQueryResult(String query, EObject model) {
		Object queryResult = null;
		HashSet<EObject> hashSet = null;
		try {
			hashSet = new HashSet<EObject>();
			queryResult = evaluateOCL(query, model);
			if (queryResult instanceof HashSet && !((HashSet<EObject>) queryResult).isEmpty()) {
				Iterator<EObject> iterator = ((HashSet<EObject>) queryResult).iterator();
				// check values
				while (iterator.hasNext()) {
					EObject res = iterator.next();
					if (res instanceof ArchiElemInstance) {
						hashSet.add((ArchiElemInstance) res);
					}
				}
			} else if(queryResult instanceof ArrayList) {
				hashSet.addAll((Collection<? extends EObject>) queryResult);
			}
			else if (queryResult instanceof ArchiElemInstance) {
				hashSet.add((ArchiElemInstance) queryResult);
			}
		} catch (

		ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashSet;
	}

	public static OCLExpression<EClassifier> getOCLExpression(String query) throws ParserException {
		// create an OCL instance for Ecore
		OCL<?, EClassifier, ?, ?, ?, EParameter, ?, ?, ?, Constraint, EClass, EObject> ocl;
		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
		// create an OCL helper object
		OCLHelper<EClassifier, ?, ?, Constraint> helper = ocl.createOCLHelper();
		// set the OCL context classifier
		helper.setContext(UMLPackage.Literals.MODEL);
		OCLExpression<EClassifier> oclExpression = helper.createQuery(query);
		return oclExpression;
	}

	public static Query<EClassifier, EClass, EObject> getOCLQuery(String query) throws ParserException {
		// create an OCL instance for Ecore
		OCL<?, EClassifier, ?, ?, ?, EParameter, ?, ?, ?, Constraint, EClass, EObject> ocl;
		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
		// create an OCL helper object
		OCLHelper<EClassifier, ?, ?, Constraint> helper = ocl.createOCLHelper();
		// set the OCL context classifier
		helper.setContext(UMLPackage.Literals.MODEL);
		OCLExpression<EClassifier> oclExpression = helper.createQuery(query);
		Query<EClassifier, EClass, EObject> oclQuery = ocl.createQuery(oclExpression);
		return oclQuery;
	}

	/**
	 * @see http://www.programcreek.com/java-api-examples/index.php?api=org.eclipse.ocl.uml.UMLEnvironmentFactory
	 * @see http://archive.eclipse.org/modeling/mdt/ocl/javadoc/3.0.0/org/eclipse/ocl/Environment.html
	 * @see http://stackoverflow.com/questions/20774594/programmatically-execute-an-ocl-query-on-a-uml-model
	 * 
	 *      SONO PARTITO DALL'ULTIMO
	 */
	public Object evaluateOCL(String query, Object contextualElement) throws ParserException {
		// create an OCL instance for Ecore
		OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);

		// UMLEnvironmentFactory factory = new UMLEnvironmentFactory();
		// OCL<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> UMLresult = OCL.newInstance(factory);
		// OCLHelper<?, ?, ?, ?> UMLhelper = (OCLHelper<?, ?, ?, ?>)
		// UMLresult.createOCLHelper();

		// create an OCL helper object
		OCLHelper<EClassifier, ?, ?, Constraint> helper = ocl.createOCLHelper();
		if (contextualElement instanceof AEmiliaSpecification)
			helper.setContext(mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
		else if (contextualElement instanceof ArchiElemInstance)
			helper.setContext(mmaemiliaPackage.Literals.ARCHI_ELEM_INSTANCE);

		// helper.setInstanceContext(UMLPackage.eINSTANCE.getClass_());
		OCLExpression<EClassifier> oclExpression = helper.createQuery(query);
		Query<EClassifier, EClass, EObject> oclQuery = ocl.createQuery(oclExpression);
		return oclQuery.evaluate(contextualElement);
	}

	public static List<?> evaluateOCL(String query, List<EObject> contextualElements) throws ParserException {
		// create an OCL instance for Ecore
		OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
		// create an OCL helper object
		OCLHelper<EClassifier, ?, ?, Constraint> helper = ocl.createOCLHelper();
		// set the OCL context classifier
		if (contextualElements.get(0) instanceof AEmiliaSpecification)
			helper.setContext(mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
		else if (contextualElements.get(0) instanceof ArchitecturalInteraction)
			helper.setContext(mmaemiliaPackage.Literals.ARCHITECTURAL_INTERACTION);
		OCLExpression<EClassifier> oclExpression = helper.createQuery(query);
		Query<EClassifier, EClass, EObject> oclQuery = ocl.createQuery(oclExpression);
		return oclQuery.evaluate(contextualElements);
	}

	public static boolean checkOCL(String query, EObject contextualElement) throws ParserException {
		// create an OCL instance for Ecore
		OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
		// create an OCL helper object
		OCLHelper<EClassifier, ?, ?, Constraint> helper = ocl.createOCLHelper();
		// set the OCL context classifier
		if (contextualElement instanceof AEmiliaSpecification)
			helper.setContext(mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
		else if (contextualElement instanceof ArchitecturalInteraction)
			helper.setContext(mmaemiliaPackage.Literals.ARCHITECTURAL_INTERACTION);
		OCLExpression<EClassifier> oclExpression = helper.createQuery(query);
		Query<EClassifier, EClass, EObject> oclQuery = ocl.createQuery(oclExpression);
		return oclQuery.check(contextualElement);
	}

	public static boolean checkOCL(String query, List<Object> contextualElements) throws ParserException {
		// create an OCL instance for Ecore
		OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
		// create an OCL helper object
		OCLHelper<EClassifier, ?, ?, Constraint> helper = ocl.createOCLHelper();
		// set the OCL context classifier
		if (contextualElements.get(0) instanceof Package)
			helper.setContext(UMLPackage.Literals.PACKAGE);
		else if (contextualElements.get(0) instanceof Component)
			helper.setContext(UMLPackage.Literals.COMPONENT);
		else if (contextualElements.get(0) instanceof Operation)
			helper.setContext(UMLPackage.Literals.OPERATION);
		else if (contextualElements.get(0) instanceof Node)
			helper.setContext(UMLPackage.Literals.NODE);
		OCLExpression<EClassifier> oclExpression = helper.createQuery(query);
		Query<EClassifier, EClass, EObject> oclQuery = ocl.createQuery(oclExpression);
		return oclQuery.check(contextualElements);
	}

	private HashSet<Object> evaluateQuery(String query, List<Object> contextualElements) {
		return (HashSet<Object>) getQueryResult(query, contextualElements);
	}

	protected HashSet<Object> getQueryResult(String query, List<Object> contextualElements) {
		Object queryResult = null;
		HashSet<Object> hashSet = null;
		try {
			hashSet = new HashSet<Object>();
			queryResult = evaluateOCL(query, contextualElements);
			if (queryResult instanceof Integer) {
				double intQueryResult = Integer.parseInt(queryResult.toString());
				hashSet.add(intQueryResult);
			} else if (queryResult instanceof Double) {
				double doubleQueryResult = Double.parseDouble(queryResult.toString());
				hashSet.add(doubleQueryResult);
			} else if (queryResult instanceof Model)
				hashSet.add((Model) queryResult);
			else if (queryResult instanceof Package)
				hashSet.add((Package) queryResult);
			else if (queryResult instanceof Component)
				hashSet.add((Component) queryResult);
			else if (queryResult instanceof Operation)
				hashSet.add((Operation) queryResult);
			else if (queryResult instanceof Node)
				hashSet.add((Node) queryResult);
			else if (queryResult instanceof HashSet<?>)
				hashSet = (HashSet<Object>) queryResult;
			else if (queryResult instanceof ArrayList<?>)
				hashSet.addAll((ArrayList<Object>) queryResult);

		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashSet;
	}

	@Override
	protected HashSet<?> getQueryResult(String query) {
		// TODO Auto-generated method stub
		Object queryResult = null;
		HashSet<Object> hashSet = null;
		hashSet = new HashSet<Object>();
		queryResult = evaluateOCL(query);
		if (queryResult instanceof Integer) {
			double intQueryResult = Integer.parseInt(queryResult.toString());
			hashSet.add(intQueryResult);
		} else if (queryResult instanceof Double) {
			double doubleQueryResult = Double.parseDouble(queryResult.toString());
			hashSet.add(doubleQueryResult);
		} else if (queryResult instanceof Model)
			hashSet.add((Model) queryResult);
		else if (queryResult instanceof Package)
			hashSet.add((Package) queryResult);
		else if (queryResult instanceof Component)
			hashSet.add((Component) queryResult);
		else if (queryResult instanceof Operation)
			hashSet.add((Operation) queryResult);
		else if (queryResult instanceof Node)
			hashSet.add((Node) queryResult);
		else if (queryResult instanceof HashSet<?>)
			hashSet = (HashSet<Object>) queryResult;
		else if (queryResult instanceof ArrayList<?>)
			hashSet.addAll((ArrayList<Object>) queryResult);
		return hashSet;
	}

	@Override
	public HashSet<Object> evaluateOCL(String query) {
		// TODO Auto-generated method stub
		
		return (HashSet<Object>) this.evaluateQuery(query);
	}
}
