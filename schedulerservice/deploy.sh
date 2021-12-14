mvn clean install -DskipTests
docker build -t michaelmogessie/scheduler .
docker push michaelmogessie/scheduler:latest
kubectl delete deploy scheduler-deployment -n michaelmogessie || true
kubectl create -f k8s/scheduler_deployment_def.yml