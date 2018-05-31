package com.kat.dmc;

import com.kat.dmc.rewrite.KatRewriteConfiguration;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
@EnableCaching
@ComponentScan(basePackages = {
        "com.kat.dmc.controller",
        "com.kat.dmc.repository",
        "com.kat.dmc.rewrite"
})
public class WebConfig {

    @Bean
    public KatRewriteConfiguration katRewriteConfiguration(){
        KatRewriteConfiguration katRewriteConfiguration = new KatRewriteConfiguration();
        return katRewriteConfiguration;
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            servletContext.setInitParameter("primefaces.THEME", "cupertino");
            servletContext.setInitParameter("com.ocpsoft.pretty.DISABLE_SERVLET_3.0_SUPPORT", "true");
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

}
