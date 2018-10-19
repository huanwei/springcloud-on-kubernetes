package cn.harmonycloud.springcloud.consumer.app;

import cn.harmonycloud.springcloud.consumer.api.CalculateApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huan on 2018/10/19.
 */
@RestController
public class ConsumerController {

    @Autowired
    CalculateApi calculateApi;

    @RequestMapping("/test")
    public String test() {
        Integer result = calculateApi.add(1, 2);
        System.out.println("the result is " + result);
        return "success, the result is " + result;
    }

}