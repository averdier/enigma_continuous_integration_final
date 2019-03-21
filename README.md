# Sample PHP Website V1

Dépendances:
- Git
- Docker
- Docker compose (version >=3)

## Installation

Cloner le projet
```
git clone -b v1 https://github.com/averdier/enigma_continuous_integration_final
```

## Lancer le projet

Windows
```
.\start.ps1
```

Linux / MacOS (non testé)
```
chmod +x start.sh

./start.sh
```

## Jenkins

Dépendances:
- Jenkins avec Docker ou Le projet [Jenkins Docker](https://github.com/averdier/enigma_jenkins_docker)
- Le plugin `Docker`
- Le plugin `Job DSL`
- Configurer un projet à partir du script `jobs/build.groovy`
- Configurer un pipeline à partir du script `jobs/Jenkinsfile`
