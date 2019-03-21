#!/bin/bash

git clone -b v1 https://github.com/Akasam/samplephpwebsite

docker container create --name temp_v1 -v samplephpwebsite_site_v1:/site -v samplephpwebsite_conf_v1:/conf hello-world
docker cp "$(pwd)/samplephpwebsite/." temp_v1:/site/src
docker cp "$(pwd)/tests/." temp_v1:/site/tests/
docker cp "$(pwd)/nginx/." temp_v1:/conf
docker rm temp_v1

docker run -v samplephpwebsite_site_v1:/app --rm phpunit/phpunit:latest tests/TestFunctions.php

docker-compose up -d