package cn.harmonycloud.springcloud.bookmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BookmanagerController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private int port;

    @RequestMapping("/getbooklist")
    public String getBooksList() {
        return "ribbon " + port + ": " + restTemplate.getForEntity("http://service-bookstore/getbooklist", String.class).getBody();
    }

    @RequestMapping("/get")
    public String getBook() {
        return "ribbon " + port + ": " + restTemplate.getForEntity("http://service-bookstore/get", String.class).getBody();
    }

    @RequestMapping("/zero")
    public String zero() {
        return "ribbon " + port + ": " + restTemplate.getForEntity("http://service-bookstore/zero", Integer.class).getBody();
    }
}
