package it.univaq.disim.sealab.metaheuristic.evolutionary;
//package it.univaq.disim.sealab.metaheuristic.evolutionary;
//
//import java.io.IOException;
//import java.rmi.UnexpectedException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.lang3.RandomUtils;
//import org.eclipse.emf.common.util.EList;
//import org.eclipse.emf.common.util.URI;
//import org.eclipse.emf.ecore.resource.Resource;
//import org.eclipse.emf.ecore.resource.ResourceSet;
//import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
//import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
//import org.eclipse.ocl.ParserException;
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//
//import it.univaq.disim.sealab.metaheuristic.managers.Manager;
//import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclManager;
//import mapmeasurestoindices.MapmeasurestoindicesPackage;
//import metamodel.mmaemilia.AEmiliaSpecification;
//import metamodel.mmaemilia.ArchiElemInstance;
//import metamodel.mmaemilia.Attachment;
//import metamodel.mmaemilia.InputInteraction;
//import metamodel.mmaemilia.OutputInteraction;
//import metamodel.mmaemilia.mmaemiliaPackage;
//
//@Ignore
//public class RCrossoverTest {
//
//	private AEmiliaSpecification aemiliaModel;
//	private ArchiElemInstance sourceAEI, sourceAEI2, sourceAEI3;
//	private ArchiElemInstance clonedAEI, clonedAEI2, clonedAEI3;
//	private ResourceSet resourceSet;
//	private Resource resource;
//
//	private EList<OutputInteraction> listOfOutputInteractions;
//	private EList<InputInteraction> listOfInputInteractions;
//	private EList<Attachment> listOfNewAttachments;
//
//	private static String AEMILIA_ABSOLUT_PATH = "/Users/peo12/git/sealab/pakimor/metaheuristic/src/main/resources/models/AemiliaModels/FTA/FTA";
//
//	private OclManager oclManager;
//	private Manager manager;
//
//	private RProblem problem;
//	private Controller controller;
//
//	@Before
//	public void init() {
//		
//		String[] args = {"/src/main/resources/config.ini", "/Users/peo12/git/sealab/pakimor/metaheuristic/src/main/resources/models/AemiliaModels/FTA/FTA"};
//		controller = new Controller(args);
//		
//		try {
//			controller.run();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		// getOclManager().inizialize(getResourceSet());
////		oclManager = new OclAemiliaManager();
//		manager = controller.getManager();
////		packageRegistering();
////		resource = getResource(AEMILIA_ABSOLUT_PATH);
////		aemiliaModel = (AEmiliaSpecification) EcoreUtil.getObjectByType(resource.getContents(),
////				mmaemiliaPackage.Literals.AEMILIA_SPECIFICATION);
//		// setREFACTORED_MODEL_BASE_PATH("/src/main/resources/models/refactored/");
////		manager.setModel(aemiliaModel);
//
//		problem = new RProblem(AEMILIA_ABSOLUT_PATH, 4, 4, 1000, 4, manager);
//
//	}
//
//	public void packageRegistering() {
//		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("rewmapping", new XMIResourceFactoryImpl());
//		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("mmaemilia", new XMIResourceFactoryImpl());
//		resourceSet = new ResourceSetImpl();
//		resourceSet.getPackageRegistry().put(MapmeasurestoindicesPackage.eINSTANCE.getNsURI(),
//				MapmeasurestoindicesPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(mmaemiliaPackage.eINSTANCE.getNsURI(), mmaemiliaPackage.eINSTANCE);
//
//	}
//
//	public Resource getResource(String ameliaAbsolutePath) {
//		URI uri = URI.createFileURI(ameliaAbsolutePath);
//		resource = resourceSet.getResource(uri, true);
//		return resource;
//	}
//
//	@Test
//	public void doCrossoverTest() {
//		try {
//			RSolution parent1 = new RSolution(problem);
//			RSolution parent2 = new RSolution(problem);
//			
//			assert(!parent1.equals(parent2));
//			
//			assert(parent1.getVariableValue(0).getRefactoring() != null);
//			assert(parent2.getVariableValue(0).getRefactoring() != null);
//			
//			List<RSolution> offspring = doCrossover(0.01, parent1, parent2);
//			
//			for(RSolution sol : offspring) {
//				assert(sol.getVariableValue(0).getModel() != null);
//			}
//			
//		} catch (UnexpectedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParserException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Perform the crossover operation.
//	 *
//	 * @param probability
//	 *            Crossover setProbability
//	 * @param parent1
//	 *            The first parent
//	 * @param parent2
//	 *            The second parent
//	 * @return An array containing the two offspring
//	 */
//	public List<RSolution> doCrossover(double probability, RSolution parent1, RSolution parent2) {
//		assert (parent1.equals(parent2));
//
//		if (parent1.equals(parent2)) {
//			// TODO something
//		}
//
//		List<RSolution> offspring = new ArrayList<>(2);
//		offspring.add((RSolution) parent1.copy());
//		assert (offspring.get(0).getModel().equals(parent1.getModel()));
//
//		offspring.add((RSolution) parent2.copy());
//		// WE NEED TO DEFINE HERE THE CROSSOVER POLICY
//
//		// CROSSOVER PROBABILITY: how many couples will be picked for mating
//		// System.out.println("r: "+randomGenerator.nextDouble() + " p: " +
//		// probability);
//		// if (randomGenerator.nextDouble() < probability) {
//		System.out.println("CROSSOVERED");
//		// 1. Get the total number of bits
//		int length = parent1.getLength();
//
//		// 2. Calculate the point to make the crossover
//		int crossoverPoint = RandomUtils.nextInt(1, length - 1);
//
//		// 3. The variable containing the sequence
//
//		int variable = 0;
//		/*
//		 * int bitsAccount = parent1.getVariableValue(variable).getBinarySetLength();
//		 * 
//		 * while (bitsAccount < (crossoverPoint + 1)) { variable++; bitsAccount +=
//		 * parent1.getVariableValue(variable).getBinarySetLength() ; }
//		 */
//		// 4. Compute the crossover point
//
//		assert (parent1.getVariableValue(variable).getLength() == parent2.getVariableValue(variable).getLength());
//
//		// 5. Apply the crossover to the variable;
//
//		assert (parent1 != null);
//		assert (parent2 != null);
//		RSolution offspring1 = new RSolution(parent1, parent2, crossoverPoint, true);
//		offspring1.setParents(parent1, parent2);
//		assert (offspring1.getModel().equals(offspring1.getModel()));
//
//		RSolution offspring2 = new RSolution(parent1, parent2, crossoverPoint, false);
//		offspring1.setParents(parent2, parent1);
//
//		offspring.add(offspring1);
//		offspring.add(offspring2);
//
//		assert (offspring.size() == 4);
//
//		// for (int i = intoVariableCrossoverPoint; i < offspring1.getBinarySetLength();
//		// i++) {
//		// boolean swap = offspring1.get(i);
//		// offspring1.set(i, offspring2.get(i));
//		// offspring2.set(i, swap);
//		// }
//
//		// offspring.get(0).setVariableValue(variable, offspring1);
//		// offspring.get(1).setVariableValue(variable, offspring2);
//
//		// // 6. Apply the crossover to the other variables
//		// for (int i = variable + 1; i < parent1.getNumberOfVariables(); i++) {
//		// offspring.get(0).setVariableValue(i, (BinarySet)
//		// parent2.getVariableValue(i).clone());
//		// offspring.get(1).setVariableValue(i, (BinarySet)
//		// parent1.getVariableValue(i).clone());
//		// }
//
//		// }
//		for (RSolution rSolution : offspring) {
//			rSolution.setCrossovered(true);
//		}
//		RSolution.XOverCounter++;
//		return offspring;
//	}
//
//}
