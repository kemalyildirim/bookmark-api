FROM amazoncorretto:19

ADD build/libs/bookmarker-api-*.jar bookmarker-api.jar
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java -jar /bookmarker-api.jar" ]