job('Sample PHP website V1 Job') {
    scm {
        remote {
            name('v1')
            url('git@github.com:averdier/enigma_continuous_integration_final.git')
            branch('v1')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    steps {
        shell("git clone -b v1 https://github.com/Akasam/samplephpwebsite")
        shell("docker container create --name temp_groovy -v samplephpwebsite_site:/site -v samplephpwebsite_conf:/conf hello-world")
        shell('docker cp "$(pwd)/samplephpwebsite/." temp_groovy:/site')
        shell('docker cp "$(pwd)/tests/." temp_groovy:/site')
        shell("docker cp ./nginx/. temp_groovy:/conf")
        shell("docker rm temp_groovy")
        shell('docker run -v samplephpwebsite_site:/app --rm phpunit/phpunit:latest tests/TestFunctions.php')
    }
}