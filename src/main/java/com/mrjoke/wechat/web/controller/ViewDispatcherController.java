package com.mrjoke.wechat.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewDispatcherController {

    /**
     * jsp视图转发
     *
     * @param view 目标视图文件名（不带后缀）
     * @return 返回目标视图
     **/
    @RequestMapping("/{view}")
    public String dispatcher(@PathVariable String view){
        return view;
    }
}
