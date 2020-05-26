package example.api;

import example.mail.EnableMail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * The Web application boot.
 *
 * Notes:
 * - The security module is automatically imported by the auto configuration if the property security.enabled = true;
 * - The mail module is imported using the annotation @EnableMail. However, it can also be imported directly via @Import(MailConfiguration.class);
 */
@SpringBootApplication
@EnableMail
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

}
