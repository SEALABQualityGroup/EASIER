package it.disim.univaq.sealab.metaheuristic.managers.ocl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.ExpressionInOCL;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.expressions.OCLExpression;

import it.disim.univaq.sealab.metaheuristic.evolutionary.Controller;
import it.disim.univaq.sealab.metaheuristic.managers.Manager;
import metamodel.mmaemilia.ArchitecturalInteraction;

public abstract class OclManager {

	protected OCL ocl;
	protected Manager manager;
	protected Controller controller;

	public HashSet<?> evaluateQuery(String query) {
		return (HashSet<?>) getQueryResult(query);
	}

	public HashSet<?> evaluateQuery(String query, EObject model) {
		return (HashSet<?>) getQueryResult(query, model);
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	protected abstract HashSet<?> getQueryResult(String query);

	protected abstract HashSet<?> getQueryResult(String query, EObject model);

	public abstract HashSet<Object> evaluateOCL(String query);

	public abstract Object evaluateOCL(String query, Object contextualElement) throws ParserException;

	public void inizialize(ResourceSet resourceSet) {
		OCL.initialize(resourceSet);
	}

	@SuppressWarnings({ "resource", "finally", "unused" })
	private FileInputStream openFile(String filePath) {
		FileInputStream in = null;
		try {
			in = new FileInputStream(filePath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			return in;
		}
	}

	private void closeFile(FileInputStream in) {
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public OCLInput createOclInput(FileInputStream in) {
		return new OCLInput(in);
	}

	@SuppressWarnings("finally")
	public List<Constraint> getOclRulesFromFile(FileInputStream file) {
		OCLInput document = createOclInput(file);
		List<Constraint> listOfConstraints = null;
		try {
			listOfConstraints = ocl.parse(document);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return listOfConstraints;
		}

	}

	public Map<String, List<ArchitecturalInteraction>> countPAsFromOCLFromFile(String filePath,
			List<Object> contextualElements) {
		// EPackage.Registry registry = new EPackageRegistryImpl();
		// registry.put(mmaemiliaPackage.eNS_URI, mmaemiliaPackage.eINSTANCE);
		// EcoreEnvironmentFactory environmentFactory = new
		// EcoreEnvironmentFactory(registry);
		// OCL ocl;

		int apCounter = 0;

		Map<String, Constraint> constraintMap = new HashMap<String, Constraint>();
		Map<String, ExpressionInOCL> exprMap = new HashMap<String, ExpressionInOCL>();
		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);

		// get an OCL text file via some hypothetical API

		FileInputStream in = openFile(filePath);
		// parse the contents as an OCL document
		List<Constraint> apRules = getOclRulesFromFile(in);

		Map<String, List<ArchitecturalInteraction>> mapOfPerformanceAntipattern = new HashMap<>();

		for (Constraint nextConstraint : apRules) {
			List<ArchitecturalInteraction> listOfPerformanceAntipattern = new ArrayList<>();
			if (nextConstraint.getName() != null) {
				constraintMap.put(nextConstraint.getName(), nextConstraint);
				ExpressionInOCL expressionInOCL = (ExpressionInOCL) nextConstraint.getSpecification();
				exprMap.put(nextConstraint.getName(), expressionInOCL);
				OCLExpression<EClassifier> body = nextConstraint.getSpecification().getBodyExpression();

				for (Object el : contextualElements) {
//					Controller.logger_.info("\tCONTEXTUAL ELEMENT: " + ((ArchitecturalInteraction) el).getName());
					if (ocl.check(el, body)) {
						listOfPerformanceAntipattern.add((ArchitecturalInteraction) el);
						apCounter++;
						Controller.logger_.warning(nextConstraint.getName() + " DETECTED!");
					}
				}
				mapOfPerformanceAntipattern.put(nextConstraint.getName(), listOfPerformanceAntipattern);
			}
		}
		closeFile(in);
		return mapOfPerformanceAntipattern;
	}
}
