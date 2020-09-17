package it.univaq.disim.sealab.epsilon.eol;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.URIMappingRegistryImpl;
import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.emc.uml.UmlModel;
import org.eclipse.papyrus.MARTE.MARTEPackage;
import org.eclipse.papyrus.MARTE.MARTE_AnalysisModel.GQAM.GQAMPackage;
import org.eclipse.papyrus.MARTE.MARTE_AnalysisModel.PAM.PAMPackage;
import org.eclipse.papyrus.MARTE.MARTE_AnalysisModel.SAM.SAMPackage;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.util.UMLUtil;

public class EasierUmlModel extends UmlModel {

	@Override
	protected ResourceSet createResourceSet() {
		ResourceSet resourceSet = super.createResourceSet();
//		UMLUtil.init(resourceSet);
//		resourceSet.getPackageRegistry().put(UMLPackage.eINSTANCE.getNsURI(), UMLPackage.eINSTANCE);

//		resourceSet.getPackageRegistry().put(UMLPackage.eINSTANCE.getNsURI(), UMLPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(EcorePackage.eINSTANCE.getNsURI(), EcorePackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(Ecore2XMLPackage.eINSTANCE.getNsURI(), Ecore2XMLPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(MARTEPackage.eINSTANCE.getNsURI(), MARTEPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(GQAMPackage.eINSTANCE.getNsURI(), GQAMPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(SAMPackage.eINSTANCE.getNsURI(), SAMPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(PAMPackage.eINSTANCE.getNsURI(), PAMPackage.eINSTANCE);

//		resourceSet.getPackageRegistry().put("http:///schemas/GQAM/_GBjG0G-CEeqmpuYYGHlOBw/0", GQAMPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put("http:///schemas/DataTypes/_GBQy8W-CEeqmpuYYGHlOBw/0", DataTypesPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put("http:///schemas/NFPs/_GBMhgG-CEeqmpuYYGHlOBw/0", NFPsPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put("http:///schemas/Time/_GBdnQG-CEeqmpuYYGHlOBw/0", TimePackage.eINSTANCE);
//		

		//TODO whene executed it arises this error ```java.net.MalformedURLException: unknown protocol: pathmap```
//		registerMetaModels();

		return resourceSet;
	}

	public ResourceSet getResourceSet() {
		return createResourceSet();
	}

	public void registerMetaModels() {
//		super.registerMetaModels();
		Map<URI, URI> uriMap = new HashMap<URI, URI>();
		uriMap = org.eclipse.uml2.uml.resources.util.UMLResourcesUtil.initURIConverterURIMap(uriMap);

		// load profile from papyrus-rt jar file, the jar file should be set in
		// classpath

//		String UMLRealTimeSMProfilePath = this.getClass().getClassLoader()
//				.getResource("umlProfile/UMLRealTimeSM-addendum.profile.uml").toString();
//		uriMap.put(URI.createURI("pathmap://UML_RT_PROFILE/UMLRealTimeSM-addendum.profile.uml"),
//				URI.createURI(UMLRealTimeSMProfilePath));
//		String RTCppPropertiesProfilePath = this.getClass().getClassLoader()
//		.getResource("profiles/RTCppProperties.profile.uml").toString();
//		uriMap.put(URI.createURI("pathmap://UMLRT_CPP/RTCppProperties.profile.uml"),
//		URI.createURI(RTCppPropertiesProfilePath));
//		String UMLRTProfilePath = this.getClass().getClassLoader().getResource("umlProfile/uml-rt.profile.uml")
//		.toString();
//		uriMap.put(URI.createURI("pathmap://UML_RT_PROFILE/uml-rt.profile.uml"), URI.createURI(UMLRTProfilePath));
		//register packages for UMLRT packages
//		URIMappingRegistryImpl.INSTANCE.putAll(uriMap);
//

		try {
			URL gqamURL = new URL(
					"file:/home/peo/git/sealab/uml2lqn/org.univaq.uml2lqn/UMLModel/MARTE.MARTE_AnalysisModel.GQAM.profile.uml");
			uriMap.put(URI.createURI("http:///schemas/GQAM/_ilfkcMA_Eeq4s5ZiqWeHpA/0"),
					URI.createURI(gqamURL.toString()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		URIMappingRegistryImpl.INSTANCE.putAll(uriMap);

		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("uml", new UMLResourceFactoryImpl());
		//Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION,
			//	UMLResource.Factory.INSTANCE);
		

		initGlobalRegistry();

//				UMLRealTimePackage.eNS_URI, UMLRealTimePackage.eINSTANCE);
//		
//		UMLRealTimePackage.class.getResource("");
//		
//		
//		EPackage.Registry.INSTANCE.put(RTCppPropertiesPackage.eNS_URI, RTCppPropertiesPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(UMLRTStateMachinesPackage.eNS_URI, UMLRTStateMachinesPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(StandardPackage.eNS_URI, StandardPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
//		System.out.println("Metamodels and profiles loaded successfully for UMLRT models");
		// System.out.println("Size of URI convertor: "+URIConverter.URI_MAP.size());
		/*
		 * for (Entry<URI, URI> entry : URIConverter.URI_MAP.entrySet()) {
		 * System.out.println(entry.getKey() + "/" + entry.getValue()); }
		 */
	}

	private void initGlobalRegistry() {
		EPackage.Registry.INSTANCE.put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(Ecore2XMLPackage.eNS_URI, Ecore2XMLPackage.eINSTANCE);

		// MagicDraw dependencies
		String gqamURI = "http:///schemas/GQAM/_ilfkcMA_Eeq4s5ZiqWeHpA/0";

//		EPackage.Registry.INSTANCE.put(MARTEPackage.eNS_URI, MARTEPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(gqamURI, GQAMPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(SAMPackage.eNS_URI, SAMPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(PAMPackage.eNS_URI, PAMPackage.eINSTANCE);
	}

}