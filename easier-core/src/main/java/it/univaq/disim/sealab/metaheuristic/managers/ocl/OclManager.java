package it.univaq.disim.sealab.metaheuristic.managers.ocl;

import java.util.HashSet;

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
import org.eclipse.ocl.types.OCLStandardLibrary;

public abstract class OclManager {

//	protected OCL ocl;
//	protected MetamodelManager MM_manager;
//	protected Controller controller;

//	public HashSet<?> evaluateQuery(final String query) {
//		return (HashSet<?>) getQueryResult(query);
//	}

//	public HashSet<?> evaluateQuery(final String query, EObject model) {
//		return (HashSet<?>) getQueryResult(query, model);
//	}

//	protected abstract HashSet<?> getQueryResult(String query);

//	protected abstract HashSet<?> getQueryResult(String query, EObject model);

//	public HashSet<Object> evaluateOCL(String query) {
//		return (HashSet<Object>) this.evaluateQuery(query);
//	}

//	public abstract Object evaluateOCL(String query, EObject contextualElement);//throws ParserException;

	public static Object evaluateOCL(String query, EObject contextualElement) { // throws ParserException {
		// create an OCL instance for Ecore
		OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE, contextualElement.eResource());

		// create an OCL helper object
		OCLHelper<EClassifier, ?, ?, Constraint> helper = ocl.createOCLHelper();

		// TODO check
		helper.setContext(contextualElement.eClass());

		// helper.setInstanceContext(UMLPackage.eINSTANCE.getClass_());
		OCLExpression<EClassifier> oclExpression;
		try {
			oclExpression = helper.createQuery(query);
//			System.out.println("Evaluating: " + query);
			Query<EClassifier, EClass, EObject> oclQuery = ocl.createQuery(oclExpression);
			Object result = oclQuery.evaluate(contextualElement);
			if (result instanceof OCLStandardLibrary<?>) {
				System.err.println("EEROR invalid query");
			}
//			System.out.println("DONE");
			oclQuery = null;
			oclExpression = null;
			helper = null;
			ocl = null;
			return result;
		} catch (ParserException e) {
			System.err.println("ContextualElement --> " + contextualElement.toString());
			System.err.println("Query --> " + query);
			e.printStackTrace();
		}
		return null;
	}

//	public void inizialize(ResourceSet resourceSet) {
//		OCL.initialize(resourceSet);
//	}

	/*
	 * @SuppressWarnings({ "resource", "finally", "unused" }) private
	 * FileInputStream openFile(Path filePath) { FileInputStream in = null; try { in
	 * = new FileInputStream(filePath.toFile()); } catch (FileNotFoundException e1)
	 * { // TODO Auto-generated catch block e1.printStackTrace(); } finally { return
	 * in; } }
	 */

	/*
	 * private void closeFile(FileInputStream in) { try { in.close(); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); } }
	 */

	/*
	 * public OCLInput createOclInput(FileInputStream in) { return new OCLInput(in);
	 * }
	 */

	/*
	 * @SuppressWarnings("finally") public List<Constraint>
	 * getOclRulesFromFile(FileInputStream file) { OCLInput document =
	 * createOclInput(file); List<Constraint> listOfConstraints = null; try {
	 * listOfConstraints = ocl.parse(document); } catch (ParserException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); } finally { return
	 * listOfConstraints; }
	 * 
	 * }
	 */

	// It is no longer used, after the introduction of Epsilon
//	@Deprecated
//	public Map<String, List<ArchitecturalInteraction>> countPAsFromOCLFromFile(Path filePath,
//			List<Object> contextualElements) {
//		// EPackage.Registry registry = new EPackageRegistryImpl();
//		// registry.put(mmaemiliaPackage.eNS_URI, mmaemiliaPackage.eINSTANCE);
//		// EcoreEnvironmentFactory environmentFactory = new
//		// EcoreEnvironmentFactory(registry);
//		// OCL ocl;
//
//		int apCounter = 0;
//
//		Map<String, Constraint> constraintMap = new HashMap<String, Constraint>();
//		Map<String, ExpressionInOCL> exprMap = new HashMap<String, ExpressionInOCL>();
//		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
//
//		// get an OCL text file via some hypothetical API
//
//		FileInputStream in = openFile(filePath);
//		// parse the contents as an OCL document
//		List<Constraint> apRules = getOclRulesFromFile(in);
//
//		/* It stores PA name and a list of contextual model elements that generate the PA*/
//		Map<String, List<ArchitecturalInteraction>> mapOfPerformanceAntipattern = new HashMap<>();
//
//		for (Constraint nextConstraint : apRules) {
//			List<ArchitecturalInteraction> listOfPerformanceAntipattern = new ArrayList<>();
//			if (nextConstraint.getName() != null) {
//				constraintMap.put(nextConstraint.getName(), nextConstraint);
//				ExpressionInOCL expressionInOCL = (ExpressionInOCL) nextConstraint.getSpecification();
//				exprMap.put(nextConstraint.getName(), expressionInOCL);
//				OCLExpression<EClassifier> body = nextConstraint.getSpecification().getBodyExpression();
//
//				for (Object el : contextualElements) {
////					Controller.logger_.info("\tCONTEXTUAL ELEMENT: " + ((ArchitecturalInteraction) el).getName());
//					if (ocl.check(el, body)) {
//						listOfPerformanceAntipattern.add((ArchitecturalInteraction) el);
//						apCounter++;
//						EasierLogger.logger_.info(nextConstraint.getName() + " DETECTED!");
//					}
//				}
//				mapOfPerformanceAntipattern.put(nextConstraint.getName(), listOfPerformanceAntipattern);
//			}
//		}
//		closeFile(in);
//		return mapOfPerformanceAntipattern;
//	}
}
