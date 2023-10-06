package ch.shukalovi;

import ch.shukalovi.model.Order;
import ch.shukalovi.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {"beans/services.xml"});
        OrderService service = ctx.getBean(OrderService.class);
        Order order = service.createOrder(new Order(800));
    }
}
