package ch.shukalovi;

import ch.shukalovi.config.ApplicationConfig;
import ch.shukalovi.model.Order;
import ch.shukalovi.service.OrderService;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.core.io.ClassPathResource;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        // new AnnotationConfigApplicationContext(ApplicationConfig.class); an old API, refreshes
        ctx.registerShutdownHook();
        ctx.register(ApplicationConfig.class);
        ctx.getEnvironment().addActiveProfile("test");
        ctx.scan("ch.shukalovi");
        ctx.refresh();

        OrderService service = ctx.getBean(OrderService.class);
        service.readResource();
        service.getValidator().testBPP();

        service.createOrder(new Order(800));
        service.createOrder(new Order(801));
        service.createOrder(new Order(802));

        System.exit(1);

        service.createOrder(new Order(803));
        service.createOrder(new Order(804));
        service.createOrder(new Order(805));

        ctx.close();
    }
}
