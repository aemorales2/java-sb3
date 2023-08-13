# Getting Started
This project was built in VS Code. To ensure a good time, be sure to add the following extensions to VS Code
* Spring Boot Extension Pack
* Extension Pack for Java
* Maven for Java
* Lombok Annotations Support for VS Code
* XML Language Support by Red hat

# Running in Command Line
First, we will package the application into a jar using: `mvn clean package`. 

Once that is built we can run the application using: `java -jar target/hello-world-java17-0.0.1-SNAPSHOT.jar`

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

## Create launch.json
Go to the Run/Debug extension and select `create a launch.json file` and select `Java`. This will generate a launch.json file under a new directory named `.vscode` in your project's root directory. If you have a setting named `current file` delete that. 

## Running the application
Now you can use the green play button in the Run/Debug extension to run your application.

# Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/html/#build-image)

