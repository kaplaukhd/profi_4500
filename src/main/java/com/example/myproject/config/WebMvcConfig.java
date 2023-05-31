package com.example.myproject.config;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(3L));
        factory.setMaxRequestSize(DataSize.ofMegabytes(3L));
        return factory.createMultipartConfig();
    }


    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .favorPathExtension(false)
                .defaultContentType(MediaType.TEXT_HTML)
                .mediaType("html", MediaType.TEXT_HTML)
                .mediaType("css", MediaType.valueOf("text/css"));
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/profile").setViewName("profile");
        registry.addViewController("/register").setViewName("register");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources", "classpath:/resources/",
                        "classpath:/static/", "classpath:/resources/**");
    }

    @Bean
    public MappingJackson2HttpMessageConverter jacksonConverter() {
        MappingJackson2HttpMessageConverter mc =
                new MappingJackson2HttpMessageConverter();
        List<MediaType> supportedMediaTypes =
                new ArrayList<>(mc.getSupportedMediaTypes());
        supportedMediaTypes
                .add(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        supportedMediaTypes.add(
                MediaType.valueOf("application/vnd.spring-boot.actuator.v2+json"));
        mc.setSupportedMediaTypes(supportedMediaTypes);
        return mc;

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jacksonConverter());
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new BufferedImageHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        converters.add(new ResourceHttpMessageConverter());
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        return templateResolver;

    }
}
