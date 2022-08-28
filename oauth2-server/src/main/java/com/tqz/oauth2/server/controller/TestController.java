package com.tqz.oauth2.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * @author tianqingzhao
 * @since 2022/8/27 10:33
 */
@RestController
public class TestController {
    
    @RequestMapping("test")
    public long test() {
        return System.currentTimeMillis();
    }
}
