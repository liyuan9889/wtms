package com.ruoyi.project.oa.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.oa.domain.*;
import com.ruoyi.project.oa.service.*;
import com.ruoyi.project.system.dept.domain.Dept;
import com.ruoyi.project.system.dept.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 我的论坛Controller
 * 
 * @author youkun
 * @date 2020-04-18
 */
@Controller
@RequestMapping("/oa/myForumMessage")
public class MyForumMessageController extends BaseController
{
    private String prefix = "oa/myForumMessage";


    @Autowired
    private IForumMessageReadService forumMessageReadService;

    @Autowired
    private IForumMessageService forumMessageService;

    @Autowired
    private IForumNoticeService forumNoticeService;

    @Autowired
    private IForumMessageCollectionService forumMessageCollectionService;

    @Autowired
    private IForumMessageReplyService forumMessageReplyService;

    @Autowired
    private IDeptService deptService;


    /**
     * 查询收藏贴
     */
    @PostMapping("/collList")
    @ResponseBody
    public TableDataInfo list(ForumMessageCollection forumMessageCollection)
    {
        startPage();
        List<ForumMessageCollection> list = forumMessageCollectionService.selectForumMessageCollectionList(forumMessageCollection);
        return getDataTable(list);
    }


    @GetMapping()
    public String myForumMessage()
    {
        return prefix + "/index";
    }

    @GetMapping("/myReply")
    public String myReply(ForumMessageReply forumMessageReply)
    {
        return prefix + "/myReply";
    }

    @GetMapping("/myColl")
    public String myColl(ForumMessageCollection forumMessageCollection)
    {
        return prefix + "/myColl";
    }

    /**
     * 查询我的回复帖子列表
     */
    @PostMapping("/myReplylist")
    @ResponseBody
    public PageInfo myReplylist(ForumMessage forumMessage)
    {
        startPage();
        //查询帖子评论
        forumMessage.setPlateType("2");
        forumMessage.setCreateUser(ShiroUtils.getUserId());
        forumMessage.setStatus(0L);
        List<ForumMessage> list = forumMessageReplyService.selectMyForumMessageReplyList(forumMessage);
        PageInfo pageInfo =  new PageInfo(list);
        return pageInfo;
    }

    /**
     * 查询我的收藏帖子列表
     */
    @PostMapping("/myColllist")
    @ResponseBody
    public PageInfo myColllist(ForumMessage forumMessage)
    {
        startPage();
        //查询帖子评论
        forumMessage.setPlateType("2");
        forumMessage.setCreateUser(ShiroUtils.getUserId());
        forumMessage.setStatus(0L);
        List<ForumMessage> list = forumMessageCollectionService.selectMyForumMessageCollectionList(forumMessage);
        PageInfo pageInfo =  new PageInfo(list);
        return pageInfo;
    }



    @GetMapping("toClass")
    public String toClass(ModelMap modelMap, Model model)
    {
       //查询运维班
        List<Dept> deptYw = deptService.selectYw();
        modelMap.put("deptYw",deptYw);
        //帖子的数量
        ForumMessage forumMessage = new ForumMessage();
        modelMap.put("forumMessageNum",forumMessageService.selectClassForumMessageNum(forumMessage));
        //今日新增帖子数量
        ForumMessage forumMessage2 = new ForumMessage();
        forumMessage2.setStartDate(DateUtils.getDate());
        modelMap.put("forumMessageAddNum",forumMessageService.selectClassForumMessageNum(forumMessage2));
        //查询公告
        PageHelper.startPage(0, 5);
        modelMap.put("noticeList",forumNoticeService.selectForumNoticeList(new ForumNotice()));
        //最热
        ForumMessage forumMessageHot = new ForumMessage();
        PageHelper.startPage(0, 5);
        modelMap.put("hotForumMessageList",forumMessageService.selectClassForumMessageHotList(forumMessageHot));
        //精品
        ForumMessage forumMessage1 = new ForumMessage();
        forumMessage1.setIsTalk(1L);
        PageHelper.startPage(0, 5);
        modelMap.put("newForumMessageList",forumMessageService.selectClassForumMessageList(forumMessage1));
        //当前用户
        modelMap.put("userId", ShiroUtils.getSysUser().getUserId());
        return prefix + "/class";
    }

    @GetMapping("toSubstation")
    public String toSubstation(ModelMap modelMap)
    {
        //查询最近逛的论坛
        ForumMessageRead forumMessageRead = new ForumMessageRead();
        forumMessageRead.setUserId(ShiroUtils.getUserId());
        forumMessageRead.setPlateType("2");
        modelMap.put("forumReadList",forumMessageReadService.selectForum(forumMessageRead));
        //帖子的数量
        ForumMessage forumMessage = new ForumMessage();
        forumMessage.setPlateType("2");
        modelMap.put("forumMessageNum",forumMessageService.selectForumMessageNum(forumMessage));
        //今日新增帖子数量
        ForumMessage forumMessage2 = new ForumMessage();
        forumMessage2.setPlateType("2");
        forumMessage2.setStartDate(DateUtils.getDate());
        modelMap.put("forumMessageAddNum",forumMessageService.selectForumMessageNum(forumMessage2));
        //查询公告
        PageHelper.startPage(0, 5);
        modelMap.put("noticeList",forumNoticeService.selectForumNoticeList(new ForumNotice()));
        //最热
        ForumMessage forumMessageHot = new ForumMessage();
        forumMessageHot.setPlateType("2");
        PageHelper.startPage(0, 5);
        modelMap.put("hotForumMessageList",forumMessageService.selectForumMessageHotList(forumMessageHot));
        //精品
        ForumMessage forumMessage1 = new ForumMessage();
        forumMessage1.setPlateType("2");
        forumMessage1.setIsTalk(1L);
        PageHelper.startPage(0, 5);
        modelMap.put("newForumMessageList",forumMessageService.selectForumMessageList(forumMessage1));
        //当前用户
        modelMap.put("userId", ShiroUtils.getSysUser().getUserId());
        modelMap.put("plateType","1");
        return prefix + "/substation";
    }

    @GetMapping("hot/{type}")
    public String hot(@PathVariable("type") Long type, ModelMap modelMap){
        if(type==1){
            modelMap.put("title","最热");
        }else{
            modelMap.put("title","精品");
        }
        modelMap.put("type",type);
        return prefix + "/hot";
    }

    /**
     * 查询最热贴
     * @param forumMessage
     * @return
     */
    @PostMapping("hotList")
    @ResponseBody
    public PageInfo hotList(ForumMessage forumMessage){
        startPage();
        //查询帖子评论
        forumMessage.setPlateType("2");
        List<ForumMessage> hotList = forumMessageService.selectForumMessageHotList(forumMessage);
        PageInfo pageInfo =  new PageInfo(hotList);
        return pageInfo;
    }

    /**
     * 查询精品
     * @param forumMessage
     * @return
     */
    @PostMapping("jpList")
    @ResponseBody
    public PageInfo jpList(ForumMessage forumMessage){
        startPage();
        forumMessage.setPlateType("2");
        forumMessage.setIsTalk(1L);
        List<ForumMessage> jpList = forumMessageService.selectForumMessageList(forumMessage);
        PageInfo pageInfo =  new PageInfo(jpList);
        return pageInfo;
    }

}
