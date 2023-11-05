package ch.shukalovi;

import ch.shukalovi.config.ApplicationConfig;
import ch.shukalovi.model.Order;
import ch.shukalovi.service.OrderService;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.SimpleThreadScope;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ctx.registerShutdownHook();
        //ctx.register(ApplicationConfig.class);
        //ctx.scan("ch.shukalovi");
        //ctx.refresh();

        OrderService service = ctx.getBean(OrderService.class);
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
