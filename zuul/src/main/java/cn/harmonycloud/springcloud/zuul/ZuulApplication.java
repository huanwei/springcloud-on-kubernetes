package cn.harmonycloud.springcloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by huan on 2018/10/17.
 */

@EnableZuulProxy
@SpringBootApplication
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
        System.out.println("Zuul gateway started");
    }
}
