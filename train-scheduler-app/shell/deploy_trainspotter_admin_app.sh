docker build -t michaelmogessie/trainspotter-admin-app .
docker push michaelmogessie/trainspotter-admin-app:latest
kubectl delete deploy trainspotter-admin-app-deployment -n michaelmogessie || true
kubectl create -f /home/michael/git/trainspotting/trainspotter-admin-app/k8s/trainspotter_admin_app_deployment_def.yml