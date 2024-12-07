FROM tomcat:10.1.20-jdk20-oracle

COPY target/semester-work-LexaKrl-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
