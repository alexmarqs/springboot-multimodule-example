package example.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * The type Logger configuration.
 */
@Configuration
public class LoggerConfiguration {

    /**
     * Logger logger.
     *
     * @param injectionPoint the injection point
     * @return the logger
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Logger logger(InjectionPoint injectionPoint){
        return LoggerFactory.getLogger(injectionPoint.getField().getDeclaringClass());
    }

}
