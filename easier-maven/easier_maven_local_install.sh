#!/bin/bash
mvn install:install-file -Dfile=org.eclipse.uml2.uml_5.5.0.v20181203-1331.jar -DgroupId=it.univaq.disim.sealab.uml -DartifactId=org.eclipse.uml2.uml -Dversion=5.5.0.v20181203-1331 -Dpackaging=jar
mvn install:install-file -Dfile=org.eclipse.uml2.uml.resources_5.5.0.v20181203-1331.jar -DgroupId=it.univaq.disim.sealab.uml -DartifactId=org.eclipse.uml2.uml.resources -Dversion=5.5.0.v20181203-1331 -Dpackaging=jar
mvn install:install-file -Dfile=org.eclipse.uml2.common_2.5.0.v20181203-1331.jar -DgroupId=it.univaq.disim.sealab.uml -DartifactId=org.eclipse.uml2.common -Dversion=2.5.0.v20181203-1331 -Dpackaging=jar
mvn install:install-file -Dfile=org.eclipse.uml2.types_2.5.0.v20181203-1331.jar -DgroupId=it.univaq.disim.sealab.uml -DartifactId=org.eclipse.uml2.types -Dversion=2.5.0.v20181203-1331 -Dpackaging=jar
mvn install:install-file -Dfile=org.eclipse.uml2.uml.profile.standard_1.5.0.v20181203-1331.jar -DgroupId=it.univaq.disim.sealab.uml.profiles -DartifactId=org.eclipse.uml2.uml.profile.standard -Dversion=1.5.0.v20181203-1331 -Dpackaging=jar
mvn install:install-file -Dfile=org.eclipse.papyrus.marte.static.profile_1.2.0.201703081153.jar -DgroupId=it.univaq.disim.sealab.uml.profiles -DartifactId=org.eclipse.papyrus.marte.static.profile -Dversion=1.2.0.201703081153 -Dpackaging=jar
mvn install:install-file -Dfile=com.masdes.dam.static.profile_0.13.1.201801221725.jar -DgroupId=es.unizar.profiles -DartifactId=com.masdes.dam.static.profile -Dversion=0.13.1.201801221725 -Dpackaging=jar
