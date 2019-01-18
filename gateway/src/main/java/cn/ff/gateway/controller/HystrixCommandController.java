package cn.ff.gateway.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HystrixCommandController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @RequestMapping("/hystrixTimeout")
    public String hystrixTimeout() {
        return "gateway触发了断路由";
    }

    @HystrixCommand(commandKey = "userHystrixCommand",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000")}
    )
    public Map userHystrixCommand() {
        Map<String, String> map = new HashMap<>();
        map.put("message", "gateway触发了userHystrixCommand");
        return map;
    }
}
