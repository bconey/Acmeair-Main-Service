ARG BASE_IMAGE=icr.io/appcafe/open-liberty:kernel-slim-java8-openj9-ubi

FROM $BASE_IMAGE

# Config
COPY --chown=1001:0 src/main/liberty/config/server.xml /config/server.xml
COPY --chown=1001:0 src/main/liberty/config/server.env /config/server.env
COPY --chown=1001:0 src/main/liberty/config/jvm.options /config/jvm.options

# App
COPY --chown=1001:0 target/acmeair-mainservice-java-6.0.war /config/apps/

User root

RUN --mount=type=secret,id=token --mount=type=secret,id=user\
       mkdir -p /mytemp && cd /mytemp \
       && curl --retry 7 -sSf -u $(cat /run/secrets/user):$(cat /run/secrets/token) \
      -O 'https://na.artifactory.swg-devops.com/artifactory/hyc-wassvt-team-maven-virtual/svtMessageApp/svtMessageApp/2.0.2/svtMessageApp-2.0.2.war' \
      && curl --retry 7 -sSf -u $(cat /run/secrets/user):$(cat /run/secrets/token) \
      -O 'https://na.artifactory.swg-devops.com/artifactory/hyc-wassvt-team-maven-virtual/microwebapp/microwebapp/2.0.1/microwebapp-2.0.1.war' \
      && curl --retry 7 -sSf -u $(cat /run/secrets/user):$(cat /run/secrets/token) \
      -O 'https://na.artifactory.swg-devops.com/artifactory/hyc-wassvt-team-maven-virtual/com/ibm/ws/lumberjack/badapp/2.0.1/badapp-2.0.1.war' \
      && chown -R 1001:0 /mytemp/*.war  && mv /mytemp/*.war /config/dropins
      
user 1001

# Setting for the verbose option
ARG VERBOSE=true
ARG FULL_IMAGE=false

# This script will add the requested XML snippets to enable Liberty features and grow image to be fit-for-purpose using featureUtility. 
# Only available in 'kernel-slim'. The 'full' tag already includes all features for convenience.

RUN if [ "$FULL_IMAGE" = "true" ] ; then echo "Skip running features.sh for full image" ; else features.sh ; fi


# Logging vars
ENV LOGGING_FORMAT=simple
ENV ACCESS_LOGGING_ENABLED=false
ENV TRACE_SPEC=*=info

# Build SCC?
ARG CREATE_OPENJ9_SCC=false
ENV OPENJ9_SCC=false
ARG VERBOSE=true
RUN configure.sh
