package cn.harmonycloud.springcloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huan on 2018/10/17.
 */
@RestController
@SpringBootApplication
public class BookApplication {

    @RequestMapping(value = "/say")
    public String say() {
        return "Say hello";
    }

    @RequestMapping(value = "/list")
    public String list() {
        return "list Spring in Action";
    }

    @RequestMapping(value = "/check")
    public String check() {
        return "check Spring in Action";
    }

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }
}
