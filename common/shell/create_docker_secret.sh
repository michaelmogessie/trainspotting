kubectl create secret generic regcred --from-file=.dockerconfigjson=/home/michael/.docker/config.json --type=kubernetes.io/dockerconfigjson -n michaelmogessie