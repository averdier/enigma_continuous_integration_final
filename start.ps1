git clone -b v3 https://github.com/Akasam/samplephpwebsite

docker container create --name temp_v3 -v samplephpwebsite_site_v3:/site -v samplephpwebsite_conf_v3:/conf -v samplephpwebsite_bdd_install_v3:/bdd/install hello-world
docker cp ${PWD}/samplephpwebsite/. temp_v3:/site/src
docker cp ${PWD}/tests/. temp_v3:/site/tests/
docker cp ${pwd}/nginx/. temp_v3:/conf
docker cp ${PWD}/bdd/install/. temp_v3:/bdd/install
docker rm temp_v3

docker run -v samplephpwebsite_site_v3:/app --rm phpunit/phpunit:latest tests/TestFunctions.php

docker-compose up -d