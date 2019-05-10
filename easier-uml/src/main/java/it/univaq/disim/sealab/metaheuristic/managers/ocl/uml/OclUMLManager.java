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
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;

import it.univaq.disim.sealab.metaheuristic.managers.MetamodelManager;
import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclManager;
import metamodel.mmaemilia.mmaemiliaPackage;

public class OclUMLManager extends OclManager{
	
	public OclUMLManager(final MetamodelManager ma) {
		this.MM_manager  = ma;
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	private HashSet<Object> getHashSet(String query, Resource resource) {
		HashSet<Object> hashSetQuery = null;
		try {
			hashSetQuery = (HashSet<Object>) evaluateOCL(query, MM_manager.getModel(), resource);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashSetQuery;
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	protected HashSet<Object> getQueryResult(final String query) {
		Object queryResult = null;
		HashSet<Object> hashSet = null;
		try {
			hashSet = new HashSet<Object>();
			queryResult = evaluateOCL(query, MM_manager.getModel(), MM_manager.getResource());
			if (queryResult instanceof Integer) {
				int intQueryResult = Integer.parseInt(queryResult.toString());
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
	public Object evaluateOCL(String query, Object contextualElement, Resource resource) throws ParserException {
		// create an OCL instance for Ecore
		OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE, resource);

		// create an OCL helper object
		OCLHelper<EClassifier, ?, ?, Constraint> helper = ocl.createOCLHelper();

		// set the OCL context classifier
		if (contextualElement instanceof Model)
			helper.setContext(UMLPackage.Literals.MODEL);
		else if (contextualElement instanceof Package)
			helper.setContext(UMLPackage.Literals.PACKAGE);
		else if (contextualElement instanceof Component)
			helper.setContext(UMLPackage.Literals.COMPONENT);
		else if (contextualElement instanceof Operation)
			helper.setContext(UMLPackage.Literals.OPERATION);
		else if (contextualElement instanceof Node)
			helper.setContext(UMLPackage.Literals.NODE);

		// helper.setInstanceContext(UMLPackage.eINSTANCE.getClass_());
		OCLExpression<EClassifier> oclExpression = helper.createQuery(query);
		Query<EClassifier, EClass, EObject> oclQuery = ocl.createQuery(oclExpression);
		return oclQuery.evaluate(contextualElement);
	}
	


	public static List<?> evaluateOCL(String query, List<Object> contextualElements, Resource resource) throws ParserException {
		// create an OCL instance for Ecore
		OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE, resource);
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
		return oclQuery.evaluate(contextualElements);
	}

	public static boolean checkOCL(String query, Object contextualElement, Resource resource) throws ParserException {
		// create an OCL instance for Ecore
		OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE, resource);
		// create an OCL helper object
		OCLHelper<EClassifier, ?, ?, Constraint> helper = ocl.createOCLHelper();
		// set the OCL context classifier
		if (contextualElement instanceof Model)
			helper.setContext(UMLPackage.Literals.MODEL);
		else if (contextualElement instanceof Package)
			helper.setContext(UMLPackage.Literals.PACKAGE);
		else if (contextualElement instanceof Component)
			helper.setContext(UMLPackage.Literals.COMPONENT);
		else if (contextualElement instanceof Operation)
			helper.setContext(UMLPackage.Literals.OPERATION);
		else if (contextualElement instanceof Node)
			helper.setContext(UMLPackage.Literals.NODE);
		OCLExpression<EClassifier> oclExpression = helper.createQuery(query);
		Query<EClassifier, EClass, EObject> oclQuery = ocl.createQuery(oclExpression);
		return oclQuery.check(contextualElement);
	}

	public static boolean checkOCL(String query, List<Object> contextualElements, Resource resource) throws ParserException {
		// create an OCL instance for Ecore
		OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE, resource);
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

	@Override
	protected HashSet<?> getQueryResult(String query, EObject model, Resource resource) {
		// TODO Auto-generated method stub
		System.out.println("ERRORE!!!! evaluateOCL method in OclUMLManager");
		return null;
	}

	@Override
	public HashSet<Object> evaluateOCL(String query, Resource resource) {
		// TODO Auto-generated method stub
		System.out.println("ERRORE!!!! evaluateOCL method in OclUMLManager");
		return null;
	}

//	public void evaluateOCLFromFile(String filePath, List<Object> contextualElements) throws ParserException {
//		EPackage.Registry registry = new EPackageRegistryImpl();
//		registry.put(mmaemiliaPackage.eNS_URI, mmaemiliaPackage.eINSTANCE);
//		EcoreEnvironmentFactory environmentFactory = new EcoreEnvironmentFactory(registry);
//		OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
//		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
//
//		// get an OCL text file via some hypothetical API
//		FileInputStream in = null;
//		try {
//			in = new FileInputStream(filePath);
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		Map<String, Constraint> constraintMap = new HashMap<String, Constraint>();
//
//		// parse the contents as an OCL document
//		try {
//			OCLInput document = new OCLInput(in);
//
//			// List<Constraint> constraints = ocl.parse(document);
//			// for (Constraint next : constraints) {
//			// constraintMap.put(next.getName(), next);
//			//
//			// OCLExpression<EClassifier> body =
//			// next.getSpecification().getBodyExpression();
//			// System.out.printf("%s: %s%n", next.getName(), body);
//			// }
//
//			List<Constraint> apRules = ocl.parse(document);
//			for (Constraint next : apRules) {
//
//				if (next.getName() != null) {
//					constraintMap.put(next.getName(), next);
//					// Variable<EClassifier, EParameter> context =
//					// next.getSpecification().getContextVariable();
//					OCLExpression<EClassifier> body = next.getSpecification().getBodyExpression();
//					System.out.println("EXECUTING" + body.toString());
//					this.evaluateQuery(body.toString());
//					// System.out.printf("%s: %s%n", next.getName(), body);
//				}
//			}
//
//			// // create an OCL helper object
//			// OCLHelper<EClassifier, ?, ?, Constraint> helper = ocl.createOCLHelper();
//			//
//			// // set the OCL context classifier
//			// if (contextualElements.get(0) instanceof Package)
//			// helper.setContext(UMLPackage.Literals.PACKAGE);
//			// else if (contextualElements.get(0) instanceof Component)
//			// helper.setContext(UMLPackage.Literals.COMPONENT);
//			// else if (contextualElements.get(0) instanceof Operation)
//			// helper.setContext(UMLPackage.Literals.OPERATION);
//			// else if (contextualElements.get(0) instanceof Node)
//			// helper.setContext(UMLPackage.Literals.NODE);
//			//
//			// //Constraint invariant = helper.createInvariant("books->forAll(b1, b2 | b1 <>
//			// b2 implies b1.title <> b2.title)");
//			// OCLExpression<EClassifier> oclExpression = helper.createQuery(query);
//		} finally {
//			try {
//				in.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}

}
