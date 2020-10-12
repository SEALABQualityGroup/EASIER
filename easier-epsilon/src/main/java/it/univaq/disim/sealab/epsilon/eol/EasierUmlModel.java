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
	
	private ResourceSet resourceSet;

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
		resourceSet = super.createResourceSet();

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
	
	/**
	 * @param resourceSet
	 * @return
	 * 
	 *         Maps physical resource and the pathmap schema. Stores every needed
	 *         package used in the model
	 */
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
	
	public ResourceSet getResourceSet() {
		return this.resourceSet;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		this.resourceSet.getResources().forEach(rs -> org.eclipse.uml2.common.util.CacheAdapter.getInstance().clear(rs));
		this.resourceSet.getResources().clear();
		//this.resourceSet = null;
	}
	
}