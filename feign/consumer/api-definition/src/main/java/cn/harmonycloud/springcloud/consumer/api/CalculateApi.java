package cn.harmonycloud.springcloud.consumer.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by huan on 2018/10/19.
 */
@FeignClient(value = "calculate",path = "/api")
public interface CalculateApi {

    @PostMapping(path = "/add")
    Integer add(@RequestParam("a") Integer a, @RequestParam("b") Integer b);

    @PostMapping(path = "/subtract")
    Integer subtract(@RequestParam("a") Integer a,@RequestParam("b") Integer b);

}
