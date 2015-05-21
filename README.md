# Setting up the environment

Requisites
------------
* [VirtualBox](https://www.virtualbox.org/)
* [Vagrant](http://docs.vagrantup.com/v2/installation/index.html)


To package the application
---------------------------
cd into the project directory and run the following command
```
./gradlew clean dist
```
The above would generate an rpm, which needs to be installed and further setup. Please follow the steps below for setting up


Properties
----------
* Since the application will be run using an embedded tomcat server, the configuration is read from environment variables.
* Add or remove properties to the local.properties file
* Gradle will set environment variables using the properties specified in this file when running tests
* If you install the rpm that is generated in a redhat machine, you will be able to edit the configuration in the
  /etc/bdshr-terminology-server/bdshr-terminology-server.conf file.


Steps to setup environment on a VM
-----------------------------------
Git clone the repositories (under a common parent directory)
* [FreeSHR-Playbooks](https://github.com/SharedHealth/FreeSHR-Playbooks)
* [Terminology-Server](https://github.com/SharedHealth/Terminology-Server)
* [openmrs-module-freeshr_terminology_feed](https://github.com/SharedHealth/openmrs-module-freeshr_terminology_feed)

##### Setup ansible group_vars
* replace FreeSHR-Playbooks/group_vars/all with FreeSHR-Playbooks/group_vars/all_example
* create a dummy ansible vault pass file in your user home folder.
```
touch ~/.vaultpass.txt
```

##### build Terminology Server
* cd Terminology-Server
* ./gradlew clean dist
* cp build/distributions/bdshr-terminology-server-0.1-1.noarch.rpm /tmp/

##### get essential openmrs modules
* wget https://oss.sonatype.org/service/local/repositories/releases/content/org/ict4h/openmrs/openmrs-atomfeed-omod/2.2/openmrs-atomfeed-omod-2.2.jar -O /tmp/openmrs-atomfeed-2.2.omod
* wget https://modules.openmrs.org/modulus/api/releases/1138/download/webservices.rest-omod-2.9.omod -O /tmp/webservices.rest-2.9.omod

##### build terminology feed module
* cd openmrs-module-freeshr_terminology_feed
* mvn clean install
* cp omod/target/freeshr-terminology-feed-1.0-SNAPSHOT.omod /tmp/

##### provision Terminology Server
* cd Terminology-Server
* vagrant up | vagrant provision

Notes: The above will provision and deploy, Terminology Server running on port 9080 on the VM.
This will also provision mysql and create an empty database "terminologies" and a sample user "terminologies" with all privileges to the database.
The server should be running at address "http://192.168.33.17:9080/openmrs". However, to get the "Terminology Server" working, you need to follow the "installation wizard" steps and setup as described in the
[confluence page](https://sharedhealth.atlassian.net/wiki/pages/viewpage.action?pageId=11370540).



### Troubleshooting
* you might have to install sshpass. Please refer here [here](http://www.nextstep4it.com/sshpass-command-non-interactive-ssh/)