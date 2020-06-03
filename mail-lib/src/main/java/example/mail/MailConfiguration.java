package example.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * The type Mail configuration.
 */
@Configuration
@ComponentScan("example.mail")
//@EnableConfigurationProperties(MailModuleProperties.class) -> alternative b)
public class MailConfiguration {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(MailConfiguration.class);

    /*@Bean -> to use instead of @ComponentScan
    public MailService mailService(){
        return new MailServiceImpl();
    }*/

    /*@Bean -> alternative a)
    @Validated
    @ConfigurationProperties(prefix = "mail")
    public MailProperties mailProperties(){
        return new MailProperties();
    }*/

    /**
     * Post construct.
     */
    @PostConstruct
    public void postConstruct() {
        logger.info("Mail module loaded!");
    }

}
