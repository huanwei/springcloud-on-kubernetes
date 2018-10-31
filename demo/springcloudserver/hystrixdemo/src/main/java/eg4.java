import com.netflix.hystrix.*;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.netflix.hystrix.exception.HystrixTimeoutException;


//run依赖调用失败触发降级

public class eg4 extends HystrixCommand<String> {

    private final String name;

    protected eg4(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("Groupkey")));
        this.name = name;
    }


    protected String run() throws Exception {

        String param = null;
        param.trim();
        return name;
    }

    @Override
    protected String getFallback() {
        return "fallback: " + name;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("===========" + new eg4("test").execute());
    }
}
