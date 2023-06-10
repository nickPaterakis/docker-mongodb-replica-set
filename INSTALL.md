<!-- Table of Contents -->
## Table of Contents
- [Prerequisites](#prerequisites)
- [Build the Spring Boot App](#build-the-spring-boot-app)
- [Start the Containers](#start-the-containers)
- [Access the vLab](#access-the-vlab)
- [Stop the Containers](#stop-the-containers)

## Prerequisites

Before running the vLab, make sure you have the following installed on your system:

- Maven
- Docker

## Build the Country Info Application

To build the Country Info application JAR file, follow these steps:

1. Open a terminal or command prompt.
2. Navigate to the root directory of the project.
3. Run the following command:

```bash
mvn clean install -Dmaven.test.skip
```

This command compiles the source code and creates an executable JAR file in the `target` directory.

Next, build the Docker image by executing the following command in the root directory of the project:

```bash
./vlab-orchestrator.sh build
```

This command builds a Docker image named `country-info`.

## Start the Containers

To start the vLab containers, use the following steps:

1. Open a terminal or command prompt.
2. Navigate to the root directory of the project.
3. Run the following command:

```bash
./vlab-orchestrator.sh start
```

This command starts the MongoDB replica set containers (`mongo1`, `mongo2`, and `mongo3`), the Mongo Express GUI container (`mongo-express`), and the Spring Boot application container (`country-info`). It also initializes the replica set if it's not already initialized.

## Access the vLab

Once the containers are started, you can access the vLab components:

1. Open a web browser.
2. Navigate to `http://localhost:8081`.
3. You will be able to visualize and interact with the data stored in the MongoDB Replica Set.

## Stop the Containers

To stop the vLab containers, use the following steps:

1. Open a terminal or command prompt.
2. Navigate to the root directory of the project.
3. Run the following command:

```bash
./vlab-orchestrator.sh stop
```

This command stops and removes the containers.

That's it! You can now build the Spring Boot app, start the containers, access the vLab components, and stop the containers when you're done.
