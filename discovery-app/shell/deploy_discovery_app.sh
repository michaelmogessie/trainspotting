mvn clean install -DskipTests
docker build -t michaelmogessie/discovery-app .
docker push michaelmogessie/discovery-app:latest
kubectl delete po discovery-app-deployment || true
kubectl create -f /home/michael/git/trainspotting/discovery-app/k8s/discovery_app_deployment_def.yml