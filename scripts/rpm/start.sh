#!/bin/sh
nohup java -jar $SERVER_OPTS /opt/bdshr-terminology-server/lib/bdshr-terminology-server.jar > /var/log/bdshr-terminology-server/bdshr-terminology-server.log 2>/var/log/bdshr-terminology-server/bdshr-terminology-server.log < /dev/null &
echo $! > /var/run/bdshr-terminology-server/bdshr-terminology-server.pid