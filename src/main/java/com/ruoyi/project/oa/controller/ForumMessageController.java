package com.ruoyi.project.oa.controller;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.oa.domain.Forum;
import com.ruoyi.project.oa.domain.ForumMessage;
import com.ruoyi.project.oa.domain.ForumMessageRead;
import com.ruoyi.project.oa.domain.Task;
import com.ruoyi.project.oa.service.IForumMessageReadService;
import com.ruoyi.project.oa.service.IForumMessageService;
import com.ruoyi.project.oa.service.IForumService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 论坛帖子Controller
 * 
 * @author ruoyi
 * @date 2020-04-18
 */
@Controller
@RequestMapping("/oa/message")
public class ForumMessageController extends BaseController
{
    private String prefix = "oa/message";

    @Autowired
    private IForumMessageService forumMessageService;

    @Autowired
    private IForumService forumService;

    @Autowired
    private IForumMessageReadService forumMessageReadService;

    @RequiresPermissions("oa:message:view")
    @GetMapping()
    public String message(ModelMap model)
    {
        List<Forum> forumList = forumService.selectForumList(new Forum());
        model.addAttribute("forumList",forumList);
        return prefix + "/message";
    }

    /**
     * 查询论坛帖子列表
     */
    @RequiresPermissions("oa:message:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ForumMessage forumMessage)
    {
        startPage();
        List<ForumMessage> list = forumMessageService.selectForumMessageList(forumMessage);
        return getDataTable(list);
    }

    /**
     * 查询运维板块论坛帖子列表
     */
    @PostMapping("/classList")
    @ResponseBody
    public TableDataInfo classList(ForumMessage forumMessage)
    {
        startPage();
        List<ForumMessage> list = forumMessageService.selectClassForumMessageList(forumMessage);
        return getDataTable(list);
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
        forumMessage.setPlateType("2");
        forumMessage.setCreateUser(ShiroUtils.getUserId());
        forumMessage.setStatus(0L);
        List<ForumMessage> hotList = forumMessageService.selectForumMessageList(forumMessage);
        PageInfo pageInfo =  new PageInfo(hotList);
        return pageInfo;
    }

    /**
     * 导出论坛帖子列表
     */
    @RequiresPermissions("oa:message:export")
    @Log(title = "论坛帖子", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ForumMessage forumMessage)
    {
        List<ForumMessage> list = forumMessageService.selectForumMessageList(forumMessage);
        for (ForumMessage t: list) {
            String content = t.getContext();
            if (StringUtils.isNotEmpty(content)){
                t.setContext(StringUtils.stripHtml(content));
            }
        }
        ExcelUtil<ForumMessage> util = new ExcelUtil<ForumMessage>(ForumMessage.class);
        return util.exportExcel(list, "message");
    }

    /**
     * 新增论坛帖子
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存论坛帖子
     */
    @RequiresPermissions("oa:message:add")
    @Log(title = "论坛帖子", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ForumMessage forumMessage)
    {
        forumMessage.setCreateUser(ShiroUtils.getUserId());
        forumMessage.setCreateTime(new Date());
        forumMessage.setStatus(0L);
        forumMessage.setIsTalk(0L);
        forumMessage.setIsZd(0L);
        return toAjax(forumMessageService.insertForumMessage(forumMessage));
    }

    /**
     * 修改论坛帖子
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ForumMessage forumMessage = forumMessageService.selectForumMessageById(id);
        mmap.put("forumMessage", forumMessage);
        return prefix + "/edit";
    }

    /**
     * 修改保存论坛帖子
     */
    @RequiresPermissions("oa:message:edit")
    @Log(title = "论坛帖子", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ForumMessage forumMessage)
    {
        return toAjax(forumMessageService.updateForumMessage(forumMessage));
    }

    /**
     * 删除论坛帖子
     */
    @RequiresPermissions("oa:message:remove")
    @Log(title = "论坛帖子", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(forumMessageService.deleteForumMessageByIds(ids));
    }

    /**
     * 查看论坛帖子内容
     */
    @GetMapping("/query/{id}")
    public String query(@PathVariable("id") Long id, ModelMap mmap)
    {
        ForumMessage forumMessage = forumMessageService.selectForumMessageById(id);
        //查看帖子保存阅读
        ForumMessageRead forumMessageRead = new ForumMessageRead();
        forumMessageRead.setUserId(ShiroUtils.getUserId());
        forumMessageRead.setForumMessageId(forumMessage.getId());
        forumMessageRead.setForumId(forumMessage.getForumId());
        forumMessageRead.setStatus(0L);
        forumMessageReadService.insertForumMessageRead(forumMessageRead);
        mmap.put("forumMessage", forumMessage);
        return prefix + "/query";
    }
}
