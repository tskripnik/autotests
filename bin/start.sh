#!/bin/bash

./bin/stop.sh

# Starts Selenium browser and runs Gradle build for the project
docker-compose -f docker/docker-compose.yml run gradle clean test
