package cn.harmonycloud.springcloud.zuul.api;

import cn.harmonycloud.springcloud.zuul.event.RefreshRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by huan on 2018/10/17.
 */
@RestController
public class ZuulApi {

    @RequestMapping("/healthz")
    public String healthz(){
        return "ok";
    }

    @Autowired
    RefreshRouteService refreshRouteService;

    @RequestMapping("/refreshRoute")
    public String refreshRoute() {
        refreshRouteService.refreshRoute();
        return "refreshRoute";
    }

    @Autowired
    ZuulHandlerMapping zuulHandlerMapping;

    @RequestMapping("/watchRoute")
    public String watchRoute() {
        Map<String, Object> handlerMap = zuulHandlerMapping.getHandlerMap();
        return "watchRoute: "+ handlerMap.toString();
    }
}
