git clone -b v1 https://github.com/Akasam/samplephpwebsite

docker run -v ${PWD}:/app --rm phpunit/phpunit:latest tests/TestFunctions.php

docker container create --name temp -v samplephpwebsite_site:/site -v samplephpwebsite_conf:/conf hello-world
docker cp ./samplephpwebsite/. temp:/site
docker cp ./nginx/. temp:/conf
docker rm temp

docker-compose up -d