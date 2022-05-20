package it.univaq.disim.sealab.metaheuristic.evolutionary.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.univaq.disim.sealab.metaheuristic.evolutionary.experiment.RExperimentAlgorithm;
import it.univaq.disim.sealab.metaheuristic.evolutionary.nsgaii.CustomNSGAIIBuilder;
import it.univaq.disim.sealab.metaheuristic.evolutionary.operator.RMutation;
import it.univaq.disim.sealab.metaheuristic.evolutionary.pesaii.CustomPESA2Builder;
import it.univaq.disim.sealab.metaheuristic.evolutionary.rnsgaii.CustomRNSGAIIBuilder;
import it.univaq.disim.sealab.metaheuristic.evolutionary.spea2.CustomSPEA2Builder;
import it.univaq.disim.sealab.metaheuristic.utils.Configurator;
import org.uma.jmetal.algorithm.impl.AbstractGeneticAlgorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAII;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.pesa2.PESA2;
import org.uma.jmetal.algorithm.multiobjective.pesa2.PESA2Builder;
import org.uma.jmetal.algorithm.multiobjective.rnsgaii.RNSGAII;
import org.uma.jmetal.algorithm.multiobjective.rnsgaii.RNSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2;
import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2Builder;
import org.uma.jmetal.lab.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.lab.experiment.util.ExperimentProblem;
import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.selection.SelectionOperator;
import org.uma.jmetal.operator.selection.impl.BinaryTournamentSelection;
import org.uma.jmetal.qualityindicator.impl.Epsilon;
import org.uma.jmetal.qualityindicator.impl.GeneralizedSpread;
import org.uma.jmetal.qualityindicator.impl.GenericIndicator;
import org.uma.jmetal.qualityindicator.impl.InvertedGenerationalDistance;
import org.uma.jmetal.qualityindicator.impl.InvertedGenerationalDistancePlus;
import org.uma.jmetal.qualityindicator.impl.Spread;
import org.uma.jmetal.qualityindicator.impl.hypervolume.impl.PISAHypervolume;

import it.univaq.disim.sealab.metaheuristic.evolutionary.RSolution;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;

public class FactoryBuilder<S extends RSolution<?>> {

