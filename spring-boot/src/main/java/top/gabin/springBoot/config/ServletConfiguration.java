package top.gabin.springBoot.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.gabin.springBoot.servlet.MyServlet;

import javax.servlet.Servlet;

@Configuration
public class ServletConfiguration {

    @Bean
    ServletRegistrationBean<Servlet> servlet() {
        return new ServletRegistrationBean<>(new MyServlet());
    }

}
