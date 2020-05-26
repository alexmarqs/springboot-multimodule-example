echo " *** Build && Run Docker Image for the application *** "
docker build -t app-image . && docker run --name app-container -p 8080:8080 app-image
# if you want to hardcode the build artifact location with the build command, run:
# docker build --build-arg JAR_FILE=web/target/*.jar -t app-image .