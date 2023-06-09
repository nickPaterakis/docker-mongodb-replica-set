#!/bin/bash

# Function to build the Country-Info app
build_app() {
    echo "Building the Spring Boot app..."
    docker build -t country-info .
    echo "Spring Boot app built successfully!"
}

# Function to start the containers
start_containers() {
    echo "Starting the containers..."
    docker-compose up -d --build
    echo "Containers started successfully!"

    echo "Waiting for MongoDB instances to be ready..."
    until docker exec -it mongo1 mongosh --eval "print(\"MongoDB is ready\")" >/dev/null 2>&1; do
        sleep 2
    done
    echo "MongoDB is ready!"

    echo "Checking replica set status..."

    RS_NAME=$(docker exec mongo1 mongosh --quiet --eval "rs.isMaster().setName")

    if [ "$RS_NAME" != "rs0" ]; then
        echo "Initializing the replica set..."
        docker exec mongo1 mongosh --eval "rs.initiate({_id: 'rs0', members: [{_id: 0, host: 'mongo1:27017'}, {_id: 1, host: 'mongo2:27017'}, {_id: 2, host: 'mongo3:27017'}]})"

        docker exec mongo1 mongosh --eval "rs.status()"
        echo "Replica set initialized!"
    else
        echo "Replica set is already initialized. Skipping initialization."
    fi
}

# Function to stop the containers
stop_containers() {
    echo "Stopping the containers..."
    docker-compose down
    echo "Containers stopped successfully!"
}

# Main script logic
case $1 in
    "start")
        start_containers
        ;;
    "build")
        build_app
        ;;
    "stop")
        stop_containers
        ;;
    *)
        echo "Usage: ./vlab-orchestrator.sh [start | build | stop]"
        ;;
esac