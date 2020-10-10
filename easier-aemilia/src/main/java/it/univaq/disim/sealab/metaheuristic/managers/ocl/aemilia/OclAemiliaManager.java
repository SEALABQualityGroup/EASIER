package it.univaq.disim.sealab.metaheuristic.managers.ocl.aemilia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;

import it.univaq.disim.sealab.metaheuristic.evolutionary.AemiliaController;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclManager;
import metamodel.mmaemilia.AEmiliaSpecification;
import metamodel.mmaemilia.ArchiElemInstance;
import metamodel.mmaemilia.mmaemiliaPackage;

public class OclAemiliaManager extends OclManager {

	public OclAemiliaManager(AemiliaController ctrl) {
//		controller = ctrl;
		// manager = controller.getManager();
	}

//	private HashSet<Object> getHashSet(String query, EObject model) {
//		HashSet<Object> hashSetQuery = null;
////		try {
//			hashSetQuery = (HashSet<Object>) evaluateOCL(query, model);
////		} catch (ParserException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		return hashSetQuery;
//	}

//	@Override
//	protected HashSet<EObject> getQueryResult(String query, EObject model) {
//		Object queryResult = null;
//		HashSet<EObject> hashSet = null;
////		try {
//		hashSet = new HashSet<EObject>();
//		queryResult = evaluateOCL(query, model);
//		if (queryResult instanceof HashSet && !((HashSet<EObject>) queryResult).isEmpty()) {
//			Iterator<EObject> iterator = ((HashSet<EObject>) queryResult).iterator();
//			// check values
//			while (iterator.hasNext()) {
//				EObject res = iterator.next();
//				if (res instanceof ArchiElemInstance) {
//					hashSet.add((ArchiElemInstance) res);
//				}
//			}
//		} else if (queryResult instanceof ArrayList) {
//			hashSet.addAll((Collection<? extends EObject>) queryResult);
//		} else if (queryResult instanceof ArchiElemInstance) {
//			hashSet.add((ArchiElemInstance) queryResult);
//		}
////		} catch (ParserException e) {
////			// TODO Auto-generated catch block
////			System.err.println("Query -->" + query);
////			e.printStackTrace();
////		}
//		return hashSet;
//	}

	// TODO it is no longer used
//	@Deprecated
//	public static OCLExpression<EClassifier> getOCLExpression(String query) throws ParserException {
//		// create an OCL instance for Ecore
//		OCL<?, EClassifier, ?, ?, ?, EParameter, ?, ?, ?, Constraint, EClass, EObject> ocl;
//		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
//		// create an OCL helper object
//		OCLHelper<EClassifier, ?, ?, Constraint> helper = ocl.createOCLHelper();
//		// set the OCL context classifier
//		helper.setContext(UMLPackage.Literals.MODEL);
//		OCLExpression<EClassifier> oclExpression = helper.createQuery(query);
//		return oclExpression;
//	}

	// TODO it is no longer used.
//	@Deprecated
//	public static Query<EClassifier, EClass, EObject> getOCLQuery(String query) throws ParserException {
//		// create an OCL instance for Ecore
//		OCL<?, EClassifier, ?, ?, ?, EParameter, ?, ?, ?, Constraint, EClass, EObject> ocl;
//		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
//		// create an OCL helper object
//		OCLHelper<EClassifier, ?, ?, Constraint> helper = ocl.createOCLHelper();
//		// set the OCL context classifier
//		helper.setContext(UMLPackage.Literals.MODEL);
//		OCLExpression<EClassifier> oclExpression = helper.createQuery(query);
//		Query<EClassifier, EClass, EObject> oclQuery = ocl.createQuery(oclExpression);
//		return oclQuery;
//	}

	/**
	 * @see http://www.programcreek.com/java-api-examples/index.php?api=org.eclipse.ocl.uml.UMLEnvironmentFactory
	 * @see http://archive.eclipse.org/modeling/mdt/ocl/javadoc/3.0.0/org/eclipse/ocl/Environment.html
	 * @see http://stackoverflow.com/questions/20774594/programmatically-execute-an-ocl-query-on-a-uml-model
	 * 
	 */
	/*
	 * public Object evaluateOCL(String query, Object contextualElement) {//throws
	 * ParserException { // create an OCL instance for Ecore OCL<?, EClassifier, ?,
	 * ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl; ocl =
	 * OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
	 * 
	 * // create an OCL helper object OCLHelper<EClassifier, ?, ?, Constraint>
	 * helper = ocl.createOCLHelper(); if (contextualElement instanceof
	 * AEmiliaSpecification)
	 * helper.setContext(mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION); else if
	 * (contextualElement instanceof ArchiElemInstance)
	 * helper.setContext(mmaemiliaPackage.Literals.ARCHI_ELEM_INSTANCE);
	 * 
	 * helper.setContext(((EObject)contextualElement).eClass());
	 * 
	 * // helper.setInstanceContext(UMLPackage.eINSTANCE.getClass_()); //TODO check
	 * when it throws a SemanticException OCLExpression<EClassifier> oclExpression;
	 * try { oclExpression = helper.createQuery(query); Query<EClassifier, EClass,
	 * EObject> oclQuery = ocl.createQuery(oclExpression); return
	 * oclQuery.evaluate(contextualElement); } catch (ParserException e) { // TODO
	 * Auto-generated catch block System.err.println("ContextualElement --> " +
	 * contextualElement.toString()); System.err.println("Query --> " + query);
	 * e.printStackTrace(); } return null; }
	 */

