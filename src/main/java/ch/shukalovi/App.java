package ch.shukalovi;

import ch.shukalovi.model.Order;
import ch.shukalovi.service.OrderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.SimpleThreadScope;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {"beans/services.xml"});
        ctx.getBeanFactory().registerScope("thread", new SimpleThreadScope());
        ctx.getBean(OrderService.class);
        ctx.getBean(OrderService.class);
        ctx.getBean(OrderService.class);

        new Thread(() -> {
            ctx.getBean(OrderService.class);
        }).start();
        OrderService service = ctx.getBean(OrderService.class);
        Order order = service.createOrder(new Order(800));
    }
}
