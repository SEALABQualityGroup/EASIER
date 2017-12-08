//package it.disim.univaq.sealab.metaheuristic.twoeagles;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.eclipse.emf.common.util.URI;
//import org.eclipse.emf.ecore.EObject;
//import org.eclipse.emf.ecore.resource.Resource;
//import org.eclipse.emf.ecore.resource.ResourceSet;
//
//import aemiliaParser.AEMparser;
//import aemiliametamodel.utilities.Find;
//import equivalenzaComportamentale.secondRelease.Equivalenza2;
//import it.disim.univaq.sealab.metaheuristic.managers.Manager;
//import it.disim.univaq.sealab.metaheuristic.mangers.aemilia.AemiliaManager;
//import it.univaq.from_aemilia_to_qn_plug_in.serialize.MMAemiliaSerialize;
//import it.univaq.from_aemilia_to_qn_plug_in.serialize.MMAemiliaToArchiType;
//import it.univaq.ttep.rew.ParseException;
//import it.univaq.ttep.rew.RewParser;
//import it.univaq.ttep.rew.classes.MeasureDef;
//import it.univaq.ttep.rew.classes.RewSpec;
//import it.univaq.ttep.rew.classes.RewardAssign;
//import it.univaq.ttep.rew.classes.RewardStructure;
//import it.univaq.ttep.rew.wizard.normalize.NormalizeRew;
//import it.univaq.ttep.rew.wizard.providers.ExtractedIndex;
//import it.univaq.ttep.val.ValParser;
//import it.univaq.ttep.val.classes.MeasureValue;
//import it.univaq.ttep.val.classes.ValSpec;
//import mapmeasurestoindices.ActionMeasure;
//import mapmeasurestoindices.AeiMeasure;
//import mapmeasurestoindices.ArchiIntMeasure;
//import mapmeasurestoindices.IndexType;
//import mapmeasurestoindices.MapmeasurestoindicesFactory;
//import mapmeasurestoindices.MeasureMapping;
//import mapmeasurestoindices.RewMapping;
//import metamodel.mmaemilia.AEmiliaSpecification;
//import metamodel.mmaemilia.ArchiElemInstance;
//import metamodel.mmaemilia.ArchitecturalInteraction;
//import metamodel.mmaemilia.Elem;
//import metamodel.mmaemilia.Behavior.Action;
//import restrizioniGenerali.RestrizioniGenException;
//import restrizioniGenerali.secondRelease.GeneraliRules2;
//import restrizioniIstanze.RestrizioniIstanzeException;
//import restrizioniIstanze.qnElemTypes.ElemTypeNorm;
//import restrizioniIstanze.restrizioniIstanze.RestrizioniIstanze;
//import scanSpecAEmilia.ScanException;
//import scanSpecAEmilia.scanExp.ScanExp;
//import specificheAEmilia.ArchiType;
//import specificheAEmilia.Expression;
//import utilities.ErrorMessage;
//import utilities.MetodiVari;
//import valutazione.normalization.NormalizeException;
//import valutazione.scope.ScopeArchiType;
//
//public class TwoEaglesHelper {
//
//	private static AemiliaManager AEMILIA_MANAGER = (AemiliaManager) Manager.getInstance(AemiliaManager.getInstance())
//			.getMetamodelManager();
//
//	private String twoTowersKernelPath = "/Users/daniele/git/sealabtools/panda-aemilia/TwoTowers/bin/TTKernel";
//
//	private final String STATIONARY_REWARD_BASED_MEASURE_CALCULATOR_GAUSSIAN_ELIMINATION = "-s";
//
//	private AEmiliaSpecification aemiliaSpecification;
//
//	private List<ExtractedIndex> extractedIndices = new ArrayList<ExtractedIndex>();
//	private Map<MeasureDef, List<metamodel.mmaemilia.Behavior.Action>> actionMap = new HashMap<MeasureDef, List<metamodel.mmaemilia.Behavior.Action>>();
//	private Map<MeasureDef, List<ArchiElemInstance>> aeiMap = new HashMap<MeasureDef, List<ArchiElemInstance>>();
//	private Map<MeasureDef, List<metamodel.mmaemilia.Behavior.Action>> actionMapUnique = new HashMap<MeasureDef, List<metamodel.mmaemilia.Behavior.Action>>();
//
//	public void aemiliaModelGeneration() {
//
//		FileInputStream aemiliaFileInputStream = null;
//
//		String aemiliaModelFilePath = AEMILIA_MANAGER.getModelPath();
//		String destinationPath = AEMILIA_MANAGER.getRefactoredModelPath();
//
//		try {
//			aemiliaFileInputStream = new FileInputStream(aemiliaModelFilePath);
//		} catch (FileNotFoundException e2) {
//			MetodiVari.stampaSuConsole(e2.getMessage());
//		}
//
//		AEMparser aeMparser = new AEMparser(aemiliaFileInputStream);
//		try {
//			ArchiType archiType = aeMparser.ArchiType();
//			GeneraliRules2 generaliRules = new GeneraliRules2(archiType);
//			boolean b = false;
//			try {
//				b = generaliRules.isCompliantGeneralRules(false);
//			} catch (RestrizioniGenException e1) {
//				MetodiVari.stampaSuConsole(e1.getMessage());
//			}
//			if (b) {
//				List<Equivalenza2> list = generaliRules.getEquivalenze();
//				RestrizioniIstanze restrizioniIstanze = null;
//				try {
//					restrizioniIstanze = new RestrizioniIstanze(list, archiType, false);
//				} catch (RestrizioniIstanzeException e1) {
//					MetodiVari.stampaSuConsole(e1.getMessage());
//				}
//				if (!restrizioniIstanze.isErrorOccurred()) {
//
//					ArchiType archiType2 = restrizioniIstanze.getArchiType();
//					List<ElemTypeNorm> elemTypeNormList = restrizioniIstanze.getElemTypeNormList();
//
//					MMAemiliaSerialize mmAemiliaSerialize = new MMAemiliaSerialize();
//					mmAemiliaSerialize.serialize_ase(archiType2, elemTypeNormList, destinationPath);
//
//				} else {
//					ErrorMessage errorMessage = restrizioniIstanze.getErrorMessage();
//					MetodiVari.stampaSuConsole(errorMessage.toString());
//				}
//			} else {
//				ErrorMessage errorMessage = generaliRules.getErrorMessage();
//				MetodiVari.stampaSuConsole(errorMessage.toString());
//			}
//		} catch (aemiliaParser.ParseException e) {
//			try {
//				MetodiVari.stampaSuConsole("Scanning error: " + MetodiVari.fromStream(aemiliaFileInputStream)
//						+ "is not a valid architectural type" + "\n");
//			} catch (IOException e1) {
//				MetodiVari.stampaSuConsole(e1.getMessage());
//			}
//			MetodiVari.stampaSuConsole(e.getMessage());
//		} catch (IOException e) {
//			MetodiVari.stampaSuConsole(e.getMessage());
//		}
//	}
//
//	@SuppressWarnings("unused")
//	public void gaussianEliminationSRBMC() {
//
//		// String aemFilePath = getBasePath() + getAemFile();
//		// String rewFilePath = getBasePath() + getRewFile();
//		String aemFilePath = AEMILIA_MANAGER.getAemFile();
//		String rewFilePath = AEMILIA_MANAGER.getRewFile();
//
//		try {
//			// object.execute_ase(twoTowersKernelPath, aemFilePath, rewFilePath,
//			// destinationFilePath);
//			Process process = new ProcessBuilder(getTwoTowersKernelPath(),
//					STATIONARY_REWARD_BASED_MEASURE_CALCULATOR_GAUSSIAN_ELIMINATION, aemFilePath, rewFilePath,
//					AEMILIA_MANAGER.getOutputFile()).start();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void measuresToIndices() {
//		String rewFilePath = AEMILIA_MANAGER.getRewFile();
//
//		// ASE support data structures
//
//		// aemiliaSpecification = (AEmiliaSpecification)
//		// aemiliaResource.getContents().get(0);
//		// aemiliaSpecification = (AEmiliaSpecification) ((AemiliaManager) Manager
//		// .getInstance(AemiliaManager.getInstance()).getMetamodelManager()).getResource().getContents().get(0);
//		aemiliaSpecification = (AEmiliaSpecification) AEMILIA_MANAGER.getResource().getContents().get(0);
//
//		// si effettua la normalizzazione di rewSpec come fatto con le istanze di
//		// elementi architetturali, senza concatenazione del selettore pero'
//		// si serializza in ArchiType il metamodello AEmilia
//		metamodel.mmaemilia.ArchiType archiType = aemiliaSpecification.getArchiTypeDecl();
//		MMAemiliaToArchiType mmAemiliaToArchiType = new MMAemiliaToArchiType();
//		ArchiType archiTypeTransformed = null;
//		try {
//			archiTypeTransformed = mmAemiliaToArchiType.transform(archiType);
//		} catch (Exception e1) {
//			MetodiVari.stampaSuConsole(e1.getMessage());
//			return;
//		}
//		// si normalizzano le misure secondo lo scope archiType
//		ScopeArchiType scopeArchiType = null;
//		try {
//			// per precondizione tale metodo non dovrebbe generare alcun errore
//			scopeArchiType = new ScopeArchiType(archiTypeTransformed, 0);
//		} catch (NormalizeException e1) {
//			MetodiVari.stampaSuConsole(e1.getMessage());
//			return;
//		}
//		NormalizeRew normalizeRew = new NormalizeRew(scopeArchiType, 0);
//		RewSpec rewSpecNormalized = null;
//		try {
//			rewSpecNormalized = normalizeRew.normalizeRew(getRewSpec(rewFilePath));
//		} catch (NormalizeException e1) {
//			MetodiVari.stampaSuConsole(e1.getMessage());
//			return;
//		}
//		List<MeasureDef> measureDefs = rewSpecNormalized.getMeasureDefs();
//		// per ogni definizione di misura
//		for (MeasureDef measureDef : measureDefs) {
//			ArrayList<ArchiElemInstance> archiElemInstances = new ArrayList<ArchiElemInstance>();
//			ArrayList<metamodel.mmaemilia.Behavior.Action> actions = new ArrayList<metamodel.mmaemilia.Behavior.Action>();
//			ArrayList<metamodel.mmaemilia.Behavior.Action> actionsUnique = new ArrayList<metamodel.mmaemilia.Behavior.Action>();
//			RewardStructure rewardStructure = measureDef.getRewardStructure();
//			List<RewardAssign> rewardAssigns = rewardStructure.getRewardAssigns();
//			// per ogni assign reward:
//			for (RewardAssign rewardAssign : rewardAssigns) {
//				String aei = rewardAssign.getAei();
//				String actionType = rewardAssign.getActionType();
//				Expression expression = rewardAssign.getSelector();
//				// si trova l'istanza di elemento architetturale aei + expression da archiType
//				Find find = new Find();
//				ArchiElemInstance archiElemInstance = find.getArchiElemInstance(archiType, aei, expression);
//				// aggiungo all'istanza se none'gia' presente
//				if (!archiElemInstances.contains(archiElemInstance))
//					archiElemInstances.add(archiElemInstance);
//				List<metamodel.mmaemilia.Behavior.Action> actions2 = find.getActions(archiElemInstance, actionType);
//				metamodel.mmaemilia.Behavior.Action action = find.getActionUnique(archiElemInstance, actionType);
//				// si considerano gli indici delle action
//				// le action si aggiungono soltanto se non sono gia' stati aggiunti
//				for (metamodel.mmaemilia.Behavior.Action action2 : actions2) {
//					if (!actions.contains(action2))
//						actions.add(action2);
//				}
//				if (!actionsUnique.contains(action))
//					actionsUnique.add(action);
//			}
//			aeiMap.put(measureDef, archiElemInstances);
//			actionMap.put(measureDef, actions);
//			actionMapUnique.put(measureDef, actionsUnique);
//		}
//
//		createRewmappingFile(actionMap, aeiMap, measureDefs);
//	}
//
//	public void aemiliaModelUpdate() {
//
//		String valFileStringPath = AEMILIA_MANAGER.getAemFile();
//		String rewmappingFileStringPath = AEMILIA_MANAGER.getRewmappingFile();
//
//		// effettuare il parsing del .val
//		FileInputStream fileInputStream = null;
//		try {
//			fileInputStream = new FileInputStream(valFileStringPath);
//		} catch (FileNotFoundException e) {
//			MetodiVari.stampaSuConsole(e.getMessage());
//			return;
//		}
//		ValParser valParser = new ValParser(fileInputStream);
//		ValSpec valSpec = null;
//		try {
//			valSpec = valParser.ValSpec();
//		} catch (it.univaq.ttep.val.ParseException e) {
//			MetodiVari.stampaSuConsole(e.getMessage());
//			return;
//		}
//		// caricare il modello relativo a rewmapping
//
//		ResourceSet resourceSet = AEMILIA_MANAGER.getResourceSet();
//
//		URI uri = URI.createFileURI(rewmappingFileStringPath);
//		Resource rewmappingResource = resourceSet.getResource(uri, true);
//
//		RewMapping rewMapping = (RewMapping) rewmappingResource.getContents().get(0);
//		// effettuare il mapping con gli elementi aemilia
//		List<MeasureValue> measures = valSpec.getMeasures();
//		for (MeasureValue measure : measures) {
//			String measureName = measure.getMeasure();
//			Expression measureSelector = measure.getSelector();
//			Float measureValue = measure.getValue();
//			List<MeasureMapping> measureMappings = rewMapping.getMappings();
//			for (MeasureMapping measureMapping : measureMappings) {
//				String measureMappingName = measureMapping.getMeasureName();
//				List<AeiMeasure> aeiMeasures = measureMapping.getInstances();
//				List<ActionMeasure> actionMeasures = measureMapping.getActions();
//				List<ArchiIntMeasure> archiIntMeasures = measureMapping.getArchiInteractions();
//				metamodel.mmaemilia.Expressions.Expression measureMappingSelector = measureMapping.getSelector();
//				MMAemiliaToArchiType mmAemiliaToArchiType = new MMAemiliaToArchiType();
//				Expression expression4 = mmAemiliaToArchiType.getExpression(measureMappingSelector);
//				if (measureName.equals(measureMappingName)) {
//					if (measureSelector != null) {
//						if (expression4 != null) {
//							if (measureSelector.equals(expression4)) {
//								// aggiorno gli elementi aemilia
//								for (AeiMeasure aeiMeasure : aeiMeasures) {
//									ArchiElemInstance archiElemInstance = aeiMeasure.getAei();
//									IndexType indexType = aeiMeasure.getIndex();
//									// ASE HOT Aemilia non supporta il Response time?????
//									if (IndexType.RESPONSE_TIME.equals(indexType)) {
//										archiElemInstance.setResponseTime(measureValue);
//									}
//									if (IndexType.THROUGHPUT.equals(indexType)) {
//										archiElemInstance.setThroughput(measureValue);
//									}
//									if (IndexType.UTILIZATION.equals(indexType)) {
//										archiElemInstance.setUtilization(measureValue);
//									}
//									try {
//										archiElemInstance.eResource().save(null);
//									} catch (IOException e) {
//										MetodiVari.stampaSuConsole(e.getMessage());
//									}
//								}
//								for (ActionMeasure actionMeasure : actionMeasures) {
//									Action action = actionMeasure.getAction();
//									IndexType indexType = actionMeasure.getIndex();
//									if (IndexType.RESPONSE_TIME.equals(indexType)) {
//										action.setActResponseTime(measureValue);
//									}
//									if (IndexType.THROUGHPUT.equals(indexType)) {
//										action.setActThroughtput(measureValue);
//									}
//									if (IndexType.UTILIZATION.equals(indexType)) {
//										action.setActUtilization(measureValue);
//									}
//									try {
//										action.eResource().save(null);
//									} catch (IOException e) {
//										MetodiVari.stampaSuConsole(e.getMessage());
//									}
//								}
//								for (ArchiIntMeasure archiIntMeasure : archiIntMeasures) {
//									ArchitecturalInteraction architecturalInteraction = archiIntMeasure
//											.getArchiInteraction();
//									IndexType indexType = archiIntMeasure.getIndex();
//									if (IndexType.RESPONSE_TIME.equals(indexType)) {
//										architecturalInteraction.setResponseTime(measureValue);
//									}
//									if (IndexType.THROUGHPUT.equals(indexType)) {
//										architecturalInteraction.setThroughput(measureValue);
//									}
//									if (IndexType.UTILIZATION.equals(indexType)) {
//										architecturalInteraction.setUtilization(measureValue);
//									}
//									try {
//										architecturalInteraction.eResource().save(null);
//									} catch (IOException e) {
//										MetodiVari.stampaSuConsole(e.getMessage());
//									}
//								}
//							}
//						}
//					} else {
//						if (expression4 == null) {
//							// aggiorno gli elementi aemilia
//							for (AeiMeasure aeiMeasure : aeiMeasures) {
//								ArchiElemInstance archiElemInstance = aeiMeasure.getAei();
//								IndexType indexType = aeiMeasure.getIndex();
//								if (IndexType.RESPONSE_TIME.equals(indexType)) {
//									archiElemInstance.setResponseTime(measureValue);
//								}
//								if (IndexType.THROUGHPUT.equals(indexType)) {
//									archiElemInstance.setThroughput(measureValue);
//								}
//								if (IndexType.UTILIZATION.equals(indexType)) {
//									archiElemInstance.setUtilization(measureValue);
//								}
//								try {
//									archiElemInstance.eResource().save(null);
//								} catch (IOException e) {
//									MetodiVari.stampaSuConsole(e.getMessage());
//								}
//							}
//							for (ActionMeasure actionMeasure : actionMeasures) {
//								Action action = actionMeasure.getAction();
//								IndexType indexType = actionMeasure.getIndex();
//								if (IndexType.RESPONSE_TIME.equals(indexType)) {
//									action.setActResponseTime(measureValue);
//								}
//								if (IndexType.THROUGHPUT.equals(indexType)) {
//									action.setActThroughtput(measureValue);
//								}
//								if (IndexType.UTILIZATION.equals(indexType)) {
//									action.setActUtilization(measureValue);
//								}
//								try {
//									action.eResource().save(null);
//								} catch (IOException e) {
//									MetodiVari.stampaSuConsole(e.getMessage());
//								}
//							}
//							for (ArchiIntMeasure archiIntMeasure : archiIntMeasures) {
//								ArchitecturalInteraction architecturalInteraction = archiIntMeasure
//										.getArchiInteraction();
//								IndexType indexType = archiIntMeasure.getIndex();
//								if (IndexType.RESPONSE_TIME.equals(indexType)) {
//									architecturalInteraction.setResponseTime(measureValue);
//								}
//								if (IndexType.THROUGHPUT.equals(indexType)) {
//									architecturalInteraction.setThroughput(measureValue);
//								}
//								if (IndexType.UTILIZATION.equals(indexType)) {
//									architecturalInteraction.setUtilization(measureValue);
//								}
//								try {
//									architecturalInteraction.eResource().save(null);
//								} catch (IOException e) {
//									MetodiVari.stampaSuConsole(e.getMessage());
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//	}
//
//	public RewSpec getRewSpec(String rewFilePath) {
//		InputStream inputStream = null;
//		try {
//			inputStream = new FileInputStream(rewFilePath);
//		} catch (FileNotFoundException e1) {
//			MetodiVari.stampaSuConsole(e1.getMessage());
//		}
//		/*
//		 * inputStream -> InputStream of rew file
//		 */
//		RewParser rewParser = new RewParser(inputStream);
//		RewSpec rewSpec = null;
//		try {
//			rewSpec = rewParser.RewSpec();
//		} catch (ParseException e1) {
//			MetodiVari.stampaSuConsole(e1.getMessage());
//		}
//		return rewSpec;
//	}
//
//	public void createRewmappingFile(Map<MeasureDef, List<Action>> measureDefActionListMap,
//			Map<MeasureDef, List<ArchiElemInstance>> measureDefArchiElemInstanceListMap,
//			List<MeasureDef> measureDefList) {
//
//		Resource rewmappingAsResource = AEMILIA_MANAGER.createRewmappingResource();
//		RewMapping rewMapping = MapmeasurestoindicesFactory.eINSTANCE.createRewMapping();
//		List<MeasureMapping> measureMappings = rewMapping.getMappings();
//
//		createSelectedIndices(measureDefList);
//
//		for (MeasureDef measureDef : measureDefList) {
//
//			List<ActionMeasure> actionMeasureList = new ArrayList<ActionMeasure>();
//			List<AeiMeasure> aeiMeasureList = new ArrayList<AeiMeasure>();
//			List<ArchiIntMeasure> archiInstMeasureList = new ArrayList<ArchiIntMeasure>();
//
//			List<Action> actionList = measureDefActionListMap.get(measureDef);
//			List<ArchiElemInstance> archiElemInstances = measureDefArchiElemInstanceListMap.get(measureDef);
//
//			List<ExtractedIndex> selectedIndices = getExtractedIndices();
//			String measureName = measureDef.getIdentifier();
//			Expression selector = measureDef.getSelector();
//
//			for (ExtractedIndex extractedIndex : selectedIndices) {
//				String actionString = extractedIndex.getAction();
//				String aeiString = extractedIndex.getAei();
//				String aeiName = getAeiName(aeiString);
//				String aeiSelectorString = getAeiSelector(aeiString);
//				ScanExp scanExp = new ScanExp(0);
//				Expression selectorExpression = null;
//				if (aeiSelectorString != null) {
//					try {
//						selectorExpression = scanExp.scanEspressione(aeiSelectorString);
//					} catch (ScanException e) {
//						MetodiVari.stampaSuConsole(e.getMessage());
//					}
//				}
//				String extractedIndexString = extractedIndex.getIndex();
//				if (actionString != null && !"".equals(actionString)) {
//					for (Action action : actionList) {
//						Elem elem = action.getElem();
//						ArchiElemInstance archiElemInstance = elem.getInstance();
//						String instanceName = archiElemInstance.getInstanceName();
//						String actionName = action.getName();
//						if (instanceName.equals(aeiName) && actionString.equals(actionName)) {
//							if (selectorExpression != null) {
//								metamodel.mmaemilia.Expressions.Expression expression = archiElemInstance.getSelector();
//								MMAemiliaToArchiType mmAemiliaToArchiType = new MMAemiliaToArchiType();
//								Expression expression2 = mmAemiliaToArchiType.getExpression(expression);
//								if (selectorExpression.equals(expression2)) {
//									ActionMeasure actionMeasure = MapmeasurestoindicesFactory.eINSTANCE
//											.createActionMeasure();
//									actionMeasure.setAction(action);
//									if ("ResponseTime".equals(extractedIndexString)) {
//										actionMeasure.setIndex(IndexType.RESPONSE_TIME);
//									}
//									if ("Utilization".equals(extractedIndexString)) {
//										actionMeasure.setIndex(IndexType.UTILIZATION);
//									}
//									if ("Throughput".equals(extractedIndexString)) {
//										actionMeasure.setIndex(IndexType.THROUGHPUT);
//									}
//									actionMeasureList.add(actionMeasure);
//								}
//							} else {
//								ActionMeasure actionMeasure = MapmeasurestoindicesFactory.eINSTANCE
//										.createActionMeasure();
//								actionMeasure.setAction(action);
//								if ("ResponseTime".equals(extractedIndexString)) {
//									actionMeasure.setIndex(IndexType.RESPONSE_TIME);
//								}
//								if ("Utilization".equals(extractedIndexString)) {
//									actionMeasure.setIndex(IndexType.UTILIZATION);
//								}
//								if ("Throughput".equals(extractedIndexString)) {
//									actionMeasure.setIndex(IndexType.THROUGHPUT);
//								}
//								actionMeasureList.add(actionMeasure);
//							}
//						}
//					}
//				} else {
//					for (ArchiElemInstance archiElemInstance : archiElemInstances) {
//						String name = archiElemInstance.getInstanceName();
//						if (aeiName.equals(name)) {
//							if (selectorExpression != null) {
//								metamodel.mmaemilia.Expressions.Expression expression = archiElemInstance.getSelector();
//								MMAemiliaToArchiType mmAemiliaToArchiType = new MMAemiliaToArchiType();
//								Expression expression2 = mmAemiliaToArchiType.getExpression(expression);
//								if (selectorExpression.equals(expression2)) {
//									AeiMeasure aeiMeasure = MapmeasurestoindicesFactory.eINSTANCE.createAeiMeasure();
//									aeiMeasure.setAei(archiElemInstance);
//									if ("ResponseTime".equals(extractedIndexString)) {
//										aeiMeasure.setIndex(IndexType.RESPONSE_TIME);
//									}
//									if ("Utilization".equals(extractedIndexString)) {
//										aeiMeasure.setIndex(IndexType.UTILIZATION);
//									}
//									if ("Throughput".equals(extractedIndexString)) {
//										aeiMeasure.setIndex(IndexType.THROUGHPUT);
//									}
//									aeiMeasureList.add(aeiMeasure);
//								}
//							} else {
//								AeiMeasure aeiMeasure = MapmeasurestoindicesFactory.eINSTANCE.createAeiMeasure();
//								aeiMeasure.setAei(archiElemInstance);
//								if ("ResponseTime".equals(extractedIndexString)) {
//									aeiMeasure.setIndex(IndexType.RESPONSE_TIME);
//								}
//								if ("Utilization".equals(extractedIndexString)) {
//									aeiMeasure.setIndex(IndexType.UTILIZATION);
//								}
//								if ("Throughput".equals(extractedIndexString)) {
//									aeiMeasure.setIndex(IndexType.THROUGHPUT);
//								}
//								aeiMeasureList.add(aeiMeasure);
//							}
//						}
//					}
//				}
//			}
//			MeasureMapping measureMapping = MapmeasurestoindicesFactory.eINSTANCE.createMeasureMapping();
//			measureMapping.setMeasureName(measureName);
//			if (selector != null) {
//				MMAemiliaSerialize mmAemiliaSerialize = new MMAemiliaSerialize();
//				metamodel.mmaemilia.Expressions.Expression expression = mmAemiliaSerialize.getExpressionMM(selector);
//				measureMapping.setSelector(expression);
//			}
//			List<AeiMeasure> aeiMeasures = measureMapping.getInstances();
//			List<ActionMeasure> actionMeasures = measureMapping.getActions();
//			List<ArchiIntMeasure> archiIntMeasures = measureMapping.getArchiInteractions();
//			aeiMeasures.addAll(aeiMeasureList);
//			actionMeasures.addAll(actionMeasureList);
//			archiIntMeasures.addAll(archiInstMeasureList);
//			measureMappings.add(measureMapping);
//		}
//		rewmappingAsResource.getContents().add(rewMapping);
//		try {
//			rewmappingAsResource.save(null);
//		} catch (IOException e) {
//			MetodiVari.stampaSuConsole(e.getMessage());
//		}
//	}
//
//	private String getAeiName(String aei) {
//		String[] strings = aei.split("[\\[\\]]+");
//		String string = strings[0];
//		return string;
//	}
//
//	private String getAeiSelector(String aei) {
//		String[] strings = aei.split("[\\[\\]]+");
//		if (strings.length > 1)
//			return strings[1];
//		else
//			return null;
//	}
//
//	// public ExtractedIndex createExtractedIndex(String archiElemInstanceName,
//	// String action, String indexTypeName) {
//	// ExtractedIndex extractedIndex = new ExtractedIndex(archiElemInstanceName,
//	// action, indexTypeName);
//	// return extractedIndex;
//	// }
//
//	/**
//	 * 
//	 * @param measureDefList
//	 *            -> contains the rew's measure definitions
//	 * @return
//	 */
//	public List<ExtractedIndex> createSelectedIndices(List<MeasureDef> measureDefList) {
//		for (MeasureDef measureDef : measureDefList) {
//			for (RewardAssign ra : measureDef.getRewardStructure().getRewardAssigns()) {
//				if (ra.getSelector() != null) { // && aei.getSelector() != null) {
//					// if(ra.getSelector().toString().equals(aei.getSelector().toString()))
//					ArchiElemInstance aei = aeiMap.get(measureDef)
//							.get(Integer.parseInt(ra.getSelector().toString()) - 1);
//					for (Action act : actionMap.get(measureDef)) {
//						if (act.eCrossReferences().get(0).eContainer().equals((EObject) aei))
//							for (IndexType index : IndexType.VALUES) {
//								if (measureDef.getIdentifier().toLowerCase().contains(index.getName().toLowerCase()))
//									getExtractedIndices().add(new ExtractedIndex(
//											aei.getInstanceName() + "[" + ra.getSelector().toString() + "]",
//											act.getName(), index.getName()));
//							}
//					}
//				} else {
//					ArchiElemInstance aei = aeiMap.get(measureDef).get(0);
//					for (Action act : actionMap.get(measureDef)) {
//						for (IndexType index : IndexType.VALUES) {
//							if (measureDef.getIdentifier().toLowerCase().contains(index.getName().toLowerCase()))
//								getExtractedIndices()
//										.add(new ExtractedIndex(aei.getInstanceName(), act.getName(), index.getName()));
//						}
//					}
//				}
//
//			}
//		}
//
//		return getExtractedIndices();
//	}
//
//	public List<ExtractedIndex> getExtractedIndices() {
//		return extractedIndices;
//	}
//
//	public void setExtractedIndices(List<ExtractedIndex> extractedIndices) {
//		this.extractedIndices = extractedIndices;
//	}
//
//	public String getTwoTowersKernelPath() {
//		return twoTowersKernelPath;
//	}
//
//	public void setTwoTowersKernelPath(String twoTowersKernelPath) {
//		this.twoTowersKernelPath = twoTowersKernelPath;
//	}
//
//}
