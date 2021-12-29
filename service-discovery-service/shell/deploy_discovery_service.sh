kubectl delete svc discovery-service -n michaelmogessie || true
kubectl create -f /home/michael/git/trainspotting/discovery-app/k8s/discovery_service_def.yml