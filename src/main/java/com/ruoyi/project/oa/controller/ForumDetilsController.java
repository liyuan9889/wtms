package com.ruoyi.project.oa.controller;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.oa.domain.*;
import com.ruoyi.project.oa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 论坛Controller
 * 
 * @author ruoyi
 * @date 2020-04-18
 */
@Controller
@RequestMapping("/oa/forumDetails")
public class ForumDetilsController extends BaseController
{
    private String prefix = "oa/forumDetails";

    @Autowired
    private IForumService forumService;

    @Autowired
    private IForumMessageService forumMessageService;

    @Autowired
    private IForumMessageReplyService forumMessageReplyService;

    @Autowired
    private IForumMessageReadService forumMessageReadService;

    @Autowired
    private IForumMessageCollectionService forumMessageCollectionService;

    @GetMapping("/{id}")
    public String myForumMessage(@PathVariable("id") Long id, ModelMap mmap)
    {
        //查询论坛信息
        Forum forum = forumService.selectForumById(id);
        mmap.put("forum",forum);
        //查询论坛的帖子数量
        ForumMessage forumMessage = new ForumMessage();
        forumMessage.setForumId(id);
        int messageNum = forumMessageService.selectForumMessageNum(forumMessage);
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
        List<ForumMessage> zdList = forumMessageService.getZdList(forumMessage);
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
        List<ForumMessage> jpList = forumMessageService.getZdList(forumMessage);
        PageInfo pageInfo =  new PageInfo(jpList);
        return pageInfo;
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
        List<ForumMessageReply> replyList = forumMessageReplyService.selectForumMessageReplyList(forumMessageReply);
        PageInfo pageInfo =  new PageInfo(replyList);
        return pageInfo;
    }

    @GetMapping("/replyIndex/{id}")
    public String  replyIndex(@PathVariable("id") Long id,ModelMap mmap){
        //查询帖子详情
       ForumMessage forumMessage =  forumMessageService.selectForumMessageById(id);
       mmap.put("forumMessage",forumMessage);
       //帖子id
        mmap.put("forumMessageId",forumMessage.getId());
        mmap.put("forumId",forumMessage.getForumId());
       //查询帖子评论
        ForumMessageReply forumMessageReply = new ForumMessageReply();
        forumMessageReply.setForumMessageId(id);
        forumMessageReply.setForumId(forumMessage.getForumId());
        mmap.put("replyList",forumMessageReplyService.selectForumMessageReplyList(forumMessageReply));
//        //查看是否收藏
//        ForumMessageCollection forumMessageController = new ForumMessageCollection();
//        forumMessageController.setForumMessageId(forumMessage.getId());
//        forumMessageController.setUserId(ShiroUtils.getUserId());
//        forumMessageController.setStatus(0L);
//        List<ForumMessageCollection> forumMessageCollectionList = forumMessageCollectionService.selectForumMessageCollectionList(forumMessageController);
//        if(null!=forumMessageCollectionList && forumMessageCollectionList.size()>0){
//            mmap.put("collId",forumMessageCollectionList.get(0).getId());
//        }else{
//            mmap.put("collId",null);
//        }
        //查看帖子保存阅读
        ForumMessageRead forumMessageRead = new ForumMessageRead();
        forumMessageRead.setUserId(ShiroUtils.getUserId());
        forumMessageRead.setForumMessageId(forumMessage.getId());
        forumMessageRead.setForumId(forumMessage.getForumId());
        forumMessageRead.setStatus(0L);
        forumMessageReadService.insertForumMessageRead(forumMessageRead);
        return prefix + "/replyIndex";
    }

    //保存回帖
    @Log(title = "保存回帖", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ForumMessageReply forumMessageReply)
    {
        return toAjax(forumMessageReplyService.insertForumMessageReply(forumMessageReply));
    }

    //保存回帖forumDetails
    @Log(title = "保存收藏", businessType = BusinessType.INSERT)
    @PostMapping("/addColl")
    @ResponseBody
    public AjaxResult addColl(ForumMessageCollection forumMessageCollection)
    {
        if(forumMessageCollection.getId()!=null){
            //删除
            forumMessageCollection.setStatus(1L);
            forumMessageCollection.setUpdateUser(ShiroUtils.getUserId());
            forumMessageCollection.setUpdateTime(new Date());
            forumMessageCollectionService.updateForumMessageCollection(forumMessageCollection);
        }else{
            //添加
            forumMessageCollection.setStatus(0L);
            forumMessageCollection.setUserId(ShiroUtils.getUserId());
            forumMessageCollection.setCreateTime(new Date());
            forumMessageCollectionService.insertForumMessageCollection(forumMessageCollection);
        }
        return toAjax(true);
    }

    @Log(title = "查看是否收藏", businessType = BusinessType.INSERT)
    @PostMapping("/queryIsColl")
    @ResponseBody
    public AjaxResult queryIsColl(ForumMessageCollection forumMessageController)
    {
        //查看是否收藏
        forumMessageController.setUserId(ShiroUtils.getUserId());
        forumMessageController.setStatus(0L);
        List<ForumMessageCollection> forumMessageCollectionList = forumMessageCollectionService.selectForumMessageCollectionList(forumMessageController);
        if(null!=forumMessageCollectionList && forumMessageCollectionList.size()>0){
            return  AjaxResult.success(forumMessageCollectionList.get(0).getId());
        }else{
            return  AjaxResult.success(false);
        }
    }
}
