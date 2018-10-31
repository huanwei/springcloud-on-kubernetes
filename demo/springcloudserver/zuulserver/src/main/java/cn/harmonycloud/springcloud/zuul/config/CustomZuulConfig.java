package cn.harmonycloud.springcloud.zuul.config;

import cn.harmonycloud.springcloud.zuul.route.CustomRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by huan on 2018/10/17.
 */
@Configuration
public class CustomZuulConfig {
    @Autowired
    ZuulProperties zuulProperties;
    @Autowired
    ServerProperties server;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Bean
    public CustomRouteLocator routeLocator() {

        //get servletPath in spring-boot-autoconfigure-2.0.1.RELEASE
        //String servletPath = this.server.getServlet().getServletPrefix();

        //get servletPath in spring-boot-autoconfigure-1.5.2.RELEASE
        String servletPath = this.server.getServletPrefix();

        CustomRouteLocator routeLocator = new CustomRouteLocator(servletPath, this.zuulProperties);
        routeLocator.setJdbcTemplate(jdbcTemplate);
        return routeLocator;
    }
}
