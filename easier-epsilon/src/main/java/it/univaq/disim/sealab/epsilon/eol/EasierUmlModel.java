package it.univaq.disim.sealab.epsilon.eol;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.epsilon.emc.uml.UmlModel;
import org.eclipse.papyrus.MARTE.MARTE_AnalysisModel.GQAM.GQAMPackage;
import org.eclipse.papyrus.MARTE.MARTE_Foundations.Alloc.AllocPackage;
import org.eclipse.papyrus.MARTE.MARTE_Foundations.CoreElements.CoreElementsPackage;
import org.eclipse.papyrus.MARTE.MARTE_Foundations.GRM.GRMPackage;
import org.eclipse.papyrus.MARTE.MARTE_Foundations.NFPs.NFPsPackage;
import org.eclipse.papyrus.MARTE.MARTE_Foundations.Time.TimePackage;
import org.eclipse.uml2.uml.UMLPlugin;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;

import com.masdes.dam.Complex_Data_Types.Complex_Data_TypesPackage;
import com.masdes.dam.Core.CorePackage;
import com.masdes.dam.DAM.DAMPackage;
import com.masdes.dam.Maintenance.MaintenancePackage;
import com.masdes.dam.Threats.ThreatsPackage;

public class EasierUmlModel extends UmlModel {
	
	
	// MARTE       
	private static final String MARTE_BASE_PATHMAP = "pathmap://Papyrus_PROFILES/";
	private static final String MARTE_PROFILE = "MARTE.profile.uml#";
	private static final String MARTE_NFP_FRAGMENT = "_U_GAoAPMEdyuUt-4qHuVvQ";
	private static final String MARTE_TIME_FRAGMENT = "_WStkoAPMEdyuUt-4qHuVvQ";
	private static final String MARTE_GRM_FRAGMENT = "_XVWGUAPMEdyuUt-4qHuVv";
	private static final String MARTE_ALLOC_FRAGMENT = "_ar8OsAPMEdyuUt-4qHuVvQ";
	private static final String MARTE_CORE_ELEMENTS_FRAGMENT = "_-wEewECLEd6UTJZnztgOLw";
	private static final String MARTE_GQAM_FRAGMENT = "_4bV20APMEdyuUt-4qHuVvQ";
	               
	// DAM         
	private static final String DAM_BASE_PATHMAP = "pathmap://DAM_PROFILES/";
	private static final String DAM_PROFILE = "DAM.profile.uml#";
	private static final String DAM_PROFILE_FRAGMENT = "_dYZGQOI-EeKRk-i8_Z91aQ";
	private static final String DAM_CORE_FRAGMENT = "_DchGAOSiEeKuSu-I2xDxSA";
	private static final String DAM_THREATS_FRAGMENT = "_G1-xoOShEeKuSu-I2xDxSA";
	private static final String DAM_MAINTENANCE_FRAGMENT = "_rsXqkOShEeKuSu-I2xDxSA";

