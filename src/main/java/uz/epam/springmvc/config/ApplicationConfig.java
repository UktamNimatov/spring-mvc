package uz.epam.springmvc.config;

import jdk.vm.ci.meta.Local;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.SessionThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;
import uz.epam.springmvc.converter.StringToEnumConverter;
import uz.epam.springmvc.interceptor.LoggingInterceptor;

import java.util.Locale;

@Configuration
@ComponentScan(basePackages = "uz.epam")
public class ApplicationConfig extends WebMvcConfigurationSupport {


    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("css/**", "images/**")
                .addResourceLocations("classpath:/static/css/", "classpath:/static/images/");
    }

    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);

        viewResolver.setOrder(2);
        return viewResolver;
    }

    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToEnumConverter());
    }

    @Override
    protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(5000);
        configurer.setTaskExecutor(mvcTaskExecutor());
    }

    @Bean
    public AsyncTaskExecutor mvcTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("hplusapp-thread-");
        return threadPoolTaskExecutor;
    }

    @Bean
    public XmlViewResolver xmlViewResolver() {
        XmlViewResolver viewResolver = new XmlViewResolver();
        viewResolver.setLocation(new ClassPathResource("views.xml"));
        viewResolver.setOrder(1);
        return viewResolver;
    }

//    @Bean
//    public ResourceBundleViewResolver resourceBundleViewResolver() {
//        ResourceBundleViewResolver resourceBundleViewResolver = new ResourceBundleViewResolver();
//        resourceBundleViewResolver.setBasename("views");
//        return resourceBundleViewResolver;
//    }


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/*");
        registry.addInterceptor(new ThemeChangeInterceptor());
        registry.addInterceptor(new LocaleChangeInterceptor());
    }

    @Bean
    public ThemeResolver themeResolver() {
//        SessionThemeResolver themeResolver = new SessionThemeResolver();
        CookieThemeResolver themeResolver = new CookieThemeResolver();
        themeResolver.setCookieName("theme");
        themeResolver.setDefaultThemeName("client-theme1");
        return themeResolver;
    }

    @Bean
    public LocaleResolver localeResolver() {
//        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        CookieLocaleResolver sessionLocaleResolver = new CookieLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.US);
        sessionLocaleResolver.setCookieName("locale");
//        sessionLocaleResolver.setDefaultLocale(Locale.US);
//        sessionLocaleResolver.setLocaleAttributeName("locale");
        return sessionLocaleResolver;
    }

}
