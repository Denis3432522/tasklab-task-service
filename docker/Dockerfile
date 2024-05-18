FROM bellsoft/liberica-openjdk-alpine:17
VOLUME /tmp

WORKDIR /server
ENV EXTRACTED=target/extracted

COPY ${EXTRACTED}/dependencies/ .
COPY ${EXTRACTED}/spring-boot-loader/ .
COPY ${EXTRACTED}/snapshot-dependencies/ .
COPY ${EXTRACTED}/application/ .

ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]