package it.univaq.disim.sealab.epsilon.refactoring;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.util.UMLUtil;

public class UmlModel extends EmfModel {
	
	@Override
	protected ResourceSet createResourceSet() {
		ResourceSet resourceSet = super.createResourceSet();
		UMLUtil.init(resourceSet);
		resourceSet.getPackageRegistry().put(UMLPackage.eINSTANCE.getNsURI(), UMLPackage.eINSTANCE);
		
//		resourceSet.getPackageRegistry().put("http:///schemas/GQAM/_GBjG0G-CEeqmpuYYGHlOBw/0", GQAMPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put("http:///schemas/DataTypes/_GBQy8W-CEeqmpuYYGHlOBw/0", DataTypesPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put("http:///schemas/NFPs/_GBMhgG-CEeqmpuYYGHlOBw/0", NFPsPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put("http:///schemas/Time/_GBdnQG-CEeqmpuYYGHlOBw/0", TimePackage.eINSTANCE);
//		
		
		return resourceSet;
	}
	
}