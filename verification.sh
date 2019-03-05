#!/bin/sh
#
# ------------------------------------------------------
# Weatherfy environment inspection script.
# ------------------------------------------------------
#

verifyJdk() 
{
	echo "Verifying JDK"
	jdk_version=$(java -version 2>&1 | head -n 1 | cut -d '"' -f2 | cut -d '.' -f1)
	if [ $jdk_version -lt "11" ]; then
  		echo "Java 11 is required"
  		exit 1
	fi
	echo "JDK is OK"
}

verifyDocker() 
{
	echo "Verifying Docker"
	if [ -x "$(command -v docker)" ]; then
		echo "Docker is OK"
	else
		echo "Docker is required"
		exit 1
	fi
}

verifyDockerCompose() 
{
	echo "Verifying Docker Compose"
	if [ -x "$(command -v docker-compose)" ]; then
		echo "Docker Compose is OK"
	else
		echo "Docker Compose is required"
		exit 1
	fi
}

echo "Starting verification"

echo "===="

verifyJdk

echo "===="

verifyDocker

echo "===="

verifyDockerCompose

echo "===="

echo "Everything is OK! :)"
