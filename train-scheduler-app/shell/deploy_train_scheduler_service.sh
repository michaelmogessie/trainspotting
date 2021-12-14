kubectl delete svc train-scheduler-service -n michaelmogessie || true
kubectl create -f /home/michael/git/trainspotting/train-scheduler-app/k8s/train_scheduler_service_def.yml