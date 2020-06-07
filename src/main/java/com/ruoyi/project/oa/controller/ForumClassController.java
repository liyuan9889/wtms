package com.ruoyi.project.oa.controller;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.oa.domain.*;
import com.ruoyi.project.oa.service.*;
import com.ruoyi.project.system.dept.domain.Dept;
import com.ruoyi.project.system.dept.service.IDeptService;
import com.ruoyi.project.system.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 运维板块的方法
 */
@Controller
@RequestMapping("/oa/class")
public class ForumClassController extends BaseController {

    @Autowired
    private IDeptService deptService;

    @Autowired
    private IForumMessageService forumMessageService;

    @Autowired
    private IForumMessageCollectionService forumMessageCollectionService;

    @Autowired
    private IForumMessageReplyService forumMessageReplyService;

    @Autowired
    private IForumMessageReadService forumMessageReadService;

    @Autowired
    private IForumService forumService;

    @Autowired
    private IUserService userService;

    private String prefix = "oa/class";

    @GetMapping()
    public String classss(ModelMap model)
    {
        List<Dept> list = deptService.selectYw();
        model.addAttribute("forumList",list);
        return prefix + "/class";
    }

    /**
     * 我的帖子
     * @param model
     * @return
     */
    @GetMapping("/myMessage")
    public String myMessage(ModelMap model)
    {
        return prefix + "/myMessage";
    }

    /**
     * 查询我的论坛帖子列表
     */
    @PostMapping("/mylist")
    @ResponseBody
    public PageInfo myList(ForumMessage forumMessage)
    {
        startPage();
        //查询我的帖子
        forumMessage.setCreateUser(ShiroUtils.getUserId());
        forumMessage.setStatus(0L);
        List<ForumMessage> hotList = forumMessageService.selectClassForumMessageList(forumMessage);
        PageInfo pageInfo =  new PageInfo(hotList);
        return pageInfo;
    }
    /**
     * 我的收藏
     * @param model
     * @return
     */
    @GetMapping("/myColl")
    public String myColl(ModelMap model)
    {
        return prefix + "/myColl";
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
        forumMessage.setCreateUser(ShiroUtils.getUserId());
        forumMessage.setStatus(0L);
        List<ForumMessage> list = forumMessageCollectionService.selectMyClassForumMessageCollectionList(forumMessage);
        PageInfo pageInfo =  new PageInfo(list);
        return pageInfo;
    }

    /**
     * 我的回复
     * @param model
     * @return
     */
    @GetMapping("/myReply")
    public String myReply(ModelMap model)
    {
        return prefix + "/myReply";
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
        forumMessage.setCreateUser(ShiroUtils.getUserId());
        forumMessage.setStatus(0L);
        List<ForumMessage> list = forumMessageReplyService.selectMyClassForumMessageReplyList(forumMessage);
        PageInfo pageInfo =  new PageInfo(list);
        return pageInfo;
    }


    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 获取运维班下的变电站
     */
    @PostMapping("/getYwList")
    @ResponseBody
    public AjaxResult getYwList()
    {
        List<Dept> list = deptService.selectYw();
        return AjaxResult.success(list);
    }

    /**
     * 获取运维班下的变电站
     */
    @PostMapping("/getSubList")
    @ResponseBody
    public AjaxResult getSubList(Dept dept)
    {
        List<Dept> list = deptService.selectDeptList(dept);
        return AjaxResult.success(list);
    }

    @PostMapping("/getSubAllList")
    @ResponseBody
    public AjaxResult getSubAllList(Dept dept)
    {
        List<Dept> list = deptService.getSubAllList(dept);
        return AjaxResult.success(list);
    }

    @GetMapping("/replyIndex/{id}")
    public String  replyIndex(@PathVariable("id") Long id, ModelMap mmap){
        //查询帖子详情
        ForumMessage forumMessage =  forumMessageService.selectClassForumMessageById(id);
        mmap.put("forumMessage",forumMessage);
        //帖子id
        mmap.put("forumMessageId",forumMessage.getId());
        mmap.put("forumId",forumMessage.getForumId());
        //查看帖子保存阅读
        ForumMessageRead forumMessageRead = new ForumMessageRead();
        forumMessageRead.setUserId(ShiroUtils.getUserId());
        forumMessageRead.setForumMessageId(forumMessage.getId());
        forumMessageRead.setForumId(forumMessage.getForumId());
        forumMessageRead.setStatus(0L);
        forumMessageReadService.insertForumMessageRead(forumMessageRead);
        return prefix + "/replyIndex";
    }

    /**
     * 查询帖子的评论
     * @param forumMessage
     * @return
     */
    @PostMapping("replyList")
    @ResponseBody
    public PageInfo replyList(ForumMessage forumMessage){
        startPage();
        //查询帖子评论
        ForumMessageReply forumMessageReply = new ForumMessageReply();
        forumMessageReply.setForumMessageId(forumMessage.getId());
        forumMessageReply.setForumId(forumMessage.getForumId());
        List<ForumMessageReply> replyList = forumMessageReplyService.selectClassForumMessageReplyList(forumMessageReply);
        PageInfo pageInfo =  new PageInfo(replyList);
        return pageInfo;
    }

