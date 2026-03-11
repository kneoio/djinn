FROM eclipse-temurin:21-jre-jammy
RUN groupadd -r jesoos && useradd -r -g jesoos jesoos
RUN apt-get update && apt-get install -y ffmpeg && rm -rf /var/lib/apt/lists/*
RUN mkdir -p /app/segmented /app/merged /app/controller-uploads /app/external /app/file-uploads /var/log/jesoos \
    && chown -R jesoos:jesoos /app /var/log/jesoos
WORKDIR /app
COPY target/jesoos-*-runner.jar app.jar
RUN chown jesoos:jesoos app.jar
USER jesoos
EXPOSE 8080 38708
ENTRYPOINT ["java", "--add-opens=java.base/java.lang=ALL-UNNAMED", "--add-opens=java.base/java.lang.invoke=ALL-UNNAMED", "-jar", "app.jar"]
