# Spring Boot multi-module application example  
This project shows you how can you modularize a Spring Boot application with Maven and how can you use some of the modules as libraries in other Spring Boot applications. Note that the project structure of this example is intended to be used to learn and play with these concepts. When you are building a real application you should think what makes sense for your project. For small projects, do not forget the KISS principle. For complex projects, a well-organized application is a must. In any approach, you need to always take into consideration the maintainability of your project.

**Overview about Spring Boot application context:**

As you should know the Spring application context contains all the beans that makes up the application (e.g. controllers, services, repositories, etc). You can register beans to your Spring Boot application context using the annotation `@Configuration`  in any class in order to act as a beans factory.  The annotated classes can contain factory methods annotated with  `@Bean`  whose return objects are automatically added to the application context by Spring. Another way to register  beans is using the annotation `@ComponentScan(basePackages="base.package.to.search")` along with `@Configuration` to specify the packages that you want to be scanned. It will look through all classes in the specified base package (if nothing is specified it will search the current package and all its sub packages) and load an instance of each class that is annotated with one of Spring’s stereotype annotations (e.g. `@Component`, `@Service`, etc) into the application context.

The `@SpringBootApplication` annotation used in the entry point Spring Boot application class is equivalent to using `@Configuration`, `@EnableAutoConfiguration` and `@ComponentScan`.   The auto-configuration feature allows a `@Configuration` class to be automatically discovered by Spring. Additional `@Conditional` annotations can be used to constrain when the auto-configuration should apply. 

**Multi-module project structure:**

Taking the above overview into account, let's look and take some conclusions about this project structure! As you can see in the code,  the Spring Boot entry point application class ([WebApplication.java](/web/src/main/java/example/api/WebApplication.java)) is defined in the *web module* (responsible for the application boot) and it is under the package `example.api`. Therefore, all the Spring configurations and beans/components defined in that package (and in its sub-packages) will be detected and taken into account for the application context.  The *core module* and the *common module* are examples of this. On the other hand, the *mail-lib module* and the *security-lib module* are in different packages (`example.mail` /` example.security`) and for that reason we need to have other strategies for importing the Spring configurations and components into the correct application context. These modules can be easily adapted for use in other applications (e.g. imagine that you need to have some libraries shared across multiple applications in your organization/company). Options for importing the modules:

a) Using `@Import` annotation
- This annotation is used to import specific configuration classes and will import all beans that come with them (declared by `@ComponentScan` or `@Bean` annotations). In the *mail-lib module* a custom annotation `@EnableMail`  is being used as a wrapper to add the `@Import(MailConfiguration.class)` annotation that imports our configuration class. This strategy is mainly used for modules that contain business logic.

b) Using Auto-Configuration
- If you want to load a module automatically instead of hard-coding the import, you can use the Spring Boot’s auto-configuration feature. To make the configuration an auto-configuration, you must list it in the file  `META-INF/spring.factories` (check the [spring.factories](/security-lib/src/main/resources/META-INF/spring.factories) file in the *security-lib module*). Spring Boot searches through all  `spring.factories`  files it finds on the classpath and loads the configurations declared within. This strategy is mainly used for importing technical modules (like security or logging/monitoring features).

**System requirements**  
- JDK 11      
- Maven      
- Docker  
      
## How to build and run   
*With docker*: 

Execute the provided script [run-docker.sh](run-docker.sh) or run directly the following commands to build and run the docker image:  `docker build -t app-example-image . && docker run --name app-example-container -p 8080:8080 app-example-image`   

 *Without docker*:
 
 Build and generate the artifact file with the maven command `mvn clean package` and then run the maven spring boot plugin     
 `mvn spring-boot:run` (or the java command `java -jar <jar file location>`).  
   
The application will be accessible at [http:localhost:8080](http:localhost:8080) (note that the configured application context path is /api).


## References

[https://docs.spring.io/spring-boot/docs/2.1.13.RELEASE/reference/html/using-boot-using-springbootapplication-annotation.html](https://docs.spring.io/spring-boot/docs/2.1.13.RELEASE/reference/html/using-boot-using-springbootapplication-annotation.html)

[https://docs.spring.io/spring-boot/docs/2.1.13.RELEASE/reference/html/using-boot-auto-configuration.html](https://docs.spring.io/spring-boot/docs/2.1.13.RELEASE/reference/html/using-boot-auto-configuration.html)

[https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/boot-features-developing-auto-configuration.html](https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/boot-features-developing-auto-configuration.html)

[https://reflectoring.io/spring-boot-starter/](https://reflectoring.io/spring-boot-starter/)

[https://reflectoring.io/spring-boot-modules/](https://reflectoring.io/spring-boot-modules/)

[https://www.baeldung.com/spring-boot-configuration-metadata](https://www.baeldung.com/spring-boot-configuration-metadata)

[https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-configuration-metadata.html#configuration-metadata-annotation-processor](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-configuration-metadata.html#configuration-metadata-annotation-processor)

[https://plugins.jetbrains.com/plugin/10229-spring-assistant](https://plugins.jetbrains.com/plugin/10229-spring-assistant)
