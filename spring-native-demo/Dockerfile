FROM openjdk:17
VOLUME /tmp
ARG JAR_FILE=build/libs/spring-native-demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","${JAVA_OPTS}","-jar","/app.jar"]
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]