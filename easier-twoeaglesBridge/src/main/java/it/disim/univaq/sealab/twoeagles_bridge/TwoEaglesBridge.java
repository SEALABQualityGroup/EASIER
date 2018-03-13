package it.disim.univaq.sealab.twoeagles_bridge;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.ldap.ManageReferralControl;
import javax.sound.midi.ControllerEventListener;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import aemiliaParser.AEMparser;
import aemiliametamodel.utilities.Find;
import equivalenzaComportamentale.secondRelease.Equivalenza2;
import it.univaq.from_aemilia_to_qn_plug_in.serialize.MMAemiliaSerialize;
import it.univaq.from_aemilia_to_qn_plug_in.serialize.MMAemiliaToArchiType;

import it.disim.univaq.sealab.ttep.rew.ParseException;
import it.disim.univaq.sealab.ttep.rew.RewParser;
import it.disim.univaq.sealab.ttep.rew.classes.MeasureDef;
import it.disim.univaq.sealab.ttep.rew.classes.RewSpec;
import it.disim.univaq.sealab.ttep.rew.classes.RewardAssign;
import it.disim.univaq.sealab.ttep.rew.classes.RewardStructure;
import it.disim.univaq.sealab.ttep.rew.wizard.normalize.NormalizeRew;
import it.disim.univaq.sealab.ttep.rew.wizard.providers.ExtractedIndex;
import it.disim.univaq.sealab.ttep.val.ValParser;
import it.disim.univaq.sealab.ttep.val.classes.MeasureValue;
import it.disim.univaq.sealab.ttep.val.classes.ValSpec;

import mapmeasurestoindices.ActionMeasure;
import mapmeasurestoindices.AeiMeasure;
import mapmeasurestoindices.ArchiIntMeasure;
import mapmeasurestoindices.IndexType;
import mapmeasurestoindices.MapmeasurestoindicesFactory;
import mapmeasurestoindices.MeasureMapping;
import mapmeasurestoindices.RewMapping;

import metamodel.mmaemilia.AEmiliaSpecification;
import metamodel.mmaemilia.ArchiElemInstance;
import metamodel.mmaemilia.ArchitecturalInteraction;
import metamodel.mmaemilia.Attachment;
import metamodel.mmaemilia.Elem;
import metamodel.mmaemilia.Behavior.Action;

import restrizioniGenerali.RestrizioniGenException;
import restrizioniGenerali.secondRelease.GeneraliRules2;
import restrizioniIstanze.RestrizioniIstanzeException;
import restrizioniIstanze.qnElemTypes.ElemTypeNorm;
import restrizioniIstanze.restrizioniIstanze.RestrizioniIstanze;

import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.scanExp.ScanExp;
import specificheAEmilia.ArchiType;
import specificheAEmilia.Expression;
import utilities.ErrorMessage;
import valutazione.normalization.NormalizeException;
import valutazione.scope.ScopeArchiType;

public class TwoEaglesBridge {

	private String twoTowersKernelPath;// = "/Users/peo12/git/sealab/panda-aemilia/TwoTowers/bin/TTKernel";
	private final String STATIONARY_REWARD_BASED_MEASURE_CALCULATOR_GAUSSIAN_ELIMINATION = "-s";

	private AEmiliaSpecification aemiliaSpecification;
	private ResourceSet resourceSet;

	// public TwoEaglesBridge(String twoTowersKernelPath) {
	// this.twoTowersKernelPath = twoTowersKernelPath;
	// }

	private List<ExtractedIndex> extractedIndices = new ArrayList<ExtractedIndex>();
	private Map<MeasureDef, List<metamodel.mmaemilia.Behavior.Action>> actionMap = new HashMap<MeasureDef, List<metamodel.mmaemilia.Behavior.Action>>();
	private Map<MeasureDef, List<ArchiElemInstance>> aeiMap = new HashMap<MeasureDef, List<ArchiElemInstance>>();
	private Map<MeasureDef, List<metamodel.mmaemilia.Behavior.Action>> actionMapUnique = new HashMap<MeasureDef, List<metamodel.mmaemilia.Behavior.Action>>();

