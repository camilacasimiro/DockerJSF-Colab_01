FROM openjdk:11
COPY src/com/company/Main.java /recurso_02/
WORKDIR /recurso_02
RUN javac Main.java
RUN jar cvf docker-jsf.jar Main.class
RUN touch DOCKER.MF
RUN echo "Main-Class: Main" > ./DOCKER.MF
RUN jar cvmf DOCKER.MF docker-jsf.jar Main.class
ENTRYPOINT java Main
CMD ["java","-jar","docker-jsf.jar"]