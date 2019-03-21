git clone -b v2 https://github.com/Akasam/samplephpwebsite

docker container create --name temp_v2 -v samplephpwebsite_site_v2:/site -v samplephpwebsite_conf_v2:/conf hello-world
docker cp ${PWD}/samplephpwebsite/. temp_v2:/site/src
docker cp ${PWD}/tests/. temp_v2:/site/tests/
docker cp ${pwd}/nginx/. temp_v2:/conf
docker rm temp_v2

docker run -v samplephpwebsite_site_v2:/app --rm phpunit/phpunit:latest tests/TestFunctions.php

docker-compose up -d