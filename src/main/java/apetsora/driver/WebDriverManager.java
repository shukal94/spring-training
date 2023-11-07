package apetsora.driver;

import org.openqa.selenium.WebDriver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WebDriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ApplicationContext context = new ClassPathXmlApplicationContext("services.xml");

    private WebDriverManager() {
    }

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            WebDriver driver = (WebDriver) context.getBean("webDriver");
            driverThreadLocal.set(driver);
        }
        return driverThreadLocal.get();
    }
}
