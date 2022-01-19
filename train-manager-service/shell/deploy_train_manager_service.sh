kubectl delete svc train-manager-service -n michaelmogessie || true
kubectl create -f /home/michael/git/trainspotting/train-manager-app/k8s/train_manager_service_def.yml