kubectl delete svc station-manager-service -n michaelmogessie || true
kubectl create -f /home/michael/git/trainspotting/station-manager-app/k8s/station_manager_service_def.yml