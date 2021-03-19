package rs.ac.bg.etf.webphoto.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String test() {
        return "Hello!";
    }

    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public String testPubblic() {
        return "Hello Reinhardt!";
    }

}