    private final HashMap<String, GenericIndicator<S>> qualityIndicatorsMap = new HashMap<>() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        {
//		put("SPREAD", Spread.class);
//		put("IGD+", InvertedGenerationalDistancePlus.class);
//		put("IGD", InvertedGenerationalDistance.class);
//		put("EPSILON", Epsilon.class);
//		put("HYPER_VOLUME", PISAHypervolume.class);
//		put("GENERALIZED_SPREAD", GeneralizedSpread.class);
            put("SPREAD", new Spread<>());
            put("IGD+", new InvertedGenerationalDistancePlus<>());
            put("IGD", new InvertedGenerationalDistance<>());
            put("EPSILON", new Epsilon<>());
            put("HYPER_VOLUME", new PISAHypervolume<>());
            put("GENERALIZED_SPREAD", new GeneralizedSpread<>());
        }
    };

    public FactoryBuilder() {
    }

    /**
     * Returns the quality indicator specified by qI
     * Supported quality indicator
     * SPREAD,IGD+,IGD,EPSILON,HYPER_VOLUME,GENERALIZED_SPREAD
     *
     * The HYPER_VOLUME qI return the PISAHypervolume indicator
     *
     * @param qI
     * @return
     */
    public GenericIndicator<S> createQualityIndicators(String qI) {

        return qualityIndicatorsMap.get(qI);


//		try {
//			try {
//				return (GenericIndicator<S>) qualityIndicatorsMap.get(qI).getDeclaredConstructor().newInstance();
//			} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException
//					| SecurityException e) {
//				System.err.println("newInstance on QualityIndicator fails. ");
//				e.printStackTrace();
//			}
//		} catch (InstantiationException | IllegalAccessException e) {
//			System.out.println("[ERROR]");
//			e.printStackTrace();
//		}
//
//		System.out.println("[ERROR] no available quality indicators builder");
//		return null;
    }

    /**
     * Generate one ExperimentAlgorithm foreach independent run.
     *
     * @param experimentProblem
     * @param eval
     * @param crossoverOperator
     * @param solutionListEvaluator
     * @param algo
     * @return List of ExperimentAlgorithm of length Configuration.eINSTANCE.getIndependetRuns()
     */
    public List<ExperimentAlgorithm<S, List<S>>> configureAlgorithmList(
            ExperimentProblem<S> experimentProblem, int eval,
            CrossoverOperator<S> crossoverOperator, SolutionListEvaluator<S> solutionListEvaluator, MutationOperator<S> mutationOperator,
            String algo) {

        List<ExperimentAlgorithm<S, List<S>>> algorithms = new ArrayList<>();
//        final CrossoverOperator<S> crossoverOperator = new UMLRCrossover<>(Configurator.eINSTANCE.getXoverProbabiliy());
//        final MutationOperator<S> mutationOperator = new RMutation<>(
//                Configurator.eINSTANCE.getMutationProbability(), Configurator.eINSTANCE.getDistributionIndex());
        final SelectionOperator<List<S>, S> selectionOperator = new BinaryTournamentSelection<S>(
                new RankingAndCrowdingDistanceComparator<S>());
//        final SolutionListEvaluator<S> solutionListEvaluator = new SListEvaluator<>();

//        String algo = Configurator.eINSTANCE.getAlgorithm();
//        for (int i = 0; i < problemList.size(); i++) {
        AbstractGeneticAlgorithm<S, List<S>> algorithm = null;
        for (int j = 0; j < Configurator.eINSTANCE.getIndependetRuns(); j++) {


            if ("nsgaii".equals(algo)) {
//            for (int j = 0; j < Configurator.eINSTANCE.getIndependetRuns(); j++) {

                NSGAIIBuilder<S> customNSGABuilder = new CustomNSGAIIBuilder<S>(
                        experimentProblem.getProblem(), crossoverOperator, mutationOperator,
                        Configurator.eINSTANCE.getPopulationSize())
                        .setMaxEvaluations(eval * Configurator.eINSTANCE.getPopulationSize())
                        .setSolutionListEvaluator(solutionListEvaluator);

//                NSGAII<S> algorithm = customNSGABuilder.build();
                algorithm = customNSGABuilder.build();

//                ExperimentAlgorithm<S, List<S>> exp = new RExperimentAlgorithm<S, List<S>>(
//                        algorithm, algorithm.getName(), problemList, j);
//
//                algorithms.add(exp);
//            }
            } else if ("spea2".equals(algo)) {

//            for (int j = 0; j < Configurator.eINSTANCE.getIndependetRuns(); j++) {
                SPEA2Builder<S> spea2Builder = new CustomSPEA2Builder<S>(
                        experimentProblem.getProblem(), crossoverOperator, mutationOperator)
                        .setSelectionOperator(selectionOperator)
                        .setSolutionListEvaluator(solutionListEvaluator).setMaxIterations(eval)
                        .setPopulationSize(Configurator.eINSTANCE.getPopulationSize());

//                SPEA2<S> algorithm = spea2Builder.build();
                algorithm = spea2Builder.build();

//                ExperimentAlgorithm<S, List<S>> exp = new RExperimentAlgorithm<S, List<S>>(
//                        algorithm, algorithm.getName(), problemList, j);
//                algorithms.add(exp);
//            }

            } else if ("rnsga".equals(algo)) {

                if (Configurator.eINSTANCE.getReferencePoints().size() % Configurator.eINSTANCE.getObjectives() == 0) {

//                for (int j = 0; j < Configurator.eINSTANCE.getIndependetRuns(); j++) {
                    RNSGAIIBuilder<S> rnsgaBuilder = new CustomRNSGAIIBuilder<S>(
                            experimentProblem.getProblem(), crossoverOperator, mutationOperator,
                            Configurator.eINSTANCE.getReferencePoints(), Configurator.eINSTANCE.getEpsilon())
                            .setPopulationSize(Configurator.eINSTANCE.getPopulationSize())
                            .setMatingPoolSize(Configurator.eINSTANCE.getPopulationSize())
                            .setOffspringPopulationSize(Configurator.eINSTANCE.getPopulationSize())
                            .setSolutionListEvaluator(solutionListEvaluator)
                            .setMaxEvaluations(eval * Configurator.eINSTANCE.getPopulationSize());

//                    RNSGAII<S> algorithm = rnsgaBuilder.build();
                    algorithm = rnsgaBuilder.build();

//                    ExperimentAlgorithm<S, List<S>> exp = new RExperimentAlgorithm<S, List<S>>(
//                            algorithm, algorithm.getName(), problemList, j);
//                    algorithms.add(exp);
//                }
                } else {
                    throw new RuntimeException("Reference points must be multiple of the number of objectives!!!");

                }

            } else if ("pesa2".equals(algo)) {
                // as reported at
                // https://github.com/jMetal/jMetal/blob/master/jmetal-algorithm/src/main/java/org/uma/jmetal/algorithm/multiobjective/pesa2/PESA2Builder.java
                // we set biSection to 5, and populationSize = archiveSize
                int biSections = 5;
//            for (int j = 0; j < Configurator.eINSTANCE.getIndependetRuns(); j++) {
                PESA2Builder<S> pesaBuilder = new CustomPESA2Builder<S>(
                        experimentProblem.getProblem(), crossoverOperator, mutationOperator)
                        .setPopulationSize(Configurator.eINSTANCE.getPopulationSize())
                        .setArchiveSize(Configurator.eINSTANCE.getPopulationSize())
                        .setBisections(biSections)
                        .setMaxEvaluations(eval * Configurator.eINSTANCE.getPopulationSize())
                        .setSolutionListEvaluator(solutionListEvaluator);
//                PESA2<S> algorithm = pesaBuilder.build();
                algorithm = pesaBuilder.build();
//                ExperimentAlgorithm<S, List<S>> exp = new RExperimentAlgorithm<S, List<S>>(
//                        algorithm, algorithm.getName(), problemList.get(i), j);
//                algorithms.add(exp);

//            }
            }
            if (algorithm == null) {
                throw new NullPointerException("Algorithm must be not null.");
            }

            ExperimentAlgorithm<S, List<S>> exp = new RExperimentAlgorithm<S, List<S>>(
                    algorithm, algorithm.getName(), experimentProblem, j);
            algorithms.add(exp);

//        }


//        }
        }
        return algorithms;
    }
}
