#!/bin/bash


#create links

ln -s /var/lib/bdshr-terminology-server/etc /etc/bdshr-terminology-server
ln -s /var/lib/bdshr-terminology-server/bin/bdshr-terminology-server /etc/init.d/bdshr-terminology-server
ln -s /var/lib/bdshr-terminology-server/run /var/run/bdshr-terminology-server
ln -s /var/lib/bdshr-terminology-server/openmrs /var/run/bdshr-terminology-server/openmrs
ln -s /var/lib/bdshr-terminology-server/log /var/log/bdshr-terminology-server

(cd /var/lib/bdshr-terminology-server/openmrs && unzip ../openmrs.war)

chkconfig --add bdshr-terminology-server