	/**
	 * It has been inspired by the solution proposed in this post
	 * 
	 * @url https://www.eclipse.org/forums/index.php/m/1701551/?srch=standalone#msg_1701551
	 * 
	 *      We are using MARTE and DAM profiles versions: MARTE :
	 *      org.eclipse.papyrus.marte.static.profile_1.2.0.201606080903, download
	 *      from @url
	 *      http://download.eclipse.org/modeling/mdt/papyrus/updates/releases/neon
	 *      DAM : com.masdes.dam.static.profile_0.13.1.201801221725.jar, downloaded
	 *      from @url https://github.com/dice-project/DICE-Profiles
	 * 
	 *      TAKE If the version of that plugin changes, this link must change as
	 *      well.
	 *
	 */
	@Override
	protected ResourceSet createResourceSet() {
		ResourceSet resourceSet = super.createResourceSet();

		resourceSet = UMLResourcesUtil.init(resourceSet);

		// stores UML model and UML profile extensions to the ExtensionToFactoryMap
		// {@see
		// org.eclipse.emf.ecore.resource.Resource.Factory.Registry.getExtensionToFactoryMap()}
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.PROFILE_FILE_EXTENSION,
				UMLResource.Factory.INSTANCE);

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION,
				UMLResource.Factory.INSTANCE);

		resourceSet = initMARTE(resourceSet);
		resourceSet = initDAM(resourceSet);

		return resourceSet;
	}

	/**
	 * @param resourceSet
	 * @return
	 * 
	 *         Maps physical resource and the pathmap schema. Stores every needed
	 *         package used in the model
	 * 
	 */
	private ResourceSet setMarte(ResourceSet resourceSet) {
		// points the profile JAR file
		String marteJarString = "jar:" + getClass()
				.getResource("/libs/org.eclipse.papyrus.marte.static.profile_1.2.0.201606080903.jar").toString() + "!/";
		
		// generates the URI for the JAR file
//		URI marteJarURI = URI.createURI(marteJarString);
		URI marteJarURI = URI.createURI(getClass().getResource("/marte").toString());
		String MARTE_PROFILES_PATHMAP = "pathmap://Papyrus_PROFILES/";
		
		// creates the map between the pathmap and the jar file
		resourceSet.getURIConverter().getURIMap().put(URI.createURI("pathmap://Papyrus_PROFILES/"),
				marteJarURI.appendSegment("resources").appendSegment(""));
		
		// The uri fragments refer to the IDs within the profile.uml
		// mapping between profiles and packages are as follows.
		MARTE_PROFILES_PATHMAP += "MARTE.profile.uml#";
		// NFP _U_GAoAPMEdyuUt-4qHuVvQ
		URI NFPURI = URI.createURI(MARTE_PROFILES_PATHMAP + "_U_GAoAPMEdyuUt-4qHuVvQ");
		UMLPlugin.getEPackageNsURIToProfileLocationMap().put(NFPsPackage.eNS_URI, NFPURI);
		resourceSet.getPackageRegistry().put(NFPsPackage.eNS_URI, NFPsPackage.eINSTANCE);
		// TIME _WStkoAPMEdyuUt-4qHuVvQ
		URI TIMEURI = URI.createURI(MARTE_PROFILES_PATHMAP + "_WStkoAPMEdyuUt-4qHuVvQ");
		UMLPlugin.getEPackageNsURIToProfileLocationMap().put(TimePackage.eNS_URI, TIMEURI);
		resourceSet.getPackageRegistry().put(TimePackage.eNS_URI, TimePackage.eINSTANCE);
		// GRM _XVWGUAPMEdyuUt-4qHuVvQ
		URI GRMURI = URI.createURI(MARTE_PROFILES_PATHMAP + "_XVWGUAPMEdyuUt-4qHuVv");
		UMLPlugin.getEPackageNsURIToProfileLocationMap().put(GRMPackage.eNS_URI, GRMURI);
		resourceSet.getPackageRegistry().put(GRMPackage.eNS_URI, GRMPackage.eINSTANCE);
		// Alloc _ar8OsAPMEdyuUt-4qHuVvQ
		URI AllocURI = URI.createURI(MARTE_PROFILES_PATHMAP + "_ar8OsAPMEdyuUt-4qHuVvQ");
		UMLPlugin.getEPackageNsURIToProfileLocationMap().put(AllocPackage.eNS_URI, AllocURI);
		resourceSet.getPackageRegistry().put(AllocPackage.eNS_URI, AllocPackage.eINSTANCE);
		// Core_Elements _-wEewECLEd6UTJZnztgOLw
		URI CoreElementsURI = URI.createURI(MARTE_PROFILES_PATHMAP + "_-wEewECLEd6UTJZnztgOLw");
		UMLPlugin.getEPackageNsURIToProfileLocationMap().put(CoreElementsPackage.eNS_URI, CoreElementsURI);
		resourceSet.getPackageRegistry().put(CoreElementsPackage.eNS_URI, CoreElementsPackage.eINSTANCE);
		// GQAM _4bV20APMEdyuUt-4qHuVvQ
		URI GQAMProfileURI = URI.createURI(MARTE_PROFILES_PATHMAP + "_4bV20APMEdyuUt-4qHuVvQ");
		UMLPlugin.getEPackageNsURIToProfileLocationMap().put(GQAMPackage.eNS_URI, GQAMProfileURI);
		resourceSet.getPackageRegistry().put(GQAMPackage.eNS_URI, GQAMPackage.eINSTANCE);

		return resourceSet;
	}
	
	private ResourceSet initMARTE(ResourceSet resourceSet) {
		final URI marteURI = URI.createURI(getClass().getResource("/marte").toString());
		resourceSet.getURIConverter().getURIMap().put(URI.createURI(MARTE_BASE_PATHMAP),
				marteURI.appendSegment(""));
		final String MARTE_PROFILES_PATHMAP = MARTE_BASE_PATHMAP + MARTE_PROFILE;
		
		// NFP
		UMLPlugin.getEPackageNsURIToProfileLocationMap()
			.put(NFPsPackage.eNS_URI, URI.createURI(MARTE_PROFILES_PATHMAP + MARTE_NFP_FRAGMENT));
		resourceSet.getPackageRegistry().put(NFPsPackage.eNS_URI, NFPsPackage.eINSTANCE);
		
		// TIME
		UMLPlugin.getEPackageNsURIToProfileLocationMap()
			.put(TimePackage.eNS_URI, URI.createURI(MARTE_PROFILES_PATHMAP + MARTE_TIME_FRAGMENT));
		resourceSet.getPackageRegistry().put(TimePackage.eNS_URI, TimePackage.eINSTANCE);
		
		// GRM
		UMLPlugin.getEPackageNsURIToProfileLocationMap()
			.put(GRMPackage.eNS_URI, URI.createURI(MARTE_PROFILES_PATHMAP + MARTE_GRM_FRAGMENT));
		resourceSet.getPackageRegistry().put(GRMPackage.eNS_URI, GRMPackage.eINSTANCE);
		
		// Alloc		
		UMLPlugin.getEPackageNsURIToProfileLocationMap()
			.put(AllocPackage.eNS_URI, URI.createURI(MARTE_PROFILES_PATHMAP + MARTE_ALLOC_FRAGMENT));
		resourceSet.getPackageRegistry().put(AllocPackage.eNS_URI, AllocPackage.eINSTANCE);
		
		// Core_Elements
		UMLPlugin.getEPackageNsURIToProfileLocationMap()
			.put(CoreElementsPackage.eNS_URI, URI.createURI(MARTE_PROFILES_PATHMAP + MARTE_CORE_ELEMENTS_FRAGMENT));
		resourceSet.getPackageRegistry().put(CoreElementsPackage.eNS_URI, CoreElementsPackage.eINSTANCE);
		
		// GQAM
		UMLPlugin.getEPackageNsURIToProfileLocationMap()
			.put(GQAMPackage.eNS_URI, URI.createURI(MARTE_PROFILES_PATHMAP + MARTE_GQAM_FRAGMENT));
		resourceSet.getPackageRegistry().put(GQAMPackage.eNS_URI, GQAMPackage.eINSTANCE);
		
		return resourceSet;
	}
	
	private ResourceSet initDAM(ResourceSet resourceSet) {
		final URI damURI = URI.createURI(getClass().getResource("/dam").toString());
		resourceSet.getURIConverter().getURIMap().put(URI.createURI(DAM_BASE_PATHMAP),
				damURI.appendSegment(""));
		final String DAM_PROFILES_PATHMAP = DAM_BASE_PATHMAP + DAM_PROFILE;
		
		// DAM_Profile
		UMLPlugin.getEPackageNsURIToProfileLocationMap()
			.put(DAMPackage.eNS_URI, URI.createURI(DAM_PROFILES_PATHMAP + DAM_PROFILE_FRAGMENT));
		resourceSet.getPackageRegistry().put(DAMPackage.eNS_URI, DAMPackage.eINSTANCE);
		
		// Core
		UMLPlugin.getEPackageNsURIToProfileLocationMap()
			.put(CorePackage.eNS_URI, URI.createURI(DAM_PROFILES_PATHMAP + DAM_CORE_FRAGMENT));
		resourceSet.getPackageRegistry().put(CorePackage.eNS_URI, CorePackage.eINSTANCE);
		
		// Threats
		UMLPlugin.getEPackageNsURIToProfileLocationMap()
			.put(ThreatsPackage.eNS_URI, URI.createURI(DAM_PROFILES_PATHMAP + DAM_THREATS_FRAGMENT));
		resourceSet.getPackageRegistry().put(ThreatsPackage.eNS_URI, ThreatsPackage.eINSTANCE);
		
		// Maintenance
		UMLPlugin.getEPackageNsURIToProfileLocationMap()
			.put(MaintenancePackage.eNS_URI, URI.createURI(DAM_PROFILES_PATHMAP + DAM_MAINTENANCE_FRAGMENT));
		resourceSet.getPackageRegistry().put(MaintenancePackage.eNS_URI, MaintenancePackage.eINSTANCE);
		
		return resourceSet;
	}
	

	/**
	 * @param resourceSet
	 * @return
	 * 
	 *         Maps physical resource and the pathmap schema. Stores every needed
	 *         package used in the model
	 */
	private ResourceSet setDAM(ResourceSet resourceSet) {

		// points the profile JAR file
		String damJarString = "jar:"
				+ getClass().getResource("/libs/com.masdes.dam.static.profile_0.13.1.201801221725.jar").toString()
				+ "!/";
		// generates the URI for the JAR file
		URI damJarURI = URI.createURI(damJarString);
		String DAM_PROFILES_PATHMAP = "pathmap://DAM_PROFILES/";
		// creates the map between the pathmap and the jar file
		resourceSet.getURIConverter().getURIMap().put(URI.createURI(DAM_PROFILES_PATHMAP),
				damJarURI.appendSegment("resources").appendSegment(""));
		// The uri fragments refer to the IDs within the profile.uml
		// mapping between profiles and packages are as follows.
		DAM_PROFILES_PATHMAP += "DAM.profile.uml#";
		// DAM_PRofile _dYZGQOI-EeKRk-i8_Z91aQ
		URI DAM_PRofile = URI.createURI(DAM_PROFILES_PATHMAP + "_dYZGQOI-EeKRk-i8_Z91aQ");
		UMLPlugin.getEPackageNsURIToProfileLocationMap().put(DAMPackage.eNS_URI, DAM_PRofile);
		resourceSet.getPackageRegistry().put(DAMPackage.eNS_URI, DAMPackage.eINSTANCE);
		// CORE _DchGAOSiEeKuSu-I2xDxSA
		URI CoreProfileURI = URI.createURI(DAM_PROFILES_PATHMAP + "_DchGAOSiEeKuSu-I2xDxSA");
		UMLPlugin.getEPackageNsURIToProfileLocationMap().put(CorePackage.eNS_URI, CoreProfileURI);
		resourceSet.getPackageRegistry().put(CorePackage.eNS_URI, CorePackage.eINSTANCE);
		// Threats _G1-xoOShEeKuSu-I2xDxSA
		URI ThreatsProfileURI = URI.createURI(DAM_PROFILES_PATHMAP + "_G1-xoOShEeKuSu-I2xDxSA");
		UMLPlugin.getEPackageNsURIToProfileLocationMap().put(ThreatsPackage.eNS_URI, ThreatsProfileURI);
		resourceSet.getPackageRegistry().put(ThreatsPackage.eNS_URI, ThreatsPackage.eINSTANCE);
		// Maintenance _rsXqkOShEeKuSu-I2xDxSA
		URI MaintenenaceProfileURI = URI.createURI(DAM_PROFILES_PATHMAP + "_rsXqkOShEeKuSu-I2xDxSA");
		UMLPlugin.getEPackageNsURIToProfileLocationMap().put(MaintenancePackage.eNS_URI, MaintenenaceProfileURI);
		resourceSet.getPackageRegistry().put(MaintenancePackage.eNS_URI, MaintenancePackage.eINSTANCE);

		// Complex_Data_Types
		resourceSet.getPackageRegistry().put(Complex_Data_TypesPackage.eNS_URI, Complex_Data_TypesPackage.eINSTANCE);
		return resourceSet;
	}

	public ResourceSet getResourceSet() {
		return this.getResource().getResourceSet();
	}
}