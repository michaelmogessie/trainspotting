mvn clean install -DskipTests
docker build -t michaelmogessie/train-service-docker .
docker push michaelmogessie/train-service-docker:latest
kubectl delete deploy train-service-deployment || true
kubectl create -f train_service_deployment_def.yml