package org.codegym.demomvc.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.Locale;

@Configuration  // Đánh dấu đây là một lớp cấu hình cho Spring
@EnableWebMvc  // Kích hoạt các tính năng của Spring MVC
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "org.codegym.demomvc")  // Quét các package để tìm các component như @Controller, @Service, @Repository
public class WebConfig implements WebMvcConfigurer {

    // Cấu hình resolver cho Thymeleaf, xác định đường dẫn tới các template
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");  // Đường dẫn tới thư mục chứa các file HTML
        templateResolver.setSuffix(".html");  // Đuôi của các template sẽ là .html
        templateResolver.setTemplateMode(TemplateMode.HTML);  // Chế độ của template là HTML
        templateResolver.setCharacterEncoding("UTF-8");  // Thiết lập encoding là UTF-8 để hiển thị tiếng Việt hoặc ký tự đặc biệt
        return templateResolver;
    }

    // Cấu hình template engine cho Thymeleaf, sử dụng templateResolver đã được cấu hình ở trên
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());  // Liên kết với resolver để xử lý các template
        return templateEngine;
    }

    // Cấu hình view resolver cho Thymeleaf, chịu trách nhiệm render các view
    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());  // Sử dụng template engine đã được cấu hình
        viewResolver.setCharacterEncoding("UTF-8");  // Đảm bảo view sử dụng đúng encoding UTF-8
        return viewResolver;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();  // Tạo RestTemplate để gửi request đến API
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("en"));
        return resolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
