

# Ejemplo de aplicación multi-módulo con Spring Boot  
Este proyecto te muestra cómo modularizar una aplicación Spring Boot con Maven y cómo utilizar algunos de los módulos como bibliotecas en otras aplicaciones Spring Boot. Ten en cuenta que la estructura del proyecto de este ejemplo está destinada a ser utilizada para aprender y experimentar con estos conceptos. Cuando estés construyendo una aplicación real, debes pensar en lo que tiene sentido para tu proyecto. Para proyectos pequeños, no olvides el principio KISS. Para proyectos complejos, una aplicación bien organizada es imprescindible. En cualquier enfoque, debes tener siempre en consideración la mantenibilidad de tu proyecto.

**Resumen sobre el contexto de aplicación de Spring Boot:**

Como deberías saber, el contexto de aplicación de Spring contiene todos los beans que componen la aplicación (p. ej., controladores, servicios, repositorios, etc.). Puedes registrar beans en el contexto de tu aplicación Spring Boot utilizando la anotación `@Configuration`  en cualquier clase para que actúe como una fábrica de beans.  Las clases anotadas pueden contener métodos de fábrica anotados con  `@Bean`  cuyos objetos devueltos son añadidos automáticamente al contexto de aplicación por Spring. Otra forma de registrar beans es utilizando la anotación `@ComponentScan(basePackages="base.package.to.search")` junto con `@Configuration` para especificar los paquetes que deseas que sean escaneados. Buscará en todas las clases del paquete base especificado (si no se especifica nada, buscará en el paquete actual y todos sus subpaquetes) y cargará una instancia de cada clase que esté anotada con una de las anotaciones de estereotipo de Spring (p. ej., `@Component`, `@Service`, etc.) en el contexto de aplicación.

La anotación `@SpringBootApplication` utilizada en la clase principal de entrada de la aplicación Spring Boot es equivalente a utilizar `@Configuration`, `@EnableAutoConfiguration` y `@ComponentScan`.   La función de auto-configuración permite que una clase `@Configuration` sea descubierta automáticamente por Spring. Se pueden utilizar anotaciones `@Conditional` adicionales para restringir cuándo debe aplicarse la auto-configuración. 

**Estructura del proyecto multi-módulo:**

Teniendo en cuenta el resumen anterior, ¡echemos un vistazo y saquemos algunas conclusiones sobre esta estructura de proyecto! Como puedes ver en el código,  la clase principal de entrada de la aplicación Spring Boot ([WebApplication.java](/web/src/main/java/example/api/WebApplication.java)) se define en el *módulo web* (responsable del arranque de la aplicación) y se encuentra bajo el paquete `example.api`. Por lo tanto, todas las configuraciones de Spring y los beans/componentes definidos en ese paquete (y en sus subpaquetes) serán detectados y tenidos en cuenta para el contexto de aplicación.  El *módulo core* y el *módulo common* son ejemplos de esto. Por otro lado, el *módulo mail-lib* y el *módulo security-lib* están en paquetes diferentes (`example.mail` /` example.security`) y por esa razón necesitamos tener otras estrategias para importar las configuraciones y componentes de Spring al contexto de aplicación correcto. Estos módulos pueden adaptarse fácilmente para su uso en otras aplicaciones (p. ej., imagina que necesitas tener algunas bibliotecas compartidas entre múltiples aplicaciones en tu organización/empresa). Opciones para importar los módulos:

a) Utilizando la anotación `@Import`
- Esta anotación se utiliza para importar clases de configuración específicas e importará todos los beans que las acompañen (declarados por las anotaciones `@ComponentScan` o `@Bean`). En el *módulo mail-lib* se utiliza una anotación personalizada `@EnableMail`  como envoltorio para añadir la anotación `@Import(MailConfiguration.class)` que importa nuestra clase de configuración. Esta estrategia se utiliza principalmente para módulos que contienen lógica de negocio.

b) Utilizando la Auto-Configuración
- Si deseas cargar un módulo automáticamente en lugar de codificar la importación de forma estática, puedes utilizar la función de auto-configuración de Spring Boot. Para que la configuración sea una auto-configuración, debes listarla en el archivo  `META-INF/spring.factories` (consulta el archivo [spring.factories](/security-lib/src/main/resources/META-INF/spring.factories) en el *módulo security-lib*). Spring Boot busca en todos los archivos  `spring.factories`  que encuentra en el classpath y carga las configuraciones declaradas en ellos. Esta estrategia se utiliza principalmente para importar módulos técnicos (como características de seguridad o de registro/monitoreo).

**Requisitos del sistema**  
- JDK 11      
- Maven      
- Docker  
      
## Cómo compilar y ejecutar   
*Con docker*: 

Ejecuta el script proporcionado [run-docker.sh](run-docker.sh) o ejecuta directamente los siguientes comandos para compilar y ejecutar la imagen docker:  `docker build -t app-example-image . && docker run --name app-example-container -p 8080:8080 app-example-image`   

 *Sin docker*:
 
 Compila y genera el archivo de artefacto con el comando maven `mvn clean package` y luego ejecuta el plugin de spring boot de maven     
 `mvn spring-boot:run` (o el comando java `java -jar <jar file location>`).  
   
La aplicación será accesible en [http:localhost:8080](http:localhost:8080) (ten en cuenta que la ruta de contexto de aplicación configurada es /api).


## Referencias

[https://docs.spring.io/spring-boot/docs/2.1.13.RELEASE/reference/html/using-boot-using-springbootapplication-annotation.html](https://docs.spring.io/spring-boot/docs/2.1.13.RELEASE/reference/html/using-boot-using-springbootapplication-annotation.html)

[https://docs.spring.io/spring-boot/docs/2.1.13.RELEASE/reference/html/using-boot-auto-configuration.html](https://docs.spring.io/spring-boot/docs/2.1.13.RELEASE/reference/html/using-boot-auto-configuration.html)

[https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/boot-features-developing-auto-configuration.html](https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/boot-features-developing-auto-configuration.html)

[https://reflectoring.io/spring-boot-starter/](https://reflectoring.io/spring-boot-starter/)

[https://reflectoring.io/spring-boot-modules/](https://reflectoring.io/spring-boot-modules/)

[https://www.baeldung.com/spring-boot-configuration-metadata](https://www.baeldung.com/spring-boot-configuration-metadata)

[https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-configuration-metadata.html#configuration-metadata-annotation-processor](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-configuration-metadata.html#configuration-metadata-annotation-processor)

[https://plugins.jetbrains.com/plugin/10229-spring-assistant](https://plugins.jetbrains.com/plugin/10229-spring-assistant)
