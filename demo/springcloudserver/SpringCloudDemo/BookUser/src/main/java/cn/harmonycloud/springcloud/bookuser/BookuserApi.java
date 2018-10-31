package cn.harmonycloud.springcloud.bookuser;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-bookstore")
public interface BookuserApi {
    @RequestMapping("/booklist")
    public String getBooksList();

    @RequestMapping("/get")
    public String getBook();

    @RequestMapping("/price")
    public double calculate(@RequestParam("price") double price, @RequestParam("count") int count);

    @RequestMapping("/zero")
    public int dividezero();

}