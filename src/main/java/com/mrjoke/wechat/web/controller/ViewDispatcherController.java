package com.mrjoke.wechat.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewDispatcherController {

    @RequestMapping("/{view}")
    public String dispatcher(@PathVariable String view){
        return view;
    }
}
