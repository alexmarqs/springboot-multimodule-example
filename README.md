## Spring Boot multi-module application example  
This project shows you how can you modularize a Spring Boot application and how can you use some of the modules as libraries in other Spring Boot applications.

### System requirements  
- JDK 11      
- Maven      
- Docker  
      
### Instructions to build and run the example locally     
*With docker*: 

Execute the provided script [run-docker.sh](run-docker.sh) or run directly the following commands to build and run the docker image:  `docker build -t app-example-image . && docker run --name app-example-container -p 8080:8080 app-example-image`   

 *Without docker*:
 
 Build and generate the artifact file with the maven command `mvn clean package` and then run the maven spring boot plugin     
 `mvn spring-boot:run` (or the java command `java -jar <jar file location>`).  
   
The application will be accessible at [http:localhost:8080](http:localhost:8080) (not that the configured application context path is /api).
