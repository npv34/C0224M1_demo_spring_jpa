package org.codegym.demomvc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Lớp WebInitializer để cấu hình DispatcherServlet cho ứng dụng Spring MVC.
 * Kế thừa từ AbstractAnnotationConfigDispatcherServletInitializer để thiết lập các cấu hình
 * cho ứng dụng web Spring.
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Cung cấp các lớp cấu hình gốc cho Spring (Root Config).
     * Đây là nơi cấu hình các thành phần không liên quan đến web, chẳng hạn như JPA.
     *
     * @return mảng các lớp cấu hình gốc.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { JpaConfig.class };  // Cung cấp lớp cấu hình JPA
    }

    /**
     * Cung cấp các lớp cấu hình cho DispatcherServlet (Servlet Config).
     * Đây là nơi cấu hình các thành phần web, chẳng hạn như MVC cấu hình.
     *
     * @return mảng các lớp cấu hình servlet.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };  // Cung cấp lớp cấu hình Spring MVC
    }

    /**
     * Cung cấp các mapping cho DispatcherServlet.
     * Đây là các đường dẫn mà DispatcherServlet sẽ xử lý.
     *
     * @return mảng các đường dẫn servlet mapping.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };  // DispatcherServlet sẽ xử lý tất cả các request đến ứng dụng
    }
}
