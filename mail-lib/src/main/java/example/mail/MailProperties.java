package example.mail;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

/**
 * The type Mail properties.
 *
 * Alternatives to register this class as bean in the spring app context:
 * a) create a MailConfiguration and use @Bean;
 * b) create a MailConfiguration and use @EnableConfigurationProperties(MailProperties.class);
 */
@Component
@ConfigurationProperties(prefix = "mail")
@Getter
@Setter
@ToString
@Validated
public class MailProperties {

    /**
     * The Default subject.
     */
    @NotEmpty
    private String defaultSubject;

}
