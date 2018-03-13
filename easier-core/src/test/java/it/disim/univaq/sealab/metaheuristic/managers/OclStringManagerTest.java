//package it.disim.univaq.sealab.metaheurist.managers;
//
//import java.util.List;
//
//import org.eclipse.uml2.uml.Component;
//import org.eclipse.uml2.uml.Model;
//import org.eclipse.uml2.uml.Node;
//import org.junit.Before;
//import org.junit.Test;
//
//import it.disim.univaq.sealab.metaheuristic.managers.Manager;
//import it.disim.univaq.sealab.metaheuristic.managers.OclStringManager;
//import it.disim.univaq.sealab.metaheuristic.managers.uml.UMLManager;
//
//public class OclStringManagerTest {
//	
//	private Model testModel;
//	
//	@Before
//	public void initialize() {
//		Manager.getInstance().init("src/main/resources/models/BGCS/BGCS.uml");
//		testModel = Manager.getInstance().getModel();
//	} 
//	
//	@Test
//	public void getComponentsQueryTest(){
//		List<Component> list_of_components = UMLManager.getAllComponents();
//		System.out.println(OclStringManager.getComponentsQuery(list_of_components));
//	}
//	
//	@Test
//	public void getDeploymentsOfTest(){
//		List<Node> list_of_nodes = UMLManager.getAllNodes();
//		System.out.println(OclStringManager.getAllDeployedElementsQuery(list_of_nodes));
//	}
//
//}
