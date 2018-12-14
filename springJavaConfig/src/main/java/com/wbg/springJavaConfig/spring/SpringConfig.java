package com.wbg.springJavaConfig.spring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

//@Configuration//声明当前类是一个配置类（相当于一个Spring的xml文件）
//@EnableWebMvc//若无此注解，WebMvcConfigurerAdapter无效
/**
 *
 * @ComponentScan 扫描（"xxx"）包下的@Service、@Controller、@Component、@Repository的类，并注册为Bean
 *相当于：<context:component-scan base-package="xxx" />
 */
//@ComponentScan("com.wbg.springJavaConfig.controller")
public class SpringConfig extends WebMvcConfigurerAdapter {
    /**
     * @Bean 相当Spring配置文件bean节点
     * 添加一个ViewResolver解析view 配置jsp
     * @return InternalResourceViewResolver
     * 这里的配置相当于：
     * <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
     *         <property name="viewClass" value="org.springframework.web.servlet.view.JstlView" />
     *         <property name="prefix" value="/WEB-INF/jsp/" />
     *         <property name="suffix" value=".jsp" />
     *     </bean>
     */
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

}
// class  implements WebApplicationInitializer WebInitializer
class WebInitializer  {
    /**
     * <context-param>
     *         <param-name>contextConfigLocation</param-name>
     *         <param-value>classpath:spring/spring-*.xml</param-value>
     *     </context-param>
     *     <listener>
     *         <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
     *     </listener>
     *
     *
     * <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
     *         <init-param>
     *             <param-name>contextConfigLocation</param-name>
     *             <param-value>classpath:spring-web.xml</param-value>
     *         </init-param>
     *
     * @param servletContext
     */
    public void onStartup(ServletContext servletContext){
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(SpringConfig.class);
        ctx.setServletContext(servletContext);
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher",new DispatcherServlet(ctx));
        dynamic.addMapping("/");
        dynamic.setLoadOnStartup(1);
    }

}