    @Log(title = "查看是否收藏")
    @PostMapping("/queryIsColl")
    @ResponseBody
    public AjaxResult queryIsColl(ForumMessageCollection forumMessageController)
    {
        //查看是否收藏
        forumMessageController.setUserId(ShiroUtils.getUserId());
        forumMessageController.setStatus(0L);
        List<ForumMessageCollection> forumMessageCollectionList = forumMessageCollectionService.selectClassForumMessageCollectionList(forumMessageController);
        if(null!=forumMessageCollectionList && forumMessageCollectionList.size()>0){
            return  AjaxResult.success(forumMessageCollectionList.get(0).getId());
        }else{
            return  AjaxResult.success(false);
        }
    }

    @GetMapping("/{id}")
    public String myForumMessage(@PathVariable("id") Long id, ModelMap mmap)
    {
        //查询论坛信息
        Dept dept = deptService.selectDeptById(id);
//        User user = userService.selectUserByLoginName(dept.getCreateBy());
        Forum forum = new Forum();
        forum.setId(dept.getDeptId());
        forum.setTitle(dept.getDeptName());
        forum.setCreateTime(dept.getCreateTime());
        mmap.put("forum",forum);
        //查询论坛的帖子数量
        ForumMessage forumMessage = new ForumMessage();
        forumMessage.setForumId(id);
        int messageNum = forumMessageService.selectClassForumMessageNum(forumMessage);
        mmap.put("messageNum",messageNum);
        //查询讨论区的帖子
//        ForumMessage zdForumMessage = new ForumMessage();
//        zdForumMessage.setForumId(id);
//        zdForumMessage.setIsTalk(0L);
//        List<ForumMessage> zdList = forumMessageService.getZdList(zdForumMessage);
//        mmap.put("zdList",zdList);
        //查询精品区
//        ForumMessage jpForumMessage = new ForumMessage();
//        jpForumMessage.setForumId(id);
//        jpForumMessage.setIsTalk(1L);
//        List<ForumMessage> jpList = forumMessageService.getZdList(jpForumMessage);
//        mmap.put("jpList",jpList);
        return prefix + "/index";
    }

    /**
     * 查询讨论区
     * @param forumMessage
     * @return
     */
    @PostMapping("talkList")
    @ResponseBody
    public PageInfo talkList(ForumMessage forumMessage){
        startPage();
        forumMessage.setIsTalk(0L);
        List<ForumMessage> zdList = forumMessageService.getClassZdList(forumMessage);
        PageInfo pageInfo =  new PageInfo(zdList);
        return pageInfo;
    }

    /**
     * 查询精品区
     * @param forumMessage
     * @return
     */
    @PostMapping("jpList")
    @ResponseBody
    public PageInfo jpList(ForumMessage forumMessage){
        startPage();
        forumMessage.setIsTalk(1L);
        List<ForumMessage> jpList = forumMessageService.getClassZdList(forumMessage);
        PageInfo pageInfo =  new PageInfo(jpList);
        return pageInfo;
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
        List<ForumMessage> hotList = forumMessageService.selectClassForumMessageHotList(forumMessage);
        PageInfo pageInfo =  new PageInfo(hotList);
        return pageInfo;
    }

    /**
     * 查询精品
     * @param forumMessage
     * @return
     */
    @PostMapping("jpqList")
    @ResponseBody
    public PageInfo jpqList(ForumMessage forumMessage){
        startPage();
        forumMessage.setIsTalk(1L);
        List<ForumMessage> jpList = forumMessageService.selectClassForumMessageList(forumMessage);
        PageInfo pageInfo =  new PageInfo(jpList);
        return pageInfo;
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
     * 获取帖子数量
     * @return
     */
    @PostMapping("getMessageNum")
    @ResponseBody
    public AjaxResult getMessageNum(ForumMessage forumMessage){
       return AjaxResult.success(forumMessageService.selectClassForumMessageNum(forumMessage));
    }

    /**
     * 今日新增帖子数量
     * @return
     */
    @PostMapping("getMessageAddNum")
    @ResponseBody
    public AjaxResult getMessageAddNum(ForumMessage forumMessage){
        forumMessage.setStartDate(DateUtils.getDate());
        return AjaxResult.success(forumMessageService.selectClassForumMessageNum(forumMessage));
    }

    /**
     * 获取最近一个月浏览的论坛
     * @return
     */
    @GetMapping("getLastMonth")
    @ResponseBody
    public AjaxResult getLastMonth(){
        Date date = new Date();//获取当前时间    
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);//当前时间前去一个月，即一个月前的时间    
        //calendar.getTime();//获取一年前的时间，或者一个月前的时间  
        return  AjaxResult.success(forumService.getClassLastMonth(DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss",calendar.getTime()),String.valueOf(ShiroUtils.getUserId())));
    }


}
