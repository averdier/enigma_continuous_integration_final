job('Sample PHP website V2 Job') {
    scm {
        git('https://github.com/averdier/enigma_continuous_integration_final', 'v2') {  node -> 
            node / gitConfigName('rastadev')
            node / gitConfigEmail('arthur@elonet.fr')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    steps {
        shell("git clone -b v2 https://github.com/Akasam/samplephpwebsite")
        shell("docker container create --name temp_groovy_v2 -v samplephpwebsite_site_v2:/site -v samplephpwebsite_conf_v2:/conf hello-world")
        shell('docker cp "$(pwd)/samplephpwebsite/." temp_groovy_v2:/site/src')
        shell('docker cp "$(pwd)/tests/." temp_groovy_v2:/site/tests/')
        shell('docker cp "$(pwd)/nginx/." temp_groovy_v2:/conf')
        shell("docker rm temp_groovy_v2")
        shell("rm -r samplephpwebsite")
        shell('docker run -v samplephpwebsite_site_v2:/app --rm phpunit/phpunit:latest tests/TestFunctions.php')
    }
}