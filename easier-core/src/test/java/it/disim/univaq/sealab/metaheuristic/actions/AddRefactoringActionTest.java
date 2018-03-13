//package it.disim.univaq.sealab.metaheuristic.actions;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//
//import org.eclipse.ocl.ParserException;
//import org.eclipse.uml2.uml.Component;
//import org.eclipse.uml2.uml.Model;
//import org.eclipse.uml2.uml.Node;
//import org.junit.Before;
//import org.junit.Test;
//
//import it.disim.univaq.sealab.metaheuristic.actions.uml.UMLAddComponentRefactoringAction;
//import it.disim.univaq.sealab.metaheuristic.actions.uml.UMLAddNodeRefactoringAction;
//import it.disim.univaq.sealab.metaheuristic.actions.uml.UMLAddOperationRefactoringAction;
//import it.disim.univaq.sealab.metaheuristic.managers.Manager;
//import it.disim.univaq.sealab.metaheuristic.managers.OclManager;
//import logicalSpecification.Action;
//
//public class AddRefactoringActionTest {
//
//	private Model testModel;
//
//	@Test
//	public void addNodeTest() {
//		// Model testModel =
//		// Manager.getUMLModel("src/main/resources/models/BGCS/BGCS.uml");
//
//		String query = "Node.allInstances()";
//		HashSet<Object> hashSetQuery;
//		List<Node> resQuery = new ArrayList<Node>();
//
//		// hashSetQuery = (HashSet<Object>) Manager.evaluateOCL(query,
//		// testModel);
//		hashSetQuery = (HashSet<Object>) getHashSetObject(query);
//
//		for (Object object : hashSetQuery) {
//			if (object instanceof Node)
//				resQuery.add((Node) object);
//		}
//
//		String query2 = "Component.allInstances()";
//
//		List<Component> resQuery2 = new ArrayList<Component>();
//
//		// hashSetQuery = (HashSet<Object>) Manager.evaluateOCL(query,
//		// testModel);
//		hashSetQuery = (HashSet<Object>) getHashSetObject(query2);
//
//		for (Object object : hashSetQuery) {
//			if (object instanceof Component)
//				resQuery2.add((Component) object);
//		}
//
//		Action action = new UMLAddNodeRefactoringAction(resQuery, resQuery2);
//		action.execute();
//
//		action.log();
//
//	}
//
//	@SuppressWarnings("unchecked")
//	@Test
//	public void addComponentTest() {
//		// Model testModel =
//		// Manager.getUMLModel("src/main/resources/models/BGCS/BGCS.uml");
//
//		String query = "Component.allInstances()";
//		HashSet<Object> hashSetQuery;
//		List<Component> resQuery = new ArrayList<Component>();
//
//		// hashSetQuery = (HashSet<Object>) Manager.evaluateOCL(query,
//		// testModel);
//		hashSetQuery = (HashSet<Object>) getHashSetObject(query);
//		for (Object object : hashSetQuery) {
//			if (object instanceof Component)
//				resQuery.add((Component) object);
//		}
//
//		String query2 = "Node.allInstances()";
//		// HashSet<Object> hashSetQuery;
//		List<Node> resQuery2 = new ArrayList<Node>();
//
//		// hashSetQuery = (HashSet<Object>) Manager.evaluateOCL(query,
//		// testModel);
//		hashSetQuery = (HashSet<Object>) getHashSetObject(query2);
//
//		for (Object object : hashSetQuery) {
//			if (object instanceof Node)
//				resQuery2.add((Node) object);
//		}
//
//		UMLAddComponentRefactoringAction action = new UMLAddComponentRefactoringAction(resQuery2);
//		action.execute();
//
//		action.log();
//
//	}
//
//	@Test
//	public void addOperationTest() {
//
//		String query = "Component.allInstances()->asSequence()->first()";
//		Object hashSetQuery;
//		Component resQuery = null;
//
//		hashSetQuery = getHashSetObject(query);
//		if (hashSetQuery instanceof Component)
//			resQuery = (Component) hashSetQuery;
//
//		UMLAddOperationRefactoringAction action = new UMLAddOperationRefactoringAction(resQuery);
//		action.execute();
//
//		action.log();
//
//	}
//
//	private Object getHashSetObject(String query) {
//		Object hashSet;
//		try {
//			//hashSet = (HashSet<Object>) Manager.evaluateOCL(query, testModel);
//			hashSet = OclManager.evaluateOCL(query, testModel);
//			return hashSet;
//		} catch (ParserException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Before
//	public void initialize() {
//		Manager.getInstance().init("src/main/resources/models/BGCS/BGCS.uml");
//		testModel = Manager.getInstance().getModel();
//	}
//}
