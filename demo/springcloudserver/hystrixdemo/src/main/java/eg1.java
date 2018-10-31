import com.netflix.hystrix.*;

import java.util.Arrays;
import java.util.List;


//1.判断熔断器是否打开
//2.判断线程池/信号量是否跑满
//3.调用HystrixCommand的run方法，运行依赖逻辑，超时
//4.依赖逻辑调用时失败
//降级


//超时触发熔断

public class eg1 extends HystrixCommand<String> {
    private int timeIndex;
    public static List<Integer> times = Arrays.asList(100, 200, 300, 400, 500, 600);


    protected eg1(int timeIndex) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("Groupkey"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("CommandKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ThreadPool"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutInMilliseconds(400)//超时时间
                )
        );

        this.timeIndex = timeIndex;
    }

    @Override
    protected String getFallback() {

        return "fallback    ";
    }

    @Override
    protected String run() {
        try {
            Thread.currentThread().sleep(times.get(this.getTimeIndex()));
        } catch (InterruptedException e) {
        }
        return "ok    ";
    }

    public int getTimeIndex() {
        return timeIndex;
    }

    public static void main(String[] args) {
        int count = 0;
        while (count < 6) {
            System.out.println("===========" + new eg1(count % 7).execute());
            count++;
        }
    }
}

