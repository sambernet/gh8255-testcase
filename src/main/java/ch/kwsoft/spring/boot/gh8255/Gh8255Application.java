package ch.kwsoft.spring.boot.gh8255;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Testcase for spring-boot issue 8255, see https://github.com/spring-projects/spring-boot/issues/8255
 * 
 * - fails in spring-boot 1.5.1.RELEASE 
 * - works as intended with spring-boot 1.4.4.RELEASE
 * 
 * @author Sam Bernet / https://github.com/sambernet
 */
@SpringBootApplication
public class Gh8255Application extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Gh8255Application.class).properties("management.security.roles:SYSOPS").run(args);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("secret").authorities("SYSOPS");
    }
    
}