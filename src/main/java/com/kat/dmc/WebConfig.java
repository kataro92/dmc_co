package com.kat.dmc;

import com.kat.dmc.rewrite.KatRewriteConfiguration;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.primefaces.webapp.filter.FileUploadFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import javax.servlet.DispatcherType;

@EnableWebMvc
@Configuration
@EnableCaching
@ComponentScan(basePackages = {
        "com.kat.dmc.controller",
        "com.kat.dmc.repository",
        "com.kat.dmc.rewrite"
})
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public KatRewriteConfiguration katRewriteConfiguration(){
        KatRewriteConfiguration katRewriteConfiguration = new KatRewriteConfiguration();
        return katRewriteConfiguration;
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Production");
            servletContext.setInitParameter("com.ocpsoft.pretty.DISABLE_SERVLET_3.0_SUPPORT", "true");
            servletContext.setInitParameter("javax.faces.STATE_SAVING_METHOD", "server");
            servletContext.setInitParameter("primefaces.THEME", "omega");
            servletContext.setInitParameter("primefaces.UPLOADER", "commons");
        };
    }

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    @Configuration
    public class MvcConfig extends WebMvcConfigurerAdapter {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry){
            registry.addResourceHandler("/**")
                    .addResourceLocations("/")
                    .setCachePeriod(0);
        }
    }

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        FileUploadFilter uploadFilter = new FileUploadFilter();
        registration.setFilter(uploadFilter);
        registration.setDispatcherTypes(DispatcherType.FORWARD);
        return registration;
    }

    @Bean
    public BirtView birtView(){
        return new BirtView();
    }

    @Bean public BeanNameViewResolver beanNameResolver(){
        return new BeanNameViewResolver();
    }

    @Bean
    protected BirtEngineFactory engine(){
        return new BirtEngineFactory() ;
    }

}
