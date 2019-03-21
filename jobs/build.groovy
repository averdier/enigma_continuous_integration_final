job('Sample PHP website V3 Job') {
    scm {
        git('https://github.com/averdier/enigma_continuous_integration_final', 'v3') {  node -> 
            node / gitConfigName('rastadev')
            node / gitConfigEmail('arthur@elonet.fr')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    steps {
        shell("git clone -b v3 https://github.com/Akasam/samplephpwebsite")
        shell("docker container create --name temp_groovy_v3 -v samplephpwebsite_site_v2:/site -v samplephpwebsite_conf_v3:/conf -v samplephpwebsite_bdd_install_v3:/bdd/install hello-world")
        shell('docker cp "$(pwd)/samplephpwebsite/." temp_groovy_v3:/site/src')
        shell('docker cp "$(pwd)/tests/." temp_groovy_v3:/site/tests/')
        shell('docker cp "$(pwd)/nginx/." temp_groovy_v3:/conf')
        shell('docker cp "$(pwd)/bdd/install/." temp_groovy_v3:/bdd/install')
        shell("docker rm temp_groovy_v3")
        shell("rm -r samplephpwebsite")
        shell('docker run -v samplephpwebsite_site_v3:/app --rm phpunit/phpunit:latest tests/TestFunctions.php')
    }
}