	// TODO it is no longer used
//	@Deprecated
//	public static List<?> evaluateOCL(String query, List<EObject> contextualElements) throws ParserException {
//		// create an OCL instance for Ecore
//		OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
//		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
//		// create an OCL helper object
//		OCLHelper<EClassifier, ?, ?, Constraint> helper = ocl.createOCLHelper();
//		// set the OCL context classifier
//		if (contextualElements.get(0) instanceof AEmiliaSpecification)
//			helper.setContext(mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
//		else if (contextualElements.get(0) instanceof ArchitecturalInteraction)
//			helper.setContext(mmaemiliaPackage.Literals.ARCHITECTURAL_INTERACTION);
//		OCLExpression<EClassifier> oclExpression = helper.createQuery(query);
//		Query<EClassifier, EClass, EObject> oclQuery = ocl.createQuery(oclExpression);
//		return oclQuery.evaluate(contextualElements);
//	}

	// TODO it is no longer used
//	@Deprecated
//	public static boolean checkOCL(String query, EObject contextualElement) throws ParserException {
//		// create an OCL instance for Ecore
//		OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
//		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
//		// create an OCL helper object
//		OCLHelper<EClassifier, ?, ?, Constraint> helper = ocl.createOCLHelper();
//		// set the OCL context classifier
//		if (contextualElement instanceof AEmiliaSpecification)
//			helper.setContext(mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
//		else if (contextualElement instanceof ArchitecturalInteraction)
//			helper.setContext(mmaemiliaPackage.Literals.ARCHITECTURAL_INTERACTION);
//		OCLExpression<EClassifier> oclExpression = helper.createQuery(query);
//		Query<EClassifier, EClass, EObject> oclQuery = ocl.createQuery(oclExpression);
//		return oclQuery.check(contextualElement);
//	}

	// TODO it is no longer used
//	@Deprecated
//	public static boolean checkOCL(String query, List<Object> contextualElements) throws ParserException {
//		// create an OCL instance for Ecore
//		OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
//		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
//		// create an OCL helper object
//		OCLHelper<EClassifier, ?, ?, Constraint> helper = ocl.createOCLHelper();
//		// set the OCL context classifier
//		if (contextualElements.get(0) instanceof Package)
//			helper.setContext(UMLPackage.Literals.PACKAGE);
//		else if (contextualElements.get(0) instanceof Component)
//			helper.setContext(UMLPackage.Literals.COMPONENT);
//		else if (contextualElements.get(0) instanceof Operation)
//			helper.setContext(UMLPackage.Literals.OPERATION);
//		else if (contextualElements.get(0) instanceof Node)
//			helper.setContext(UMLPackage.Literals.NODE);
//		OCLExpression<EClassifier> oclExpression = helper.createQuery(query);
//		Query<EClassifier, EClass, EObject> oclQuery = ocl.createQuery(oclExpression);
//		return oclQuery.check(contextualElements);
//	}

	// TODO is is no longer used
//	@Deprecated
//	protected HashSet<Object> getQueryResult(String query, List<Object> contextualElements) {
//		Object queryResult = null;
//		HashSet<Object> hashSet = null;
////		try {
//			hashSet = new HashSet<Object>();
//			queryResult = evaluateOCL(query, contextualElements);
//			if (queryResult instanceof Integer) {
//				double intQueryResult = Integer.parseInt(queryResult.toString());
//				hashSet.add(intQueryResult);
//			} else if (queryResult instanceof Double) {
//				double doubleQueryResult = Double.parseDouble(queryResult.toString());
//				hashSet.add(doubleQueryResult);
//			} else if (queryResult instanceof Model)
//				hashSet.add((Model) queryResult);
//			else if (queryResult instanceof Package)
//				hashSet.add((Package) queryResult);
//			else if (queryResult instanceof Component)
//				hashSet.add((Component) queryResult);
//			else if (queryResult instanceof Operation)
//				hashSet.add((Operation) queryResult);
//			else if (queryResult instanceof Node)
//				hashSet.add((Node) queryResult);
//			else if (queryResult instanceof HashSet<?>)
//				hashSet = (HashSet<Object>) queryResult;
//			else if (queryResult instanceof ArrayList<?>)
//				hashSet.addAll((ArrayList<Object>) queryResult);
//
////		} catch (ParserException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		return hashSet;
//	}

	// TODO is no longer used
//	@Override
//	protected HashSet<?> getQueryResult(String query) {
//		Object queryResult = null;
//		HashSet<Object> hashSet = null;
//		hashSet = new HashSet<Object>();
//		queryResult = evaluateOCL(query);
//		
//		if (queryResult instanceof Integer) {
//			double intQueryResult = Integer.parseInt(queryResult.toString());
//			hashSet.add(intQueryResult);
//		} else if (queryResult instanceof Double) {
//			double doubleQueryResult = Double.parseDouble(queryResult.toString());
//			hashSet.add(doubleQueryResult);
//		} else if (queryResult instanceof Model)
//			hashSet.add((Model) queryResult);
//		else if (queryResult instanceof Package)
//			hashSet.add((Package) queryResult);
//		else if (queryResult instanceof Component)
//			hashSet.add((Component) queryResult);
//		else if (queryResult instanceof Operation)
//			hashSet.add((Operation) queryResult);
//		else if (queryResult instanceof Node)
//			hashSet.add((Node) queryResult);
//		
//		else if (queryResult instanceof HashSet<?>)
//			hashSet = (HashSet<Object>) queryResult;
//		else if (queryResult instanceof ArrayList<?>)
//			hashSet.addAll((ArrayList<Object>) queryResult);
//		return hashSet;
//	}

//	@Override
//	protected HashSet<?> getQueryResult(String query, Object model) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
