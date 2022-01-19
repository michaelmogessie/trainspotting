mvn clean install -DskipTests -Pprod
docker build -t michaelmogessie/train-manager-app .
docker push michaelmogessie/train-manager-app:latest
kubectl delete deploy train-manager-deployment -n michaelmogessie || true
kubectl create -f /home/michael/git/trainspotting/train-manager-app/k8s/train_manager_deployment_def.yml