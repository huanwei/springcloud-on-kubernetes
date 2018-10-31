import com.netflix.hystrix.*;

import java.util.concurrent.TimeUnit;

//熔断触发降级

public class eg2 extends HystrixCommand<String> {

    private final String name;

    protected eg2(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("Groupkey"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("CommandKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ThreadPool"))
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                                .withCoreSize(200)  // 配置线程池里的猪狗多线程数，以防线程池跑满却未熔断
                )
                .andCommandPropertiesDefaults(  // 配置熔断器
                        HystrixCommandProperties.Setter()
                                .withCircuitBreakerEnabled(true)//默认
                                .withCircuitBreakerRequestVolumeThreshold(3)//请求量阈值
                                .withCircuitBreakerErrorThresholdPercentage(50)
                )
        );
        this.name = name;
    }


    protected String run() throws Exception {

//        int num = Integer.valueOf(name);
//        if (num < 10) {
//            // 直接返回
//            System.out.println("ok :" + name+"     CircuitBreaker:"+this.isCircuitBreakerOpen());
//            return name;
//        } else {
        TimeUnit.MILLISECONDS.sleep(2000);
        return name;
//        }
    }

    @Override
    protected String getFallback() {
        return "fallback: " + name + "    CircuitBreaker:" + this.isCircuitBreakerOpen();
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println("===========" + new eg2(String.valueOf(i)).execute());

        }
    }
}
