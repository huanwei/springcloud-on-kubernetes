package cn.harmonycloud.springcloud.zuul.filter

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.springframework.stereotype.Component

@Component
class MyFilter extends ZuulFilter {
    @Override
    String filterType() {
        return "pre"
    }

    @Override
    int filterOrder() {
        return 0
    }

    @Override
    boolean shouldFilter() {
        return true
    }

    @Override
    Object run() {
        RequestContext context = RequestContext.getCurrentContext()
        def request = context.getRequest()
        def pwd = request.getParameter("pwd")
        if(!"123".equals(pwd)){
            context.setSendZuulResponse(false)
            context.setResponseStatusCode(401)
            context.getResponse().getWriter().write("this is filter")
        }
        return null
    }
}
