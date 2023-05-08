package com.dsu.arslan.coursework.config;

import com.dsu.arslan.coursework.endpoint.AuthorsEndpoint;
import com.dsu.arslan.coursework.endpoint.BooksEndpoint;
import com.dsu.arslan.coursework.endpoint.GenresEndpoint;
import com.dsu.arslan.coursework.endpoint.GreetingEndpoint;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {

//    public static final String NAMESPACE_GREETING = "http://arslan.dsu.com/coursework/ws/greeting";

    // регистрируем точку в контексте, где будут находиться веб сервисы
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "greeting")
    public DefaultWsdl11Definition defaultWsdl11Definition() {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("GreetingPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(GreetingEndpoint.NAMESPACE_URL);
        wsdl11Definition.setSchema(xsdGreetingSchema());
        return wsdl11Definition;
    }

    @Bean("greetingSchema")
    public XsdSchema xsdGreetingSchema() {
        return new SimpleXsdSchema(new ClassPathResource("ws/greeting.xsd"));
    }

    @Bean(name = "books")
    public DefaultWsdl11Definition booksWsdlDefinition() {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("BooksPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(BooksEndpoint.NAMESPACE_URL);
        wsdl11Definition.setSchema(xsdBooksSchema());
        return wsdl11Definition;
    }

    @Bean("booksSchema")
    public XsdSchema xsdBooksSchema() {
        return new SimpleXsdSchema(new ClassPathResource("ws/books.xsd"));
    }

    @Bean(name = "authors")
    public DefaultWsdl11Definition authorsWsdlDefinition() {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("AuthorsPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(AuthorsEndpoint.NAMESPACE_URL);
        wsdl11Definition.setSchema(xsdAuthorsSchema());
        return wsdl11Definition;
    }

    @Bean("authorsSchema")
    public XsdSchema xsdAuthorsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("ws/authors.xsd"));
    }

    @Bean(name = "genres")
    public DefaultWsdl11Definition genresWsdlDefinition() {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("GenresPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(GenresEndpoint.NAMESPACE_URL);
        wsdl11Definition.setSchema(xsdGenresSchema());
        return wsdl11Definition;
    }

    @Bean("genresSchema")
    public XsdSchema xsdGenresSchema() {
        return new SimpleXsdSchema(new ClassPathResource("ws/genres.xsd"));
    }

}
