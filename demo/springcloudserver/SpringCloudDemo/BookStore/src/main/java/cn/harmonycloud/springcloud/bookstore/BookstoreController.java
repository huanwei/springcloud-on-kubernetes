package cn.harmonycloud.springcloud.bookstore;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookstoreController {
    @Value("${server.port}")
    private int port;

    @RequestMapping("/booklist")
    @HystrixCommand(fallbackMethod = "fallback")
    public String getBooksList() {
        return "getBooksList(" + port + ")";
    }

    @RequestMapping("/get")
    @HystrixCommand(fallbackMethod = "fallback")
    public String getBook() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "getBook(" + port + ")";
    }

    @RequestMapping("/price")
    @HystrixCommand(fallbackMethod = "fallback")
    public double calculate(double price, int count) {
        return price * count;
    }

    @RequestMapping("/zero")
    @HystrixCommand(fallbackMethod = "fallback")
    public int dividezero() {
        return 1 / 0;
    }

    public String fallback() {
        return "端口号为" + port + "的服务降级";
    }

    public double fallback(double price, int count) {
        return 0d;
    }
}
