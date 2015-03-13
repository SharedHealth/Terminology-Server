#!/bin/bash


#create links

ln -s /opt/bdshr-terminology-server/etc /etc/bdshr-terminology-server
ln -s /opt/bdshr-terminology-server/bin/bdshr-terminology-server /etc/init.d/bdshr-terminology-server
ln -s /opt/bdshr-terminology-server/run /var/run/bdshr-terminology-server
ln -s /opt/bdshr-terminology-server/openmrs /var/run/bdshr-terminology-server/openmrs
ln -s /opt/bdshr-terminology-server/log /var/log/bdshr-terminology-server

(cd /opt/bdshr-terminology-server/openmrs && unzip ../openmrs.war)

chkconfig --add bdshr-terminology-server