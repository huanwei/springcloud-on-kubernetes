package cn.harmonycloud.springcloud.zuul.filter

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.netflix.config.ConfigurationManager
import com.netflix.loadbalancer.ILoadBalancer
import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

import javax.servlet.RequestDispatcher

@Component
class MyFilter extends ZuulFilter {
    @Override
    String filterType() {
        return "route"
    }

    @Override
    int filterOrder() {
        return 11
    }

    @Override
    boolean shouldFilter() {
        return true
    }

    @Override
    Object run() {
        RequestContext context = RequestContext.getCurrentContext()
        String serviceId = context.get("serviceId")
        println(serviceId)
        def request = context.getRequest()
        def response = context.getResponse()

        String v = request.getParameter("version")
        if (v == null || v.equals(""))
            return null

        String uri = request.getRequestURI().split(context.getZuulRequestHeaders().get("x-forwarded-prefix"))[1];

        JSONObject result = JSONObject.parseObject(new RestTemplate().getForObject("http://10.10.102.53:8761/eureka/apps/" + context.get("serviceId").toString() + "/", String.class))
        JSONArray resultList = result.getJSONObject("application").getJSONArray("instance")
        StringBuffer servicesStr = new StringBuffer()
        for (Object instaceService : resultList) {
            instaceService = (JSONObject) instaceService
            String version = instaceService.getString("instanceId").split(":")[3]
            if (version.equals(v)) {
                if (servicesStr.length() != 0) {
                    servicesStr.append(",")
                }
                String ipAddress = instaceService.getString("ipAddr")
                servicesStr.append(ipAddress + ":" + instaceService.getJSONObject("port").getString("\$"))
            }
        }

        ConfigurationManager.getConfigInstance().setProperty(serviceId + ".ribbon.listOfServers", servicesStr.toString())
        String str = "http://" + LoadbalancerFactory.get(serviceId) + uri;
        println(str)
        context.setSendZuulResponse(false)
        RestTemplate rs = new RestTemplate();
        String val = rs.getForObject(str, String.class).toString()
        println(val)
        context.getResponse().getWriter().write(val)
        return null
    }
}
