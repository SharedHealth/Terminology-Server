#!/bin/bash

#Clean up
rm -rf /var/lib/bdshr-terminology-server

rm -f /etc/bdshr-terminology-server
rm -f /etc/init.d/bdshr-terminology-server
rm -f /var/run/bdshr-terminology-server
rm -f /var/log/bdshr-terminology-server


service bdshr-terminology-server stop || true