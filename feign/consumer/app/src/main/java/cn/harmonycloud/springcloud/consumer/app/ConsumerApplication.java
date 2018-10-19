package cn.harmonycloud.springcloud.consumer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Created by huan on 2018/10/19.
 */
@EnableFeignClients(basePackages = {"cn.harmonycloud.springcloud.consumer.api"})
@SpringBootApplication
public class ConsumerApplication {

    public static void main(String []args){
        SpringApplication.run(ConsumerApplication.class,args);
    }

}