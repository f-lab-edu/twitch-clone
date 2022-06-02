#!/bin/sh

java -javaagent:./elastic-apm-agent-1.31.0.jar \
-Delastic.apm.service_name=apm-1 \
-Delastic.apm.server_urls=http://49.50.162.164:8200 \
-Delastic.apm.application_packages=com.example \
-jar ./module-api/build/libs/module-api-0.0.1-SNAPSHOT.jar