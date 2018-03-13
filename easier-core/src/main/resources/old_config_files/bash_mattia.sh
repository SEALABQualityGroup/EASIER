#!/bin/bash
# declare an array variable
declare -a tries=( 1 2 3 4 5 )
declare -a popul=( 10 20 50 100 200 )
declare -a evals=( 50 100 200 500 1000 10000 )
declare -a cross=( 0.2 0.4 0.6 0.8 )
declare -a mutat=( 0.1 0.2 0.4 0.6 )
for p in "${popul[@]}"
do
for e in "${evals[@]}"
do
for m in "${mutat[@]}"
do
for c in "${cross[@]}"
do
for i in "${tries[@]}"
do
echo "# Properties
length=4
model_path=/home/mdemidio/workspace/pakimor/metaheuristic/src/main/resources/models/AemiliaModels/FTA/FTA
p_crossover=$c
p_mutation=$m
d_index_mutation=20
maxEvaluations=$e
populationSize=$p
number_of_actions=4
allowed_failures=1000
rule_file_path=/home/mdemidio/workspace/pakimor/metaheuristic/src/main/resources/ocl/detectionSingleValuePA.ocl
outputFolder=/home/mdemidio/workspace/runner/experimental_results/
ttKernel=/home/mdemidio/workspace/twotowers/TwoTowers/bin/TTKernel
sourceValPath=/home/mdemidio/workspace/pakimor/metaheuristic/src/main/resources/models/AemiliaModels/FTA/FTA.aem.val
sourceRewPath=/home/mdemidio/workspace/pakimor/metaheuristic/target/classes/models/AemiliaModels/FTA/fta_result.rew
maxCloning=3" > config.ini
java -jar metaheuristic-0.0.1-SNAPSHOT-jar-with-dependencies.jar /config.ini /home/mdemidio/workspace/pakimor/metaheuristic/src/main/resources/models/AemiliaModels/FTA/FTA
done
done
done
done
done
