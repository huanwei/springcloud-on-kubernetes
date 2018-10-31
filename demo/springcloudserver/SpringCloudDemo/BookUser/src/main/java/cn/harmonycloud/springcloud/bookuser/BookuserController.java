package cn.harmonycloud.springcloud.bookuser;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookuserController {
    @Autowired
    private BookuserApi bookuserApi;

    @Value("${server.port}")
    private int port;

    @RequestMapping("/getbooklist")
    @HystrixCommand(fallbackMethod = "fallback")
    public String getBooksList() {
        return "feign " + port + ": " + bookuserApi.getBooksList();
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping("/get")
    public String getBook() {
        return "feign " + port + ": " + bookuserApi.getBook();
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping("/price")
    public String calculate(double price, int count) {
        return "feign " + port + ": " + bookuserApi.calculate(price, count);
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping("/zero")
    public String zero() {
        return "feign " + port + ": " + bookuserApi.dividezero();
    }

    public String fallback() {
        return "fallback()";
    }

    public String fallback(double price, int count) {
        return "price fallback()";
    }
}
