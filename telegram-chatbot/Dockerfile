FROM tomcat:8.0.43-jre8
ARG WAR_FILE
COPY target/telegram-chatbot.war /usr/local/tomcat/webapps/telegram-chatbot.war
EXPOSE 8080
CMD chmod +x /usr/local/tomcat/bin/catalina.sh
CMD ["catalina.sh", "run"]