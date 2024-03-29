package com.codegym.javamyprojectmodule2;

import com.codegym.javamyprojectmodule2.formatter.*;
import com.codegym.javamyprojectmodule2.service.*;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class JavaMyProjectModule2Application {

    public static void main(String[] args) {
        SpringApplication.run(JavaMyProjectModule2Application.class, args);
    }


    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("ValidationMessages");
        return messageSource;
    }

    @Configuration
    class ApplicationConfig implements WebMvcConfigurer, ApplicationContextAware {

        private ApplicationContext appContext;

        @Override
        public void addFormatters(FormatterRegistry registry) {
            registry.addFormatter(new ClubFormatter(appContext.getBean(ClubService.class)));
            registry.addFormatter(new TournamentsFormatter(appContext.getBean(TournamentsService.class)));
            registry.addFormatter(new NationalFormatter(appContext.getBean(NationalService.class)));
            registry.addFormatter(new PositionFormatter(appContext.getBean(PositionService.class)));
            registry.addFormatter(new PlayerFormatter(appContext.getBean(PlayerService.class)));
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            appContext = applicationContext;
        }
    }
}
