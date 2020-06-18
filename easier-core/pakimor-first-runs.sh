#!/bin/bash

SOURCE="${BASH_SOURCE[0]}"
DIR="$( dirname "$SOURCE" )"
while [ -h "$SOURCE" ]
do
  SOURCE="$(readlink "$SOURCE")"
  [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE"
  DIR="$( cd -P "$( dirname "$SOURCE"  )" && pwd )"
done
DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"

cd "$DIR"

printf "\nSTARTING ON BoA with LENGTH=4 POP=8\n"

java -jar ./target/metaheuristic-0.0.1-SNAPSHOT-jar-with-dependencies.jar /src/main/resources/configs/config-BoA-length4-pop8.ini

printf "\nSTARTING ON BoA with LENGTH=6 POP=2\n"

java -jar ./target/metaheuristic-0.0.1-SNAPSHOT-jar-with-dependencies.jar /src/main/resources/configs/config-BoA-length6-pop2.ini

echo "\nSTARTING ON BoA with LENGTH=6 POP=5\n"

java -jar ./target/metaheuristic-0.0.1-SNAPSHOT-jar-with-dependencies.jar /src/main/resources/configs/config-BoA-length6-pop5.ini

echo "\nSTARTING ON BoA with LENGTH=6 POP=8\n"

java -jar ./target/metaheuristic-0.0.1-SNAPSHOT-jar-with-dependencies.jar /src/main/resources/configs/config-BoA-length6-pop8.ini

echo "\nDONE!\n"
