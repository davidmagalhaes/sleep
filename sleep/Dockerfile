FROM openjdk:17-bullseye

WORKDIR /app

COPY build.gradle gradlew settings.gradle ./
COPY gradle/ gradle/

RUN ./gradlew wrapper

COPY src/ src

RUN ./gradlew build; exit 0

# ENTRYPOINT ["java","-jar","build/libs/sleep-0.0.1-SNAPSHOT.jar"]

CMD tail -f /dev/null