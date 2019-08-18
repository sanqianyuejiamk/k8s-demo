package com.mengka.springboot.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author huangyy
 * @date 2017/12/12.
 */
@Log4j2
@RestController
public class DemoController {

    @RequestMapping("/")
    public String serverInfo(HttpServletRequest request) throws UnknownHostException {
        String resutl = "My IP  address is  a : " + InetAddress.getLocalHost().toString();
        log.info("------serverInfo resutl = " + resutl);
        return resutl;
    }


    @RequestMapping("/health")
    public String health(HttpServletRequest request) throws UnknownHostException {
        return "I am ok.";
    }
}