	public void aemiliaModelGeneration(String aemiliaModelFilePath, String refactoreModelFilePath) {

		FileInputStream aemiliaFileInputStream = null;

		// = getModelPath();
		// = getRefactoredModelPath();

		try {
			aemiliaFileInputStream = new FileInputStream(aemiliaModelFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			// MetodiVari.stampaSuConsole(e2.getMessage());
		}

		AEMparser aeMparser = new AEMparser(aemiliaFileInputStream);
		try {
			ArchiType archiType = aeMparser.ArchiType();
			GeneraliRules2 generaliRules = new GeneraliRules2(archiType);
			boolean b = false;
			try {
				b = generaliRules.isCompliantGeneralRules(false);
			} catch (RestrizioniGenException e) {
				e.printStackTrace();
				;
			}
			if (b) {
				List<Equivalenza2> list = generaliRules.getEquivalenze();
				RestrizioniIstanze restrizioniIstanze = null;
				try {
					restrizioniIstanze = new RestrizioniIstanze(list, archiType, false);
				} catch (RestrizioniIstanzeException e) {
					e.printStackTrace();
				}
				if (!restrizioniIstanze.isErrorOccurred()) {

					ArchiType archiType2 = restrizioniIstanze.getArchiType();
					List<ElemTypeNorm> elemTypeNormList = restrizioniIstanze.getElemTypeNormList();

					MMAemiliaSerialize mmAemiliaSerialize = new MMAemiliaSerialize();
					mmAemiliaSerialize.serialize_ase(archiType2, elemTypeNormList, refactoreModelFilePath);

				} else {
					ErrorMessage errorMessage = restrizioniIstanze.getErrorMessage();
					System.out.println(errorMessage.toString());
				}
			} else {
				ErrorMessage errorMessage = generaliRules.getErrorMessage();
				System.out.println(errorMessage.toString());
			}
		} catch (aemiliaParser.ParseException e) {
			// try {
			// MetodiVari.stampaSuConsole("Scanning error: " +
			// MetodiVari.fromStream(aemiliaFileInputStream)
			// + "is not a valid architectural type" + "\n");
			// } catch (IOException e1) {
			// MetodiVari.stampaSuConsole(e1.getMessage());
			// }
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public void gaussianEliminationSRBMC(String aemFilePath, String rewFilePath, String outputFilePath) {
		try {
			Process process = new ProcessBuilder(getTwoTowersKernelPath(),
					STATIONARY_REWARD_BASED_MEASURE_CALCULATOR_GAUSSIAN_ELIMINATION, aemFilePath, rewFilePath,
					outputFilePath).start();
			process.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void measuresToIndices(Resource rewmappingAsResource, RewMapping rewMapping, String rewFilePath) {
		// String rewFilePath = AEMILIA_MANAGER.getRewFile();

		// ASE support data structures

		// aemiliaSpecification = (AEmiliaSpecification)
		// aemiliaResource.getContents().get(0);
		// aemiliaSpecification = (AEmiliaSpecification) ((AemiliaManager) Manager
		// .getInstance(AemiliaManager.getInstance()).getMetamodelManager()).getResource().getContents().get(0);
		// aemiliaSpecification = (AEmiliaSpecification)
		// AEMILIA_MANAGER.getResource().getContents().get(0);

		// si effettua la normalizzazione di rewSpec come fatto con le istanze di
		// elementi architetturali, senza concatenazione del selettore pero'
		// si serializza in ArchiType il metamodello AEmilia
		metamodel.mmaemilia.ArchiType archiType = getAemiliaSpecification().getArchiTypeDecl();
		MMAemiliaToArchiType mmAemiliaToArchiType = new MMAemiliaToArchiType();
		ArchiType archiTypeTransformed = null;
		try {
			archiTypeTransformed = mmAemiliaToArchiType.transform(archiType);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		// si normalizzano le misure secondo lo scope archiType
		ScopeArchiType scopeArchiType = null;
		try {
			// per precondizione tale metodo non dovrebbe generare alcun errore
			scopeArchiType = new ScopeArchiType(archiTypeTransformed, 0);
		} catch (NormalizeException e) {
			e.printStackTrace();
			return;
		}
		NormalizeRew normalizeRew = new NormalizeRew(scopeArchiType, 0);
		RewSpec rewSpecNormalized = null;
		try {
			rewSpecNormalized = normalizeRew.normalizeRew(getRewSpec(rewFilePath));
		} catch (NormalizeException e) {
			e.printStackTrace();
			return;
		}
		List<MeasureDef> measureDefs = rewSpecNormalized.getMeasureDefs();
		// per ogni definizione di misura
		for (MeasureDef measureDef : measureDefs) {
			ArrayList<ArchiElemInstance> archiElemInstances = new ArrayList<ArchiElemInstance>();
			ArrayList<metamodel.mmaemilia.Behavior.Action> actions = new ArrayList<metamodel.mmaemilia.Behavior.Action>();
			ArrayList<metamodel.mmaemilia.Behavior.Action> actionsUnique = new ArrayList<metamodel.mmaemilia.Behavior.Action>();
			RewardStructure rewardStructure = measureDef.getRewardStructure();
			List<RewardAssign> rewardAssigns = rewardStructure.getRewardAssigns();
			// per ogni assign reward:
			for (RewardAssign rewardAssign : rewardAssigns) {
				String aei = rewardAssign.getAei();
				String actionType = rewardAssign.getActionType();
				Expression expression = rewardAssign.getSelector();
				// si trova l'istanza di elemento architetturale aei + expression da archiType
				Find find = new Find();
				ArchiElemInstance archiElemInstance = find.getArchiElemInstance(archiType, aei,
						(specificheAEmilia.Expression) expression);
				// aggiungo all'istanza se none'gia' presente
				if (!archiElemInstances.contains(archiElemInstance))
					archiElemInstances.add(archiElemInstance);
				List<metamodel.mmaemilia.Behavior.Action> actions2 = find.getActions(archiElemInstance, actionType);
				metamodel.mmaemilia.Behavior.Action action = find.getActionUnique(archiElemInstance, actionType);
				// si considerano gli indici delle action
				// le action si aggiungono soltanto se non sono gia' stati aggiunti
				for (metamodel.mmaemilia.Behavior.Action action2 : actions2) {
					if (!actions.contains(action2))
						actions.add(action2);
				}
				if (!actionsUnique.contains(action))
					actionsUnique.add(action);
			}
			aeiMap.put(measureDef, archiElemInstances);
			actionMap.put(measureDef, actions);
			actionMapUnique.put(measureDef, actionsUnique);
		}

		createRewmappingFile(actionMap, aeiMap, measureDefs, rewmappingAsResource, rewMapping);
	}

	public void aemiliaModelUpdate(String aemFilePath, String rewmappingFilePath) {

		// effettuare il parsing del .val
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(aemFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		ValParser valParser = new ValParser(fileInputStream);
		ValSpec valSpec = null;
		try {
			valSpec = valParser.ValSpec();
		} catch (it.disim.univaq.sealab.ttep.val.ParseException e) {
			e.printStackTrace();
			return;
		}
		// caricare il modello relativo a rewmapping

		// ResourceSet resourceSet = AEMILIA_MANAGER.getResourceSet();
		// setResourceSet();

		URI uri = URI.createFileURI(rewmappingFilePath);
		Resource rewmappingResource = getResourceSet().getResource(uri, true);

		RewMapping rewMapping = (RewMapping) rewmappingResource.getContents().get(0);
		// effettuare il mapping con gli elementi aemilia
		List<MeasureValue> measures = valSpec.getMeasures();
		for (MeasureValue measure : measures) {
			String measureName = measure.getMeasure();
			Expression measureSelector = measure.getSelector();
			Float measureValue = measure.getValue();
			List<MeasureMapping> measureMappings = rewMapping.getMappings();
			for (MeasureMapping measureMapping : measureMappings) {
				String measureMappingName = measureMapping.getMeasureName();
				List<AeiMeasure> aeiMeasures = measureMapping.getInstances();
				List<ActionMeasure> actionMeasures = measureMapping.getActions();
				List<ArchiIntMeasure> archiIntMeasures = measureMapping.getArchiInteractions();
				metamodel.mmaemilia.Expressions.Expression measureMappingSelector = measureMapping.getSelector();
				MMAemiliaToArchiType mmAemiliaToArchiType = new MMAemiliaToArchiType();
				Expression expression4 = mmAemiliaToArchiType.getExpression(measureMappingSelector);
				if (measureName.equals(measureMappingName)) {
					if (measureSelector != null) {
						if (expression4 != null) {
							if (measureSelector.equals(expression4)) {
								// aggiorno gli elementi aemilia
								for (AeiMeasure aeiMeasure : aeiMeasures) {
									ArchiElemInstance archiElemInstance = aeiMeasure.getAei();
									IndexType indexType = aeiMeasure.getIndex();
									// ASE HOT Aemilia non supporta il Response time?????
									if (IndexType.RESPONSE_TIME.equals(indexType)) {
										archiElemInstance.setResponseTime(measureValue);
									}
									if (IndexType.THROUGHPUT.equals(indexType) && measureName.contains("throughput")) {
										archiElemInstance.setThroughput(measureValue);
									}
									if (IndexType.UTILIZATION.equals(indexType)
											&& measureName.contains("utilization")) {
										archiElemInstance.setUtilization(measureValue);
									}
									try {
										archiElemInstance.eResource().save(null);
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								for (ActionMeasure actionMeasure : actionMeasures) {
									Action action = actionMeasure.getAction();
									IndexType indexType = actionMeasure.getIndex();
									if (IndexType.RESPONSE_TIME.equals(indexType)) {
										action.setActResponseTime(measureValue);
									}
									if (IndexType.THROUGHPUT.equals(indexType) && measureName.contains("throughput")) {
										action.setActThroughtput(measureValue);
									}
									if (IndexType.UTILIZATION.equals(indexType)
											&& measureName.contains("utilization")) {
										action.setActUtilization(measureValue);
									}
									try {
										action.eResource().save(null);
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								for (ArchiIntMeasure archiIntMeasure : archiIntMeasures) {
									ArchitecturalInteraction architecturalInteraction = archiIntMeasure
											.getArchiInteraction();
									IndexType indexType = archiIntMeasure.getIndex();
									if (IndexType.RESPONSE_TIME.equals(indexType)) {
										architecturalInteraction.setResponseTime(measureValue);
									}
									if (IndexType.THROUGHPUT.equals(indexType) && measureName.contains("throughput")) {
										architecturalInteraction.setThroughput(measureValue);
									}
									if (IndexType.UTILIZATION.equals(indexType)
											&& measureName.contains("utilization")) {
										architecturalInteraction.setUtilization(measureValue);
									}
									try {
										architecturalInteraction.eResource().save(null);
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							}
						}
					} else {
						if (expression4 == null) {
							// aggiorno gli elementi aemilia
							for (AeiMeasure aeiMeasure : aeiMeasures) {
								ArchiElemInstance archiElemInstance = aeiMeasure.getAei();
								IndexType indexType = aeiMeasure.getIndex();
								if (IndexType.RESPONSE_TIME.equals(indexType)) {
									archiElemInstance.setResponseTime(measureValue);
								}
								if (IndexType.THROUGHPUT.equals(indexType) && measureName.contains("throughput")) {
									archiElemInstance.setThroughput(measureValue);
								}
								if (IndexType.UTILIZATION.equals(indexType) && measureName.contains("utilization")) {
									archiElemInstance.setUtilization(measureValue);
								}
								try {
									archiElemInstance.eResource().save(null);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							for (ActionMeasure actionMeasure : actionMeasures) {
								Action action = actionMeasure.getAction();
								EList<ArchitecturalInteraction> archiInteractions = getAemiliaSpecification()
										.getArchiTypeDecl().getAtDeclaration().getAiDecl();
								for (ArchitecturalInteraction interaction : archiInteractions) {
									if (interaction.getIs_A().getIntName().contains(action.getName())) {
										IndexType indexType = actionMeasure.getIndex();
										if (IndexType.RESPONSE_TIME.equals(indexType)) {
											interaction.setResponseTime(measureValue);
										}
										if (IndexType.THROUGHPUT.equals(indexType)
												&& measureName.contains("throughput")) {
											interaction.setThroughput(measureValue);
										}
										if (IndexType.UTILIZATION.equals(indexType)
												&& measureName.contains("utilization")) {
											interaction.setUtilization(measureValue);
										}
									}
								}
								IndexType indexType = actionMeasure.getIndex();
								if (IndexType.RESPONSE_TIME.equals(indexType)) {
									action.setActResponseTime(measureValue);
								}
								if (IndexType.THROUGHPUT.equals(indexType) && measureName.contains("throughput")) {
									action.setActThroughtput(measureValue);
								}
								if (IndexType.UTILIZATION.equals(indexType) && measureName.contains("utilization")) {
									action.setActUtilization(measureValue);
								}
								try {
									action.eResource().save(null);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							for (ArchiIntMeasure archiIntMeasure : archiIntMeasures) {
								ArchitecturalInteraction architecturalInteraction = archiIntMeasure
										.getArchiInteraction();
								IndexType indexType = archiIntMeasure.getIndex();
								if (IndexType.RESPONSE_TIME.equals(indexType)) {
									architecturalInteraction.setResponseTime(measureValue);
								}
								if (IndexType.THROUGHPUT.equals(indexType) && measureName.contains("throughput")) {
									architecturalInteraction.setThroughput(measureValue);
								}
								if (IndexType.UTILIZATION.equals(indexType) && measureName.contains("utilization")) {
									architecturalInteraction.setUtilization(measureValue);
								}
								try {
									architecturalInteraction.eResource().save(null);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
	}

	public RewSpec getRewSpec(String rewFilePath) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(rewFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		/*
		 * inputStream -> InputStream of rew file
		 */
		RewParser rewParser = new RewParser(inputStream);
		RewSpec rewSpec = null;
		try {
			rewSpec = rewParser.RewSpec();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rewSpec;
	}

	public void createRewmappingFile(Map<MeasureDef, List<Action>> measureDefActionListMap,
			Map<MeasureDef, List<ArchiElemInstance>> measureDefArchiElemInstanceListMap,
			List<MeasureDef> measureDefList, Resource rewmappingAsResource, RewMapping rewMapping) {

		// Resource rewmappingAsResource = createRewmappingResource();
		// RewMapping rewMapping =
		// MapmeasurestoindicesFactory.eINSTANCE.createRewMapping();
		List<MeasureMapping> measureMappings = rewMapping.getMappings();

		createSelectedIndices(measureDefList);

		for (MeasureDef measureDef : measureDefList) {

			List<ActionMeasure> actionMeasureList = new ArrayList<ActionMeasure>();
			List<AeiMeasure> aeiMeasureList = new ArrayList<AeiMeasure>();
			List<ArchiIntMeasure> archiInstMeasureList = new ArrayList<ArchiIntMeasure>();

			List<Action> actionList = measureDefActionListMap.get(measureDef);
			List<ArchiElemInstance> archiElemInstances = measureDefArchiElemInstanceListMap.get(measureDef);

			List<ExtractedIndex> selectedIndices = getExtractedIndices();
			String measureName = measureDef.getIdentifier();
			Expression selector = measureDef.getSelector();

			for (ExtractedIndex extractedIndex : selectedIndices) {
				String actionString = extractedIndex.getAction();
				String aeiString = extractedIndex.getAei();
				String aeiName = getAeiName(aeiString);
				String aeiSelectorString = getAeiSelector(aeiString);
				ScanExp scanExp = new ScanExp(0);
				Expression selectorExpression = null;
				if (aeiSelectorString != null) {
					try {
						selectorExpression = scanExp.scanEspressione(aeiSelectorString);
					} catch (ScanException e) {
						e.printStackTrace();
					}
				}
				String extractedIndexString = extractedIndex.getIndex();
				if (actionString != null && !"".equals(actionString)) {
					for (Action action : actionList) {
						Elem elem = action.getElem();
						ArchiElemInstance archiElemInstance = elem.getInstance();
						String instanceName = archiElemInstance.getInstanceName();
						String actionName = action.getName();
						if (instanceName.equals(aeiName) && actionString.equals(actionName)) {
							if (selectorExpression != null) {
								metamodel.mmaemilia.Expressions.Expression expression = archiElemInstance.getSelector();
								MMAemiliaToArchiType mmAemiliaToArchiType = new MMAemiliaToArchiType();
								Expression expression2 = mmAemiliaToArchiType.getExpression(expression);
								if (selectorExpression.equals(expression2)) {
									ActionMeasure actionMeasure = MapmeasurestoindicesFactory.eINSTANCE
											.createActionMeasure();
									actionMeasure.setAction(action);
									if ("ResponseTime".equals(extractedIndexString)) {
										actionMeasure.setIndex(IndexType.RESPONSE_TIME);
									}
									if ("Utilization".equals(extractedIndexString)) {
										actionMeasure.setIndex(IndexType.UTILIZATION);
									}
									if ("Throughput".equals(extractedIndexString)) {
										actionMeasure.setIndex(IndexType.THROUGHPUT);
									}
									actionMeasureList.add(actionMeasure);
								}
							} else {
								ActionMeasure actionMeasure = MapmeasurestoindicesFactory.eINSTANCE
										.createActionMeasure();
								actionMeasure.setAction(action);
								if ("ResponseTime".equals(extractedIndexString)) {
									actionMeasure.setIndex(IndexType.RESPONSE_TIME);
								}
								if ("Utilization".equals(extractedIndexString)) {
									actionMeasure.setIndex(IndexType.UTILIZATION);
								}
								if ("Throughput".equals(extractedIndexString)) {
									actionMeasure.setIndex(IndexType.THROUGHPUT);
								}
								actionMeasureList.add(actionMeasure);
							}
						}
					}
				} else {
					for (ArchiElemInstance archiElemInstance : archiElemInstances) {
						String name = archiElemInstance.getInstanceName();
						if (aeiName.equals(name)) {
							if (selectorExpression != null) {
								metamodel.mmaemilia.Expressions.Expression expression = archiElemInstance.getSelector();
								MMAemiliaToArchiType mmAemiliaToArchiType = new MMAemiliaToArchiType();
								Expression expression2 = mmAemiliaToArchiType.getExpression(expression);
								if (selectorExpression.equals(expression2)) {
									AeiMeasure aeiMeasure = MapmeasurestoindicesFactory.eINSTANCE.createAeiMeasure();
									aeiMeasure.setAei(archiElemInstance);
									if ("ResponseTime".equals(extractedIndexString)) {
										aeiMeasure.setIndex(IndexType.RESPONSE_TIME);
									}
									if ("Utilization".equals(extractedIndexString)) {
										aeiMeasure.setIndex(IndexType.UTILIZATION);
									}
									if ("Throughput".equals(extractedIndexString)) {
										aeiMeasure.setIndex(IndexType.THROUGHPUT);
									}
									aeiMeasureList.add(aeiMeasure);
								}
							} else {
								AeiMeasure aeiMeasure = MapmeasurestoindicesFactory.eINSTANCE.createAeiMeasure();
								aeiMeasure.setAei(archiElemInstance);
								if ("ResponseTime".equals(extractedIndexString)) {
									aeiMeasure.setIndex(IndexType.RESPONSE_TIME);
								}
								if ("Utilization".equals(extractedIndexString)) {
									aeiMeasure.setIndex(IndexType.UTILIZATION);
								}
								if ("Throughput".equals(extractedIndexString)) {
									aeiMeasure.setIndex(IndexType.THROUGHPUT);
								}
								aeiMeasureList.add(aeiMeasure);
							}
						}
					}
				}
			}
			MeasureMapping measureMapping = MapmeasurestoindicesFactory.eINSTANCE.createMeasureMapping();
			measureMapping.setMeasureName(measureName);
			if (selector != null) {
				MMAemiliaSerialize mmAemiliaSerialize = new MMAemiliaSerialize();
				metamodel.mmaemilia.Expressions.Expression expression = mmAemiliaSerialize.getExpressionMM(selector);
				measureMapping.setSelector(expression);
			}
			List<AeiMeasure> aeiMeasures = measureMapping.getInstances();
			List<ActionMeasure> actionMeasures = measureMapping.getActions();
			List<ArchiIntMeasure> archiIntMeasures = measureMapping.getArchiInteractions();
			aeiMeasures.addAll(aeiMeasureList);
			actionMeasures.addAll(actionMeasureList);
			archiIntMeasures.addAll(archiInstMeasureList);
			measureMappings.add(measureMapping);
		}
		rewmappingAsResource.getContents().add(rewMapping);
		try {
			rewmappingAsResource.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getAeiName(String aei) {
		String[] strings = aei.split("[\\[\\]]+");
		String string = strings[0];
		return string;
	}

	private String getAeiSelector(String aei) {
		String[] strings = aei.split("[\\[\\]]+");
		if (strings.length > 1)
			return strings[1];
		else
			return null;
	}

	// public ExtractedIndex createExtractedIndex(String archiElemInstanceName,
	// String action, String indexTypeName) {
	// ExtractedIndex extractedIndex = new ExtractedIndex(archiElemInstanceName,
	// action, indexTypeName);
	// return extractedIndex;
	// }

	/**
	 * 
	 * @param measureDefList
	 *            -> contains the rew's measure definitions
	 * @return
	 */
	public List<ExtractedIndex> createSelectedIndices(List<MeasureDef> measureDefList) {
		for (MeasureDef measureDef : measureDefList) {
			for (RewardAssign ra : measureDef.getRewardStructure().getRewardAssigns()) {
				if (ra.getSelector() != null) { // && aei.getSelector() != null) {
					// if(ra.getSelector().toString().equals(aei.getSelector().toString()))
					ArchiElemInstance aei = aeiMap.get(measureDef)
							.get(Integer.parseInt(ra.getSelector().toString()) - 1);
					for (Action act : actionMap.get(measureDef)) {
						if (act.eCrossReferences().get(0).eContainer().equals((EObject) aei))
							for (IndexType index : IndexType.VALUES) {
								if (measureDef.getIdentifier().toLowerCase().contains(index.getName().toLowerCase()))
									getExtractedIndices().add(new ExtractedIndex(
											aei.getInstanceName() + "[" + ra.getSelector().toString() + "]",
											act.getName(), index.getName()));
							}
					}
				} else {
					ArchiElemInstance aei = aeiMap.get(measureDef).get(0);
					for (Action act : actionMap.get(measureDef)) {
						for (IndexType index : IndexType.VALUES) {
							if (measureDef.getIdentifier().toLowerCase().contains(index.getName().toLowerCase()))
								getExtractedIndices()
										.add(new ExtractedIndex(aei.getInstanceName(), act.getName(), index.getName()));
						}
					}
				}

			}
		}

		return getExtractedIndices();
	}

	public List<ExtractedIndex> getExtractedIndices() {
		return extractedIndices;
	}

	public void setExtractedIndices(List<ExtractedIndex> extractedIndices) {
		this.extractedIndices = extractedIndices;
	}

	public String getTwoTowersKernelPath() {
		return twoTowersKernelPath;
	}

	public void setTwoTowersKernelPath(String twoTowersKernelPath) {
		this.twoTowersKernelPath = twoTowersKernelPath;
	}

	public ResourceSet getResourceSet() {
		return resourceSet;
	}

	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	public AEmiliaSpecification getAemiliaSpecification() {
		return aemiliaSpecification;
	}

	public void setAemiliaSpecification(AEmiliaSpecification aemiliaSpecification) {
		this.aemiliaSpecification = aemiliaSpecification;
	}
}
