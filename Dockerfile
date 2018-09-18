FROM centos:6.6

ENV JAVA_VERSION_MAJOR=8 \
    JAVA_VERSION_MINOR=171 \
    JAVA_VERSION_BUILD=11 \
    JAVA_URL_HASH=512cd62ec5174c3487ac17c61aaa89e8

RUN yum install -y wget && \
    wget -q --no-cookies --no-check-certificate \
      --header 'Cookie:oraclelicense=accept-securebackup-cookie' \
      "http://download.oracle.com/otn-pub/java/jdk/${JAVA_VERSION_MAJOR}u${JAVA_VERSION_MINOR}-b${JAVA_VERSION_BUILD}/${JAVA_URL_HASH}/jre-${JAVA_VERSION_MAJOR}u${JAVA_VERSION_MINOR}-linux-x64.rpm" && \
    yum install -y jre-${JAVA_VERSION_MAJOR}u${JAVA_VERSION_MINOR}-linux-x64.rpm && rm -f jre-*.rpm &&  yum clean all

COPY build/distributions/bdshr-*.noarch.rpm /tmp/tr.rpm
RUN yum install -y /tmp/tr.rpm && rm -f /tmp/tr.rpm && yum clean all
COPY env/* /etc/bdshr-terminology-server/
RUN wget https://dl.bintray.com/openmrs/omod/webservices.rest-2.12.omod -O /opt/bdshr-terminology-server/modules/webservices.rest-2.12.omod \
 && wget https://oss.sonatype.org/service/local/repositories/releases/content/org/ict4h/openmrs/openmrs-atomfeed-omod/2.5.2/openmrs-atomfeed-omod-2.5.2.jar -O /opt/bdshr-terminology-server/modules/openmrs-atomfeed-2.5.2.omod \ 
&& wget -O /opt/bdshr-terminology-server/modules/freeshr-terminology-feed-2.6-SNAPSHOT.omod  https://github.com/SharedHealth/openmrs-module-freeshr_terminology_feed/releases/download/2.6-82/freeshr-terminology-feed-2.6-SNAPSHOT.omod 
RUN mkdir -p /root/.OpenMRS/ &&  ln -s /opt/bdshr-terminology-server/modules/ ~/.OpenMRS/
ENTRYPOINT . /etc/bdshr-terminology-server/bdshr-terminology-server.conf && java -jar $SERVER_OPTS /opt/bdshr-terminology-server/lib/bdshr-terminology-server.jar > /var/log/bdshr-terminology-server/bdshr-terminology-server.log
#ENTRYPOINT tail -f /dev/null

