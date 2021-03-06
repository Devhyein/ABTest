#!/bin/bash

#########################################
##  Step 1 : Set variables for script  ##
#########################################

# Set docker image and container title
TITLE=abtest

# Set name of images
SPRING_IMG=${TITLE}/springback
FRONT_IMG=${TITLE}/front
SAMPLE_IMG=${TITLE}/sample

# Set name of containers
SPRING_DOCKER=${TITLE}-springback
FRONT_DOCKER=${TITLE}-front
SAMPLE_DOCKER=${TITLE}-sample

####################################################
##  Step 2 : Remove docker images and containers  ##
####################################################

pwd

# Remove current image
docker rmi -f $SPRING_IMG || true
docker rmi -f $FRONT_IMG || true
docker rmi -f $SAMPLE_IMG || true

# Remove and force stop current running container
docker rm -f $SPRING_DOCKER || true
docker rm -f $FRONT_DOCKER || true
docker rm -f $SAMPLE_DOCKER || true

################################################
##  Step 3 : Build and Deploy spring project  ##
################################################
# Move to spring folder for build and deploy spring backend
cd springback
pwd

chmod +x ./mvnw

# Build spring project
./mvnw package

# Make docker image
docker build -t $SPRING_IMG .

# Create docker container and run
# sudo docker run --name $BACK_DOCKER --network="host" -v tvility:/tvility -d $BACK_IMG
docker run --name $SPRING_DOCKER --network="host" -d $SPRING_IMG

##################################################
##  Step 4 : Build and Deploy frontend project  ##
##################################################
# Move to frontend folder for build and deploy frontend
cd ../frontend
pwd

# Make docker image
docker build -t $FRONT_IMG .

# Create docker container and run
docker run --name $FRONT_DOCKER -p 8081:8081 -d $FRONT_IMG

# Move to samplepage folder for build and deploy samplepage
cd ../samplepage
pwd

# Make docker image
docker build -t $SAMPLE_IMG .

# Create docker container and run
docker run --name $SAMPLE_DOCKER -p 8082:8082 -d $SAMPLE_IMG
