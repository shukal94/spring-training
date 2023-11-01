package ch.shukalovi;

import ch.shukalovi.model.Order;
import ch.shukalovi.service.OrderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.SimpleThreadScope;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {"beans/services.xml"});
        ctx.registerShutdownHook();
        OrderService service = ctx.getBean(OrderService.class);
        ctx.getBean(OrderService.class);
        ctx.getBean(OrderService.class);
        ctx.getBean(OrderService.class);
//        service.createOrder(new Order(800));
//        service.createOrder(new Order(801));
//        service.createOrder(new Order(802));
//
//        System.exit(1);
//
//        service.createOrder(new Order(803));
//        service.createOrder(new Order(804));
//        service.createOrder(new Order(805));
//
//        ctx.close();
    }
}
