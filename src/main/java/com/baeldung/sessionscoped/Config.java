package com.baeldung.sessionscoped;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.Ordered;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class Config implements WebMvcConfigurer {

    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "redirect:/form" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
    }

//    @Bean
//    @Scope(
//        value = WebApplicationContext.SCOPE_SESSION, 
//        proxyMode = ScopedProxyMode.TARGET_CLASS)
//    public TodoList todos() {
//        return new TodoList();
//    }

    @Bean
    public List<Category> allCategories() {
        return Category.ALL_CATEGORIES;
    }
}
