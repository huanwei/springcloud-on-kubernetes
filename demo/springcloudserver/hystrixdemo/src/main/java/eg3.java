import com.netflix.hystrix.*;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


//线程池满触发降级

public class eg3 extends HystrixCommand<String> {

    private final String name;

    public eg3(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("Groupkey"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("CommandKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ThreadPool"))
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                                .withCoreSize(9)    // 配置线程池里的线程数
                )
        );
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        System.out.println(name);
        TimeUnit.MILLISECONDS.sleep(2000);
        return name;
    }

    @Override
    protected String getFallback() {
        return "fallback: " + name;
    }

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 30; i++) {
            Future<String> future = new eg3("thread " + i).queue();

        }
        for (int i = 0; i < 20; i++) {
            System.out.println("===========" + new eg3("thread" + i).execute());
        }
    }
}
