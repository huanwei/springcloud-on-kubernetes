package cn.harmonycloud.springcloud.zuul.filter

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.netflix.config.ConfigurationManager
import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TesterFilter extends ZuulFilter {
    String allowOrigin = '*'

    @Override
    String filterType() {
        return "pre"
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
        RequestContext context = RequestContext.currentContext
        def request = context.getRequest()
        def response = context.getResponse()

        if (StringUtils.isNotBlank(allowOrigin)) {
            String origin = allowOrigin;
            String requestOrigin = request.getHeader("Origin");
            if (StringUtils.isNotBlank(requestOrigin)
                    && (allowOrigin.equals("*") || allowOrigin.indexOf(requestOrigin) > -1)) {
                origin = requestOrigin;
            }
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, PATCH");
            response.setHeader("Access-Control-Max-Age", "1200");
            response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Content-Type");
            response.setHeader("Access-Control-Allow-Credentials", "true");
        }

        JSONObject result = JSONObject.parseObject(new RestTemplate().getForObject("http://192.168.1.42:8761/eureka/apps/" + context.get("serviceId").toString() + "/", String.class))
        ConfigurationManager.getConfigInstance().setProperty(context.get("serviceId").toString() + ".ribbon.listOfServers", getServiceStr(result))

        String split = context.getZuulRequestHeaders().get("x-forwarded-prefix")
        String pix = context.getRequest().getRequestURI().split(split)[1]

        String url = "http://" + LoadbalancerFactory.get(context.get("serviceId").toString()) + pix
        println(url)

        HttpServletResponse resp = this.httpRequest(url, getVerb(context.getRequest()), context.getRequest().getParameterMap(), context.getResponse())
        context.setSendZuulResponse(false)

        return resp
    }

    static String getServiceStr(JSONObject obj) {
        StringBuffer sb = new StringBuffer()

        JSONArray resultList = obj.getJSONObject("application").getJSONArray("instance")
        for (Object rs : resultList) {
            JSONObject instaceService = (JSONObject) rs
            if (sb.length() != 0) {
                sb.append(",")
            }
            String ipAddress = instaceService.getString("ipAddr")
            sb.append(ipAddress + ":" + instaceService.getJSONObject("port").getString("\$"))
        }

        return sb.toString()
    }

    HttpServletResponse httpRequest(String requestUrl,
                                    String requestMethod,
                                    Map<String, String> requestParameters,
                                    HttpServletResponse response) throws IOException {
        try {
            URL url = new URL(requestUrl)
            HttpURLConnection conn = (HttpURLConnection) url.openConnection()
            conn.setDoOutput(true)
            conn.setDoInput(true)
            conn.setRequestMethod(requestMethod)
            conn.setRequestProperty("content-type", "text/html")
            // 使用输出流添加数据至请求体
            if (requestParameters.size() != 0) {
                String json = JSON.toJSON(requestParameters)
                OutputStream os = conn.getOutputStream()//获取输出流
                os.write(json.getBytes())//写入
                os.flush()
            }
            conn.connect() // 读取服务器端返回的内容
            InputStream is = conn.getInputStream()
            InputStreamReader isr = new InputStreamReader(is, "utf-8")
            BufferedReader br = new BufferedReader(isr)
            String line
            StringBuffer sb = new StringBuffer()

            while ((line = br.readLine()) != null) {
                sb.append(line)
            }
            response.getWriter().write(sb.toString())
            return response
        } catch (Exception e) {
            logger.error("http request exception (zuul)", e)
            throw e
        }
    }

    static String getVerb(HttpServletRequest request) {
        String method = request.getMethod()
        return method == null ? "GET" : method
    }
}