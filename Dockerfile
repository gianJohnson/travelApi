FROM java:8-jdk-alpine
COPY ./build/libs/case-0.1.0-0.1.0.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch case-0.1.0-0.1.0.jar'
ENTRYPOINT ["java","-jar","case-0.1.0-0.1.0.jar"]