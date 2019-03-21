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
- Le plugin `Pusblish over SSH`

### Génération de clés SSH

Afin de publier le site sur un serveur linux, il est nécessaire de créer un jeu de clés SSH
```
mkdir keys

docker run --rm -it -v ${PWD}/keys/:/keys/ rastasheep/ubuntu-sshd:18.04 bash -c 'ssh-keygen -t rsa -f /keys/id_rsa'
```

### Configuration du serveur

Testé sur un VPS Ovh avec Docker

Ajouter la clé `id_rsa.pub` dans `~/.ssh/authorized_keys`
```
cat id_rsa.pub >> ~/.ssh/authorized_keys
```

### Lancer Jenkins

Lancer Jenkins
```
docker run --name jenkins_docker -p 8080:8080 -v //var/run/docker.sock:/var/run/docker.sock -v ${PWD}/keys/id_rsa:/root/.ssh/id_rsa jenkins/docker
```

- Choisir les plugins suggérés
- Ajouter le premier utilisateur
- Configurer un projet à partir du script `jobs/build.groovy`
- Configurer un pipeline à partir du script `jobs/Jenkinsfile`
