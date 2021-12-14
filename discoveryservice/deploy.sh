mvn clean install -DskipTests
docker build -t michaelmogessie/discovery-service-docker .
docker push michaelmogessie/discovery-service-docker:latest
kubectl delete po discovery-service-deployment || true
kubectl create -f discovery_service_deployment_def.yml