package ru.flawden.divinitybankspring.config;

import jakarta.servlet.Filter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ru.flawden.divinitybankspring.security.SecurityConfig;

/**
 * Initializes the DispatcherServlet for the Spring MVC application and sets up the configuration classes.
 * Also registers a filter to handle hidden HTTP methods such as PUT and DELETE.
 *
 *  @author Flawden
 *  @version 1.0
 */
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Returns the root application context configuration classes.
     * Currently, returns null as there is no root context configuration.
     *
     * @return null since no root configuration is required.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    /**
     * Specifies the configuration classes for the servlet application context.
     *
     * @return An array of configuration classes including SpringConfig, SpringDataJpaConfig, and SecurityConfig.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class, SpringDataJpaConfig.class, SecurityConfig.class};
    }

    /**
     * Maps the DispatcherServlet to the root URL pattern ("/").
     *
     * @return An array with a single mapping for the servlet, mapping all requests to "/".
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * Registers any additional filters and performs servlet context initialization.
     *
     * @param servletContext The ServletContext to initialize.
     * @throws ServletException If an error occurs during initialization.
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        registerHiddenFieldFilter(servletContext);
    }

    /**
     * Registers the {@link HiddenHttpMethodFilter}, which enables support for HTTP methods like PUT and DELETE.
     *
     * @param servletContext The ServletContext to register the filter with.
     */
    private void registerHiddenFieldFilter(ServletContext servletContext) {
        servletContext.addFilter("hiddenHttpMethodFilter",
                (Filter) new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null, true, "/*");
    }

}
