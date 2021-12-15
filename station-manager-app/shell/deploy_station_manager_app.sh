mvn clean install -DskipTests
docker build -t michaelmogessie/station-manager-app .
docker push michaelmogessie/station-manager-app:latest
kubectl delete deploy station-manager-deployment -n michaelmogessie || true
kubectl create -f /home/michael/git/trainspotting/station-manager-app/k8s/station_manager_deployment_def.yml