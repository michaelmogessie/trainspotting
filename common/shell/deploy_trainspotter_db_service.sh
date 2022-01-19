kubectl delete svc trainspotter-db-service -n michaelmogessie || true
kubectl create -f /home/michael/git/trainspotting/common/k8s/trainspotter_db_service_def.yml