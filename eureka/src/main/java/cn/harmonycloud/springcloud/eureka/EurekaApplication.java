package cn.harmonycloud.springcloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by huan on 2018/10/15.
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(EurekaApplication.class, args);
        System.out.println( "Eureka server started." );
    }
}
