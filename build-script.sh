#!/bin/bash

cd docker/
docker-compose up -d

cd ../parcel-delivery-commons/
mvn clean install

cd ../parcel-delivery-kafka/
mvn clean install

cd ../identity-and-access-management-service/
mvn clean install

cd ../resource-server/
mvn clean install

cd ../delivery-service/
mvn clean install


echo "This script is about to run another script."
bash ./test.sh
echo "This script has just run another script."
