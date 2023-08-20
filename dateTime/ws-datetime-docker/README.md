# Getting Started
This project was built in VS Code. To ensure a good time, be sure to add the following extensions to VS Code
* Spring Boot Extension Pack
* Extension Pack for Java
* Maven for Java
* Lombok Annotations Support for VS Code
* XML Language Support by Red hat
* Docker

# Running in Command Line
First, we will package the application into a jar using: `mvn clean package`. 

Then, we need to build our docker image using: `docker build . -t ws-datetime:v1`

Finally, we can run our app using: `docker run -t --rm -e JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005" -p 8080:8080 -p 5005:5005 ws-datetime:v1`

# Running using VS Code Run/Debug
Be sure you have vscode configured to use maven and the version of java you are using for your project if you want to use the available UI features.

## Configuring Maven
Open VS Code settings (ctrl + ',') and search for `maven.executable.path`. If this is blank, be sure to set this to the path where you have maven installed 

## Configuring Java
Open VS Code settings (ctrl + ',') and search for `java.configuration.runtimes`. Select `edit in settings.json` and add the paths for the different versions of Java you have. Below is a sample of what that might look like. 

```
"java.configuration.runtimes": [
    {
        "name": "JavaSE-11",
        "path": "C:\\Program Files\\Java\\jdk-11.0.12"
    },
    {
        "name": "JavaSE-16",
        "path": "C:\\Program Files\\Java\\jdk-16.0.2"
    },
    {
        "name": "JavaSE-17",
        "path": "C:\\Program Files\\Java\\jdk-17"
    }
],
```

## Create launch.json and tasks.json
Go to the Run/Debug extension and select `create a launch.json file` and select `Java`. This will generate a launch.json file under a new directory named `.vscode` in your project's root directory. Replace the content of the file with the below.

```
{
    "version": "2.0.0",
    "configurations": [
        {
            "type": "java",
            "name": "Docker Java Debug (Attach)",
            "preLaunchTask": "Docker Run Container",
            "request": "attach",
            "hostName": "localhost",
            "port": 5005,
            "postDebugTask": "Docker Cleanup"
        }
    ]
}
```

Create a new file named `tasks.json` with the following content (remember to replace desired-image-name with your desired value): 

```
{
    "version": "2.0.0",
    "tasks": [
      {
        "label": "Docker Build Image",
        "type": "shell",
        "command": "docker build . -t {desired-image-name}:v1"
      },
      {
        "label": "Docker Run Container",
        "type": "shell", 
        "command": "docker", 
        "args": ["run", "-d", "-e", "JAVA_TOOL_OPTIONS=-XX:MaxRAMPercentage=60.0 -Dspring.profiles.active=LOCAL -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-p", "8080:8080", "-p", "5005:5005", "{desired-image-name}:v1"],
        "dependsOrder": "sequence",
        "dependsOn": ["Docker Build Image"]
      },
      {
        "label": "Docker Stop Container",
        "type": "shell", 
        "command": "docker ps -a | grep {desired-image-name}:v1 | awk '{print $1}' | xargs docker stop"
      },
      {
        "label": "Docker Delete Container",
        "type": "shell", 
        "command": "docker ps -a | grep {desired-image-name}:v1 | awk '{print $1}' | xargs docker rm",
        "dependsOrder": "sequence",
        "dependsOn": ["Docker Stop Container"]
      },
      {
        "label": "Docker Delete Image",
        "type": "shell", 
        "command": "docker images | grep {desired-image-name} | awk '{print $3}' | xargs docker rmi", 
        "dependsOrder": "sequence",
        "dependsOn": ["Docker Delete Container"]
      },
      {
        "label": "Docker Cleanup",
        "type": "shell",
        "command": "echo 'The container and image for {desired-image-name}:v1 have been successfully deleted.'",
        "dependsOrder": "sequence",
        "dependsOn": ["Docker Delete Image"]
      }
    ]
}

```

## Running the application
Use `mvn clean package` to build the app. Now you can use the green play button in the Run/Debug extension to run your application.

# Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/html/#build-image)

