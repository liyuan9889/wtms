package com.ruoyi.project.oa.controller;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.oa.domain.ForumNotice;
import com.ruoyi.project.oa.service.IForumNoticeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 论坛公告Controller
 * 
 * @author ruoyi
 * @date 2020-04-25
 */
@Controller
@RequestMapping("/oa/notice")
public class ForumNoticeController extends BaseController
{
    private String prefix = "oa/notice";

    @Autowired
    private IForumNoticeService forumNoticeService;

    @RequiresPermissions("oa:notice:view")
    @GetMapping()
    public String notice()
    {
        return prefix + "/notice";
    }

    @GetMapping("/noticeList")
    public String noticeList()
    {
        return prefix + "/noticeList";
    }
    /**
     * 查询论坛公告列表
     */
    @RequiresPermissions("oa:notice:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ForumNotice forumNotice)
    {
        startPage();
        List<ForumNotice> list = forumNoticeService.selectForumNoticeList(forumNotice);
        return getDataTable(list);
    }

    /**
     * 查询论坛公告列表
     */
    @PostMapping("/getlist")
    @ResponseBody
    public PageInfo getlist(ForumNotice forumNotice)
    {
        startPage();
        List<ForumNotice> list = forumNoticeService.selectForumNoticeList(forumNotice);
        PageInfo pageInfo =  new PageInfo(list);
        return pageInfo;
    }
    /**
     * 导出论坛公告列表
     */
    @RequiresPermissions("oa:notice:export")
    @Log(title = "论坛公告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ForumNotice forumNotice)
    {
        List<ForumNotice> list = forumNoticeService.selectForumNoticeList(forumNotice);
        for (ForumNotice t: list) {
            String content = t.getContent();
            if (StringUtils.isNotEmpty(content)){
                t.setContent(StringUtils.stripHtml(content));
            }
        }
        ExcelUtil<ForumNotice> util = new ExcelUtil<ForumNotice>(ForumNotice.class);
        return util.exportExcel(list, "notice");
    }

    /**
     * 新增论坛公告
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存论坛公告
     */
    @RequiresPermissions("oa:notice:add")
    @Log(title = "论坛公告", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ForumNotice forumNotice)
    {
        return toAjax(forumNoticeService.insertForumNotice(forumNotice));
    }

    /**
     * 修改论坛公告
     */
    @GetMapping("/edit/{forumNoticeId}")
    public String edit(@PathVariable("forumNoticeId") Long forumNoticeId, ModelMap mmap)
    {
        ForumNotice forumNotice = forumNoticeService.selectForumNoticeById(forumNoticeId);
        mmap.put("forumNotice", forumNotice);
        return prefix + "/edit";
    }

    /**
     * 修改保存论坛公告
     */
    @RequiresPermissions("oa:notice:edit")
    @Log(title = "论坛公告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ForumNotice forumNotice)
    {
        return toAjax(forumNoticeService.updateForumNotice(forumNotice));
    }

    /**
     * 删除论坛公告
     */
    @RequiresPermissions("oa:notice:remove")
    @Log(title = "论坛公告", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(forumNoticeService.deleteForumNoticeByIds(ids));
    }

    /**
     * 修改论坛公告
     */
    @GetMapping("/query/{forumNoticeId}")
    public String query(@PathVariable("forumNoticeId") Long forumNoticeId, ModelMap mmap)
    {
        ForumNotice forumNotice = forumNoticeService.selectForumNoticeById(forumNoticeId);
        mmap.put("forumNotice", forumNotice);
        return prefix + "/query";
    }
}
