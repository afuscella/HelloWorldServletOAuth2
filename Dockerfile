# Dockerfile
FROM tomcat:8.0.20-jre8
MAINTAINER Arthur Silva <fuscellaarthur@gmail.com>

ADD /target/*.war /usr/local/tomcat/webapps/

CMD ["catalina.sh", "run"]
