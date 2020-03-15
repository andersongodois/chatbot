FROM openjdk:8-jre
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/telegram-chatbot.jar
WORKDIR /app
ENTRYPOINT java -jar telegram-chatbot.jar