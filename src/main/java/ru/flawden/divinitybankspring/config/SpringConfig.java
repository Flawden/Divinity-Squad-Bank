package ru.flawden.divinitybankspring.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

/**
 * Configuration class for setting up Spring MVC and Thymeleaf in the application.
 * This class implements WebMvcConfigurer to configure resources, view resolvers, and controllers.
 *
 * @author Flawden
 * @version 1.0
 */
@Configuration
@ComponentScan("ru.flawden.divinitybankspring")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Configures resource handlers to serve static resources like CSS, JavaScript, and images.
     *
     * @param registry The registry to add resource handlers to.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/static/**")
                .addResourceLocations("classpath:/static/");
    }

    /**
     * Bean for configuring the Thymeleaf template resolver.
     * It specifies the location of the HTML templates.
     *
     * @return The configured SpringResourceTemplateResolver.
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    /**
     * Bean for configuring the SpringTemplateEngine.
     * Sets the template resolver and enables the Spring EL compiler.
     *
     * @return The configured SpringTemplateEngine.
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    /**
     * Configures the view resolvers for Thymeleaf.
     *
     * @param registry The registry to add view resolvers to.
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }

    /**
     * Adds simple automated view controllers for specific URLs.
     *
     * @param registry The registry to add view controllers to.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }


    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

}
