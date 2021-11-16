#!/bin/bash

mvn clean assembly:single -pl easier-uml -am -DskipTests -T4
mvn install -DskipTests
