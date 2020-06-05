package com.ruoyi.project.oa.controller;

import com.ruoyi.framework.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 论坛Controller
 * 
 * @author ruoyi
 * @date 2020-04-18
 */
@Controller
@RequestMapping("/oa/forumCreate")
public class ForumCreateController extends BaseController
{
    private String prefix = "oa/forumCreate";

    @GetMapping("/{id}")
    public String myForumMessage(@PathVariable("id") Long id, ModelMap mmap)
    {
        //论坛id
        mmap.put("forumId",id);
        return prefix + "/index";
    }
}
