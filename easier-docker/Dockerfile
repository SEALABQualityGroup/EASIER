FROM lqn_solver:v5
COPY ./easier-uml/target/easier-uml-0.6.3-jar-with-dependencies.jar /opt/easier/easier-uml 
COPY ./easier-refactoringLibrary /opt/easier/easier-refactoringLibrary
COPY ./easier-uml2lqn /opt/easier/easier-uml2lqn 
COPY ./easier-uml2lqnCaseStudy /opt/easier/easier-uml2lqnCaseStudy
COPY ./easier-core/uml_config.ini /opt/easier/
WORKDIR /opt/lqn_solver
#WORKDIR /opt/easier/easier-uml/ 
#CMD java -jar easier-uml-0.6.3-jar-with-dependencies.jar @../uml_config.ini

