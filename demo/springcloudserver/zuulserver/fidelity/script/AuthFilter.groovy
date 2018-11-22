package cn.harmonycloud.springcloud.zuul.filter

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.apache.commons.lang3.StringUtils
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

/**
 *  网关用户鉴权过滤器
 */

@Component
class AuthFilter extends ZuulFilter {
    List<String> WHITE_URL_LIST = Arrays.asList(["/springcloud","users/auth/login", "validation", "getToken", "/clusters", "system/configs/trialtime", "cicd/trigger/webhookTrigger", "users/auth/token", "/testcallback"] as String[]);
    String allowOrigin = '*'

    @Override
    String filterType() {
        return "pre"
    }

    @Override
    int filterOrder() {
        return 2
    }

    @Override
    boolean shouldFilter() {
        return true
    }

    @Override
    Object run() {
        RequestContext context = RequestContext.currentContext
        HttpServletRequest request = context.getRequest()
        HttpServletResponse response = context.getResponse()

        def parameter = request.getParameter("type")
        if (parameter!=null && parameter.toString().equals("admin")){
            return null
        }

        // 不符合条件的，返回401 Unauthorized
        response.setStatus(401)
        context.setSendZuulResponse(false)
        return response
    }

}
