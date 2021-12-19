mvn clean install -DskipTests -Pprod
docker build -t michaelmogessie/train-scheduler-app .
docker push michaelmogessie/train-scheduler-app:latest
kubectl delete deploy train-scheduler-deployment -n michaelmogessie || true
kubectl create -f /home/michael/git/trainspotting/train-scheduler-app/k8s/train_scheduler_deployment_def.yml