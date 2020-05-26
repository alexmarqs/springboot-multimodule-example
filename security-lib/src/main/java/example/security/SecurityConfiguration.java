package example.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.PostConstruct;

/**
 * The type Security configuration.
 */
@Configuration
@ConditionalOnProperty(name = "security.enabled", havingValue = "true")
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers("/v1/customer/guest/**").hasRole("GUEST")
                .antMatchers("/v1/customer/admin/**").hasRole("ADMIN")
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("GUEST");
        auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("ADMIN");
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**");
    }

    /**
     * Post constructor.
     */
    @PostConstruct
    public void postConstructor() {
        logger.info("Security module loaded!");
    }

}
