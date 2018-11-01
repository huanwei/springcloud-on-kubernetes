package cn.harmonycloud.springcloud.zuul.filter;

import com.netflix.client.ClientFactory;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;

import java.util.HashMap;
import java.util.Map;

public class LoadbalancerFactory {
    //    private static ILoadBalancer loadBalancer;
    private static Map<String, ILoadBalancer> balancerMap = new HashMap<String, ILoadBalancer>();
    //    private static IRule chooseRule = new RoundRobinRule();
    private static Map<String, IRule> chooseRuleMap = new HashMap<String, IRule>();


    public static String get(String serviceName) {
        if (!chooseRuleMap.containsKey(serviceName)) {
            System.out.println("no serviceName !");
            init(serviceName);
        }
        return chooseRuleMap.get(serviceName).choose(null).getHostPort();
    }

    private static void init(String serviceName) {
        balancerMap.put(serviceName, ClientFactory.getNamedLoadBalancer(serviceName));
        IRule rule = new RoundRobinRule();
        rule.setLoadBalancer(balancerMap.get(serviceName));
        chooseRuleMap.put(serviceName, rule);
    }
}
