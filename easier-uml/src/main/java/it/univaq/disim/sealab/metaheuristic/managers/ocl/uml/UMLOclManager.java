package it.univaq.disim.sealab.metaheuristic.managers.ocl.uml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.ocl.types.OCLStandardLibrary;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;

import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclManager;
import metamodel.mmaemilia.mmaemiliaPackage;

public class UMLOclManager extends OclManager {

	public UMLOclManager(final MetamodelManager ma) {
//		this.MM_manager = ma;
	}

	/*
	 * private HashSet<Object> getHashSet(String query, Resource resource) {
	 * HashSet<Object> hashSetQuery = null; try { hashSetQuery = (HashSet<Object>)
	 * evaluateOCL(query, MM_manager.getModel()); } catch (ParserException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); } return hashSetQuery; }
	 */

//	@SuppressWarnings({ "unchecked", "static-access" })
//	protected HashSet<Object> getQueryResult(final String query) {
//		Object queryResult = null;
//		HashSet<Object> hashSet = null;
//		hashSet = new HashSet<Object>();
//		queryResult = evaluateOCL(query, MM_manager.getModel());
//		if (queryResult instanceof Integer) {
//			int intQueryResult = Integer.parseInt(queryResult.toString());
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
//		else if (queryResult instanceof HashSet<?>)
//			hashSet = (HashSet<Object>) queryResult;
//		else if (queryResult instanceof ArrayList<?>)
//			hashSet.addAll((ArrayList<Object>) queryResult);
//		return hashSet;
//	}

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
	 */
//	//TODO check if it can be moved to the super class
//	public Object evaluateOCL(String query, EObject contextualElement) { // throws ParserException {
//		// create an OCL instance for Ecore
//		OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
//		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE, contextualElement.eResource());
//
//		// create an OCL helper object
//		OCLHelper<EClassifier, ?, ?, Constraint> helper = ocl.createOCLHelper();
//
//		
//		//TODO check
//		helper.setContext(contextualElement.eClass());
//
//		// helper.setInstanceContext(UMLPackage.eINSTANCE.getClass_());
//		OCLExpression<EClassifier> oclExpression;
//		try {
//			oclExpression = helper.createQuery(query);
////			System.out.println("Evaluating: " + query);
//			Query<EClassifier, EClass, EObject> oclQuery = ocl.createQuery(oclExpression);
//			Object result = oclQuery.evaluate(contextualElement);
//			if(result instanceof OCLStandardLibrary<?>) {
//				System.err.println("EEROR invalid query");
//			}
////			System.out.println("DONE");
//			oclQuery = null;
//			oclExpression = null;
//			helper = null;
//			ocl = null;
//			return result;
//		} catch (ParserException e) {
//			System.err.println("ContextualElement --> " + contextualElement.toString());
//			System.err.println("Query --> " + query);
//			e.printStackTrace();
//		}
//		return null;
//	}

//	@Override
//	protected HashSet<?> getQueryResult(String query, EObject model) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
