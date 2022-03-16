FROM danieledipompeo/lqn_solver:v5
COPY ./easier-uml/target/easier-uml-0.6.3-jar-with-dependencies.jar /opt/easier/easier-uml/easier.jar 
COPY ./easier-docker/easier.sh /opt/easier/easier-uml/easier.sh 
COPY ./easier-refactoringLibrary /opt/easier/easier-refactoringLibrary
COPY ./easier-uml2lqn /opt/easier/easier-uml2lqn 
COPY ./easier-uml2lqnCaseStudy /opt/easier/easier-uml2lqnCaseStudy
WORKDIR /opt/easier/easier-uml/ 
ENTRYPOINT ["sh", "easier.sh"]
