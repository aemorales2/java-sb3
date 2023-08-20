# Getting Started
This project was built in VS Code. To ensure a good time, be sure to add the following extensions to VS Code
* Spring Boot Extension Pack
* Extension Pack for Java
* Maven for Java
* Lombok Annotations Support for VS Code
* XML Language Support by Red hat
* Docker
* Kubernetes

# PreReqs
* Have a kubernetes cluster and kubectl available
* Install helm CLI 
* Install ingress controller to avoid using port forwarding

```
helm repo add nginx-stable https://helm.nginx.com/stable
helm repo update
helm install nginx-ingress nginx-stable/nginx-ingress --set rbac.create=true -n nginx --create-namespace
```

# Running in Command Line
First, we will package the application into a jar using: `mvn clean package`. 

Then, we need to build our docker image using: `docker build . -t ws-datetime:v1`.

Before we package our chart, lets check for syntax error and verify our values using: `helm template --debug helm`

Next, we will package our helm application using: `helm package helm`

Finally, we can run our app using: `helm install ws-datetime ws-datetime-0.0.1.tgz -n local --create-namespace`. This will create the deployment, ingress, and service (along with acocompanying managed resources and namespace).

Our app is available at: `http://localhost/ws_datetime/currentDateTime`.

# Clean Up the Cluster
Run the following command to delete all the objects specified in the manifest file: `helm uninstall ws-datetime -n local`.

# Debugging in VS Code
Run the application by following the steps in the section above. 

In `.vscode/launch.json` add a new configuration under `Java: Attach to Remote Program` that should look something like below.
```
{
    "type": "java",
    "name": "kube Debugger",
    "request": "attach",
    "hostName": "localhost",
    "port": "5005"
}
```

The above works thanks to line 29 of manifest.yaml where we enable the Java Debug Wire Protocol (JDWP) with port 5005.