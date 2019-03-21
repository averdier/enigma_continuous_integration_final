job('Sample PHP website V1 Job') {
    scm {
        git('https://github.com/averdier/enigma_continuous_integration_final', 'v1') {  node -> 
            node / gitConfigName('rastadev')
            node / gitConfigEmail('arthur@elonet.fr')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    steps {
        shell("git clone -b v1 https://github.com/Akasam/samplephpwebsite")
        shell("docker container create --name temp_groovy -v samplephpwebsite_site:/site -v samplephpwebsite_conf:/conf hello-world")
        shell('docker cp "$(pwd)/samplephpwebsite/." temp_groovy:/site/src')
        shell('docker cp "$(pwd)/tests/." temp_groovy:/site/tests/')
        shell('docker cp "$(pwd)/nginx/." temp_groovy:/conf')
        shell("docker rm temp_groovy")
        shell("rm -r samplephpwebsite")
        shell('docker run -v samplephpwebsite_site:/app --rm phpunit/phpunit:latest tests/TestFunctions.php')
    }
}