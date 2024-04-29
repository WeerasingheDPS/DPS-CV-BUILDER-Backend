FROM openjdk:17
EXPOSE 8000
ADD target/dps_cv_builder.jar dps_cv_builder.jar
ENTRYPOINT ["java","-jar","/dps_cv_builder.jar"]