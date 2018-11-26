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
//        RequestContext context = RequestContext.currentContext
//        HttpServletRequest request = context.getRequest()
//        HttpServletResponse response = context.getResponse()
//
//        判断是否开启sso，开启则不执行拦截器的逻辑
//         TODO 此处可以使用http请求查看sso是否开启
//        if (SsoClient.isOpen()) {
//            return null;
//        }
//         设置跨域访问header信息
//        if (StringUtils.isNotBlank(allowOrigin)) {
//            String origin = allowOrigin;
//            String requestOrigin = request.getHeader("Origin");
//            if (StringUtils.isNotBlank(requestOrigin)
//                    && (allowOrigin.equals("*") || allowOrigin.indexOf(requestOrigin) > -1)) {
//                origin = requestOrigin;
//            }
//            response.setHeader("Access-Control-Allow-Origin", origin);
//            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, PATCH");
//            response.setHeader("Access-Control-Max-Age", "1200");
//            response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Content-Type");
//            response.setHeader("Access-Control-Allow-Credentials", "true");
//        }
//        String httpMethod = request.getMethod();
//        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(httpMethod)) {
//            return null;
//        }
//         获取请求的URL
//        String url = request.getRequestURI();
//        路径包含openapi的不需要验证是否登陆，oam task定时任务没有用户
//        if (url.indexOf("/openapi/") > -1) {
//            return null;
//        }
//         判断是否为白名单中的请求地址，是直接返回验证成功
//        for (String whiteUrl : WHITE_URL_LIST) {
//            if (url.indexOf(whiteUrl) > -1) {
//                return null
//            }
//        }
//
//         获取Session
//        HttpSession session = request.getSession();
//        String username = (String) session.getAttribute("username");
//        if (username != null) {
//            return null
//        }
//
//         不符合条件的，返回401 Unauthorized
//        response.setStatus(401)
//        context.setSendZuulResponse(false)
        return null
    }

}
