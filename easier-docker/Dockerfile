FROM danieledipompeo/lqn_solver:v5
COPY ./easier-uml/target/easier-uml-0.6.3-jar-with-dependencies.jar /opt/easier/easier-uml/easier.jar 
COPY ./easier-uml/easier.sh /opt/easier/easier-uml/easier.sh 
COPY ./easier-refactoringLibrary /opt/easier/easier-refactoringLibrary
COPY ./easier-uml2lqn /opt/easier/easier-uml2lqn 
COPY ./easier-uml2lqnCaseStudy /opt/easier/easier-uml2lqnCaseStudy
COPY ./easier-core/uml_config.ini /opt/easier/
WORKDIR /opt/easier/easier-uml/ 
#CMD java -jar easier-uml-0.6.3-jar-with-dependencies.jar @../uml_config.ini
ENTRYPOINT ["sh", "easier.sh"]
#ENTRYPOINT ["wget -O config.ini"]
#ENTRYPOINT ["java", "-jar", "target/easier-uml-0.6.3-jar-with-dependencies.jar @config.ini"]

