package cn.harmonycloud.springcloud.provider;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huan on 2018/10/19.
 */
@RestController
@RequestMapping("/api")
public class CalculateController {

    @PostMapping("/add")
    public Integer add(@RequestParam Integer a, @RequestParam Integer b){
        return a+b;
    }

    @PostMapping("/subtract")
    public Integer subtract(@RequestParam Integer a,@RequestParam Integer b){
        return a-b;
    }

}
